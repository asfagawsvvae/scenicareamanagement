<template>
  <el-card class="card_1">
    <template #header>
      <div class="card-header">
        <span class="card-header-text">景区管理</span>
        <el-button class="card-header-button" @click="visibleDrawer=true; drawerTitle='新增景区'; visibleUrl=true;">新增景区</el-button>
      </div>
    </template>

    <el-form :inline="true" :model="searchForm" class="demo-form-inline">
      <el-form-item label="景区名称">
        <el-input v-model="searchScenicAreaName" placeholder="请输入景区名称" clearable @clear="scenicAreaList"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="scenicAreaList">搜索</el-button>
        <!--        <el-button type="primary" @click="scenicAreaList">搜索</el-button>-->
      </el-form-item>
    </el-form>

    <el-table :data="scenicAreas" style="width: 100%;height: 100%" class="table-column-text">
      <el-table-column prop="scenicAreaName" label="景区名称" width="120"/>
      <el-table-column prop="maxCapacity" label="最大客容量" width="120"/>
      <el-table-column prop="parkingSpaces" label="停车位数目" width="120"/>
      <el-table-column prop="address" label="景区地址" width="300"/>
      <el-table-column prop="ticketPrice" label="景区票价" width="150"/>
      <el-table-column prop="description" label="景区描述" width="200"/>
      <el-table-column fixed="right" label="操作" min-width="200">
        <template #default="{row}">
          <el-button link type="primary" size="small" @click="handleEdit(row)" class="table-column-button">
            编辑
          </el-button>
          <el-button link type="danger" size="small" @click="handleDelete(row)" class="table-column-button">
            删除
          </el-button>

          <el-button link type="info" size="small" @click="showRating(row)" class="table-column-button">
            查看评分
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        v-model:current-page="page"
        v-model:page-size="limit"
        :page-sizes="[10, 20, 50, 100]"
        layout="total, sizes, prev, pager, next, jumper"
        :total=total
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        style="margin-top: 20px;justify-content: flex-end"
    />
  </el-card>


  <el-drawer
      v-model="visibleDrawer"
      :title="drawerTitle"
      size="30%"
      @close="clearData"

  >
    <el-form :model="scenicAreaModel" label-width="auto" style="max-width: 600px">
      <el-form-item label="景区名:">
        <el-input v-model="scenicAreaModel.scenicAreaName" placeholder="请输入景区名"/>
      </el-form-item>
      <el-form-item label="最大容客量:">
        <el-input v-model="scenicAreaModel.maxCapacity" placeholder="请输入景区最大容客量"/>
      </el-form-item>
      <el-form-item label="停车位数目:">
        <el-input v-model="scenicAreaModel.parkingSpaces" placeholder="请输入景区停车位数目"/>
      </el-form-item>
      <el-form-item label="景区地址:">
        <el-input v-model="scenicAreaModel.address" placeholder="请输入景区地址"/>
      </el-form-item>
      <el-form-item label="景区票价:">
        <el-input v-model="scenicAreaModel.ticketPrice" placeholder="请输入景区票价"/>
      </el-form-item>
      <el-form-item label="景区描述:">
        <el-input v-model="scenicAreaModel.description" placeholder="请输入景区描述"/>
      </el-form-item>
      <el-form-item label="是否在境内">
        <el-select v-model="scenicAreaModel.international" placeholder="请选择是否在境内">
          <el-option label="是" value="1" />
          <el-option label="否" value="0" />
        </el-select>
      </el-form-item>
      <el-form-item label="景区图片:" v-show="visibleUrl">
<!--        <el-input v-model="scenicAreaModel.imageUrl" placeholder="请输入景区图片url"/>-->

        <el-upload
            ref="upload"
            class="upload-demo"
            action="http://localhost:8080/uploads"
            :limit="300"
            :on-exceed="handleExceed"
            :auto-upload="false"
            :show-file-list="flag"
            :on-change="handleChange"

        >
          <template #trigger>
            <el-button type="primary" @click="change">添加图片</el-button>
          </template>


        </el-upload>

      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="drawerTitle==='新增景区'?doAddScenicArea():doUpdateScenicArea()">提交</el-button>

        <el-button @click="cancelAdd">取消</el-button>
      </el-form-item>
    </el-form>
  </el-drawer>

  <el-dialog v-model="visibleDialog" :title="dialogTitle + '评分'" style="width: 300px" @close="clearRating">
    <el-rate
        v-model="rating.rate"
        disabled
        show-score
        text-color="#ff9900"
    />
    <template #footer>
      <el-button @click="visibleDialog=false; clearRating()">关闭</el-button>
    </template>
  </el-dialog>

</template>
<script setup lang="ts">
import {ref} from "vue";
import {
  addScenicArea,
  deleteScenicArea,
  getScenicAreaList,
  updateScenicArea,
  getRateByName
} from "@/api/getData.js";
import {ElMessage, ElMessageBox,genFileId} from "element-plus";
import type { UploadInstance, UploadProps, UploadRawFile } from 'element-plus'
const upload = ref<UploadInstance>()
const fileName = ref();
const flag = ref(false)
const scenicAreas = ref([]);

const searchScenicAreaName = ref('');

const visibleDrawer = ref(false);

const drawerTitle = ref('');

const visibleUrl = ref(true);

const searchForm = ref({});

const page = ref(1);

const total = ref(10);

const limit = ref(10);
const visibleDialog = ref(false);

const dialogTitle = ref('');

const rating = ref({
  rate:0
})

const clearRating = () => {
  rating.value = {
    rate:0
  }
}
const change=()=>{
  flag.value = true;
}
const handleSizeChange = (size) => {
  limit.value = size;
  scenicAreaList();
}


const handleCurrentChange = (num) => {
  page.value = num;
  scenicAreaList();
}

const showRating = (row) => {
  visibleDialog.value = true;
  dialogTitle.value = row.scenicAreaName;
  getRateByName(dialogTitle.value).then( (res) => {
    if (res.code === 200) {
      rating.value.rate = res.datas;
      console.log(rating.value.rate);
    }
  })
}
const scenicAreaList = () => {
  let params = {
    scenicAreaName: searchScenicAreaName.value,
    page: page.value,
    limit: limit.value
  }
  console.log(params.scenicAreaName)
  getScenicAreaList(params).then((res)=>{
    if (res.code === 200) {
      scenicAreas.value=res.datas;
      total.value=res.total;
    }
  })
}

const scenicAreaModel = ref({
  scenicAreaName:'',
  scenicAreaCode:'',
  maxCapacity:'',
  parkingSpaces:'',
  address:'',
  ticketPrice:'',
  imageUrl:'',
  description:'',
  international:''
})

const clearData = () => {
  scenicAreaModel.value = {
    scenicAreaName:'',
    scenicAreaCode:'',
    maxCapacity:'',
    parkingSpaces:'',
    address:'',
    ticketPrice:'',
    imageUrl:'',
    description:'',
    international:''
  }
}

const cancelAdd = () => {
  clearData();
  visibleDrawer.value = false;
}

const handleEdit = (row) => {
  visibleDrawer.value = true;
  visibleUrl.value = false;
  drawerTitle.value = "编辑景区信息";
  scenicAreaModel.value = {...row};

}
const handleDelete = (row) => {
  ElMessageBox.confirm(
      '确定删除景区\"' + row.scenicAreaName + '\"吗？',
      '提示',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }
  ).then(() => {
    deleteScenicArea(row.scenicAreaName).then( (res) => {
      if (res.code === 200) {
        scenicAreaList();
        ElMessage({
          type: 'success',
          message: '删除成功',
        })
      }
    });
  }).catch(() => {
    ElMessage({
      type: 'info',
      message: '取消删除',
    })
  })
}
const handleExceed: UploadProps['onExceed'] = (files) => {
  upload.value!.clearFiles()
  const file = files[0] as UploadRawFile
  file.uid = genFileId()
  upload.value!.handleStart(file)
}
const doAddScenicArea = () => {
  if (scenicAreaModel.value.scenicAreaName === '') {
    ElMessage.warning("景区名称不能为空!")
    return;
  }else {
    upload.value!.submit()
    flag.value=false
    addScenicArea(scenicAreaModel.value).then((res)=>{
      if (res.code === 200){
        ElMessage.success("添加成功")
      }
      clearData();
      visibleDrawer.value = false;
      scenicAreaList();
    })
  }
}

const doUpdateScenicArea = () => {
  updateScenicArea(scenicAreaModel.value).then((res)=>{
    if (res.code === 200){
      ElMessage.success("编辑成功")
    }
    clearData();
    visibleDrawer.value = false;
    scenicAreaList();
  })
}
const handleChange = (file) => {
  // 获取文件名
  fileName.value = file.name;
  // scenicAreaModel.imageUrl.value = fileName.value;
  scenicAreaModel.value.imageUrl = 'http://localhost:8080/images/'+fileName.value;


};
scenicAreaList();

</script>
<style scoped>
.card-header{
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.card-header-text{
  font-weight: bold; /* 加粗 */
  font-size: 1.5em; /* 放大，这里使用了相对大小，可以根据需要调整 */
}
.card-header-button {
  padding: 1.5em 3.0em;
  //font-size: 12px;
  text-transform: uppercase;
  //letter-spacing: 2.5px;
  //font-weight: 500;
  color: #000;
  background-color: #fff;
  //border: none;
  border-radius: 45px;
  box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease 0s;
  cursor: pointer;
  outline: none;
}

.card-header-button:hover {
  background-color: #3dc1e1;
  box-shadow: 0px 15px 20px rgba(0, 240, 255, 0.4);
  color: #fff;
  transform: translateY(-7px);
}

.card-header-button:active {
  transform: translateY(-1px);
}
.table-column-text{
  font-weight: bold; /* 加粗 */
  font-size: 1.2em; /* 放大，这里使用了相对大小，可以根据需要调整 */
}
.table-column-button{
  font-weight: bold; /* 加粗 */
  font-size: 0.9em; /* 放大，这里使用了相对大小，可以根据需要调整 */
}

</style>
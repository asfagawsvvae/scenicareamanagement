<script setup>
  import {ElMessage, ElMessageBox} from "element-plus";
  import {
    addComplaint,
    deleteComplaint,
    getComplaintListByType,
    getComplaintListByUsername,
    getHotels,
    getScenicAreas
  } from "@/api/getData.js";
  import {delStorage, getStorage, setStorage} from "@/utils/localStorage.js";
  import {ref} from "vue";
  import {createRouter as transformedArray} from "vue-router";

  const complaintsByName = ref([]);

  const page = ref(1);

  const total = ref(10);

  const limit = ref(10);

  const name = getStorage("username");

  const scenicAreas = ref([]);

  const hotels = ref([]);

  const visibleDrawer = ref(false);

  const firstValue = ref('');

  const complaintModel = ref({
    username: name,
    targetType: '',
    targetName: '',
    content: ''
  });

  const clearData = () => {
    complaintModel.value = {
      username: name,
      targetType: '',
      targetName: '',
      content: ''
    }
  }

  const cancelAdd = () => {
    clearData();
    visibleDrawer.value = false;
  }

  const handleSizeChange = (size) => {
    limit.value = size;
    complaintListByName();
  }

  const handleCurrentChange = (num) => {
    page.value = num;
    complaintListByName();
  }

  const getScenicAreaNames = () => {
    console.log(name)
    getScenicAreas().then( (res) => {
      if (res.code === 200) {
        scenicAreas.value = res.datas.map(name=>({
          label: name,
          value: name
        }))
      }
    })
  }

  const getHotelNames = () => {
    getHotels().then( (res) => {
      if (res.code === 200) {
        hotels.value = res.datas.map(name=>({
          label: name,
          value: name
        }));
      }
    })
  }
  const complaintListByName = () => {
    let params = {
      username: name,
      page: page.value,
      limit: limit.value

    };
    // console.log(scenicAreas);
    // console.log(hotels);
    getComplaintListByUsername(params).then((res)=>{
      if (res.code === 200) {
        complaintsByName.value=res.datas;
        total.value=res.total;
      }
    })
  }
  const handleDelete = (row) =>{
    ElMessageBox.confirm(
      '确定删除该条投诉吗？',
      '提示',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }
    ).then(() => {
      deleteComplaint(row.complaintId).then( (res) => {
        if (res.code === 200) {
          complaintListByName();
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
  const doAddComplaint = () => {
    if (complaintModel.value.targetType === ''){
      ElMessage.warning("请选择投诉对象类型!");
      return;
    }else if (complaintModel.value.targetName === ''){
      ElMessage.warning("请选择投诉对象!");
      return;
    }else {
      console.log(complaintModel.value.username)
      addComplaint(complaintModel.value).then(res => {
        if (res.code === 200) {
          ElMessage.success("投诉成功");
        }
        visibleDrawer.value = false;
        complaintListByName();
        clearData();
      })
    }
  }

  // const test = () => {
  //   console.log(getStorage("Token"))
  //   console.log(getStorage("UserInfo"))
  // }

  complaintListByName();

  getScenicAreaNames();

  getHotelNames();

</script>

<template>
  <el-card class="card_1">
    <template #header>
      <div class="card-header">
        <span class="card-header-text">我的投诉</span>
        <el-button class="card-header-button" @click="visibleDrawer=true">发起投诉</el-button>
      </div>
    </template>

    <el-table :data="complaintsByName" style="width: 100%;height: 100%" class="table-column-text">
      <el-table-column prop="targetType" label="投诉对象类型" width="450"/>
      <el-table-column prop="targetName" label="投诉对象" width="450"/>
      <el-table-column prop="content" label="投诉内容" width="450"/>
      <el-table-column fixed="right" label="操作" min-width="200">
        <template #default="{row}">
          <el-button link type="danger" size="small" @click="handleDelete(row)" class="table-column-button">
            删除
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
      title="发起投诉"
      size="30%"
      @close="clearData"

  >
    <el-form :model="complaintModel" label-width="auto" style="max-width: 600px">

      <el-form-item label="投诉对象类型">
        <el-select v-model="complaintModel.targetType" placeholder="请选择投诉对象类型" @change="firstValue=complaintModel.targetType">
          <el-option label="景区" value="景区"/>
          <el-option label="酒店" value="酒店"/>
        </el-select>
      </el-form-item>
      <el-form-item label="投诉对象名称">
        <el-select v-model="complaintModel.targetName" placeholder="请选择投诉对象" :disabled="complaintModel.targetType === ''">
          <el-option
              v-for="option in firstValue==='酒店'?hotels:scenicAreas"
              :label="option.label"
              :value="option.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="投诉内容">
        <el-input v-model="complaintModel.content" type="textarea" placeholder="请输入投诉内容"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="doAddComplaint()">提交</el-button>
        <el-button @click="cancelAdd">取消</el-button>
      </el-form-item>
    </el-form>
  </el-drawer>
</template>

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
.card-header-button{
  padding: 1.5em 3.0em;  //font-size: 12px;
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
.card-header-button:hover{
  background-color: #3dc1e1;
  box-shadow: 0px 15px 20px rgba(0, 240, 255, 0.4);
  color: #fff;
  transform: translateY(-7px);
}
.card-header-button:active{
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
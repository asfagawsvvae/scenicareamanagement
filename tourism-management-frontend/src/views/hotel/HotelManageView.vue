<template>
  <el-card >
    <template #header>
      <div class="card-header">
        <span>酒店管理</span>
        <el-button class="add-btn" type="primary" @click="visibleDrawer=true;clearData(); drawerTitle='新增酒店'" style="font-size: 0.9em">新增酒店</el-button>
      </div>
    </template>

    <!--  搜索表单-->
    <el-form :inline="true" :model="searchForm" class="demo-form-inline">
      <el-form-item >
        <el-input v-model="searchHotelName" placeholder="请输入酒店信息" clearable @clear="hotelList"/>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="hotelList">搜索</el-button>
      </el-form-item>
    </el-form>
    <!--  酒店列表-->
    <el-table :data="hotels" style="width: 100% ;font-size: 1.2em;font-weight: bolder">
      <el-table-column fixed prop="hotelName" label="酒店名字" width="150"/>
      <el-table-column prop="maxCapacity" label="最大客容量" width="120"/>
      <el-table-column prop="parkingSpaces" label="停车位" width="80"/>
      <el-table-column prop="maxRooms" label="房间总数" width="120"/>
      <el-table-column prop="location" label="所属景区" width="100"/>
      <el-table-column prop="address" label="酒店地址" width="400"/>
      <el-table-column fixed="right" label="操作" min-width="130">
        <template #default="{row}">
          <el-button link type="primary" style="font-size: 0.9em;font-weight: bolder" @click="handleEdit(row)">
            编辑
          </el-button>
          <el-button link type="danger" style="font-size: 0.9em;font-weight: bolder" @click="handleDelete(row)">
            删除
          </el-button>
          <el-button link type="info" style="font-size: 0.9em;font-weight: bolder" @click="handleShowScore(row)">
            查看评分
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--  分页-->
    <el-pagination
        v-model:current-page="page"
        v-model:page-size="limit"
        :page-sizes="[20, 50, 100, 200]"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        style="margin-top: 20px; justify-content: flex-end"
    />
    <!--  抽屉-->
    <el-drawer
        v-model="visibleDrawer"
        :title="drawerTitle"
        size="35%"
    >
      <el-form
          :model="hotelModel"
          label-width="auto"
          style="max-width: 600px"
      >
        <el-form-item label="酒店名">
          <el-input v-model="hotelModel.hotelName" placeholder="请输入酒店名"/>
        </el-form-item>
        <el-form-item label="最大客容量">
          <el-input v-model="hotelModel.maxCapacity" placeholder="请输入最大客容量"/>
        </el-form-item>
        <el-form-item label="停车位">
          <el-input v-model="hotelModel.parkingSpaces" placeholder="请输入酒店停车位数"/>
        </el-form-item>
        <el-form-item label="房间总数">
          <el-input v-model="hotelModel.maxRooms" placeholder="请输入酒店房间总数"/>
        </el-form-item>
        <el-form-item label="所属景区">
          <el-select v-model="hotelModel.location">
            <el-option v-for="(item) in scenics" :value="item.scenicAreaName" :label="item.scenicAreaName" :key="item.scenicAreaName"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="酒店地址">
          <el-input v-model="hotelModel.address" placeholder="请输入酒店地址"/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="drawerTitle === '新增酒店' ? doAddHotel() : doUpdateHotel()"
                     class="submit-btn">提交
          </el-button>
        </el-form-item>
      </el-form>
    </el-drawer>
  </el-card>

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

<script setup>

//酒店数据
import {ref} from "vue";
import {addHotel, deleteHotel, getHotelsByHotelNameList, getRatingByHotelName, updateHotel} from "@/api/getData.js";
import {ElMessage, ElMessageBox} from "element-plus";
import {getStorage} from "@/utils/localStorage.js";

const value = ref(3.5)
const scenics = ref([])
scenics.value = JSON.parse(getStorage("cards"))
console.log(scenics.value)

/**
 * 搜索表单数据
 * @type {Ref<UnwrapRef<{}>>}
 */
const searchForm = ref({})
/**
 * 搜索字段
 * @type {Ref<UnwrapRef<string>>}
 */
const searchHotelName = ref('')
/**
 * 分页插件的参数
 * @type {Ref<UnwrapRef<number>>}
 */
const page = ref(1)
const total = ref(20)
const limit = ref(10)

const visibleDialog = ref(false)
const dialogTitle = ref('')
const rating = ref({
  rate: 0
})

let hotels = ref([])
/**
 * 酒店列表
 */
const hotelList = () => {
  let params = {
    hotelName: searchHotelName.value,
    page: page.value,
    limit: limit.value
  }
  getHotelsByHotelNameList(params).then((res) => {
    if (res.code === 200) {
      hotels.value = res.datas
      total.value = res.total
    }
  })
}
/**
 * 一页个数变化
 */
const handleSizeChange = (size) => {
  limit.value = size
  hotelList()
}
/**
 * 页码变化
 */
const handleCurrentChange = (currentPage) => {
  page.value = currentPage
  hotelList()
}

hotelList();


/**
 * 抽屉关闭
 * @type {Ref<UnwrapRef<boolean>>}
 */
const visibleDrawer = ref(false)
const drawerTitle = ref()

const hotelModel = ref({
  hotelName: '',
  address: '',
  maxCapacity: '',
  parkingSpaces: '',
  maxRooms: '',
  location: ''
})
const clearData = () => {
  hotelModel.value = {
    hotelName: '',
    address: '',
    maxCapacity: '',
    parkingSpaces: '',
    maxRooms: '',
    location: ''
  }
}

const doAddHotel = () => {
  if (hotelModel.value.hotelName === '') {
    ElMessage.warning('酒店名不能为空')

  } else {
    addHotel(hotelModel.value).then((res) => {
      if (res.code === 200) {
        ElMessage.success('添加成功')
      }

      clearData();
      visibleDrawer.value = false
      hotelList()
    })
  }
}

const doUpdateHotel = () => {
  updateHotel(hotelModel.value).then((res) => {
    if (res.code === 200) {
      ElMessage.success('编辑成功')
    }
    clearData();
    visibleDrawer.value = false
    hotelList()
  })
}
const handleEdit = (row) => {
  visibleDrawer.value = true
  drawerTitle.value = '编辑酒店信息'
  hotelModel.value = {...row}
}

const handleDelete = (row) => {
  ElMessageBox.confirm(
      '确认删除该酒店吗？',
      '提示',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }
  ).then(() => {
    deleteHotel(row.hotelName).then((res) => {
      if (res.code === 200) {
        hotelList()
        ElMessage({
          type: 'success',
          message: '删除成功'
        })
      }
    })
  })
      .catch(() => {
        ElMessage({
          type: 'info',
          message: '取消删除',
        })
      })
}

const handleShowScore = (row) => {
  visibleDialog.value = true;
  dialogTitle.value = row.hotelName;
  getRatingByHotelName(dialogTitle.value).then((res) => {
    if (res.code === 200) {
      rating.value.rate = res.datas
      console.log(rating.value)
    }
  })
}

const clearRating = () => {
  rating.value = {
    rate: 0
  }
}

</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 20px;
  font-weight: bold;
  margin-left: 25px;
}

.add-btn {
  padding: 1.1em 2.0em;
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

.add-btn:hover {
  background-color: #3dc1e1;
  box-shadow: 0px 15px 20px rgba(0, 240, 255, 0.4);
  color: #fff;
  transform: translateY(-7px);
}

.add-btn:active {
  transform: translateY(-1px);
}

.submit-btn {
  margin-left: auto;
  margin-right: auto;
  width: 150px;
  margin-top: 20px;
  letter-spacing: 10px;
}
</style>
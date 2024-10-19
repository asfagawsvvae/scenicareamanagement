<template>
  <el-card >
    <template #header>
      <div class="card-header">
        <span>缆车管理</span>
        <el-button class="add-btn" type="primary" @click="visibleDrawer=true; drawerTitle='新增缆车信息';clearForm()" style="font-size: 0.9em">新增缆车</el-button>
      </div>
    </template>

    <!--  搜索表单-->
    <el-form :inline="true" :model="searchForm" class="demo-form-inline">
      <el-form-item >
        <el-input v-model="searchCableCar" placeholder="请输入缆车信息" clearable @clear="cableCarList"/>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="cableCarList">搜索</el-button>
      </el-form-item>
    </el-form>
    <!--  酒店列表-->
    <el-table :data="cableCars" style="width: 100% ;font-size: 1.2em;font-weight: bolder" >
      <el-table-column fixed prop="cableCarName" label="缆车名字" width="250" />
      <el-table-column prop="startTime" label="运行开始时间" width="150" />
      <el-table-column prop="endTime" label="运行结束时间" width="150" />
      <el-table-column prop="intervalTime" label="间隔时间" width="130" />
      <el-table-column prop="location" label="所在景区" width="190" />
      <el-table-column fixed="right" label="操作" min-width="150">
        <template #default="{row}">
          <el-button link type="primary" style="font-size: 0.9em;font-weight: bolder" @click="handleEdit(row)">
            编辑
          </el-button>
          <el-button link type="danger" style="font-size: 0.9em;font-weight: bolder" @click="handleDelete(row)">
            删除
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
          :model="cableCarModel"
          label-width="auto"
          style="max-width: 600px"
      >
        <el-form-item label="缆车名字">
          <el-input v-model="cableCarModel.cableCarName" placeholder="请输入缆车名" />
        </el-form-item>
        <el-form-item label="运行开始时间">
          <el-input v-model="cableCarModel.startTime" placeholder="请输入缆车运行开始时间" />
        </el-form-item>
        <el-form-item label="运行结束时间">
          <el-input v-model="cableCarModel.endTime" placeholder="请输入缆车运行结束时间" />
        </el-form-item>
        <el-form-item label="间隔时间">
          <el-input v-model="cableCarModel.intervalTime" placeholder="请输入缆车的间隔时间" />
        </el-form-item>
        <el-form-item label="所在景区">
          <el-select v-model="cableCarModel.location" placeholder="请选择缆车所在的景区">
            <el-option
                v-for="option in scenicAreas"
                :label="option.label"
                :value="option.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="drawerTitle === '新增缆车信息' ? doAddCableCar() : doUpdateCableCar()" class="submit-btn">提交</el-button>
        </el-form-item>
      </el-form>
    </el-drawer>
  </el-card>
</template>

<script setup>

import {ref} from "vue";
import {addCableCar, deleteCableCar, getCableCarByNameList, getScenicAreas, updateCableCar} from "@/api/getData.js";
import {ElMessage, ElMessageBox} from "element-plus";

const searchForm = ref({})
const searchCableCar = ref('')
const page = ref(1)
const limit = ref(20)
const total = ref(0)
let cableCars = ref([])
const visibleDrawer = ref(false)
const drawerTitle = ref()

const scenicAreas = ref([]);

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

getScenicAreaNames();

const cableCarList = () => {
  let params = {
    cableCarName: searchCableCar.value,
    location: searchCableCar.value,
    page: page.value,
    limit: limit.value
  }
  getCableCarByNameList(params).then((res) => {
    if (res.code === 200) {
      cableCars.value = res.datas
      total.value = res.total
    }
  })
}
cableCarList()

const handleDelete = (row) => {
  ElMessageBox.confirm(
      '确认要删除该缆车信息吗？',
      '提示',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }
  ).then(() => {
    deleteCableCar(row.cableCarName).then((res) => {
      if (res.code === 200) {
        cableCarList()
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
          message: '取消删除'
        })
      })
}

const handleSizeChange = (size) => {
  limit.value = size
  cableCarList()
}
const handleCurrentChange = (currentPage) => {
  page.value = currentPage
  cableCarList()
}

const cableCarModel = ref({
  cableCarName: '',
  startTime: '',
  endTime: '',
  intervalTime: '',
  location: ''
})

const clearForm = () => {
  cableCarModel.value = {
    cableCarName: '',
    startTime: '',
    endTime: '',
    intervalTime: '',
    location: ''
  }
}

const doAddCableCar = () => {
    if(cableCarModel.value.cableCarName === ''){
      ElMessage.warning('缆车名字不能为空')
    }else{
      addCableCar(cableCarModel.value).then((res) => {
        if (res.code === 200) {
          ElMessage.success('添加成功')
        }
        clearForm()
        visibleDrawer.value = false
        cableCarList()
      })
    }
}

const doUpdateCableCar = () => {
  updateCableCar(cableCarModel.value).then((res) => {
    if (res.code === 200) {
      ElMessage.success('修改成功')
    }
    clearForm()
    visibleDrawer.value = false
    cableCarList()
  })
}

const handleEdit = (row) => {
  visibleDrawer.value = true
  drawerTitle.value = '编辑缆车信息'
  cableCarModel.value = {...row}
}


</script>

<style scoped>
.card-header{
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 20px;
  font-weight: bold;
  margin-left: 25px;
}
.add-btn{
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
.add-btn:hover{
  background-color: #3dc1e1;
  box-shadow: 0px 15px 20px rgba(0, 240, 255, 0.4);
  color: #fff;
  transform: translateY(-7px);
}
.add-btn:active{
  transform: translateY(-1px);
}
.submit-btn{
  margin-left: auto;
  margin-right: auto;
  width: 150px;
  margin-top: 20px;
  letter-spacing: 10px;
}
</style>
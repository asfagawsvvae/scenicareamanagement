<script setup>

import {onMounted, ref} from "vue";
import {getAllHotel, getAllScenic, getLocationList, getRescuerList} from "@/api/getData.js";

const searchForm = ref({});
// 定义输入框
const employeeName = ref('');
// 定义分页插件的参数
const page = ref(1);
const total = ref(20);
const limit = ref(10);
// 定义救援人员列表数组
const rescuer = ref([]);

// 工作地点选择
const rescuerLocation = ref('')
const locationOptions = ref([])

// 页面大小发生改变
const handleSizeChange =(size)=>{
  limit.value = size;
  employeeList();
}
// 页码发生变化
const handleCurrentChange =(num)=>{
  page.value = num;
  employeeList();
}

// 重置员工列表
const resetEmployeeList =()=>{
  employeeName.value = '';
  rescuerLocation.value = '';
  employeeList();
}

const locationList =()=>{
  getLocationList().then((res)=>{
    if(res.code===200){
      locationOptions.value = res.datas.map(location=>({
        value: location,
        label: location
      }))
    }
  })
}
const scenicOptionList = ref([])
const scenicList =()=>{
  getAllScenic().then((res)=>{
    if(res.code===200){
      scenicOptionList.value = res.datas.map(scenic=>({
        value: scenic,
        label: scenic
      }))
    }
  })
}
const hotelOptionList = ref([])
const hotelList =()=>{
  getAllHotel().then((res)=>{
    if(res.code===200){
      hotelOptionList.value = res.datas.map(hotel=>({
        value: hotel,
        label: hotel
      }))
    }
  })
}

const employeeList =()=>{
  let params = {
    employeeName: employeeName.value,
    location: rescuerLocation.value,
    page: page.value,
    limit: limit.value
  }
  getRescuerList(params).then((res)=>{
    if(res.code===200){
      rescuer.value = res.datas
      total.value=res.total;
      console.log(res)
    }
  })
}

// 初始默认调用
onMounted(()=>{
  employeeList();
  locationList();
  scenicList();
  hotelList();
})

</script>

<template>
  <el-card>
    <template #header>
      <div class="card-header">
        <span class="card-header-text">救援人员查看</span>
      </div>
    </template>

    <!--搜索表单-->
    <el-form :inline="true" :model="searchForm">
      <el-form-item label="员工名">
        <el-input
            v-model="employeeName"
            placeholder="请输入员工姓名"
            style="width: 200px;"
            clearable
            @change="employeeList"
        />
      </el-form-item>

      <el-form-item label="工作地点">
        <el-select
            v-model="rescuerLocation"
            placeholder="请选择工作地点"
            style="width: 200px;"
            clearable
            @change="employeeList"
        >
          <el-option
              v-for="item in locationOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
          />
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="employeeList">搜索</el-button>
      </el-form-item>
      <el-form-item>
        <el-button class="resetButton" @click="resetEmployeeList">重置</el-button>
      </el-form-item>
    </el-form>

    <!--员工列表-->
    <el-table :data="rescuer" style="width: 100%" class="table-column-text" border>
      <el-table-column prop="employeeName" label="员工名" width="200" />
      <el-table-column prop="gender" label="性别" width="100" />
      <el-table-column prop="age" label="年龄" width="200" />
      <el-table-column prop="job" label="职务" width="300" />
      <el-table-column prop="location" label="工作地点" width="300" />
    </el-table>

    <!--分页插件-->
    <el-pagination
        v-model:current-page="page"
        v-model:page-size="limit"
        :page-sizes="[10, 20, 50, 100]"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        style="margin-top: 20px; justify-content: flex-end"
    />
  </el-card>
</template>

<style scoped>

.addEmployeeButton{
  background-color: #3dc1e1;
}
.addEmployeeButton:hover{
  background-color: #13a13d;
}

.resetButton{
  margin-right: 20px;
}


.card-header{
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.card-header-text{
  font-weight: bold; /* 加粗 */
  font-size: 1.5em; /* 放大，这里使用了相对大小，可以根据需要调整 */
}

.table-column-text{
  font-weight: bold; /* 加粗 */
  font-size: 1.2em; /* 放大，这里使用了相对大小，可以根据需要调整 */
}
.table-column-button{
  font-weight: bold; /* 加粗 */
  font-size: 0.9em; /* 放大，这里使用了相对大小，可以根据需要调整 */
}

.card-header-button {
  padding: 1.3em 3em;
  //font-size: 12px;
  text-transform: uppercase;
  color: #000;
  background-color: #fff;
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

</style>
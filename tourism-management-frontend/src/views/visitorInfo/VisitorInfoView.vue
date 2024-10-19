
<template>
  <el-card>
    <template #header>
      <div class="card-header">
        <span>游客信息</span>
      </div>
    </template>

    <!--  搜索表单-->
    <el-form :inline="true" :model="searchForm" class="demo-form-inline">
      <el-form-item >
        <el-input v-model="searchVisitorName" placeholder="请输入游客信息" clearable @clear="visitorList"/>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="visitorList">搜索</el-button>
      </el-form-item>
    </el-form>

    <!--    学生显示列表-->
    <el-table :data="visitors" style="width: 100%; font-size: 1.2em;font-weight: bolder">
      <el-table-column fixed prop="username" label="游客姓名" width="130" />
      <el-table-column prop="phone" label="电话" width="180" />

      <el-table-column prop="isDrive" label="是否开车" width="170" >
        <template #default="{ row }">
          <span v-if="row.isDrive === 1">
            是
          </span>
          <span v-if="row.isDrive === 0">
            否
          </span>
        </template>
      </el-table-column>
      <el-table-column prop="destination" label="目的地" width="170" />
      <el-table-column prop="hotel" label="酒店" width="200" />
      <el-table-column prop="guide" label="导游" width="150" />
      <el-table-column prop="date" label="日期" width="200" />
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

  </el-card>
</template>


<script setup>

import {ref} from "vue";
import {getVisitorinfoByVisitorNameList} from "@/api/getData.js";
import {ElMessage, ElMessageBox} from "element-plus";


const searchForm = ref({})
const searchVisitorName = ref('')
const page=ref(1)
const total = ref(20)
const limit =  ref(20)
let visitors = ref([])

const visitorList = ()=>{
  let params = {
    username: searchVisitorName.value,
    page: page.value,
    limit: limit.value
  }
  getVisitorinfoByVisitorNameList(params).then((res)=>{
    if(res.code === 200){

     visitors.value=res.datas
     total.value=res.total

    }
  })
}


visitorList()

const handleSizeChange = (size) => {
  limit.value = size
  visitorList()
}
const handleCurrentChange = (currentPage) => {
  page.value = currentPage
  visitorList()
}

</script>

<style scoped>
.card-header{
  font-size: 20px;
  font-weight: bold;
  margin-left: 25px;
}
</style>
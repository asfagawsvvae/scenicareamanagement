
<template>
  <el-card>
    <template #header>
      <div class="card-header">
        <span>导游管理</span>
      </div>
    </template>

<!--<搜索表单>-->
    <el-form :inline="true" :model="searchForm" class="demo-form-inline">
      <el-form-item label="姓名">
        <el-input v-model="searchTourguideName" placeholder="请输入导游姓名" clearable />
      </el-form-item>

      <el-form-item>
          <el-button type="primary" @click="TourguideList">搜索</el-button>
      </el-form-item>
    </el-form>



<!--  显示列表-->

  <el-table :data="Tourguides" style="width: 100%">
    <el-table-column fixed prop="guidename" label="姓名" width="360" />
    <el-table-column prop="gender" label="性别" width="360" />
    <el-table-column prop="age" label="年龄" width="360" />
    <el-table-column prop="agencyname" label="旅行社" width="360" />
    <el-table-column fixed="right"  prop="rating" label="评价(满分5分)" />


  </el-table>
    <el-pagination
        v-model:current-page="page"
        v-model:page-size="limit"
        :page-sizes="[20, 50, 100, 200]"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        style="margin-top: 20px;justify-content: flex-end"
    />
  </el-card>

</template>



<script setup>
import {ref} from "vue";
import {getTourguideByNameList} from "@/api/getData.js";

const searchForm=ref()
const searchTourguideName=ref('')
const Tourguides=ref([])

const page = ref(1);
const total = ref(20)
const limit  =ref(10)



const TourguideList=()=>{
  let params = {
    guidename :searchTourguideName.value,
    page: page.value,
    limit:limit.value
  }


  getTourguideByNameList(params).then((res)=>{

    if(res.code==200){
      Tourguides.value=res.datas
      total.value=res.total;
      console.log(res)
    }
  })


}

const handleSizeChange = (size)=>{
  limit.value =size
  TourguideList();

}
const handleCurrentChange=(num)=>{
  page.value = num;
  TourguideList();
}
TourguideList();
</script>

<style scoped>

</style>
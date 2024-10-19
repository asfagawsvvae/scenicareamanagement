<template>
  <el-card >
    <template #header>
      <div class="card-header">
        <span>收件箱</span>
      </div>
    </template>

    <!--  搜索表单-->
    <el-form :inline="true" :model="searchForm" class="demo-form-inline">
      <el-form-item style="margin-left: 90px">
        <el-input v-model="searchInfo" placeholder="请输入搜索信息" clearable @clear="infoList(0)"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="infoList(0)">搜索</el-button>
      </el-form-item>
    </el-form>

<!--    <el-table :data="info" style="width: 100% ;font-size: 1.2em;font-weight: bolder" >-->
<!--      <el-table-column  prop="info" label="信息" width="400" />-->
<!--      <el-table-column  prop="timeStamp" label="时间" width="200" />-->
<!--    </el-table>-->

    <el-collapse style="margin-top: 20px;margin-right: auto;width: 90%">
      <el-collapse-item v-for="(item,index) in info" :title="item.timeStamp" :name="index" >
        <span style="font-weight: bold;font-size: 16px">详情:</span>
        <p style="margin-left: 30px">{{item.info}}</p>
      </el-collapse-item>
    </el-collapse>


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
import { getInfoPublish} from "@/api/getData.js";

const searchForm = ref({})
const searchInfo = ref('')
const page = ref(1)
const limit = ref(20)
const total = ref(0)
let info = ref([])

const infoList = (published) => {
  let params = {
    info: searchInfo.value,
    page: page.value,
    limit: limit.value,
    published : published || 0
  }
  getInfoPublish(params).then((res) => {
    if (res.code === 200) {
      info.value = res.datas
      console.log(info.value)
      total.value = res.total
    }
  })
}

infoList()

const handleSizeChange = (size) => {
  limit.value = size
  infoList()
}
const handleCurrentChange = (currentPage) => {
  page.value = currentPage
  infoList()
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
</style>
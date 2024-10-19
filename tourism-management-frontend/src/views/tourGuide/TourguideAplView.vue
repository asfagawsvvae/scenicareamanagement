
<template>
  <el-card>
    <template #header>
      <div class="card-header">
        <span>导游申请</span>
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
      <el-table-column fixed prop="guidename" label="姓名" width="350" />
      <el-table-column prop="gender" label="性别" width="350" />
      <el-table-column prop="age" label="年龄" width="350" />
      <el-table-column prop="agencyname" label="意向旅行社" width="350" />
      <el-table-column fixed="right" label="操作" min-width="350">
        <template #default="{row}">
            <el-button link type="primary" size="small" @click="GotYou(row)">
            录用
          </el-button>
          <el-button link type="danger" size="small" @click="LoseYou(row)">
            删除
          </el-button>
        </template>
      </el-table-column>

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
import {deleteTourguide, getTourguideByNameList1, updateTourguide} from "@/api/getData.js";
import {ElMessage, ElMessageBox} from "element-plus";

const searchForm=ref()
const searchTourguideName=ref('')
const Tourguides=ref([])

const page = ref(1);
const total = ref(20)
const limit  =ref(10)

const  guideModel =ref( {
  guideId:'',
  guideName:'',
  gender:'',
  age:'',
  agencyname:'',
})

const clearData = ()=>{
  guideModel.value = {
    guideId:'',
    guideName:'',
    gender:'',
    age:'',
    agencyname:'',

  }

}

/**
 * 不录用(点击后删除所选的行）
 * @constructor
 */
const LoseYou=(row)=>{
  ElMessageBox.confirm(
      '您确定删除这条数据么',
      '提示',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }
  )
      .then(() => {
        deleteTourguide(row.guideid).then((res) => {
          console.log(row.guideid)
          if(res.code==200){
            TourguideList();
            ElMessage({
              type: 'success',
              message: '删除成功',
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
/**
 * 录用  （在前端将statment设置为0，返回到更改数据库，再次进行搜索）
 * @constructor
 */


const GotYou = (row) => {
  guideModel.value = { ...row };
  updateTourguide(guideModel.value).then((res) => {
    if (res.code == 200) {
      ElMessage.success("录用成功");

      deleteTourguide(row.guideid).then(() => {
        TourguideList();
      })

      clearData();
      TourguideList();
    }
  });

};




const TourguideList=()=>{
  let params = {
    studentName :searchTourguideName.value,
    page: page.value,
    limit:limit.value
  }


  getTourguideByNameList1(params).then((res)=>{

    if(res.code==200){
      Tourguides.value=res.datas
      total.value = res.total;
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
<script setup>

import {onMounted, ref} from "vue";
import {deleteReview, getAllHotel, getAllScenic, getReviewList} from "@/api/getData.js";
import {ElMessage, ElMessageBox} from "element-plus";

const searchForm = ref({});
// 搜索栏输入字段
const searchUsername = ref('');
// 搜索栏对象选择
const target = ref('')
const scenicOptions = ref([]);
const hotelOptions = ref([]);
// 景区列表
const scenicList =()=>{
  getAllScenic().then((res)=>{
    if(res.code===200){
      scenicOptions.value=res.datas.map(scenic=>({
        value: scenic,
        label: scenic
      }))
      targetOptions.value[0].children = scenicOptions;
    }
  })
}
// 酒店列表
const hotelList =()=>{
  getAllHotel().then((res)=>{
    if(res.code===200){
      hotelOptions.value=res.datas.map(hotel=>({
        value: hotel,
        label: hotel
      }))
      targetOptions.value[1].children = hotelOptions;
    }
  })
}

// 重置按钮
const resetInput =()=>{
  searchUsername.value = '';
  selectTarget.value = null;
  target.value = '';
  reviewList();
}

// 定义分页插件参数
const page = ref(1);
const limit = ref(10);
const total = ref(20);
// 评论区列表数组
const reviews = ref([]);

const reviewList =()=>{
  let params = {
    username: searchUsername.value,
    target: selectTarget.value && selectTarget.value.length > 1 ? selectTarget.value[1] : '',
    page: page.value,
    limit: limit.value,
  }
  getReviewList(params).then((res)=>{
    if(res.code===200){
      reviews.value=res.datas;
      total.value=res.total;
    }
  })
}

// 改变页面大小
const handleSizeChange = (size) => {
  limit.value = size;
  reviewList();
}

// 改变页码
const handleCurrentChange =(num)=>{
  page.value = num;
  reviewList();
}

// 初始默认调用
onMounted(()=>{
  reviewList();
  scenicList();
  hotelList();
})

// 清空搜索栏同时显示初始列表
const clearSearch =()=>{
  if(searchUsername.value === ''){
    reviewList();
  }
}

// 删除指定评论
const handleDelete =(row)=>{
  ElMessageBox.confirm(
      '您确定删除这条评论吗',
      '提示',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }
  ).then(()=>{
    deleteReview(row.reviewId).then((res)=>{
      if(res.code===200){
        reviewList();
        ElMessage({
          type: 'success',
          message: '删除成功'
        })
      }
    })
  })
    .catch(()=>{
      ElMessage({
        type: 'info',
        message: '取消删除'
      })
    })
}

// 评论对象列表
const targetOptions = ref([
  {
    value: '景区',
    label: '景区',
    children: []
  },
  {
    value: '酒店',
    label: '酒店',
    children: []
  }
])
// 评论对象选中
const selectTarget = ref([]);

</script>

<template>

  <el-card>
    <template #header>
      <div class="card-header">
        <span class="card-header-text">评论区</span>
      </div>
    </template>

    <!--搜索表单-->
    <el-form :inline="true" :model="searchForm">
      <el-form-item label="用户名">
        <el-input
            v-model="searchUsername"
            placeholder="请输入用户名"
            clearable
            @clear="clearSearch"
            style="width: 225px"
            @change="reviewList"
        />
      </el-form-item>

      <el-form-item label="评论对象">
        <el-cascader
            v-model="selectTarget"
            :options="targetOptions"
            :show-all-levels="false"
            clearable
            @change="reviewList"
        />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="reviewList" class="searchButton">搜索</el-button>
      </el-form-item>
      <el-form-item>
        <el-button @click="resetInput">重置</el-button>
      </el-form-item>
    </el-form>

    <!--评论区列表-->
    <el-table :data="reviews" style="width: 100%" class="table-column-text" border>
      <el-table-column prop="username" label="用户名" width="125" />
      <el-table-column prop="rating" label="评价" width="200">
        <template #default="{row}">
          <!-- 使用 el-rate 组件展示评分 -->
          <el-rate
              v-model="row.rating"
              disabled
              text-color="#ff9900"
          />
        </template>
      </el-table-column>
      <el-table-column prop="target" label="地点" width="200" />
      <el-table-column prop="reviewDate" label="评论时间" width="200" />
      <el-table-column prop="comment" label="评论内容" width="500" />
      <el-table-column fixed="right" label="操作" width="200">
        <template #default="{row}">
          <el-button link type="danger" size="small" class="table-column-button" @click="handleDelete(row)">删除</el-button>
        </template>
      </el-table-column>
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

.searchButton{
  margin-left: 20px;
  background-color: #3dc1e1;
}
.searchButton:hover{
  background-color: #13a13d;
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
</style>
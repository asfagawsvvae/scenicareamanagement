<script setup>

// 定义分页插件参数
import {onMounted, reactive, ref} from "vue";
import {
  addReview,
  deleteReview,
  getAllHotel,
  getAllScenic,
  getReviewByUsername,
  updateReview
} from "@/api/getData.js";
import {getStorage} from "@/utils/localStorage.js";
import {ElMessage, ElMessageBox} from "element-plus";

const page = ref(1);
const limit = ref(10);
const total = ref(20);
// 评论区列表数组
const touristReviews = ref([]);

// 通过JSON获取用户名
const getUsername = getStorage('username');

// 获取评论
const touristReviewList =()=>{
  let params = {
    username: getUsername,
    page: page.value,
    limit: limit.value
  }
  getReviewByUsername(params).then((res)=>{
    if(res.code===200){
      touristReviews.value=res.datas;
      total.value=res.total;
    }
  })
}

// 改变页面大小
const handleSizeChange = (size) => {
  limit.value = size;
  touristReviewList();
}
// 改变页码
const handleCurrentChange =(num)=>{
  page.value = num;
  touristReviewList();
}

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
        touristReviewList();
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

const reviewTitle = ref();
// 游客评论弹框
const touristReviewDialog = ref(false);
const form = reactive({
  reviewId: '',
  kind: '',
  target: '',
  rating: '5',
  comment: ''
})
// 编辑评论
const handleEdit =(row)=>{
  touristReviewDialog.value=true;
  form.reviewId=row.reviewId;
  form.target=row.target;
  form.rating=row.rating;
  form.comment=row.comment;
  // 通过 target 判断类型
  if(isInArray(row.target,scenicOptions.value)){
    form.kind='景区'
  } else{
    form.kind='酒店'
  }

  reviewTitle.value='更改评论';
}
function isInArray(targetValue, array) {
  return array.some(item => item.value === targetValue);
}
// 清空评论
const clearReview =()=>{
  form.kind='';
  form.target='';
  form.rating='5';
  form.comment='';
}
// 景区列表
const scenicOptions = ref([]);
const scenicList =()=>{
  getAllScenic().then((res)=>{
    if(res.code===200){
      scenicOptions.value=res.datas.map(scenic=>({
        value: scenic,
        label: scenic
      }))
    }
  })
}
// 酒店列表
const hotelOptions = ref([]);
const hotelList =()=>{
  getAllHotel().then((res)=>{
    if(res.code===200){
      hotelOptions.value=res.datas.map(hotel=>({
        value: hotel,
        label: hotel
      }))
    }
  })
}
// 选择类型时清空 target 内容
const clearTargetOption =()=>{
  form.target='';
}

// 评论规则
const rules = ref({
  kind: [{ required: true, message: '请选择对象类型', trigger: 'blur' }],
  target: [{ required: true, message: '请选择评论对象', trigger: 'blur' }]
})
const reviewRule = ref(null)

// 提交评论
const confirmReview =()=>{
  reviewRule.value.validate((valid)=>{
    if(valid){
      console.log(reviewRule.value)
      successAddReview();
    } else{
      ElMessage.error("请选择您想要评价的对象");
    }
  })
}
const successAddReview =()=>{
  let params ={
    username: getUsername,
    rating: form.rating,
    comment: form.comment,
    target: form.target
  }
  ElMessageBox.confirm(
      '确认提交该评论吗？',
      '提示',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }
  ).then(() => {
    addReview(params).then((res)=>{
      if(res.code===200){
        ElMessage.success("评论成功");
        touristReviewDialog.value=false;
        clearReview();
        touristReviewList();
      }
    })
  }).catch(() => {
    ElMessage({
      type: 'info',
      message: '已取消评论',
    });
  });
}
// 修改用户评论
const doUpdateReview =()=>{
  let params = {
    rating: form.rating,
    comment: form.comment,
    reviewId: form.reviewId
  }
  ElMessageBox.confirm(
      '确认修改该评论吗？',
      '提示',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }
  ).then(() => {
    updateReview(params).then((res)=>{
      if(res.code===200){
        ElMessage.success("修改成功");
        touristReviewDialog.value=false;
        clearReview();
        touristReviewList();
      }
    })
  }).catch(() => {
    ElMessage({
      type: 'info',
      message: '已取消修改该评论',
    });
  });
}

// 初始默认调用
onMounted(()=>{
  scenicList();
  hotelList();
  touristReviewList();
})

</script>

<template>
  <el-card>
    <template #header>
      <div class="card-header">
        <span class="card-header-text">游客评论区</span>

        <el-button class="card-header-button" type="primary" plain @click="touristReviewDialog=true; clearReview(); reviewTitle='游客评论'">评论</el-button>
        <el-dialog
            v-model="touristReviewDialog"
            :title="reviewTitle"
            width="500"
            style="font-weight: bolder"
        >
          <el-form ref ="reviewRule" :model="form" :rules="rules">
            <el-form-item label="对象类型" label-width="auto" class="reviewDialogStyle" prop="kind" >
              <el-select
                  v-model="form.kind"
                  placeholder="请选择类型"
                  @click="clearTargetOption"
                  :disabled="reviewTitle==='更改评论'"
              >
                <el-option label="景区" value="景区" />
                <el-option label="酒店" value="酒店" />
              </el-select>
            </el-form-item>
            <el-form-item label="评论对象" label-width="auto" class="reviewDialogStyle" prop="target" >
              <el-select
                  v-model="form.target"
                  autocomplete="off"
                  placeholder="请选择评论对象"
                  :disabled="reviewTitle==='更改评论'"
              >
                <el-option
                    v-for="item in form.kind==='景区'?scenicOptions:form.kind==='酒店'?hotelOptions:null"
                    :key="item.value"
                    :label="item.value"
                    :value="item.value"
                />
              </el-select>
            </el-form-item>
            <el-form-item label="您的评分" label-width="auto" class="reviewDialogStyle" prop="rating" >
                <el-rate v-model="form.rating" allow-half />
            </el-form-item>
            <el-form-item label="评论内容" label-width="auto" class="reviewDialogStyle" prop="comment" >
              <el-input
                  v-model="form.comment"
                  autocomplete="off"
                  :rows="5"
                  type="textarea"
                  placeholder="请输入您的评论"
              />
            </el-form-item>
          </el-form>
          <template #footer>
            <div class="dialog-footer">
              <el-button @click="touristReviewDialog=false; clearReview()">取消</el-button>
              <el-button type="primary" @click="reviewTitle==='游客评论'?confirmReview():doUpdateReview()">确定</el-button>
            </div>
          </template>
        </el-dialog>
      </div>
    </template>

    <!--评论区列表-->
    <el-table :data="touristReviews" style="width: 100%; height: 100%" class="table-column-text" border>
      <el-table-column prop="reviewDate" label="评论时间" width="150" />
      <el-table-column prop="target" label="地点" width="175" />
      <el-table-column prop="rating" label="评价" width="200">
        <template #default="{row}">
          <el-rate
              v-model="row.rating"
              disabled
              text-color="#ff9900"
          />
        </template>
      </el-table-column>
      <el-table-column prop="comment" label="评论内容" width="500">
        <template #default="{row}">
          {{ row.comment === '' ? '无' : row.comment }}
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="200">
        <template #default="{row}">
          <el-button link type="primary" size="small" class="table-column-button" @click="handleEdit(row)">修改</el-button>
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

.reviewDialogStyle{
  margin-left: 20px;
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
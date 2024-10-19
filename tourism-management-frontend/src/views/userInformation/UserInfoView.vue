<template>
  <div class="info-container">
    <el-card class="info-card">
      <div class="info-header">
        <h2>个人信息展示</h2>
      </div>

      <el-form
          :model="infoModel"
          label-width="auto"
          style="width: 100%"
      >
        <el-form-item label="用户名">
          <el-input v-model="infoModel.username" disabled />
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="infoModel.phone" autocomplete="off" clearable />
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="infoModel.email" autocomplete="off" clearable />
        </el-form-item>
        <el-form-item label="出生日期">
          <el-date-picker
              v-model="infoModel.birth"
              label-width="auto"
              type="date"
              :size="'default'"
              style="width: 100%"
          />
        </el-form-item>
        <el-form-item label="所在地">
          <el-input v-model="infoModel.location" autocomplete="off" clearable />
        </el-form-item>
        <el-form-item label="个人简介">
          <el-input
              v-model="infoModel.sign"
              autocomplete="off"
              :rows="5"
              type="textarea"
          />
        </el-form-item>
      </el-form>

      <el-button plain @click="changePassword=true" type="danger" style="margin-top: 30px; margin-right: 30px">修改密码</el-button>
      <el-dialog v-model="changePassword" title="修改密码" width="500">
        <el-form :model="form">
          <el-form-item label="新的密码" label-width="auto" style="width: 100%">
            <el-input v-model="form.password" autocomplete="off" />
          </el-form-item>
          <el-form-item label="重复密码" label-width="auto" style="width: 100%">
            <el-input v-model="form.repeatedPassword" autocomplete="off" />
          </el-form-item>
        </el-form>
        <template #footer>
          <div class="dialog-footer">
            <el-button @click="changePassword=false">取消</el-button>
            <el-button type="primary" @click="confirmChangePassword">确认修改</el-button>
          </div>
        </template>
      </el-dialog>

      <el-button type="primary" style="margin-top: 30px; margin-left: 30px" @click="changeInformation">确定</el-button>
    </el-card>
  </div>
</template>
<script setup>

import {reactive, ref} from "vue";
import {selectUserByUsername, updatePassword, updateUser} from "@/api/getData.js";
import {getStorage} from "@/utils/localStorage.js";
import {ElMessage, ElMessageBox} from "element-plus";

// 通过JSON获取用户名
const getUsername =getStorage('username');
const infoModel = ref({
  username: getUsername
})

// 确认修改个人信息
const changeInformation =()=>{
  let birth = new Date(infoModel.value.birth);
  console.log(infoModel.value.birth)
  birth.setHours(birth.getHours() + 1);
  console.log(birth)
  let params = {
    username: infoModel.value.username,
    phone: infoModel.value.phone,
    email: infoModel.value.email,
    birth: birth,
    location: infoModel.value.location,
    sign: infoModel.value.sign
  }
  updateUser(params).then((res)=>{
    if(res.code===200){
      ElMessage.success("保存成功");
    }
  })
}

// 修改密码
const changePassword = ref(false);
const form = reactive({
  password: '',
  repeatedPassword: ''
})
const confirmChangePassword =()=>{
  if(form.password!==form.repeatedPassword){
    ElMessage.error("两次输入的密码不一致");
  } else{
    let params ={
      username: infoModel.value.username,
      password: form.password
    }
    ElMessageBox.confirm(
        '确认修改密码吗？',
        '提示',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
        }
    ).then(() => {
      updatePassword(params).then((res)=>{
        if(res.code===200){
          form.password = '';
          form.repeatedPassword = '';
          ElMessage.success("修改成功");
          changePassword.value=false;
        }
      })
    }).catch(() => {
      ElMessage({
        type: 'info',
        message: '已取消修改',
      });
    });
  }
}

const init=()=>{
  selectUserByUsername(infoModel.value).then((res)=>{
    if(res.code===200){

      infoModel.value=res.datas
    }
  })
}

init();

</script>



<style scoped>
.info-header {
  margin-bottom: 40px;
}
.info-container {
  padding-left: 300px;
    align-items: center;
  height: 100vh;
  padding-top: 40px;
}
.info-card {
  width: 600px;
  padding: 40px;
  text-align: center;
  border-radius: 16px;
  box-shadow: 0 4px 8px rgb(0,0,0,0.1);
}
</style>
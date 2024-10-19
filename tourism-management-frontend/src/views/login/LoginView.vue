<template>
  <div class="login-container">
    <el-card class="login-card">
      <div class="login-header">
        <h3 class="login-title">登录</h3>
        <p class="login-subtitle">游客系统</p >
      </div>

      <el-form
          ref="loginFormRule"
          :model="loginForm"
          :rules="rules"
          label-width="auto"
          class="login-form">
        <el-form-item label="用户名" prop="username" style="font-weight: bolder; margin-left: 15%">
          <el-input v-model="loginForm.username"  autocomplete="off" placeholder="请输入用户名" style="width: 75%" />
        </el-form-item>
        <el-form-item label="密码" prop="password" style="font-weight: bolder; margin-left: 15%">
          <el-input v-model="loginForm.password"  type="password" autocomplete="off" placeholder="请输入密码" style="width: 75%"/>
        </el-form-item>
        <el-form-item>
          <el-button class="login-button" type="primary" @click="submitForm()">
            登录
          </el-button>
          <el-button class="reset-button" type="primary" @click="resetForm()">
            重置
          </el-button>
        </el-form-item>
      </el-form>
      <p>
        还没有账号？<router-link to="/register" class="login-footer">去注册</router-link>
      </p>
    </el-card>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import {ElMessage} from "element-plus";
import router from "@/router/index.js";
import {doLogin} from "@/api/getData.js";
import {getStorage, setStorage} from "@/utils/localStorage.js";
const loginForm = ref({
  username: '',
  password: '',
});

const loginFormRule = ref(null);

const  submitForm =()=>{
  //提交前验证
  loginFormRule.value.validate((valid)=>{
    if(valid){
      login();
    }else{
      ElMessage.error("用户名或密码为空！")
    }
  })
  //跳转到首页
}

const  resetForm =()=>{
  loginForm.value.username = '';
  loginForm.value.password = '';
}

const login = ()=>{
  doLogin(loginForm.value).then((res)=>{
    if(res.code===200) {
      setStorage("Token",res.datas.token);
      setStorage("UserInfo",JSON.stringify(res.datas));
      setStorage("username",res.datas.user.username)
      setStorage("phoneNumber",res.datas.user.phone)
      setStorage("userType",res.datas.user.userType)






      router.push("/home")
    }
  });
}

const  rules = ref({
  username : [{required:true,message:'请输入用户名',trigger:'blur'}],
  password : [{required:true,message:'请输入密码',trigger:'blur'}]
})
</script>
<style scoped>
.login-container{
  display: flex;
  justify-content: right;
  align-items: center;
  height: 100vh;
  padding-right: 150px;
  background-image: url('@/assets/1.jpg');
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
}

.login-card{
  width: 550px;
  //padding: 40px;
  text-align: center;
  border-radius: 50px;
  box-shadow: 0 4px 8px rgb(0,0,0,0.1);
  background-color: rgba(248, 206, 176, 0.84);
}
.login-header{
  margin-bottom: 40px;
}
.login-title{
  font-size: 30px;
  font-weight: bold;
  color: #333333;
  margin-top: 5px;
  margin-bottom: 10px;
  letter-spacing: 10px;
}
.login-subtitle{
  font-size: 25px;
  color: #666666;
  letter-spacing: 20px;
}
.login-form{
  width: 100%;
}
.login-button{
  width: 15%;
  font-size: 16px;
  display: inline-block; /* 使按钮成为行内块元素 */
  margin: 0 auto; /* 水平居中 */
  background-color: #f1d3d3;
  color: #666666;
}
.login-button:hover{
  background-color: white;
}
.reset-button{
  width: 15%;
  font-size: 16px;
  display: inline-block; /* 使按钮成为行内块元素 */
  margin: 0 auto; /* 水平居中 */
  background-color: #f1d3d3;
  color: #666666;
  margin-left: 5px;
}

.reset-button:hover{
  background-color: white;
}
.login-footer{
  color: #3dc1e1;
  font-size: 16px;
  padding-left: 10px;
}
.login-footer:hover{
  color: #f14e66;
}

</style>
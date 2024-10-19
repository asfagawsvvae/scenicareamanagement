<template>
  <div class="register-container">
    <el-card class="register-card">
      <div class="register-title">欢迎注册游客系统</div>
      <div class="register-title2">每一天，乐在旅游</div>

      <el-form
          :model="form"
          :rules="rules"
          ref="registerForm"
          label-width="100px"
          class="register-form">

        <el-form-item prop="username">
          <el-input v-model="form.username" placeholder="请输入用户名" />
          <span v-if="form.username" class="clear-btn" @click="clearInput_username">&#10006;</span>
        </el-form-item>
        <el-form-item prop="password">
          <el-input type="password" v-model="form.password" placeholder="请输入密码" />
          <span v-if="form.password" class="clear-btn" @click="clearInput_password">&#10006;</span>
        </el-form-item>
        <el-form-item prop="confirmPassword">
          <el-input type="password" v-model="form.confirmPassword" placeholder="请确认密码" />
          <span v-if="form.confirmPassword" class="clear-btn" @click="clearInput_confirmPassword">&#10006;</span>
        </el-form-item>
        <el-form-item prop="phone">
          <el-input v-model="form.phone" placeholder="请输入电话号" />
          <span v-if="form.phone" class="clear-btn" @click="clearInput_phone">&#10006;</span>
        </el-form-item>
        <el-form-item prop="email">
          <el-input v-model="form.email" placeholder="请输入邮箱" />
          <span v-if="form.email" class="clear-btn" @click="clearInput_email">&#10006;</span>
        </el-form-item>

        <div class="phone">
        </div>

        <el-form-item>
          <el-button class="register" type="primary" @click="submitForm()">注册</el-button>
        </el-form-item>
      </el-form>

      <p >已有账号？<router-link to="/" class="register-tip">登 录</router-link></p>

    </el-card>
  </div>
</template>

<script setup>
import {ref} from "vue";
import {registerUser} from "@/api/getData.js";
import {ElMessage} from "element-plus";
import router from "@/router/index.js";

const form = ref({
  username: '',
  password: '',
  confirmPassword: '',
  phone: '',
  email: ''
})

const rules = ref({
  username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
  password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
  confirmPassword: [{ required: true, message: '请确认密码', trigger: 'blur' },
    { validator: (rule, value, callback) => {
        if (value !== form.value.password) {callback(new Error('两次输入的密码不一致'));}
        else {callback();}}, trigger: 'blur'}],
  phone: [{ required: true, message: '请输入手机号', trigger: 'blur' }],
  email: [{ required: true, message: '请输入邮箱', trigger: 'blur' }]
});
const registerForm = ref(null);

const submitForm = () => {
  registerForm.value.validate((valid) => {
    if (valid) {
      onSubmit()
    } else {
      ElMessage.error('请填写完整信息')
    }
  })
}

const userModel = ref({
  username: '',
  password: ''
})

const onSubmit = ()=>{
  registerUser(form.value).then((res) => {
    if(res.code === 200) {
      ElMessage.success('注册成功')
      router.push('/')
    } else{
      ElMessage.error("用户名已存在，请重新注册！")
    }
  })
};

const clearInput_phone = () => {
  form.value.phone = ''
}
const clearInput_username = () => {
  form.value.username = ''
}
const clearInput_password = () => {
  form.value.password = ''
}
const clearInput_confirmPassword = () => {
  form.value.confirmPassword = ''
}
const clearInput_email = () => {
  form.value.email = ''
}


</script>

<style scoped>
.register-title {
  font-size: 26px;
  font-weight: bold;
  margin-top: 20px;
  letter-spacing: 10px;
}
.register-title2 {
  font-size: 17px;
  margin-top: 15px;

}
.register-form {
  max-width: 450px;
  margin-top: 30px;
}
.register-container{
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: linear-gradient(to bottom right, #00b2ff, #81c7e7, #eceaea,#1550a8);
}
.register-card {
  width: 600px;
  text-align: center;
  box-shadow: 0 6px 8px rgb(0,0,0,0.1);
  border-radius: 25px;
}
.register {
  margin-top: 20px;
  text-align: center;
  letter-spacing: 10px;
  margin-left: auto;
  margin-right: auto;
  text-decoration: none;
  position: relative;
  border: none;
  font-size: 16px;
  font-family: inherit;
  cursor: pointer;
  color: #fff;
  width: 10em;
  height: 2.3em;
  line-height: 2em;
  background: linear-gradient(90deg, #03a9f4, #f441a5, #ffeb3b, #03a9f4);
  background-size: 300%;
  border-radius: 30px;
  z-index: 1;
}
.register:hover {
  animation: ani 8s linear infinite;
  border: none;
}
@keyframes ani {
  0% {
    background-position: 0%;
  }
  100% {
    background-position: 400%;
  }
}
.register:before {
  content: "";
  position: absolute;
  top: -5px;
  left: -5px;
  right: -5px;
  bottom: -5px;
  z-index: -1;
  background: linear-gradient(90deg, #03a9f4, #f441a5, #ffeb3b, #03a9f4);
  background-size: 400%;
  border-radius: 35px;
  transition: 1s;
}
.register:hover:before {
  filter: blur(20px);
}
.register:active {
  background: linear-gradient(32deg, #03a9f4, #f441a5, #ffeb3b, #03a9f4);
}
.register-tip {
  color: #93cfde;
  font-size: 16px;
  padding-left: 10px;
}
.register-tip:hover {
  color: #f14e66;
}

.clear-btn {
  position: absolute;
  top: 50%;
  right: 5px;
  transform: translateY(-50%);
  cursor: pointer;
  color: #f12d2d; /* 可根据需要调整叉号图标的颜色 */
}

</style>
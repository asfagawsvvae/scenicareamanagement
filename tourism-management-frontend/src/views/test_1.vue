<template>
  <el-container class="layout-container">
    <!-- 左侧区域 -->
    <el-aside width="200" class="left">
      <el-header>
        <el-radio-group v-model="isCollapse" style="margin-bottom: 20px">
          <el-radio-button :label="false">展开列表</el-radio-button>
          <el-radio-button :label="true">收起列表</el-radio-button>
        </el-radio-group>
      </el-header>
      <el-menu
          default-active="2"
          class="el-menu-vertical-demo dark-mode"
          :collapse="isCollapse"
          @open="handleOpen"
          @close="handleClose"
          :background-color="menuBackgroundColor"
          :text-color="menuTextColor"
          :active-text-color="menuActiveTextColor"
      >
        <el-menu-item index="0" @click="toHouse" popper-class="dark-mode_0">
          <el-icon><House /></el-icon>
          <template #title>首页</template>
        </el-menu-item>
        <el-sub-menu index="1" popper-class="dark-mode_1">
          <template #title>
            <el-icon><Grid /></el-icon>

            <span>管理列表</span>
          </template>
          <el-sub-menu index="1-1">
            <template #title><span>景区</span></template>
            <el-menu-item index="1-1-1"@click="toScenicInfo">景区信息</el-menu-item>
            <el-menu-item index="1-1-2" @click="toScenicManage">景区管理</el-menu-item>

          </el-sub-menu>
          <el-sub-menu index="1-2">
            <template #title><span>酒店</span></template>
            <el-menu-item index="1-2-1">酒店信息</el-menu-item>
            <el-menu-item index="1-2-2" @click="toHotelManage">酒店管理</el-menu-item>
          </el-sub-menu>
          <el-menu-item index="1-3" @click="toEmployee">员工</el-menu-item>
          <el-sub-menu index="1-4">
            <template #title><span>旅行社管理及旅游车辆管理</span></template>
            <el-menu-item index="1-4-1"@click="toTourguide">导游信息</el-menu-item>
            <el-menu-item index="1-4-2" @click="toTourGuideApl">导游申请 </el-menu-item>
            <el-menu-item index="1-4-3" @click="toRescuer">救援信息</el-menu-item>
            <el-menu-item index="1-4-4" @click="toAgency">旅行社信息</el-menu-item>

          </el-sub-menu>
          <el-menu-item index="1-5" @click="toVisitorInfo">游客管理</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="2" popper-class="dark-mode_2">
          <template #title>
            <el-icon><Document /></el-icon>
            <span>信息列表</span>
          </template>
          <el-menu-item index="2-1" @click="toReservation">游客预约</el-menu-item>
          <el-menu-item index="2-2" @click="toComplaint">游客投诉</el-menu-item>
          <el-menu-item index="2-3" @click="toReview">游客评论</el-menu-item>
          <el-menu-item index="2-4" @click="toInfoManagement">告警信息管理</el-menu-item>
        </el-sub-menu>
        <el-menu-item index="3" popper-class="dark-mode_3" @click="toUserInfo">
          <el-icon><Avatar /></el-icon>
          <template #title>个人信息</template>
        </el-menu-item>
        <el-menu-item index="4" popper-class="dark-mode_4" @click="dialogVisible=true">
          <el-icon><SwitchButton /></el-icon>
          <template #title>退出登录</template>
        </el-menu-item>
        <el-menu-item index="5" popper-class="dark-mode_5" @click="toTouristReview">
          <el-icon><SwitchButton /></el-icon>
          <template #title>评论</template>
        </el-menu-item>
        <el-menu-item index="6" popper-class="dark-mode_6" @click="toReceive">
          <el-icon><IconCopy1 /></el-icon>
          <template #title>收件箱</template>
        </el-menu-item>
        <el-menu-item index="7" popper-class="dark-mode_7" @click="toRegistration">
          <el-icon><IconCopy1 /></el-icon>
          <template #title>登记</template>
        </el-menu-item>
        <el-menu-item index="8" popper-class="dark-mode_8" @click="toVisitorGuideApi">
          <el-icon><IconCopy1 /></el-icon>
          <template #title>导游申请</template>
        </el-menu-item>
      </el-menu>
      <el-menu
          default-active="1"
          class="el-menu-vertical-demo dark-mode"
          :collapse="isCollapse"
          @open="handleOpen"
          @close="handleClose"
          :background-color="menuBackgroundColor"
          :text-color="menuTextColor"
          :active-text-color="menuActiveTextColor">

      </el-menu>
    </el-aside>
    <!--右侧主区域-->
    <el-container>

      <!--中间区域-->
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>

  <el-dialog
      v-model="dialogVisible"
      title="提示"
      width="30%"
  >
    <span>确定退出登录吗？亲</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="danger" @click="dialogVisible=false;toQuit()">确定</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import {User,Crop,EditPen,SwitchButton,} from "@element-plus/icons-vue";
import { ref } from 'vue'
import router from "@/router";
import {delStorage,getStorage} from "@/utils/localStorage.js";


import {
  Document,
  Grid,
  House,
  Avatar
} from '@element-plus/icons-vue'

const isCollapse = ref(false) // 修改为 false 使菜单默认展开
const menuBackgroundColor = ref('black')
const menuTextColor = ref('#ffffff')
const menuActiveTextColor = ref('#ffffff')
const dialogVisible = ref(false);
//定义用户类型
// const userType = ref()
// userType.value = JSON.parse(getStorage(""))
// 定义数组变量
const items = ref<string[]>(['item1', 'item2', 'item3'])
const userInfo = ref({
  menus:[],
  user:{}
})
// 添加更多项目
items.value.push('item4')

const handleOpen = (key: string, keyPath: string[]) => {

}
const handleClose = (key: string, keyPath: string[]) => {

}
const toQuit=()=>{
  // delStorage("UserInfo");
  delStorage("Token");
  location.reload(); // 刷新浏览器

}
const toHouse = () => {
  router.push("/main")
}
const toReview = () => {
  router.push("/customReview")
}
const toTourGuideApl = () => {
  router.push("/tourguideApl")
}
const toScenicManage = () => {
  router.push("/scenicManage")
}
const toHotelManage = () => {
  router.push("/hotelManage")
}
const toVisitorInfo = () => {
  router.push("/toVisitorInfo")
}
const toEmployee = () => {
  router.push("/employee")
}
const toUserInfo = () => {
  router.push("/userInfo")
}
const toReservation =()=>{
  router.push("/reservation")
}

const toTourguide = ( ) => {
  router.push("/tourguide")
}

const toScenicInfo=()=>{
  router.push("/scenicInfo")
}

const toComplaint=()=>{
  router.push("/complaintManage")
}

const toAgency=()=>{
  router.push("/travelAgencyManage")
}

const toRescuer=()=>{
  router.push("/rescuer")
}
const toTouristReview=()=>{
  router.push("/touristReview")

}

const toInfoManagement=()=>{
  router.push("/infoManagement")
}
const toReceive=()=>{
  router.push("/receiveInfo")
}
const toRegistration=()=>{
  router.push("/registrationManage")
}

const toVisitorGuideApi=()=>{
  router.push("/visitorGuideApi")
}
</script>

<style scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 270px;
  min-height: 400px;
}

.left {
  position: fixed; /* 使左侧菜单固定 */
  top: 0;
  left: 0;
  height: 100vh;
  background-color: black;
  overflow-y: auto; /* 允许菜单内部滚动 */
}

.el-main {
  margin-left: 250px; /* 确保内容不被固定的菜单遮挡 */
}

.el-menu-item:hover {
  background-color: rgba(100, 190, 255, 0.9) !important;
}
</style>

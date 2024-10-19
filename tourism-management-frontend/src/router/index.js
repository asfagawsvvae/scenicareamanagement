import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/home/HomeView.vue'
import LoginView from "@/views/login/LoginView.vue";
import RegisterView from "@/views/register/RegisterView.vue";
import {ElMessage} from "element-plus";
import {getStorage} from "@/utils/localStorage.js";
import test from "@/views/test.vue";
import HotelManageView from "@/views/hotel/HotelManageView.vue";
import VisitorInfoView from "@/views/visitorInfo/VisitorInfoView.vue";
import EmployeeView from "@/views/employee/EmployeeView.vue";
import UserInfoView from "@/views/userInformation/UserInfoView.vue";
import ReservationView from "@/views/reservation/ReservationView.vue";
import TourguideAplView from "@/views/tourGuide/TourguideAplView.vue";
import TourguideView from "@/views/tourGuide/TourguideView.vue";
import ScenicInfoView from "@/views/scenicInfo/ScenicInfoView.vue";
import ScenicItemView from "@/views/scenicInfo/ScenicItemView.vue";
import ComplaintManageView from "@/views/complaint/ComplaintManageView.vue";
import ComplaintView from "@/views/complaint/ComplaintView.vue";
import TravelAgencyManageView from "@/views/tourGuide/TravelAgencyManageView.vue";
import RegistrationManageView from "@/views/registration/RegistrationManageView.vue";
import RescuerView from "@/views/employee/RescuerView.vue";
import TouristReviewView from "@/views/review/TouristReviewView.vue";
import CableCarView from "@/views/cablecar/CableCarView.vue";
import InfoManagementView from "@/views/infoManagement/InfoManagementView.vue";
import ReceiveInfoView from "@/views/receiveInfo/ReceiveInfoView.vue";
import VisitorGuideApl from "@/views/tourGuide/VisitorGuideApl.vue";
import TouristToRescuerView from "@/views/employee/TouristToRescuerView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'login',
      component: LoginView
    },
    {
      path: '/register',
      name: 'register',
      component: () => import('../views/register/RegisterView.vue')
    },
    {
      path: '/test',
      name: 'test',
      component: () => import('../views/test.vue')
    },
    {
      path: '/test_1',
      name: 'test_1',
      component: () => import('../views/test_1.vue')
    },
    {
      path: '/home',
      name: 'home',
      component: HomeView,
      redirect:'main',
      children:[
        {
          path: '/main',
          name: 'main',
          component: () => import('../views/main/MainView.vue')
        },
        {
          path: '/customReview',
          name: 'customReview',
          component: () => import('../views/review/ReviewView.vue')
        },
        {
          path: '/scenicManage',
          name: 'scenicManage',
          component: () => import('../views/scenic/scenicManage.vue')
        },
        {
          path: '/hotelManage',
          name: 'hotelManage',
          component: HotelManageView
        },
        {
          path: '/toVisitorInfo',
          name: 'toVisitorInfo',
          component: VisitorInfoView
        },
        {
          path: '/employee',
          name: 'employee',
          component: EmployeeView
        },
        {
          path: '/userInfo',
          name: 'userInfo',
          component: UserInfoView
        },
        {
          path: '/reservation',
          name: 'reservation',
          component: ReservationView
        },
        {
          path: '/tourguide',
          name: 'tourguide',
          component:TourguideView
        },
        {
          path: '/tourguideApl/',
          name: 'tourguideApl',
          component:TourguideAplView
        },
        {
          path: '/scenicInfo/',
          name: 'scenicInfo',
          component:ScenicInfoView,

        },
        {
          path: '/scenicItem/:id',
          name: 'scenicItem',
          component:ScenicItemView
        },
        {
          path: '/complaintManage',
          name: 'complaintManage',
          component: ComplaintManageView
        },
        {
          path: '/complaint',
          name: 'complaint',
          component: ComplaintView
        },
        {
          path: '/travelAgencyManage',
          name: 'travelAgencyManage',
          component: TravelAgencyManageView
        },
        {
          path: '/registrationManage',
          name: 'registrationManage',
          component: RegistrationManageView
        }, {
          path: '/rescuer',
          name: 'rescuer',
          component: RescuerView
        },
        {
          path: '/touristReview',
          name: 'touristReview',
          component: TouristReviewView
        },
        {
          path: '/cableCar',
          name: 'cableCar',
          component: CableCarView
        },
        {
          path: '/infoManagement',
          name: 'infoManagement',
          component: InfoManagementView
        },
        {
          path: '/receiveInfo',
          name: 'receiveInfo',
          component: ReceiveInfoView
        },
        {
          path: '/visitorGuideApi',
          name: 'visitorGuideApi',
          component: VisitorGuideApl
        },
        {
          path: '/touristToRescuer',
          name: 'touristToRescuer',
          component: TouristToRescuerView
        },


      ]
    },
    // {
    //   path: '/test',
    //   name: 'register',
    //   component: test
    // }
  ]
})
// 全局路由守卫
router.beforeEach((to, from, next) => {
  if (to.path === '/' || to.path === '/register') {
    // 如果目标路由是 /，则直接放行
    next()
  } else {
    // 否则进行身份验证、权限判断等操作
    //添加token
    let token = getStorage("Token");
    //判断有没有token
    if(token){
      next() // 放行
    } else {
      next('/') // 重定向到登录页面
      ElMessage.error('请先登录')
    }
  }
})

export default router

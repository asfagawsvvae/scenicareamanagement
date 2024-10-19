import axios from 'axios';
import {ElMessage } from 'element-plus';
import Router from '@/router';
import { getStorage } from "@/utils/localStorage.js";
const URL =  "http://localhost:8080";
// create an axios instance
const service = axios.create({
    baseURL: URL, // url = base url + request url
    timeout: 10000, // request timeout
    withCredentials: true,
    crossDomain: true
})

// http request 拦截器
service.interceptors.request.use(
    config => {
        config.headers['Token'] = getStorage('Token')
        return config
    },
    error => {
        return Promise.reject(error)
    }
)
service.interceptors.response.use(
    response => {
        if(response.data){//服务器返回了数据
            //对自己的返回结果做判断
            if(response.data.code == 200){
                return response.data
            }else if(response.data.code == -2){
                //登录失效, 跳转到登录页面
                //提示
                ElMessage({
                    message: response.data.message,
                    type: "error",
                    duration: 2000
                });
                //跳转到登录页面
                Router.push({path: "/"})
                return false;
            }else{
                //提示
                ElMessage({
                    message: response.data.message,
                    type: "error",
                    duration: 2000
                });
            }
            return -1;
        }

    },
    error => {
        return Promise.reject(error)
    }
)

export default service
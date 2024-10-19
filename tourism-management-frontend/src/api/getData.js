import request from "@/utils/request";
import axios from "axios";

/**
 * 用户登录的api
 * @param data
 */
export  const doLogin=(data)=>{
    return request({
        url:'user/login', //请求url
        method :'post', //请求方式
        data //请求参数
    }

    )
}
// 获取评论列表
export const getReviewList=(data)=>{
    return request({
        url:'review/getReviewList',                         // 请求url
        method:'post',                                      // 请求方式
        data                                                // 请求参数
    })
}

// 删除评论
export const deleteReview=(id)=>{
    return request({
        url:'review/deleteReviewByReviewId/'+id,            // 请求url
        method:'post',                                      // 请求方式
    })
}
export  const registerUser=(data)=>{

    return request({
        url:'user/register' ,//请求url
        method :'post', //请求方式
        data,
    })
}


export const getScenicAreaList=(data)=>{
    return request({
        url:'scenicArea/getScenicAreaList', //请求url
        method :'post', //请求方式
        data
    })
}

export const deleteScenicArea=(scenicAreaName)=>{
    return request({
        url:'scenicArea/deleteScenicArea/'+scenicAreaName, //请求url
        method :'post', //请求方式
    })
}

export const addScenicArea=(data)=>{
    return request({
        url:'scenicArea/addScenicArea', //请求url
        method :'post', //请求方式
        data
    })
}

export const updateScenicArea=(data)=> {
    return request({
        url: 'scenicArea/updateScenicArea', //请求url
        method: 'post', //请求方式
        data
    })
}



/**
 * 编辑学生
 */
export  const updateTourguide=(data)=>{
    return request({
        url:'tourguide/updateTourguide' ,//请求url
        method :'post', //请求方式
        data
    })
}

export const getHotelsByHotelNameList=(data)=>{
    return request({
        url:'hotel/getHotelsByHotelNameList' ,//请求url
        method :'post', //请求方式
        data
    })
}
/**
 * 添加酒店
 * @param data
 */
export const addHotel=(data)=>{
    return request({
        url:'hotel/addHotel' ,//请求url
        method :'post', //请求方式
        data
    })
}
/**
 * 删除酒店
 * @param data
 */
export const deleteHotel=(hotelName)=>{
    return request({
        url:'hotel/deleteHotel/' + hotelName,//请求url
        method :'post' //请求方式
    })
}
/**
 *修改酒店
 * @param data
 */
export const updateHotel=(data)=>{
    return request({
        url:'hotel/updateHotel' ,//请求url
        method :'post', //请求方式
        data
    })
}

export const getVisitorinfoByVisitorNameList=(data)=>{
    return request({
        url:'visitorinfo/getVisitorinfoByVisitorNameList' ,//请求url
        method :'post', //请求方式
        data
    })
}


// 获取员工列表
export const getEmployeeList=(data)=>{
    return request({
        url:'employee/getEmployeeList',                     // 请求url
        method:'post',                                      // 请求方式
        data                                                // 请求参数
    })
}

// 删除/解雇员工
export const deleteEmployeeByEmployeeID=(id)=>{
    return request({
        url:'employee/deleteEmployeeByEmployeeID/'+id,      // 请求url
        method:'post',                                      // 请求方式
    })
}

// 增加员工
export const addEmployee=(data)=>{
    return request({
        url:'employee/addEmployee',                         // 请求url
        method:'post',                                      // 请求方式
        data                                                // 请求参数
    })
}

// 修改员工信息
export const updateEmployee=(data)=> {
    return request({
        url:'employee/updateEmployee',                      // 请求url
        method:'post',                                      // 请求方式
        data                                                // 请求参数
    })
}
// 获取职称名单
export const getJobList=(data)=>{
    return request({
        url:'employee/getJobList',                          // 请求url
        method:'post',                                      // 请求方式
        data                                                // 请求参数
    })
}

// 获取工作地点列表
export const getLocationList=(data)=>{
    return request({
        url:'employee/getLocationList',                     // 请求url
        method:'post',                                      // 请求方式
        data                                                // 请求参数
    })
}

// 修改个人信息
export const updateUser=(data)=>{
    return request({
        url:'user/updateUser' ,                             // 请求url
        method :'post',                                     // 请求方式
        data                                                // 请求参数
    })
}
// 修改密码
export const updatePassword=(data)=>{
    return request({
        url:'user/updatePassword' ,                         // 请求url
        method :'post',                                     // 请求方式
        data                                                // 请求参数
    })
}
export const selectUserByUsername=(data)=>{
    return request({
        url:'user/selectUserByUsername',                    // 请求url
        method:'post',                                      // 请求方式
        data                                                // 请求参数
    })
}
export const getScenicInfo=()=>{
    return request({
        url:'scenicArea/getScenicInfo',                     // 请求url
        method:'post',                                      // 请求方式
                                                            // 请求参数
    })

}

export const getScenicInfo_1=()=>{
    return request({
        url:'scenicArea/getScenicInfo_1',                     // 请求url
        method:'post',                                      // 请求方式
                                                            // 请求参数
    })

}

export  const deleteReservation=(data)=>{

    return request({
        url:'reservation/deleteReservation',
        method :'post', //请求方式
        data
    })
}
export  const getReservationByNameList=(data)=>{

    return request({
        url:'reservation/getReservationByNameList', //请求url
        method :'post', //请求方式
        data//请求参数
    })
}


export  const addVisitorInfo=(data)=>{

    return request({
        url:'reservation/addVisitorInfo' ,//请求url
        method :'post', //请求方式
        data
    })
}

export  const deleteTourguide=(guideid)=>{

    return request({
        url:'tourguide/deleteTourguide/'+guideid, //请求url
        method :'post', //请求方式
    })
}

export  const getTourguideByNameList1=(data)=>{

    return request({
        url:'tourguide/getTourguideByNameList1', //请求url
        method :'post', //请求方式
        data//请求参数
    })
}

export  const getTourguideByNameList=(data)=>{

    return request({
        url:'tourguide/getTourguideByNameList', //请求url
        method :'post', //请求方式
        data//请求参数
    })
}


export const getComplaintListByType=(data)=>{
    return request({
        url:'complaint/getComplaintListByType', //请求url
        method :'post', //请求方式
        data
    })
}

export const deleteComplaint=(complaintId)=>{
    return request({
        url:'complaint/deleteComplaint/'+complaintId, //请求url
        method :'post' //请求方式
    })
}

export const getComplaintListByUsername=(data)=>{
    return request({
        url:'complaint/getComplaintListByUsername', //请求url
        method :'post', //请求方式
        data
    })
}
export const getScenicAreas=()=>{
    return request({
        url:'complaint/getScenicAreas', //请求url
        method :'post', //请求方式
    })
}

export const getHotels=()=>{
    return request({
        url:'complaint/getHotels', //请求url
        method :'post', //请求方式
    })
}

export const addComplaint=(data)=>{
    return request({
        url:'complaint/addComplaint', //请求url
        method :'post', //请求方式
        data
    })
}
export const getVehicleListByAgency=(data)=>{
    return request({
        url:'vehicle/getVehicleListByAgency', //请求url
        method :'post', //请求方式
        data
    })
}

export const addVehicle=(data)=>{
    return request({
        url:'vehicle/addVehicle', //请求url
        method :'post', //请求方式
        data
    })
}

export const updateVehicle=(data)=>{
    return request({
        url:'vehicle/updateVehicle', //请求url
        method :'post', //请求方式
        data
    })
}

export const deleteVehicle=(vehicleId)=>{
    return request({
        url:'vehicle/deleteVehicle/'+vehicleId, //请求url
        method :'post' //请求方式
    })
}


export const deleteTravelAgency=(name)=>{
    return request({
        url:'travelAgency/deleteTravelAgency/'+name, //请求url
        method :'post' //请求方式
    })
}
export const addTravelAgency=(data)=>{
    return request({
        url:'travelAgency/addTravelAgency', //请求url
        method :'post', //请求方式
        data
    })
}
export const getTravelAgencyByNameList=(data)=>{
    return request({
        url:'travelAgency/getTravelAgencyByNameList', //请求url
        method :'post', //请求方式
        data
    })
}

export const updateTravelAgency=(data)=>{
    return request({
        url:'travelAgency/updateTravelAgency', //请求url
        method :'post', //请求方式
        data
    })
}




export  const getAgencyNameList=(data)=>{

    return request({
        url:'travelAgency/getAgencyNameList', //请求url
        method :'post', //请求方式
        data
    })
}


export  const getAgencyNameList_1=()=>{

    return request({
        url:'travelAgency/getAgencyNameList_1', //请求url
        method :'post', //请求方式
    })
}






export  const getGuideNameList=(data)=>{

    return request({
        url:'tourguide/getGuideNameList', //请求url
        method :'post', //请求方式
        data
    })
}

export  const getReservationNum=()=>{

    return request({
        url:'reservation/getReservationNum', //请求url
        method :'post', //请求方式
    })
}

export  const getHotelByScenicAreaName=(data)=>{

    return request({
        url:'hotel/getHotelByScenicAreaName', //请求url
        method :'post', //请求方式
        data
    })
}
export  const   addReservation=(data)=>{

    return request({
        url:'reservation/addReservation', //请求url
        method :'post', //请求方式
        data
    })
}

export const addRegistration=(data)=>{
    return request({
        url:'registration/addRegistration', //请求url
        method :'post', //请求方式
        data
    })
}



export const getRegistrationList=(data)=>{
    return request({
        url:'registration/getRegistrationList', //请求url
        method :'post', //请求方式
        data
    })
}

export const getAllHotel=()=>{
    return request({
        url:'review/getAllHotel' ,                          // 请求url
        method :'post',                                     // 请求方式
    })
}

export const getAllScenic=()=>{
    return request({
        url:'review/getAllScenic' ,                         // 请求url
        method :'post',                                     // 请求方式
    })
}

export const getRescuerList=(data)=>{
    return request({
        url:'employee/getRescuerList',                      // 请求url
        method:'post',                                      // 请求方式
        data                                                // 请求参数
    })
}


// 添加游客评论
export const addReview=(data)=>{
    return request({
        url:'review/addReview' ,                            // 请求url
        method :'post',                                     // 请求方式
        data                                                // 请求参数
    })
}


// 根据用户名获取用户评论
export const getReviewByUsername=(data)=>{
    return request({
        url:'review/getReviewByUsername' ,                  // 请求url
        method :'post',                                     // 请求方式
        data                                                // 请求参数
    })
}

// 修改游客评论
export const updateReview=(data)=>{
    return request({
        url:'review/updateReview' ,                         // 请求url
        method :'post',                                     // 请求方式
        data                                                // 请求参数
    })
}


/**
 *获取缆车列表
 * @param data
 */
export const getCableCarByNameList=(data)=>{
    return request({
        url:'cablecar/getCableCarByNameList' ,//请求url
        method :'post',//请求方式
        data
    })
}
/**
 *获取缆车列表
 * @param data
 */
export const addCableCar=(data)=>{
    return request({
        url:'cablecar/addCableCar' ,//请求url
        method :'post',//请求方式
        data
    })
}
/**
 *获取缆车列表
 * @param data
 */
export const deleteCableCar=(cableCarName)=>{
    return request({
        url:'cablecar/deleteCableCar/'+cableCarName ,//请求url
        method :'post'//请求方式
    })
}
/**
 *获取缆车列表
 * @param data
 */
export const updateCableCar=(data)=>{
    return request({
        url:'cablecar/updateCableCar' ,//请求url
        method :'post',
        data//请求方式
    })
}

export const getRatingByHotelName=(hotelName)=>{
    return request({
        url:'hotel/getRatingByHotelName/'+hotelName ,//请求url
        method :'post'//请求方式
    })
}

export const getInfoById=(data)=>{
    return request({
        url:'infoManagement/getInfoById' ,//请求url
        method :'post',
        data//请求方式
    })
}

export const addInfo=(data)=>{
    return request({
        url:'infoManagement/addInfo' ,//请求url
        method :'post',
        data//请求方式
    })
}

export const updateInfo=(data)=>{
    return request({
        url:'infoManagement/updateInfo' ,//请求url
        method :'post',
        data//请求方式
    })
}

export const updatePublishStatus=(data)=>{
    return request({
        url:'infoManagement/updatePublishStatus' ,//请求url
        method :'post',
        data//请求方式
    })
}

/**
 * 通过酒店名字得到酒店的空余房间数和停车位
 */
export  const getHotelNum=(data)=>{
    return request({
        url:'hotel/getHotelNum' ,//请求url
        method :'post', //请求方式
        data
    })
}


/**
 * 游客录用表
 * 通过景区名字得到景区未住酒店的停车位
 */
export  const getVisitorParkingNum=(data)=>{
    return request({
        url:'visitorinfo/getVisitorParkingNum' ,//请求url
        method :'post', //请求方式
        data
    })
}

/**
 * 游客录用表
 * 通过酒店名字得到酒店的车位数量
 */
export  const getVisitorHotelParkingNum=(data)=>{
    return request({
        url:'visitorinfo/getVisitorHotelParkingNum' ,//请求url
        method :'post', //请求方式
        data
    })
}
export  const addTourguide=(data)=>{
    return request({
        url:'tourguide/addTourguide' ,//请求url
        method :'post', //请求方式
        data
    })
}

export const getRateByName=(scenicAreaName)=>{
    return request({
        url:'scenicArea/getRateByName/'+scenicAreaName, //请求url
        method :'post' //请求方式
    })
}

export const getInfoPublish=(data)=>{
    return request({
        url:'infoManagement/getInfoPublished', //请求url
        method :'post',
        data//请求方式
    })
}


<template>
  <div class="card-wrapper">
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <span>{{ item.scenicAreaName }} 描述信息</span>
        </div>
      </template>
      <el-row>
        <el-col :span="12">
          <div class="image-container">
            <img :src="item.imageUrl" class="cardImage" />
          </div>
        </el-col>
        <el-col :span="12">
          <div class="card-content">
            <p class="price">价格：{{ item.ticketPrice }}&nbsp元</p>
            <p class="address">地址：{{ item.address }}</p>
            <p class="capacity">最大容客量：{{ item.maxCapacity }}&nbsp人</p>
            <p class="parkingSpaces">停车位数量：{{ item.parkingSpaces }}&nbsp辆</p>
            <p class="visitorCount">当前游客数量：{{ item.visitorCount }}&nbsp人</p>
            <p class="leftParkingSpaces">剩余停车位: {{ item.parkingSpaces - item.visitorCount }}&nbsp辆</p>
          </div>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form class="form-container">
            <el-form-item label="酒店">
              <el-select v-model="reservationModel.hotel" placeholder="请选择酒店" clearable>
                <el-option
                    v-for="item in Hotel"
                    :key="item.value"
                    :label="item.label"
                    :value="item.label"
                />
                <el-option label="无" value="无" />
              </el-select>
            </el-form-item>
            <el-form-item label="意向旅行社">
              <el-select v-model="reservationModel.travelagency" placeholder="请选择旅行社" clearable @change="handleTravelAgencyChange">
                <el-option
                    v-for="item in AgencyName"
                    :key="item.value"
                    :label="item.label"
                    :value="item.label"
                />
                <el-option label="无" value="无" />
              </el-select>
            </el-form-item>
            <el-form-item label="开车情况">
              <el-select v-model="reservationModel.parking" placeholder="请选择是否开车" clearable>
                <el-option label="是" value="1" />
                <el-option label="否" value="0" />
              </el-select>
            </el-form-item>
            <el-form-item label="导游">
              <el-select v-model="reservationModel.guide" placeholder="请选择导游" clearable :disabled="reservationModel.travelagency === '无'">
                <el-option
                    v-for="item in GuideName"
                    :key="item.value"
                    :label="item.label"
                    :value="item.label"
                />
                <el-option v-if="reservationModel.travelagency === '无'" label="无" value="无" />
              </el-select>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <div class="demo-date-picker">
            <el-date-picker
                v-model="reservationModel.date"
                type="date"
                placeholder="选择时间"
                :size="size"
                :picker-options="pickerOptions"
                :disabled-date="disabledDate"
            />
          </div>
        </el-col>
        <el-col :span="12">
          <el-button type="primary" @click="doAddReservation">
            确定预约
          </el-button>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { getStorage } from "@/utils/localStorage.js";
import {
  addReservation,
  getAgencyNameList,
  getGuideNameList,
  getHotelByScenicAreaName, getHotelNum, getVisitorHotelParkingNum, getVisitorParkingNum,
} from "@/api/getData.js";
import {ElMessage} from "element-plus";

const item = ref({});
const Hotel = ref([]);
const value1 = ref('');
const AgencyName = ref([]);
const GuideName = ref([]);
const OccupiedRoomsNum = ref([]);
const maxRoomsNum = ref([]);
const HotelParkingNum = ref([]);
const HotelVisitorParkingNum = ref([]);
const VisitorParkingNum = ref([]);
const size = ref('');
const pickerOptions = ref({});

function formatDate(date) {
  const d = new Date(date);
  const year = d.getFullYear();
  const month = ('0' + (d.getMonth() + 1)).slice(-2);
  const day = ('0' + d.getDate()).slice(-2);
  return `${year}-${month}-${day}`;
}

item.value = JSON.parse(getStorage("item"));

const reservationModel = ref({
  username: '', //缓存
  phone: '', //缓存
  scenicarea: '', //缓存
  hotel: '', //选
  travelagency: '', //选
  date: '', //选
  guide: '', //选
  parking: '', //选
  idcard:'1',
});
reservationModel.value.username = getStorage("username");
reservationModel.value.phone = getStorage("phoneNumber");
reservationModel.value.scenicarea = getStorage("item_scenicarea");

// 得到景区不住酒店的游客的停车数量
const dogetVisitorParkingNum = () => {
  let params = {
    destination: reservationModel.value.scenicarea,
    date: formatDate(reservationModel.value.date),
  };

  getVisitorParkingNum(params).then((res) => {
    if (res.code == 200) {
      VisitorParkingNum.value = res.datas
    }
  })
}

const doAddReservation = () => {

  if (!reservationModel.value.hotel || !reservationModel.value.travelagency || !reservationModel.value.parking || !reservationModel.value.guide||!reservationModel.value.date) {
    ElMessage.warning('请完善表格');
    return;
  }




  console.log(transferDate(reservationModel.value.date))
  if (item.value.visitorCount >= item.value.maxCapacity) {
    ElMessage({
      type: "warning",
      message: "当天预约人数已满",
    })
    return
  }

  dogetVisitorParkingNum()

  if (reservationModel.value.hotel!= "无") {

    let params = {
      hotelName: reservationModel.value.hotel,
      date: transferDate(reservationModel.value.date)
    };

    getHotelNum(params).then((res) => {
      console.log(res)
      if (res.code == 200) {
        maxRoomsNum.value = res.datas[0].maxRooms
        OccupiedRoomsNum.value = res.datas[0].occupiedRooms
        HotelParkingNum.value = res.datas[0].parkingSpaces


      }
    })


    if(OccupiedRoomsNum.value>=maxRoomsNum.value){
      ElMessage({
        type: "warning",
        message: "酒店房间数已满",
      })
      return
    }


    if (reservationModel.value.parking != 0) {
      let params = {
        hotel: reservationModel.value.hotel,
        date: formatDate(reservationModel.value.date)
      };
      // 每个景区对应酒店
      getVisitorHotelParkingNum(params).then((res) => {
        if (res.code == 200) {
          HotelVisitorParkingNum.value = res.datas
        }
      })
      // 景区不住酒店+景区住酒店>景区停车位+酒店停车位
      if (VisitorParkingNum.value + HotelVisitorParkingNum.value > HotelParkingNum.value + item.value.parkingSpaces) {
        ElMessage({
          type: "warning",
          message: "当天车位已满",
        })
        return
      }
    }
  } else {
    if (reservationModel.value.parking != 0 && VisitorParkingNum.value > item.value.parkingSpaces) {
      ElMessage({
        type: "warning",
        message: "当天车位已满",
      })
      return
    }
  }

  addReservation(reservationModel.value).then((res) => {
    if (res.code == 200) {
      ElMessage({
        type: 'success',
        message: '预约成功',
      })
    }
  })
};

const transferDate = (time) => {
  const today = new Date();
  const tomorrow = new Date(today);
  const dayAfterTomorrow = new Date(today);

  tomorrow.setDate(today.getDate() + 1);
  dayAfterTomorrow.setDate(today.getDate() + 2);

  const todayTime = today.setHours(0, 0, 0, 0);
  const tomorrowTime = tomorrow.setHours(0, 0, 0, 0);
  const dayAfterTomorrowTime = dayAfterTomorrow.setHours(0, 0, 0, 0);
  if (time.getTime() == todayTime) return "今天"
  else if (time.getTime() == tomorrowTime) return "明天"
  else if (time.getTime() == dayAfterTomorrowTime) return "后天"
}

const disabledDate = (time) => {
  const today = new Date();
  const tomorrow = new Date(today);
  const dayAfterTomorrow = new Date(today);

  tomorrow.setDate(today.getDate() + 1);
  dayAfterTomorrow.setDate(today.getDate() + 2);

  const todayTime = today.setHours(0, 0, 0, 0);
  const tomorrowTime = tomorrow.setHours(0, 0, 0, 0);
  const dayAfterTomorrowTime = dayAfterTomorrow.setHours(0, 0, 0, 0);

  return (
      time.getTime() !== todayTime &&
      time.getTime() !== tomorrowTime &&
      time.getTime() !== dayAfterTomorrowTime
  );
};

const doGetHotelByScenicAreaName = () => {
  let params = {
    location: reservationModel.value.scenicarea,
  };

  getHotelByScenicAreaName(params).then((res) => {
    if (res.code == 200) {
      Hotel.value = res.datas.map(item => ({
        value: item.hotelId.toString(),
        label: item.hotelName
      }))
    }
  })
}
doGetHotelByScenicAreaName();
/**
 * 提交
 */

/**
 * 得到旅行社名字
 */
const doGetAgencyNameList = () => {
  getAgencyNameList().then((res) => {
    if (res.code === 200 && res.datas) {
      AgencyName.value = res.datas.map(item => ({
        value: item.agencyId.toString(),
        label: item.agencyName
      }));
    }
  });
};

doGetAgencyNameList();

const doGetGuideNameList = () => {
  let params = {
    agencyname: reservationModel.value.travelagency,
  };
  // 可以传输过去params
  getGuideNameList(params).then((res) => {
    if (res.code === 200 && res.datas) {
      GuideName.value = res.datas.map(item => ({
        value: item.guideid.toString(),
        label: item.guidename
      }));
    }
  }).catch((error) => {
  });
};

const handleTravelAgencyChange = () => {
  if (reservationModel.value.travelagency === '无') {
    reservationModel.value.guide = '无';
  } else {
    reservationModel.value.guide = ''; // Reset selected guide when travel agency changes
    doGetGuideNameList(); // 调用获取导游列表的方法
  }
};
</script>

<style scoped>
.card-wrapper {
  padding: 20px;
  padding-left: 200px;
}

.box-card {
  width: 80%;
  border-radius: 10px;
}

.card-header {
  font-size: 18px;
  font-weight: bold;
  text-align: center;
  background-color: #f5f5f5;
  padding: 10px;
}

.image-container {
  text-align: center;
  padding: 10px;
}

.cardImage {
  width: 100%;
  height: auto;
  border-radius: 10px;
}

.card-content {
  padding: 10px;
  font-size: 20px;
  padding-top: 20px;
}

.card-content p {
  margin: 0 0 10px;
}

.form-container {
  padding: 10px;
}

.demo-date-picker {
  margin-top: 10px;
  text-align: center;
  width: 630px;
}

.el-button {
  margin-top: 10px;
}
</style>

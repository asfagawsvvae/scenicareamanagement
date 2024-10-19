<template>
  <div class="container">
    <div class="container_header">
    </div>
    <el-main  class="main-view-main">
      <div class="header_container">
        <div class="header">
          中南大学旅行调度和管理系统
        </div>
      </div>
    <div class="container_1 carousel">
      <el-carousel
          class="carousel"
          ref="carousel"
          :interval="3000"
          arrow="always"
          type="card"
      >
        <el-carousel-item v-for="(item, index) in scenicInfos" :key="index" @click="toItem(item)">
          <div class="image-container">
            <img :src="item.imageUrl" class="carousel-image" />
          </div>
        </el-carousel-item>

      </el-carousel>
    </div>

    <div class="container_2">
      <el-space wrap :size=80>
        <div class="card-container">
          <el-card v-for="(item, index) in scenicInfos" :key="index" class="box-card" @click="toItem(item)">
            <div class="image-container">
              <img :src="item.imageUrl" class="cardImage" />
            </div>
            <template #header>
              <div class="card-header">
                <span>{{ item.scenicAreaName }}</span>
              </div>
            </template>
            <div class="card-content">
              <p class="price">票价： {{ item.ticketPrice }}元</p>
              <p class="address">地址： {{ item.address }}</p>
            </div>
          </el-card>
          <el-card v-for="(item, index) in scenicInfos_1" :key="index" class="box-card" @click="toItem(item)">
            <div class="image-container">
              <img :src="item.imageUrl" class="cardImage" />
            </div>
            <template #header>
              <div class="card-header">
                <span>{{ item.scenicAreaName }}</span>
              </div>
            </template>
            <div class="card-content">
              <p class="price">票价： {{ item.ticketPrice }}元</p>
              <p class="address">地址： {{ item.address }}</p>
            </div>
          </el-card>
        </div>

      </el-space>
    </div>
    </el-main>
  </div>
</template>
<script setup>
import router from "@/router/index.js";
import {ref} from "vue";
import {getScenicInfo,getScenicInfo_1} from "@/api/getData.js";
import {getStorage, setStorage} from "@/utils/localStorage.js";

const scenicInfos = ref([])
const scenicInfos_1 = ref([])

const listScenicInfo=()=>{
  getScenicInfo().then((res)=>{
    if(res.code == 200){
      scenicInfos.value = res.datas;
      setStorage("cards",JSON.stringify(res.datas))
    }
  });
  getScenicInfo_1().then((res)=>{
    if(res.code == 200){
      scenicInfos_1.value = res.datas;
      setStorage("cards_1",JSON.stringify(res.datas))
    }
  });

}
listScenicInfo();
const toView = () =>{
  router.push("/customReview")

}

const toItem=(item)=>{
  // console.log(item)
  // console.log(item.scenicAreaCode)
  setStorage("item",JSON.stringify(item))
  setStorage("item_scenicarea",item.scenicAreaName)
  setStorage("item_international",item.international)
  // console.log(item.scenicAreaName)
  router.push("scenicItem/"+item.scenicAreaCode);
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Zhi+Mang+Xing&display=swap');
.main-view-main{
  background: linear-gradient(to  right, #9CD7FF,#9CC5FF, #27B5F2);

}
.container_2{
  width: 100%;
  display: flex;
  justify-content: center;
  padding-left: 80px;
  padding-top: 20px;
}
.box-card {
  height: 400px;
  background: linear-gradient(45deg, #E8F5FF,#72D8F7,#FFD04F);
  width: 250px;
  margin: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  transition: box-shadow 0.3s ease;
}
.cardImage {
  width: 100%;
  height: 150px; /* 调整图片高度 */
  object-fit: cover; /* 图片填充方式 */
  transition: transform 0.3s ease;
}

.card-header {
  font-family: 'Zhi Mang Xing', cursive;
  font-size: 30px !important;
  font-weight: bold;
  color: #000;
  text-shadow: 2px 2px 5px rgba(0, 0, 0, 0.3);
  padding: 10px;
  text-align: center;

  //font-weight: bold;

}

.card-content {
  padding: 10px;

}

.price, .address {
  margin-top: 10px !important;
  margin-bottom: 8px!important;
  font-size: 20px;

}
.carousel-image {
  width: 100%;
  height: 100%;
  object-fit: contain;
  cursor: pointer;
  transition: transform 0.3s ease;
}

.carousel {
  width: 100%;
  height: 350px; /* Adjust height as needed */
  position: relative;

}
.card-container {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
}

.image-container {
  overflow: hidden;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.image-container:hover .cardImage {
  transform: scale(1.2);
}


.image-container:hover .carousel-image {
  transform: scale(1.2);
}
.header_container{
    text-align: center;
    font-weight: bolder;
    color: #091ddc;
    padding-bottom: 25px;
}
.header{
  font-family: 'Zhi Mang Xing', cursive;
  font-size: 60px;
}
</style>
<!--.container_header{-->
<!--width: 100%;-->
<!--height: 200px;-->
<!--text-align: center;-->
<!--}-->
//.header{
//  display: inline-block;
//  width: 100%;
//  background: linear-gradient(to  right, #9CD7FF,#9CC5FF, #27B5F2);
//  font-family: 'Zhi Mang Xing', cursive;
//  height: 150px;
//  font-size: 40px;
//  font-weight: bolder;
//
//  padding-top: 50px;
//  text-align: center;
//  text-decoration-color: #00b2ff;
//}
//.container{
//
//}

<!--<el-header>-->
<!--<el-header class="header">-->
<!--  中南大学旅行调度和管理系统-->
<!--</el-header>-->
<!--</el-header>-->
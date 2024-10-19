<template>
  <div>
    <el-tabs class="container_3"v-model="activeName">
      <el-tab-pane label="国内" name="domestic">
        <div class="container_2">
          <el-space wrap :size="80">
            <div class="card-container">
              <el-card
                  v-for="(item, index) in photos"
                  :key="index"
                  class="box-card"
                  @click="toItem(item)"

              >
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
      </el-tab-pane>



      <el-tab-pane label="国外" name="international">
        <div class="container_2">
          <el-space wrap :size="80">
            <div class="card-container">
              <el-card
                  v-for="(item, index) in photos_1"
                  :key="index"
                  class="box-card"
                  @click="toItem(item)"

              >
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
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script setup>
import { getStorage, setStorage } from "@/utils/localStorage.js";
import { ref } from "vue";
import router from "@/router/index.js";

const photos = ref([]);
const photos_1 = ref([]);
const activeName = ref("domestic");

const listPhotos = () => {
  photos.value = JSON.parse(getStorage("cards"));
  photos_1.value = JSON.parse(getStorage("cards_1"));
};

listPhotos();

const toItem = (item) => {
  console.log(item);
  console.log(item.scenicAreaCode);
  setStorage("item", JSON.stringify(item));
  setStorage("item_scenicarea", item.scenicAreaName);
  setStorage("item_international", item.international);
  console.log(getStorage("item_international"));
  router.push("scenicItem/" + item.scenicAreaCode);
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Zhi+Mang+Xing&display=swap');

.container_2{
  width: 100%;
  display: flex;
  justify-content: center;
  padding-left: 80px;
  padding-top: 20px;
  background: linear-gradient(to  right, #9CD7FF,#9CC5FF, #27B5F2);

}
.box-card {
  height: 400px;
  background: linear-gradient(45deg, #E8F5FF,#72D8F7,#FFD04F);
  width: 250px;
  margin: 0px;
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
}


.price, .address {
  margin-top: 10px !important;
  margin-bottom: 8px!important;
  font-size: 20px;


}

.card-container {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
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

</style>

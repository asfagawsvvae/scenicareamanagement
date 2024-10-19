<template>
  <el-carousel
      class="carousel"
      ref="carousel"
      :interval="5000"
      arrow="always"
      height="400px"
      type="card"
  >
    <el-carousel-item v-for="(item, index) in images" :key="index">
      <img :src="item" class="carousel-image" @click="goToPage(index)" />
    </el-carousel-item>
  </el-carousel>
</template>

<script setup>
import img1 from '../assets/1.jpg'
import img2 from '../assets/2.jpg'
import img3 from '../assets/3.jpg'
import img4 from '../assets/4.jpg'

const images = [img1, img2, img3,img4]

const goToPage = (index) => {
  window.open('http://www.baidu.com')
}
</script>

<style scoped>
.carousel-image {
  width: 100%;
  height: 100%;
  object-fit: contain; /* or 'cover' depending on the effect you want */
  cursor: pointer;
}

.carousel {
  width: 100%;
  height: 600px; /* Adjust height as needed */
}
</style>

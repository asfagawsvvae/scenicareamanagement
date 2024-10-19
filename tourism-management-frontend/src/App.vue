<template>
  <el-config-provider :locale="zhCn">
    <RouterView />
  </el-config-provider>
</template>
<script setup>
import {ElConfigProvider} from "element-plus";
import {zhCn} from "element-plus/es/locale/index";
</script>


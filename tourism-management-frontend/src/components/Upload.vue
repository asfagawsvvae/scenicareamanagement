<template>
  <el-upload
      ref="upload"
      class="upload-demo"
      action="http://localhost:8080/uploads"
      :limit="1"
      :on-exceed="handleExceed"
      :auto-upload="false"
      :on-change="handleChange"
  >
    <template #trigger>
      <el-button type="primary">select file</el-button>
    </template>
    <el-button class="ml-3" type="success" @click="submitUpload">
      upload to server
    </el-button>
    <template #tip>
      <div class="el-upload__tip text-red">
        limit 1 file, new file will cover the old file
      </div>
    </template>
  </el-upload>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { genFileId } from 'element-plus'
import type { UploadInstance, UploadProps, UploadRawFile } from 'element-plus'

const upload = ref<UploadInstance>()
const fileName = ref();
const handleExceed: UploadProps['onExceed'] = (files) => {
  upload.value!.clearFiles()
  const file = files[0] as UploadRawFile
  file.uid = genFileId()
  upload.value!.handleStart(file)
}

const handleChange = (file) => {
  // 获取文件名
  fileName.value = file.name;
  console.log('文件名:', fileName);
  // 处理你的逻辑，例如验证文件类型、大小等
  // ...
};

const submitUpload = () => {
  upload.value!.submit()
}
</script>
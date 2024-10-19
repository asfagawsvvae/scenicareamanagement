<template>
  <div class="container">
  <el-card class="info">
    <div class="info-header">
      <h2>导游申请</h2>
    </div>
  <el-form :model="VisitorModel" label-width="auto" style="max-width: 600px">


    <el-form-item label="姓名">
      <el-input v-model="VisitorModel.guidename" disabled />
    </el-form-item>

    <el-form-item label="性别">
      <el-select v-model="VisitorModel.gender" placeholder="请选择性别" clearable>
        <el-option label="男" value="男" />
        <el-option label="女" value="女" />
      </el-select>
    </el-form-item>

    <el-form-item label="年龄">
      <el-input v-model.number="VisitorModel.age" placeholder="请输入年龄" />
    </el-form-item>

    <el-form-item label="意向旅行社">
      <el-select v-model="VisitorModel.agencyname" placeholder="请选择旅行社" clearable>
        <el-option
            v-for="item in AgencyName"
            :key="item.value"
            :label="item.label"
            :value="item.label"
        />
      </el-select>
    </el-form-item>



    <el-button type="primary" style="margin-left: 16px" @click="doApplication">
      申请
    </el-button>
  </el-form>
  </el-card>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import {addTourguide, getAgencyNameList, getAgencyNameList_1} from "@/api/getData.js";
import { ElMessage } from "element-plus";
import {getStorage} from "@/utils/localStorage.js";


const VisitorModel = ref({
  guidename:getStorage("username"),
  gender: '',
  age: '',
  agencyname: ''
});
const AgencyName = ref([]);

const doGetAgencyNameList = () => {
  getAgencyNameList_1().then((res) => {
    console.log(res)
    if (res.code === 200 && res.datas) {
      AgencyName.value = res.datas.map(item => ({
        value: item.agencyId.toString(),
        label: item.agencyName
      }));
    }
  }).catch((error) => {
    console.error('Failed to fetch agency names:', error);
  });
};

onMounted(() => {
  doGetAgencyNameList();
});

const doApplication = () => {
  if (!VisitorModel.value.guidename || !VisitorModel.value.gender || !VisitorModel.value.age || !VisitorModel.value.agencyname) {
    ElMessage.warning('请完善表格');
    return;
  }

  if (!Number.isInteger(VisitorModel.value.age) || VisitorModel.value.age < 0) {
    ElMessage.warning('年龄必须为正整数');
    return;
  }

  addTourguide(VisitorModel.value).then((res) => {
    if (res.code === 200) {
      ElMessage.success('添加成功');
    }
  });
};

</script>

<style scoped>
.info{
  width: 600px;
  padding: 40px;
  text-align: center;
  border-radius: 16px;
  box-shadow: 0 4px 8px rgb(0,0,0,0.1);
}
.container{
  padding-left: 300px;
  align-items: center;
  height: 100vh;
  padding-top: 40px;
}
.info-header{
  margin-bottom: 40px;
}
</style>

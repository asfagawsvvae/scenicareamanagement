<script setup>
  import {ref} from "vue";
  import {
    addRegistration,
    getRegistrationList,
    getScenicAreas
  } from "@/api/getData.js";
  import {ElMessage} from "element-plus";

  const visibleDrawer = ref(false);

  const drawerTitle = ref('');

  const searchForm = ref({});

  const searchUsername = ref('');

  const registrations = ref([]);

  const page = ref(1);

  const total = ref(10);

  const limit = ref(10);

  const scenicAreas = ref([]);

  const registrationModel = ref({
    username: '',
    destination: '',
    parking: '',
    date: ''
  })

  const clearData = () => {
    registrationModel.value = {
      username: '',
      destination: '',
      parking: ''
    }
  }

  const handleSizeChange = (size) => {
    limit.value = size;
    registrationList();
  }

  const handleCurrentChange = (num) => {
    page.value = num;
    registrationList();
  }

  const registrationList = () => {
    let params = {
      username: searchUsername.value,
      page: page.value,
      limit: limit.value
    }
    getRegistrationList(params).then((res)=>{
      if (res.code === 200) {
        registrations.value=res.datas;
        total.value=res.total;
      }
    })
  }

  const cancelAdd = () => {
    clearData();
    visibleDrawer.value = false;
  }

  const getScenicAreaNames = () => {
    getScenicAreas().then( (res) => {
      if (res.code === 200) {
        scenicAreas.value = res.datas.map(name=>({
          label: name,
          value: name
        }))
      }
    })
  }

  const doAddRegistration = () => {
    if (registrationModel.value.username === ''){
      ElMessage.warning("请输入用户名!");
      return;
    }else if (registrationModel.value.destination === ''){
      ElMessage.warning("请选择目的地!");
      return;
    }else {
      addRegistration(registrationModel.value).then(res => {
        if (res.code === 200) {
          ElMessage.success("登记成功");
        }
        visibleDrawer.value = false;
        registrationList();
        clearData();
      })
    }
  }

  registrationList();

  getScenicAreaNames();
</script>

<template>
  <el-card>
    <template #header>
      <div class="card-header">
        <span class="card-header-text">登记管理</span>
        <el-button class="card-header-button" @click="visibleDrawer=true; drawerTitle='游客登记'">游客登记</el-button>
      </div>
    </template>

    <el-form :inline="true" :model="searchForm" class="demo-form-inline">
      <el-form-item label="游客姓名或目的地:">
        <el-input v-model="searchUsername" placeholder="请输入游客姓名或目的地" clearable @clear="registrationList"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="registrationList">搜索</el-button>
      </el-form-item>
    </el-form>

    <el-table :data="registrations" style="width: 100%;height: 100%" class="table-column-text">
      <el-table-column prop="username" label="用户名" width="300"/>
      <el-table-column prop="destination" label="目的地" width="200"/>
      <el-table-column prop="parking" label="是否开车" width="200">
        <template #default="{row}">
          <span>{{ row.parking === 0 ? '否' : '是' }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="date" label="日期" width="300"/>
    </el-table>
    <el-pagination
        v-model:current-page="page"
        v-model:page-size="limit"
        :page-sizes="[10, 20, 50, 100]"
        layout="total, sizes, prev, pager, next, jumper"
        :total=total
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        style="margin-top: 20px;justify-content: flex-end"
    />
  </el-card>

  <el-drawer
      v-model="visibleDrawer"
      :title="drawerTitle"
      size="30%"
      @close="clearData"

  >
    <el-form :model="registrationModel" label-width="auto" style="max-width: 600px">
      <el-form-item label="用户名:" >
        <el-input v-model="registrationModel.username" placeholder="请输入用户名" />
      </el-form-item>
      <el-form-item label="目的地">
        <el-select v-model="registrationModel.destination" placeholder="请选择目的地" @clear="">
          <el-option
              v-for="option in scenicAreas"
              :label="option.label"
              :value="option.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="是否驾车:">
        <el-select v-model="registrationModel.parking" placeholder="请选择是否驾车">
          <el-option label="是" value="1" />
          <el-option label="否" value="0" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="doAddRegistration()">提交</el-button>
        <el-button @click="cancelAdd">取消</el-button>
      </el-form-item>
    </el-form>
  </el-drawer>
</template>

<style scoped>
.card-header{
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.card-header-text{
  font-weight: bold; /* 加粗 */
  font-size: 1.5em; /* 放大，这里使用了相对大小，可以根据需要调整 */
}
.card-header-button{
  padding: 1.1em 2.0em;
  //font-size: 12px;
  text-transform: uppercase;
  //letter-spacing: 2.5px;
  //font-weight: 500;
  color: #000;
  background-color: #fff;
  //border: none;
  border-radius: 45px;
  box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease 0s;
  cursor: pointer;
  outline: none;
}
.card-header-button:hover{
  background-color: #3dc1e1;
  box-shadow: 0px 15px 20px rgba(0, 240, 255, 0.4);
  color: #fff;
  transform: translateY(-7px);
}
.card-header-button:active{
  transform: translateY(-1px);
}
.table-column-text{
  font-weight: bold; /* 加粗 */
  font-size: 1.2em; /* 放大，这里使用了相对大小，可以根据需要调整 */
}
</style>
<script setup>

import {onMounted, ref} from "vue";
import {
  addEmployee,
  deleteEmployeeByEmployeeID, getAllHotel, getAllScenic,
  getEmployeeList,
  getJobList,
  getLocationList,
  updateEmployee
} from "@/api/getData.js";
import {ElMessage, ElMessageBox} from "element-plus";

const searchForm = ref({});
// 定义输入框
const employeeName = ref('');
// 定义分页插件的参数
const page = ref(1);
const total = ref(20);
const limit = ref(10);
// 定义员工列表数组
const employees = ref([]);

// 员工列表显示方法
const employeeList =()=>{
  let params = {
    employeeName: employeeName.value,
    job: employeeJob.value,
    location: employeeLocation.value,
    page: page.value,
    limit: limit.value
  }
  getEmployeeList(params).then((res)=>{
    console.log(params)
    if(res.code===200){
      employees.value = res.datas
      total.value=res.total;
    }
  })
}
// 重置员工列表
const resetEmployeeList =()=>{
  employeeName.value = '';
  employeeJob.value = '';
  employeeLocation.value = '';
  clearDrawer();
  employeeList();
}


// 页面大小发生改变
const handleSizeChange =(size)=>{
  limit.value = size;
  employeeList();
}
// 页码发生变化
const handleCurrentChange =(num)=>{
  page.value = num;
  employeeList();
}

// 初始默认调用
onMounted(()=>{
  employeeList();
  jobList();
  locationList();
  scenicList();
  hotelList();
})

// 删除方法
const handleDelete =(row)=>{
  ElMessageBox.confirm(
      '您确定删除这条数据吗',
      '提示',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }
  ) .then(() => {
    deleteEmployeeByEmployeeID(row.employeeId).then((res)=>{
      if(res.code===200){
        resetEmployeeList();
        ElMessage({
          type: 'success',
          message: '删除成功',
        })
      }
    });
  })
    .catch(() => {
      ElMessage({
        type: 'info',
        message: '取消删除',
      })
    })
}

// 抽屉样式默认不显示
const visibleDrawer = ref(false);
const drawerTitle = ref();

// 抽屉基本属性设置
const employeeModel = ref({
    employeeName: '',
    gender: '',
    age: '',
    job: '景区工作人员',
    location: ''
  }
)
// 清空抽屉
const clearDrawer =()=>{
  employeeModel.value = {
    employeeName: '',
    gender: '',
    age: '',
    job: '景区工作人员',
    location: ''
  }
}

// 点击编辑后进行赋值
const handleEdit =(row)=>{
  visibleDrawer.value=true;
  drawerTitle.value="员工信息编辑";
  employeeModel.value = {...row};
}

const employeeRule = ref(null)

// 新增员工
const doAddEmployee =()=>{
  employeeRule.value.validate((valid)=>{
    if(valid){
      addSuccess();
    } else{
      ElMessage.error("请输入完整的员工信息");
    }
  })
}
const addSuccess=()=>{
  addEmployee(employeeModel.value).then((res)=> {
    if (res.code === 200) {
      ElMessage.success("添加成功");
      clearDrawer();
      visibleDrawer.value = false;
      employeeList();
    }
  })
}

// 修改员工信息
const doUpdateEmployee =()=>{
  employeeRule.value.validate((valid)=>{
    if(valid){
      updateSuccess();
    } else{
      ElMessage.error("请输入完整的员工信息");
    }
  })
}
const updateSuccess=()=>{
  updateEmployee(employeeModel.value).then((res)=> {
    if (res.code === 200) {
      ElMessage.success("修改成功");
      resetEmployeeList();
      visibleDrawer.value = false;
      employeeList();
    }
  })
}

// 指定输入规则
const rules = ref({
  employeeName: [{ required: true, message: '请输入员工名', trigger: 'blur' }],
  gender: [{ required: true, message: '请选择性别', trigger: 'blur' }],
  age: [{ required: true, message: '请输入年龄', trigger: 'blur' }],
  job: [{ required: true, message: '请选择职位', trigger: 'blur' }],
  location: [{ required: true, message: '请选择工作地点', trigger: 'blur' }]
})

// 性别选择
const sexOptions = [
  { value: '男', label: '男' },
  { value: '女', label: '女' }
]

// 职务选择
const employeeJob = ref('')
const jobOptions = ref([])
const jobOptionsInDrawer = ref([])
const jobList =()=>{
  getJobList().then((res)=>{
    if(res.code===200){
      jobOptions.value = res.datas.map(job=>({
        value: job,
        label: job
      }))
      jobOptionsInDrawer.value = res.datas.map(job=>({
        value: job,
        label: job
      }))
    }
  })
}

// 工作地点选择
const employeeLocation = ref('')
const locationOptions = ref([])
const locationList =()=>{
  getLocationList().then((res)=>{
    if(res.code===200){
      locationOptions.value = res.datas.map(location=>({
        value: location,
        label: location
      }))
    }
  })
}

const scenicOptionList = ref([])
const scenicOptionListInDrawer = ref([])
const scenicList =()=>{
  getAllScenic().then((res)=>{
    if(res.code===200){
      scenicOptionList.value = res.datas.map(scenic=>({
        value: scenic,
        label: scenic
      }))
      scenicOptionListInDrawer.value = res.datas.map(scenic=>({
        value: scenic,
        label: scenic
      }))
    }
  })
}
const hotelOptionList = ref([])
const hotelOptionListInDrawer = ref([])
const hotelList =()=>{
  getAllHotel().then((res)=>{
    if(res.code===200){
      hotelOptionList.value = res.datas.map(hotel=>({
        value: hotel,
        label: hotel
      }))
      hotelOptionListInDrawer.value = res.datas.map(hotel=>({
        value: hotel,
        label: hotel
      }))
    }
  })
}

// 点击职务后清空工作地点选项
const clearLocation =()=>{
  employeeModel.value.location = '';

}


</script>

<template>

  <el-card>
    <template #header>
      <div class="card-header">
        <span class="card-header-text">员工管理</span>
        <el-button type="primary" class="card-header-button" @click="visibleDrawer=true; drawerTitle='新增员工';clearDrawer()">新增员工</el-button>
      </div>
    </template>

    <!--搜索表单-->
    <el-form :inline="true" :model="searchForm">
      <el-form-item label="员工名">
        <el-input
            v-model="employeeName"
            placeholder="请输入员工姓名"
            style="width: 200px;"
            clearable
            @change="employeeList"
        />
      </el-form-item>

      <el-form-item label="职务">
        <el-select
            v-model="employeeJob"
            placeholder="请选择职务"
            style="width: 200px;"
            clearable
            @change="employeeList"
        >
          <el-option
              v-for="item in jobOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="工作地点">
        <el-select
            v-model="employeeLocation"
            placeholder="请选择工作地点"
            style="width: 200px;"
            clearable
            @change="employeeList"
        >
          <el-option
              v-for="item in employeeJob===''?locationOptions:employeeJob==='景区工作人员'?scenicOptionList:hotelOptionList"
              :key="item.value"
              :label="item.label"
              :value="item.value"
          />
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="employeeList">搜索</el-button>
      </el-form-item>
      <el-form-item>
        <el-button class="resetButton" @click="resetEmployeeList">重置</el-button>
      </el-form-item>
    </el-form>

    <!--员工列表-->
    <el-table :data="employees" style="width: 100%" class="table-column-text" border>
      <el-table-column prop="employeeName" label="员工名" width="200" />
      <el-table-column prop="gender" label="性别" width="100" />
      <el-table-column prop="age" label="年龄" width="200" />
      <el-table-column prop="job" label="职务" width="300" />
      <el-table-column prop="location" label="工作地点" width="300" />
      <el-table-column fixed="right" label="操作" min-width="200">
        <template #default="{row}">
          <el-button link type="primary" size="small" class="table-column-button" @click="handleEdit(row)">
            编辑
          </el-button>
          <el-button link type="danger" size="small" class="table-column-button" @click="handleDelete(row)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--分页插件-->
    <el-pagination
        v-model:current-page="page"
        v-model:page-size="limit"
        :page-sizes="[10, 20, 50, 100]"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        style="margin-top: 20px; justify-content: flex-end"
    />

    <!--右侧增加抽屉样式进行员工信息编辑-->
    <el-drawer
        v-model="visibleDrawer"
        :title="drawerTitle"
        :direction="rtl"
        size="25%"
    >
      <el-form
          ref ="employeeRule"
          :model="employeeModel"
          :rules="rules"
          label-width="auto"
          style="max-width: 600px"
      >
        <el-form-item label="员工名" prop="employeeName">
          <el-input v-model="employeeModel.employeeName" placeholder="请输入员工名"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-select v-model="employeeModel.gender" placeholder="请选择性别">
            <el-option
                v-for="item in sexOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input-number v-model="employeeModel.age" :min="18" :max="65" label="年龄" />
        </el-form-item>
        <el-form-item label="职务" prop="job">
          <el-select
              v-model="employeeModel.job"
              placeholder="请选择职务"
              @click="clearLocation"
          >
            <el-option
                v-for="item in jobOptionsInDrawer"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="工作地点" prop="location">
          <el-select
              v-model="employeeModel.location"
              placeholder="请选择工作地点"
          >
            <el-option
                v-for="item in employeeModel.job==='景区工作人员'?scenicOptionListInDrawer:hotelOptionListInDrawer"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            />
          </el-select>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="drawerTitle==='新增员工'?doAddEmployee():doUpdateEmployee()">提交</el-button>
        </el-form-item>
      </el-form>

    </el-drawer>

  </el-card>
</template>

<style scoped>

.addEmployeeButton{
  background-color: #3dc1e1;
}
.addEmployeeButton:hover{
  background-color: #13a13d;
}

.resetButton{
  margin-right: 20px;
}


.card-header{
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.card-header-text{
  font-weight: bold; /* 加粗 */
  font-size: 1.5em; /* 放大，这里使用了相对大小，可以根据需要调整 */
}

.table-column-text{
  font-weight: bold; /* 加粗 */
  font-size: 1.2em; /* 放大，这里使用了相对大小，可以根据需要调整 */
}
.table-column-button{
  font-weight: bold; /* 加粗 */
  font-size: 0.9em; /* 放大，这里使用了相对大小，可以根据需要调整 */
}

.card-header-button {
  padding: 1.3em 3em;
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

.card-header-button:hover {
  background-color: #3dc1e1;
  box-shadow: 0px 15px 20px rgba(0, 240, 255, 0.4);
  color: #fff;
  transform: translateY(-7px);
}

.card-header-button:active {
  transform: translateY(-1px);
}
</style>
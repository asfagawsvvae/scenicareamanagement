<script setup>

  import {ref} from "vue";
  import {
    addTravelAgency, addVehicle,
    deleteTravelAgency, deleteVehicle,
    getTravelAgencyByNameList,
    getVehicleListByAgency,
    updateTravelAgency, updateVehicle
  } from "@/api/getData.js";
  import {ElMessage, ElMessageBox} from "element-plus";

  const visibleDrawer = ref(false);

  const drawerTitle = ref('');

  const searchForm = ref({});

  const searchTravelAgencyName = ref('');

  const travelAgencies = ref([]);

  const page = ref(1);

  const total = ref(10);

  const limit = ref(10);

  const visibleName = ref(true);

  const visibleDialog = ref(false);

  const vehicles = ref([]);

  const dialogTitle = ref('');

  const vehicleModel = ref({
    vehicleId: '',
    vehicleNumber: '',
    vehicleType: '',
    agency: '',
    state: ''
  });

  const clearVehicleData = () => {
    vehicleModel.value = {
      vehicleId: '',
      vehicleNumber: '',
      vehicleType: '',
      agency: '',
      state: ''
    }
  }

  const travelAgencyModel = ref({
    agencyCode: '',
    agencyName: '',
    maxTourists: '',
    rating: '',
    international: ''
  });

  const clearData = () => {
    travelAgencyModel.value = {
      agencyCode: '',
      agencyName: '',
      maxTourists: '',
      rating: '',
      international: ''
    }
  }

  const cancelAdd = () => {
    clearData();
    visibleDrawer.value = false;
  }

  const cancelAddVehicle = () => {
    clearVehicleData();
    vehicleDrawer.value = false;
  }

  const travelAgencyList = () => {
    let params = {
      agencyName: searchTravelAgencyName.value,
      page: page.value,
      limit: limit.value
    }
    getTravelAgencyByNameList(params).then(res => {
      if (res.code === 200) {
        travelAgencies.value=res.datas;
        total.value=res.total;
      }
    })
  }

  const handleSizeChange = (size) => {
    limit.value = size;
    travelAgencyList();
  }

  const handleCurrentChange = (num) => {
    page.value = num;
    travelAgencyList();
  }

  const doAddTravelAgency = () => {
    // const checkInternational = ref('');
    // checkInternational.value = travelAgencyModel.value.international;
    // travelAgencyModel.value.international = (checkInternational.value === '是' ? 1 : 0);
    if (travelAgencyModel.value.agencyName === '') {
      ElMessage.warning("旅行社名称不能为空!");
      return;
    }else {
      addTravelAgency(travelAgencyModel.value).then(res => {
        if (res.code === 200){
          ElMessage.success("添加成功!")
        }
        clearData();
        visibleDrawer.value = false;
        travelAgencyList();
      })
    }
  }

  const doAddVehicle = () => {
    vehicleModel.value.agency = dialogTitle.value;
    if (vehicleModel.value.vehicleNumber === '') {
      ElMessage.warning("车牌号不能为空!");
      return;
    }else {
      addVehicle(vehicleModel.value).then(res => {

        if (res.code === 200){
          ElMessage.success("添加成功!")
        }
        clearVehicleData();
        vehicleDrawer.value = false;
        vehicleList();
      })
    }
  }

  const handleDelete = (row) => {
    ElMessageBox.confirm(
        '确定删除确定删除旅行社\"' + row.agencyName + '\"吗？',
        '提示',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
        }
    ).then(() => {
      deleteTravelAgency(row.agencyName).then( (res) => {
        if (res.code === 200) {
          travelAgencyList();
          ElMessage({
            type: 'success',
            message: '删除成功',
          })
        }
      });
    }).catch(() => {
      ElMessage({
        type: 'info',
        message: '取消删除',
      })
    })
  }

  const handleDeleteVehicle = (row) => {
    ElMessageBox.confirm(
        '确定删除确定删除该车辆吗？',
        '提示',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
        }
    ).then(() => {
      deleteVehicle(row.vehicleId).then( (res) => {
        if (res.code === 200) {
          vehicleList();
          ElMessage({
            type: 'success',
            message: '删除成功',
          })
        }
      });
    }).catch(() => {
      ElMessage({
        type: 'info',
        message: '取消删除',
      })
    })
  }

  const handleEdit = (row) => {
    // console.log(travelAgencyModel.value);
    visibleDrawer.value = true;
    visibleName.value = false;
    drawerTitle.value = "编辑旅行社信息";
    travelAgencyModel.value = {...row};
    // travelAgencyModel.value.international = (row.international === 1?'是':'否');
  }

  const handleEditVehicle = (row) => {
    vehicleDrawer.value = true;
    vehicleDrawerTitle.value = "编辑车辆信息";
    vehicleModel.value = {...row};
  }

  const doUpdateVehicle = () => {
    updateVehicle(vehicleModel.value).then((res) => {
      if (res.code === 200){
        ElMessage.success("编辑成功")
      }
      clearVehicleData();
      vehicleDrawer.value = false;
      vehicleList();
    })
  }

  const doUpdateTravelAgency = () => {
    updateTravelAgency(travelAgencyModel.value).then((res) => {
      if (res.code === 200){
        ElMessage.success("编辑成功")
      }
      clearData();
      visibleDrawer.value = false;
      travelAgencyList();
    })
  }

  travelAgencyList();

  const showDialog = (row) => {
    visibleDialog.value = true;
    dialogTitle.value = row.agencyName;
    //console.log(dialogTitle)
    vehicleList();
  }

  const vehicleList = () => {
    let param = {
      agency: dialogTitle.value
    };
    console.log(param);
    getVehicleListByAgency(param).then((res) => {
      if (res.code === 200){
        vehicles.value = res.datas;
      }
    })
  }

  const vehicleDrawer = ref(false);

  const vehicleDrawerTitle = ref('');



</script>

<template>

<!--  车辆部分-->

  <el-drawer
      v-model="vehicleDrawer"
      :title="vehicleDrawerTitle"
      size="30%"
      @close="clearVehicleData"
  >
    <el-form :model="vehicleModel" label-width="auto" style="max-width: 600px">
      <el-form-item label="车牌号:" >
        <el-input v-model="vehicleModel.vehicleNumber" placeholder="请输入车牌号" />
      </el-form-item>
      <el-form-item label="车辆类型:">
        <el-select v-model="vehicleModel.vehicleType" placeholder="请选择车辆类型">
          <el-option label="面包车" value="面包车" />
          <el-option label="大巴车" value="大巴车" />
        </el-select>
      </el-form-item>
      <el-form-item label="车辆状态:">
        <el-select v-model="vehicleModel.state" placeholder="请选择车辆状态">
          <el-option label="在运" value="在运" />
          <el-option label="停运" value="停运" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <template #default="{row}">
          <el-button type="primary" @click="vehicleDrawerTitle === '新增车辆'?doAddVehicle():doUpdateVehicle()">提交</el-button>
          <el-button @click="cancelAddVehicle">取消</el-button>
        </template>
      </el-form-item>
    </el-form>
  </el-drawer>

  <el-dialog v-model="visibleDialog" :title="dialogTitle + '车辆信息'">
    <el-table :data="vehicles" class="dialog-table">
      <el-table-column prop="vehicleNumber" label="车牌号" width="200"/>
      <el-table-column prop="vehicleType" label="车辆类型" width="200"/>
      <el-table-column prop="state" label="状态" width="200"/>
      <el-table-column fixed="right" label="操作" min-width="190">
        <template #default="{row}">
          <el-button link type="primary" size="small" @click="handleEditVehicle(row)" class="dialog-button">
            编辑
          </el-button>
          <el-button link type="danger" size="small" @click="handleDeleteVehicle(row)" class="dialog-button">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <template #footer>
      <el-button type="primary" @click="vehicleDrawer=true; vehicleDrawerTitle='新增车辆'">新增车辆</el-button>
      <el-button @click="visibleDialog=false">关闭</el-button>
    </template>
  </el-dialog>

<!--  旅行社部分-->
  <el-card>
    <template #header>
      <div class="card-header">
        <span class="card-header-text">旅行社管理</span>
        <el-button class="card-header-button" @click="visibleDrawer=true; drawerTitle='新增旅行社'; visibleName=true">新增旅行社</el-button>
      </div>
    </template>

    <el-form :inline="true" :model="searchForm" class="demo-form-inline">
      <el-form-item label="旅行社名称:">
        <el-input v-model="searchTravelAgencyName" placeholder="请输入旅行社名称" clearable @clear="travelAgencyList"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="travelAgencyList">搜索</el-button>
      </el-form-item>
    </el-form>

    <el-table :data="travelAgencies" style="width: 100%;height: 100%" class="table-column-text">
      <el-table-column prop="agencyName" label="旅行社名称" width="400" />
      <el-table-column prop="maxTourists" label="最大客容量" width="200" />
      <el-table-column prop="international" label="是否境外" width="200" >
        <template #default="{row}">
          <span>{{ row.international === 0 ? '是' : '否' }}</span>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" min-width="300">
        <template #default="{row}">
          <el-button link type="primary" size="small" @click="handleEdit(row)" class="table-column-button">
            编辑
          </el-button>
          <el-button link type="danger" size="small" @click="handleDelete(row)" class="table-column-button">
            删除
          </el-button>
          <el-button link type="info" size="small" @click="showDialog(row)" class="table-column-button">
            查看车辆信息
          </el-button>
        </template>
      </el-table-column>
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
    <el-form :model="travelAgencyModel" label-width="auto" style="max-width: 600px">
      <el-form-item label="旅行社名称:" >
        <el-input v-model="travelAgencyModel.agencyName" placeholder="请输入旅行社名称" />
      </el-form-item>
      <el-form-item label="最大客容量:">
        <el-input v-model="travelAgencyModel.maxTourists" placeholder="请输入旅行社最大容客量"/>
      </el-form-item>
      <el-form-item label="是否能出境:">
        <el-select v-model="travelAgencyModel.international" placeholder="请选择是否能出境">
          <el-option label="是" value="1" />
          <el-option label="否" value="0" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="drawerTitle==='新增旅行社'?doAddTravelAgency():doUpdateTravelAgency()">提交</el-button>
        <el-button @click="cancelAdd">取消</el-button>
      </el-form-item>
    </el-form>
  </el-drawer>
</template>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.card-header-text{
  font-weight: bold; /* 加粗 */
  font-size: 1.5em; /* 放大，这里使用了相对大小，可以根据需要调整 */
}
.card-header-button{
  padding: 1.5em 3.0em;  //font-size: 12px;
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
.table-column-button{
  font-weight: bold; /* 加粗 */
  font-size: 0.9em; /* 放大，这里使用了相对大小，可以根据需要调整 */
}
.dialog-table{
  font-weight: bold; /* 加粗 */
  font-size: 1em; /* 放大，这里使用了相对大小，可以根据需要调整 */
}
.dialog-button{
  font-weight: bold; /* 加粗 */
  font-size: 1em; /* 放大，这里使用了相对大小，可以根据需要调整 */
}
</style>
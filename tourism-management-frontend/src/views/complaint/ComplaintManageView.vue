<script setup>
  import {ref} from "vue";
  import {deleteComplaint, deleteTravelAgency, getComplaintListByType, getScenicAreaList} from "@/api/getData.js";
  import {ElMessage, ElMessageBox} from "element-plus";


  const searchForm = ref({});

  const searchComplaintTargetName = ref('');

  const searchComplaintTargetType = ref('');

  const page = ref(1);

  const total = ref(10);

  const limit = ref(10);

  const complaints = ref([]);

  const dialogTitle = ref('');

  const complaintList = () => {
    let params = {
      targetName: searchComplaintTargetName.value,
      targetType: searchComplaintTargetType.value,
      page: page.value,
      limit: limit.value
    }
    getComplaintListByType(params).then((res)=>{
      if (res.code === 200) {
        complaints.value=res.datas;
        total.value=res.total;
      }
    })
  }

  const handleSizeChange = (size) => {
    limit.value = size;
    complaintList();
  }

  const handleCurrentChange = (num) => {
    page.value = num;
    complaintList();
  }

  const showContent = (row) => {
    let text = row.content;
    ElMessageBox.alert(text, '用户\"' + row.username + '\"对\"' + row.targetName + '\"的投诉内容:', {
      confirmButtonText: '关闭',
    })
  }

  const handleDelete = (row) =>{
    ElMessageBox.confirm(
        '确定删除该条投诉吗？',
        '提示',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
        }
    ).then(() => {
      deleteComplaint(row.complaintId).then( (res) => {
        if (res.code === 200) {
          complaintList();
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

  complaintList();
</script>

<template>
  <el-card>
    <template #header>
      <div class="card-header">
        <span class="card-header-text">投诉信箱</span>
      </div>
    </template>

    <el-form :inline="true" :model="searchForm" class="demo-form-inline">
      <el-form-item label="投诉对象:">
        <el-input v-model="searchComplaintTargetName" placeholder="请输入投诉对象" clearable @clear="complaintList"/>
      </el-form-item>
      <el-form-item>
        <el-select v-model="searchComplaintTargetType" placeholder="请选择投诉对象类型" style="width: 200px" @change="complaintList">
          <el-option label="景区" value="景区" />
          <el-option label="酒店" value="酒店" />
          <el-option label="无" value="" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="complaintList">搜索</el-button>
      </el-form-item>
    </el-form>

    <el-table :data="complaints" style="width: 100%;height: 100%" class="table-column-text">
      <el-table-column prop="username" label="投诉人" width="450"/>
      <el-table-column prop="targetType" label="投诉对象类型" width="450"/>
      <el-table-column prop="targetName" label="投诉对象" width="450"/>
      <el-table-column fixed="right" label="操作" min-width="200">
        <template #default="{row}">
          <el-button link type="primary" size="small" @click="showContent(row)" class="table-column-button">
            查看内容
          </el-button>
          <el-button link type="danger" size="small" @click="handleDelete(row)" class="table-column-button">
            删除
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
.table-column-text{
  font-weight: bold; /* 加粗 */
  font-size: 1.2em; /* 放大，这里使用了相对大小，可以根据需要调整 */
}
.table-column-button{
  font-weight: bold; /* 加粗 */
  font-size: 0.9em; /* 放大，这里使用了相对大小，可以根据需要调整 */
}
</style>
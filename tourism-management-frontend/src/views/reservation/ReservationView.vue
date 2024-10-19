<template>
  <el-card>
    <!-- 搜索框 -->
    <template #header>
      <div class="card-header">
        <span class="title">旅客预约管理</span>
      </div>
    </template>

    <!-- 搜索表单-->
    <el-form :inline="true" :model="searchForm">
      <el-form-item  class="custom-form-item">
        <el-input v-model="searchReservationName" size="large" placeholder="请输入旅客姓名" clearable />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" size="large" @click="reservationList">搜索</el-button>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" size="large" @click="reservationList_1">显示全部</el-button>
      </el-form-item>


    </el-form>

    <!-- 搜索信息-->
    <el-table :data="Reservation" style="width: 100%" class="form">
      <el-table-column fixed prop="username" label="名字" width="80" />
      <el-table-column prop="phone" label="手机号码" width="200" />
      <el-table-column prop="scenicarea" label="旅游地点" min-width="100" />
      <el-table-column prop="hotel" label="酒店" min-width="120" />
      <el-table-column prop="travelagency" label="旅行社" min-width="100" />
      <el-table-column prop="guide" label="导游" min-width="100" />
      <el-table-column prop="parking" label="停车" min-width="100">
        <template #default="{ row }">
          {{ row.parking === 1 ? '已停车' : '未停车' }}
        </template>
      </el-table-column>
      <el-table-column  prop="date" label="日期" width="160" />
      <el-table-column fixed="right" label="操作" min-width="120">
        <template #default="{ row }">
          <el-button link type="primary" size="small" @click="Yes(row)" class="button_1">
            批准
          </el-button>
          <el-button link type="danger" size="small" @click="No(row)" class="button_2">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
        v-model:current-page="page"
        v-model:page-size="limit"
        :page-sizes="[20, 50, 100, 200]"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        style="margin-top: 20px; justify-content: flex-end"
    />
  </el-card>
</template>

<script setup>
import { ref } from "vue";
import {
  addVisitorInfo,
  deleteReservation,
  getReservationByNameList,
} from "@/api/getData.js";
import { ElMessage, ElMessageBox } from "element-plus";

const searchForm = ref({});
const searchReservationName = ref("");
const Reservation = ref([]);
const page = ref(1);
const total = ref(20);
const limit = ref(10);
function formatDate(dateString) {
  // 创建一个日期对象
  const date = new Date(dateString);

  // 获取年、月、日
  const year = date.getFullYear();
  const month = ('0' + (date.getMonth() + 1)).slice(-2); // 月份是从0开始计算的，需要加1，并补足两位
  const day = ('0' + date.getDate()).slice(-2); // 获取日期，并补足两位

  // 拼接成 yyyy-MM-dd 的格式
  const formattedDate = `${year}-${month}-${day}`;

  return formattedDate;
}
function convertToISO(dateString) {
  // 创建一个新的日期对象（假设时间为当天的 00:00:00）
  const date = new Date(`${dateString}T00:00:00.000Z`);

  // 调整时区（如果需要将日期调整回指定时间，如 16:00:00）
  date.setUTCHours(date.getUTCHours() - 8); // 假设时区偏移量为 -8 小时

  // 转换为 ISO 8601 格式的字符串
  const isoDateString = date.toISOString();

  return isoDateString;
}

// 示例用法


/**
 * 查询列表中的旅客信息
 */
const reservationList = () => {
  let params = {
    username: searchReservationName.value,
    page: page.value,
    limit: limit.value,
  };
  getReservationByNameList(params).then((res) => {
    if (res.code == 200) {

      res.datas.forEach(item => {
        item.date = formatDate(item.date);
      });


      Reservation.value = res.datas;
      total.value = res.total;
    }
  });
};
const reservationList_1 = () => {
  let params = {
    username: '',
    page: page.value,
    limit: limit.value,
  };
  getReservationByNameList(params).then((res) => {
    if (res.code == 200) {

      res.datas.forEach(item => {
        item.date = formatDate(item.date);
      });


      Reservation.value = res.datas;
      total.value = res.total;
    }
  });
};





/**
 * 批准和否定按钮
 * 批准 将数据库中的批准选项设置为1
 * 删除 将游客预约从库里删除
 */
const Yes = (row) => {
  addVisitorInfo(row).then((res) => {
    if (res.code == 200) {
      ElMessage.success("批准成功");
      let params = {
        username: row.username,
        date:convertToISO(row.date),
        scenicarea: row.scenicarea
      };
      deleteReservation(params).then(() => {
          reservationList();
      })

    }

  });


};

const No = (row) => {
  ElMessageBox.confirm("您确定删除这条数据么", "提示", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning",
  })
      .then(() => {
        let params = {
          username: row.username,
          date:convertToISO(row.date),
          scenicarea: row.scenicarea
        };
        // console.log(params.date)//2024-07-17
        console.log(params)
        deleteReservation(params).then((res) => {
          if (res.code == 200) {
            reservationList();
            ElMessage({
              type: "success",
              message: "删除成功",
            });
          }
        });
      })
      .catch(() => {
        ElMessage({
          type: "info",
          message: "取消删除",
        });
      });
};

const handleSizeChange = (size) => {
  limit.value = size;
  reservationList();
};

const handleCurrentChange = (num) => {
  page.value = num;
  reservationList();
};
reservationList()
</script>

<style scoped>
.title {
  font-size: 30px; /* 设置标题字体大小为20px */
  font-weight: bold;
}
.form{
  font-weight: bold;
  font-size: 16px; /* 设置标题字体大小为20px */
}
.button_1 {
  font-weight: bold; /* 加粗 */
  font-size: 0.9em; /* 放大，这里使用了相对大小，可以根据需要调整 */
}
.button_2{
  font-weight: bold; /* 加粗 */
  font-size: 0.9em; /* 放大，这里使用了相对大小，可以根据需要调整 */

}




</style>

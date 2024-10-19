<template>
  <el-card >
    <template #header>
      <div class="card-header">
        <span>告警信息管理</span>
        <el-button class="add-btn" type="primary" @click="visibleDrawer=true; ; drawerTitle='新增告警信息';clearForm()" style="font-size: 0.9em">新增告警信息</el-button>
      </div>
    </template>

    <el-container>
      <el-header>
        <!--  搜索表单-->
        <el-form :inline="true" :model="searchForm" >
          <el-form-item >
            <el-input v-model="searchInfo" placeholder="请输入查询信息" clearable @clear="infoList(0)"/>
          </el-form-item>
          <el-form-item >
            <el-button type="primary" @click="infoList(0)">搜索</el-button>
            <el-button class="publish-btn" type="primary" style="font-size: 1.0em;font-weight: bolder" @click="publish">
              发布
            </el-button>
          </el-form-item>
        </el-form>
      </el-header>
      <el-container style="border: 1px solid #eee">
        <el-aside width="150px" >
          <el-menu >
            <el-menu-item style="display: flex; justify-content: center; align-items: center; background-color: rgba(241,240,240,0.93)" @click="allInfo">
              <span slot="title" style="font-weight: bolder">全部</span>
            </el-menu-item>
            <el-menu-item style="display: flex; justify-content: center; align-items: center; background-color: rgb(241,240,240,0.93);margin-top: 1px" @click="publishedInfo(2)">
              <span slot="title" style="font-weight: bolder">已发布</span>
            </el-menu-item>
            <el-menu-item style="display: flex; justify-content: center; align-items: center; background-color: rgb(241,240,240,0.93);margin-top: 1px" @click="publishedInfo(1)">
              <span slot="title" style="font-weight: bolder">未发布</span>
            </el-menu-item>
          </el-menu>
        </el-aside>
        <el-main>
          <el-table :data="info" style="width: 100% ;font-size: 1.2em;font-weight: bolder" @selection-change="publishChanged">
            <el-table-column type="selection" width="55"/>
            <el-table-column  prop="info" label="信息内容" width="400" />
            <el-table-column prop="timeStamp" label="修改时间" width="130" />
            <el-table-column prop="published" label="状态" width="130" >
              <template #default="{ row }">
                  <span v-if="row.published === 1" style="color: #a92020">
                    未发布
                  </span>
                <span v-if="row.published === 2" style="color: #27ad2e">
                    已发布
                  </span>
              </template>
            </el-table-column>
            <el-table-column fixed="right" label="操作" min-width="130">
              <template #default="{row}">
                <el-button link type="primary" style="font-size: 0.9em;font-weight: bolder" @click="handleEdit(row)">
                  编辑
                </el-button>
              </template>
            </el-table-column>
          </el-table>

        <!--  分页-->
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
        </el-main>
      </el-container>
    </el-container>
    <!--  抽屉-->
    <el-drawer
        v-model="visibleDrawer"
        :title="drawerTitle"
        :direction="'rtl'"
        size="35%"
    >
      <el-form
          :model="infoModel"
          label-width="auto"
          style="max-width: 600px"
      >
        <el-form-item label="信息内容">
          <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 10 }" v-model="infoModel.info" placeholder="请输入告警信息" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="drawerTitle === '新增告警信息' ? doAddInfo() : doUpdateInfo()" class="submit-btn">提交</el-button>
        </el-form-item>
      </el-form>
    </el-drawer>
  </el-card>
</template>

<script setup>

import {ref} from "vue";
import {
  addInfo,
  getInfoById,
  updateInfo, updatePublishStatus
} from "@/api/getData.js";
import {ElMessage} from "element-plus";

const searchForm = ref({})
const searchInfo = ref('')
const page = ref(1)
const limit = ref(20)
const total = ref(0)
let info = ref([])
const visibleDrawer = ref(false)
const drawerTitle = ref()

const allInfo = () => {
  infoList(0)
}

const publishedInfo = (published) => {
  infoList(published)
}

const ids = ref([])
const publishChanged = (selection) => {
  ids.value = selection.map(item => item.infoId)
}

const publish = () => {
  let params = {
    ids: ids.value
  }
  updatePublishStatus(params).then((res) => {
    if (res.code === 200) {
      ElMessage.success('发布成功')
    }
    infoList()
  })
}

const infoList = (published) => {
  let params = {
    info: searchInfo.value,
    page: page.value,
    limit: limit.value,
    published : published || 0
  }
  getInfoById(params).then((res) => {
    if (res.code === 200) {
      info.value = res.datas
      total.value = res.total
    }
  })
}

infoList()

const handleSizeChange = (size) => {
  limit.value = size
  infoList()
}
const handleCurrentChange = (currentPage) => {
  page.value = currentPage
  infoList()
}

const infoModel = ref({
  info: '',
  timeStamp: '',
  published: ''
})

const clearForm = () => {
  infoModel.value = {
    info: '',
    timeStamp: '',
    published: ''
  }
}

const doAddInfo = () => {
  if(infoModel.value.info === ''){
    ElMessage.warning('告警信息不能为空')
  }else{
    addInfo(infoModel.value).then((res) => {
      if (res.code === 200) {
        ElMessage.success('添加成功')
      }
      clearForm()
      visibleDrawer.value = false
      infoList()
    })
  }
}

const doUpdateInfo = () => {
  updateInfo(infoModel.value).then((res) => {
    if (res.code === 200) {
      ElMessage.success('修改成功')
    }
    clearForm()
    visibleDrawer.value = false
    infoList()
  })
}

const handleEdit = (row) => {
  visibleDrawer.value = true
  drawerTitle.value = '编辑告警信息'
  infoModel.value = {...row}
}

</script>

<style scoped>
.card-header{
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 20px;
  font-weight: bold;
  margin-left: 25px;
}
.add-btn{
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
.add-btn:hover{
  background-color: #3dc1e1;
  box-shadow: 0px 15px 20px rgba(0, 240, 255, 0.4);
  color: #fff;
  transform: translateY(-7px);
}
.add-btn:active{
  transform: translateY(-1px);
}
.submit-btn{
  margin-left: auto;
  margin-right: auto;
  width: 150px;
  margin-top: 20px;
  letter-spacing: 10px;
}
.publish-btn{
  background-color: #3dc1e1;
  position: absolute;
  left: 100px;
}
.publish-btn:hover{
  background-color: #13a13d;
}

</style>
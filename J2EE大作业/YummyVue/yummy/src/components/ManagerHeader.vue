<template>
  <div class="layout">
    <Layout>
      <Header style="background-color:white;margin-bottom: 15px;height: 74px">
        <Menu mode="horizontal" theme="light" style="height: 70px" >
          <div class="layout-logo"></div>
          <div class="layout-nav">
            <router-link style="height: 70px" :to="{ name: 'approveApplication'}">
              <MenuItem name="1">
                <Icon type="ios-navigate"></Icon>
                申请审批
              </MenuItem>
            </router-link>
            <router-link style="height: 70px" :to="{ name: 'managerStatistics'}">
              <MenuItem name="2">
                <Icon type="ios-keypad"></Icon>
                统计信息
              </MenuItem>
            </router-link>
          </div>
        </Menu>
      </Header>
    </Layout>
    <p>
      <router-link to="/" style="float: right;margin-right: 30px"> 注销</router-link>
    </p>
  </div>
</template>

<script>
    export default {
      name: "ManagerHeader",
      data(){
        return{

        }
      }
    }
</script>

<style scoped>
  .layout{
    border: 1px solid #d7dde4;
    /*background: #f5f7f9;*/
    background: #f5f7f9;
    position: relative;
    border-radius: 4px;
    overflow: hidden;
  }
  .layout-logo{
    width: 100px;
    height: 30px;
    /*background: #5b6270;*/
    background: #5b6270;
    border-radius: 3px;
    float: left;
    position: relative;
    top: 15px;
    left: 20px;
  }
  .layout-nav{
    /*background-color: green;*/
    width: 420px;
    height: 70px;
    margin-left:  auto;
    margin-right: 20px;
    margin-top: 5px;
  }
</style>

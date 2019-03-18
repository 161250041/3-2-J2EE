<template>
  <div class="layout">
    <Layout>
      <Header style="background-color:white;margin-bottom: 15px;height: 74px">
        <Menu mode="horizontal" theme="light" style="height: 70px" >
          <div class="layout-logo"></div>
          <div class="layout-nav">
            <router-link style="height: 70px" :to="{ name: 'restaurantList', params: { email: this.email }}">
              <MenuItem name="1">
                <Icon type="ios-navigate"></Icon>
                点餐
              </MenuItem>
            </router-link>
            <router-link style="height: 70px" :to="{ name: 'userOrder', params: { email: this.email }}">
              <MenuItem name="2">
                <Icon type="ios-keypad"></Icon>
                我的订单
              </MenuItem>
            </router-link>
            <router-link style="height: 70px" :to="{ name: 'userInfo', params: { email: this.email }}">
              <MenuItem name="3">
                <Icon type="ios-analytics"></Icon>
                个人资料
              </MenuItem>
            </router-link>
            <router-link style="height: 70px" :to="{ name: 'userStatistics', params: { email: this.email }}">
              <MenuItem name="4">
                <Icon type="ios-analytics"></Icon>
                统计数据
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
      name: "MyHeader",
      data(){
        return{
          email: this.$route.params.email
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
    width: 500px;
    height: 70px;
    margin-left:  auto;
    margin-right: 20px;
    margin-top: 5px;
  }
</style>

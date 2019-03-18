<template>
  <div>
    <RestaurantHeader></RestaurantHeader>
    <br/><br/>
    <b>商家id: {{info.id}}</b>
    <br/>
    <b>商家名称：</b><Input v-model="info.name" placeholder="输入名称" style="width: 200px" />
    <br/>
    <b>类型：</b>
    <Select v-model="info.type" style="width:200px">
      <Option v-for="item in this.restaurantTypeList" :value="item" :key="item">{{ item }}</Option>
    </Select>
    <br/>
    <b>地址：</b><Input v-model="info.address" placeholder="输入地址" style="width: 200px" />
    <br/>
    <b>联系方式：</b><Input v-model="info.phoneNumber" placeholder="输入联系方式" style="width: 200px" />
    <br/><br/>
    <Button type="primary" style="margin-top:70px;margin-left: 600px" size="medium" @click="save()">保存</Button>
  </div>
</template>

<script>
    import RestaurantHeader from "./RestaurantHeader";
    export default {
        name: "RestaurantInfo",
      components: {RestaurantHeader},
      data() {
        return {
          code:this.$route.params.code,
          info:{
            id:"",
            name: "",
            type: "",
            address: "",
            phoneNumber: "",
          },
          restaurantTypeList: ["全部商家", "美食", "快餐便当", "特色菜系", "异国料理", "小吃夜宵", "甜品饮品", "果蔬生鲜", "商店超市", "鲜花绿植", "医药健康", "早餐", "午餐", "下午茶", "晚餐", "夜宵"],
        };
      },
      methods:{
        getRestaurantInfo() {
          this.$axios({
            url: 'http://localhost:8081/restaurantInfo/getRestaurantInfo',//请求的地址
            method: 'post',//请求的方式
            data: {code: this.code}
          }).then(res => {
            // alert(res.data);
            console.info('后台返回的数据', res.data);
            this.info = res.data;
          }).catch(err => {
            // alert(err.response.message);
            console.info('报错的信息', err.response.message);
          });
        },
        save(){
          this.$axios({
            url: 'http://localhost:8081/restaurantInfo/modifyRestaurantInfo',//请求的地址
            method: 'post',//请求的方式
            data: this.info
          }).then(res => {
            // alert(res.data);
            console.info('后台返回的数据', res.data);
            if (res.data === true) {
              alert("保存成功")
            }
          }).catch(err => {
            // alert(err.response.message);
            console.info('报错的信息', err.response.message);
          });
        }
      },
      mounted() {
        this.getRestaurantInfo();
      }
    }
</script>

<style scoped>

</style>

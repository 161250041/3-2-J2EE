<template>
  <div>
    <MyHeader></MyHeader>
    <br/><br/>
    <b>姓名：</b><Input v-model="name" placeholder="输入您的姓名" style="width: 200px" />
    <br/>
    <b>电话：</b><Input v-model="phoneNumber" placeholder="输入您的电话" style="width: 200px" />
    <br/><br/>
    <b>累计消费金额：</b><b>{{consumption}}</b>
    <br/><br/>
    <b>会员等级：</b><b>{{level}}</b>
    <br/><br/>

    <b style="float:left;margin-left: 37.5%">地址：</b>
    <Card style="width: 400px;margin-left:40%">
      <!--{{addressList}}-->
      <div  v-for="(item,index) in addressList"  :key="addressList.addressId">
        <b v-if="item!==null">{{item.address}}</b>
        <Button type="error" style="margin-left: 2px" size="small" @click="deleteAddress(index)">删除</Button>
      </div>
      <Button type="primary" style="margin-top:10px;margin-left: 250px" size="small" @click="modal1=true">+ 增加地址</Button>
    </Card>

    <Button type="primary" style="margin-top:70px;margin-left: 600px" size="medium" @click="save()">保存</Button>
    <Modal
      v-model="modal1"
      okText="确定"
      cancelText="取消"
      @on-ok="addAddress()">
      <Form ref="address">
        <FormItem  v-model="address">
          <b>请输入地址：</b>
          <Input type="text" prefix="ios-contact" placeholder="请输入地址" style="width: 250px"
                 v-model="address"/>
        </FormItem>
      </Form>
    </Modal>
  </div>

</template>

<script>
    import MyHeader from "./MyHeader";
    export default {
      name: "UserInfo",
      components: {MyHeader},
      data(){
        return{
          email: this.$route.params.email,
          name:"",
          level:"普通会员",
          phoneNumber: "",
          consumption: 0,
          addressList:[],
          modal1: false,
          address:""
        }
      },
      methods:{
        getAddressList() {
          this.$axios({
            url: 'http://localhost:8081/restaurant/addressList',//请求的地址
            method: 'post',//请求的方式
            data: {email: this.email}
          }).then(res => {
            // alert(res.data);
            console.info('后台返回的数据', res.data);
            this.addressList = res.data
            // alert(this.commodityType);
          }).catch(err => {
            // alert(err.response.message);
            console.info('报错的信息', err.response.message);
          });
        },
        getUserInfo() {
          this.$axios({
            url: 'http://localhost:8081/userInfo/getUserInfo',//请求的地址
            method: 'post',//请求的方式
            data: {email: this.email}
          }).then(res => {
            // alert(res.data);
            console.info('后台返回的数据', res.data);
            this.name = res.data.name;
            this.phoneNumber = res.data.phoneNumber;
            this.consumption = res.data.consumption;
            if (this.consumption > 1000) {
              this.level="金会员"
            }else if (this.consumption > 500) {
              this.level="银会员"
            }
            // alert(this.commodityType);
          }).catch(err => {
            // alert(err.response.message);
            console.info('报错的信息', err.response.message);
          });
        },
        addAddress() {
          if(this.addressList.length>0){
            this.addressList.push(
              {addressId: this.addressList[this.addressList.length-1].addressId+1, address: this.address});
          }else {
            this.addressList.push(
              {addressId: 1, address: this.address});
          }
          this.address = "";
        },
        deleteAddress(index){
          // alert(index);
          this.addressList.splice(index,1);
        },
        save(){
          // alert(this.addressList)
          this.$axios({
            url: 'http://localhost:8081/userInfo/modifyUserInfo',//请求的地址
            method: 'post',//请求的方式
            data: {
              email: this.email,
              name:this.name,
              phoneNumber:this.phoneNumber,
              addresses: this.addressList
            }
          }).then(res => {
            // alert(res.data);
            console.info('后台返回的数据', res.data);
            // alert(this.commodityType);
          }).catch(err => {
            // alert(err.response.message);
            console.info('报错的信息', err.response.message);
          });
        }
      },
      mounted() {
        this.getUserInfo();
        this.getAddressList();
      }
    }
</script>

<style scoped>

</style>

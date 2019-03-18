<template>
  <div>
    <RestaurantHeader></RestaurantHeader>
    <Collapse simple>
      <Panel name="1">
        <b style="float: left;margin-left: 50px">未付款订单</b>
        <div slot="content">
          <Table border :columns="columns" :data="list0">
            <template slot-scope="{ row, index }" slot="orderId">
              <strong>{{ row.orderId }}</strong>
            </template>
            <template slot-scope="{ row, index }" slot="action">
              <Button type="primary" size="small" style="margin-right: 5px" @click="wantToPay(index)">付款</Button>
              <Button type="error" size="small" @click="notPay(index)">取消</Button>
            </template>
          </Table>
        </div>
      </Panel>
      <Panel name="2">
        <b style="float: left;margin-left: 50px">配送中订单</b>
        <div slot="content">
          <Table border :columns="columns" :data="list1">
            <template slot-scope="{ row, index }" slot="orderId" >
              <strong>{{ row.orderId }}</strong>
            </template>

            <template slot-scope="{ row, index }" slot="action">
              <Button type="primary" size="small" style="margin-right: 5px" @click="confirm(index)">收货</Button>
              <Button type="error" size="small" @click="cancel(index)">退款</Button>
            </template>
          </Table>
        </div>
      </Panel>
      <Panel name="3">
        <b style="float: left;margin-left: 50px">已完成订单</b>
        <div slot="content">
          <Table border :columns="columns" :data="list2">
            <template slot-scope="{ row,index }" slot="orderId">
              <strong>{{ row.orderId }}</strong>
            </template>
          </Table>
        </div>
      </Panel>
      <Panel name="4">
        <b style="float: left;margin-left: 50px">已退款订单</b>
        <div slot="content">
          <Table border :columns="columns" :data="list4">
            <template slot-scope="{ row,index }" slot="orderId">
              <strong>{{ row.orderId }}</strong>
            </template>
          </Table>
        </div>
      </Panel>
      <Panel name="5">
        <b style="float: left;margin-left: 50px">已失效订单</b>
        <div slot="content">
          <Table border :columns="columns" :data="list3">
            <template slot-scope="{ row,index }" slot="orderId">
              <strong>{{ row.orderId }}</strong>
            </template>
          </Table>
        </div>
      </Panel>
    </Collapse>
  </div>
</template>

<script>
  import RestaurantHeader from "./RestaurantHeader";
  export default {
    name: "RestaurantOrder",
    data() {
      return {
        code: this.$route.params.code,
        orderId: "",
        password: "",
        list: [],//0待支付 1已支付在配送 2配送送达（成功） 3过期未支付 4已退款
        list0: [],
        list1: [],
        list2: [],
        list3: [],
        list4: [],
        modal1: false,
        modal2: false,
        actualPrice: 0,
        refund: 0,
        columns: [
          {
            title: '订单编号',
            key: 'orderId'
          },
          {
            title: '商品',
            key: 'commodities'
          },
          {
            title: '价格',
            key: 'price'
          },
          {
            title: '折后价',
            key: 'actualPrice'
          },
          {
            title: '送货地址',
            key: 'address'
          },
          {
            title: '用户',
            key: 'user'
          },
          {
            title: '退款',
            key: 'refund'
          },
          {
            title: '下单时间',
            key:'beginTime'
          },
          {
            title: '收货时间',
            key: 'endTime'
          }
        ],
      };
    },
    components: {RestaurantHeader},
    methods:{
      getOrder(){
        this.$axios({
          url: 'http://localhost:8081/restaurantOrder/getRestaurantOrder',//请求的地址
          method: 'post',//请求的方式
          data: {code: this.code}//请求的表单数据
        }).then(res => {
          console.info('后台返回的数据', res.data);
          this.list = res.data;
          for (var i = 0; i < this.list.length; i++) {
            if(this.list[i].state===0){
              this.list0.push(this.list[i]);
            }else if(this.list[i].state===1){
              this.list1.push(this.list[i]);
            }else if(this.list[i].state===2){
              this.list2.push(this.list[i]);
            }else if(this.list[i].state===3){
              this.list3.push(this.list[i]);
            }else if(this.list[i].state===4){
              this.list4.push(this.list[i]);
            }
          }
        }).catch(err => {
          console.info('报错的信息', err.response.message);
        });
      },
      wantToPay(index) {
        this.modal1 = true;
        this.actualPrice = this.list0[index].actualPrice;
        this.orderId = this.list0[index].orderId;
      }
    },
    mounted() {
      this.getOrder();
    }
  }
</script>

<style scoped>

</style>

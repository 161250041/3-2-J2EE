<template>
  <div>
    <MyHeader></MyHeader>
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
    <Modal
      v-model="modal1"
      title="订单"
      okText="支付"
      cancelText="取消"
      @on-ok="pay()">
      <Form ref="password">
        <FormItem  v-model="password">
          <p>需要支付{{getPrice(actualPrice)}}元</p>
          <b>请输入支付密码：</b>
          <Input type="text" prefix="ios-contact" placeholder="请输入支付密码" style="width: 250px"
                 v-model="password"/>
        </FormItem>
      </Form>
    </Modal>
    <Modal
      v-model="modal2"
      title="确认"
      okText="取消订单"
      cancelText="再想想"
      @on-ok="remove()">
      <b>确认取消吗？</b>
    </Modal>
  </div>
</template>

<script>
    import MyHeader from "./MyHeader";
    export default {
      name: "UserOrder",
      data() {
        return {
          email: this.$route.params.email,
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
              slot: 'orderId'
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
              key: 'actualPrice',
            },
            {
              title: '送货地址',
              key: 'address'
            },
            {
              title: '商家',
              key: 'restaurantName'
            },
            {
              title: '退款',
              key: 'refund',
            },
            {
              title: '操作',
              slot: 'action',
              width: 150,
              align: 'center'
            }
          ],
        };
      },
      components: {MyHeader},
      methods:{
        getOrder(){
          this.$axios({
            url: 'http://localhost:8081/userOrder/getUserOrder',//请求的地址
            method: 'post',//请求的方式
            data: {email: this.email}//请求的表单数据
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
        },
        pay() {
          //密码是否正确
          //正确则 订单状态设为1
          //不正确 订单状态设为0
          this.$axios({
            url: 'http://localhost:8081/takeOrder/pay',//请求的地址
            method: 'post',//请求的方式
            data:{
              orderId: this.orderId,
              email: this.email,
              password: this.password,
            }
          }).then(res => {
            // alert(res.data);
            console.info('后台返回的数据', res.data);
            var result = res.data;
            if (result === 0) {
              // location.reload();
              alert('密码错误');
            }else if (result === 1) {
              alert("支付成功")
              // this.$Message.success('支付成功');
            }else{
              // location.reload();
              alert('余额不足');
            }
            location.reload();

          }).catch(err => {
            // alert(err.response.message);
            console.info('报错的信息', err.response.message);
          });
        },
        notPay(index){
          this.$axios({
            url: 'http://localhost:8081/takeOrder/notPay',//请求的地址
            method: 'post',//请求的方式
            data:{
              orderId: this.list0[index].orderId
            }
          }).then(res => {
            // alert(res.data);
            console.info('后台返回的数据', res.data);
            location.reload();

          }).catch(err => {
            // alert(err.response.message);
            console.info('报错的信息', err.response.message);
          });
        },
        getPrice(price) {
          return parseFloat(price.toFixed(2));
        },
        cancel(index) {
          this.modal2 = true;
          this.orderId = this.list1[index].orderId;
        },
        confirm(index){
          this.$axios({
            url: 'http://localhost:8081/takeOrder/confirm',//请求的地址
            method: 'post',//请求的方式
            data:{
              orderId: this.list1[index].orderId
            }
          }).then(res => {
            // alert(res.data);
            console.info('后台返回的数据', res.data);
            location.reload();

          }).catch(err => {
            // alert(err.response.message);
            console.info('报错的信息', err.response.message);
          });
        },
        remove() {
          this.$axios({
            url: 'http://localhost:8081/takeOrder/remove',//请求的地址
            method: 'post',//请求的方式
            data:{
              orderId: this.orderId,
            }
          }).then(res => {
            // alert(res.data);
            console.info('后台返回的数据', res.data);
            this.refund = res.data;
            alert("成功退回" + this.refund + "元");
            location.reload();
          }).catch(err => {
            // alert(err.response.message);
            alert("退款失败");
            console.info('报错的信息', err.response.message);
          });
        },
      },
      mounted() {
        this.getOrder();
      }
    }
</script>

<style scoped>

</style>

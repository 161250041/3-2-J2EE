<template>
  <div>
    <RestaurantHeader></RestaurantHeader>
    <Content style="padding: 0 50px">
      <Card :style="{marginBottom: '10px'}">

        <p style="float: left">商品列表：</p>
        <br/>
        <Table border :columns="commodityColumns" :data="commodityList">
        </Table>
        <Button type="primary" style="margin-top:10px;margin-left: 250px" size="small" @click="modal1=true">+ 添加商品</Button>

        <br/>
        <p style="float: left">套餐列表：</p>
        <br/>
        <Table border :columns="setColumns" :data="setList">
        </Table>
        <Button type="primary" style="margin-top:10px;margin-left: 250px" size="small" @click="modal2=true">+ 添加套餐</Button>

      </Card>
      <Card>

      </Card>
    </Content>
    <Footer class="layout-footer-center">2019-2020 &copy; Yummy</Footer>
    <Modal
      v-model="modal1"
      okText="确定"
      cancelText="取消"
      @on-ok="addCommodity()">
      <!--{{formInline}}-->
      <Form ref="address" v-model="formInline">
        <FormItem  v-model="formInline.name">
          <b>请输入商品名称：</b>
          <Input type="text" prefix="ios-contact" style="width: 250px"
                 v-model="formInline.name"/>
        </FormItem>
        <FormItem  v-model="formInline.price">
        <b>请输入商品单价：</b>
        <Input type="text" prefix="ios-contact" style="width: 250px"
               v-model="formInline.price"/>
        </FormItem>
        <FormItem  v-model="formInline.type">
          <b>请输入商品类型：</b>
          <Input type="text" prefix="ios-contact" style="width: 250px"
                 v-model="formInline.type"/>
        </FormItem>
        <FormItem  v-model="formInline.num">
          <b>请输入商品数量：</b>
          <InputNumber  v-model="formInline.num"  :min="1" size="small" :style="{width:'50px'}"></InputNumber>

        </FormItem>
        <FormItem>
          <b>请选择商品有效时间：</b>
          <DatePicker v-model="formInline.beginTime" type="datetime" format="yyyy-MM-dd HH:mm" placeholder="请选择时间" style="width: 200px"></DatePicker>
          <DatePicker v-model="formInline.endTime" type="datetime" format="yyyy-MM-dd HH:mm" placeholder="请选择时间" style="width: 200px"></DatePicker>
        </FormItem>
      </Form>
    </Modal>

    <Modal
      v-model="modal2"
      okText="确定"
      cancelText="取消"
      @on-ok="addSet()">
      <!--{{formInline}}-->
      <Form ref="address" v-model="formInline">
        <FormItem  v-model="formInline.name">
          <b>请输入套餐名称：</b>
          <Input type="text" prefix="ios-contact" style="width: 250px"
                 v-model="formInline.name"/>
        </FormItem>
        <FormItem  v-model="formInline.price">
          <b>请输入套餐单价：</b>
          <Input type="text" prefix="ios-contact" style="width: 250px"
                 v-model="formInline.price"/>
        </FormItem>
        <FormItem  v-model="formInline.num">
          <b>请输入套餐数量：</b>
          <InputNumber  v-model="formInline.num"  :min="1" size="small" :style="{width:'50px'}"></InputNumber>

        </FormItem>
        <FormItem>
          <b>请选择套餐有效时间：</b>
          <DatePicker v-model="formInline.beginTime" type="datetime" format="yyyy-MM-dd HH:mm" placeholder="请选择时间" style="width: 200px"></DatePicker>
          <DatePicker v-model="formInline.endTime" type="datetime" format="yyyy-MM-dd HH:mm" placeholder="请选择时间" style="width: 200px"></DatePicker>
        </FormItem>
      </Form>
    </Modal>

  </div>

</template>

<script>
    import RestaurantHeader from "./RestaurantHeader";
    export default {
        name: "RestaurantManager",
      components: {RestaurantHeader},
      data(){
        return {
          code: this.$route.params.code,
          commodityList: [],
          setList: [],
          modal1: false,
          modal2: false,
          formInline: {
            code: this.$route.params.code,
            name: '',
            price: '',
            num: 0,
            beginTime: '',
            endTime: '',
            type: '',
          },

          commodityColumns: [
            {
              title: "商品名称",
              key: "commodityName"
            },
            {
              title: "商品单价",
              key: "commodityPrice"
            },
            {
              title: "商品类型",
              key: "type"
            },
            {
              title: "商品库存数量",
              key: "commodityNum"
            },
            {
              title: "上架时间",
              key: "beginTime"
            },
            {
              title: "下架时间",
              key: "endTime"
            }
          ],
          setColumns: [
            {
              title: "套餐名称",
              key: "commodityName"
            },
            {
              title: "套餐单价",
              key: "commodityPrice"
            },
            {
              title: "套餐库存数量",
              key: "commodityNum"
            },
            {
              title: "上架时间",
              key: "beginTime"
            },
            {
              title: "下架时间",
              key: "endTime"
            }
          ]
        };
      },
      methods:{
        getCommodityList(){
          this.$axios({
            url: 'http://localhost:8081/restaurantManager/getCommodityList',//请求的地址
            method: 'post',//请求的方式
            data: {code: this.code}
          }).then(res => {
            // alert(res.data);
            console.info('后台返回的数据', res.data);
            this.commodityList = res.data
            // alert(this.commodityType);
          }).catch(err => {
            // alert(err.response.message);
            console.info('报错的信息', err.response.message);
          });
        },
        addCommodity(){
          this.$axios({
            url: 'http://localhost:8081/restaurantManager/addCommodity',//请求的地址
            method: 'post',//请求的方式
            data: this.formInline
          }).then(res => {
            // alert(res.data);
            console.info('后台返回的数据', res.data);
            alert("成功");
            location.reload();
            // alert(this.commodityType);
          }).catch(err => {
            // alert(err.response.message);
            console.info('报错的信息', err.response.message);
          });
        },
        getSetList(){
          this.$axios({
            url: 'http://localhost:8081/restaurantManager/getSetList',//请求的地址
            method: 'post',//请求的方式
            data: {code: this.code}
          }).then(res => {
            // alert(res.data);
            console.info('后台返回的数据', res.data);
            this.setList = res.data
            // alert(this.commodityType);
          }).catch(err => {
            // alert(err.response.message);
            console.info('报错的信息', err.response.message);
          });
        },
        addSet(){
          this.formInline.type = "套餐";
          this.$axios({
            url: 'http://localhost:8081/restaurantManager/addCommodity',//请求的地址
            method: 'post',//请求的方式
            data: this.formInline
          }).then(res => {
            console.info('后台返回的数据', res.data);
            alert("成功");
            location.reload();
            // alert(this.commodityType);
          }).catch(err => {
            // alert(err.response.message);
            console.info('报错的信息', err.response.message);
          });
        }

      },
      mounted() {
        this.getCommodityList();
        this.getSetList();
      }
    }
</script>

<style scoped>

</style>

<template>
  <div class="layout">
    <Layout>
      <MyHeader></MyHeader>
      <Breadcrumb style="margin: 20px 50px 10px">
        <BreadcrumbItem class="breadcrumbItem">主页</BreadcrumbItem>
        <BreadcrumbItem class="breadcrumbItem">商家列表</BreadcrumbItem>
        <BreadcrumbItem class="breadcrumbItem">{{restaurantName}}</BreadcrumbItem>
      </Breadcrumb>
      <Content>
        <Layout style="padding: 0 50px">
          <Sider breakpoint="md" :style="{background: '#fff'}" collapsible :collapsed-width="78" v-model="isCollapsed">
            <br><br>
            <Menu :active-name=commodityTypeList[0] theme="light" width="auto" :class="menuitemClasses">
              <div v-for="(item,index) in commodityTypeList" :key="item">
                <MenuItem :name=index @click.native="commodityType=item">
                  <Icon type="md-beer"/>
                  <span>{{item}}</span>
                </MenuItem>
              </div>
            </Menu>
            <div slot="trigger"></div>
          </Sider>
          <Content style="width:2000px;">
            <!--<p>{{addressList}}</p>-->
            <!--<p>{{numList}}</p>-->
            <!--<p v-model="commodities">{{commodities}}</p>-->
            <Row style="background:#f5f7f9;padding:20px">
              <Col span="6" v-for="(item,index) in commodities" :key="commodities.commodityId" >
                <Card v-if="(item.type===commodityType)" :bordered="true" :style="{height:'130px',marginRight:'10px',marginBottom:'8px'}">
                  <img :alt=item.commodityName  class="pic" v-bind:src=staticURL>
                  <br>
                  <h3 style="float:left;margin-left: 10px">{{item.commodityName}}</h3>
                  <br/>
                  <b style="float: left;margin-left: 20px;color:rgba(238,136,0,0.79);font-size: 17px">¥{{item.commodityPrice}}</b>
                  <br/><br/>
                  <small style='font-weight:400;font-size:1px;float: left;margin-left: 20px'>
                    购买数量:</small>
                  <InputNumber :name=inputName(index)  v-model="numList[index]" :max=item.commodityNum :min="0" size="small" :style="{width:'40px',height: '22px',float:'right',marginBottom: '2px',marginRight: '50px'}"></InputNumber>
                  <Button :name=buttonName(index) type="primary" ghost style="float: right;position: absolute;top:0px;right:0px;height: 130px;width:50px;border-width:0 0 0 1px;border-radius: 0px" @click="chooseCommodity(index)">
                    <Icon style="height: 10px" type="md-cart" />
                    <p style="height: 15px">加</p>
                    <p style="height: 15px">入</p>
                    <p style="height: 15px">购</p>
                    <p style="height: 16px">物</p>
                    <p style="height: 15px">车</p>
                  </Button>
                </Card>
              </Col>
            </Row>
          </Content>
        </layout>
      </Content>
      <Footer class="layout-footer-center">2019-2020 &copy; TalkingData</Footer>
    </Layout>
    <Affix :offset-bottom="20" style="float: right;margin-right: 80px">
      <div @click="inputInfo()">
        <b>{{myCommodities}}</b>
        <Badge :count=getPrice(price)>
          <img src='../../static/icons/cart.png' style="width: 80%"><img/>
        </Badge>
      </div>
    </Affix>
    <Modal
      v-model="modal1"
      title="填写信息"
      okText="提交订单"
      cancelText="取消"
      @on-ok="takeOrder()">
      <Form>
        {{addressId}}
        {{expectedDeliveryTime}}
        请选择地址<Select v-model="addressId" style="width:200px">
          <Option v-for="item in addressList" :value="item.addressId" :key="item.addressId">{{ item.address }}</Option>
        </Select>
        请选择时间<DatePicker v-model="expectedDeliveryTime" type="datetime" format="yyyy-MM-dd HH:mm" placeholder="请选择时间" style="width: 200px"></DatePicker>
      </Form>
    </Modal>
    <Modal
      v-model="modal2"
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
  </div>
</template>

<script>
    import MyHeader from "./MyHeader";
    export default {
      name: "RestaurantCommodityList",
      data(){
          return{
            email: this.$route.params.email,
            commodityType:"",
            restaurantId: this.$route.params.restaurantId,
            restaurantName:'商家详情',
            commodityTypeList:[],
            commodities:[],
            isCollapsed: false,
            staticURL: '../../static/commodities/commodity.png',
            price:0,
            myCommodities:"",
            numList:[],
            password:"",
            modal1:false,
            modal2: false,
            addressList:[],
            addressId:0,
            orderId: 0,
            actualPrice: 0,
            expectedDeliveryTime:""
          }
      },
      methods: {
        getAddressList(){
          this.$axios({
            url: 'http://localhost:8081/restaurant/addressList',//请求的地址
            method: 'post',//请求的方式
            data:{email: this.email}
          }).then(res => {
            // alert(res.data);
            console.info('后台返回的数据', res.data);
            this.addressList = res.data;
            // alert(this.commodityType);
          }).catch(err => {
            // alert(err.response.message);
            console.info('报错的信息', err.response.message);
          });
        },
        getCommodityTypeList(){
          this.$axios({
            url: 'http://localhost:8081/restaurant/commodityTypeList',//请求的地址
            method: 'post',//请求的方式
            data:{id: this.restaurantId}
          }).then(res => {
            // alert(res.data);
            console.info('后台返回的数据', res.data);
            this.commodityTypeList = res.data;
            this.commodityType=this.commodityTypeList.length > 0 ? this.commodityTypeList[0] : "";
            // alert(this.commodityType);
          }).catch(err => {
            // alert(err.response.message);
            console.info('报错的信息', err.response.message);
          });
        },
        getCommodityList(){
          this.$axios({
            url: 'http://localhost:8081/restaurant/commodityList',//请求的地址
            method: 'post',//请求的方式
            data:{id: this.restaurantId}
          }).then(res => {
            // alert(res.data);
            console.info('后台返回的数据', res.data);
            // alert(res.data);
            this.commodities = res.data;
            for (var i=0;i<res.data.length;i++) {
              this.numList[i] = 0;
            }
          }).catch(err => {
            // alert(err.response.message);
            console.info('报错的信息', err.response.message);
          });
        },
        menuitemClasses () {
          return [
            'menu-item',
            this.isCollapsed ? 'collapsed-menu' : ''
          ]
        },
        chooseCommodity(index) {
          var num = this.numList[index];

          // alert(this.myCommodities, this.price);
          if (num !== 0) {
            document.getElementsByName(this.buttonName(index)).item(0).style.backgroundColor = "#80C4FF";
            document.getElementsByName(this.inputName(index)).item(0).style.backgroundColor = "#80C4FF";
            document.getElementsByName(this.buttonName(index)).item(0).onclick = null;
            document.getElementsByName(this.inputName(index)).item(0).onclick = null;
            var commodityName = this.commodities[index].commodityName;
            var commodityPrice = this.commodities[index].commodityPrice.toFixed(2);
            this.myCommodities = this.myCommodities + commodityName + "*" + num+",";
            this.price = this.price + num * commodityPrice;
          }
        },
        inputName(index) {
          return "input" + index;
        },
        buttonName(index) {
          return "button" + index;
        }
        ,
        getPrice(price) {
          return parseFloat(price.toFixed(2));
        },
        inputInfo(){
          this.modal1 = true;
        },
        takeOrder(){
          this.$axios({
            url: 'http://localhost:8081/takeOrder/take',//请求的地址
            method: 'post',//请求的方式
            data:{email: this.email,
              commodity:this.myCommodities,
              price:this.getPrice(this.price),
              addressId:this.addressId,
              expectedDeliveryTime: this.expectedDeliveryTime,
              restaurantId: this.restaurantId
            }
          }).then(res => {
            // alert(res.data);
            console.info('后台返回的数据', res.data);
            var result = res.data; //orderId,actualPrice
            this.orderId=result.orderId;
            this.actualPrice = result.actualPrice;
            this.modal2 = true;
          }).catch(err => {
            // alert(err.response.message);
            console.info('报错的信息', err.response.message);
          });
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
              expectedDeliveryTime: this.expectedDeliveryTime
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
            // this.commodities = res.data;
            for (var i=0;i<res.data.length;i++) {
              this.numList[i] = 0;
            }
            this.price = 0;
            this.myCommodities = "";
            location.reload();

          }).catch(err => {
            // alert(err.response.message);
            console.info('报错的信息', err.response.message);
          });
        },
      },
      created(){
        this.getCommodityTypeList();
        this.getCommodityList();
        this.getAddressList();
      },
      components: { MyHeader}
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
  .breadcrumbItem{
    float: left;
    /*background-color: yellow;*/
  }
  .pic {
    /*size: 10px 50px;*/
    width: 30%;
    float: left;
    margin-left: 5px;
    /*position: absolute;*/
    /*float: left;*/
  }
  .layout-footer-center{
    text-align: center;
  }
  .menu-item span{
    display: inline-block;
    overflow: hidden;
    width: 69px;
    text-overflow: ellipsis;
    white-space: nowrap;
    vertical-align: bottom;
    transition: width .2s ease .2s;
  }
  .menu-item i{
    transform: translateX(0px);
    transition: font-size .2s ease, transform .2s ease;
    vertical-align: middle;
    font-size: 16px;
  }
  .collapsed-menu span{
    width: 0px;
    transition: width .2s ease;
  }
  .collapsed-menu i{
    transform: translateX(5px);
    transition: font-size .2s ease .2s, transform .2s ease .2s;
    vertical-align: middle;
    font-size: 22px;
  }
</style>

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
    width: 90%;
    float: left;
    margin-left: 14px;
    /*position: absolute;*/
    /*float: left;*/
  }
  .layout-footer-center{
    text-align: center;
  }
</style>
<template>
  <div class="layout">
    <Layout>
      <MyHeader></MyHeader>
      <Breadcrumb style="margin: 20px 50px 10px">
        <BreadcrumbItem class="breadcrumbItem">主页</BreadcrumbItem>
        <BreadcrumbItem class="breadcrumbItem">商家列表</BreadcrumbItem>
      </Breadcrumb>
      <Content style="padding: 0 50px">
        <Card :style="{marginBottom: '10px'}">
          <b style="min-height: 50px" v-for="item in restaurantTypeList" inline>
              <Button type="text" @click="classifyRestaurant(item)">{{item}} </Button>
          </b>
        </Card>
        <Card>
          <!--<p v-model="restaurants">{{restaurants}}</p>-->
          <Row style="background:#eee;padding:20px">
            <Col span="6" v-for="item in restaurants" :key="restaurants.id" >
              <Card :bordered="false" style="height: 350px;width:300px">
                <!--<a href="#/restaurantLogin/">-->
                <img  alt=item.restaurantName  class="pic" v-bind:src=staticURL+item.id+png @click="getRestaurantDetails(item.id)">
                <!--</a>-->
                <br><br>
                <h3 style="position: absolute;margin-top: 240px;margin-left:14px" @click="getRestaurantDetails(item.id)">
                  {{item.restaurantName}}
                </h3>
              </Card>
            </Col>
          </Row>
        </Card>
      </Content>
      <Footer class="layout-footer-center">2019-2020 &copy; TalkingData</Footer>
    </Layout>
  </div>
</template>
<script>
  import MyHeader from "./MyHeader";
  export default {
    name:'restaurantList',
    components: {MyHeader},
    data(){
      return{
        email: this.$route.params.email,
        restaurantTypeList: ["全部商家", "美食", "快餐便当", "特色菜系", "异国料理", "小吃夜宵", "甜品饮品", "果蔬生鲜", "商店超市", "鲜花绿植", "医药健康", "早餐", "午餐", "下午茶", "晚餐", "夜宵"],
        staticURL: '../../static/restaurants/restaurant',
        png: '.png',
        restaurants:[],
        type:'全部商家',
        formInline: {
          email: '123',
          password: '123'
        },
      }
    },
    methods:{
      classifyRestaurant(item){
        this.type = item;
        // alert(item);
        this.$axios({
          url: 'http://localhost:8081/restaurant/restaurantList',//请求的地址
          method: 'post',//请求的方式
          data: {type: item}//请求的表单数据
        }).then(res => {
          // alert(res.data);
          console.info('后台返回的数据', res.data);
          this.restaurants = res.data;
        }).catch(err => {
          // alert(err.response.message);
          console.info('报错的信息', err.response.message);
        });

      },
      getRestaurantDetails(id){
        // alert(this.email+" "+id);
        var path = "/restaurantCommodityList/" + this.email + "/" + id;
        this.$router.push(path);
      }
    },
    mounted() {
      this.classifyRestaurant("全部商家");
    }
  }
</script>

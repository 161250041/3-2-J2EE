<template>
  <div>
    <MyHeader></MyHeader>
    <!--点餐/退订/消费等-->
    <!--按照时间、金额、餐厅等分类统计-->
    <Col span="17">
      <Card dis-hover>
        <p slot="title">
          <Icon type="ios-film-outline"></Icon>
          订单
        </p>
        <b style="float: left">请选择时间区间:</b>
        <DatePicker v-model="time" type="daterange" format="yyyy/MM/dd"  placement="bottom-end" placeholder="Select date" style="width: 200px;float: left;margin-left: 10px">
        </DatePicker>
        订单数：{{this.totalNum}}   订单总额：{{this.totalPrice}}元
        <!--{{this.numData}}-->
        <!--{{this.option}}-->
        <br/><br/>
        <Table border :columns="columns" :data="list1">
        </Table>
      </Card>
    </Col>
    <Col span="7">
      <Card dis-hover>
        各级别消费金额的消费次数(单位:次)
        <div id="chart1"></div>
        <Divider />
        在各商家的消费总额(单位:元)
        <div id="chart2"></div>
      </Card>
    </Col>
    <!--点了什么餐 总共几单 总金额   按金额0-20 21-50 51-100 >100占比   按每个餐厅  几单  多少钱-->
  </div>

</template>

<script>
  import MyHeader from "./MyHeader";
  import echarts from 'echarts'

  var myDate = new Date();
  var mytime=myDate.toLocaleDateString();     //获取当前时间

  export default {
    name: "UserStatistics",
    components: {MyHeader},
    data(){
      return{
        email:this.$route.params.email,
        time: [mytime, mytime],
        list1:[],  //订单表格
        list2:[],  //res_price
        totalPrice:0,
        totalNum:0,
        myecharts: "",
        myecharts2: "",
        numData:[
          {value:0, name:'<=20'},
          {value:0, name:'21-100'},
          {value:0, name:'101-200'},
          {value:0, name:'>200'},
        ],
        priceData:[],
        columns:[
          {
            title: "订单编号",
            key:"orderId",
          },
          {
            title: "商家",
            key:"restaurant",
          },
          {
            title: "商品",
            key:"commodities",
          },
          {
            title: "下单时间",
            key:"beginTime",
            sortable:true
          },
          {
            title: "价格",
            key:"actualPrice",
            sortable:true
          },
          {
            title: "订单状态",
            key:"state",
          },
        ],
        option : {
          tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
          },
          series: [
            {
              name:'消费次数',
              type:'pie',
              radius: ['40%', '60%'],
              avoidLabelOverlap: false,
              data:[]
            }
          ],
        },
        option2 : {
          tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
          },
          legend: {
            orient: 'vertical',
            x: 'left',
            data:['<=20','21-100','101-200','>200']
          },
          series: [
            {
              name:'消费总额',
              type:'pie',
              radius: ['40%', '60%'],
              avoidLabelOverlap: false,
              data:[]
            }
          ],
        }
      }
    },
    watch:{
      time(newVal,oldVal){
        this.getList();
      }
    },
    methods:{
      getList(){
        // alert(this.time);
        this.$axios({
          url: 'http://localhost:8081/statistics/getUserStatistics',//请求的地址
          method: 'post',//请求的方式
          data: {
            email:this.email,
            beginTime:this.time[0],
            endTime: this.time[1]
          }//请求的表单数据
        }).then(res => {
          console.info('后台返回的数据', res.data);
          this.list1 = res.data.list1;
          let mylist = res.data.list1;
          this.list2 = res.data.list2;
          // 点了什么餐 总共几单 总金额   按金额0-20 21-50 51-100 >100占比   按每个餐厅  几单  多少钱
          for (var i=0;i< mylist.length;i++) {
            this.totalPrice = this.totalPrice + mylist[i].actualPrice;
            this.totalNum = this.totalNum + 1;
            if ( mylist[i].actualPrice < 21) {
              this.numData[0].value = this.numData[0].value + 1;
            }else if( mylist[i].actualPrice<101){
              this.numData[1].value = this.numData[1].value + 1;
            }else if ( mylist[i].actualPrice < 201) {
              this.numData[2].value = this.numData[2].value + 1;
            }else{
              this.numData[3].value = this.numData[3].value + 1;
            }
          }
          this.option.series[0].data = this.numData;
          this.option2.series[0].data = this.list2;
          this.myecharts = echarts.init(document.getElementById("chart1"));
          this.myecharts.setOption(this.option);
          this.myecharts2 = echarts.init(document.getElementById("chart2"));
          this.myecharts2.setOption(this.option2);
        }).catch(err => {
          console.info('报错的信息', err.response.message);
        });
      }
    },
    created() {
      this.getList();
    },
    mounted() {
      const that = this;
      window.addEventListener('resize', function () {
        that.myecharts.resize()
        that.myecharts2.resize()
      });
    }

  }
</script>

<style scoped>
  #chart1 {
    width: 90%;
    height: 350px;
    /*border: 1px solid red;*/
    margin: 0 auto;
  }
  #chart2 {
    width: 90%;
    height: 350px;
    /*border: 1px solid red;*/
    margin: 0 auto;
  }

</style>

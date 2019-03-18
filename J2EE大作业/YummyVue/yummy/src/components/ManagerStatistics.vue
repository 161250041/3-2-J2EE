<template>
  <div>
    <ManagerHeader></ManagerHeader>
    <Col span="19">
      <Card>
        营业额
        <div id="chart1"></div>
      </Card>
    </Col>
    <Col span="5">
      <div style="margin-top: 50px">餐厅数量:{{resNum}}</div>
      <div style="margin-top: 100px">用户数量:{{userNum}}</div>
    </Col>
    <!--查看统计信息（非详情）-->
    <!--餐厅统计-->
    <!--会员统计-->
    <!--Yummy！财务情况-->
    <!--采用图表显示（可选）-->
    <!--餐厅列表 会员列表 总数-->
    <!--总营业额-->
  </div>


</template>

<script>
    import ManagerHeader from "./ManagerHeader";
    import echarts from 'echarts'

    export default {
        name: "UserStatistics",
      components: {ManagerHeader},
      data(){
          return{
            mychart: "",
            list: "",
            userNum: 0,
            resNum: 0,
            option: {
              xAxis: {
                type: 'category',
                data: ['18.10', '18.11', '18.12', '19.1', '19.2', '19.3']
              },
              yAxis: {
                type: 'value'
              },
              series: [{
                data: [820, 932, 901, 934, 1290, 1330, 1320],
                type: 'line'
              }]
            },
          }
      },
      methods: {
          getList(){
            // alert(this.time);
            this.$axios({
              url: 'http://localhost:8081/statistics/getManagerStatistics',//请求的地址
              method: 'post',//请求的方式
              data:{
              }//请求的表单数据
            }).then(res => {
              console.info('后台返回的数据', res.data);
              this.list = res.data;
              // alert(this.list)
              //
              this.option.series[0].data = this.list;
              // this.option2.series[0].data = this.stateData;
              this.myecharts = echarts.init(document.getElementById("chart1"));
              this.myecharts.setOption(this.option);
              // this.myecharts2 = echarts.init(document.getElementById("chart2"));
              // this.myecharts2.setOption(this.option2);
            }).catch(err => {
              console.info('报错的信息', err.response.message);
            });
          },
        getResNum(){
          this.$axios({
            url: 'http://localhost:8081/statistics/getResNum',//请求的地址
            method: 'post',//请求的方式
            data:{
            }//请求的表单数据
          }).then(res => {
            console.info('后台返回的数据', res.data);
            this.resNum= res.data;

          }).catch(err => {
            console.info('报错的信息', err.response.message);
          });
        },
        getUserNum() {
          this.$axios({
            url: 'http://localhost:8081/statistics/getUserNum',//请求的地址
            method: 'post',//请求的方式
            data:{
            }//请求的表单数据
          }).then(res => {
            console.info('后台返回的数据', res.data);
            this.userNum= res.data;

          }).catch(err => {
            console.info('报错的信息', err.response.message);
          });
        }

      },
      created() {
        this.getList();
        this.getResNum();
        this.getUserNum();
      },
      mounted() {
        // this.option.series[0].data = this.numData;
        this.myecharts = echarts.init(document.getElementById("chart1"));
        this.myecharts.setOption(this.option);

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
</style>

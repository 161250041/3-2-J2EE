<template>
  <div>
    <!--<p>{{message}}</p>-->
    <!--<router-link :to="{ name: login}">点击我跳转到登陆页面</router-link>-->
  </div>
</template>

<script>
    export default {
      name: "ActivePage",
      data(){
        return{
          message: "ddd",
          activeInfo:{
            email: "",
            code:""
          }
        }
      },
      mounted() {
        this.doActive();
      },
      methods:{
        doActive() {
          this.activeInfo.code=this.$route.params.code;
          this.activeInfo.email=this.$route.params.email;
          this.$axios({
            url: 'http://localhost:8081/signin/active',//请求的地址
            method: 'post',//请求的方式
            data: this.activeInfo//请求的表单数据
          }).then(res => {
            console.info('后台返回的数据', res.data);
            if (res.data === true) {    //用户不存在
              this.$Message.success('激活成功');
            } else {
              this.$Message.info("激活失败");
            }
            console.info(this.$route.params);
            this.$router.push("/");
          }).catch(err => {
            console.info('报错的信息', err.response.message);
            this.$Message.error('激活发生错误');
          });
        }
      }
    }
</script>

<style scoped>

</style>

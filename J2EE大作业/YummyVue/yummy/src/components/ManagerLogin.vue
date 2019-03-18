<template>
  <div id="login" style="background-image:url(http://img.hb.aicdn.com/f3e17907fe2a96b52cdbff1c30fb4f097a1401ac2f5166-MSpvIS_fw658);
    background-repeat: no-repeat;background-size:800px;background-color:#EFF8FB;background-position: right;width: 100%;height: 100%;margin-top: 0px;">
    <router-link to="/" style="float: left">返回用户登陆页面</router-link>
    <Form ref="formInline" :model="formInline"  id="signinform" inline >
      <FormItem prop="id">
        <b>管理员id：</b>
        <Input type="text" prefix="ios-contact" placeholder="请输入名称" style="width: 250px"
               v-model="formInline.id"/>
      </FormItem>
      <br><br>
      <FormItem prop="password">
        <b>管理员密码：</b>
        <Input type="password" prefix="ios-lock" placeholder="请输入密码" style="width: 250px"
               v-model="formInline.password"/>
      </FormItem>
      <br><br>
      <FormItem>
        <Button type="primary" @click="handleSubmit('formInline')">登录</Button>
      </FormItem>
    </Form>
  </div>
</template>

<script>
    export default {
        name: "ManagerLogin",
      data() {
        return {
          modal1: false,
          formInline: {
            id: '',
            password: ''
          }
        }
      },
      methods:{
        handleSubmit(name) {   //登录
          this.$refs[name].validate((valid) => {
            if (valid) {
              if (this.formInline.id==="admin"&&this.formInline.password==="admin") {
                this.$Message.success("登陆成功");
                this.$router.push("/approveApplication");
              }else{
                this.$Message.info("请填写正确的用户名和密码");
              }
            }else {
              this.$Message.error('请填写有效的邮箱和密码!');
            }
          })
        }
      }
    }
</script>

<style scoped>
  #signinform {
    position: absolute;
    /*width: 27%;*/
    margin-top: 230px;
    margin-left: 150px;
    padding: 50px 55px 10px 55px;
    background-color: white;
    opacity: 0.9;
    border-radius: 5%;
  }
</style>

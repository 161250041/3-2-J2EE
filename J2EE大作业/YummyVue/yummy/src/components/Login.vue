<template>
  <div id="login" style="background-image:url(http://img.hb.aicdn.com/f3e17907fe2a96b52cdbff1c30fb4f097a1401ac2f5166-MSpvIS_fw658);
    background-repeat: no-repeat;background-size:800px;background-color:#EFF8FB;background-position: right;width: 100%;height: 100%;margin-top: 0px;">
    <router-link to="/managerLogin" style="float: left">管理员由此进入</router-link>

    <Form ref="formInline" :model="formInline" :rules="ruleInline" id="signinform" inline >
      <FormItem prop="email">
        <b>邮箱：</b>
        <Input type="text" prefix="ios-contact" placeholder="请输入邮箱" style="width: 250px"
        v-model="formInline.email"/>
      </FormItem>
      <br><br>
      <FormItem prop="password">
        <b>密码：</b>
        <Input type="password" prefix="ios-lock" placeholder="请输入密码" style="width: 250px"
        v-model="formInline.password"/>
      </FormItem>
      <br><br>
      <FormItem>
        <Button type="primary" @click="handleSubmit('formInline')">登录</Button>
      </FormItem>
      <FormItem>
        <Button type="dashed" @click="modal1=true">注册</Button>
      </FormItem>
      <p>
      <router-link to="/restaurantLogin">我是商家</router-link>
      <!--<router-link :to="{ name: 'buttonTest', params: { id: 123 }}">Go to see the buttons</router-link>-->
      </p>
    </Form>


    <!--<button onclick="document.getElementById('id01').style.display='block'" id="register">注册</button>-->

    <Modal
      v-model="modal1"
      title="注册"
      okText="提交"
      cancelText="取消"

      @on-ok="submit('registerInline')">
      <!--@on-cancel="cancel">-->
      <Form ref="registerInline" :model="registerInline" :rules="ruleInline" inline>
        <FormItem prop="email">
          <b>邮箱：</b>
          <Input type="text" prefix="ios-contact" placeholder="请输入邮箱" style="width: 250px"
                 v-model="registerInline.email"/>
        </FormItem>
        <br><br>
        <FormItem prop="password">
          <b>密码：</b>
          <Input type="password" prefix="ios-lock" placeholder="请输入密码" style="width: 250px"
                 v-model="registerInline.password"/>
        </FormItem>
        <br><br>
        <FormItem prop="repeatedPassword">
          <b>再次输入密码：</b>
          <Input type="password" prefix="ios-lock" placeholder="请再次输入密码" style="width: 250px"
                 v-model="registerInline.repeatedPassword"/>
        </FormItem>
      </Form>
      <!--<p>{{registerInline}}</p>-->
      <span v-if="!checkRepeat()" style="color:red;">{{registerInline.errorText}}</span>
    </Modal>

  </div>

</template>

<script>

  export default {
    name: 'Login',
    data() {
      return {
        modal1:false,
        formInline: {
          email: '',
          password: ''
        },
        registerInline:{
          email: '',
          password:'',
          repeatedPassword:'',
          errorText:"两次密码输入不相同"
        }
        ,
        ruleInline: {
          email: [
            {required: true, message: '请填写邮箱', trigger: 'blur'}
          ],
          password: [
            {required: true, message: '请填写密码', trigger: 'blur'},
            {
              type: 'string',
              min: 6,
              message: '密码长度不可小于6个字符',
              trigger: 'blur'
            }
          ],
          repeatedPassword:[
            {required: true, message: '请再次填写密码', trigger: 'blur'},
            {
              type: 'string',
              min: 6,
              message: '密码长度不可小于6个字符',
              trigger: 'blur'
            }
          ]
        }
      }
    },
    methods: {
      handleSubmit(name) {   //登陆
        this.$refs[name].validate((valid) => {
          if (valid) {
            //todo
            this.$axios({
              url: 'http://localhost:8081/signin/login',//请求的地址
              method: 'post',//请求的方式
              data: this.formInline//请求的表单数据
            }).then(res => {
              console.info('后台返回的数据', res.data);
              if (res.data === 0) {    //用户不存在
                this.$Message.info('用户不存在');
              } else if (res.data === 2) {
                this.$Message.success("登录成功");
                this.$router.push("/restaurantList/"+this.formInline.email);
                console.info(this.$route.params);
              } else {
                this.$Message.info('密码错误');
              }
            }).catch(err => {
              console.info('报错的信息', err.response.message);
              this.$Message.error('登录发生错误');
            });
          } else {
            this.$Message.error('请填写有效的邮箱和密码!');
          }
        })
        // this.formInline.email = '';
        // this.formInline.password = '';
      },
      submit(name){          //注册
        this.$refs[name].validate((valid) => {
          if (valid) {
            //todo
            var registerInfo = {email: this.registerInline.email, password:this.registerInline.password};

            this.$axios({
              url: 'http://localhost:8081/signin/register',//请求的地址
              method: 'post',//请求的方式
              data: registerInfo//请求的表单数据
            }).then(res => {
              console.info('后台返回的数据', res.data);
              if (res.data===0) {
                this.$Message.info("注册失败，请检查邮箱格式是否正确");
              } else if (res.data === 1) {
                this.$Message.info("该用户名已被占用");
              } else {
                this.$Message.success("注册成功，请进入链接进行邮箱验证");
              }
            }).catch(err => {
              console.info('报错的信息', err.response.message);
              this.$Message.error('注册发生错误');
            });
          } else {
            this.$Message.error('请填写符合要求的邮箱和密码!');
          }
          // this.registerInline.email = '';
          // this.registerInline.password = '';
          // this.registerInline.repeatedPassword = '';
        })
      },
      checkRepeat() {
        var pwd1 = this.registerInline.password;
        var pwd2 = this.registerInline.repeatedPassword;
        // <!-- 对比两次输入的密码 -->
        if(pwd1 === pwd2) {
          return true;
        }
        else {
          return false;
        }
      }
    }
  }

</script>

<style type="text/css">
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

  /*input[type=text], input[type=password] {*/
    /*width: 100%;*/
    /*padding: 12px 20px;*/
    /*margin: 8px 0px;*/
    /*display: inline-block;*/
    /*border: 1px solid #ccc;*/
    /*box-sizing: border-box;*/
  /*}*/

  /*#signin{*/
    /*background-color: midnightblue;*/
    /*position: absolute;*/
    /*color: white;*/
    /*padding: 10px 80px;*/
    /*margin: 50px 240px;*/
    /*border: none;*/
    /*cursor: pointer;*/
  /*}*/
  #register{
    background-color: gray;
    position: absolute;
    color: white;
    padding: 10px 80px;
    margin: 50px 13px;
    border: none;
    cursor: pointer;
  }
  /* Set a style for all buttons */
  button{
    background-color: #4CAF50;
    color: white;
    padding: 10px 80px;
    margin: 8px 0px;
    border: none;
    cursor: pointer;
  }

  /*!* Extra styles for the cancel button *!*/
  .cancelbtn {
    background-color: #f44336;
  }

  /* Float cancel and signup buttons and add an equal width */
  .cancelbtn,.signupbtn {float:left;width:50%}

  /* Add padding to container elements */
  .container {
    padding: 16px;
  }

  /* The Modal (background) */
  .modal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
    padding-top: 60px;
  }

  /* Modal Content/Box */
  .modal-content {
    background-color: #fefefe;
    margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
    border: 1px solid #888;
    width: 30%; /* Could be more or less, depending on screen size */
  }

  /* The Close Button (x) */
  .close {
    position: absolute;
    right: 35px;
    top: 15px;
    color: #000;
    font-size: 40px;
    font-weight: bold;
  }

  .close:hover,
  .close:focus {
    color: red;
    cursor: pointer;
  }

  /* Clear floats */
  .clearfix::after {
    content: "";
    clear: both;
    display: table;
  }

  Change styles for cancel button and signup button on extra small screens
  @media screen and (max-width: 300px) {
    .cancelbtn, .signupbtn {
      width: 100%;
    }
  }
</style>

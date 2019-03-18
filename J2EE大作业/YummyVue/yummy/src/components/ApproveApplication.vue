<template>
  <div>
    <ManagerHeader></ManagerHeader>
    <Table border :columns="columns" :data="list">
      <template slot-scope="{ row, index }" slot="action">
        <Button type="primary" size="small" style="margin-right: 5px" @click="approve(index)">通过</Button>
        <Button type="error" size="small" @click="reject(index)">拒绝</Button>
      </template>
    </Table>
  </div>
</template>

<script>
    import ManagerHeader from "./ManagerHeader";
    export default {
        name: "ApproveApplication",
      components: {ManagerHeader, Manager},
      data(){
          return{
            list:[],
            columns:[
              {
                title: "商家id",
                key:"code"
              },
              {
                title: "商家名称",
                key:"name"
              },
              {
                title: "联系方式",
                key:"phoneNumber"
              },
              {
                title: "类型",
                key:"type"
              },
              {
                title: "地址",
                key:"address"
              },
              {
                title: "操作",
                slot:"action"
              },
            ]
          }
      },
      methods:{
        approve(index){
          this.$axios({
            url: 'http://localhost:8081/approveApplication/approve',//请求的地址
            method: 'post',//请求的方式
            data:{id:this.list[index].id}
          }).then(res => {
            if (res.data === true) {
              location.reload();
            }
          }).catch(err => {
            console.info('报错的信息', err.response.message);
            this.$Message.error('发生错误');
          });
        },
        reject(index){
          this.$axios({
            url: 'http://localhost:8081/approveApplication/reject',//请求的地址
            method: 'post',//请求的方式
            data:{id:this.list[index].id}
          }).then(res => {
            location.reload();
          }).catch(err => {
            console.info('报错的信息', err.response.message);
            this.$Message.error('发生错误');
          });
        },
        getUnapprovedInfo(){
          this.$axios({
            url: 'http://localhost:8081/approveApplication/getUnApprovedInfo',//请求的地址
            method: 'post',//请求的方式
          }).then(res => {
            this.list = res.data;
          }).catch(err => {
            console.info('报错的信息', err.response.message);
            this.$Message.error('发生错误');
          });
        }
      },
      mounted() {
        this.getUnapprovedInfo();
      }
    }
</script>

<style scoped>

</style>

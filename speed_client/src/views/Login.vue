<template>
  <div id="login">
    
    <div class="card-login form">
      <div class="header">
        <h2>管理员登录</h2>
      </div>
      <div class="body">
        <div class="msgs">
          <span v-if="loading" class="yellow msg">登录中...</span>
          <!-- <span v-else-if="errorMsg" class="icon iconfont iconnotice red msg" v-text="errorMsg"></span> -->
        </div>
        <div class="row">
          <input ref="username" id="username" placeholder="账号" type="text" v-model="form.username">
        </div>
        <div class="row">
          <input ref="password" id="password" placeholder="密码" type="password" v-model="form.password">
        </div>
      </div>
      <!-- <div class="row">
        <label for="captcha">验证码</label>
        <input id="captcha" type="text" v-model="form.tryCode">
        <img alt="验证码" src="/api/defaultKaptcha" @click="getCaptcha()" ref="captcha"/>
      </div> -->
      <div class="footer">
        <span class="btn btn-yellow login" @click="onSubmit">登录</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      form: {
        username: "",
        password: ""
      },
      loading:false,
      //errorMsg:''
    };
  },
  methods: {
    onSubmit() {
      if(!this.form.username){
        //this.errorMsg='请输入账号';
        this.$toast({
          showCancel:false,
          t1:'提示',//弹窗的标题
          t2: '请输入账号',//弹窗的内容
          type:'error'
        });
        this.$refs['username'].focus();
      }else if(!this.form.password){
        //this.errorMsg='请输入密码';
        this.$toast({
          showCancel:false,
          t1:'提示',//弹窗的标题
          t2: '请输入密码',//弹窗的内容
          type:'error'
        });
        this.$refs['password'].focus();
      }else{
        this.loading = true;
        //this.errorMsg='';
        let data = this.$qs.stringify(this.form);
        let that = this;
        this.$axios({
            method:'post',
            url:'/api/admin/login',
            data: data
        }).then((response) =>{
            console.log('返回登录反馈数据：↓');
            console.log(response.data);
            let info = response.data;
            if(info.flag){
              let admin = info.data;
              that.$store.commit('setAdmin',admin);
              that.$router.push('/admin/records');
              this.$toast({
                showCancel:false,
                t1:'登陆成功',//弹窗的标题
                t2: '欢迎您，<b>'+admin.nickname+'</b>',//弹窗的内容
                type:'success'
              });
            }else{
              //that.errorMsg = info.errorMsg;
              this.$toast({
                showCancel:false,
                t1:'错误',//弹窗的标题
                t2: info.errorMsg,//弹窗的内容
                type:'error'
              });
            }
            that.loading = false;
        }).catch((error) => {
            this.$toast({
                showCancel:false,
                t1:'错误',//弹窗的标题
                t2:'出错了',//弹窗的内容
                type:'error'
              });
            that.loading = false;
        });
      }
    },
  },
};
</script>

<style lang="less" scoped>
#login {
  border: 1px solid transparent;

  .form {
    margin-top: 1rem;
    .header{
      text-align: center;
    }
    .body{
      .msgs{
        text-align: center;
        
        .msg{
          display: inline-block;
          margin-top: 1rem;
          font-size: 0.9rem;
        }
      }
      .row{
        margin-top: 1rem;
        input{
          display: block;
          box-sizing: border-box;
          width: 100%;
          background-color: @c_bg;
          height: 2.4rem;
          border-radius: 1.2rem;
          line-height: 2.4rem;
          padding: 0 1rem;
          font-size: 1rem;
          border: none;
          outline: none;
          color: @c2;
          text-align: center;
          &:focus{
            border: none;
          }
        }
      }
    }
    .footer{
      margin-top: 1rem;
      .login{
        display: block;
        text-align: center;
      }
    }
  }
}
</style>

<template>
<div id="donate">
  <div class="content card">
    <img :src="url" alt="微信支付">
    <p class="wechat">感谢各位的支持与理解，捐赠将用于网站服务器的开销</p>
    <p class="wechat"><span class="icon iconfont iconweixin"></span>微信扫码（显示昵称请备注，谢谢支持~）</p>
  </div>
  <div class="list card">
    <div class="header">
      <span class="icon iconfont iconleft yellow"></span>
      <span class="header_name">捐助榜</span>
      <span class="icon iconfont iconright yellow"></span>
    </div>
    <div class="body">

      <div class="loading" v-if="loading">
        <span class="icon iconfont iconloading_filled"></span>
      </div>
      <div class="rows" v-if="donates.length>0">
        <div class="row row_header">
          <span class="name">昵称</span>
           <div class="money th-btn"
          :class="{'yellow':orderBy=='moneyUp'||orderBy=='moneyDown'}" 
          @click="clickMoney()">
            <span>金额</span>
            <i class="iconfont iconxiangxia" 
            :class="orderBy=='moneyUp'?'rotate180':(orderBy=='moneyDown'?'yellow':'grey2')">
            </i>
          </div>

          <div class="date th-btn" 
          :class="{'yellow':orderBy=='dateUp'||orderBy=='dateDown'}"
          @click="clickDate()">
            <span>日期</span>
            <i 
            class="iconfont iconxiangxia" 
            :class="orderBy=='dateUp'?'rotate180':(orderBy=='dateDown'?'':'grey2')">
            </i>
          </div>
        </div>
        <div class="row" v-for="donate in donatesAll" :key="donate.id">
          <span class="name" v-text="donate.name"></span>
          <span class="money" v-text="'￥'+donate.money"></span>
          <span class="date" v-text="donate.date"></span>
        </div>
      </div>

    </div>
  </div>
</div>
</template>

<script>

export default {
    name: "Donate",
    components: {

    },
    mounted(){
      this.initData();
    },
    watch: {

    },
    computed: {
      donatesAll(){
        return this.donates
        .sort(this.orderBySort);
      },
    },
    data() {
      return {
        url:require('@/assets/img/donate.jpg'),
        donates:[],
        loading: true,
        orderBy:'dateDown',
      };
    },
    methods: {
      
      initData(){
        this.loading=true;
        this.$axios({
          method: "get",
          url: "/api/donates",
        }).then((response) => {
          console.log("返回捐款数据↓");
          let info = response.data;
          console.log(info);
          if(info.flag){
            this.donates=info.data;
          }else{
            console.error(info.errorMsg);
          }
          this.loading=false;
        }).catch((error) => {
          console.error(error);
          this.loading=false;
        });
      },
       orderBySort(a,b){
        switch(this.orderBy){
          case 'moneyUp':return a.money - b.money;
          case 'moneyDown':return b.money - a.money;
          case 'dateUp':return new Date(a.date) - new Date(b.date);
          case 'dateDown':return new Date(b.date) - new Date(a.date);
        }
      },
      clickMoney(){
        if(this.orderBy=='moneyDown'){
          this.orderBy='moneyUp'
        }else{
          this.orderBy='moneyDown'
        }
      },
      clickDate(){
        if(this.orderBy=='dateDown'){
          this.orderBy='dateUp'
        }else{
          this.orderBy='dateDown'
        }
      },
    }
};
</script>

<style lang="less" scoped>
#donate {
  border: 1px solid transparent;

  .content {
    text-align: center;
    
    img {
      width: 12rem;
      height: 12rem;
    }
    .wechat {
      font-size: 1rem;
      color: @c2;
      line-height: 2;
      .icon {
        color: @c_green;
        font-size: 1rem;
        margin-right: 0.5rem;
      }
    }
  }

  .list {
    .header {
      text-align: center;
      height: 1.2rem;
      line-height: 1.2rem;
      font-size: 1.2rem;
      font-weight: bold;
      color: @c1;
    }

    .body {
      .loading {
        margin:2rem 0;
        text-align: center;
        font-size: 1rem;
        font-weight: bold;
        color: @c2;
        .icon {
          display: inline-block;
          width: 1rem;
          height: 1rem;
          line-height: 1rem;
          font-size: 1rem;
          text-align: center;
          animation: rotate linear 1s infinite;
          -moz-animation: rotate linear 1s infinite;	/* Firefox */
          -webkit-animation: rotate linear 1s infinite;	/* Safari 和 Chrome */
          -o-animation: rotate linear 1s infinite;
        }
      }
      .rows {
        max-width: 30rem;
        margin: 0 auto;
        .row {
          margin: 1rem;
          display: flex;
          color: @c1;
          
          .name { 
            flex: 2;
            overflow: hidden;
            text-align: center;
          }
          .money {
            flex: 1;
            text-align: center;
            color: @c_yellow;
            overflow: hidden;
          }
          .date {
            flex: 2;
            overflow: hidden;
            text-align: center;
          }
        }
        .row_header {
          font-weight: bold;
          margin-top: 2rem;
          color: @c2;
          .money {
            color: @c2;
          }

          .money,.date{
            cursor: pointer;
          }
        }
      }
    }
  }
}
</style>
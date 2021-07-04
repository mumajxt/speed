<template>
  <div class="mask-wrapper animate__animated" :class="visible?'animate__fadeIn':'animate__fadeOut'" @click="close()" @click.stop>
    <div class="drawer animate__animated" :class="visible?'animate__fadeInUp':'animate__fadeOutDown'" @click.stop>
      <div class="drawer-header">
        <h2>选择车手</h2>
      </div>
      <div class="drawer-body">
         <div class="search-box">
          <span class="search icon iconfont iconsousuo"></span>
          <input placeholder="搜索" type="text" v-model="searchKey">
          <span class="close">
            <span v-if="searchKey" class="icon iconfont iconclose" @click="searchKey=''"></span>
          </span>
          <span class="btn btn-yellow" @click="isShowDrawerPlayer=true">
            <span class="icon iconfont iconclose rotate45"></span>
            <span> 添加</span>
          </span>
        </div>
        <div class="container">
          <div class="left nav">
            <div class="nav-item"
              :class="navSelectedItem=='all'?'nav-item-selected':''"
              @click="navSelectedItem='all'">
              全部<span class="count" v-text="playersAll.length"></span>
            </div>
            <div class="nav-item"
              :class="navSelectedItem=='player'?'nav-item-selected':''"
              @click="navSelectedItem='player'">
              普通车手<span class="count" v-text="players.length"></span>
            </div>
            <div class="nav-item"
              :class="navSelectedItem=='proPlayer'?'nav-item-selected':''"
              @click="navSelectedItem='proPlayer'">
              职业车手<span class="count" v-text="proPlayers.length"></span>
            </div>
            
          </div>
          <div class="right players">
            <span class="label btn btn-label"
              v-for="player in tempPlayers" 
              :key="player.id" v-text="player.name" 
              :class="playerOut?(playerOut.id==player.id?'btn-label-yellow':''):''"
              @click="chosePlayer(player)">
            </span>
          </div>
        </div>
        
      </div>
      <div class="drawer-footer">
        <div class="drawer-footer-btn drawer-footer-btn-grey" @click="close()">取消</div>
      </div>
    </div>

    <DrawerPlayer :playerNameOut="searchKey" v-if="isShowDrawerPlayer" @close="isShowDrawerPlayer=false" @chosePlayer="chosePlayer"></DrawerPlayer>
  </div>
</template>

<script>
import DrawerPlayer from '@/components/drawer/DrawerPlayer';
export default {
    name: "DrawerChosePlayer",
    components: {
      DrawerPlayer
    },
    props: {
      playerOut:Object,
      typeOut:String
    },
    mounted(){
      this.initPlayers();
    },
    watch: {
      playersAll:{
        handler(val){
          this.players = [],
          this.proPlayers = [],
          val.forEach(player => {
            switch(player.isPro){
              case false:this.players.push(player);break;
              case true:this.proPlayers.push(player);break;
              default:break;
            }
          });
        },
        deep:true
      }
    },
    computed: {
      playersAll(){
        let reg = new RegExp(this.searchKey);
        return this.$store.state.players
        .sort((a,b)=>{
          return b.power-a.power;
        })
        .filter(player=>reg.test(player.name));
      },
      tempPlayers(){
        switch(this.navSelectedItem){
          case 'all':return this.playersAll;
          case 'player':return this.players;
          case 'proPlayer':return this.proPlayers;
          default:return [];
        }
      }
    },
    data() {
      return {
        visible:true,
        searchKey:'',
        isShowDrawerPlayer:false,
        navSelectedItem:'all',
        loading:false,

        players:[],
        proPlayers:[],
      };
    },
    methods: {
      close(){
        this.visible=false;
        setTimeout(()=>{
          this.$emit('close');
        },300);
      },
      chosePlayer(player){
        this.$emit('chosePlayer',player);
        this.close();
      },
      initPlayers(){
        this.loading=true;
        if(this.playersAll.length==0){
           this.$store.dispatch('listPlayers').then(() => {
            this.loading = false;
          }).catch(() => {
            this.$toast({
              showCancel:false,
              t1:'错误',
              t2: '无法请求车队数据',
              type:'error'
            });
            this.loading = false;
          });
        }
      }
    }
};
</script>

<style lang="less" scoped>
.mask-wrapper {
  background: rgba(0, 0, 0, 0.5);
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: 2001;

  .drawer{
    border-top-left-radius: 1rem;
    border-top-right-radius: 1rem;
    background-color: @c_body_bg;
    overflow: hidden;
    position: fixed;
    bottom: 0;
    left: 0;
    width: 100%;
    height: auto;
    max-height: 90%;
    display: flex;
    flex-direction: column;

    .drawer-header{
        text-align: center;
        padding: 1rem;
        background-color: white;
    }
    .drawer-body{
      flex: 1;
      margin-top: 0.1rem;
      overflow: hidden;
      background-color: #fff;

      .search-box{
        position: relative;
        display: flex;
        align-items: center;
        color: @c3;
        margin:1rem;
        flex: 1;

        span.search{
          height:2.4rem;
          width: 2.4rem;
          line-height: 2.4rem;
          text-align: center;
          
          border-top-left-radius: 0.6rem;
          border-bottom-left-radius: 0.6rem;
          background-color: @c_bg;
        }
        input{
          display: block;
          width: 0;
          flex: 1;
          box-sizing: border-box;
          background-color: @c_bg;
          height: 2.4rem;
          line-height: 2.4rem;
          font-size: 1rem;
          border: none;
          border-radius: 0;
          outline: none;
          color: @c1;
          text-align: left;
          &:focus{
            border: none;
          }
        }
        span.close{
          cursor: pointer;
          height: 2.4rem;
          width: 2.4rem;
          line-height: 2.4rem;
          text-align: center;
          border-top-right-radius: 0.6rem;
          border-bottom-right-radius:0.6rem;
          background-color: @c_bg;
        }
        span.btn{
          margin-left: 1rem;

        }
        
      }

      .container{
        display: flex;
        .left{
          overflow-y: auto;
          height: 21.6rem;
          .nav-item{
            height: 2.4rem;
            line-height: 2.4rem;
            padding: 0 1rem;
            cursor: pointer;
            .count{
              font-size: 0.8rem;
              margin-left: 0.5rem;
            }
          }
          .nav-item-selected{
            border-left: 0.2rem solid @c_yellow;
            font-weight: bold;
            color: @c_yellow;
            background-image: linear-gradient(to right,@c_yellow_bg, #fff);
          }
        }

        .right{
          flex: 1;
          padding: 0.5rem;
          height: 21.6rem;
          overflow-y: auto;
          .label{
            margin-right: 0.5rem;
            margin-bottom: 0.5rem;
          }
        }
      }

    }
    .drawer-footer{
        margin-top: 0.5rem;
        padding: 1rem 1rem 3rem 1rem;
        background: white;
    }
  }
  
}

</style>

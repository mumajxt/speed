<template>
  <div class="mask-wrapper animate__animated" :class="visible?'animate__fadeIn':'animate__fadeOut'" @click="close()" @click.stop>
    <div class="drawer animate__animated" :class="visible?'animate__fadeInUp':'animate__fadeOutDown'" @click.stop>
      <div class="drawer-header">
        <h2>筛选车手</h2>
      </div>
      <div class="drawer-body">
        <div class="input-wrapper">
          <div class="input-icon">
            <i class="iconfont iconsousuo"></i>
          </div>
          <input type="text" placeholder="搜索"  v-model="searchKey">
          <span class="input-icon">
            <i v-if="searchKey" class="iconfont iconclose" @click="searchKey=''"></i>
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
        <span class="divider"></span>
        <div class="drawer-footer-btn drawer-footer-btn-red" @click="chosePlayer(null)">清除筛选</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
    name: "UserDrawerChosePlayer",
    props: {
      playerOut:Object,
      type:String,
      pet:String,
      ecu:String
    },
    created(){
      this.initPlayers();
    },
    watch: {
      type:function(){
        this.initPlayers();
      },
      pet:function(){
        this.initPlayers();
      },
      ecu:function(){
        this.initPlayers();
      },
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
        return this.ps
        .filter(player=>reg.test(player.name))
        .sort((a,b)=>{
          return b.power-a.power;
        });
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
        navSelectedItem:'all',
        loading:false,
        ps:[],
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
        let params={
          type:this.type,
          pet:this.pet,
          ecu:this.ecu
        }
        this.$axios({
          method: "get",
          url: "/api/players",
          params: params
        }).then((response) => {
          let info = response.data;
          console.log("--------------------------");
          if(info.flag){
            console.log("返回筛选车手数据↓");
            console.log(info.data);
            this.ps=info.data;
          }else{
            console.error(info.errorMsg);
          }
          console.log("--------------------------");
          this.loading=false;
        }).catch((error) => {
          console.error(error);
          this.loading=false;
        });
      }
    }
};
</script>

<style lang="less" scoped>
.mask-wrapper {
  .drawer{
    .drawer-body{
      background-color: #fff;

      .input-wrapper{
        margin: 1rem;
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
  }
}

</style>

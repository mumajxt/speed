<template>
  <div class="mask-wrapper animate__animated" :class="visible?'animate__fadeIn':'animate__fadeOut'" @click="close()" @click.stop>
    <div class="drawer animate__animated" :class="visible?'animate__fadeInUp':'animate__fadeOutDown'" @click.stop :style="{height:height}">
      <div class="drawer-header">
        <h2>选择车队</h2>
      </div>
      <div class="drawer-body">
         <div class="search-box">
          <span class="search icon iconfont iconsousuo"></span>
          <input placeholder="搜索" type="text" v-model="searchKey">
          <span class="close">
            <span v-if="searchKey" class="icon iconfont iconclose" @click="searchKey=''"></span>
          </span>
          <span class="btn btn-yellow" @click="isShowDrawerTeam=true">
            <span class="icon iconfont iconclose rotate45"></span>
            <span> 添加</span>
          </span>
        </div>
        <div class="container">
          <div class="left nav">
            <div class="nav-item"
              :class="navSelectedItem=='teams'?'nav-item-selected':''"
              @click="navSelectedItem='teams'">
              普通车队<span class="count" v-text="teams.length"></span>
            </div>
            <div class="nav-item"
              :class="navSelectedItem=='proTeams'?'nav-item-selected':''"
              @click="navSelectedItem='proTeams'">
              职业车队<span class="count" v-text="proTeams.length"></span>
            </div>
          </div>
          <div class="right teams">
            <span class="label btn btn-label team"
              v-for="team in tempTeams" 
              :key="team.id" v-text="team.name" 
              @click="chose(team)">
            </span>
          </div>
        </div>
        
      </div>
      <div class="drawer-footer">
        <div class="drawer-footer-btn drawer-footer-btn-grey" @click="close()">取消</div>
      </div>
    </div>

    <DrawerTeam :teamNameOut="searchKey"  v-if="isShowDrawerTeam" @close="isShowDrawerTeam=false" @choseTeam="chose"></DrawerTeam>
  </div>
</template>

<script>
import DrawerTeam from '@/components/drawer/DrawerTeam';
export default {
    name: "Drawer",
    components: {
      DrawerTeam
    },
    props: {
      height:String
    },
    mounted(){
      this.initTeams();
    },
    computed: {
      tempTeams(){
        if(this.navSelectedItem=='teams'){
          return this.teams;
        }else if(this.navSelectedItem=='proTeams'){
          return this.proTeams;
        }else{
          return [];
        }
      },
      teams(){
        return this.$store.state.teams
        .filter(team=>!team.isPro)
        .filter(team=>new RegExp(this.searchKey).test(team.name))
        .sort((a,b)=>{
          return b.power-a.power;
        });
      },
      proTeams(){
        return this.$store.state.teams
        .filter(team=>team.isPro)
        .filter(team=>new RegExp(this.searchKey).test(team.name))
        .sort((a,b)=>{
          return b.power-a.power;
        });
      },
    },
    data() {
      return {
        visible:true,
        searchKey:'',
        isShowDrawerTeam:false,
        navSelectedItem:'teams'
      };
    },
    methods: {
      close(){
        this.visible=false;
        setTimeout(()=>{
          this.$emit('close');
        },300);
      },
      chose(team){
        this.$emit('choseTeam',team);
        this.close();
      },
      initTeams(){
        this.loading=true;
        if(this.teams.length==0||this.proTeams.length==0){
           this.$store.dispatch('listTeams').then(() => {
            this.loading = false;
          }).catch(() => {
            that.$toast({
              showCancel:false,
              t1:'错误',//弹窗的标题
              t2: '无法请求车队数据',//弹窗的内容
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
          height: 16rem;
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
        text-align: center;
        color: @c2;
    }
  }
  
}

</style>

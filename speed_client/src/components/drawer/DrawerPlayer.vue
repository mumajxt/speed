<template>
  <div class="mask-wrapper animate__animated" :class="visible?'animate__fadeIn':'animate__fadeOut'" @click="close()" @click.stop>
    <div class="drawer animate__animated" :class="visible?'animate__fadeInUp':'animate__fadeOutDown'" @click.stop>
      <div class="drawer-header">
        <h2 v-if="playerOut">修改车手</h2>
        <h2 v-else>新增车手</h2>
      </div>
      <div class="drawer-body">
        <form class="attribute-list">
          <div class="attribute-list-item name">
            <label>名称<span v-show="!player.name" class="red">*</span></label>
            <div class="value input-wrapper">
              <input placeholder="输入车手名称" type="text" v-model="player.name">
            </div>
          </div>
          <div class="attribute-list-item team">
            <label>车队</label>
             <div class="value input-wrapper  input-wrapper-select">
              <input readonly @click="isShowTeamDrawer=true" placeholder="选择车队" type="text" v-model="team.name">
              <div class="input-icon" @click="player.team?player.team=null:isShowTeamDrawer=true">
                <i class="iconfont" :class="player.team?'iconclose':'iconxiala'"></i>
              </div>
            </div>
            
          </div>
          <div class="attribute-list-item pro">
            <label>职业</label>
            <div class="switch" :class="player.isPro == true?'true':'false'" @click="player.isPro = !player.isPro">
              <span></span>
              <span></span>
            </div>
          </div>
        </form>
      </div>
      <div class="drawer-footer">
        
        <div class="drawer-footer-btn drawer-footer-btn-grey" @click="close()">取消</div>
        <span class="divider"></span>
        <div v-if="playerOut" class="drawer-footer-btn drawer-footer-btn-yellow" @click="updatePlayer()">修改</div>
        <div v-else class="drawer-footer-btn drawer-footer-btn-yellow" @click="savePlayer()">添加</div>
      </div>
    </div>

    <DrawerChoseTeam v-if="isShowTeamDrawer" @close="isShowTeamDrawer=false" @choseTeam="choseTeam"></DrawerChoseTeam>
  </div>
</template>

<script>
import DrawerChoseTeam from '@/components/drawer/chose/DrawerChoseTeam';
export default {
    name: "DrawerPlayer",
    components: {
      DrawerChoseTeam
    },
    props: {
      playerOut:Object,
      playerNameOut:String
    },
    mounted(){
      
    },
    watch: {
      playerOut: {
        immediate: true,
        handler(value) {
          if(value){
            this.player = JSON.parse(JSON.stringify(value));
          }else{
            this.player={
              id:'',
              name:'',
              team:null,
              isPro:false
            }
          }
        },
      },
      playerNameOut:function(val){
        if(val){
          this.player.name=val;
        }
      }
    },
    computed: {
      
      team(){
        if(this.player.team){
          return this.player.team;
        }else{
          return {
            id:'',
            name:''
          }
        }
      }
    },
    data() {
      return {
        visible:true,
        player:null,
        isShowTeamDrawer:false,
        loading:false
      };
    },
    methods: {
      close(){
        this.visible=false;
        setTimeout(()=>{
          this.$emit('close');
        },300);
      },
      choseTeam(team){
        this.player.team = team;
      },
      verify(player){
        if(!player.name){
          this.$toast({
            showCancel:false,
            t1:'提示',//弹窗的标题
            t2:'请输入车手名称',//弹窗的内容
            type:'warning'
          });
          return false;
        }else{
          return true;
        }
      },
      savePlayer(){
        if(this.verify(this.player)){
          let postPlayer = {
            name:this.player.name,
            teamId:this.player.team?this.player.team.id:'',
            isPro:this.player.isPro
          }
          let data = this.$qs.stringify(postPlayer);
          let that = this;
          this.loading = true;
          this.$axios({
            method:'post',
            url:'/api/player',
            data: data
          }).then((response) =>{
            console.log('返回数据：↓');
            console.log(response.data);
            let info = response.data;
            if(info.flag){
              that.$toast({
                showCancel:false,
                t1:'提示',//弹窗的标题
                t2:'添加成功',//弹窗的内容
                type:'success'
              });
              that.$store.dispatch('listPlayers');
              that.$emit('chosePlayer',info.data);
              that.close();
            }else{
              that.$toast({
                showCancel:false,
                t1:'提示',//弹窗的标题
                t2: info.errorMsg,//弹窗的内容
                type:'error'
              });
            }
            that.loading = false;
          }).catch((error) => {
            that.loading = false;
            that.$toast({
              showCancel:false,
              t1:'提示',//弹窗的标题
              t2: error,//弹窗的内容
              type:'error'
            });
          });
        }
      },
      updatePlayer(){
        if(this.verify(this.player)){
          let postPlayer = {
            id:this.player.id,
            name:this.player.name,
            teamId:this.player.team?this.player.team.id:'',
            isPro:this.player.isPro
          }
          let data = this.$qs.stringify(postPlayer);
          let that = this;
          this.loading = true;
          this.$axios({
              method:'put',
              url:'/api/player',
              data: data
          }).then((response) =>{
              let info = response.data;     
              if(info.flag){
                that.$toast({
                  showCancel:false,
                  t1:'成功',//弹窗的标题
                  t2:'修改车手成功',//弹窗的内容
                  type:'success'
                });
                that.$store.dispatch('listPlayers');          
              }else{
                that.$toast({
                  showCancel:false,
                  t1:'提示',//弹窗的标题
                  t2: info.errorMsg,//弹窗的内容
                  type:'error'
                });
              }
              that.loading = false;
              that.close();
          }).catch((error) => {
            that.loading = false;
            that.$toast({
              showCancel:false,
              t1:'提示',//弹窗的标题
              t2: error,//弹窗的内容
              type:'error'
            });
          });
        }
      },
    }
};
</script>
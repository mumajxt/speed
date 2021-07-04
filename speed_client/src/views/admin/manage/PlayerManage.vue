<template>
  <div id="player_manage">
    <div class="manage-container card">
      <div class="nav1">
        <div class="nav1-items">
          <div class="nav1-item" :class="nav1SelectedItem=='player'?'nav1-item-selected':''" @click="nav1SelectedItem='player'">
            <h3>车手</h3>
          </div>
          <div class="nav1-item" :class="nav1SelectedItem=='team'?'nav1-item-selected':''"  @click="nav1SelectedItem='team'">
            <h3>车队</h3>
          </div>
        </div>

         <div class="input-wrapper nav1-input">
          <div class="input-icon">
            <i class="iconfont iconsousuo"></i>
          </div>
          <input type="text" placeholder="搜索地图车手/车队"  v-model="searchKey">
          <span class="input-icon">
            <i v-if="searchKey" class="iconfont iconclose" @click="searchKey=''"></i>
          </span>
        </div>

        <div v-show="nav1SelectedItem=='player'" @click="savePlayer()" class="nav1-btn btn btn-yellow">
          <span class="icon iconfont iconclose rotate45"></span>
        </div>
        <div v-show="nav1SelectedItem=='team'" @click="saveTeam()" class="nav1-btn btn btn-yellow">
          <span class="icon iconfont iconclose rotate45"></span>
        </div>

      </div>
      <div class="nav2">
        <div class="nav2-player nav2-items" v-if="nav1SelectedItem=='player'">
          <div class="btn-sm btn-label nav2-item" :class="nav2PlayerSelectedItem=='all'?'btn-label-yellow bold':'btn-grey'" @click="nav2PlayerSelectedItem='all'">
            <span>全部</span>
            <span class="count" v-text="playersAll.length"></span>
          </div>
          <div class="btn-sm btn-label nav2-item" :class="nav2PlayerSelectedItem=='players'?'btn-label-yellow bold':'btn-grey'" @click="nav2PlayerSelectedItem='players'">
            <span>普通车手</span>
            <span class="count" v-text="players.length"></span>
          </div>
          <div class="btn-sm btn-label nav2-item" :class="nav2PlayerSelectedItem=='proPlayers'?'btn-label-yellow':'btn-grey'"  @click="nav2PlayerSelectedItem='proPlayers'">
            <span>职业选手</span>
            <span class="count" v-text="proPlayers.length"></span>
          </div>
        </div>
        <div class="nav2-team  nav2-items" v-if="nav1SelectedItem=='team'">
          <div class="btn-sm btn-label nav2-item" :class="nav2TeamSelectedItem=='all'?'btn-label-yellow bold':'btn-grey'" @click="nav2TeamSelectedItem='all'">
            <span>全部</span>
            <span class="count" v-text="teamsAll.length"></span>
          </div>
          <div class="btn-sm btn-label nav2-item" :class="nav2TeamSelectedItem=='teams'?'btn-label-yellow bold':'btn-grey'" @click="nav2TeamSelectedItem='teams'">
            <span>普通车队</span>
            <span class="count" v-text="teams.length"></span>
          </div>
          <div class="btn-sm btn-label nav2-item" :class="nav2TeamSelectedItem=='proTeams'?'btn-label-yellow':'btn-grey'"  @click="nav2TeamSelectedItem='proTeams'">
            <span>职业战队</span>
            <span class="count" v-text="proTeams.length"></span>
          </div>
        </div>
      </div>

      
      <div class="table" v-show="nav1SelectedItem=='player'">
        <div class="th">
          <div class="id th-btn"
          :class="{'yellow':orderBy=='idUp'||orderBy=='idDown'}" 
          @click="clickId()">
            <span>ID</span>
            <i class="iconfont iconxiangxia" 
            :class="orderBy=='idUp'?'rotate180':(orderBy=='idDown'?'yellow':'grey2')">
            </i>
          </div>
          <div class="name th-btn"
          :class="{'yellow':orderBy=='nameUp'||orderBy=='nameDown'}" 
          @click="clickName()">
            <span>名称</span>
            <i class="iconfont iconxiangxia" 
            :class="orderBy=='nameUp'?'rotate180':(orderBy=='nameDown'?'yellow':'grey2')">
            </i>
          </div>
          <div class="is_pro">职业</div>
          <div class="team_name">车队</div>
          <div class="operation">操作</div>
        </div>
        <div class="tr" v-for="player in tempPlayers" :key="player.id">
          <div class="id" v-text="player.id"></div>
          <div class="name" >
            <router-link :to="'/rank/player/'+player.id" v-text="player.name"></router-link>
          </div>
          <div class="is_pro">
            <span class="iconfont" :class="player.isPro?'iconchecked green':'iconclose grey'"></span>
          </div>
          <div class="team_name">
            <router-link v-if="player.team" :to="'/rank/team/'+player.team.id" v-text="player.team.name"></router-link>
            <span v-else class="empty">暂无</span>
          </div>
           <div class="operation" @click.stop>
            <span @click="updatePlayer(player)" class="btn btn-yellow iconfont iconupdate"></span>
            <span class="btn btn-red  iconfont icondelete" @click="readyToDeletePlayer(player)"></span>
          </div>
        </div>
      </div>

      <div class="table" v-show="nav1SelectedItem=='team'">
        <div class="th">
          <div class="id th-btn"
          :class="{'yellow':orderBy=='idUp'||orderBy=='idDown'}" 
          @click="clickId()">
            <span>ID</span>
            <i class="iconfont iconxiangxia" 
            :class="orderBy=='idUp'?'rotate180':(orderBy=='idDown'?'yellow':'grey2')">
            </i>
          </div>
          <div class="name th-btn"
          :class="{'yellow':orderBy=='nameUp'||orderBy=='nameDown'}" 
          @click="clickName()">
            <span>名称</span>
            <i class="iconfont iconxiangxia" 
            :class="orderBy=='nameUp'?'rotate180':(orderBy=='nameDown'?'yellow':'grey2')">
            </i>
          </div>
          <div class="is_pro">职业</div>
          <div class="operation">操作</div>
        </div>
        <div class="tr" v-for="team in tempTeams" :key="team.id">
          <div class="id" v-text="team.id"></div>
          <div class="name" >
            <router-link :to="'/rank/team/'+team.id" v-text="team.name"></router-link>
          </div>
          <div class="is_pro">
            <span class="iconfont" :class="team.isPro?'iconchecked green':'iconclose grey'"></span>
          </div>
           <div class="operation" @click.stop>
            <span @click="updateTeam(team)" class="btn btn-yellow iconfont iconupdate"></span>
            <span @click="readyToDeleteTeam(team)" class="btn btn-red  iconfont icondelete"></span>
          </div>
        </div>
      </div>

    </div>
    

    <DrawerPlayer :playerOut="player" v-if="isShowDrawerPlayer" @close="isShowDrawerPlayer=false"></DrawerPlayer>
    <DrawerTeam :teamOut="team" v-if="isShowDrawerTeam" @close="isShowDrawerTeam=false"></DrawerTeam>

  
  </div>
</template>

<script>
export default {
    name: "playerManage",
    components: {
      DrawerPlayer:() => import('@/components/drawer/DrawerPlayer'),
      DrawerTeam:() => import('@/components/drawer/DrawerTeam'),
    },
    mounted(){
      this.listPlayers();
      this.listTeams();
    },
    computed: {
      playersAll(){
        return this.$store.state.players
        .filter(player=>new RegExp(this.searchKey).test(player.name))
        .sort(this.orderBySort);
      },
      teamsAll(){
        return this.$store.state.teams
        .filter(team=>new RegExp(this.searchKey).test(team.name))
        .sort(this.orderBySort);
      },
      tempPlayers(){
        if(this.nav2PlayerSelectedItem=='all'){
          return this.playersAll;
        }else if(this.nav2PlayerSelectedItem=='players'){
          return this.players;
        }else if(this.nav2PlayerSelectedItem=='proPlayers'){
          return this.proPlayers;
        }else {
          return [];
        }
      },
      tempTeams(){
        if(this.nav2TeamSelectedItem=='all'){
          return this.teamsAll;
        }else if(this.nav2TeamSelectedItem=='teams'){
          return this.teams;
        }else if(this.nav2TeamSelectedItem=='proTeams'){
          return this.proTeams;
        }else{
          return [];
        }
      },
      players(){
        return this.playersAll
        .filter(player=>!player.isPro);
      },
      proPlayers(){
        return this.playersAll
        .filter(player=>player.isPro);
      } ,
      teams(){
        return this.teamsAll
        .filter(team=>!team.isPro);
      },
      proTeams(){
        return this.teamsAll
        .filter(team=>team.isPro);
      }
    },
    data() {
      return {
        loading:false,
        searchKey:'',
        player:null,
        team:null,
        isShowDrawerPlayer:false,
        isShowDrawerTeam:false,
        orderBy:'idDown',
        nav1SelectedItem:'player',
        nav2PlayerSelectedItem:'all',
        nav2TeamSelectedItem:'all',
      };
    },
    methods: {
       orderBySort(a,b){
        switch(this.orderBy){
          case 'idUp':return a.id - b.id;
          case 'idDown':return b.id - a.id;
          case 'nameUp' : return b.name.localeCompare(a.name, 'zh-Hans-CN', {sensitivity: 'accent'});
          case 'nameDown' : return a.name.localeCompare(b.name, 'zh-Hans-CN', {sensitivity: 'accent'});
        }
      },
      clickId(){
        if(this.orderBy=='idDown'){
          this.orderBy='idUp'
        }else{
          this.orderBy='idDown'
        }
      },
      clickName(){
        if(this.orderBy=='nameDown'){
          this.orderBy='nameUp'
        }else{
          this.orderBy='nameDown'
        }
      },
      updatePlayer(player){
        this.player=player;
        this.isShowDrawerPlayer=true;
      },
      updateTeam(team){
        this.team=team;
        this.isShowDrawerTeam=true;
      },
      savePlayer(){
        this.player=null;
        this.isShowDrawerPlayer=true;
      },
      saveTeam(){
        this.team=null;
        this.isShowDrawerTeam=true;
      },
      listPlayers(){
        if(this.players.length==0&&this.proPlayers.length==0){
          let that = this;
          this.$store.dispatch('listPlayers').then(() => {
            that.loading = false;
          }).catch(() => {
            that.$toast({
              showCancel:false,
              t1:'错误',//弹窗的标题
              t2: '无法请求选手数据',//弹窗的内容
              type:'error'
            });
          });
        }
      },
      listTeams(){
        if(this.teams.length==0&&this.proTeams.length==0){
          let that = this;
          this.$store.dispatch('listTeams').then(() => {
            that.loading = false;
          }).catch(() => {
            that.$toast({
              showCancel:false,
              t1:'错误',//弹窗的标题
              t2: '无法请求车队数据',//弹窗的内容
              type:'error'
            });
          });
        }
      },
      // 准备删除
      readyToDeletePlayer(player){
        this.$toast({
          showCancel:true,
          t1:'提示',//弹窗的标题
          t2: '确定要<span class="red">删除</span>车手<b>'+player.name+'</b>?',//弹窗的内容
          type:'warning'
        }).then(()=>{
          this.deletePlayer(player);
        }).catch(()=>{
          console.log('取消删除');
        });
      },

      // 删除选手
      deletePlayer(player){
        let that = this;
        this.$axios({
            method:'delete',
            url:'/api/player/'+player.id,
        }).then((response) =>{
            console.log('返回数据：↓');
            console.log(response.data);
            let info = response.data;
            if(info.flag){
              that.$toast({
                showCancel:false,
                t1:'提示',//弹窗的标题
                t2: '删除成功',//弹窗的内容
                type:'success'
              });
              that.$store.dispatch('listPlayers');
            }else{
              that.$toast({
                showCancel:false,
                t1:'提示',//弹窗的标题
                t2: info.msg,//弹窗的内容
                type:'error'
              });
            }
        }).catch((error) => {
          that.$toast({
            showCancel:false,
            t1:'提示',//弹窗的标题
            t2: '出错了！',//弹窗的内容
            type:'error'
          });
        });
      },


      // 准备删除
      readyToDeleteTeam(team){
        this.$toast({
          showCancel:true,
          t1:'提示',//弹窗的标题
          t2: '确定要<span class="red">删除</span>车队<b>'+team.name+'</b>?',//弹窗的内容
          type:'warning'
        }).then(()=>{
          this.deleteTeam(team);
        }).catch(()=>{
          console.log('取消删除');
        });
      },

      // 删除车队
      deleteTeam(team){
        let that = this;
        this.$axios({
            method:'delete',
            url:'/api/team/'+team.id,
        }).then((response) =>{
            console.log('返回数据：↓');
            console.log(response.data);
            let info = response.data;
            if(info.flag){
              that.$toast({
                showCancel:false,
                t1:'提示',//弹窗的标题
                t2: '删除成功',//弹窗的内容
                type:'success'
              });
              that.$store.dispatch('listTeams');
            }else{
              that.$toast({
                showCancel:false,
                t1:'提示',//弹窗的标题
                t2: info.msg,//弹窗的内容
                type:'error'
              });
            }
        }).catch((error) => {
          that.$toast({
            showCancel:false,
            t1:'提示',//弹窗的标题
            t2: '出错了！',//弹窗的内容
            type:'error'
          });
        });
      },
    }
};
</script>

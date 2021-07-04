<template>
  <div id="track_manage">
    <div class="manage-container card">
      <div class="nav1">
        <div class="nav1-items">
          <div class="nav1-item nav1-item-selected">
            <h3>地图</h3>
          </div>
        </div>

        <div class="input-wrapper nav1-input">
          <div class="input-icon">
            <i class="iconfont iconsousuo"></i>
          </div>
          <input type="text" placeholder="搜索地图"  v-model="searchKey">
          <span class="input-icon">
            <i v-if="searchKey" class="iconfont iconclose" @click="searchKey=''"></i>
          </span>
        </div>
        <div @click="saveTrack()" class="nav1-btn btn btn-yellow">
          <span class="icon iconfont iconclose rotate45"></span>
        </div>
      </div>
      <div class="nav2">
        <div class="nav2-track nav2-items">
          <div class="btn-sm btn-label nav2-item" :class="nav2TrackSelectedItem=='all'?'btn-label-yellow bold':'btn-grey'" @click="nav2TrackSelectedItem='all'">
            <span>全部</span>
            <span class="count" v-text="tracksAll.length"></span>
          </div>
          <div class="btn-sm btn-label nav2-item" :class="nav2TrackSelectedItem=='news'?'btn-label-yellow bold':'btn-grey'" @click="nav2TrackSelectedItem='news'">
            <span>新图</span>
            <span class="count" v-text="news.length"></span>
          </div>
          <div class="btn-sm btn-label nav2-item" :class="nav2TrackSelectedItem=='s7s'?'btn-label-yellow':'btn-grey'"  @click="nav2TrackSelectedItem='s7s'">
            <span>7星</span>
            <span class="count" v-text="s7s.length"></span>
          </div>
          <div class="btn-sm btn-label nav2-item" :class="nav2TrackSelectedItem=='s6s'?'btn-label-yellow':'btn-grey'"  @click="nav2TrackSelectedItem='s6s'">
            <span>6星</span>
            <span class="count" v-text="s6s.length"></span>
          </div>
          <div class="btn-sm btn-label nav2-item" :class="nav2TrackSelectedItem=='s5s'?'btn-label-yellow':'btn-grey'"  @click="nav2TrackSelectedItem='s5s'">
            <span>5星</span>
            <span class="count" v-text="s5s.length"></span>
          </div>
          <div class="btn-sm btn-label nav2-item" :class="nav2TrackSelectedItem=='s4s'?'btn-label-yellow':'btn-grey'"  @click="nav2TrackSelectedItem='s4s'">
            <span>4星</span>
            <span class="count" v-text="s4s.length"></span>
          </div>
          <div class="btn-sm btn-label nav2-item" :class="nav2TrackSelectedItem=='s3s'?'btn-label-yellow':'btn-grey'"  @click="nav2TrackSelectedItem='s3s'">
            <span>3星</span>
            <span class="count" v-text="s3s.length"></span>
          </div>
          <div class="btn-sm btn-label nav2-item" :class="nav2TrackSelectedItem=='s2s'?'btn-label-yellow':'btn-grey'"  @click="nav2TrackSelectedItem='s2s'">
            <span>2星</span>
            <span class="count" v-text="s2s.length"></span>
          </div>
          <div class="btn-sm btn-label nav2-item" :class="nav2TrackSelectedItem=='s1s'?'btn-label-yellow':'btn-grey'"  @click="nav2TrackSelectedItem='s1s'">
            <span>1星</span>
            <span class="count" v-text="s1s.length"></span>
          </div>
          <div class="btn-sm btn-label nav2-item" :class="nav2TrackSelectedItem=='s99s'?'btn-label-yellow':'btn-grey'"  @click="nav2TrackSelectedItem='s99s'">
            <span>拉力</span>
            <span class="count" v-text="s99s.length"></span>
          </div>
          <div class="btn-sm btn-label nav2-item" :class="nav2TrackSelectedItem=='seas'?'btn-label-yellow':'btn-grey'"  @click="nav2TrackSelectedItem='seas'">
            <span>航海</span>
            <span class="count" v-text="seas.length"></span>
          </div>
          <div class="btn-sm btn-label nav2-item" :class="nav2TrackSelectedItem=='olds'?'btn-label-yellow':'btn-grey'"  @click="nav2TrackSelectedItem='olds'">
            <span>怀旧</span>
            <span class="count" v-text="olds.length"></span>
          </div>
        </div>
      </div>

      
      <div class="table">
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
          <div class="stars">星级</div>
          <div class="new">新图</div>
          <div class="league">联赛</div>
          <div class="sea">航海</div>
          <div class="old">怀旧</div>
          <div class="date">上线日期</div>
          <div class="operation">操作</div>
        </div>

        <div class="tr" v-for="track in tempTracks" :key="track.id">
          <div class="id" v-text="track.id"></div>
          <div class="name" >
            <router-link :to="'/rank/track/'+track.id" v-text="track.name"></router-link>
          </div>
          <div class="stars yellow" v-text="track.stars==99?'拉':track.stars"></div>
          <div class="new icon iconfont"  :class="track.isNew?'iconchecked green':'iconclose grey2'"></div>
          <div class="league icon iconfont"  :class="track.isLeague?'iconchecked yellow':'iconclose grey2'"></div>
          <div class="sea icon iconfont"  :class="track.isSea?'iconchecked blue':'iconclose grey2'"></div>
          <div class="old icon iconfont"  :class="track.isOld?'iconchecked grey':'iconclose grey2'"></div>
          <div class="date" v-text="track.date"></div>
          <div class="operation" @click.stop>
            <span @click="updateTrack(track)" class="btn btn-yellow iconfont iconupdate"></span>
            <span class="btn btn-red  iconfont icondelete" @click="readyToDeleteTrack(track)"></span>
          </div>
        </div>
      </div>
    </div>
    

    <DrawerTrack :trackOut="track" v-if="isShowDrawerTrack" @close="isShowDrawerTrack=false"></DrawerTrack>
  
  </div>
</template>

<script>
import DrawerTrack from '@/components/drawer/DrawerTrack';
export default {
    name: "trackManage",
    components: {
      DrawerTrack,
    },
    mounted(){
      this.listTracks();
    },
    computed: {
      tracksAll(){
        return this.$store.state.tracks
        .filter(track=>new RegExp(this.searchKey).test(track.name))
        .sort(this.orderBySort);
      },
      tempTracks(){
        switch(this.nav2TrackSelectedItem){
          case 'all':return this.tracksAll;
          case 'news':return this.news;
          case 's7s':return this.s7s;
          case 's6s':return this.s6s;
          case 's5s':return this.s5s;
          case 's4s':return this.s4s;
          case 's3s':return this.s3s;
          case 's2s':return this.s2s;
          case 's1s':return this.s1s;
          case 's99s':return this.s99s;
          case 'seas':return this.seas;
          case 'olds':return this.olds;
          default:return [];
        }
      },
      news(){
        return this.tracksAll
        .filter(track=>track.isNew);
      },
      s7s(){
        return this.tracksAll
        .filter(track=>track.stars==7);
      },
      s6s(){
        return this.tracksAll
        .filter(track=>track.stars==6);
      },
      s5s(){
        return this.tracksAll
        .filter(track=>track.stars==5);
      },
      s4s(){
        return this.tracksAll
        .filter(track=>track.stars==4);
      },
      s3s(){
        return this.tracksAll
        .filter(track=>track.stars==3);
      },
      s2s(){
        return this.tracksAll
        .filter(track=>track.stars==2);
      },
      s1s(){
        return this.tracksAll
        .filter(track=>track.stars==1);
      },
      s99s(){
        return this.tracksAll
        .filter(track=>track.stars==99);
      },
      seas(){
        return this.tracksAll
        .filter(track=>track.isSea);
      },
      olds(){
        return this.tracksAll
        .filter(track=>track.isOld);
      },
    },
    data() {
      return {
        loading:false,
        searchKey:'',
        track:null,
        skin:null,
        isShowDrawerTrack:false,
        orderBy:'idDown',
        nav2TrackSelectedItem:'all',
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
      updateTrack(track){
        this.track=track;
        this.isShowDrawerTrack=true;
      },
      saveTrack(){
        this.track=null;
        this.isShowDrawerTrack=true;
      },
      listTracks(){
        if(this.tracksAll.length==0){
          let that = this;
          this.$store.dispatch('listTracks').then(() => {
            that.loading = false;
          }).catch(() => {
            that.$toast({
              showCancel:false,
              t1:'错误',//弹窗的标题
              t2: '无法请求赛车数据',//弹窗的内容
              type:'error'
            });
          });
        }
      },
      // 准备删除
      readyToDeleteTrack(track){
        this.$toast({
          showCancel:true,
          t1:'提示',//弹窗的标题
          t2: '确定要<span class="red">删除</span>地图<b>'+track.name+'</b>?',//弹窗的内容
          type:'warning'
        }).then(()=>{
          this.deleteTrack(track);
        }).catch(()=>{
          console.log('取消删除');
        });
      },

      // 删除赛车
      deleteTrack(track){
        let that = this;
        this.$axios({
            method:'delete',
            url:'/api/track/'+track.id,
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
              that.$store.dispatch('listTracks');
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
            t2: '服务器出错！',//弹窗的内容
            type:'error'
          });
        });
      }
    }
};
</script>

<style lang="less" scoped>
.name{
  flex: 2 !important;
}
@media all and (max-width: 768px){
  .date{
    display: none;
  }
}
</style>

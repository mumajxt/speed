<template>
  <div class="mask-wrapper animate__animated" :class="visible?'animate__fadeIn':'animate__fadeOut'"  @click="close()" @click.stop>
    <div class="drawer animate__animated" :class="visible?'animate__fadeInUp':'animate__fadeOutDown'" @click.stop>
      <div class="drawer-header">
        <h2 v-if="trackOut">修改地图</h2>
        <h2 v-else>新增地图</h2>
      </div>
      <div class="drawer-body">
        <form class="attribute-list">

          <div class="attribute-list-item">
            <label>名称<span v-show="!track.name" class="red">*</span></label>
            <div class="value input-wrapper  input-wrapper-select">
              <input class="value" placeholder="输入地图名称" type="text" v-model="track.name">
            </div>
          </div>
          <div class="attribute-list-item">
            <label>难度<span v-show="!track.stars" class="red">*</span></label>
            <div class="value input-wrapper  input-wrapper-select">
              <input class="value" readonly @click="isShowStarsDrawer=true" placeholder="选择难度" type="text" v-model="trackStars">
              <div class="input-icon">
                <i class="iconfont iconxiala"></i>
              </div>
            </div>
          </div>
          <div class="attribute-list-item">
            <label>新图</label>
            <div class="switch green-switch" :class="track.isNew == true?'true':'false'" @click="track.isNew = !track.isNew">
              <span></span>
              <span></span>
            </div>
          </div>
          <div class="attribute-list-item">
            <label>联赛</label>
            <div class="switch yellow-switch" :class="track.isLeague == true?'true':'false'" @click="track.isLeague = !track.isLeague">
              <span></span>
              <span></span>
            </div>
          </div>
          <div class="attribute-list-item">
            <label>航海</label>
            <div class="switch blue-switch" :class="track.isSea == true?'true':'false'" @click="track.isSea = !track.isSea">
              <span></span>
              <span></span>
            </div>
          </div>
          
          <div class="attribute-list-item">
            <label>怀旧</label>
            <div class="switch grey-switch" :class="track.isOld == true?'true':'false'" @click="track.isOld = !track.isOld">
              <span></span>
              <span></span>
            </div>
          </div>

          <div class="attribute-list-item">
            <label>日期<span v-show="!track.date" class="red">*</span></label>         
            <div class="value input-wrapper">
              <input class="value" type="text" placeholder="0000-00-00" v-model="track.date">
            </div>
          </div>
        </form>
      </div>
      <div class="drawer-footer">
        <div class="drawer-footer-btn drawer-footer-btn-grey" @click="close()">取消</div>
        <span class="divider"></span>
        <div v-if="trackOut" class="drawer-footer-btn drawer-footer-btn-yellow" @click="updateTrack">修改</div>
        <div v-else class="drawer-footer-btn drawer-footer-btn-yellow" @click="saveTrack">添加</div>
      </div>
    </div>

    
    <Drawer
    :visible.sync="isShowStarsDrawer"
    :height="'auto'"
    @close="isShowStarsDrawer=false"
    class="isShowStarsDrawer">
      <template v-slot:header>
        <h2>选择地图星级</h2>
      </template>

      <template v-slot:body>
        <div class="drawer-labels">
          <div class="drawer-label-wrapper"><div class="drawer-label" @click="track.stars=7;isShowStarsDrawer=false" :class="track.stars==7?'active':''">7星</div></div>
          <div class="drawer-label-wrapper"><div class="drawer-label" @click="track.stars=6;isShowStarsDrawer=false" :class="track.stars==6?'active':''">6星</div></div>
          <div class="drawer-label-wrapper"><div class="drawer-label" @click="track.stars=5;isShowStarsDrawer=false" :class="track.stars==5?'active':''">5星</div></div>
          <div class="drawer-label-wrapper"><div class="drawer-label" @click="track.stars=4;isShowStarsDrawer=false" :class="track.stars==4?'active':''">4星</div></div>
          <div class="drawer-label-wrapper"><div class="drawer-label" @click="track.stars=3;isShowStarsDrawer=false" :class="track.stars==3?'active':''">3星</div></div>
          <div class="drawer-label-wrapper"><div class="drawer-label" @click="track.stars=2;isShowStarsDrawer=false" :class="track.stars==2?'active':''">2星</div></div>
          <div class="drawer-label-wrapper"><div class="drawer-label" @click="track.stars=1;isShowStarsDrawer=false" :class="track.stars==1?'active':''">1星</div></div>
          <div class="drawer-label-wrapper"><div class="drawer-label" @click="track.stars=99;isShowStarsDrawer=false" :class="track.stars==99?'active':''">拉力</div></div>
        </div>
      </template>
      <template v-slot:footer>
        <div class="drawer-footer-btn drawer-footer-btn-grey" @click="close()">取消</div>
      </template>
    </Drawer>
  </div>
</template>

<script>
import Drawer from '@/components/notice/Drawer';
export default {
  name: "DrawerTrack",
  components: {
    Drawer
  },
  props: {
    trackOut:Object,
    trackNameOut:String
  },
  watch: {
    trackOut: {
      immediate: true,
      handler(value) {
        if(value){
          this.track = JSON.parse(JSON.stringify(value));
        }else{
          this.track={
            id:'',
            name:'',
            stars:'',
            isNew:true,
            isSea:false,
            isLeague:true,
            isOld:false,
            date: new Date().format('yyyy-MM-dd')
          }
        }
      },
    },
    trackNameOut:function(val){
      if(val){
        this.track.name=val;
      }
    }
  },
  computed: {
    trackStars(){
      if(this.track.stars==99){
        return '拉力';
      }else{
        return this.track.stars;
      }
    }
  },
  data() {
    return {
      visible:true,
      track:null,
      isShowStarsDrawer:false,
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
    saveTrack(){
      if(this.verify()){
        this.track.date = this.track.date.replace(/\./g,'-');
        let data = this.$qs.stringify(this.track);
        let that = this;
        this.loading = true;
        this.$axios({
          method:'post',
          url:'/api/track',
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
            that.$store.dispatch('listTracks');
            that.$emit('choseTrack',info.data);
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
        }).catch(() => {
          that.loading = false;
          that.$toast({
            showCancel:false,
            t1:'提示',//弹窗的标题
            t2: '服务器出错！',//弹窗的内容
            type:'error'
          });
        });
      }
    },
    updateTrack(){
      if(this.verify()){
        let data = this.$qs.stringify(this.track);
        let that = this;
        this.loading = true;
        this.$axios({
            method:'put',
            url:'/api/track',
            data: data
        }).then((response) =>{
            let info = response.data;
            if(info.flag){
              that.$toast({
                showCancel:false,
                t1:'成功',//弹窗的标题
                t2:'修改地图成功',//弹窗的内容
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
            that.loading = false;
            that.close();
        }).catch(() => {
          that.loading = false;
          that.$toast({
            showCancel:false,
            t1:'提示',//弹窗的标题
            t2: '服务器出错！',//弹窗的内容
            type:'error'
          });
        });
      }
    },
    verify(){
      if(!this.track.name){
        this.$toast({
          showCancel:false,
          t1:'提示',//弹窗的标题
          t2:'请输入地图名称',//弹窗的内容
          type:'warning'
        });
        return false;
      }else if(!this.track.stars){
        this.$toast({
          showCancel:false,
          t1:'提示',//弹窗的标题
          t2:'请选择地图难度',//弹窗的内容
          type:'warning'
        });
        return false;
      }else if(!this.track.date){
        this.$toast({
          showCancel:false,
          t1:'提示',//弹窗的标题
          t2:'请输入上线日期',//弹窗的内容
          type:'warning'
        });
        return false;
      }else if(!this.verifyDate(this.track.date)){
        this.$toast({
          showCancel:false,
          t1:'错误',//弹窗的标题
          t2:'日期格式有误',//弹窗的内容
          type:'error'
        });
        return false;
      }else{
        return true;
      }
    },
    verifyDate(date){
      return /^\d{4}[-\.]\d{1,2}[-\.]\d{1,2}$/.test(date) && new Date((new Date().format('yyyy-MM-dd'))) >= new Date(date);
    }
  }
};
</script>

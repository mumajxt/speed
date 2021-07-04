<template>
  <div class="mask-wrapper animate__animated" :class="visible?'animate__fadeIn':'animate__fadeOut'" @click="close()" @click.stop>
    <div class="drawer animate__animated" :class="visible?'animate__fadeInUp':'animate__fadeOutDown'" @click.stop>
      <div class="drawer-header">
        <h2>筛选地图</h2>
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
              全部<span class="count" v-text="tracksAll.length"></span>
            </div>
            <div class="nav-item"
              :class="navSelectedItem=='new'?'nav-item-selected':''"
              @click="navSelectedItem='new'">
              新图<span class="count" v-text="news.length"></span>
            </div>
            <div class="nav-item"
              :class="navSelectedItem=='s7'?'nav-item-selected':''"
              @click="navSelectedItem='s7'">
              7星<span class="count" v-text="s7s.length"></span>
            </div>
            <div class="nav-item"
              :class="navSelectedItem=='s6'?'nav-item-selected':''"
              @click="navSelectedItem='s6'">
              6星<span class="count" v-text="s7s.length"></span>
            </div>
            <div class="nav-item"
              :class="navSelectedItem=='s5'?'nav-item-selected':''"
              @click="navSelectedItem='s5'">
              5星<span class="count" v-text="s5s.length"></span>
            </div>
            <div class="nav-item"
              :class="navSelectedItem=='s4'?'nav-item-selected':''"
              @click="navSelectedItem='s4'">
              4星<span class="count" v-text="s4s.length"></span>
            </div>
            <div class="nav-item"
              :class="navSelectedItem=='s3'?'nav-item-selected':''"
              @click="navSelectedItem='s3'">
              3星<span class="count" v-text="s3s.length"></span>
            </div>
            <div class="nav-item"
              :class="navSelectedItem=='s2'?'nav-item-selected':''"
              @click="navSelectedItem='s2'">
              2星<span class="count" v-text="s2s.length"></span>
            </div>
            <div class="nav-item"
              :class="navSelectedItem=='s1'?'nav-item-selected':''"
              @click="navSelectedItem='s1'">
              1星<span class="count" v-text="s1s.length"></span>
            </div>

            <div class="nav-item"
              :class="navSelectedItem=='s99'?'nav-item-selected':''"
              @click="navSelectedItem='s99'">
              拉力<span class="count" v-text="s99s.length"></span>
            </div>

            <div class="nav-item"
              :class="navSelectedItem=='sea'?'nav-item-selected':''"
              @click="navSelectedItem='sea'">
              航海<span class="count" v-text="seas.length"></span>
            </div>

            <div class="nav-item"
              :class="navSelectedItem=='old'?'nav-item-selected':''"
              @click="navSelectedItem='old'">
              怀旧<span class="count" v-text="olds.length"></span>
            </div>


          </div>
          <div class="right tracks">
            <span class="label btn btn-label"
              :class="trackOut?(trackOut.id==track.id?'btn-label-yellow':''):''"
              v-for="track in tempTracks" 
              :key="track.id" v-text="track.name" 
              @click="choseTrack(track)">
            </span>
          </div>
        </div>
        
      </div>
      <div class="drawer-footer">
        <div class="drawer-footer-btn drawer-footer-btn-grey" @click="close()">取消</div>
        <span class="divider"></span>
        <div class="drawer-footer-btn drawer-footer-btn-red" @click="choseTrack(null)">清除筛选</div>
      </div>
    </div>

  </div>
</template>

<script>
export default {
    name: "UserDrawerChoseTrack",
    props: {
      trackOut:Object,
      type:String,
      pet:String,
      ecu:String
    },
    mounted(){
      this.initTracks();
    },
    watch: {
       type:function(){
        this.initTracks();
      },
      pet:function(){
        this.initTracks();
      },
      ecu:function(){
        this.initTracks();
      },
    },
    computed: {
      tracksAll(){
        return this.tracks
        .filter(track=>new RegExp(this.searchKey).test(track.name))
        .sort((a,b)=>{
          return b.power-a.power;
        });
      },
      tempTracks(){
        switch(this.navSelectedItem){
          case 'all':return this.tracksAll;
          case 'new':return this.news;
          case 's7':return this.s7s;
          case 's6':return this.s6s;
          case 's5':return this.s5s;
          case 's4':return this.s4s;
          case 's3':return this.s3s;
          case 's2':return this.s2s;
          case 's1':return this.s1s;
          case 's99':return this.s99s;
          case 'sea':return this.seas;
          case 'old':return this.olds;
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
        visible:true,
        searchKey:'',
        loading:false,
        tracks:[],
        navSelectedItem:'all'
      };
    },
    methods: {
      close(){
        this.visible=false;
        setTimeout(()=>{
          this.$emit('close');
        },300);
      },
      choseTrack(track){
        this.$emit('choseTrack',track);
        this.close();
      },
      initTracks(){
        this.loading=true;
        let params={
          type:this.type,
          pet:this.pet,
          ecu:this.ecu
        }
        this.$axios({
          method: "get",
          url: "/api/tracks",
          params: params
        }).then((response) => {
          let info = response.data;
          console.log("--------------------------");
          if(info.flag){
            console.log("返回筛选地图数据↓");
            console.log(info.data);
            this.tracks=info.data;
          }else{
            console.error(info.errorMsg);
          }
          console.log("--------------------------");
          this.loading=false;
        }).catch((error) => {
          this.$toast({
            showCancel:false,
            t1:'错误',//弹窗的标题
            t2: '无法获取赛道',//弹窗的内容
            type:'error'
          });
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
        height: 100%;
        .left{
          //height: 29.8rem;
          height: 100%;
          overflow-y: auto;
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
          height: 28.8rem;
          box-sizing: border-box;
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

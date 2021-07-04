<template>
  <div class="mask-wrapper animate__animated" :class="visible?'animate__fadeIn':'animate__fadeOut'" @click="close()" @click.stop>
    <div class="drawer animate__animated" :class="visible?'animate__fadeInUp':'animate__fadeOutDown'" @click.stop>
      <div class="drawer-header">
        <h2>选择地图</h2>
      </div>
      <div class="drawer-body">
         <div class="search-box">
          <span class="search icon iconfont iconsousuo"></span>
          <input placeholder="搜索" type="text" v-model="searchKey">
          <span class="close">
            <span v-if="searchKey" class="icon iconfont iconclose" @click="searchKey=''"></span>
          </span>
          <span class="btn btn-yellow" @click="isShowDrawerTrack=true">
            <span class="icon iconfont iconclose rotate45"></span>
            <span> 添加</span>
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
      </div>
    </div>

    <DrawerTrack :trackNameOut="searchKey" v-if="isShowDrawerTrack" @close="isShowDrawerTrack=false" @choseTrack="choseTrack"></DrawerTrack>
  </div>
</template>

<script>
export default {
    name: "DrawerChoseTrack",
    components: {
      DrawerTrack:() => import('@/components/drawer/DrawerTrack')
    },
    props: {
      trackOut:Object
    },
    mounted(){
      this.initTracks();
    },
    computed: {
      tracksAll(){
        return this.$store.state.tracks
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
        isShowDrawerTrack:false,
        navSelectedItem:'all',
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
      listTracks(){
        if(this.tracksAll.length==0){
          let that = this;
          this.$store.dispatch('listTracks').then(() => {
            that.loading = false;
          }).catch(() => {
            that.$toast({
              showCancel:false,
              t1:'错误',
              t2: '无法请求赛车数据',
              type:'error'
            });
          });
        }
      },
      choseTrack(track){
        this.$emit('choseTrack',track);
        this.close();
      },
      initTracks(){
        this.loading=true;
        if(this.tracksAll.length==0){
           this.$store.dispatch('listTracks').then(() => {
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
          height: 28.8rem;
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

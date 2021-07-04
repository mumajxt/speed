<template>
  <div class="mask-wrapper animate__animated" :class="visible?'animate__fadeIn':'animate__fadeOut'" @click="close()" @click.stop>
    <div class="drawer animate__animated" :class="visible?'animate__fadeInUp':'animate__fadeOutDown'" @click.stop>
      <div class="drawer-header">
        <h2>选择赛车皮肤</h2>
      </div>
      <div class="drawer-body">
         <div class="search-box">
          <span class="search icon iconfont iconsousuo"></span>
          <input placeholder="搜索" type="text" v-model="searchKey">
          <span class="close">
            <span v-if="searchKey" class="icon iconfont iconclose" @click="searchKey=''"></span>
          </span>
          <span class="btn btn-yellow" @click="isShowDrawerCarSkin=true">
            <span class="icon iconfont iconclose rotate45"></span>
            <span> 添加</span>
          </span>
        </div>
        <div class="container">
          <div class="right carSkins">
            <span class="label btn btn-label"
              v-for="carSkin in carSkinsAll" 
              :key="carSkin.id" v-text="carSkin.name" 
              :class="carSkinOut?(carSkinOut.id==carSkin.id?'btn-label-yellow':''):''"
              @click="choseCarSkin(carSkin)">
            </span>
            <div v-if="carSkinsAll.length==0" class="miss">
              <span class="grey">暂无皮肤</span>
            </div>
            
          </div>
          
        </div>
        
      </div>
      <div class="drawer-footer">
        <div class="drawer-footer-btn drawer-footer-btn-grey" @click="close()">取消</div>
      </div>
    </div>

    <DrawerCarSkin :carSkinNameOut="searchKey" :carOut="carOut" v-if="isShowDrawerCarSkin" @close="isShowDrawerCarSkin=false" @choseCarSkin="choseCarSkin"></DrawerCarSkin>
  </div>
</template>

<script>
import DrawerCarSkin from '@/components/drawer/DrawerCarSkin';
export default {
    name: "DrawerChoseCarSkin",
    components: {
      DrawerCarSkin
    },
    props: {
      carSkinOut:Object,
      carOut:Object
    },
    mounted(){
      this.initCarSkins();
    },
    watch: {

    },
    computed: {
      carSkinsAll(){
        let reg = new RegExp(this.searchKey);
        let tempList = this.$store.state.carSkins
        .filter(carSkin=>reg.test(carSkin.name))
        .sort((a,b)=>{
          return b.power-a.power;
        });
        if(this.carOut){
          tempList = tempList.filter(carSkin => carSkin.car.id==this.carOut.id)
        }
        return tempList;
      }
    },
    data() {
      return {
        visible:true,
        searchKey:'',
        isShowDrawerCarSkin:false,
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
      choseCarSkin(carSkin){
        this.$emit('choseCarSkin',carSkin);
        this.close();
      },
      initCarSkins(){
        this.loading=true;
        if(this.carSkinsAll.length==0){
           this.$store.dispatch('listCarSkins').then(() => {
            this.loading = false;
          }).catch(() => {
            that.$toast({
              showCancel:false,
              t1:'错误',
              t2: '无法请求赛车皮肤数据',
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
        .right{
          flex: 1;
          padding: 1rem;
          height: 21.6rem;
          overflow-y: auto;
          .label{
            margin-right: 0.5rem;
            margin-bottom: 0.5rem;
          }
          .miss{
            height: 21.6rem;
            line-height: 21.6rem;
            text-align: center;
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

<template>
  <div class="mask-wrapper animate__animated" :class="visible?'animate__fadeIn':'animate__fadeOut'" @click="close()" @click.stop>
    <div v-if="petOut" class="drawer animate__animated" :class="visible?'animate__fadeInUp':'animate__fadeOutDown'" @click.stop>
      <div class="drawer-header">
        <h2>选择宠物<span v-text="petOut.name"></span>皮肤</h2>
      </div>
      <div class="drawer-body">
         <div class="search-box">
          <span class="search icon iconfont iconsousuo"></span>
          <input placeholder="搜索" type="text" v-model="searchKey">
          <span class="close">
            <span v-if="searchKey" class="icon iconfont iconclose" @click="searchKey=''"></span>
          </span>
          <span class="btn btn-yellow" @click="isShowDrawerPetSkin=true">
            <span class="icon iconfont iconclose rotate45"></span>
            <span> 添加</span>
          </span>
        </div>
        <div class="container">
          <div class="right petSkins">
            <span class="label btn btn-label"
              v-for="petSkin in petSkinsAll" 
              :key="petSkin.id" v-text="petSkin.name" 
              :class="petSkinOut?(petSkinOut.id==petSkin.id?'btn-label-yellow':''):''"
              @click="chosePetSkin(petSkin)">
            </span>
            <div v-if="petSkinsAll.length==0" class="miss">
              <span class="grey">暂无皮肤</span>
            </div>
            
          </div>
          
        </div>
        
      </div>
      <div class="drawer-footer">
        <div class="drawer-footer-btn drawer-footer-btn-grey" @click="close()">取消</div>
      </div>
    </div>

    <DrawerPetSkin :petSkinNameOut="searchKey"  :petOut="petOut" v-if="isShowDrawerPetSkin" @close="isShowDrawerPetSkin=false" @chosePetSkin="chosePetSkin"></DrawerPetSkin>
  </div>
</template>

<script>
import DrawerPetSkin from '@/components/drawer/DrawerPetSkin';
export default {
    name: "DrawerChosePetSkin",
    components: {
      DrawerPetSkin
    },
    props: {
      petSkinOut:Object,
      petOut:Object
    },
    mounted(){
      this.initPetSkins();
    },
    computed: {
      petSkinsAll(){
        let reg = new RegExp(this.searchKey);
        let tempList = this.$store.state.petSkins
        .filter(petSkin=>reg.test(petSkin.name))
        .sort((a,b)=>{
          return b.power-a.power;
        });
        if(this.petOut){
          tempList = tempList.filter(petSkin => petSkin.pet.id==this.petOut.id)
        }
        return tempList;
      }
    },
    data() {
      return {
        visible:true,
        searchKey:'',
        isShowDrawerPetSkin:false,
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
      chosePetSkin(petSkin){
        this.$emit('chosePetSkin',petSkin);
        this.close();
      },
      initPetSkins(){
        this.loading=true;
        if(this.petSkinsAll.length==0){
           this.$store.dispatch('listPetSkins').then(() => {
            this.loading = false;
          }).catch(() => {
            that.$toast({
              showCancel:false,
              t1:'错误',
              t2: '无法请求宠物皮肤数据',
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

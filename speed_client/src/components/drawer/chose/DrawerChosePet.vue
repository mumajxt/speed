<template>
  <div class="mask-wrapper animate__animated" :class="visible?'animate__fadeIn':'animate__fadeOut'" @click="close()" @click.stop>
    <div class="drawer animate__animated" :class="visible?'animate__fadeInUp':'animate__fadeOutDown'" @click.stop>
      <div class="drawer-header">
        <h2>选择宠物</h2>
      </div>
      <div class="drawer-body">
         <div class="search-box">
          <span class="search icon iconfont iconsousuo"></span>
          <input placeholder="搜索" type="text" v-model="searchKey">
          <span class="close">
            <span v-if="searchKey" class="icon iconfont iconclose" @click="searchKey=''"></span>
          </span>
          <span class="btn btn-yellow" @click="isShowDrawerPet=true">
            <span class="icon iconfont iconclose rotate45"></span>
            <span> 添加</span>
          </span>
        </div>
        <div class="container">
          <div class="right pets">
            <span class="label btn btn-label"
              v-for="pet in tempPets" 
              :key="pet.id" v-text="pet.name"
              :class="petOut?(petOut.id==pet.id?'btn-label-yellow':''):''"
              @click="chosePet(pet)">
            </span>
          </div>
        </div>
        
      </div>
      <div class="drawer-footer">
        <div class="drawer-footer-btn drawer-footer-btn-grey" @click="close()">取消</div>
      </div>
    </div>

    <DrawerPet :petNameOut="searchKey"  v-if="isShowDrawerPet" @close="isShowDrawerPet=false" @chosePet="chosePet"></DrawerPet>
  </div>
</template>

<script>
import DrawerPet from '@/components/drawer/DrawerPet';
export default {
    name: "Drawer",
    components: {
      DrawerPet
    },
    props: {
      petOut:Object
    },
    mounted(){
      this.initPets();
    },
    computed: {
      petsAll(){
        let reg = new RegExp(this.searchKey);
        return this.$store.state.pets
        .filter(pet=>reg.test(pet.name))
        .sort((a,b)=>{
          return b.power-a.power;
        });;
      },
      tempPets(){
        return this.petsAll;
      },
    },
    data() {
      return {
        visible:true,
        searchKey:'',
        isShowDrawerPet:false,
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
      chosePet(pet){
        this.$emit('chosePet',pet);
        this.close();
      },
      initPets(){
        this.loading=true;
        if(this.petsAll.length==0){
           this.$store.dispatch('listPets').then(() => {
            this.loading = false;
          }).catch(() => {
            that.$toast({
              showCancel:false,
              t1:'错误',//弹窗的标题
              t2: '无法请求宠物数据',//弹窗的内容
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
          padding-left: 1rem;
          
          height: 20rem;
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

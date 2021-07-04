<template>
  <div class="mask-wrapper animate__animated" :class="visible?'animate__fadeIn':'animate__fadeOut'" @click="close()" @click.stop>
    <div class="drawer animate__animated" :class="visible?'animate__fadeInUp':'animate__fadeOutDown'" @click.stop>
      <div class="drawer-header">
        <h2 v-if="petSkinOut">修改宠物皮肤</h2>
        <h2 v-else>新增宠物皮肤</h2>
      </div>
      <div class="drawer-body">
        <form class="attribute-list">
          <div class="attribute-list-item name">
            <label>名称</label>
            <div class="value input-wrapper">
              <input placeholder="输入宠物皮肤名称" type="text" v-model="petSkin.name">
            </div>
          </div>
          <div class="attribute-list-item pet_name">
            <label>宠物</label>
            <div class="value input-wrapper  input-wrapper-select">
              <input readonly @click="isShowDrawerChosePet=true" placeholder="选择宠物" type="text" v-model="pet.name">
              <div class="input-icon">
                <i class="iconfont iconxiala"></i>
              </div>
            </div>
          </div>
        </form>
      </div>
      <div class="drawer-footer">
        <div class="drawer-footer-btn drawer-footer-btn-grey" @click="close()">取消</div>
        <span class="divider"></span>
        <div v-if="petSkinOut" class="drawer-footer-btn drawer-footer-btn-yellow" @click="post()">修改</div>
        <div v-else class="drawer-footer-btn drawer-footer-btn-yellow" @click="post()">添加</div>
      </div>
    </div>


    <DrawerChosePet :visible.sync="isShowDrawerChosePet" @close="isShowDrawerChosePet=false" @chosePet="chosePet"></DrawerChosePet>
  
  </div>
</template>

<script>
import DrawerChosePet from '@/components/drawer/chose/DrawerChosePet';
export default {
    name: "DrawerPetSkin",
    props: {
      petOut:Object,
      petSkinOut:Object,
      petSkinNameOut:String
    },
    components:{
      DrawerChosePet
    },
    mounted(){
      
    },
    watch: {
      petOut:function(val){
        this.petSkin.pet=val;
      },
      petSkinOut: {
        immediate: true,
        handler(value) {
          if(value){
            this.petSkin = JSON.parse(JSON.stringify(value));
          }else{
            this.petSkin={
              id:'',
              name:'',
              pet:null
            }
          }
        },
      },
      petSkinNameOut:function(val){
        if(val){
          this.petSkin.name=val;
        }
      }
    },
    computed: {
      pet(){
        if(this.petSkin.pet){
          return this.petSkin.pet;
        }else{
          return {
            name:''
          }
        }
      },
    },
    data() {
      return {
        visible:true,
        petSkin:null,
        loading:false,
        isShowDrawerChosePet:false
      };
    },
    methods: {
      close(){
        this.$emit('close');
      },
      chosePet(pet){
        this.petSkin.pet=pet;
        this.isShowDrawerChosePet=false;
      },
      post(){
        //this.messageBox = true;
        if(!this.petSkin.name){
          this.$toast({
            showCancel:false,
            t1:'提示',//弹窗的标题
            t2:'请输入宠物皮肤名称',//弹窗的内容
            type:'warning'
          });
        }else{
          let petSkinPost = {
            id:this.petSkin.id,
            name:this.petSkin.name,
            petId:this.petSkin.pet.id,
          }
          let data = this.$qs.stringify(petSkinPost);
          let that = this;

          if(that.petSkinOut){
            this.loading = true;
            this.$axios({
                method:'put',
                url:'/api/petSkin',
                data: data
            }).then((response) =>{
                let info = response.data;
                if(info.flag){
                  that.$toast({
                    showCancel:false,
                    t1:'成功',//弹窗的标题
                    t2:'修改宠物皮肤成功',//弹窗的内容
                    type:'success'
                  });
                  that.$store.dispatch('listPetSkins');
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
              that.msg = '服务器出错！';
              that.$toast({
                showCancel:false,
                t1:'提示',//弹窗的标题
                t2: '服务器出错！',//弹窗的内容
                type:'error'
              });
            });
            
          }else{
            this.loading = true;
             this.$axios({
                method:'post',
                url:'/api/petSkin',
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
                  that.$store.dispatch('listPetSkins');
                  let petSkin = info.data;
                  that.$emit('chosePetSkin',petSkin);
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
              that.msg = '服务器出错！';
              that.$toast({
                showCancel:false,
                t1:'提示',//弹窗的标题
                t2: '服务器出错！',//弹窗的内容
                type:'error'
              });
            });
          }
         
        }
      },
    }
};
</script>

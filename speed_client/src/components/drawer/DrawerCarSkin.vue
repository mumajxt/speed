<template>
  <div class="mask-wrapper animate__animated" :class="visible?'animate__fadeIn':'animate__fadeOut'" @click="close()" @click.stop>
    <div class="drawer animate__animated" :class="visible?'animate__fadeInUp':'animate__fadeOutDown'" @click.stop>
      <div class="drawer-header">
        <h2 v-if="carSkinOut">修改赛车皮肤</h2>
        <h2 v-else>新增赛车皮肤</h2>
      </div>
      <div class="drawer-body">
        <form class="attribute-list">
          <div class="attribute-list-item car_name">
            <label>赛车<span v-show="!carSkin.car" class="red">*</span></label>
            <div class="value input-wrapper  input-wrapper-select">
              <input readonly @click="isShowDrawerChoseCar=true" placeholder="选择赛车" type="text" v-model="car.name">
              <div class="input-icon">
                <i class="iconfont iconxiala"></i>
              </div>
            </div>
          </div>
          <div class="attribute-list-item name">
            <label>名称<span v-show="!carSkin.name" class="red">*</span></label>
            <div class="value input-wrapper">
              <input placeholder="输入赛车皮肤名称" type="text" v-model="carSkin.name">
            </div>
          </div>
        </form>
      </div>
      <div class="drawer-footer">
        <div class="drawer-footer-btn drawer-footer-btn-grey" @click="close()">取消</div>
        <span class="divider"></span>
        <div v-if="carSkinOut" class="drawer-footer-btn drawer-footer-btn-yellow" @click="post()">修改</div>
        <div v-else class="drawer-footer-btn drawer-footer-btn-yellow" @click="post()">添加</div>
      </div>
    </div>


    <DrawerChoseCar v-if="isShowDrawerChoseCar" @close="isShowDrawerChoseCar=false" @choseCar="choseCar"></DrawerChoseCar>
  
  </div>
</template>

<script>
import DrawerChoseCar from '@/components/drawer/chose/DrawerChoseCar';
export default {
    name: "DrawerCarSkin",
    props: {
      carSkinOut:Object,
      carOut:Object,
      carSkinNameOut:String
    },
    components:{
      DrawerChoseCar
    },
    mounted(){
      
    },
    watch: {
      carOut:function(val){
        this.carSkin.car=val;
      },
      carSkinOut: {
        immediate: true,
        handler(value) {
          if(value){
            this.carSkin = JSON.parse(JSON.stringify(value));
          }else{
            this.carSkin={
              id:'',
              name:'',
              car:null
            }
          }
        },
      },
      carSkinNameOut:function(val){
        if(val){
          this.carSkin.name=val;
        }
      }
    },
    computed: {
      car(){
        if(this.carSkin.car){
          return this.carSkin.car;
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
        carSkin:null,
        loading:false,
        isShowDrawerChoseCar:false
      };
    },
    methods: {
      close(){
        this.visible=false;
        setTimeout(()=>{
          this.$emit('close');
        },300);
      },
      choseCar(car){
        this.carSkin.car=car;
        this.isShowDrawerChoseCar=false;

      },
      post(){
        //this.messageBox = true;
        if(!this.carSkin.name){
          this.$toast({
            showCancel:false,
            t1:'提示',//弹窗的标题
            t2:'请输入赛车皮肤名称',//弹窗的内容
            type:'warning'
          });
        }else{
          let carSkinPost = {
            id:this.carSkin.id,
            name:this.carSkin.name,
            carId:this.carSkin.car.id,
          }
          let data = this.$qs.stringify(carSkinPost);
          let that = this;

          if(that.carSkinOut){
            this.loading = true;
            this.$axios({
                method:'put',
                url:'/api/carSkin',
                data: data
            }).then((response) =>{
                let info = response.data;
                if(info.flag){
                  that.$toast({
                    showCancel:false,
                    t1:'成功',//弹窗的标题
                    t2:'修改赛车皮肤成功',//弹窗的内容
                    type:'success'
                  });
                  that.$store.dispatch('listCarSkins');
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
                url:'/api/carSkin',
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
                  that.$store.dispatch('listCarSkins');
                  let carSkin = info.data;
                  that.$emit('choseCarSkin',carSkin);
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

<template>
  <div class="mask-wrapper animate__animated" :class="visible?'animate__fadeIn':'animate__fadeOut'" v-show="isShow" @click="close()" @click.stop>
    <div class="drawer animate__animated" :class="visible?'animate__fadeInUp':'animate__fadeOutDown'" @click.stop>
      <div class="drawer-header">
        <h2>选择是否携带宠物</h2>
      </div>
      <div class="drawer-body">
        <div class="drawer-labels">
          <div class="drawer-label-wrapper">
            <span class="drawer-label" :class="{'active':typeOut=='all'}" @click="chosePetType('all')">不限</span>
          </div>
          <div class="drawer-label-wrapper">
            <span class="drawer-label" :class="{'active':typeOut=='withPet'}" @click="chosePetType('withPet')">有宠</span>
          </div>
          <div class="drawer-label-wrapper">
            <span class="drawer-label" :class="{'active':typeOut=='withOutPet'}" @click="chosePetType('withOutPet')">无宠</span>
          </div>
        </div>
      </div>
      <div class="drawer-footer">
        <div class="drawer-footer-btn drawer-footer-btn-grey" @click="close()">取消</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
    name: "DrawerChosePetType",
    components: {
      
    },
    props: {
      visible:Boolean,
      typeOut:String,
    },
    mounted(){
      
    },
    watch: {
      visible:function(val){
        if(val){
          this.isShow=true;
        }else{
          setTimeout(()=>{
            this.isShow=false;
          },300);
        }
      }
    },
    computed: {

    },
    data() {
      return {
        isShow:false
      };
    },
    methods: {
      close(){
        this.$emit('close');
      },
      chosePetType(type){
        this.$emit('chosePetType',type);
        this.close();
      }
    }
};
</script>
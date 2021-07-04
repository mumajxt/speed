<template>
  <div class="mask-wrapper animate__animated" :class="visible?'animate__fadeIn':'animate__fadeOut'" v-show="isShow" @click="close()" @click.stop>
    <div class="drawer animate__animated" :class="visible?'animate__fadeInUp':'animate__fadeOutDown'" @click.stop :style="{height:height}">
      <div class="drawer-header">
        <slot name="header"></slot>
      </div>
      <div class="drawer-body" @scroll.stop>
        <slot name="body"></slot>
      </div>
      <div class="drawer-footer clickable" @click="close()">
        <slot name="footer"></slot>
      </div>
    </div>
  </div>
</template>

<script>

export default {
    name: "Drawer",
    components: {

    },
    props: {
      visible:Boolean,
      height:String
    },
    mounted(){

    },
    destroyed(){

    },
    watch: {
      visible:function(val){
        if(val){
          this.isShow=true;
        }else{
          setTimeout(()=>{
            this.isShow=false;
          },300)
        }
      }
    },
    computed: {

    },
    data() {
      return {
        isShow:false,
      };
    },
    methods: {
      close(){
        this.$emit('close');
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

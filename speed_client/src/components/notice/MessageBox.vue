<template>
  <div v-if="isShow" class="message_box_wrapper animate__animated" :class="visibale?'animate__fadeIn':'animate__fadeOut'"  @click="showCancel?closeMyself():''" @scroll.stop>
    <div class="main animate__animated" :class="visibale?'animate__zoomIn':'animate__zoomOut'" @click.stop>
      <span class="icon iconfont iconclose"  @click="closeMyself" v-if="showCancel"></span>
       <div class="message-header">
        <slot name="header">
          <h3 v-html="t1"></h3>
        </slot>
      </div>
      <div class="message-body" @scroll.stop>
        <slot name="body">
          <p>
            <i v-if="type=='success'" class="icon iconfont iconsuccess green"></i>
            <i v-if="type=='error'" class="icon iconfont iconerror red"></i>
            <i v-if="type=='info'" class="icon iconfont iconinfo grey"></i>
            <i v-if="type=='warning'" class="icon iconfont iconwarning yellow"></i>
            <span v-html="t2"></span>
          </p>
        </slot>
      </div>
      <div class="message-footer">
        <slot name="footer">
          <span class="btn btn-grey bold" @click="closeMyself" v-if="showCancel">取消</span>
          <span class="btn btn-yellow bold" @click="besure">确定</span>
        </slot>
      </div>
    </div>
  </div>
</template>

<script>

export default {
    name: "MessageBox",
    components: {

    },
    
    data() {
      return {
        isShow: false,
        visibale:true,
        showCancel:false,
        t1:'',
        t2:'',
        type:''
      };
    },
    watch:{
      /* visibale:{
        handler(val){
          if(val){
            this.isShow=true;
          }else{
            setTimeout(()=>{
              this.isShow=false;
            },300)
          }
        },
        immediate:true
      } */
    },
    methods: {
      show(params){  //初始化参数
        let { showCancel,t1,t2,type } = params
        this.showCancel = showCancel
        this.t1 = t1
        this.t2 = t2
        this.type = type
        this.isShow = true
        this.visibale=true
      },
      closeMyself () {
        this.visibale = false;
        setTimeout(()=>{
          this.isShow=false;
          this.callBack(false);
        },300)
      },
      besure(){  //确定按钮
        this.visibale = false;
        setTimeout(()=>{
          this.isShow=false;
          this.callBack(true);
        },300)
      }
    }
};
</script>

<style lang="less" scoped>
.message_box_wrapper {
  background: rgba(0, 0, 0, 0.5);
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  text-align: center;
  z-index: 2025;

  &::after {
    content: "";
    display: inline-block;
    height: 100%;
    width: 0;
    vertical-align: middle;
  }

  .main{
    display: inline-block;
    position: relative;
    width: 70%;
    max-width: 26rem;
    border-radius: 1rem;
    background-color: white;
    overflow: hidden;
    vertical-align: middle;
    box-sizing: border-box;
    padding: 1rem;
    text-align: left;

    .iconclose{
      position: absolute;
      top: 1rem;
      right: 1rem;
      font-size: 1.2rem;
      cursor: pointer;
      color: @c3;
      &:hover{
        color: @c1;
      }
    }

    .message-header{
      padding-bottom: 1rem;
    }

    .message-body{
      font-size: 1rem;
      color: @c2;
      //min-height: 6rem;
      padding-bottom: 1rem;
      .icon {
        margin-right: 0.2rem;
      }
    }

    .message-footer{
      text-align: right;

      .btn{
        margin-left: 0.5rem;
      }
    }   
  }
  
}

</style>

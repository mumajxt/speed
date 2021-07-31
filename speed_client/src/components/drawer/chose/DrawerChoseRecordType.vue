<template>
  <div class="mask-wrapper animate__animated" :class="visible?'animate__fadeIn':'animate__fadeOut'" v-show="isShow" @click="close()" @click.stop>
    <div class="drawer animate__animated" :class="visible?'animate__fadeInUp':'animate__fadeOutDown'" @click.stop>
      <div class="drawer-header">
        <h2>选择记录类型</h2>
      </div>
      <div class="drawer-body">
        <div class="drawer-labels">
            <div class="drawer-label-wrapper" v-if="isShowAll">
              <span class="drawer-label" :class="{'active':typeOut=='all'}" @click="choseRecordType('all')">不限</span>
            </div>
          <div class="drawer-label-wrapper">
            <span class="drawer-label" :class="{'active':typeOut=='t'}" @click="choseRecordType('t')">T车</span>
          </div>
            <div class="drawer-label-wrapper">
              <span class="drawer-label" :class="{'active':typeOut=='a'}" @click="choseRecordType('a')">A车</span>
            </div>
            <div class="drawer-label-wrapper">
              <span class="drawer-label" :class="{'active':typeOut=='undrift'}" @click="choseRecordType('undrift')">A车<span class="grey">(抓地)</span></span>
            </div>
            <div class="drawer-label-wrapper">
              <span class="drawer-label" :class="{'active':typeOut=='x'}" @click="choseRecordType('x')">X悬浮车</span>
            </div>
            <div class="drawer-label-wrapper">
              <span class="drawer-label" :class="{'active':typeOut=='b'}" @click="choseRecordType('b')">B车</span>
            </div>
            <div class="drawer-label-wrapper">
              <span class="drawer-label" :class="{'active':typeOut=='c'}" @click="choseRecordType('c')">C车</span>
            </div>
            <div class="drawer-label-wrapper">
              <span class="drawer-label" :class="{'active':typeOut=='d'}" @click="choseRecordType('d')">D车</span>
            </div>
<!--            <div class="drawer-label-wrapper">-->
<!--              <span class="drawer-label" :class="{'active':typeOut=='l1'}" @click="choseRecordType('l1')">L1滑板</span>-->
<!--            </div>-->
<!--            <div class="drawer-label-wrapper">-->
<!--              <span class="drawer-label" :class="{'active':typeOut=='m1'}" @click="choseRecordType('m1')">M1摩托</span>-->
<!--            </div>-->
<!--            <div class="drawer-label-wrapper">-->
<!--              <span class="drawer-label" :class="{'active':typeOut=='m2'}" @click="choseRecordType('m2')">M2摩托</span>-->
<!--            </div>-->
          </div>


          <div class="drawer-labels">
            <div class="drawer-label-wrapper">
              <span class="drawer-label" :class="{'active':typeOut=='sCup'}" @click="choseRecordType('sCup')">S联赛</span>
            </div>
            <div class="drawer-label-wrapper">
              <span class="drawer-label" :class="{'active':typeOut=='asiaCup'}" @click="choseRecordType('asiaCup')">亚洲杯</span>
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
    name: "DrawerChoseRecordType",
    components: {
      
    },
    props: {
      visible:Boolean,
      typeOut:String,
      isShowAll:Boolean
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
      choseRecordType(type){
        this.$emit('choseRecordType',type);
        this.close();
      }
    }
};
</script>

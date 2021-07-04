<template>
  <div class="mask-wrapper animate__animated" :class="visible?'animate__fadeIn':'animate__fadeOut'" @click="close()" @click.stop>
    <div class="drawer animate__animated" :class="visible?'animate__fadeInUp':'animate__fadeOutDown'" @click.stop>
      <div class="drawer-header">
        <h2 v-if="carOut">修改赛车</h2>
        <h2 v-else>新增赛车</h2>
      </div>
      <div class="drawer-body">
        <form class="attribute-list">
          <div class="attribute-list-item name">
            <label>名称<span v-show="!car.name" class="red">*</span></label>
            
            <div class="value input-wrapper">
              <input placeholder="输入赛车名称" type="text" v-model="car.name">
            </div>
          </div>
          <div class="attribute-list-item rank">
            <label>等级</label>
            
            <div class="value input-wrapper  input-wrapper-select">
              <input readonly @click="isShowRankDrawer=true" placeholder="选择等级" type="text" v-model="car.rank">
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
        <div v-if="carOut" class="drawer-footer-btn drawer-footer-btn-yellow" @click="post()">修改</div>
        <div v-else class="drawer-footer-btn drawer-footer-btn-yellow" @click="post()">添加</div>
      </div>
    </div>

    <Drawer 
    :visible.sync="isShowRankDrawer" 
    @close="isShowRankDrawer=false">
       <template v-slot:header>
        <h2>选择赛车等级</h2>
      </template>

      <template v-slot:body>
        <div class="drawer-labels">
          <div class="drawer-label-wrapper"><span class="drawer-label" @click="choseRank('A')" :class="{'active':car.rank=='A'}">A</span></div>
          <div class="drawer-label-wrapper"><span class="drawer-label" @click="choseRank('B')" :class="{'active':car.rank=='B'}">B</span></div>
          <div class="drawer-label-wrapper"><span class="drawer-label" @click="choseRank('C')" :class="{'active':car.rank=='C'}">C</span></div>
          <div class="drawer-label-wrapper"><span class="drawer-label" @click="choseRank('D')" :class="{'active':car.rank=='D'}">D</span></div>
          <div class="drawer-label-wrapper"><span class="drawer-label" @click="choseRank('X')" :class="{'active':car.rank=='X'}">X</span></div>
          <div class="drawer-label-wrapper"><span class="drawer-label" @click="choseRank('L1')" :class="{'active':car.rank=='L1'}">L1</span></div>
          <div class="drawer-label-wrapper"><span class="drawer-label" @click="choseRank('M1')" :class="{'active':car.rank=='M1'}">M1</span></div>
          <div class="drawer-label-wrapper"><span class="drawer-label" @click="choseRank('M2')" :class="{'active':car.rank=='M2'}">M2</span></div>
        </div>
      </template>
      <template v-slot:footer>
        <div class="drawer-footer-btn drawer-footer-btn-grey" @click="isShowRankDrawer=false">取消</div>
      </template>
    </Drawer>
  </div>
</template>

<script>
import Drawer from '@/components/notice/Drawer';
export default {
    name: "DrawerCar",
    components: {
      Drawer
    },
    props: {
      carOut:Object,
      carNameOut:String
    },
    mounted(){
      
    },
    watch: {
      carOut: {
        immediate: true,
        handler(value) {
          if(value){
            this.car = JSON.parse(JSON.stringify(value));
          }else{
            this.car={
              id:'',
              name:'',
              rank:'A'
            }
          }
        },
      },
      carNameOut:function(val){
        if(val){
          this.car.name=val;
        }
      }
    },
    computed: {

    },
    data() {
      return {
        visible:true,
        car:null,
        isShowRankDrawer:false,
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
      choseRank(rank){
        this.car.rank = rank;
        this.isShowRankDrawer=false;
      },
      post(){
        if(!this.car.name){
          this.$toast({
            showCancel:false,
            t1:'提示',//弹窗的标题
            t2:'请输入赛车名称',//弹窗的内容
            type:'warning'
          });
        }else{
          let data = this.$qs.stringify(this.car);
          let that = this;
          if(that.carOut){
            this.loading = true;
            this.$axios({
                method:'put',
                url:'/api/car',
                data: data
            }).then((response) =>{
                let info = response.data;
                if(info.flag){
                  that.$toast({
                    showCancel:false,
                    t1:'成功',//弹窗的标题
                    t2:'修改赛车成功',//弹窗的内容
                    type:'success'
                  });
                  that.$store.dispatch('listCars');
                }else{
                  that.$toast({
                    showCancel:false,
                    t1:'提示',//弹窗的标题
                    t2: info.msg,//弹窗的内容
                    type:'error'
                  });
                }
                that.loading = false;
                that.close();
            }).catch(() => {
              that.loading = false;
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
                url:'/api/car',
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
                  that.$store.dispatch('listCars');
                  that.$emit('choseCar',info.data);
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
            }).catch(() => {
              that.loading = false;
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
<template>
  <div class="mask-wrapper animate__animated" :class="visible?'animate__fadeIn':'animate__fadeOut'" @click="close()" @click.stop>
    <div class="drawer animate__animated" :class="visible?'animate__fadeInUp':'animate__fadeOutDown'" @click.stop>
      <div class="drawer-header">
        <h2 v-if="ecuOut">修改ECU</h2>
        <h2 v-else>新增ECU</h2>
      </div>
      <div class="drawer-body">
        <form class="attribute-list">
          <div class="attribute-list-item name">
            <label>名称<span v-show="!ecu.name" class="red">*</span></label>
            <div class="value input-wrapper">
              <input placeholder="输入ECU名称" type="text" v-model="ecu.name">
            </div>
          </div>
        </form>
      </div>
      <div class="drawer-footer">
        <div class="drawer-footer-btn drawer-footer-btn-grey" @click="close()">取消</div>
        <span class="divider"></span>
        <div v-if="ecuOut" class="drawer-footer-btn drawer-footer-btn-yellow" @click="post()">修改</div>
        <div v-else class="drawer-footer-btn drawer-footer-btn-yellow" @click="post()">添加</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
    name: "DrawerEcu",
    components: {
      
    },
    props: {
      ecuOut:Object,
      ecuNameOut:String
    },
    mounted(){
      
    },
    watch: {
      ecuOut: {
        immediate: true,
        handler(value) {
          if(value){
            this.ecu = JSON.parse(JSON.stringify(value));
          }else{
            this.ecu={
              id:'',
              name:'',
            }
          }
        },
      },
      ecuNameOut:function(val){
        if(val){
          this.ecu.name=val;
        }
      }
    },
    computed: {

    },
    data() {
      return {
        visible:true,
        ecu:null,
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
      post(){
        if(!this.ecu.name){
          this.$toast({
            showCancel:false,
            t1:'提示',//弹窗的标题
            t2:'请输入ECU名称',//弹窗的内容
            type:'warning'
          });
        }else{
          let data = this.$qs.stringify(this.ecu);
          let that = this;
          if(that.ecuOut){
            this.loading = true;
            this.$axios({
                method:'put',
                url:'/api/ecu',
                data: data
            }).then((response) =>{
                let info = response.data;
                if(info.flag){
                  that.$toast({
                    showCancel:false,
                    t1:'成功',//弹窗的标题
                    t2:'修改ECU成功',//弹窗的内容
                    type:'success'
                  });
                  that.$store.dispatch('listEcus');
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
                url:'/api/ecu',
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
                  that.$store.dispatch('listEcus');
                  that.$emit('choseEcu',info.data);
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

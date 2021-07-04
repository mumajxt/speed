<template>
  <div class="mask-wrapper animate__animated" :class="visible?'animate__fadeIn':'animate__fadeOut'" v-show="isShow" @click="close()" @click.stop>
    <div class="drawer animate__animated" :class="visible?'animate__fadeInUp':'animate__fadeOutDown'" @click.stop>
      <div class="drawer-header">
        <h2 v-if="donateOut">修改好心人</h2>
        <h2 v-else>新增好心人</h2>
      </div>
      <div class="drawer-body">
        <form class="attribute-list">
          <div class="attribute-list-item name">
            <label>名称<span v-show="!donate.name" class="red">*</span></label>
            <div class="value input-wrapper">
              <input placeholder="输入好心人名称" type="text" v-model="donate.name">
            </div>
          </div>
          <div class="attribute-list-item name">
            <label>金额<span v-show="!donate.money" class="red">*</span></label>
            <div class="value input-wrapper">
              <input placeholder="输入捐助金额" type="number" v-model="donate.money">
            </div>
          </div>
          <div class="attribute-list-item">
            <label>日期<span v-show="!donate.date" class="red">*</span></label>         
            <div class="value input-wrapper">
              <input class="value" type="text" placeholder="0000-00-00" v-model="donate.date">
            </div>
          </div>
        </form>
      </div>
      <div class="drawer-footer">
        <div class="drawer-footer-btn drawer-footer-btn-grey" @click="close()">取消</div>
        <span class="divider"></span>
        <div v-if="donateOut" class="drawer-footer-btn drawer-footer-btn-yellow" @click="post()">修改</div>
        <div v-else class="drawer-footer-btn drawer-footer-btn-yellow" @click="post()">添加</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
    name: "DrawerDonate",
    components: {
      
    },
    props: {
      visible:Boolean,
      donateOut:Object
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
          },300)
        }
      },
      donateOut: {
        immediate: true,
        handler(value) {
          if(value){
            this.donate = JSON.parse(JSON.stringify(value));
          }else{
            this.donate={
              id:'',
              name:'匿名好心人',
              money:'',
              date: new Date().format('yyyy-MM-dd')
            }
          }
        },
      }
    },
    computed: {

    },
    data() {
      return {
        isShow:false,
        donate:null,
        loading:false
      };
    },
    methods: {
      close(){
        this.$emit('close');
      },
       
      post(){
        if(!this.donate.name){
          this.$toast({
            showCancel:false,
            t1:'提示',//弹窗的标题
            t2:'请输入好心人名称',//弹窗的内容
            type:'warning'
          });
        }else{
          let data = this.$qs.stringify(this.donate);
          let that = this;
          if(that.donateOut){
            this.loading = true;
            this.$axios({
                method:'put',
                url:'/api/donate',
                data: data
            }).then((response) =>{
                let info = response.data;
                if(info.flag){
                  that.$toast({
                    showCancel:false,
                    t1:'成功',//弹窗的标题
                    t2:'修改好心人成功',//弹窗的内容
                    type:'success'
                  });
                  //that.$store.dispatch('listDonates');
                  this.$emit('initData');
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
                url:'/api/donate',
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
                  this.$emit('initData');
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
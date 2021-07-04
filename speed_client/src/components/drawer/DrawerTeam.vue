<template>
  <div class="mask-wrapper animate__animated" :class="visible?'animate__fadeIn':'animate__fadeOut'" @click="close()" @click.stop>
    <div class="drawer animate__animated" :class="visible?'animate__fadeInUp':'animate__fadeOutDown'" @click.stop>
      <div class="drawer-header">
        <h2 v-if="teamOut">修改车队</h2>
        <h2 v-else>新增车队</h2>
      </div>
      <div class="drawer-body">
        <form class="attribute-list">
          <div class="attribute-list-item name">
            <label>名称<span v-show="!team.name" class="red">*</span></label>      
            <div class="value input-wrapper">
              <input placeholder="输入车队名称" type="text" v-model="team.name">
            </div>
          </div>
          <div class="attribute-list-item pro">
            <label>职业</label>
            <div class="switch" :class="team.isPro == true?'true':'false'" @click="team.isPro = !team.isPro">
              <span></span>
              <span></span>
            </div>
          </div>
        </form>
      </div>
      <div class="drawer-footer">
        <div class="drawer-footer-btn drawer-footer-btn-grey" @click="close()">取消</div>
        <span class="divider"></span>
        <div v-if="teamOut" class="drawer-footer-btn drawer-footer-btn-yellow" @click="post()">修改</div>
        <div v-else class="drawer-footer-btn drawer-footer-btn-yellow" @click="post()">添加</div>
      </div>
    </div>
  </div>
</template>

<script>

export default {
    name: "DrawerTeam",
    props: {
      teamOut:Object,
      teamNameOut:String
    },
    mounted(){

    },
    watch: {
      teamOut: {
        immediate: true,
        handler(value) {
          if(value){
            this.team = JSON.parse(JSON.stringify(value));
          }else{
            this.team={
              id:'',
              name:'',
              isPro:false
            }
          }
        },
      },
      teamNameOut:function(val){
        if(val){
          this.team.name=val;
        }
      }
    },
    computed: {

    },
    data() {
      return {
        visible:true,
        team:null,
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
        //this.messageBox = true;
        if(!this.team.name){
          this.$toast({
            showCancel:false,
            t1:'提示',//弹窗的标题
            t2:'请输入车队名称',//弹窗的内容
            type:'warning'
          });
        }else{
          
          let data = this.$qs.stringify(this.team);
          let that = this;

          if(that.teamOut){
            this.loading = true;
            this.$axios({
                method:'put',
                url:'/api/team',
                data: data
            }).then((response) =>{
                let info = response.data;
                if(info.flag){
                  that.$toast({
                    showCancel:false,
                    t1:'成功',//弹窗的标题
                    t2:'修改车队成功',//弹窗的内容
                    type:'success'
                  });
                  that.$store.dispatch('listTeams');
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
                url:'/api/team',
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
                  that.$store.dispatch('listTeams');
                  let team = info.data;
                  that.$emit('choseTeam',team);
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
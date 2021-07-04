<template>
  <div class="mask-wrapper animate__animated" :class="visible?'animate__fadeIn':'animate__fadeOut'" @click="close()" @click.stop>
    <div class="drawer animate__animated" :class="visible?'animate__fadeInUp':'animate__fadeOutDown'" @click.stop>
      <div class="drawer-header">
        <h2 v-if="petOut">修改宠物</h2>
        <h2 v-else>新增宠物</h2>
      </div>
      <div class="drawer-body">
        <form class="attribute-list">
          <div class="attribute-list-item name">
            <label>名称<span v-show="!pet.name" class="red">*</span></label>
            <div class="value input-wrapper">
              <input placeholder="输入宠物名称" type="text" v-model="pet.name">
            </div>
          </div>
        </form>
      </div>
       <div class="drawer-footer">
        <div class="drawer-footer-btn drawer-footer-btn-grey" @click="close()">取消</div>
        <span class="divider"></span>
        <div v-if="petOut" class="drawer-footer-btn drawer-footer-btn-yellow" @click="post()">修改</div>
        <div v-else class="drawer-footer-btn drawer-footer-btn-yellow" @click="post()">添加</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
    name: "DrawerPet",
    components: {
      
    },
    props: {
      petOut:Object,
      petNameOut:String
    },
    mounted(){
      
    },
    watch: {
      petOut: {
        immediate: true,
        handler(value) {
          if(value){
            this.pet = JSON.parse(JSON.stringify(value));
          }else{
            this.pet={
              id:'',
              name:'',
            }
          }
        },
      },
      petNameOut:function(val){
        if(val){
          this.pet.name=val;
        }
      }
    },
    computed: {

    },
    data() {
      return {
        visible:true,
        pet:null,
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
        if(!this.pet.name){
          this.$toast({
            showCancel:false,
            t1:'提示',//弹窗的标题
            t2:'请输入宠物名称',//弹窗的内容
            type:'warning'
          });
        }else{
          let data = this.$qs.stringify(this.pet);
          let that = this;
          if(that.petOut){
            this.loading = true;
            this.$axios({
                method:'put',
                url:'/api/pet',
                data: data
            }).then((response) =>{
                let info = response.data;
                if(info.flag){
                  that.$toast({
                    showCancel:false,
                    t1:'成功',//弹窗的标题
                    t2:'修改宠物成功',//弹窗的内容
                    type:'success'
                  });
                  that.$store.dispatch('listPets');
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
                url:'/api/pet',
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
                  that.$store.dispatch('listPets');
                  that.$emit('chosePet',info.data);
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

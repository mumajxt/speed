<template>
  <div id="ecu_manage">
    <div class="manage-container card">
      <div class="nav1">
        <div class="nav1-items">
          <div class="nav1-item nav1-item-selected">
            <h3>ECU</h3>
          </div>
        </div>
        <div class="input-wrapper nav1-input">
          <div class="input-icon">
            <i class="iconfont iconsousuo"></i>
          </div>
          <input type="text" placeholder="搜索ECU"  v-model="searchKey">
          <span class="input-icon">
            <i v-if="searchKey" class="iconfont iconclose" @click="searchKey=''"></i>
          </span>
        </div>
        <div @click="saveEcu()" class="nav1-btn btn btn-yellow">
          <span class="icon iconfont iconclose rotate45"></span>
        </div>
      </div>

      <div class="nav2">
        <div class="nav2-ecu nav2-items" v-if="nav1SelectedItem=='ecu'">
          <div class="btn-sm btn-label nav2-item" :class="nav2EcuSelectedItem=='all'?'btn-label-yellow bold':'btn-grey'" @click="nav2EcuSelectedItem='all'">
            <span>全部</span>
            <span class="count" v-text="ecusAll.length"></span>
          </div>
        </div>
      </div>

      
      <div class="table" v-show="nav1SelectedItem=='ecu'">
        <div class="th">
          <div class="id th-btn"
          :class="{'yellow':orderBy=='idUp'||orderBy=='idDown'}" 
          @click="clickId()">
            <span>ID</span>
            <i class="iconfont iconxiangxia" 
            :class="orderBy=='idUp'?'rotate180':(orderBy=='idDown'?'yellow':'grey2')">
            </i>
          </div>
          <div class="name th-btn"
          :class="{'yellow':orderBy=='nameUp'||orderBy=='nameDown'}" 
          @click="clickName()">
            <span>名称</span>
            <i class="iconfont iconxiangxia" 
            :class="orderBy=='nameUp'?'rotate180':(orderBy=='nameDown'?'yellow':'grey2')">
            </i>
          </div>
          <div class="operation">操作</div>
        </div>

        <div class="tr" v-for="ecu in tempEcus" :key="ecu.id">
          <div class="id" v-text="ecu.id"></div>
          <div class="name" v-text="ecu.name"></div>
          <div class="operation" @click.stop>
            <span @click="updateEcu(ecu)" class="btn btn-yellow iconfont iconupdate"></span>
            <span @click="readyToDeleteEcu(ecu)" class="btn btn-red  iconfont icondelete"></span>
          </div>
        </div>
      </div>
    </div>
    

    <DrawerEcu :ecuOut="ecu" v-if="isShowDrawerEcu" @close="isShowDrawerEcu=false"></DrawerEcu>

  </div>
</template>

<script>
export default {
    name: "ecuManage",
    components: {
      DrawerEcu:() => import('@/components/drawer/DrawerEcu'),
    },
    mounted(){
      this.listEcus();
    },
    computed: {
      ecusAll(){
        return this.$store.state.ecus
        .filter(ecu=>new RegExp(this.searchKey).test(ecu.name))
        .sort(this.orderBySort);
      },
      tempEcus(){
        return this.ecusAll;
      }
    },
    data() {
      return {
        loading:false,
        searchKey:'',
        ecu:null,
        isShowDrawerEcu:false,
        orderBy:'idDown',
        nav1SelectedItem:'ecu',
        nav2EcuSelectedItem:'all',
      };
    },
    methods: {
       orderBySort(a,b){
        switch(this.orderBy){
          case 'idUp':return a.id - b.id;
          case 'idDown':return b.id - a.id;
          case 'nameUp' : return b.name.localeCompare(a.name, 'zh-Hans-CN', {sensitivity: 'accent'});
          case 'nameDown' : return a.name.localeCompare(b.name, 'zh-Hans-CN', {sensitivity: 'accent'});
        }
      },
      clickId(){
        if(this.orderBy=='idDown'){
          this.orderBy='idUp'
        }else{
          this.orderBy='idDown'
        }
      },
      clickName(){
        if(this.orderBy=='nameDown'){
          this.orderBy='nameUp'
        }else{
          this.orderBy='nameDown'
        }
      },
      updateEcu(ecu){
        this.ecu=ecu;
        this.isShowDrawerEcu=true;
      },
      saveEcu(){
        this.ecu=null;
        this.isShowDrawerEcu=true;
      },
      listEcus(){
        if(this.ecusAll.length==0){
          let that = this;
          this.$store.dispatch('listEcus').then(() => {
            that.loading = false;
          }).catch(() => {
            that.$toast({
              showCancel:false,
              t1:'错误',//弹窗的标题
              t2: '无法请求ECU数据',//弹窗的内容
              type:'error'
            });
          });
        }
      },
      // 准备删除
      readyToDeleteEcu(ecu){
        this.$toast({
          showCancel:true,
          t1:'提示',//弹窗的标题
          t2: '确定要<span class="red">删除</span>ECU<b>'+ecu.name+'</b>?',//弹窗的内容
          type:'warning'
        }).then(()=>{
          this.deleteEcu(ecu);
        }).catch(()=>{
          console.log('取消删除');
        });
      },

      // 删除ECU
      deleteEcu(ecu){
        let that = this;
        this.$axios({
            method:'delete',
            url:'/api/ecu/'+ecu.id,
        }).then((response) =>{
            console.log('返回数据：↓');
            console.log(response.data);
            let info = response.data;
            if(info.flag){
              that.$toast({
                showCancel:false,
                t1:'提示',//弹窗的标题
                t2: '删除成功',//弹窗的内容
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
        }).catch((error) => {
          that.$toast({
            showCancel:false,
            t1:'提示',//弹窗的标题
            t2: '服务器出错！',//弹窗的内容
            type:'error'
          });
        });
      }
    }
};
</script>

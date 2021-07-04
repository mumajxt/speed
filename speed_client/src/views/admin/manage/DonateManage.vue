<template>
  <div id="donate_manage">
    <div class="manage-container card">
      <div class="nav1">
        <div class="nav1-items">
          <div class="nav1-item nav1-item-selected">
            <h3>打赏</h3>
          </div>
        </div>
        <div class="input-wrapper nav1-input">
          <div class="input-icon">
            <i class="iconfont iconsousuo"></i>
          </div>
          <input type="text" placeholder="搜索打赏"  v-model="searchKey">
          <span class="input-icon">
            <i v-if="searchKey" class="iconfont iconclose" @click="searchKey=''"></i>
          </span>
        </div>
        <div @click="saveDonate()" class="nav1-btn btn btn-yellow">
          <span class="icon iconfont iconclose rotate45"></span>
        </div>
      </div>

      <div class="nav2">
        <div class="nav2-donate nav2-items" v-if="nav1SelectedItem=='donate'">
          <div class="btn-sm btn-label nav2-item" :class="nav2DonateSelectedItem=='all'?'btn-label-yellow bold':'btn-grey'" @click="nav2DonateSelectedItem='all'">
            <span>全部</span>
            <span class="count" v-text="donatesAll.length"></span>
          </div>
        </div>
      </div>

      
      <div class="table" v-show="nav1SelectedItem=='donate'">
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

          <div class="money th-btn"
          :class="{'yellow':orderBy=='moneyUp'||orderBy=='moneyDown'}" 
          @click="clickMoney()">
            <span>金额</span>
            <i class="iconfont iconxiangxia" 
            :class="orderBy=='moneyUp'?'rotate180':(orderBy=='moneyDown'?'yellow':'grey2')">
            </i>
          </div>

          <div class="date th-btn" 
          :class="{'yellow':orderBy=='dateUp'||orderBy=='dateDown'}"
          @click="clickDate()">
            <span>日期</span>
            <i 
            class="iconfont iconxiangxia" 
            :class="orderBy=='dateUp'?'rotate180':(orderBy=='dateDown'?'':'grey2')">
            </i>
          </div>
          <div class="operation">操作</div>
        </div>

        <div class="tr" v-for="donate in tempDonates" :key="donate.id">
          <div class="id" v-text="donate.id"></div>
          <div class="name" v-text="donate.name"></div>
          <div class="money" v-text="donate.money"></div>
          <div class="date" v-text="donate.date"></div>
          <div class="operation" @click.stop>
            <span @click="updateDonate(donate)" class="btn btn-yellow iconfont iconupdate"></span>
            <span @click="readyToDeleteDonate(donate)" class="btn btn-red  iconfont icondelete"></span>
          </div>
        </div>
      </div>
    </div>
    

    <DrawerDonate @initData="initData" :donateOut="donate" :visible.sync="isShowDrawerDonate" @close="isShowDrawerDonate=false"></DrawerDonate>

  </div>
</template>

<script>
import DrawerDonate from '@/components/drawer/DrawerDonate';
export default {
    name: "donateManage",
    components: {
      DrawerDonate,
    },
    mounted(){
      this.initData();
    },
    computed: {
      donatesAll(){
        return this.donates
        .filter(donate=>new RegExp(this.searchKey).test(donate.name))
        .sort(this.orderBySort);
      },
      tempDonates(){
        return this.donatesAll;
      }
    },
    data() {
      return {
        loading:false,
        searchKey:'',
        donate:null,
        donates:[],
        isShowDrawerDonate:false,
        orderBy:'idDown',
        nav1SelectedItem:'donate',
        nav2DonateSelectedItem:'all',
      };
    },
    methods: {
       orderBySort(a,b){
        switch(this.orderBy){
          case 'idUp':return a.id - b.id;
          case 'idDown':return b.id - a.id;
          case 'moneyUp':return a.money - b.money;
          case 'moneyDown':return b.money - a.money;
          case 'dateUp':return new Date(a.date) - new Date(b.date);
          case 'dateDown':return new Date(b.date) - new Date(a.date)
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
      clickMoney(){
        if(this.orderBy=='moneyDown'){
          this.orderBy='moneyUp'
        }else{
          this.orderBy='moneyDown'
        }
      },
      clickDate(){
        if(this.orderBy=='dateDown'){
          this.orderBy='dateUp'
        }else{
          this.orderBy='dateDown'
        }
      },
      updateDonate(donate){
        this.donate=donate;
        this.isShowDrawerDonate=true;
      },
      saveDonate(){
        this.donate=null;
        this.isShowDrawerDonate=true;
      },
      initData(){
        this.loading=true;
        this.$axios({
          method: "get",
          url: "/api/donates",
        }).then((response) => {
          console.log("返回捐款数据↓");
          let info = response.data;
          console.log(info);
          if(info.flag){
            this.donates=info.data;
          }else{
            console.error(info.errorMsg);
          }
          this.loading=false;
        }).catch((error) => {
          console.error(error);
          this.loading=false;
        });
      }, 
      // 准备删除
      readyToDeleteDonate(donate){
        this.$toast({
          showCancel:true,
          t1:'提示',//弹窗的标题
          t2: '确定要<span class="red">删除</span>好心人<b>'+donate.name+'</b>?',//弹窗的内容
          type:'warning'
        }).then(()=>{
          this.deleteDonate(donate);
        }).catch(()=>{
          console.log('取消删除');
        });
      },

      // 删除好心人
      deleteDonate(donate){
        let that = this;
        this.$axios({
            method:'delete',
            url:'/api/donate/'+donate.id,
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
              this.initData();
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

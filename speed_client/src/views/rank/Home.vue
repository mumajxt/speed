<template>
  <div id="home">
    <!-- 主要信息 -->
    <div id="main">
      <!-- banner菜单 -->
      <div id="banner" class="card">
        <div class="menus">
          <div class="menu" @click="isShowDrawerChoseRecordType=!isShowDrawerChoseRecordType">
              <div class="car pic">
                <span class="icon iconfont iconcar1"></span>
                <span class="icon iconfont iconcar2"></span>
              </div>
              <div class="select">
                <h4 class="car_rank label" v-text="typeCH"></h4>
                <span class="icon iconfont iconxiala" :class="isShowDrawerChoseRecordType?'rotate180-animation':''"></span>
              </div>
          </div>
          <div class="menu" @click="isShowDrawerChosePetType=!isShowDrawerChosePetType" :class="pet=='withOutPet'?'btn_grey':''" v-if="isShowPet">
              <div class="pet pic">
                <span class="icon iconfont iconpet1"></span>
                <span class="icon iconfont iconpet2"></span>
              </div>
              <div class="select">
                <h4 class="is_pet label" v-text="petCH"></h4>
                 <span class="icon iconfont iconxiala" :class="isShowDrawerChosePetType?'rotate180-animation':''"></span>
              </div>
          </div>
          
          <div class="menu" @click="isShowDrawerChoseEcuType=!isShowDrawerChoseEcuType" :class="ecu=='withOutEcu'?'btn_grey':''" v-if="isShowEcu">
              <div class="ecu pic">
                <span class="icon iconfont iconecu1"></span>
                <span class="icon iconfont iconecu2"></span>
                <span class="icon iconfont iconecu3"></span>
                <span class="icon iconfont iconecu4"></span>
                <span class="icon iconfont iconecu5"></span>
              </div>
              <div class="select">
                <h4 class="is_ecu label" v-text="ecuCH">ECU</h4>
                 <span class="icon iconfont iconxiala" :class="isShowDrawerChoseEcuType?'rotate180-animation':''"></span>
              </div>
          </div>
        </div>
      </div>



    <div id="list" v-if="records.length>0">
      <!-- 筛选栏 -->
      <div id="list-nav" class="card">
        <div class="input-wrapper list-nav-input">
            <div class="input-icon">
              <i class="iconfont iconsousuo"></i>
            </div>
            <input type="text" placeholder="搜索地图/赛车/车手"  v-model="searchKey">
            <span class="input-icon">
              <i v-if="searchKey" class="iconfont iconclose" @click="searchKey=''"></i>
            </span>
          </div>
          <div class="label-switch">
              <span class="default" @click="linkToView('default')" :class="{'active':view=='default'}">默认</span>
              <span class="statistics" @click="linkToView('statistics')" :class="{'active':view=='statistics'}">统计</span>
              <span class="new" @click="linkToView('new');" :class="{'active':view=='new'}">历史</span>
          </div>
      </div>

      <!-- 默认分类的记录 -->
      <ViewDefault
      v-if="view=='default'"
      :type="type"
      :pet="pet"
      :ecu="ecu"
      :searchKey="searchKey"
      :recordsOut="records"
      :loading="loading"
      @showDetail="showDetail">
      </ViewDefault>
    
      <!-- 最新记录 -->
      <ViewStatistics 
      v-if="view=='statistics'"
      :type="type"
      :pet="pet"
      :ecu="ecu"
      :searchKey="searchKey"
      :recordsOut="records"
      :loading="loading"
      @showDetail="showDetail">
      </ViewStatistics>

      <!-- 最新记录 -->
      <ViewNew 
      v-if="view=='new'"
      :type="type"
      :pet="pet"
      :ecu="ecu"
      :screenTrackOut="screenTrack"
      :screenPlayerOut="screenPlayer"
      :screenCarOut="screenCar"
      :searchKey="searchKey"
      :recordsInRankOut="records"
      @showDetail="showDetail">
      </ViewNew>
    </div>
    <div class="short_info" v-else>
      <p v-if="loading">
        <Loading>加载中...</Loading>
      </p>
      <span v-else>暂未收录任何记录，请选择其他类型~</span>
    </div>
    </div>
    

    <!-- 侧边栏 -->
    <div id="side">
      <div class="side-wrapper">
         <!-- posts -->
        <div id="post" class="card">
          <p>
            <span class="green">●</span>
            8月1日起收录T级赛车计时
          </p>
          <router-link to="/rank/about" class="btn btn-yellow bold">
            <span>我要投稿</span>
          </router-link>
        </div>

        <!-- 联系方式 -->
        <!-- <div id="contact" class="card">
          <p @click="joinQQGroup()"><span class="qq icon iconfont iconqq"></span>加入交流群</p>
          <p @click="QQFeedback()"><span class="qq icon iconfont iconqq"></span>网站建议与反馈</p>
          <textarea readonly id="qq_group" style="position: absolute;top: 0;left: 0;opacity: 0;z-index: -10;">620736393</textarea>
          <textarea readonly id="qq_feedback" style="position: absolute;top: 0;left: 0;opacity: 0;z-index: -10;">1491822925</textarea>
        </div> -->

        <!-- tips -->
        <div id="notice" class="card">
          <p>兴趣使然建立的网站</p>
          <p>谢谢支持~</p>
          <router-link to="/rank/donate" class="btn btn-green bold">
            <span>捐助本站</span>
          </router-link>
        </div>

        
      </div>
     

    </div>



    <!-- 选择类型弹框 -->
    <DrawerChoseRecordType
    :isShowAll="true"
    :visible.sync="isShowDrawerChoseRecordType"
    :typeOut="type"
    @close="isShowDrawerChoseRecordType=false"
    @choseRecordType="linkToType">
    </DrawerChoseRecordType>

    <DrawerChosePetType
    :visible.sync="isShowDrawerChosePetType"
    :typeOut="pet"
    @close="isShowDrawerChosePetType=false"
    @chosePetType="linkToPet">
    </DrawerChosePetType>

    <DrawerChoseEcuType
    :visible.sync="isShowDrawerChoseEcuType"
    :typeOut="ecu"
    @close="isShowDrawerChoseEcuType=false"
    @choseEcuType="linkToEcu">
    </DrawerChoseEcuType>

    <RecordDetailDrawerUser 
    :record="record"
    @showMoreAboutTrack="showMoreAboutTrack"
    @showMoreAboutPlayer="showMoreAboutPlayer"
    @showMoreAboutCar="showMoreAboutCar"
    :visible.sync="isShowRecordDetailDrawer" 
    @close="isShowRecordDetailDrawer=false">
    </RecordDetailDrawerUser>

   
    
  </div>

</template>

<script>
import DrawerChoseRecordType from "@/components/drawer/chose/DrawerChoseRecordType";
import DrawerChosePetType from "@/components/drawer/chose/DrawerChosePetType";
import DrawerChoseEcuType from "@/components/drawer/chose/DrawerChoseEcuType";
import RecordDetailDrawerUser from '@/components/drawer/detail/RecordDetailDrawerUser';
import Loading from '@/components/Loading';

//const { export_json_to_excel } = require('@/vendor/Export2Excel');
export default {
  name: "home",
  components: {
    ViewNew:() => import('@/views/ViewNew'),
    ViewStatistics:() => import('@/views/ViewStatistics'),
    ViewDefault:() => import('@/views/ViewDefault'),
    DrawerChoseRecordType,
    DrawerChosePetType,
    DrawerChoseEcuType,
    RecordDetailDrawerUser,
    Loading
  },
  created(){
    let type = this.$route.query.type;
    let pet = this.$route.query.pet;
    let ecu = this.$route.query.ecu;
    let view = this.$route.query.view;

    this.type=type?type:this.type;
    this.pet=pet?pet:this.pet;
    this.ecu=ecu?ecu:this.ecu;
    this.view=view?view:this.view;
    this.initRecordsInRank();
  },
  mounted() {

  },
  watch:{
    '$route.query.view':{
      handler(view){
        this.view=view?view:'default';
      }
    },
    '$route.query.type':{
      handler(type){
        this.type=type?type:'all';
        this.initRecordsInRank();
      }
    },
    '$route.query.pet':{
      handler(pet){
        this.pet=pet?pet:'all';
        this.initRecordsInRank();
      }
    },
    '$route.query.ecu':{
      handler(ecu){
        this.ecu=ecu?ecu:'all';
        this.initRecordsInRank();
      }
    }
  },
  computed:{
    typeCH(){
      switch(this.type){
        case 'all':return '不限';
        case 't':return 'T车';
        case 'a':return 'A车';
        case 'b':return 'B车';
        case 'c':return 'C车';
        case 'd':return 'D车';
        case 'x':return '悬浮车';
        case 'l1':return 'L1滑板';
        case 'm1':return 'M1摩托';
        case 'm2':return 'M2摩托';
        case 'undrift':return '抓地';
        case 'asiaCup':return '亚洲杯';
        case 'sCup':return 'S联赛';
        default :return '未知类型';
      }
    },
    petCH(){
      switch(this.pet){
        case 'all':return '不限';
        case 'withPet':return '有宠';
        case 'withOutPet':return '无宠';
        default :return '未知类型';
      }
    },
    ecuCH(){
      switch(this.ecu){
        case 'all':return '不限';
        case 'withEcu':return '有E';
        case 'withOutEcu':return '无E';
        default :return '未知类型';
      }
    }
  },

  data(){
    return {
      // 全局信息
      type:'all',
      pet:'all',
      ecu:'all',
      view:'default',
      loading:false,
      isShowDrawerChoseRecordType:false,
      isShowDrawerChosePetType:false,
      isShowDrawerChoseEcuType:false,
      isShowRecordDetailDrawer:false,
      isShowPet:true,
      isShowEcu:true,
      records:[],
      record:null,//展示中的record
      searchKey:'',
      introduce:'',
      screenTrack:null,
      screenPlayer:null,
      screenCar:null,
    }
  },

  methods:{
    //全局方法
    initRecordsInRank(){

      this.loading=true;
      this.records=[];
      let params = {
        type:this.type,
        pet:this.pet,
        ecu:this.ecu
      };
      this.$axios({
        method: "get",
        url: "/api/records",
        params: params
      }).then((response) => {
        let info = response.data;
        console.log("--------------------------");
        console.log("返回在榜记录");
        if(info.flag){
          this.records=info.data;
          console.log(info.data);
        }else{
          console.error(info.errorMsg);
        }
        console.log("--------------------------");
        this.loading=false;
      }).catch((error) => {
        console.error(error);
        this.loading=false;
      });
    },   
    linkToType(type){
      let query={};
      if(type!='all'){
        query['type']=type;
      }
      if(this.pet!='all'){
        query['pet']=this.pet;
      }
      if(this.ecu!='all'){
        query['ecu']=this.ecu;
      }
      if(this.view!='default'){
        query['view']=this.view;
      }
      this.$router.push(
        {
          query:query
        }
      );
    },
    linkToPet(pet){
      let query={};
      if(pet!='all'){
        query['pet']=pet;
      }
      if(this.type!='all'){
        query['type']=this.type;
      }
      if(this.ecu!='all'){
        query['ecu']=this.ecu;
      }
      if(this.view!='default'){
        query['view']=this.view;
      }
      this.$router.push(
        {
          query:query
        }
      );
    },
    linkToEcu(ecu){
      let query={};
      if(ecu!='all'){
        query['ecu']=ecu;
      }
      if(this.type!='all'){
        query['type']=this.type;
      }
      if(this.pet!='all'){
        query['pet']=this.pet;
      }
      if(this.view!='default'){
        query['view']=this.view;
      }
      this.$router.push(
        {
          query:query
        }
      );
    },
    linkToView(view){
      let query={};
      if(view!='default'){
        query['view']=view;
      }
      if(this.type!='all'){
        query['type']=this.type;
      }
      if(this.pet!='all'){
        query['pet']=this.pet;
      }
      if(this.ecu!='all'){
        query['ecu']=this.ecu;
      }
      this.$router.push(
        {
          query:query
        }
      );
    },



    showDetail(record){
      this.record = record;
      this.isShowRecordDetailDrawer=true;
    },
    /* joinQQGroup: function() {
      let input = document.getElementById("qq_group");
      input.select(); // 选中文本
      document.execCommand("copy"); // 执行浏览器复制命令
      this.$toast({
        showCancel:false,
        t1:':)',//弹窗的标题
        t2: "已复制群号("+input.value+")，去QQ粘贴搜索吧~",//弹窗的内容
        type:'success'
      });
      //alert("已复制群号（"+input.value+"），去QQ粘贴搜索吧~");
    },
    QQFeedback: function() {
      let input = document.getElementById("qq_feedback");
      input.select(); // 选中文本
      document.execCommand("copy"); // 执行浏览器复制命令
      //alert("已复制QQ（"+input.value+"），去QQ粘贴搜索吧~");
      this.$toast({
        showCancel:false,
        t1:':)',//弹窗的标题
        t2: "已复制个人账号("+input.value+")，去QQ粘贴搜索吧~",//弹窗的内容
        type:'success'
      });
    }, */
    

    showMoreAboutTrack(track){
      this.isShowRecordDetailDrawer=false;
      this.linkToView('new');
      this.screenPlayer=null;
      this.screenCar=null;
      this.screenTrack=track;
      //this.choseTrack(track);
    },
    showMoreAboutPlayer(player){
      this.isShowRecordDetailDrawer=false;
      this.linkToView('new');
      this.screenTrack=null;
      this.screenCar=null;
      this.screenPlayer=player;
      //this.chosePlayer(player);
    },
    showMoreAboutCar(car){
      this.isShowRecordDetailDrawer=false;
      this.linkToView('new');
      this.screenPlayer=null;
      this.screenTrack=null;
      this.screenCar=car;
      //this.choseCar(car);
    },
    /* clearScreen(){
      this.screenTrack=null;
      this.screenPlayer=null;
      this.screenCar=null;
    } */
  }

};
</script>

<style lang="less" scoped>
/* /deep/ :focus {
  outline: 0;
} */
#home {
  //主要信息
  #main {

    // banner菜单
    #banner {
      .menus {
        display: flex;
      
        .menu:first-child {
          margin-left: 0;
        }
        .menu:last-child {
          margin-right: 0;
        }
        
        // 普通按钮
        .menu{
          flex: 1;
          margin: 0 0.5rem;
          cursor: pointer;
          background: @c_yellow_bg;
          border-radius: 1rem;
          display: flex;
          align-items: center;
          flex-wrap: wrap;
          justify-content: center;
    
          // &:hover {
          //   .car {
          //     .iconcar1 {
                
          //     }
          //     .iconcar2 {
                
          //     } 
          //   }
          // }
    
          // 赛道标
          .pic {
            position: relative;
            display: inline-block;
            height: 4rem;
            width: 4rem;
            margin: 0.2rem;
            span {
              position: absolute;
              top: 0;
              left: 0;
              color: @c_yellow;
              height: 4rem;
              line-height: 4rem;
              font-size: 4rem;
            }
          }
  
          .car {
            .iconcar1 {
              color: @c_yellow;
              background-image: -webkit-linear-gradient(bottom,@c_yellow,white); 
              background-clip: text; 
              -webkit-background-clip: text; 
              -webkit-text-fill-color: transparent; 
            }
            .iconcar2 {
              color: @c1;
            } 
          }
    
          .pet {
            .iconpet1 {
              color: @c_yellow;
              background-image: -webkit-linear-gradient(bottom,@c_yellow,#fff8ee); 
              background-clip: text; 
              -webkit-background-clip: text; 
              -webkit-text-fill-color: transparent; 
            }
            .iconpet2 {
              color: @c1;
            }
          }
    
          .ecu {
            .iconecu1 {
              color: @c_yellow;
              background-image: -webkit-linear-gradient(left,white,@c_yellow); 
              background-clip: text; 
              -webkit-background-clip: text; 
              -webkit-text-fill-color: transparent; 
              text-shadow: 0 0 0.1rem @c_yellow;
              opacity: 0.5;
            }
            .iconecu2 {
              color: @c_yellow;
              background-image: -webkit-linear-gradient(top,@c_yellow,#fff8ee,white); 
              background-clip: text; 
              -webkit-background-clip: text; 
              -webkit-text-fill-color: transparent; 
              text-shadow: 0 0 0.5rem @c_yellow;
            }
            .iconecu3 {
              color: @c_yellow;
              background-image: -webkit-linear-gradient(top,#fff8ee,white); 
              background-clip: text; 
              -webkit-background-clip: text; 
              -webkit-text-fill-color: transparent; 
              
            }
            .iconecu4 {
              color: @c1;
              background-image: -webkit-linear-gradient(left,#303133,#fff8ee); 
              background-clip: text; 
              -webkit-background-clip: text; 
              -webkit-text-fill-color: transparent; 
            }
            .iconecu5 {
              color: white;
              background-image: -webkit-linear-gradient(left,#fff8ee,white); 
              background-clip: text; 
              -webkit-background-clip: text; 
              -webkit-text-fill-color: transparent;
              text-shadow: 0 0 0.2rem white;
            }
          }
    
          .select {
            margin: 0.5rem 0.2rem;
            width: 6rem;
            display: flex;
            align-items: center;
            justify-content: center;
            .iconfont {
              font-size: 1.2rem;
              margin-left: 0.1rem;
            }
            .iconxiala {
              color: @c_yellow;
            }
            .iconchecked{
              color: @c_green;
            }
            .iconban{
              color: @c3;
            }
    
          }
        }
    
        // 禁用后的按钮
        .btn_grey {
          background: @c_bg;
    
          .pet {
            .iconpet1 {
              color: @c3;
              background-image: -webkit-linear-gradient(bottom,@c3,#ffffff); 
              background-clip: text; 
              -webkit-background-clip: text; 
              -webkit-text-fill-color: transparent; 
            }
            .iconpet2 {
              color: @c1;
            }
          }
    
          .ecu {
            .iconecu1 {
              color: @c3;
              background-image: -webkit-linear-gradient(left,white,@c3);
              text-shadow: 0 0 0.1rem @c3;
            }
            .iconecu2 {
              color: @c3;
              background-image: -webkit-linear-gradient(top,@c3,#fff8ee,white);
              text-shadow: 0 0 0.5rem @c3;
            }
            .iconecu3 {
              color: @c3;
              background-image: -webkit-linear-gradient(top,#fff8ee,white);
              
            }
            .iconecu4 {
              color: @c1;
              background-image: -webkit-linear-gradient(left,#303133,#fff8ee);
            }
            .iconecu5 {
              color: white;
              background-image: -webkit-linear-gradient(left,#fff8ee,white);
            }
          }
    
          .select {
            .label {
              color: @c2;
            }
            .iconxiala{
              color: @c3;
            }
          }
        }
      }
    }

    
  }
  // 侧边栏
  #side {
    .side-wrapper{
      // 注释
      #notice,#post {
        font-size: 1rem;
        p {
          color: @c2;
          margin-bottom: 0.5rem;
        }
      }
    
      //联系我们
      #contact {
        font-size: 1rem;
        color: @c2;
        p {
          cursor: pointer;
          margin-top: 0.5rem;
          &:hover {
            color: @c1;
          }
          &:first-child {
            margin-top: 0;
          }
          .qq {
            margin-right: 0.1rem;
            color: @c_blue;
            font-size: 1rem;
          }
          a {
            color: @c2;
          }
        }
      }
    } 
  }
}

@media all and (min-width: 768px){
  #home {
    display: flex;
    #main {
      flex: 2;
    }
    #side {
      flex: 1;
      margin-left: 1rem;
      .side-wrapper{
        position: sticky;
        position: -webkit-sticky;
        top: 5rem;
        z-index: 98;
      }
    }
  }
}


#list-nav{
  display: flex;
  .list-nav-input{
    flex: 1;
    margin-right: 1rem;
    border-radius: 1.2rem;
    padding: 0 0.3rem;
  }
}

</style>
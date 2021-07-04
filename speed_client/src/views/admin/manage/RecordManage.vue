<template>
  <div id="record_manage" class="manage-container">
      <div class="navs card">
        <div class="nav1">
          <div class="nav1-items">
            <div class="nav1-item nav1-item-selected">
              <h3>记录</h3>
            </div>
          </div>

          <div class="input-wrapper nav1-input">
            <div class="input-icon">
              <i class="iconfont iconsousuo"></i>
            </div>
            <input type="text" placeholder="搜索ID/地图/选手/赛车/宠物/ECU"  v-model="searchKey">
            <span class="input-icon">
              <i v-if="searchKey" class="iconfont iconclose" @click="searchKey=''"></i>
            </span>
          </div>

          <div @click="saveRecord()" class="nav1-btn btn btn-yellow">
            <span class="icon iconfont iconclose rotate45"></span>
          </div>

        </div>


        <div class="nav2">
          <div class="nav2-car nav2-items">
            <div @click="linkToType('all')" class="btn-sm btn-label nav2-item" :class="type=='all'?'btn-label-yellow bold':''">
              <span>全部</span>
              <!-- <span class="count" v-text="recordsAll.length"></span> -->
            </div>
            <div @click="linkToType('a')" class="btn-sm btn-label nav2-item" :class="type=='a'?'btn-label-yellow bold':''" >
              <span>A车</span>
              <!-- <span class="count" v-text="as.length"></span> -->
            </div>
            <div @click="linkToType('undrift')" class="btn-sm btn-label nav2-item" :class="type=='undrift'?'btn-label-yellow bold':''">
              <span>A车(抓地)</span>
              <!-- <span class="count" v-text="undrifts.length"></span> -->
            </div>
            <div @click="linkToType('x')" class="btn-sm btn-label nav2-item" :class="type=='x'?'btn-label-yellow bold':''">
              <span>X悬浮车</span>
              <!-- <span class="count" v-text="xs.length"></span> -->
            </div>
            <div @click="linkToType('b')" class="btn-sm btn-label nav2-item" :class="type=='b'?'btn-label-yellow':''">
              <span>B车</span>
              <!-- <span class="count" v-text="bs.length"></span> -->
            </div>
            <div @click="linkToType('c')" class="btn-sm btn-label nav2-item" :class="type=='c'?'btn-label-yellow':''">
              <span>C车</span>
              <!-- <span class="count" v-text="cs.length"></span> -->
            </div>
            <div @click="linkToType('d')" class="btn-sm btn-label nav2-item" :class="type=='d'?'btn-label-yellow':''">
              <span>D车</span>
              <!-- <span class="count" v-text="ds.length"></span> -->
            </div>
            <div @click="linkToType('l1')" class="btn-sm btn-label nav2-item" :class="type=='l1'?'btn-label-yellow':''">
              <span>L1滑板</span>
              <!-- <span class="count" v-text="l1s.length"></span> -->
            </div>
            <div @click="linkToType('m1')" class="btn-sm btn-label nav2-item" :class="type=='m1'?'btn-label-yellow':''">
              <span>M1摩托</span>
              <!-- <span class="count" v-text="m1s.length"></span> -->
            </div>
            <div @click="linkToType('m2')" class="btn-sm btn-label nav2-item" :class="type=='m2'?'btn-label-yellow':''">
              <span>M2摩托</span>
              <!-- <span class="count" v-text="m2s.length"></span> -->
            </div>
            <div @click="linkToType('sCup')" class="btn-sm btn-label nav2-item" :class="type=='sCup'?'btn-label-yellow':''">
              <span>S联赛</span>
              <!-- <span class="count" v-text="sCups.length"></span> -->
            </div>
            <div @click="linkToType('asiaCup')" class="btn-sm btn-label nav2-item" :class="type=='asiaCup'?'btn-label-yellow':''">
              <span>亚洲杯</span>
              <!-- <span class="count" v-text="asiaCups.length"></span> -->
            </div>
          </div>
        </div>

        <div class="nav3">
          <div class="nav3-pet nav3-items">
            <div @click="linkToPet('all')" class="btn-sm btn-label nav3-item" :class="pet=='all'?'btn-label-yellow bold':''">
              <span>全部</span>
              <!-- <span class="count" v-text="tempCarRecords.length"></span> -->
            </div>
            <div @click="linkToPet('withPet')" class="btn-sm btn-label nav3-item" :class="pet=='withPet'?'btn-label-yellow bold':''">
              <span>有宠物</span>
              <!-- <span class="count" v-text="withPets.length"></span> -->
            </div>
            <div @click="linkToPet('withOutPet')" class="btn-sm btn-label nav3-item" :class="pet=='withOutPet'?'btn-label-yellow bold':''">
              <span>无宠物</span>
              <!-- <span class="count" v-text="withOutPets.length"></span> -->
            </div>
          </div>
        </div>


        <div class="nav4">
          <div class="nav4-ecu nav4-items">
            <div @click="linkToEcu('all')" class="btn-sm btn-label nav4-item" :class="ecu=='all'?'btn-label-yellow bold':''">
              <span>全部</span>
              <!-- <span class="count" v-text="tempPetRecords.length"></span> -->
            </div>
            <div @click="linkToEcu('withEcu')" class="btn-sm btn-label nav4-item" :class="ecu=='withEcu'?'btn-label-yellow bold':''">
              <span>有ECU</span>
              <!-- <span class="count" v-text="withEcus.length"></span> -->
            </div>
            <div @click="linkToEcu('withOutEcu')" class="btn-sm btn-label nav4-item" :class="ecu=='withOutEcu'?'btn-label-yellow bold':''">
              <span>无ECU</span>
              <!-- <span class="count" v-text="withOutEcus.length"></span> -->
            </div>
          </div>
        </div>
      </div>
      
      <!-- 短信息 -->
      <div class="short_info">
        <p v-if="loading">
          <Loading>加载中...</Loading>
        </p>
        <p v-else>
          <span>
            <span v-if="searchKey">搜索到</span>
            <span v-else>共</span>

            <span v-if="screenCar||screenTrack||screenPlayer" class="yellow" v-text="pageBean.screenCount+' / '"></span>
            <span class="yellow" v-text="totalCount"></span>条记录，
            <span class="yellow">黄色</span>记录为当前分类在榜记录<span class="more">，<span class="yellow underline">下划线</span>为有视频链接</span>
          </span>  
        </p>
      </div>

      
      <div class="table card" 
        v-infinite-scroll="load"
        infinite-scroll-disabled="disabled"
        infinite-scroll-distance="200"
        infinite-scroll-immediate = "true">

        <div class="th">
          <div class="id th-btn"
          :class="{'yellow':orderBy=='idUp'||orderBy=='idDown'}" 
          @click="clickId()">
            <span>ID</span>
            <i class="iconfont iconxiangxia" 
            :class="orderBy=='idUp'?'rotate180':(orderBy=='idDown'?'yellow':'grey2')">
            </i>
          </div>

          <div class="track_name th-btn" 
          :class="screenTrack?'yellow':''" 
          @click="isShowDrawerChoseTrack=true">
            <span v-text="screenTrack?screenTrack.name:'地图'">地图</span>
            <i class="iconfont iconFilter" :class="screenTrack?'yellow':'grey2'"></i>
          </div>

          <div class="time th-btn" 
          :class="{'yellow':orderBy=='timeUp'||orderBy=='timeDown'}" 
          @click="clickOrderTime()">
            <span>记录</span>
            <i 
            class="iconfont iconxiangxia" 
            :class="orderBy=='timeUp'?'rotate180':(orderBy=='timeDown'?'yellow':'grey2')">
            </i>
          </div>

          <div class="player_name th-btn" :class="screenPlayer?'yellow':''"  @click="isShowDrawerChosePlayer=true">
            <span v-text="screenPlayer?screenPlayer.name:'车手'">车手</span>
            <i class="iconfont iconFilter" :class="screenPlayer?'yellow':'grey2'"></i>
          </div>

          <div class="car_name th-btn" :class="screenCar?'yellow':''"  @click="isShowDrawerChoseCar=true">
            <span v-text="screenCar?screenCar.name:'赛车'">赛车</span>
            <i class="iconfont iconFilter" :class="screenCar?'yellow':'grey2'"></i>
          </div>

          <div class="pet_name">宠物</div>
          <div class="ecu_name">ECU</div>
          <div class="date th-btn" 
          :class="{'yellow':orderBy=='dateUp'||orderBy=='dateDown'}"
          @click="clickOrderDate()">
            <span>日期</span>
            <i 
            class="iconfont iconxiangxia" 
            :class="orderBy=='dateUp'?'rotate180':(orderBy=='dateDown'?'':'grey2')">
            </i>
          </div>
          <div class="operation">操作</div>
        </div>

        <div class="tr" v-for="record in records" :key="record.id" @click="showRecordDetail(record)">
          <div class="id">
            <span v-text="record.id"></span>
          </div>
          <div class="track_name">
            <span v-text="record.track.name"></span>
          </div>
          <div class="time" :class="record.isIn?'yellow':'grey'">
            <span v-text="record.time" :class="{'url':record.videoUrl}"></span>
          </div>
          <div class="player_name">
            <span v-text="record.player.name"></span>
          </div>
          <div class="car_name">
            <span v-text="record.car.name"></span>
          </div>
          <div class="pet_name">
            <span v-if="record.pet" v-text="record.pet.name"></span>
            <span v-else class="icon iconfont iconclose grey"></span>
          </div>
          <div class="ecu_name">
            <span v-if="record.ecu" v-text="record.ecu.name"></span>
            <span v-else class="icon iconfont iconclose grey"></span>
          </div>
          <div class="date">
            <span v-text="record.date"></span>
          </div>
          <div class="operation" @click.stop>
            <span @click="updateRecord(record)" class="btn btn-yellow iconfont iconupdate"></span>
            <span @click="readyToDeleteRecord(record)" class="btn btn-red  iconfont icondelete"></span>
          </div>
        </div>
      </div>

      <div class="short_info center">
        <Loading v-if="loading">加载中...</Loading>
      </div>


    <DrawerRecord 
    :petLabel="pet" 
    :ecuLabel="ecu" 
    :type="type" 
    :recordOut="record"
    v-if="isShowDrawerRecord"
    @initData="initData" 
    @close="closeDrawerRecord"> 
    </DrawerRecord>

    <RecordDetailDrawer 
    :record="record" 
    :visible.sync="isShowRecordDetailDrawer" 
    @close="isShowRecordDetailDrawer=false" 
    @updateRecord="updateRecord"
    @readyToDeleteRecord="readyToDeleteRecord"
    @showMoreAboutTrack="showMoreAboutTrack"
    @showMoreAboutPlayer="showMoreAboutPlayer"
    @showMoreAboutCar="showMoreAboutCar">
    </RecordDetailDrawer>



    <UserDrawerChoseTrack 
    v-if="isShowDrawerChoseTrack"
    :trackOut="screenTrack"
    :type="type"
    :pet="pet"
    :ecu="ecu"
    @close="isShowDrawerChoseTrack=false"
    @choseTrack="choseTrack">
    </UserDrawerChoseTrack>

    <UserDrawerChosePlayer 
    v-if="isShowDrawerChosePlayer"
    :playerOut="screenPlayer"
    :type="type"
    :pet="pet"
    :ecu="ecu"
    @close="isShowDrawerChosePlayer=false"
    @chosePlayer="chosePlayer">
    </UserDrawerChosePlayer>

    <UserDrawerChoseCar 
    v-if="isShowDrawerChoseCar"
    :carOut="screenCar"
    :type="type"
    :pet="pet"
    :ecu="ecu"
    @close="isShowDrawerChoseCar=false"
    @choseCar="choseCar">
    </UserDrawerChoseCar>

  </div>
</template>

<script>
import RecordDetailDrawer from '@/components/drawer/detail/RecordDetailDrawer';

export default {
  name: "recordManage",
  components: {
    RecordDetailDrawer,
    DrawerRecord:() => import('@/components/drawer/DrawerRecord'),
    UserDrawerChoseTrack:() => import('@/components/drawer/chose/UserDrawerChoseTrack'),
    UserDrawerChosePlayer:() => import('@/components/drawer/chose/UserDrawerChosePlayer'),
    UserDrawerChoseCar:() => import('@/components/drawer/chose/UserDrawerChoseCar'),
    Loading:() => import('@/components/Loading'),
  },
  created(){
    let type = this.$route.query.type;
    let pet = this.$route.query.pet;
    let ecu = this.$route.query.ecu;

    this.type=type?type:this.type;
    this.pet=pet?pet:this.pet;
    this.ecu=ecu?ecu:this.ecu;
  },
  mounted(){
    this.initData();
  },
  watch:{
    '$route.query.type':{
      handler(type,old){
        this.type=type?type:'all';
        this.initData();
      }
    },
    '$route.query.pet':{
      handler(pet){
        this.pet=pet?pet:'all';
        this.initData();
      }
    },
    '$route.query.ecu':{
      handler(ecu){
        this.ecu=ecu?ecu:'all';
        this.initData();
      }
    },
    searchKey:{
      handler(){
        if(this.timer){
          clearTimeout(this.timer);
        }
        this.timer = setTimeout(this.initData,1000);
      }
    },
  },
  computed: {
    noMore () {
      return this.pageBean.records.length < this.pageBean.pageSize;
    },
    disabled () {
      return this.loading || this.noMore
    },
  },
  data() {
    return {
      loading:false,
      timer:null,

      pageBean:{
        totalCount:0,
        screenCount:0,
        totalPage:0,
        currentPage:1,
        pageSize:16,
        records:[]
      },
      totalCount:0,

      records:[],
      recordsInRank:[],
      screenTrack:null,
      screenCar:null,
      screenPlayer:null,
      isShowDrawerChoseTrack:false,
      isShowDrawerChoseCar:false,
      isShowDrawerChosePlayer:false,

      searchKey:'',
      record:null,
      //skin:null,
      isShowDrawerRecord:false,
      isShowRecordDetailDrawer:false,
      orderBy:'idDown',
      nav1SelectedItem:'record',
      type:'all',
      pet:'all',
      ecu:'all',
      //types:['a','b','c','d','x','l1','m1','m2','sCup','asia-cup','undrift'],      
    };
  },
  methods: {
    //初始化数据
    initData(){
      this.records=[];
      this.pageBean.currentPage=1;
      this.initRecordsInRank().then(()=>{
        this.listRecentRecords();
      }).catch((error)=>{
        this.$toast({
          showCancel:true,
          t1:'提错误',//弹窗的标题
          t2: error,//弹窗的内容
          type:'error'
        })
      })
      
    },
    initRecordsInRank(){
      return new Promise((resolve,reject)=>{
        this.loading=true;
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
          console.log("返回榜单数据↓");
          let info = response.data;
          console.log(info);
          if(info.flag){
            this.recordsInRank=info.data;
            resolve();
          }else{
            console.error(info.errorMsg);
            reject(info.errorMsg);
          }
          this.loading=false;
        }).catch((error) => {
          console.error(error);
          this.loading=false;
          reject(error);
        });
      });
    },  
    //发送请求
    listRecentRecords(){
      this.loading = true 
      console.log("加载更多数据...");
      let params = {
        type:this.type,
        pet:this.pet,
        ecu:this.ecu,
        currentPage:this.pageBean.currentPage,
        pageSize:this.pageBean.pageSize,
        searchKey:this.searchKey,

        trackId:this.screenTrack?this.screenTrack.id:null,
        playerId:this.screenPlayer?this.screenPlayer.id:null,
        carId:this.screenCar?this.screenCar.id:null,
        orderBy:this.orderBy
      };
      this.$axios({
        method: "get",
        url: "/api/recentRecords",
        params: params,
      }).then((response) => {
       let info = response.data;
        if (info.flag) {   
          /* if(this.pageBean.currentPage!=1info.data.currentPage-this.pageBean.currentPage==1){
            
          } */
          this.pageBean = info.data;
          console.log("--------------------------");
          console.log("返回第"+this.pageBean.currentPage+"页记录");
          console.log(this.pageBean);
          console.log("--------------------------");
          this.pageBean.records.forEach(record=>{
            this.recordsInRank.forEach(r=>{
              if(r.id==record.id){
                record.isIn=true;
              }
            })
          });
          this.records=this.records.concat(this.pageBean.records);
          //只在第一页计算总页数
          if(this.pageBean.currentPage==1){
            this.totalCount=this.pageBean.totalCount;
          }
          
        }else {
          console.log(info.errorMsg);
        }
        this.loading=false;
      }).catch((error) => {
        console.error(error);
        this.loading=false;
      }); 
    },
    load() {
      this.pageBean.currentPage++;
      this.listRecentRecords();
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
      this.$router.push(
        {
          query:query
        }
      );
    },


    closeDrawerRecord(){
      this.isShowDrawerRecord=false;
      this.record = null;
    },
    showRecordDetail(record){
      this.record = record;
      this.isShowRecordDetailDrawer=true;
    },

    /*
    标头筛选
    */
    clickId(){
      this.orderBy=='idUp'?this.orderBy='idDown':(this.orderBy=='idDown'?this.orderBy='idUp':this.orderBy='idDown');
      this.initData();
    },
    clickOrderTime(){
      this.orderBy=='timeUp'?this.orderBy='timeDown':(this.orderBy=='timeDown'?this.orderBy='timeUp':this.orderBy='timeDown');
      this.initData();
    },
    clickOrderDate(){
      this.orderBy=='dateUp'?this.orderBy='dateDown':(this.orderBy=='dateDown'?this.orderBy='dateUp':this.orderBy='dateDown');
      this.initData();
    },
    choseTrack(track){
      this.screenTrack=track;
      this.pageBean.currentPage=1;
      this.initData();
    },
    chosePlayer(player){
      this.screenPlayer=player;
      this.pageBean.currentPage=1;
      this.initData();
    },
    choseCar(car){
      this.screenCar=car;
      this.pageBean.currentPage=1;
      this.initData();
    },
    showMoreAboutTrack(track){
      this.screenPlayer=null;
      this.screenCar=null;
      this.screenTrack=track;
      this.choseTrack(track);
    },
    showMoreAboutPlayer(player){
      this.screenTrack=null;
      this.screenCar=null;
      this.screenPlayer=player;
      this.chosePlayer(player);
    },
    showMoreAboutCar(car){
      this.screenPlayer=null;
      this.screenTrack=null;
      this.screenCar=car;
      this.choseCar(car);
    },


    updateRecord(record){
      this.record=record;
      this.isShowDrawerRecord=true;
    },
    saveRecord(){
      this.record=null;
      this.isShowDrawerRecord=true;
    },
    // 准备删除
    readyToDeleteRecord(record){
      this.$toast({
        showCancel:true,
        t1:'提示',//弹窗的标题
        t2: '确定要<span class="red">删除</span>记录?',//弹窗的内容
        type:'warning'
      }).then(()=>{
        this.deleteRecord(record);
      }).catch(()=>{
        console.log('取消删除');
      });
    },
    // 删除记录
    deleteRecord(record){
      let that = this;
      this.$axios({
          method:'delete',
          url:'/api/record/'+record.id,
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
            //that.$store.dispatch('listRecentRecords');
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
    },
  }
};
</script>


<style lang="less" scoped>
@media all and (max-width: 425px){
  .pet_name{
    display: none;
  }
}
@media all and (max-width: 600px){
  .ecu_name{
    display: none;
  }
}
@media all and (max-width: 768px){
  .operation{
    display: none;
  }
}

.card{
  padding: 0;
}
</style>

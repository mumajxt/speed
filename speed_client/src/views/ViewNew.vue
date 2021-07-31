<template>
  <div id="view_new" class="records-container new_records">
      <!-- 短信息 -->
    <div class="short_info">
      <p v-if="loading">
        <Loading>加载中...</Loading>
      </p>
      <p v-else>
        <span v-if="recordsInRankOut.length>0">
          共<span class="yellow" v-text="totalCount"></span>条记录，
          <span class="yellow">黄色</span>记录为当前类型在榜记录
        </span>
        <span v-else>暂无历史记录~</span>  
      </p>
    </div>
    <div  v-if="recordsInRankOut.length>0">
      <div class="table card"
        v-infinite-scroll="load"
        infinite-scroll-disabled="disabled"
        infinite-scroll-distance="200"
        infinite-scroll-immediate = "true">

        <div class="table-title">
          <span class="table-title-border bg-yellow"></span>
          <h3>历史记录</h3>
          <span class="table-title-info grey">
            <span v-if="screenTrack||screenPlayer||screenCar">筛选出<span class="yellow" v-text="screenCount"></span>条记录</span><!-- 第<span class="yellow" v-text="pageBean.currentPage+'/'+ pageBean.totalPage"></span>页 -->
          </span>
        </div>
        <div class="th">
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
        </div>
        <div class="tr" 
        v-for="record in records" 
        :key="record.id"
        @click="$emit('showDetail',record)">
          <div class="track_name"  v-text="record.track.name">地图</div>
          <div class="time" 
          :class="record.isIn?'yellow':'grey'">
            <span v-text="record.time"  :class="{'url':record.videoUrl}"></span>
          </div>
          <div class="player_name" v-text="record.player.name">车手</div>
          <div class="car_name" v-text="record.car.name">赛车</div>
          <div class="pet_name">
            <span v-if="record.pet" v-text="record.pet.name"></span>
            <span v-else class="grey2">无宠</span>
          </div>
          <div class="ecu_name">
            <span v-if="record.ecu" v-text="record.ecu.name"></span>
            <span v-else class="grey2">无E</span>
          </div>
          <div class="date" v-text="record.date">日期</div>
        </div>
      </div>
    </div>
    


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


      <div class="short_info center">
        <Loading v-if="loading">加载中...</Loading>
      </div>
  </div>
</template>

<script>
//import Loading from '@/components/Loading';
export default {
    name: "ViewNew",
    components: {
      UserDrawerChoseTrack:() => import('@/components/drawer/chose/UserDrawerChoseTrack'),
      UserDrawerChosePlayer:() => import('@/components/drawer/chose/UserDrawerChosePlayer'),
      UserDrawerChoseCar:() => import('@/components/drawer/chose/UserDrawerChoseCar'),
      Loading:() => import('@/components/Loading'),
    },
    props: {
      type:String,
      pet:String,
      ecu:String,
      screenTrackOut:Object,
      screenPlayerOut:Object,
      screenCarOut:Object,
      searchKey:String,
      recordsInRankOut:Array
    },
    created(){
      this.screenTrack=this.screenTrackOut;
      this.screenPlayer=this.screenPlayerOut;
      this.screenCar=this.screenCarOut;
      this.initData();
    },
    watch: {
      type:{
        handler(){
          this.initData();
        }
      },
      pet:{
        handler(){
          this.initData();
        }
      },
      ecu:{
        handler(){
          this.initData();
        }
      },
      searchKey:{
        handler(val){
          if(this.timer){
            clearTimeout(this.timer);
          }
          this.timer = setTimeout(this.initData,1000);
        }
      },
      screenTrackOut:function(val){
        this.choseTrack(val);
      },
      screenPlayerOut:function(val){
        this.chosePlayer(val);
      },
      screenCarOut:function(val){
        this.choseCar(val);
      }
    },
    computed: {
      noMore () {
        return this.pageBean.records.length < this.pageBean.pageSize;
      },
      disabled () {
        return this.loading || this.noMore
      }
    },
    data() {
      return {
        //懒加载
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
        screenCount:0,

        records:[],
        screenTrack:null,
        screenCar:null,
        screenPlayer:null,
        orderBy:'dateDown',
        isShowDrawerChoseTrack:false,
        isShowDrawerChoseCar:false,
        isShowDrawerChosePlayer:false,
      };
    },
    methods: {
      //初始化数据
      initData(){
        this.records=[];
        this.pageBean.currentPage=1;
        this.listRecentRecords(); 
      },
      //发送请求
      listRecentRecords(){
        this.loading = true 
        console.log("加载历史记录数据...");
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
            this.pageBean = info.data;
            console.log("--------------------------");
            console.log("返回第"+this.pageBean.currentPage+"页记录");
            console.log(this.pageBean);
            console.log("--------------------------");
            this.pageBean.records.forEach(record=>{
              this.recordsInRankOut.forEach(r=>{
                if(r.id==record.id){
                  record.isIn=true;
                }
              })
            });
            this.records=this.records.concat(this.pageBean.records);
            //只在第一页计算总页数
            if(this.pageBean.currentPage==1){
              this.totalCount=this.pageBean.totalCount;
              this.screenCount=this.pageBean.screenCount;
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
      clickOrderTime(){
        this.orderBy=='timeUp'?this.orderBy='timeDown':(this.orderBy=='timeDown'?this.orderBy='timeUp':this.orderBy='timeDown');
        this.initData();
      },
      clickOrderDate(){
        this.orderBy=='dateUp'?this.orderBy='dateDown':(this.orderBy=='dateDown'?this.orderBy='dateUp':this.orderBy='dateDown');
        this.initData();
      },
      clearScreen(){
        this.screenTrack=null;
        this.screenPlayer=null;
        this.screenCar=null;
        this.pageBean.currentPage=1;
        this.initData();
      }
    }
};
</script>

<style lang="less" scoped>
#view_new {
  //background: #000;

  .card{
    margin-top: 0;
  }
}

</style>

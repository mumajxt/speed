<template>
  <div id="view_default" class="records-container">
   
    <!-- 短信息 -->
    <div class="short_info">
      <p>
        <span v-if="searchKey">搜索到</span><span v-else>共</span><span class="yellow" v-text="filteredRecords.length"></span>条国服记录
        ，更新于<span v-text="lastDate" class="green"></span>
        <span v-if="filteredRecords.length>0 && $store.state.admin" @click="export2Excel()" title=" 导出Excel表格">
          ，<i class="iconfont iconExcel"> 导出表格</i>
        </span>
      </p>
    </div>
    <!-- <span class="jxt">新图</span><span class="jxt-count" ></span> -->
    <div class="table card" v-if="news.length>0">
      <div class="table-title">
        <span class="table-title-border bg-green"></span>
        <h3>新图</h3>
        <span class="table-title-count green" v-text="news.length"></span>
      </div>
      <!--  -->
      <div class="tr" 
      v-for="record in news" 
      :key="record.id"
      @click="$emit('showDetail',record)">
        <div class="track_name"  v-text="record.track.name">地图</div>
        <div class="time yellow" v-text="record.time" :class="{'url':record.videoUrl}">记录</div>
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



    <div class="table card" v-if="s7s.length>0">
      <div class="table-title">
        <span class="table-title-border bg-yellow"></span>
        <h3>7星图</h3>
        <span class="table-title-count yellow" v-text="s7s.length"></span>
      </div>
      
      <div class="tr" 
      v-for="record in s7s" 
      :key="record.id"
      @click="$emit('showDetail',record)">
        <div class="track_name"  v-text="record.track.name">地图</div>
        <div class="time yellow" v-text="record.time" :class="{'url':record.videoUrl}">记录</div>
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


    <div class="table card" v-if="s6s.length>0">
      <div class="table-title">
        <span class="table-title-border bg-yellow"></span>
        <h3>6星图</h3>
        <span class="table-title-count yellow" v-text="s6s.length"></span>
      </div>
      
      <div class="tr" 
      v-for="record in s6s" 
      :key="record.id"
      @click="$emit('showDetail',record)">
        <div class="track_name"  v-text="record.track.name">地图</div>
        <div class="time yellow" v-text="record.time" :class="{'url':record.videoUrl}">记录</div>
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



    <div class="table card" v-if="s5s.length>0">
      <div class="table-title">
        <span class="table-title-border bg-yellow"></span>
        <h3>5星图</h3>
        <span class="table-title-count yellow" v-text="s5s.length"></span>
      </div>
      
      <div class="tr" 
      v-for="record in s5s" 
      :key="record.id"
      @click="$emit('showDetail',record)">
        <div class="track_name"  v-text="record.track.name">地图</div>
        <div class="time yellow" v-text="record.time" :class="{'url':record.videoUrl}">记录</div>
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



    <div class="table card" v-if="s4s.length>0">
      <div class="table-title">
        <span class="table-title-border bg-yellow"></span>
        <h3>4星图</h3>
        <span class="table-title-count yellow" v-text="s4s.length"></span>
      </div>
      
      <div class="tr" 
      v-for="record in s4s" 
      :key="record.id"
      @click="$emit('showDetail',record)">
        <div class="track_name"  v-text="record.track.name">地图</div>
        <div class="time yellow" v-text="record.time" :class="{'url':record.videoUrl}">记录</div>
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


    <div class="table card" v-if="s3s.length>0">
      <div class="table-title">
        <span class="table-title-border bg-yellow"></span>
        <h3>3星图</h3>
        <span class="table-title-count yellow" v-text="s3s.length"></span>
      </div>
      
      <div class="tr" 
      v-for="record in s3s/*.filter(r => r.track.isLeague)*/"
      :key="record.id"
      @click="$emit('showDetail',record)">
        <div class="track_name"  v-text="record.track.name">地图</div>
        <div class="time yellow" v-text="record.time" :class="{'url':record.videoUrl}">记录</div>
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
<!--      <div class="tr-title">-->
<!--        <span>非联赛图</span>-->
<!--      </div>-->
<!--      <div class="tr"-->
<!--           v-for="record in s3s.filter(r => !r.track.isLeague)"-->
<!--           :key="record.id"-->
<!--           @click="$emit('showDetail',record)">-->
<!--        <div class="track_name"  v-text="record.track.name">地图</div>-->
<!--        <div class="time yellow" v-text="record.time" :class="{'url':record.videoUrl}">记录</div>-->
<!--        <div class="player_name" v-text="record.player.name">车手</div>-->
<!--        <div class="car_name" v-text="record.car.name">赛车</div>-->
<!--        <div class="pet_name">-->
<!--          <span v-if="record.pet" v-text="record.pet.name"></span>-->
<!--          <span v-else class="grey2">无宠</span>-->
<!--        </div>-->
<!--        <div class="ecu_name">-->
<!--          <span v-if="record.ecu" v-text="record.ecu.name"></span>-->
<!--          <span v-else class="grey2">无E</span>-->
<!--        </div>-->
<!--        <div class="date" v-text="record.date">日期</div>-->
<!--      </div>-->
    </div>


    <div class="table card" v-if="s2s.length>0">
      <div class="table-title">
        <span class="table-title-border  bg-yellow"></span>
        <h3>2星图</h3>
        <span class="table-title-count yellow" v-text="s2s.length"></span>
      </div>
      
      <div class="tr" 
      v-for="record in s2s" 
      :key="record.id"
      @click="$emit('showDetail',record)">
        <div class="track_name"  v-text="record.track.name">地图</div>
        <div class="time yellow" v-text="record.time" :class="{'url':record.videoUrl}">记录</div>
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


    <div class="table card" v-if="s1s.length>0">
      <div class="table-title">
        <span class="table-title-border bg-yellow"></span>
        <h3>1星图</h3>
        <span class="table-title-count yellow" v-text="s1s.length"></span>
      </div>
      
      <div class="tr" 
      v-for="record in s1s" 
      :key="record.id"
      @click="$emit('showDetail',record)">
        <div class="track_name"  v-text="record.track.name">地图</div>
        <div class="time yellow" v-text="record.time" :class="{'url':record.videoUrl}">记录</div>
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


    <div class="table card" v-if="s99s.length>0">
      <div class="table-title">
        <span class="table-title-border bg-purple"></span>
        <h3>拉力图</h3>
        <span class="table-title-count purple" v-text="s99s.length"></span>
      </div>
      
      <div class="tr" 
      v-for="record in s99s" 
      :key="record.id"
      @click="$emit('showDetail',record)">
        <div class="track_name"  v-text="record.track.name">地图</div>
        <div class="time yellow" v-text="record.time" :class="{'url':record.videoUrl}">记录</div>
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


    <div class="table card" v-if="seas.length>0">
      <div class="table-title">
        <span class="table-title-border bg-blue"></span>
        <h3>航海图</h3>
        <span class="table-title-count blue" v-text="seas.length"></span>
      </div>
      
      <div class="tr" 
      v-for="record in seas" 
      :key="record.id"
      @click="$emit('showDetail',record)">
        <div class="track_name"  v-text="record.track.name">地图</div>
        <div class="time yellow" v-text="record.time" :class="{'url':record.videoUrl}">记录</div>
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

    <div class="table card" v-if="olds.length>0">
      <div class="table-title">
        <span class="table-title-border bg-grey"></span>
        <h3>怀旧图</h3>
        <span class="table-title-count grey" v-text="olds.length"></span>
      </div>
      
      <div class="tr" 
      v-for="record in olds" 
      :key="record.id"
      @click="$emit('showDetail',record)">
        <div class="track_name"  v-text="record.track.name">地图</div>
        <div class="time yellow" v-text="record.time" :class="{'url':record.videoUrl}">记录</div>
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
</template>

<script>
import Loading from '@/components/Loading';
export default {
  name: "ViewDefault",
  components: {
    Loading
  },
  props: {
    recordsOut:Array,
    searchKey:String,
    type:String,
    pet:String,
    ecu:String,
    loading:Boolean
  },
  created(){
    //this.initRecords();
  },
  mounted(){

  },
  watch:{
    filteredRecords:{
      handler(filteredRecords){
        this.news=[];
        this.s7s=[];
        this.s6s=[];
        this.s5s=[];
        this.s4s=[];
        this.s3s=[];
        this.s2s=[];
        this.s1s=[];
        this.s99s=[];
        this.seas=[];
        this.olds=[];
        filteredRecords.forEach(record=>{
          if(record.track.isNew){
            this.news.push(record);
          }else if(record.track.isOld){
            this.olds.push(record);
          }else if(record.track.isSea){
            this.seas.push(record);
          }else{
            switch(record.track.stars){
              case 1:this.s1s.push(record);break;
              case 2:this.s2s.push(record);break;
              case 3:this.s3s.push(record);break;
              case 4:this.s4s.push(record);break;
              case 5:this.s5s.push(record);break;
              case 6:this.s6s.push(record);break;
              case 7:this.s7s.push(record);break;
              case 99:this.s99s.push(record);break;
              default:break;
            }
          }
        });
      },
      deep:true,
      immediate:true
    },
  },
  computed: {
    filteredRecords(){
      let reg = new RegExp(this.searchKey,'i');
      return this.recordsOut.filter(record=>reg.test(record.track.name) 
        || reg.test(record.player.name)
        || reg.test(record.car.name)
        || (record.pet?reg.test(record.pet.name):false)
        || (record.ecu?reg.test(record.ecu.name):false))
      .sort((a,b)=>new Date(b.track.date)-new Date(a.track.date));
    },
    lastDate(){
      if(this.recordsOut.length>0){
        return this.recordsOut.sort((a,b)=>new Date(b.date)-new Date(a.date))[0].date;
      }else{
        return '0000-00-00';
      }
    },
    title(){
      return (this.typeCH=='不限'?'':this.typeCH)
      +(this.petCH=='不限'?'':this.petCH)
      +(this.ecuCH=='不限'?'':this.ecuCH)
      +'国服榜单'+new Date().format('yyyy-MM-dd');
    },
    typeCH(){
      switch(this.type){
        case 'all':return '不限';
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
  data() {
    return {
      //记录
      news:[],
      s7s:[],
      s6s:[],
      s5s:[],
      s4s:[],
      s3s:[],
      s2s:[],
      s1s:[],
      s99s:[],
      seas:[],
      olds:[],
    };
  },
  methods: {
    formatJson(filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => v[j]))
    },
    export2Excel(){
      require.ensure([], () => {
        const { jxt } = require('@/vendor/Export2Excel');
        let merges = [];
        let cl = 2;
        if(this.news.length>0)
        merges.push({
          s: {r:cl, c:0}, e: {r:(cl=cl+this.news.length)-1, c:0} 
        });
        if(this.s7s.length>0)
        merges.push({
          s: {r:cl, c:0}, e: {r:(cl=cl+this.s7s.length)-1, c:0} 
        });
        if(this.s6s.length>0)
        merges.push({
          s: {r:cl, c:0}, e: {r:(cl=cl+this.s6s.length)-1, c:0} 
        });
        if(this.s5s.length>0)
        merges.push({
          s: {r:cl, c:0}, e: {r:(cl=cl+this.s5s.length)-1, c:0} 
        });
        if(this.s4s.length>0)
        merges.push({
          s: {r:cl, c:0}, e: {r:(cl=cl+this.s4s.length)-1, c:0} 
        });
        if(this.s3s.length>0)
        merges.push({
          s: {r:cl, c:0}, e: {r:(cl=cl+this.s3s.length)-1, c:0} 
        });
        if(this.s2s.length>0)
        merges.push({
          s: {r:cl, c:0}, e: {r:(cl=cl+this.s2s.length)-1, c:0} 
        });
        if(this.s1s.length>0)
        merges.push({
          s: {r:cl, c:0}, e: {r:(cl=cl+this.s1s.length)-1, c:0} 
        });
        if(this.s99s.length>0)
        merges.push({
          s: {r:cl, c:0}, e: {r:(cl=cl+this.s99s.length)-1, c:0} 
        });
        if(this.seas.length>0)
        merges.push({
          s: {r:cl, c:0}, e: {r:(cl=cl+this.seas.length)-1, c:0} 
        });
        if(this.olds.length>0)
        merges.push({
          s: {r:cl, c:0}, e: {r:(cl=cl+this.olds.length)-1, c:0} 
        }); 
        
        console.log(merges);
        const list = [...this.news,...this.s7s,...this.s6s,...this.s5s,...this.s4s,...this.s3s,...this.s2s,...this.s1s,...this.s99s,...this.seas,...this.olds];
        const newList = [];
        //console.log(list[4]);
        list.forEach(r => {
          //console.log(r.track);
          newList.push({
            type: r.track.label,
            track_name: r.track.name,
            time:r.time,
            player_name:r.player.name,
            car_name:r.car.name,
            pet_name:r.pet?r.pet.name:'',
            ecu_name:r.ecu?r.ecu.name:'',
            date:r.date,
            videoUrl:r.videoUrl,
          })
        });
        const tHeader = ['类型','地图','记录','车手','赛车','宠物','ECU','日期','视频地址'];
        const filterVal = ['type','track_name', 'time', 'player_name', 'car_name','pet_name', 'ecu_name','date','videoUrl', ];
        const data = newList.map(v => filterVal.map(j => v[j]));//(filterVal, newList);
        jxt(this.title,tHeader, data, merges);
      });
      
    },
  }
};
</script>

<style lang="less" scoped>

</style>

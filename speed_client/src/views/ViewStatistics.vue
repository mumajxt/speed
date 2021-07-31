<template>
  <div id="view_statistics" class="records-container">
    <!-- 短信息 -->
    <div class="short_info">
      <p v-if="loading">
        <Loading>加载中...</Loading>
      </p>
      <p v-else>
        <span v-if="recordsOut.length>0"><span v-if="searchKey">搜索到</span><span v-else>在榜</span><span v-if="playerStatistics.length>0"><span class="yellow" v-text="playerStatistics.length"></span>位选手</span>
          <span v-if="carStatistics.length>0">，<span class="yellow" v-text="carStatistics.length"></span>辆赛车</span>
          <span v-if="petStatistics.length>0">，<span class="yellow" v-text="petStatistics.length"></span>只宠物</span>
          <span v-if="ecuStatistics.length>0">，<span class="yellow" v-text="ecuStatistics.length"></span>个ECU</span>
        </span>
        <span v-else>暂无记录~</span>
      </p>
    </div>

    <!-- 车手统计 -->
    <div class="statistic card" v-if="playerStatistics.length>0">
      <div class="statistic-header">
          <h3>
              <span class="icon iconfont iconleft yellow"></span>
              <span class="header_name">车手<span class="yellow">TOP</span>榜</span>
              <span class="icon iconfont iconright yellow"></span>
          </h3>
      </div>
      <div class="statistic-list">
        <div class="statistic-list-item" 
        v-for="(playerStatistic, index) in playerStatistics" 
        :key="playerStatistic.player.id"
        :class="playerStatistics[index].isShow?'show':''">
            <div class="statistic-list-item-info" @click="playerStatistics[index].isShow = !playerStatistics[index].isShow">
                <span class="player" v-text="playerStatistic.player.name"></span>
                <span class="persent" v-text="$formatToPercent(playerStatistic.records.length/recordsOut.length)"></span>
                <div class="blank">
                    <div class="bar" :style="{flex: playerStatistic.records.length}"></div>
                    <div class="bar_blank" :style="{flex: playerStatistics[0].records.length - playerStatistic.records.length}"></div>
                </div>
                <span class="count" v-text="playerStatistic.records.length"></span>
                <span class="icon iconfont iconxiala" :class="playerStatistics[index].isShow?'':'rotate-90'"></span>
            </div>

            <div class="statistic-list-item-detail" v-if="playerStatistics[index].isShow">
              <div class="table">
                <div class="tr" 
                v-for="record in playerStatistic.records" 
                :key="record.id"
                @click="$emit('showDetail',record)">
                  <div class="track_name"  v-text="record.track.name">地图</div>
                  <div class="time yellow"  v-text="record.time" :class="{'url':record.videoUrl}">记录</div>
                  <!-- <div class="player_name" v-text="record.player.name">车手</div> -->
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
        </div>
      </div>
    </div>

    <!-- 赛车统计 -->
    <div class="statistic card" v-if="carStatistics.length>0">

      <div class="statistic-header">
          <h3>
              <span class="icon iconfont iconleft yellow"></span>
              <span class="header_name">赛车<span class="yellow">TOP</span>榜</span>
              <span class="icon iconfont iconright yellow"></span>
          </h3>
      </div>

      <div class="statistic-list">
        <div class="statistic-list-item" 
        v-for="(carStatistic, index) in carStatistics" 
        :key="carStatistic.car.id"
        :class="carStatistics[index].isShow?'show':''">


            <div class="statistic-list-item-info" @click="carStatistics[index].isShow = !carStatistics[index].isShow">
                <span class="car" v-text="carStatistic.car.name"></span>
                <span class="persent" v-text="$formatToPercent(carStatistic.records.length/recordsOut.length)"></span>
                <div class="blank">
                    <div class="bar" :style="{flex: carStatistic.records.length}"></div>
                    <div class="bar_blank" :style="{flex: carStatistics[0].records.length - carStatistic.records.length}"></div>
                </div>
                <span class="count" v-text="carStatistic.records.length"></span>
                <span class="icon iconfont iconxiala" :class="carStatistics[index].isShow?'':'rotate-90'"></span>
            </div>

            <div class="statistic-list-item-detail" v-if="carStatistics[index].isShow">
              <div class="table">
                <div class="tr" 
                v-for="record in carStatistic.records" 
                :key="record.id"
                @click="$emit('showDetail',record)">
                  <div class="track_name"  v-text="record.track.name">地图</div>
                  <div class="time yellow"  v-text="record.time" :class="{'url':record.videoUrl}">记录</div>
                  <div class="player_name" v-text="record.player.name">车手</div>
                  <!-- <div class="car_name" v-text="record.car.name">赛车</div> -->
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
        </div>
      </div>
    </div>

    <!-- 宠物统计 -->
    <div class="statistic card" v-if="petStatistics.length>0">

      <div class="statistic-header">
          <h3>
              <span class="icon iconfont iconleft yellow"></span>
              <span class="header_name">宠物<span class="yellow">TOP</span>榜</span>
              <span class="icon iconfont iconright yellow"></span>
          </h3>
      </div>

      <div class="statistic-list">
        <div class="statistic-list-item" 
        v-for="(petStatistic, index) in petStatistics" 
        :key="petStatistic.pet.id"
        :class="petStatistics[index].isShow?'show':''">


            <div class="statistic-list-item-info" @click="petStatistics[index].isShow = !petStatistics[index].isShow">
                <span class="pet" v-text="petStatistic.pet.name"></span>
                <span class="persent" v-text="$formatToPercent(petStatistic.records.length/recordsOut.length)"></span>
                <div class="blank">
                    <div class="bar" :style="{flex: petStatistic.records.length}"></div>
                    <div class="bar_blank" :style="{flex: petStatistics[0].records.length - petStatistic.records.length}"></div>
                </div>
                <span class="count" v-text="petStatistic.records.length"></span>
                <span class="icon iconfont iconxiala" :class="petStatistics[index].isShow?'':'rotate-90'"></span>
            </div>

            <div class="statistic-list-item-detail" v-if="petStatistics[index].isShow">
              <div class="table">
                <div class="tr" 
                v-for="record in petStatistic.records" 
                :key="record.id"
                @click="$emit('showDetail',record)">
                  <div class="track_name"  v-text="record.track.name">地图</div>
                  <div class="time yellow"  v-text="record.time" :class="{'url':record.videoUrl}">记录</div>
                  <div class="player_name" v-text="record.player.name">车手</div>
                  <div class="car_name" v-text="record.car.name">赛车</div>
                  <!-- <div class="pet_name">
                    <span v-if="record.pet" v-text="record.pet.name"></span>
                    <span v-else class="grey2">无宠</span>
                  </div> -->
                  <div class="ecu_name">
                    <span v-if="record.ecu" v-text="record.ecu.name"></span>
                    <span v-else class="grey2">无E</span>
                  </div>
                  <div class="date" v-text="record.date">日期</div>
                </div>
              </div>
            </div>
        </div>
      </div>
    </div>

    <!-- ECU统计 -->
    <div class="statistic card" v-if="ecuStatistics.length>0">

      <div class="statistic-header">
          <h3>
              <span class="icon iconfont iconleft yellow"></span>
              <span class="header_name">ECU<span class="yellow">TOP</span>榜</span>
              <span class="icon iconfont iconright yellow"></span>
          </h3>
      </div>

      <div class="statistic-list">
        <div class="statistic-list-item" 
        v-for="(ecuStatistic, index) in ecuStatistics" 
        :key="ecuStatistic.ecu.id"
        :class="ecuStatistics[index].isShow?'show':''">


            <div class="statistic-list-item-info" @click="ecuStatistics[index].isShow = !ecuStatistics[index].isShow">
                <span class="ecu" v-text="ecuStatistic.ecu.name"></span>
                <span class="persent" v-text="$formatToPercent(ecuStatistic.records.length/recordsOut.length)"></span>
                <div class="blank">
                    <div class="bar" :style="{flex: ecuStatistic.records.length}"></div>
                    <div class="bar_blank" :style="{flex: ecuStatistics[0].records.length - ecuStatistic.records.length}"></div>
                </div>
                <span class="count" v-text="ecuStatistic.records.length"></span>
                <span class="icon iconfont iconxiala" :class="ecuStatistics[index].isShow?'':'rotate-90'"></span>
            </div>

            <div class="statistic-list-item-detail" v-if="ecuStatistics[index].isShow">
              <div class="table">
                <div class="tr" 
                v-for="record in ecuStatistic.records" 
                :key="record.id"
                @click="$emit('showDetail',record)">
                  <div class="track_name"  v-text="record.track.name">地图</div>
                  <div class="time yellow"  v-text="record.time" :class="{'url':record.videoUrl}">记录</div>
                  <div class="player_name" v-text="record.player.name">车手</div>
                  <div class="car_name" v-text="record.car.name">赛车</div>
                  <div class="pet_name">
                    <span v-if="record.pet" v-text="record.pet.name"></span>
                    <span v-else class="grey2">无宠</span>
                  </div>
                  <!-- <div class="ecu_name">
                    <span v-if="record.ecu" v-text="record.ecu.name"></span>
                    <span v-else class="grey2">无E</span>
                  </div> -->
                  <div class="date" v-text="record.date">日期</div>
                </div>
              </div>
            </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Loading from '@/components/Loading';
export default {
  name: "ViewStatistics",
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

  },
  mounted(){

  },
  watch:{
    filteredRecords:{
      handler(records){
        this.playerStatistics=[];
        this.carStatistics=[];
        this.petStatistics=[];
        this.ecuStatistics=[];
        records.forEach(record=>{
          //初始化player
          let playerIndex = -1;
          let isPlayerExist = this.playerStatistics.some((playerStatistic,j)=>{
              if(record.player.id==playerStatistic.player.id){
                  playerIndex = j;
                  return true;
              }
          });
          if(!isPlayerExist){
              this.playerStatistics.push({
                  player: record.player,
                  records: [record],
                  isShow: false
              })
          }else{
              this.playerStatistics[playerIndex].records.push(record);
          }

          //初始化car
          let carIndex = -1;
          let isCarExist = this.carStatistics.some((carStatistic,j)=>{
              if(record.car.id==carStatistic.car.id){
                  carIndex = j;
                  return true;
              }
          });
          if(!isCarExist){
              this.carStatistics.push({
                  car: record.car,
                  records: [record],
                  isShow: false
              })
          }else{
              this.carStatistics[carIndex].records.push(record);
          }
          //初始化pet
          if(record.pet){
            let petIndex = -1;
            let isPetExist = this.petStatistics.some((petStatistic,j)=>{
              if(record.pet.id==petStatistic.pet.id){
                  petIndex = j;
                  return true;
              }
            });
            if(!isPetExist){
                this.petStatistics.push({
                    pet: record.pet,
                    records: [record],
                    isShow: false
                })
            }else{
                this.petStatistics[petIndex].records.push(record);
            }
          }
          //初始化ecu
          if(record.ecu){
            let ecuIndex = -1;
            let isEcuExist = this.ecuStatistics.some((ecuStatistic,j)=>{
                if(record.ecu.id==ecuStatistic.ecu.id){
                    ecuIndex = j;
                    return true;
                }
            });
            if(!isEcuExist){
                this.ecuStatistics.push({
                    ecu: record.ecu,
                    records: [record],
                    isShow: false
                })
            }else{
                this.ecuStatistics[ecuIndex].records.push(record);
            }
          }

        });

        this.playerStatistics.sort((a,b)=>b.records.length-a.records.length);
        this.playerStatistics.forEach(playerStatistic => {
          playerStatistic.records.sort((a,b)=>b.date>a.date);
        });
        this.carStatistics.sort((a,b)=>b.records.length-a.records.length);
        this.carStatistics.forEach(carStatistic => {
          carStatistic.records.sort((a,b)=>b.date>a.date);
        });
        this.petStatistics.sort((a,b)=>b.records.length-a.records.length);
        this.petStatistics.forEach(petStatistic => {
          petStatistic.records.sort((a,b)=>b.date>a.date);
        });
        this.ecuStatistics.sort((a,b)=>b.records.length-a.records.length);
        this.ecuStatistics.forEach(ecuStatistic => {
          ecuStatistic.records.sort((a,b)=>b.date>a.date);
        });
      },
      immediate:true
    }
  },
  computed: {
    filteredRecords(){
      let reg = new RegExp(this.searchKey,'i');
      return this.recordsOut.filter(record=>reg.test(record.track.name) 
        || reg.test(record.player.name)
        || reg.test(record.car.name)
        || (record.pet?reg.test(record.pet.name):false)
        || (record.ecu?reg.test(record.ecu.name):false));
    },
  },
  data() {
    return {
      playerStatistics:[],
      carStatistics:[],
      petStatistics:[],
      ecuStatistics:[],
    };
  },
  methods: {

  }
};
</script>

<style lang="less" scoped>
.statistic {
    padding: 0;
    .statistic-header {
        text-align: center;
        height: 3rem;
        line-height: 3rem;
    }
    .statistic-list {
        .statistic-list-item {
            .statistic-list-item-info {
              position: sticky;
              position: -webkit-sticky;
              top: 4rem;
              z-index: 98;
              background-color: #fff;
              border-bottom: 1px solid @c_grey_bg;


              display: flex;
              height: 2.4rem;
              padding: 0 1rem;
              align-items: center;
              cursor: pointer;

              &:hover{
                .player {
                  color: @c_yellow;
                }
                .blank {
                  .bar {
                        opacity: 1;
                    }
                }
              }

              .player,.pet,.car,.ecu {
                  width: 8rem;
                  font-weight: bold;
                  transition: color 0.3s;
              }
              .persent {
                  color: @c3;
                  font-size: 0.8rem;
                  text-align: right;
                  width: 3rem;
                  padding-right: 0.4rem;
              }
              .blank {
                  flex: 1;
                  display: flex;
                  height: 0.4rem;
                  .bar {
                      background-color: @c_yellow;
                      border-radius: 0.2rem;
                      opacity: 0.5;
                      transition: opicity 0.3s;
                  }
              }
              .count {
                  text-align: right;
                  width: 2rem;
                  font-size: 1rem;
              }
              .iconfont{
                  margin-left: 0.5rem;
                  font-size: 1rem;
                  color: @c3;
              }
            }

            /* .statistic-list-item-detail {
              //background-color: @c_input_bg;
            } */
        }
    }
}

</style>

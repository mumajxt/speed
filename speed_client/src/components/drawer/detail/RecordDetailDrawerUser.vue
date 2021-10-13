<template>
  <div class="mask-wrapper animate__animated" :class="visible?'animate__fadeIn':'animate__fadeOut'" v-show="isShow" @click="close()" @click.stop>
    <div v-if="record" class="drawer animate__animated" :class="visible?'animate__fadeInUp':'animate__fadeOutDown'" @click.stop>
      <div class="drawer-header">
        <h2>记录详情
        <span class="divider"></span>
        <span class="info">
          <span v-text="rankCH"></span><span class="id grey" v-text="'#'+record.id"></span>
        </span>
        </h2>
      </div>
      <div class="drawer-body">
        <div class="attribute-list">
          <div class="attribute-list-item link" @click="showMoreAboutTrack(record.track)">
            <label>地图</label>
            <p>
              <span v-text="record.track.name"></span>
              <span v-if="!record.track.isLeague" class="info info-miss grey">非联赛图</span>
            </p>
            <div><i class="iconfont iconxiala rotate-90"></i></div>
          </div>
          <div class="attribute-list-item">
            <label>记录</label>
            <p v-text="record.time" :class="record.isIn==null?'yellow':(record.isIn==false?'grey':'yellow')"></p>
          </div>
          <div  class="attribute-list-item link" @click="showMoreAboutPlayer(record.player)">
            <label>车手</label>
            <p>
              <span v-text="record.player.name"></span>
              
              <span v-if="record.player.team" class="info" v-text="record.player.team.name"></span>
              <span v-else class="info info-miss grey">无车队</span>
            </p>
            <div><i class="iconfont iconxiala rotate-90"></i></div>
          </div>
          <div class="attribute-list-item link"  @click="showMoreAboutCar(record.car)">
            <label>赛车</label>
            <p>
              <span v-text="record.car.name"></span>
              
              <span v-if="record.carSkin" class="info" v-text="record.carSkin.name"></span>
              <span v-else class="info info-miss grey">无皮肤</span>
            </p>
            <div><i class="iconfont iconxiala rotate-90"></i></div>
          </div>


          <div class="attribute-list-item" v-if="record.pet">
            <label>宠物</label>
            <p>
              <span v-text="record.pet.name"></span>
              <span v-if="record.petSkin" class="info" v-text="record.petSkin.name"></span>
              <span v-else class="info info-miss grey">无皮肤</span>
            </p>
            
            <!-- <div><i class="iconfont iconxiala rotate-90"></i></div> -->
          </div>
          <div class="attribute-list-item" v-else>
            <label>宠物</label>
            <p class="grey">无宠物</p>
          </div>
          

          <div  v-if="record.ecu" class="attribute-list-item">
            <label>ECU</label>
            <p v-text="record.ecu.name"></p>
            <!-- <div><i class="iconfont iconxiala rotate-90"></i></div> -->
          </div>
          <div class="attribute-list-item" v-else>
            <label>ECU</label>
            <p class="grey">无ECU</p>
          </div>


          <a :href="record.videoUrl" target="_blank" class="attribute-list-item" v-if="record.videoUrl">
            <label>视频</label>
            <p v-text="record.videoUrl" class="bili"></p>
            <div><i class="iconfont iconxiala rotate-90"></i></div>
          </a>
          <div class="attribute-list-item" v-else>
            <label>视频</label>
            <p class="grey">暂无视频</p>
          </div>

          <div class="attribute-list-item">
            <label>日期</label>
            <p v-text="record.date"></p>
          </div>
        </div>
      </div>
      <div class="drawer-footer">
        <div class="drawer-footer-btn drawer-footer-btn-grey" @click="close()">关闭</div>
      </div>
    </div>
  </div>
</template>

<script>

export default {
    name: "RecordDetailDrawerUser",
    components: {

    },
    props: {
      visible:Boolean,
      record:Object
    },
    mounted(){

    },
    destroyed(){

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
      }
    },
    computed: {
      rankCH(){
        switch(this.record.type){
          case 't':return 'T车' + (this.record.pet?'有宠':'无宠') + (this.record.ecu?'有E':'无E');
          case 'a':return 'A车' + (this.record.pet?'有宠':'无宠') + (this.record.ecu?'有E':'无E');
          case 'b':return 'B车'+(this.record.pet?'有宠':'无宠') +(this.record.ecu?'有E':'无E');
          case 'c':return 'C车'+(this.record.pet?'有宠':'无宠') +(this.record.ecu?'有E':'无E');
          case 'd':return 'D车'+(this.record.pet?'有宠':'无宠') +(this.record.ecu?'有E':'无E');
          case 'x':return '悬浮车'+(this.record.pet?'有宠':'无宠') +(this.record.ecu?'有E':'无E');
          case 'l1':return 'L1滑板'+(this.record.ecu?'有E':'无E') ;
          case 'm1':return 'M1摩托'+(this.record.pet?'有宠':'无宠') +(this.record.ecu?'有E':'无E');
          case 'm2':return 'M2摩托'+(this.record.pet?'有宠':'无宠') +(this.record.ecu?'有E':'无E');
          case 'undrift':return '抓地'+(this.record.pet?'有宠':'无宠') +(this.record.ecu?'有E':'无E');
          case 'asiaCup':return '亚洲杯('+(this.record.pet?'有宠':'无宠') +(this.record.ecu?'有E':'无E')+')';
          case 'sCup':return 'S联赛('+(this.record.pet?'有宠':'无宠') +(this.record.ecu?'有E':'无E')+')';
          default :return '未知类型';
        }
      },
    },
    data() {
      return {
        isShow:false,
      };
    },
    methods: {
      close(){
        this.$emit('close');
      },
      showMoreAboutTrack(track){
        this.$emit('showMoreAboutTrack',track);
        this.close();
      },
      showMoreAboutPlayer(player){
        this.$emit('showMoreAboutPlayer',player);
        this.close();
      },
      showMoreAboutCar(car){
        this.$emit('showMoreAboutCar',car);
        this.close();
      }
    }
};
</script>

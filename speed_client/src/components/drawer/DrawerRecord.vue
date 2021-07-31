<template>
  <div class="mask-wrapper animate__animated" :class="visible?'animate__fadeIn':'animate__fadeOut'" @click="confirm()" @click.stop>
    <div class="drawer animate__animated" :class="visible?'animate__fadeInUp':'animate__fadeOutDown'" @click.stop>
      <div class="drawer-header">
        <h2 v-if="recordOut"><span v-text="'#'+recordOut.id" class="grey"></span>修改记录</h2>
        <h2 v-else>新增记录</h2>
      </div>
      <div class="drawer-body">
        <form class="attribute-list">
          <div class="attribute-list-item type">
            <label>类型<span v-show="!record.type" class="red">*</span></label>

            <div class="value input-wrapper  input-wrapper-select">
              <input type="text" readonly @click="isShowDrawerChoseRecordType=true" placeholder="记录类型" v-model="typeCH">
              <div class="input-icon">
                <i class="iconfont iconxiala"></i>
              </div>
            </div>

            <div class="label-switch" :class="isPet?'true':'false'">
              <div class="switch-item" @click="isPet=false">无宠</div>
              <div class="switch-item" @click="isPet=true">有宠</div>
            </div>

             <div class="label-switch" :class="isEcu?'true':'false'">
              <div class="switch-item" @click="isEcu=false">无E</div>
              <div class="switch-item" @click="isEcu=true">有E</div>
            </div>
          </div>

          <div class="attribute-list-item track">
            <label>地图<span v-show="!record.track" class="red">*</span></label>
            <div class="value input-wrapper  input-wrapper-select">
              <input readonly @click="isShowDrawerChoseTrack=true" placeholder="选择地图" type="text" v-model="trackName">
               <div class="input-icon">
                <i class="iconfont iconxiala"></i>
              </div>
            </div>
          </div>

          <div class="attribute-list-item time">
            <label>记录<span v-show="!record.time" class="red">*</span></label>
            <div class="value input-wrapper">
              <input placeholder="00:00:00" type="text" v-model="record.time">
            </div>
          </div>
          
          <div class="attribute-list-item player">
            <label>车手<span v-show="!record.player" class="red">*</span></label>
            <div class="value input-wrapper  input-wrapper-select">
              <input readonly @click="isShowDrawerChosePlayer=true" placeholder="选择车手" type="text" v-model="playerName">
               <div class="input-icon">
                <i class="iconfont iconxiala"></i>
              </div>
            </div>
          </div>

          <div class="attribute-list-item car">
            <label>赛车<span v-show="!record.car" class="red">*</span></label>
            <div class="value input-wrapper  input-wrapper-select">
              <input readonly @click="isShowDrawerChoseCar=true" type="text" v-model="carName" placeholder="选择赛车">
               <div class="input-icon">
                <i class="iconfont iconxiala"></i>
              </div>
            </div>

            <div class="value input-wrapper value-second  input-wrapper-select" v-if="record.car">
              <input readonly @click="isShowDrawerChoseCarSkin=true" type="text" v-model="carSkinName" placeholder="选择赛车皮肤">
               <div class="input-icon" @click="record.carSkin?record.carSkin=null:isShowDrawerChoseCarSkin=true">
                <i class="iconfont" :class="record.carSkin?'iconclose':'iconxiala'"></i>
              </div>
            </div>
          </div>

          <div class="attribute-list-item pet" v-if="isPet">
            <label>宠物</label>
            <div class="value input-wrapper  input-wrapper-select">
              <input readonly @click="isShowDrawerChosePet=true" type="text" v-model="petName" placeholder="选择宠物">
              <div class="input-icon" @click="record.pet?record.pet=null:isShowDrawerChosePet=true">
                <i class="iconfont" :class="record.pet?'iconclose':'iconxiala'"></i>
              </div>
            </div>
            <div class="value input-wrapper value-second  input-wrapper-select" v-if="record.pet">
              <input readonly @click="isShowDrawerChosePetSkin=true" type="text" v-model="petSkinName" placeholder="选择宠物皮肤">
              <div class="input-icon" @click="record.petSkin?record.petSkin=null:isShowDrawerChosePetSkin=true">
                <i class="iconfont" :class="record.petSkin?'iconclose':'iconxiala'"></i>
              </div>
            </div>
            </div>
          <div class="attribute-list-item ecu" v-if="isEcu">
            <label>ECU</label>
            <div class="value input-wrapper  input-wrapper-select">
              <input readonly @click="isShowDrawerChoseEcu=true" type="text" v-model="ecuName" placeholder="选择ECU">
              <div class="input-icon" @click="record.ecu?record.ecu=null:isShowDrawerChoseEcu=true">
                <i class="iconfont" :class="record.ecu?'iconclose':'iconxiala'"></i>
              </div>
            </div>
          </div>
          <div class="attribute-list-item url">
            <label>视频</label>
            <div class="value input-wrapper">
              <input type="url" v-model="record.videoUrl" placeholder="https://www.xxx.com/xxx">
              <div v-if="record.videoUrl" class="input-icon" @click="record.videoUrl=''">
                <i class="iconfont iconclose"></i>
              </div>
            </div>
          </div>
          
          <div class="attribute-list-item date">
            <label>日期<span v-show="!record.date" class="red">*</span></label>
            <div class="value input-wrapper">
             <input type="text" placeholder="0000-00-00" v-model="record.date">
            </div>
          </div>

          <div class="attribute-list-item">
            <label>备注</label>
            <div class="value input-wrapper">
              <input type="text" v-model="record.comment" placeholder="添加备注信息">
              <div v-if="record.comment" class="input-icon" @click="record.comment=''">
                <i class="iconfont iconclose"></i>
              </div>
            </div>
          </div>
        </form>
      </div>
      <div class="drawer-footer">
        <div class="drawer-footer-btn drawer-footer-btn-grey" @click="confirm()">取消</div>
        <span class="divider"></span>
        <div v-if="recordOut" class="drawer-footer-btn drawer-footer-btn-yellow" @click="updateRecord">修改</div>
        <div v-else class="drawer-footer-btn drawer-footer-btn-yellow" @click="saveRecord">添加</div>
      </div>
    </div>
    <DrawerChoseRecordType
    :isShowAll="false"
    :visible.sync="isShowDrawerChoseRecordType"
    :typeOut="record.type"
    @close="isShowDrawerChoseRecordType=false"
    @choseRecordType="choseRecordType">
    </DrawerChoseRecordType>

    <DrawerChoseTrack 
    v-if="isShowDrawerChoseTrack"
    :trackOut="record.track"
    @close="isShowDrawerChoseTrack=false"
    @choseTrack="choseTrack">
    </DrawerChoseTrack>

   

    <DrawerChoseCar
    v-if="isShowDrawerChoseCar"
    :carOut="record.car"
    :typeOut="record.type"
    @close="isShowDrawerChoseCar=false"
    @choseCar="choseCar">
    </DrawerChoseCar>

    <DrawerChoseCarSkin
    v-if="isShowDrawerChoseCarSkin"
    :carOut="record.car"
    :carSkinOut="record.carSkin"
    @close="isShowDrawerChoseCarSkin=false"
    @choseCarSkin="choseCarSkin">
    </DrawerChoseCarSkin>

    <DrawerChosePlayer
    v-if="isShowDrawerChosePlayer"
    :playerOut="record.player"
    :typeOut="record.type"
    @close="isShowDrawerChosePlayer=false"
    @chosePlayer="chosePlayer">
    </DrawerChosePlayer>

    

    <DrawerChosePet
    v-if="isShowDrawerChosePet"
    :petOut="record.pet"
    @close="isShowDrawerChosePet=false"
    @chosePet="chosePet">
    </DrawerChosePet>

    <DrawerChosePetSkin
    v-if="isShowDrawerChosePetSkin"
    :petSkinOut="record.petSkin"
    :petOut="record.pet"
    @close="isShowDrawerChosePetSkin=false"
    @chosePetSkin="chosePetSkin">
    </DrawerChosePetSkin>

    <DrawerChoseEcu
    v-if="isShowDrawerChoseEcu"
    :ecuOut="record.ecu"
    @close="isShowDrawerChoseEcu=false"
    @choseEcu="choseEcu">
    </DrawerChoseEcu>
  </div>
</template>

<script>
import DrawerChoseRecordType from '@/components/drawer/chose/DrawerChoseRecordType';
import DrawerChoseTrack from '@/components/drawer/chose/DrawerChoseTrack';
import DrawerChosePlayer from '@/components/drawer/chose/DrawerChosePlayer';
import DrawerChoseCar from '@/components/drawer/chose/DrawerChoseCar';
import DrawerChoseCarSkin from '@/components/drawer/chose/DrawerChoseCarSkin';
import DrawerChosePet from '@/components/drawer/chose/DrawerChosePet';
import DrawerChosePetSkin from '@/components/drawer/chose/DrawerChosePetSkin';
import DrawerChoseEcu from '@/components/drawer/chose/DrawerChoseEcu';

export default {
    name: "DrawerRecord",
    components: {
      DrawerChoseRecordType,
      DrawerChoseTrack,
      DrawerChosePlayer,
      DrawerChoseCar,
      DrawerChoseCarSkin,
      DrawerChosePetSkin,
      DrawerChosePet,
      DrawerChoseEcu
    },
    props: {
      recordOut:Object,
      type:String,
      petLabel:String,
      ecuLabel:String,
    },
    mounted(){
      
    },
    watch: {
      recordOut: {
        immediate: true,
        handler(value) {
          if(value){
            this.record = JSON.parse(JSON.stringify(value));
            this.isPet = value.pet!=null;
            this.isEcu = value.ecu!=null;
          }else{
            this.record={
              type:'',
              track:null,
              time:'',
              player:null,
              car:null,
              carSkin:null,
              pet:null,
              petSkin:null,
              ecu:null,
              videoUrl:'',
              date:new Date().format('yyyy-MM-dd'),
              admin:null,
              comment:''
            }
          }
        },
      },
      type:{
        handler(val){
          if(val!='all'){
            this.record.type=val;
          }
        },
        immediate:true
      },
      petLabel:{
        handler(val){
         switch(val){
          case 'all':this.isPet = true;break;
          case 'withPet':this.isPet=true;break;
          case 'withOutPet':this.isPet=false;break;
          default:break;
        }
      },
        immediate:true
      },
      ecuLabel:{
        immediate:true,
        handler(val){
          switch(val){
            case 'all': this.isEcu = true;break;
            case 'withEcu':this.isEcu=true;break;
            case 'withOutEcu':this.isEcu=false;break;
            default:break;
          }
        }
      },
      
      record:{
        handler(val){
          this.trackName = val.track?val.track.name:'';
          this.playerName = val.player?val.player.name:'';
          this.carName = val.car?val.car.name:'';
          this.carSkinName = val.carSkin?val.carSkin.name:'';
          this.petName = val.pet?val.pet.name:'';
          this.petSkinName = val.petSkin?val.petSkin.name:'';
          this.ecuName = val.ecu?val.ecu.name:'';
        },
        deep: true,
        immediate:true
      },
      isPet:function(val){
        if(!val){
          this.record.pet=null;
          this.record.petSkin=null;
        }
      },
      isEcu:function(val){
        if(!val){
          this.record.ecu=null;
        }
      }
    },
    computed: {
      typeCH(){
        switch(this.record.type){
          case 't':return 'T车';
          case 'a':return 'A车';
          case 'x':return 'X悬浮车';
          case 'undrift':return 'A车(抓地)';
          case 'b':return 'B车';
          case 'c':return 'C车';
          case 'd':return 'D车';
          case 'l1':return 'L1滑板';
          case 'm1':return 'M1摩托';
          case 'm2':return 'M2摩托';
          case 'sCup':return 'S联赛';
          case 'asiaCup':return '亚洲杯';
          default:return '';
        }
      }
    },
    data() {
      return {
        visible:true,
        isPet:true,
        isEcu:true,

        record:null,

        trackName:'',
        playerName:'',
        carName:'',
        carSkinName:'',
        petName:'',
        petSkinName:'',
        ecuName:'',

        isShowDrawerChoseRecordType:false,
        isShowDrawerChoseTrack:false,
        isShowDrawerChosePlayer:false,
        isShowDrawerChoseCar:false,
        isShowDrawerChoseCarSkin:false,
        isShowDrawerChosePet:false,
        isShowDrawerChosePetSkin:false,
        isShowDrawerChoseEcu:false,
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

      confirm(){
        this.$toast({
          showCancel:true,
          t1:'提示',//弹窗的标题
          t2: '修改未提交，是否退出编辑？',//弹窗的内容
          type:'warning'
        }).then(()=>{
          this.close();
        }).catch(()=>{
          //DONOTHING
        })
      },

      //更新记录
      updateRecord(){
        if(this.verifyRecord(this.record)){
          this.loading = true;
          console.log('校验记录成功,正在修改...');
          let record = this.record;
          let postRecord = {
            id:record.id,
            type: record.type,
            trackId: record.track.id,
            playerId: record.player.id,
            carId: record.car.id,
            carSkinId: record.carSkin?record.carSkin.id:'',
            petId: record.pet?record.pet.id:'',
            petSkinId: record.petSkin?record.petSkin.id:'',
            ecuId: record.ecu?record.ecu.id:'',
            videoUrl: record.videoUrl,
            dateStr:record.date.replace(/\./g,'-'),
            timeStr:record.time.replace(/[：\.]/g,':'),
            comment:record.comment
          }
          let data = this.$qs.stringify(postRecord);
          let that = this;
          this.$axios({
            method:'put',
            url:'/api/record',
            data: data
          }).then((response) =>{
            console.log('返回添加结果：↓');
            console.log(response.data);
            let info = response.data;
            if(info.flag){
              that.$toast({
                showCancel:false,
                t1:'提示',//弹窗的标题
                t2:'修改成功',//弹窗的内容
                type:'success'
              });
              //that.$store.dispatch('listRecords');
              that.$emit('initData');
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
      },
      //添加记录
      saveRecord(){
        if(this.verifyRecord(this.record)){
          this.loading = true;
          console.log('校验记录成功,正在添加...');

          let record = this.record;
          let postRecord = {
            type: record.type,
            trackId: record.track.id,
            playerId: record.player.id,
            carId: record.car.id,
            carSkinId: record.carSkin?record.carSkin.id:'',
            petId: record.pet?record.pet.id:'',
            petSkinId: record.petSkin?record.petSkin.id:'',
            ecuId: record.ecu?record.ecu.id:'',
            videoUrl: record.videoUrl,
            dateStr:record.date.replace(/\./g,'-'),
            timeStr:record.time.replace(/[：\.]/g,':'),
            comment:record.comment
          }
          let data = this.$qs.stringify(postRecord);
          let that = this;
          this.$axios({
            method:'post',
            url:'/api/record',
            data: data
          }).then((response) =>{
            console.log('返回添加结果：↓');
            console.log(response.data);
            let info = response.data;
            if(info.flag){
              that.$toast({
                showCancel:false,
                t1:'提示',//弹窗的标题
                t2:'添加成功',//弹窗的内容
                type:'success'
              });
              //that.$store.dispatch('listRecords');
              //that.$emit('choseRecord',info.data);
              that.$emit('initData');
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

        /*  */
      },


      //选择
      choseRecordType(type){
        this.record.type = type;
      },
      choseTrack(track){
        this.record.track = track;
      },
      chosePlayer(player){
        this.record.player = player;
      },
      choseCar(car){
        if(this.record.car){
          if(this.record.car.id !=car.id){
            this.record.carSkin = null;
            this.record.car = car;
          }
        }else{
          this.record.car = car;
          this.record.carSkin = null;
        }
        
      },
      choseCarSkin(carSkin){
        this.record.carSkin = carSkin;
      },
      chosePet(pet){
        if(this.record.pet){
          if(this.record.pet.id !=pet.id){
            this.record.pet = pet;
            this.record.petSkin = null;
          }
        }else{
          this.record.pet = pet;
          this.record.petSkin = null;
        }
        
      },
      chosePetSkin(petSkin){
        this.record.petSkin = petSkin;
      },
      choseEcu(ecu){
        this.record.ecu = ecu;
      },

      //验证合法
      verifyRecord(record){
        if(!record.type){
          this.$toast({
            showCancel:false,
            t1:'提示',//弹窗的标题
            t2: '请选择类型',//弹窗的内容
            type:'info'
          });
          return false;
        }else if(!record.track){
          this.$toast({
            showCancel:false,
            t1:'提示',//弹窗的标题
            t2: '请选择地图',//弹窗的内容
            type:'info'
          });
          return false;
        }else if(!record.time){
          this.$toast({
            showCancel:false,
            t1:'提示',//弹窗的标题
            t2: '请输入记录用时',//弹窗的内容
            type:'info'
          });
          return false;
        }else if(!this.verifyTime(record.time)){
          this.$toast({
            showCancel:false,
            t1:'错误',//弹窗的标题
            t2: '记录输入不规范',//弹窗的内容
            type:'error'
          });
          return false;
        }else if(!record.player){
          this.$toast({
            showCancel:false,
            t1:'提示',//弹窗的标题
            t2: '请选择车手',//弹窗的内容
            type:'info'
          });
          return false;
        }else if(!record.car){
          this.$toast({
            showCancel:false,
            t1:'提示',//弹窗的标题
            t2: '请选择赛车',//弹窗的内容
            type:'info'
          });
          return false;
        }else if(record.videoUrl&&this.verifyUrl(record.videoUrl)){
          this.$toast({
            showCancel:false,
            t1:'提示',//弹窗的标题
            t2: '视频地址有误',//弹窗的内容
            type:'info'
          });
          return false;
        }else if(!record.date){
          this.$toast({
            showCancel:false,
            t1:'提示',//弹窗的标题
            t2: '请输入日期',//弹窗的内容
            type:'info'
          });
          return false;
        }else if(!this.verifyDate(record.date)){
          this.$toast({
            showCancel:false,
            t1:'错误',//弹窗的标题
            t2: '日期格式有误',//弹窗的内容
            type:'error'
          });
          return false;
        }else{
          return true;
        }
      },
      verifyTime(time){
        return /^\d{1,2}[:：\.]\d{1,2}[:：\.]\d{1,2}$/.test(time);
      },
      verifyUrl(url){
        let strRegex = "^((https|http|ftp|rtsp|mms)?://)"
        + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@ 
        + "(([0-9]{1,3}\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184 
        + "|" // 允许IP和DOMAIN（域名）
        + "([0-9a-z_!~*'()-]+\.)*" // 域名- www. 
        + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\." // 二级域名 
        + "[a-z]{2,6})" // first level domain- .com or .museum 
        + "(:[0-9]{1,4})?" // 端口- :80 
        + "((/?)|" // a slash isn't required if there is no file name 
        + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$"; 
        let re=new RegExp(strRegex);
        return re.test(url)
      },
      verifyDate(date){
        return /^\d{4}[-\.]\d{1,2}[-\.]\d{1,2}$/.test(date) && new Date((new Date().format('yyyy-MM-dd'))) >= new Date(date);
      }
    }
};
</script>

<style lang="less" scoped>
.label-switch{
  margin-left: 0.5rem;
}
</style>

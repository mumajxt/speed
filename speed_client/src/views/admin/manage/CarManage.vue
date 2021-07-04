<template>
  <div id="car_manage">
    <div class="manage-container card">
      <div class="nav1">
        <div class="nav1-items">
          <div class="nav1-item" :class="nav1SelectedItem=='car'?'nav1-item-selected':''" @click="nav1SelectedItem='car'">
            <h3>赛车</h3>
          </div>
          <div class="nav1-item" :class="nav1SelectedItem=='skin'?'nav1-item-selected':''"  @click="nav1SelectedItem='skin'">
            <h3>赛车皮肤</h3>
          </div>
        </div>
        <div class="input-wrapper nav1-input">
          <div class="input-icon">
            <i class="iconfont iconsousuo"></i>
          </div>
          <input type="text" placeholder="搜索赛车/皮肤"  v-model="searchKey">
          <span class="input-icon">
            <i v-if="searchKey" class="iconfont iconclose" @click="searchKey=''"></i>
          </span>
        </div>
        <div v-show="nav1SelectedItem=='car'" @click="saveCar()" class="nav1-btn btn btn-yellow">
          <span class="icon iconfont iconclose rotate45"></span>
        </div>
        <div v-show="nav1SelectedItem=='skin'" @click="saveSkin()" class="nav1-btn btn btn-yellow">
          <span class="icon iconfont iconclose rotate45"></span>
        </div>

      </div>
      <div class="nav2">
        <div class="nav2-car nav2-items" v-if="nav1SelectedItem=='car'">
          <div class="btn-sm btn-label nav2-item" :class="nav2CarSelectedItem=='all'?'btn-label-yellow bold':'btn-grey'" @click="nav2CarSelectedItem='all'">
            <span>全部</span>
            <span class="count" v-text="carsAll.length"></span>
          </div>
          <div class="btn-sm btn-label nav2-item" :class="nav2CarSelectedItem=='as'?'btn-label-yellow bold':'btn-grey'" @click="nav2CarSelectedItem='as'">
            <span>A</span>
            <span class="count" v-text="as.length"></span>
          </div>
          <div class="btn-sm btn-label nav2-item" :class="nav2CarSelectedItem=='bs'?'btn-label-yellow':'btn-grey'"  @click="nav2CarSelectedItem='bs'">
            <span>B</span>
            <span class="count" v-text="bs.length"></span>
          </div>
          <div class="btn-sm btn-label nav2-item" :class="nav2CarSelectedItem=='cs'?'btn-label-yellow':'btn-grey'"  @click="nav2CarSelectedItem='cs'">
            <span>C</span>
            <span class="count" v-text="cs.length"></span>
          </div>
          <div class="btn-sm btn-label nav2-item" :class="nav2CarSelectedItem=='ds'?'btn-label-yellow':'btn-grey'"  @click="nav2CarSelectedItem='ds'">
            <span>D</span>
            <span class="count" v-text="ds.length"></span>
          </div>
          <div class="btn-sm btn-label nav2-item" :class="nav2CarSelectedItem=='xs'?'btn-label-yellow':'btn-grey'"  @click="nav2CarSelectedItem='xs'">
            <span>X</span>
            <span class="count" v-text="xs.length"></span>
          </div>
          <div class="btn-sm btn-label nav2-item" :class="nav2CarSelectedItem=='l1s'?'btn-label-yellow':'btn-grey'"  @click="nav2CarSelectedItem='l1s'">
            <span>L1</span>
            <span class="count" v-text="l1s.length"></span>
          </div>
          <div class="btn-sm btn-label nav2-item" :class="nav2CarSelectedItem=='m1s'?'btn-label-yellow':'btn-grey'"  @click="nav2CarSelectedItem='m1s'">
            <span>M1</span>
            <span class="count" v-text="m1s.length"></span>
          </div>
          <div class="btn-sm btn-label nav2-item" :class="nav2CarSelectedItem=='m2s'?'btn-label-yellow':'btn-grey'"  @click="nav2CarSelectedItem='m2s'">
            <span>M2</span>
            <span class="count" v-text="m2s.length"></span>
          </div>
        </div>

        <div class="nav2-skin  nav2-items" v-if="nav1SelectedItem=='skin'">
          <div class="btn-sm btn-label nav2-item" :class="nav2SkinSelectedItem=='all'?'btn-label-yellow bold':'btn-grey'" @click="nav2SkinSelectedItem='all'">
            <span>全部</span>
            <span class="count" v-text="skinsAll.length"></span>
          </div>
        </div>
      </div>

      
      <div class="table" v-show="nav1SelectedItem=='car'">
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
          <div class="rank">等级</div>
          <div class="operation">操作</div>
        </div>
        <div class="tr" v-for="car in tempCars" :key="car.id">
          <div class="id" v-text="car.id"></div>
          <div class="name" >
            <router-link :to="'/rank/car/'+car.id" v-text="car.name"></router-link>
          </div>
          <div class="rank">
            <span v-text="car.rank"></span>
          </div>
          <div class="operation" @click.stop>
            <span @click="updateCar(car)" class="btn btn-yellow iconfont iconupdate"></span>
            <span @click="readyToDeleteCar(car)" class="btn btn-red  iconfont icondelete"></span>
          </div>
        </div>
      </div>

      <div class="table" v-show="nav1SelectedItem=='skin'">
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
          <div class="car_name">赛车</div>
          <div class="operation">操作</div>
        </div>
        <div class="tr" v-for="skin in tempSkins" :key="skin.id">
          <div class="id" v-text="skin.id"></div>
          <div class="name" >
            <span v-text="skin.name"></span>
          </div>
          <div class="car_name">
            <span v-text="skin.car.name"></span>
          </div>
          <div class="operation" @click.stop>
            <span @click="updateSkin(skin)" class="btn btn-yellow iconfont iconupdate"></span>
            <span @click="readyToDeleteSkin(skin)" class="btn btn-red  iconfont icondelete"></span>
          </div>
        </div>
      </div>

    </div>
    

    <DrawerCar :carOut="car" v-if="isShowDrawerCar" @close="isShowDrawerCar=false"></DrawerCar>
    <DrawerCarSkin :carSkinOut="skin" v-if="isShowDrawerSkin" @close="isShowDrawerSkin=false"></DrawerCarSkin>

  
  </div>
</template>

<script>
export default {
    name: "carManage",
    components: {
      DrawerCar:() => import('@/components/drawer/DrawerCar'),
      DrawerCarSkin:() => import('@/components/drawer/DrawerCarSkin')
    },
    mounted(){
      this.listCars();
      this.listSkins();
    },
    computed: {
      carsAll(){
        return this.$store.state.cars
        .filter(car=>new RegExp(this.searchKey).test(car.name))
        .sort(this.orderBySort);
      },
      skinsAll(){
        return this.$store.state.carSkins
        .filter(skin=>new RegExp(this.searchKey).test(skin.name))
        .sort(this.orderBySort);
      },
      tempCars(){
        switch(this.nav2CarSelectedItem){
          case 'all':return this.carsAll;
          case 'as':return this.as;
          case 'bs':return this.bs;
          case 'cs':return this.cs;
          case 'ds':return this.ds;
          case 'xs':return this.xs;
          case 'l1s':return this.l1s;
          case 'm1s':return this.m1s;
          case 'm2s':return this.m2s;
          default:return [];
        }
      },
      tempSkins(){
        return this.skins;
      },
      as(){
        return this.carsAll
        .filter(car=>car.rank=='A');
      },
      bs(){
        return this.carsAll
        .filter(car=>car.rank=='B');
      },
      cs(){
        return this.carsAll
        .filter(car=>car.rank=='C');
      },
      ds(){
        return this.carsAll
        .filter(car=>car.rank=='D');
      },
      xs(){
        return this.carsAll
        .filter(car=>car.rank=='X');
      },
      l1s(){
        return this.carsAll
        .filter(car=>car.rank=='L1');
      },
      m1s(){
        return this.carsAll
        .filter(car=>car.rank=='M1');
      },
      m2s(){
        return this.carsAll
        .filter(car=>car.rank=='M2');
      },
      skins(){
        return this.skinsAll;
      }
    },
    data() {
      return {
        loading:false,
        searchKey:'',
        car:null,
        skin:null,
        isShowDrawerCar:false,
        isShowDrawerSkin:false,
        orderBy:'idDown',
        nav1SelectedItem:'car',
        nav2CarSelectedItem:'all',
        nav2SkinSelectedItem:'all',
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
      updateCar(car){
        this.car=car;
        this.isShowDrawerCar=true;
      },
      updateSkin(skin){
        this.skin=skin;
        this.isShowDrawerSkin=true;
      },
      saveCar(){
        this.car=null;
        this.isShowDrawerCar=true;
      },
      saveSkin(){
        this.skin=null;
        this.isShowDrawerSkin=true;
      },
      listCars(){
        if(this.carsAll.length==0){
          let that = this;
          this.$store.dispatch('listCars').then(() => {
            that.loading = false;
          }).catch(() => {
            that.$toast({
              showCancel:false,
              t1:'错误',//弹窗的标题
              t2: '无法请求赛车数据',//弹窗的内容
              type:'error'
            });
          });
        }
      },
      listSkins(){
        if(this.skinsAll.length==0){
          let that = this;
          this.$store.dispatch('listCarSkins').then(() => {
            that.loading = false;
          }).catch(() => {
            that.$toast({
              showCancel:false,
              t1:'错误',//弹窗的标题
              t2: '无法请求赛车皮肤数据',//弹窗的内容
              type:'error'
            });
          });
        }
      },
      // 准备删除
      readyToDeleteCar(car){
        this.$toast({
          showCancel:true,
          t1:'提示',//弹窗的标题
          t2: '确定要<span class="red">删除</span>赛车<b>'+car.name+'</b>?',//弹窗的内容
          type:'warning'
        }).then(()=>{
          this.deleteCar(car);
        }).catch(()=>{
          console.log('取消删除');
        });
      },

      // 删除赛车
      deleteCar(car){
        let that = this;
        this.$axios({
            method:'delete',
            url:'/api/car/'+car.id,
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
              that.$store.dispatch('listCars');
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


      // 准备删除
      readyToDeleteSkin(skin){
        this.$toast({
          showCancel:true,
          t1:'提示',//弹窗的标题
          t2: '确定要<span class="red">删除</span>赛车皮肤<b>'+skin.name+'</b>?',//弹窗的内容
          type:'warning'
        }).then(()=>{
          this.deleteSkin(skin);
        }).catch(()=>{
          console.log('取消删除');
        });
      },

      // 删除车队
      deleteSkin(skin){
        let that = this;
        this.$axios({
            method:'delete',
            url:'/api/carSkin/'+skin.id,
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
              that.$store.dispatch('listCarSkins');
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

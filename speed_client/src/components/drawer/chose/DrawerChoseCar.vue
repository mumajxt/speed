<template>
  <div class="mask-wrapper animate__animated" :class="visible?'animate__fadeIn':'animate__fadeOut'" @click="close()" @click.stop>
    <div class="drawer animate__animated" :class="visible?'animate__fadeInUp':'animate__fadeOutDown'" @click.stop>
      <div class="drawer-header">
        <h2>选择赛车</h2>
      </div>
      <div class="drawer-body">
         <div class="search-box">
          <span class="search icon iconfont iconsousuo"></span>
          <input placeholder="搜索" type="text" v-model="searchKey">
          <span class="close">
            <span v-if="searchKey" class="icon iconfont iconclose" @click="searchKey=''"></span>
          </span>
          <span class="btn btn-yellow" @click="isShowDrawerCar=true">
            <span class="icon iconfont iconclose rotate45"></span>
            <span> 添加</span>
          </span>
        </div>
        <div class="container">
          <div class="left nav">
            <div class="nav-item"
              :class="navSelectedItem=='all'?'nav-item-selected':''"
              @click="navSelectedItem='all'">
              全部<span class="count" v-text="carsAll.length"></span>
            </div>
            <div class="nav-item" v-if="as.length"
              :class="navSelectedItem=='a'?'nav-item-selected':''"
              @click="navSelectedItem='a'">
              A<span class="count" v-text="as.length"></span>
            </div>
            <div class="nav-item" v-if="bs.length"
              :class="navSelectedItem=='b'?'nav-item-selected':''"
              @click="navSelectedItem='b'">
              B<span class="count" v-text="bs.length"></span>
            </div>
            <div class="nav-item" v-if="cs.length"
              :class="navSelectedItem=='c'?'nav-item-selected':''"
              @click="navSelectedItem='c'">
              C<span class="count" v-text="cs.length"></span>
            </div>
            <div class="nav-item" v-if="ds.length"
              :class="navSelectedItem=='d'?'nav-item-selected':''"
              @click="navSelectedItem='d'">
              D<span class="count" v-text="ds.length"></span>
            </div>
            <div class="nav-item" v-if="xs.length"
              :class="navSelectedItem=='x'?'nav-item-selected':''"
              @click="navSelectedItem='x'">
              X<span class="count" v-text="xs.length"></span>
            </div>
            <div class="nav-item" v-if="l1s.length"
              :class="navSelectedItem=='l1'?'nav-item-selected':''"
              @click="navSelectedItem='l1'">
              L1<span class="count" v-text="l1s.length"></span>
            </div>
            <div class="nav-item" v-if="m1s.length"
              :class="navSelectedItem=='m1'?'nav-item-selected':''"
              @click="navSelectedItem='m1'">
              M1<span class="count" v-text="m1s.length"></span>
            </div>
            <div class="nav-item" v-if="m2s.length"
              :class="navSelectedItem=='m2'?'nav-item-selected':''"
              @click="navSelectedItem='m2'">
              M2<span class="count" v-text="m2s.length"></span>
            </div>
          </div>
          <div class="right cars">
            <span class="label btn btn-label"
              v-for="car in tempCars" 
              :key="car.id" v-text="car.name" 
              :class="carOut?(carOut.id==car.id?'btn-label-yellow':''):''"
              @click="choseCar(car)">
            </span>
          </div>
        </div>
        
      </div>
      <div class="drawer-footer">
        <div class="drawer-footer-btn drawer-footer-btn-grey" @click="close()">取消</div>
      </div>
    </div>

    <DrawerCar :carNameOut="searchKey"  v-if="isShowDrawerCar" @close="isShowDrawerCar=false" @choseCar="choseCar"></DrawerCar>
  </div>
</template>

<script>
import DrawerCar from '@/components/drawer/DrawerCar';
export default {
    name: "DrawerChoseCar",
    components: {
      DrawerCar
    },
    props: {
      carOut:Object,
      typeOut:String
    },
    mounted(){
      this.initCars();
    },
    watch: {
      visible:function(val){
        if(val){
          this.isShow=true;
          this.navSelectedItem='all'
        }else{
          setTimeout(()=>{
            this.isShow=false;
          },300);
          this.searchKey='';
        }
      }
    },
    computed: {
      carsAll(){
        let reg = new RegExp(this.searchKey);
        return this.$store.state.cars
        .filter(car => this.carRankList.indexOf(car.rank)>-1)
        .filter(car=>reg.test(car.name))
        .sort((a,b)=>{
          return b.power-a.power;
        });
      },
      tempCars(){
        switch(this.navSelectedItem){
          case 'all':return this.carsAll;
          case 'a':return this.as;
          case 'b':return this.bs;
          case 'c':return this.cs;
          case 'd':return this.ds;
          case 'x':return this.xs;
          case 'l1':return this.l1s;
          case 'm1':return this.m1s;
          case 'm2':return this.m2s;
          default:return [];
        }
      },
      carRankList(){
        switch(this.typeOut){
          case 'a':return ['A'];
          case 'b':return ['B'];
          case 'c':return ['C'];
          case 'd':return ['D'];
          case 'x':return ['X'];
          case 'l1':return ['L1'];
          case 'm1':return ['M1'];
          case 'm2':return ['M2'];
          case 'undrift':return ['A','X'];
          case 'sCup':return ['A'];
          case 'asiaCup':return ['A'];
          default:return ['A','B','C','D','X','L1','M1','M2'];
        }
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
    },
    data() {
      return {
        visible:true,
        searchKey:'',
        isShowDrawerCar:false,
        navSelectedItem:'all',
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
      choseCar(car){
        this.$emit('choseCar',car);
        this.close();
      },
      initCars(){
        this.loading=true;
        if(this.carsAll.length==0){
           this.$store.dispatch('listCars').then(() => {
            this.loading = false;
          }).catch(() => {
            that.$toast({
              showCancel:false,
              t1:'错误',
              t2: '无法请求车队数据',
              type:'error'
            });
            this.loading = false;
          });
        }
      }
    }
};
</script>

<style lang="less" scoped>
.mask-wrapper {
  .drawer{
    .drawer-body{
      //overflow: hidden;
      background-color: #fff;

      .search-box{
        position: relative;
        display: flex;
        align-items: center;
        color: @c3;
        margin:1rem;
        flex: 1;

        span.search{
          height:2.4rem;
          width: 2.4rem;
          line-height: 2.4rem;
          text-align: center;
          
          border-top-left-radius: 0.6rem;
          border-bottom-left-radius: 0.6rem;
          background-color: @c_bg;
        }
        input{
          display: block;
          width: 0;
          flex: 1;
          box-sizing: border-box;
          background-color: @c_bg;
          height: 2.4rem;
          line-height: 2.4rem;
          font-size: 1rem;
          border: none;
          border-radius: 0;
          outline: none;
          color: @c1;
          text-align: left;
          &:focus{
            border: none;
          }
        }
        span.close{
          cursor: pointer;
          height: 2.4rem;
          width: 2.4rem;
          line-height: 2.4rem;
          text-align: center;
          border-top-right-radius: 0.6rem;
          border-bottom-right-radius:0.6rem;
          background-color: @c_bg;
        }
        span.btn{
          margin-left: 1rem;

        }
        
      }

      .container{
        display: flex;
        .left{
          overflow-y: auto;
          height: 21.6rem;
          .nav-item{
            height: 2.4rem;
            line-height: 2.4rem;
            padding: 0 1rem;
            cursor: pointer;
            .count{
              font-size: 0.8rem;
              margin-left: 0.5rem;
            }
          }
          .nav-item-selected{
            border-left: 0.2rem solid @c_yellow;
            font-weight: bold;
            color: @c_yellow;
            background-image: linear-gradient(to right,@c_yellow_bg, #fff);
          }
        }

        .right{
          flex: 1;
          padding: 0.5rem;
          height: 21.6rem;
          overflow-y: auto;
          .label{
            margin-right: 0.5rem;
            margin-bottom: 0.5rem;
          }
        }
      }

    }
  }  
}

</style>

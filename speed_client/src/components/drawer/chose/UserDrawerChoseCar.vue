<template>
  <div class="mask-wrapper animate__animated" :class="visible?'animate__fadeIn':'animate__fadeOut'" @click="close()" @click.stop>
    <div class="drawer animate__animated" :class="visible?'animate__fadeInUp':'animate__fadeOutDown'" @click.stop>
      <div class="drawer-header">
        <h2>筛选赛车</h2>
      </div>
      <div class="drawer-body">
        <div class="input-wrapper">
          <div class="input-icon">
            <i class="iconfont iconsousuo"></i>
          </div>
          <input type="text" placeholder="搜索"  v-model="searchKey">
          <span class="input-icon">
            <i v-if="searchKey" class="iconfont iconclose" @click="searchKey=''"></i>
          </span>
        </div>
        <div class="container">
          <div class="left nav">
            <div class="nav-item"
              :class="navSelectedItem=='all'?'nav-item-selected':''"
              @click="navSelectedItem='all'">
              全部<span class="count" v-text="carsAll.length"></span>
            </div>
            <div class="nav-item" v-if="ts.length"
                 :class="navSelectedItem=='t'?'nav-item-selected':''"
                 @click="navSelectedItem='t'">
              T<span class="count" v-text="ts.length"></span>
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
<!--            <div class="nav-item" v-if="l1s.length"-->
<!--              :class="navSelectedItem=='l1'?'nav-item-selected':''"-->
<!--              @click="navSelectedItem='l1'">-->
<!--              L1<span class="count" v-text="l1s.length"></span>-->
<!--            </div>-->
<!--            <div class="nav-item" v-if="m1s.length"-->
<!--              :class="navSelectedItem=='m1'?'nav-item-selected':''"-->
<!--              @click="navSelectedItem='m1'">-->
<!--              M1<span class="count" v-text="m1s.length"></span>-->
<!--            </div>-->
<!--            <div class="nav-item" v-if="m2s.length"-->
<!--              :class="navSelectedItem=='m2'?'nav-item-selected':''"-->
<!--              @click="navSelectedItem='m2'">-->
<!--              M2<span class="count" v-text="m2s.length"></span>-->
<!--            </div>-->
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
        <span class="divider"></span>
        <div class="drawer-footer-btn drawer-footer-btn-red" @click="choseCar(null)">清除筛选</div>
      </div>
    </div>

  </div>
</template>

<script>
export default {
    name: "UserDrawerChoseCar",
    props: {
      carOut:Object,
      type:String,
      pet:String,
      ecu:String
    },
    created(){
      this.initCars();
    },
    watch: {
      type:function(){
        this.initCars();
      },
      pet:function(){
        this.initCars();
      },
      ecu:function(){
        this.initCars();
      }
    },
    computed: {
      carsAll(){
        let reg = new RegExp(this.searchKey);
        return this.cars
        .filter(car=>reg.test(car.name))
        .sort((a,b)=>{
          return b.power-a.power;
        });
      },
      tempCars(){
        switch(this.navSelectedItem){
          case 'all':return this.carsAll;
          case 't':return this.ts;
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
     ts(){
        return this.carsAll
            .filter(car=>car.rank=='T');
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
        navSelectedItem:'all',
        loading:false,
        cars:[],
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
        let params={
          type:this.type,
          pet:this.pet,
          ecu:this.ecu
        }
        this.$axios({
          method: "get",
          url: "/api/cars",
          params: params
        }).then((response) => {
          let info = response.data;
          console.log("--------------------------");
          if(info.flag){
            console.log("返回筛选赛车数据↓");
            console.log(info.data);
            this.cars=info.data;
          }else{
            console.error(info.errorMsg);
          }
          console.log("--------------------------");
          this.loading=false;
        }).catch((error) => {
          console.error(error);
          this.loading=false;
        });
      }
    }
};
</script>

<style lang="less" scoped>
.mask-wrapper {
  .drawer{
    .drawer-body{
      background-color: #fff;
      .input-wrapper{
        margin: 1rem;
      }
      .container{
        display: flex;
        .left{
          overflow-y: auto;
          height: 16.2rem;
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
          height: 16.2rem;
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

<template>
  <div id="pet_manage">
    <div class="manage-container card">
      <div class="nav1">
        <div class="nav1-items">
          <div class="nav1-item" :class="nav1SelectedItem=='pet'?'nav1-item-selected':''" @click="nav1SelectedItem='pet'">
            <h3>宠物</h3>
          </div>
          <div class="nav1-item" :class="nav1SelectedItem=='skin'?'nav1-item-selected':''"  @click="nav1SelectedItem='skin'">
            <h3>宠物皮肤</h3>
          </div>
        </div>
        <div class="input-wrapper nav1-input">
          <div class="input-icon">
            <i class="iconfont iconsousuo"></i>
          </div>
          <input type="text" placeholder="搜索宠物/皮肤"  v-model="searchKey">
          <span class="input-icon">
            <i v-if="searchKey" class="iconfont iconclose" @click="searchKey=''"></i>
          </span>
        </div>
        <div v-show="nav1SelectedItem=='pet'" @click="savePet()" class="nav1-btn btn btn-yellow">
          <span class="icon iconfont iconclose rotate45"></span>
        </div>
         <div v-show="nav1SelectedItem=='skin'" @click="saveSkin()" class="nav1-btn btn btn-yellow">
          <span class="icon iconfont iconclose rotate45"></span>
        </div>
      </div>

      <div class="nav2">
        <div class="nav2-pet nav2-items" v-if="nav1SelectedItem=='pet'">
          <div class="btn-sm btn-label nav2-item" :class="nav2PetSelectedItem=='all'?'btn-label-yellow bold':'btn-grey'" @click="nav2PetSelectedItem='all'">
            <span>全部</span>
            <span class="count" v-text="petsAll.length"></span>
          </div>
        </div>

        <div class="nav2-skin  nav2-items" v-if="nav1SelectedItem=='skin'">
          <div class="btn-sm btn-label nav2-item" :class="nav2SkinSelectedItem=='all'?'btn-label-yellow bold':'btn-grey'" @click="nav2SkinSelectedItem='all'">
            <span>全部</span>
            <span class="count" v-text="skinsAll.length"></span>
          </div>
        </div>
      </div>

      
      <div class="table" v-show="nav1SelectedItem=='pet'">
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
          <div class="operation">操作</div>
        </div>

        <div class="tr" v-for="pet in tempPets" :key="pet.id">
          <div class="id" v-text="pet.id"></div>
          <div class="name" >
            <router-link :to="'/rank/pet/'+pet.id" v-text="pet.name"></router-link>
          </div>
           <div class="operation" @click.stop>
            <span @click="updatePet(pet)" class="btn btn-yellow iconfont iconupdate"></span>
            <span @click="readyToDeletePet(pet)" class="btn btn-red  iconfont icondelete"></span>
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
          <div class="pet_name">宠物</div>
          <div class="operation">操作</div>
        </div>
        <div class="tr" v-for="skin in tempSkins" :key="skin.id">
          <div class="id" v-text="skin.id"></div>
          <div class="name" >
            <span v-text="skin.name"></span>
          </div>
          <div class="pet_name">
            <span v-text="skin.pet.name"></span>
          </div>
          <div class="operation" @click.stop>
            <span @click="updateSkin(skin)" class="btn btn-yellow iconfont iconupdate"></span>
            <span @click="readyToDeleteSkin(skin)" class="btn btn-red  iconfont icondelete"></span>
          </div>
        </div>
      </div>

    </div>
    

    <DrawerPet :petOut="pet" v-if="isShowDrawerPet" @close="isShowDrawerPet=false"></DrawerPet>
    <DrawerPetSkin :petSkinOut="skin" v-if="isShowDrawerSkin" @close="isShowDrawerSkin=false"></DrawerPetSkin>

  
  </div>
</template>

<script>
export default {
    name: "petManage",
    components: {
      DrawerPet:() => import('@/components/drawer/DrawerPet'),
      DrawerPetSkinK:() => import('@/components/drawer/DrawerPetSkin')
    },
    mounted(){
      this.listPets();
      this.listSkins();
    },
    computed: {
      petsAll(){
        return this.$store.state.pets
        .filter(pet=>new RegExp(this.searchKey).test(pet.name))
        .sort(this.orderBySort);
      },
      skinsAll(){
        return this.$store.state.petSkins
        .filter(skin=>new RegExp(this.searchKey).test(skin.name))
        .sort(this.orderBySort);
      },
      tempPets(){
        return this.petsAll;
      },
      tempSkins(){
        return this.skins;
      },
      skins(){
        return this.skinsAll;
      }
    },
    data() {
      return {
        loading:false,
        searchKey:'',
        pet:null,
        skin:null,
        isShowDrawerPet:false,
        isShowDrawerSkin:false,
        orderBy:'idDown',
        nav1SelectedItem:'pet',
        nav2PetSelectedItem:'all',
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
      updatePet(pet){
        this.pet=pet;
        this.isShowDrawerPet=true;
      },
      updateSkin(skin){
        this.skin=skin;
        this.isShowDrawerSkin=true;
      },
      savePet(){
        this.pet=null;
        this.isShowDrawerPet=true;
      },
      saveSkin(){
        this.skin=null;
        this.isShowDrawerSkin=true;
      },
      listPets(){
        if(this.petsAll.length==0){
          let that = this;
          this.$store.dispatch('listPets').then(() => {
            that.loading = false;
          }).catch(() => {
            that.$toast({
              showCancel:false,
              t1:'错误',//弹窗的标题
              t2: '无法请求宠物数据',//弹窗的内容
              type:'error'
            });
          });
        }
      },
      listSkins(){
        if(this.skinsAll.length==0){
          let that = this;
          this.$store.dispatch('listPetSkins').then(() => {
            that.loading = false;
          }).catch(() => {
            that.$toast({
              showCancel:false,
              t1:'错误',//弹窗的标题
              t2: '无法请求宠物皮肤数据',//弹窗的内容
              type:'error'
            });
          });
        }
      },
      // 准备删除
      readyToDeletePet(pet){
        this.$toast({
          showCancel:true,
          t1:'提示',//弹窗的标题
          t2: '确定要<span class="red">删除</span>宠物<b>'+pet.name+'</b>?',//弹窗的内容
          type:'warning'
        }).then(()=>{
          this.deletePet(pet);
        }).catch(()=>{
          console.log('取消删除');
        });
      },

      // 删除宠物
      deletePet(pet){
        let that = this;
        this.$axios({
            method:'delete',
            url:'/api/pet/'+pet.id,
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
              that.$store.dispatch('listPets');
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
          t2: '确定要<span class="red">删除</span>宠物皮肤<b>'+skin.name+'</b>?',//弹窗的内容
          type:'warning'
        }).then(()=>{
          this.deleteSkin(skin);
        }).catch(()=>{
          console.log('取消删除');
        });
      },

      // 删除宠物
      deleteSkin(skin){
        let that = this;
        this.$axios({
            method:'delete',
            url:'/api/petSkin/'+skin.id,
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
              that.$store.dispatch('listPetSkins');
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

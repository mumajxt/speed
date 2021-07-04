<template>
  <div id="admin">
    <div id="header" class="shadow">
      <div class="center">
        <div class="nav">
          <div class="bread" @click="showListDrawer=true">
            <h1 class="iconfont iconbread1">
            </h1>
            <h1  class="iconfont iconbread2 yellow"></h1>
          </div>
          <div class="nav-links">
            <router-link to="/admin/records" class="logo">
              <Logo></Logo>
              <h1>榜单<span class="yellow">管理</span></h1>
            </router-link>
            <div class="links">
              <router-link to="/admin/records" active-class="active">记录</router-link>
              <router-link to="/admin/tracks" active-class="active">地图</router-link>
              <router-link to="/admin/players" active-class="active">车手</router-link>
              <router-link to="/admin/cars" active-class="active">赛车</router-link>
              <router-link to="/admin/pets" active-class="active">宠物</router-link>
              <router-link to="/admin/ecus" active-class="active">ECU</router-link>
            </div>
            
          </div>
          <div v-if="admin" class="admin" @click="showAdminDrawer = true">
            <h1 class="iconfont iconuser1 yellow"></h1>
            <h1 class="iconfont iconuser2"></h1>
          </div>
        </div>
        
      </div>
      
		</div>
		<div id="admin-container" class="center">
			<router-view/>
		</div>

    <Drawer
    :visible.sync="showAdminDrawer"
    @close="showAdminDrawer=false"
    class="adminDrawer drawer"
     v-if="admin">
      <template v-slot:header>
        <h2 v-text="admin.nickname"></h2>
      </template>

      <template v-slot:body>
        <ul>
          <li class="clickable" @click="linkTo('/admin-login')">
            <span>切换账号</span>
          </li>
        </ul>
      </template>
      <template v-slot:footer>
        <div class="drawer-footer-btn drawer-footer-btn-grey" @click="showAdminDrawer=false">取消</div>
      </template>
    </Drawer>

    <Drawer
    :visible.sync="showListDrawer"
    @close="showListDrawer=false"
    class="listDrawer drawer">
      <template v-slot:header>
        <h2>菜单</h2>
      </template>

      <template v-slot:body>
        <div class="drawer-labels" @click="showListDrawer=false">
          <div class="drawer-label-wrapper"><router-link class="drawer-label" to="/admin/records" active-class="active">记录</router-link></div>
          <div class="drawer-label-wrapper"><router-link class="drawer-label" to="/admin/tracks" active-class="active">地图</router-link></div>
          <div class="drawer-label-wrapper"><router-link class="drawer-label" to="/admin/players" active-class="active">车手</router-link></div>
          <div class="drawer-label-wrapper"><router-link class="drawer-label" to="/admin/cars" active-class="active">赛车</router-link></div>
          <div class="drawer-label-wrapper"><router-link class="drawer-label" to="/admin/pets" active-class="active">宠物</router-link></div>
          <div class="drawer-label-wrapper"><router-link class="drawer-label" to="/admin/ecus" active-class="active">ECU</router-link></div>
        </div>
      </template>
      <template v-slot:footer>
        <div class="drawer-footer-btn drawer-footer-btn-grey" @click="showListDrawer=false">取消</div>
      </template>
    </Drawer>
  </div>
</template>

<script>
import Logo from '@/components/Logo';
export default {
  name: "Admin",
  components: {
    Logo
  },
  created(){

  },
  mounted(){

  },
  destroyed(){

  },
  watch: {

  },
  computed: {
    admin: function(){
      return this.$store.state.admin;
    }
  },
  data() {
    return {
      showAdminDrawer: false,
      showListDrawer:false
    };
  },
  methods: {
    linkTo(url){
      this.showAdminDrawer = false;
      this.$router.push(url);
    }
  }
};
</script>

<style lang="less" scoped>
#admin {
  #header{
    position: fixed;
    top: 0;
    left: 0;
    z-index: 999;
    height: 4rem;
    width: 100%;
    background-color: rgba(255, 255, 255, 0.9);
    backdrop-filter: blur(0.5rem);
    
    .nav{
      display: flex;
      align-items: center;
      height: 4rem;
      padding: 0 1rem;

      .bread{
        width: 2rem;
        height: 2rem;
        text-align: center;
        line-height: 2rem;
        cursor: pointer;
        position: relative;
        &>*{
          position: absolute;
          top: 0;
          left: 0;
        }
      }
      .admin{
        width: 2rem;
        height: 2rem;
        text-align: center;
        line-height: 2rem;
        cursor: pointer;
        position: relative;
        &>*{
          position: absolute;
          top: 0;
          left: 0;
        }
      }

      
      
      .nav-links {
        flex: 1;
        display: flex;
        align-items: center;
        justify-content: center;
        .logo{
            display: inline-flex;
            align-items: center;
            h1{
                margin-left: 0.5rem;
                height: 1.6rem;
                line-height: 1.6rem;
            }
        }
        .links {
          display: none;
          width: 24rem;
          margin-left: 0.5rem;
          
          &>* {
            height: 4rem;
            line-height: 4rem;
            font-size: 1.2rem;
            padding: 0 0.5rem;
            color: @c2;
            text-decoration: none;
            box-sizing: border-box;
            &:hover {
              color: @c1;
            }
          }
          .active {
            font-weight: bold;
            color: @c_yellow;
            border-bottom: 0.2rem solid @c_yellow;
          }
        }
      }

      @media all and (min-width: 768px){
        .bread{
          display: none;
        }
        .nav-links{
          justify-content: start;
          .links{
            display: flex;
          }
        }
      }
    }
    
  }

  #admin-container{
      margin-top: 5rem;
  }


  .drawer {
		ul {
			li{
				padding: 1rem;
				text-align: center;
				font-size: 1rem;
				color: @c1;
				margin-bottom: 0.1rem;
				background-color: #fff;
				font-weight: bold;
			}
		}
	}
  .listDrawer{
    ul{
      li{
        a{
          color: @c1;
        }
        .active{
          font-weight: bold;
          color: @c_yellow;
        }
      }
    }
  }
}

</style>

<template>
  <div class="notfound">
    <div class="info">
      <div class="top">
        <Logo></Logo>
        <h2>页面维护中...</h2>
      </div>
      
       <router-link to="/">返回主页<i class="iconfont iconxiala rotate-90"></i> </router-link>
    </div>
   
  </div>
</template>

<script>
import Logo from '@/components/Logo';
export default {
  name: "NotFound",
  components: {
    Logo
  },
};
</script>

<style lang="less" scoped>
.notfound {
  
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  text-align: center;
  &::after {
    content: "";
    display: inline-block;
    height: 100%;
    width: 0;
    vertical-align: middle;
  }
  

  .info{
    display: inline-block;
    .top{
      display: flex;
      align-items: center;
    }
  }
}

</style>

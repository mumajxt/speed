<template>
  <div id="about" class="records-container">
    <div class="table card">
      <div class="table-title">
        <span class="bg-yellow table-title-border"></span>
        <h3>投稿规则</h3>
      </div>
      <div class="content">
        <p>1.投稿请发送影子链接，欢迎投稿~</p>
        <p>2.只收录正式服记录；</p>
        <p>3.后追不收录国服记录；</p>
        <p>4.同毫秒展示日期较早的记录；</p>
      </div>
    </div>

    <div class="table card">
      <div class="table-title">
        <span class="bg-yellow table-title-border"></span>
        <h3>联系我们</h3>
      </div>
      <div class="content link">
        <p @click="joinQQGroup()">投稿与交流群(<i class="blue iconfont iconqq"></i>620736393)</p>
        <p @click="QQFeedback()">网站建议与反馈(<i class="blue iconfont iconqq"></i>1491822925)</p>
        <textarea readonly id="qq_group" style="position: absolute;top: 0;left: 0;opacity: 0;z-index: -10;">620736393</textarea>
        <textarea readonly id="qq_feedback" style="position: absolute;top: 0;left: 0;opacity: 0;z-index: -10;">1491822925</textarea>
      </div>
      <!-- <div class="content">

        <article>
          A车：q某<span class="iconfont iconqq blue">825481332</span>
        </article>
       
        <article>
          悬浮车：海神<span class="iconfont iconqq blue">1204830838</span>
        </article>

        <article>
          B车榜单：<span class="iconfont iconqq blue">350998719</span>
        </article>

        <article>
          C、D车榜单：黑桥宝宝<span class="iconfont iconqq blue">237396614</span>
        </article>

         <article>
          A车(抓地)：阿烁<span class="iconfont iconqq blue">913973160</span>
        </article>

        <article>
          S联赛：胖胖胖胖胖溢<span class="iconfont iconqq blue">1046780453</span>
        </article>
      </div> -->
    </div>
  </div>
</template>

<script>

export default {
    name: "About",
    components: {

    },
    props: {
      list:String
    },
    data() {
      return {
        loading: true,
      };
    },
    methods: {
      joinQQGroup: function() {
        let input = document.getElementById("qq_group");
        input.select(); // 选中文本
        document.execCommand("copy"); // 执行浏览器复制命令
        this.$toast({
          showCancel:false,
          t1:':)',//弹窗的标题
          t2: "已复制群号("+input.value+")，去QQ粘贴搜索吧~",//弹窗的内容
          type:'success'
        });
        //alert("已复制群号（"+input.value+"），去QQ粘贴搜索吧~");
      },
      QQFeedback: function() {
        let input = document.getElementById("qq_feedback");
        input.select(); // 选中文本
        document.execCommand("copy"); // 执行浏览器复制命令
        //alert("已复制QQ（"+input.value+"），去QQ粘贴搜索吧~");
        this.$toast({
          showCancel:false,
          t1:':)',//弹窗的标题
          t2: "已复制个人账号("+input.value+")，去QQ粘贴搜索吧~",//弹窗的内容
          type:'success'
        });
      }
    }
};
</script>

<style lang="less" scoped>
#about {
  border-top: 1px solid transparent;
  .content{
    padding: 1rem;
    p{
      line-height: 1.6rem;
    }

    &.link{
      p{
        cursor: pointer;
        &:hover{
          color: @c_yellow;
        }
      }
    }
   /*  article{
      padding: 1rem 0;
      border-bottom: 1px dashed @c_grey_bg;
      &:first-child{
        padding-top: 0;
      }
    } */
  }
  //overflow: hidden;
  //padding-top: 1rem;
 /*  article {
    background-color: white;
    margin-bottom: 0.5rem;
    padding: 0.5rem;

    .title {
      margin: 0.5rem 0;
      height: 1rem;
      line-height: 1rem;
      border-left: @c_yellow solid 0.2rem;
      padding-left: 0.3rem;
      font-size: 1rem;
      font-weight: bold;
    }
    .text {
      margin: 0 0.5rem;
      font-size: 1rem;
      .iconbilibili-fill {
        font-size: 1rem;
        color: @c_bili;
      }
      .icontubiao215 {
        font-size: 1rem;
        color: @c_qq;
      }
    }
  } */
}

</style>

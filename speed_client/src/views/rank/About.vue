<template>
  <div id="about" class="records-container">
    <div class="table card">
      <div class="table-title">
        <span class="bg-yellow table-title-border"></span>
        <h3>投稿规则</h3>
      </div>
      <div class="content">
        <p>1.榜单不计<span class="red">后追</span>记录；</p>
        <p>2.榜单不计<span class="red">连点器/宏</span>记录；</p>
        <p>3.榜单不计<span class="red">非正式服</span>记录；</p>
        <p>4.榜单不计<span class="red">非国服</span>记录；</p>
      </div>
    </div>
    <div class="table card">
      <div class="table-title">
        <span class="bg-yellow table-title-border"></span>
        <h3>数据支持</h3>
      </div>
      <div class="content">
        <p><a href="https://space.bilibili.com/390629619/dynamic" target="_blank">@purple紫灵</a>（T车、A车榜）</p>
        <p><a href="https://space.bilibili.com/436508261/dynamic" target="_blank">@易成yc</a>（B车榜）</p>
        <p><a href="https://space.bilibili.com/157342682/dynamic" target="_blank">@黑桥宝宝</a>（C车、板车榜）</p>
        <p><a href="https://space.bilibili.com/452006583/dynamic" target="_blank">@無欣不欢</a>（悬浮榜）</p>
        <p><a href="https://space.bilibili.com/150405895/dynamic" target="_blank">@Chanlq</a>（联赛榜）</p>
        <p><a href="https://space.bilibili.com/43717434/dynamic" target="_blank">@胖胖胖胖胖溢</a>（亚洲杯榜）</p>
        <p><a href="https://space.bilibili.com/85907356/dynamic" target="_blank">@DBG_呵呵</a>（鸽子榜（bushi) 前联赛榜）</p>
        <p><a href="https://space.bilibili.com/487546810/dynamic" target="_blank">@某q的冬瓜</a>（前A车有宠带ecu榜）</p>
        <p><a href="https://space.bilibili.com/305762820/dynamic" target="_blank">@暴风西决</a>（精神支持）</p>
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
        <textarea readonly id="qq_group"
                  style="position: absolute;top: 0;left: 0;opacity: 0;z-index: -10;">620736393</textarea>
        <textarea readonly id="qq_feedback" style="position: absolute;top: 0;left: 0;opacity: 0;z-index: -10;">1491822925</textarea>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'About',
  components: {},
  props: {
    list: String,
  },
  data() {
    return {
      loading: true,
    };
  },
  methods: {
    joinQQGroup: function () {
      let input = document.getElementById('qq_group');
      input.select(); // 选中文本
      document.execCommand('copy'); // 执行浏览器复制命令
      this.$toast({
        showCancel: false,
        t1: ':)',//弹窗的标题
        t2: '已复制群号(' + input.value + ')，去QQ粘贴搜索吧~',//弹窗的内容
        type: 'success',
      });
      //alert("已复制群号（"+input.value+"），去QQ粘贴搜索吧~");
    },
    QQFeedback: function () {
      let input = document.getElementById('qq_feedback');
      input.select(); // 选中文本
      document.execCommand('copy'); // 执行浏览器复制命令
      //alert("已复制QQ（"+input.value+"），去QQ粘贴搜索吧~");
      this.$toast({
        showCancel: false,
        t1: ':)',//弹窗的标题
        t2: '已复制个人账号(' + input.value + ')，去QQ粘贴搜索吧~',//弹窗的内容
        type: 'success',
      });
    },
  },
};
</script>

<style lang="less" scoped>
#about {
  border-top: 1px solid transparent;

  .content {
    padding: 1rem;

    p {
      line-height: 1.6rem;

      a {
        color: @c_blue;
      }
    }

    &.link {
      p {
        cursor: pointer;

        &:hover {
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

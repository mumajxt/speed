<template>
  <div class="logo">
    <span class="icon iconfont iconlogo3"></span>
    <span class="icon iconfont iconlogo2 zoom"></span>
    <span class="icon iconfont iconlogo1 rotate"></span>
  </div>
</template>

<style lang="less" scoped>
.logo {
  position: relative;
  display: inline-block;
  height: 2rem;
  width: 2rem;
  span {
    position: absolute;
    top: 0;
    left: 0;
    color: @c_yellow;
    height: 2rem;
    line-height: 2rem;
    font-size: 2rem;
  }
  .iconlogo2 {
    color: @c1;
    opacity: 0.9;
  }
  .iconlogo3 {
    opacity: 0.2;
  }
}


.rotate {
    animation: iRotate 0.2s ease-out infinite alternate;
    -moz-animation: iRotate 0.2s ease-out infinite alternate;	/* Firefox */
    -webkit-animation: iRotate 0.2s ease-out infinite alternate;	/* Safari 和 Chrome */
    -o-animation: iRotate 0.2s ease-out infinite alternate;
}

//旋转动画
@keyframes iRotate
{
    0% {transform: rotate(-5deg);}
    100% {transform:rotate(5deg);}
}

@-moz-keyframes iRotate /* Firefox */
{
    0% {transform: rotate(-5deg);}
    100% {transform:rotate(5deg);}
}

@-webkit-keyframes iRotate /* Safari 和 Chrome */
{
    0% {transform: rotate(-5deg);}
    100% {transform:rotate(5deg);}
}

@-o-keyframes iRotate /* Opera */
{
    0% {transform: rotate(-5deg);}
    100% {transform:rotate(5deg);}
}



//缩放
@keyframes iZoom
{
    0% {transform: scale(0.98);}
    100% {transform:scale(1.02);}
}

@-moz-keyframes iZoom /* Firefox */
{
    0% {transform: scale(0.98);}
    100% {transform:scale(1.02);}
}

@-webkit-keyframes iZoom /* Safari 和 Chrome */
{
    0% {transform: scale(0.98);}
    100% {transform:scale(1.02);}
}

@-o-keyframes iZoom /* Opera */
{
    0% {transform: scale(0.98);}
    100% {transform:scale(1.02);}
}

.zoom {
    animation: iZoom 0.8s ease-out infinite alternate;
    -moz-animation: iZoom 0.8s ease-out infinite alternate;	/* Firefox */
    -webkit-animation: iZoom 0.8s ease-out infinite alternate;	/* Safari 和 Chrome */
    -o-animation: iZoom 0.8s ease-out infinite alternate;
}
</style>

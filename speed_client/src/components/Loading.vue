<template>
<div class="wrapper">
  <i class="iconfont iconloading rotate"></i>
  <slot></slot>
</div>
</template>

<style lang="less" scoped>
.wrapper{
  display: inline-flex;
  align-items: center;
  height: 1rem;
}
i{
  display: inline-block;
  height: 1rem;
  width: 1rem;
  line-height: 1rem;
  text-align: center;
}


.rotate {
    animation: iRotate 1s linear infinite;
    -moz-animation: iRotate 1s linear infinite;	/* Firefox */
    -webkit-animation: iRotate 1s linear infinite;	/* Safari 和 Chrome */
    -o-animation: iRotate 1s linear infinite;
}
//旋转动画
@keyframes iRotate
{
    0% {transform: rotate(0deg);}
    100% {transform:rotate(360deg);}
}

@-moz-keyframes iRotate /* Firefox */
{
    0% {transform: rotate(0deg);}
    100% {transform:rotate(360deg);}
}

@-webkit-keyframes iRotate /* Safari 和 Chrome */
{
    0% {transform: rotate(0deg);}
    100% {transform:rotate(360deg);}
}

@-o-keyframes iRotate /* Opera */
{
    0% {transform: rotate(0deg);}
    100% {transform:rotate(360deg);}
}
</style>

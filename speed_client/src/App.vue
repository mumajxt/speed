<template>
	<div id="app">
		<router-view/>
		<div id="footer" class="center">
			<!-- <p class="author"> -->
				<p>Designed by @OYaJi</p>
				<a href="http://www.miitbeian.gov.cn">赣ICP备2021004624号</a>
			<!-- </p> -->
			
		</div>
	</div>
</template>

<style lang="less">
	@import "./assets/less/main.less";
	#footer {
		//height: 3rem;
		//line-height: 3rem;
		padding: 1rem 0;
		font-size: 0.8rem;
		text-align: center;
		
		p,a{
			color: @c4;
		}
		a{
			text-decoration: underline;
			&:hover{
				color: @c_blue;
			}
		}
	}
</style>

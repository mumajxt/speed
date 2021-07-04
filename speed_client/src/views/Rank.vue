<template>
	<div id="rank">
		<div id="header" class="shadow">
			<div class="center">
				<div class="nav">
					<router-link to="/rank/home" class="logo">
						<Logo></Logo>
						<h1 class="title">国服<span class="primary">榜</span></h1>
					</router-link>
					<div class=links>
						<router-link class="link" to="/rank/donate" active-class="active">捐助榜</router-link>
						<router-link class="link" to="/rank/about" active-class="active">关于</router-link>
					</div>
				</div>
			</div>
		</div>
		<div id="container" class="center">
			<router-view/>
			<!-- <keep-alive>
				<router-view v-if="$route.params.keepAlive"/>
			</keep-alive>
			<router-view v-if="!$route.params.keepAlive"/> -->
		</div>

		


	</div>
</template>

<script>
import Logo from '@/components/Logo';
export default {
    name: "Rank",
	components:{
		Logo
	},
	mounted(){
		
	},
	data(){
		return {
			
		}
	},
	methods:{
	}
};
</script>

<style lang="less" scoped>

#rank {
	width: 100%;
	#header {
		position: fixed;
		top: 0;
		left: 0;
		z-index: 999;
		height: 4rem;
		width: 100%;
		background-color: rgba(255, 255, 255, 0.9);
		backdrop-filter: blur(0.5rem);

		.nav {
			display: flex;
			align-items: center;
			height: 4rem;
			padding: 0 1rem;
			.logo {
				display: flex;
				align-items: center;
				.icon {
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
				h1 {
					color: @c1;
					margin-left: 0.4rem;
					.primary {
						color: @c_yellow;
					}
				}
			}


			.links {
				margin-left: 0.5rem;
				flex: 1;
				.link {
					font-size: 1.2rem;
					padding: 0 0.5rem;
					color: @c2;
					&:hover {
						color: @c1;
					}
				}
				.active {
					font-weight: bold;
					color: @c1;
				}
			}

		}
	}

	#container {
		margin-top: 4rem;
	}

	

	
	
}
</style>


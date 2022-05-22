<template>
	<div id="app" class="container">
		<div class="row">
			<div class="col-lg-12">
				<h1>NotebookSPA</h1>
				<h4>{ username = '{{this.$store.state.user.username}}',
					id={{this.$store.state.user.id}} }</h4>
			</div>
		</div>

		<div class="row">
			<nav class="nav col-lg-12">
				<router-link to="/user_signup">首页</router-link>
				<router-link to="/item_add">记账</router-link>
				<router-link :to="{name:'item_list'}">列表</router-link>
			</nav>
		</div>

		<div class="row">
			<div id="placeholder" class="col-lg-12">
				<router-view></router-view>
			</div>			
		</div>
		<!-- 路由出口 -->
		<!-- 路由匹配到的组件将渲染在这里 -->
		
		<div class="row">
			<div class="footer">
				<p>All Rights Reserved 2020-2021 &copy Justin</p>
			</div>
		</div>		
	</div>
</template>
<script>
	export default {
		name: 'app',
	}
</script>

<style scoped>
	#app {
		font-family: 'Avenir', Helvetica, Arial, sans-serif;
		-webkit-font-smoothing: antialiased;
		-moz-osx-font-smoothing: grayscale;
		text-align: center;
		color: #2c3e50;
		margin-top: 10px;
	}

	#placeholder {
		min-height: 300px;
	}

	.nav a {
		border: 1px solid #337ab7;
		display: inline-block;
		width: 120px;
		padding: 10px 20px;
		margin: 0 20px;
		text-decoration: none;
	}

	.router-link-active {
		background-color: #337ab7;
		color: #fff;
	}
</style>

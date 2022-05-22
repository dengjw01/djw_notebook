<template>
	<div class="TheSignup">
		<h2>首页-用户登录</h2>
		<div class="form-inline" v-show="this.$store.state.user.id==0">
			<div class="alert-danger" v-show="user.id == -1">
				<p>用户名或密码错误！</p>
			</div>
			<div>
				<label>用 户</label>
				<input type="text" v-model="user.username" class="form-control" />
			</div>
			<div>
				<label>密 码</label>
				<input type="password" v-model="user.password" class="form-control" />
			</div>
			<div>
				<!-- <h4>{{user.id}}:{{user.username}}/{{user.password}}</h4> -->
			</div>
			<div><button v-on:click="Signin" class="btn btn-primary"> 登 录 </button></div>
		</div>
		<div v-show="this.$store.state.user.id>0">
			<h4>{ username = '{{this.$store.state.user.username}}',
				id={{this.$store.state.user.id}} } 用户登录成功！</h4>
				<div><button v-on:click="Signout" class="btn btn-danger">退出登录</button></div>
		</div>
	</div>
</template>

<script>
	export default {
		data: function() {
			return {
				user: {
					id: 0,
					username: "",
					password: "",
				}
			}
		},
		methods: {
			Signout:function(){				
				this.user.id = 0;
				this.user.username = "";
				this.user.password = "";
				this.$store.commit("setUser", this.user);
			},
			Signin: function() {
				var self = this;
				this.$axios.get('servlet/UserSigninServlet', {
						params: self.user
					})
					.then(function(response) {
						console.log(response);
						if (response.data > 0) {
							self.user.id = response.data;
							self.$store.commit("setUser", self.user);
							//alert("Signup Ok!");
							self.$router.push({
								path: "/item_add",
								params: {
									userid: response.data,
								},
							});
						} else {
							self.user.id = response.data;
							//alert("username or password is not correct!")
						}
					})
					.catch(function(error) {
						if (error.request) {
							alert(error.request);
						}
						alert(error);
						console.log(error.message);
						console.log(error.config);
					});
				/*
				$.ajax({
					url: "http://localhost:8080/NotebookSPA0/servlet/UserSigninServlet",
					data: self.user,
					dataType: "text",
					success: function(res) {
						alert(res);
						if (res > 0) {
							self.$router.push({
								path: "/item_list",
								params: {
									userid: data
								},
							});
						}
					},
					error: function() {
						alert("network error!");
					}
				});
				*/
			}
		}
	}
</script>

<style scoped>
	div.TheSignup div {
		margin: 20px auto;
	}
.alert-danger p{
	height:60px;
	line-height: 60px;
	font-weight: bolder;
}
	label {
		margin-right: 20px;
	}
</style>

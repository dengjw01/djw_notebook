<template>
	<div>
		<h2>Item List</h2>
		<table id="table1" class="table table-hover">
			<tr>
				<td>序</td>
				<td>支出项目</td>
				<td>分类</td>
				<td>金额</td>
				<td>日期</td>
				<td>操作</td>
			</tr>
			<tr v-for="(item,index) in items" v-bind:key="item.id">
				<td>{{index}}</td>
				<td>{{item.item_text}}</td>
				<td>{{item.category_text}}</td>
				<td>{{item.amount}}</td>
				<td>{{item.date_paid}}</td>
				<td><button class='btn btn-info' v-on:click="item_update(item.id)">修改</button>&nbsp;&nbsp;
					<button class="btn btn-danger" v-on:click="item_del(item.id)">删除</button></td>

			</tr>
		</table>
		<div v-show="isshow" class="tab-pane">
			<h3>修改</h3>
			<div>
				<label>开支项目</label>
				<input type="text" v-model="updated.item_text" class="form-control" />
			</div>
			<div>
				<label>消费金额</label>
				<input type="text" v-model="updated.amount" class="form-control" />
			</div>
			<div>
				<label>消费日期</label>
				<input type="date" v-model="updated.date_paid" class="form-control" value='2020-01-01' />
			</div>
			<button class="btn btn-primary btn-block" v-on:click="update()">修改</button>
		</div>
		<h3>修改、删除之后要点到其他页面，再回来才会刷新</h3>
	</div>
</template>

<script>
	export default {
		data: function() {
			return {
				isshow: false,
				items: [],
				updated: {
					updateid:"",
					item_text: "",
					amount: 0,
					date_paid: "2020-1-1",
				},
			}
		},
		mounted: function() {
			var self = this;
			// react to route changes...
			// don't forget to call next()
			this.$axios.get('servlet/ItemListServlet', {
					params: {
						userid: self.$store.state.user.id,
					}
				})
				.then(function(response) {
					console.log(response);
					self.items = response.data;
				})
				.catch(function(error) {
					alert(error);
				});
		},
		beforeRouteUpdate: function(to, from, next) {
			var self = this;
			// react to route changes...
			// don't forget to call next()
			this.$axios.get('servlet/ItemListServlet', {
					params: {
						userid: self.$store.state.user.id,
					}
				})
				.then(function(response) {
					console.log(resposne);
					self.items = response.data;
				})
				.catch(function(error) {
					alert(error);
				});
			next();
		},
		methods: {
			item_del: function(id) {
				var del = {
					del_id: id
				}
				var self = this;
				this.$axios.get('servlet/ItemDeleteServlet', {
						params: del
					})
					.then(function(response) {
						console.log(response);
						if (response.data > 0) {
							alert("Delete Ok!");
							self.$router.push({
								path: "/item_list",
								params: {
									userid: self.$store.state.user.id,
								},
							});
						} else {
							alert("Delete not ok!")
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
			},
			item_update: function(id) {
				var self = this;
				alert("update item, id = " + id);
				this.updated.updateid = id;
				self.isshow = !this.isshow;

			},
			update: function() {
				var self = this;
				var item_id = this.updated.updateid;
				alert("update item, id = " + item_id);
				var item_text = this.updated.item_text;
				var amount = this.updated.amount;
				var date_paid = this.updated.date_paid;
				this.$axios.get('servlet/ItemUpdateServlet', {
						params: {
							item_id,
							item_text,
							amount,
							date_paid,
						}
					})
					.then(function(response) {
						console.log(response);
						if (response.data > 0) {
							alert("update Ok!");
							self.$router.push({
								path: "/item_list",
								params: {
									userid: self.$store.state.user.id,
								},
							});
						} else {
							alert("update not ok!")
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
			},

		}
	}
</script>

<style>
</style>

<template>
	<div>
		<h2>Item Add</h2>
		<div class="form-inline">
					<div class="alert-danger">
						
					</div>
					<div>
						<label>开支项目</label>
						<input type="text" v-model="item.item_text"  class="form-control" />
					</div>
					<div>
						<label>开支分类</label>
						<select class="form-control" v-model="item.category_id">
							<option value="0" disabled>请选择开支分类</option>
							<option v-for="(category,index) in categories"
												v-bind:key="category.id" v-bind:value="category.id">{{category.category_text}}</option>
						</select>
					</div>
					<div>
						<label>消费金额</label>
						<input type="text" v-model="item.amount" class="form-control" />
					</div>
					<div>
						<label>消费日期</label>
						<input type="date" v-model="item.date_paid" class="form-control" value='2020-01-01' />
					</div>
					<div>
						<button v-on:click="item_add" class="btn btn-primary">新增</button>
					</div>
				</div>
	</div>
</template>

<script>
	export default {
		data: function() {
			return {
				item: {
					id: 0,
					item_text: "",
					amount: 0,
					date_paid: "2020-1-1",
					category_id: 0,
					user_id: 0,
				},
				categories: [{
					id: 1,
					category_text: "学习"
				}, {
					id: 2,
					category_text: "购物"
				}, {
					id: 3,
					category_text: "就餐"
				}],
			}
		},
		methods: {
			item_add: function() {
					var self = this;
					var user_id = self.$store.state.user.id;
					var item_text = this.item.item_text;
					var category_id = this.item.category_id;
					var amount = this.item.amount;
					var date_paid = this.item.date_paid;
					this.$axios.get('servlet/ItemAddServlet', {
							params: {
								user_id,
								item_text,
								amount,
								date_paid,
								category_id,
							}
					})
				.then(function(response) {
					console.log(response);
					if (response.data >0) {
						alert("Add Ok!");
						self.$router.push({
							path: "/item_list",
							params: {
								userid: user_id,
							},
						});
					} else {
						alert("Add not ok!")
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

		}
	}
	}
</script>

<style scoped>
	.form-inline div{
		margin: 20px 0;
	}
	.form-inline .form-control{
		width:200px;
		margin: 0 20px;
	}
</style>

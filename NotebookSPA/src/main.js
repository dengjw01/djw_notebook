import Vue from 'vue'
import Vuex from 'vuex'
import VueRouter from 'vue-router'
import axios from 'axios'
import App from './App.vue'
import TheSignup from './pages/TheSignup.vue'
import TheItemAdd from './pages/TheItemAdd.vue'
import TheItemList from './pages/TheItemList.vue'
import './assets/css/bootstrap.min.css'
import './assets/js/jquery-3.3.1.js'
import './assets/js/bootstrap.min.js'
Vue.config.productionTip = false;
//模块化代码中，需要使用vuerouter
Vue.use(VueRouter);
axios.defaults.baseURL = 'http://127.0.0.1:8080/NotebookSPA0/';
Vue.prototype.$axios = axios
// 2. 定义路由
// 每个路由应该映射一个组件。 其中"component" 可以是
// 通过 Vue.extend() 创建的组件构造器，
// 或者，只是一个组件配置对象。
// 我们晚点再讨论嵌套路由。
const routes = [{
		path: '/',
		redirect: '/user_signup'
	},
	{
		path: '/user_signup',
		component: TheSignup,
		meta:{title:"用户注册"},
	},
	{
		name:"item_list",
		path: '/item_list',
		component: TheItemList,
		meta:{title:"记账列表"},
	},
	{
		path: '/item_add',
		component: TheItemAdd,
		meta:{title:"新增记账"},
	}
];

// 3. 创建 router 实例，然后传 `routes` 配置
// 你还可以传别的配置参数, 不过先这么简单着吧。
const router = new VueRouter({
	mode: 'history',
	routes:routes, // (缩写) 相当于 routes: routes
});

router.beforeEach((to,from,next)=>{
	if(to.meta.title){
		document.title = to.meta.title;
	}
	next();
});

var vm = new Vue({
	data:{},
	methods:{},
	computed:{},
});

Vue.use(Vuex);
const store = new Vuex.Store({
  state: {
	user:{
		id:0,
		username:"未登录",
	}
  },
  getters:{
	  getUser:function(){
		return state.user.id + "-" +state.user.username; 
	  },
  },
  mutations: {    
	setUser:function(state,user){
		state.user.id = user.id;
		state.user.username = user.username;
	},	
  }
})
//Vue.prototype.$store = store;
// 4. 创建和挂载根实例。
// 记得要通过 router 配置参数注入路由，
// 从而让整个应用都有路由功能
new Vue({
	router:router,
	store:store,
	render: h => h(App),
}).$mount('#app')

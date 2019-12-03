
var userService = {
    register(fn){
        axios
            .post('/api/auth/signup')
            .then(response => fn(response))
            .catch(error =>console.log(error));
    },
    login(user,fn) {
        axios
            .post('/api/auth/signin',user)
            .then(response => {fn(response)})
            .catch(error =>console.log(error));
    }
};

var Login = Vue.extend({
    template: '#login-user',
    data: function () {
        console.log("asdfasdfasdf");
        return{
            user:{username: '', password: ''}
        }
    },
    methods:{
        userLogin() {console.log("HACIIIIIITTTTTTT");
            userService.login(this.user, r => router.push('/'+this.user.username))
        }
    }
});

var router = new VueRouter({
    routes: [
        {path: '/login', component: Login}

    ]
});

new Vue({
    router
}).$mount('#app')
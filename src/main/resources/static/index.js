var users = [];
var posts = [];

function findPost(postId) {
    return posts[findPostKey(postId)];
}

function findPostKey(postId) {
    for (var key = 0; key < posts.length; key++){
        if (posts[key].id == postId){
            return key;
        }
    }
}

var postService = {
    findAll(fn) {
        axios
            .get('/post')
            .then(response => fn(response))
    .catch(error =>{console.log(error);
    console.log("BURADAYIM")} )
    },

    login(user,fn) {
        axios
            .post("/api/auth/signin",user)
            .then(response => fn(response))
            .catch(error => console.log(response))
    }
};

var List = Vue.extend({
    template: '#post-list',
    data: function () {
        console.log("HACIIIII HACI");
        return{posts:[]}
    },
    mounted() {
        console.log(this.posts);
        postService.findAll(r => {this.posts = r.data; posts = r.data})
        console.log(this.posts)
    }
});

var Login = Vue.extend({
    template: '#login-template',
    data: function () {
    return{
        user: {username: '', password: ''}
    }
    },
    methods: {
        loginUser(){
            postService.login(this.user,r=>router.push('/'+this.user.username))
        }
    }
})

var router = new VueRouter({
    routes: [
        {path: '/', component: List},
        {path: '/:username', component: Login, name: 'login-user'}
    ]
});


new Vue({
    router
}).$mount('#app')
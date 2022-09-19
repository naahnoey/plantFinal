<template>
    <div>
        <div class="sidebar">
            <ul>
                <router-link v-for="list in lists" :key="list.key" :to="list.link" tag="li" class="" class-active="active"
                >{{list.title}}</router-link>
            </ul>
        </div>
    </div>

</template>
<script>
    
    export default {
     data () {
     return{
    
      lists: [
        {link: "", title: "회원 정보"},
        {link: '/user/orders', title: "주문 관리"},
        {link: '/user/address', title: "배송지 관리"},
      ]
     }
     },
    
    methods:{
     
     },
    mounted() {
        var token = window.localStorage.getItem("user");
        var tokenJson = JSON.parse(token);
        this.username = tokenJson.username;
        this.lists[0].link = `/user/detail/${this.username}`;
        console.log(this.username);  
        }
    }
</script>

<style scoped>
    .sidebar {
    margin-top: 60px;
}
 .sidebar ul{
    
    list-style : none;
    background-color: rgb(224, 224, 224);
    width: 150px;
    padding: 0;
    margin: 0;
    position: fixed;
    overflow: auto;
    text-align: center;
}
.sidebar ul li {
    text-decoration: none;
    display: block;
    color: black;
    padding: 8px 15px 8px 15px;
    font-weight: bold;
    font-size: 14px;
    cursor: pointer;
}
.router-link-active{
    background-color: rgb(22, 160, 133);
    color: white;
}
.sidebar ul li:hover{
    background-color: rgb(22, 160, 133);
    color: white;
}
</style>
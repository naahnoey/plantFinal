<template>

<div>
        <div class="sidebar">
            <ul>
                <router-link v-for="list in lists" :key="list.key" :to="list.link" tag="li" class-active="active"
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
        {link: '/admin/user', title: "회원관리"},
        {link : '/admin/addProduct', title: "상품등록"},
        {link: '/admin/ProductManage', title: "상품관리"},
        {link: '/admin/orders', title: "주문관리"},
        {link: '/admin/notice', title: "공지사항관리"},
        {link: '/admin/faq', title: "FAQ관리"},
        {link: '/admin/question', title: "문의사항관리"},
        {link: '/admin/review', title: "리뷰관리"},
        {link: '/6', title: "매출관리"},
      ]
     }
     },
    
    methods:{
     
     }
    }
    </script>
    <style scoped>
    .sidebar{
          margin-top: 50px;
    }
    .sidebar ul {
    
    list-style : none;
    background-color: rgb(22, 160, 133);
    width: 150px;
    padding: 0;
    margin: 0;
    position: fixed;
    height: 340px;
    overflow: auto;

}

.sidebar ul li {
    text-decoration: none;
    display: block;
    color: white;
    padding: 8px 15px 8px 15px;
    font-weight: bold;
    font-size: 14px;
    cursor: pointer;
}

.router-link-active {
    background-color: rgb(241, 196, 15);
    color: white;
}
    </style>
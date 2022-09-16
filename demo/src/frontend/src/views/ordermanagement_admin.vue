<template>
    <section>
        <side-menu></side-menu>
        <div style="margin-left: 200px; margin-top: 50px; padding:1px 16px; width:1500px; height:1000px;">
            <table class="table text-center" id="order-manager">
                <thead style="background-color: rgb(224, 224, 224);">
                  <tr>
                    <th scope="col">주문번호</th>
                    <th scope="col">상품번호</th>
                    <th scope="col">이미지</th>
                    <th scope="col">상품명</th>
                    <th scope="col">주문수량</th>
                    <th scope="col">결제금액</th>
                    <th scope="col">구매자</th>
                    <th scope="col">주문상태</th>
                    <th scope="col">주문상태변경</th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="(order, index) in orders" :key="index">
                    <td>{{order.oid}}</td>
                    <td>{{order.pid}}</td>
                    <td><img :src="order.pimg1" class="image1"></td>
                    <td>{{order.pname}}</td>
                    <td>{{order.ocount}}</td>
                    <td>{{order.ototal}}원</td>
                    <td>{{order.username}}</td>
                    <td>{{order.ostatus}}</td>
                    <td>       
                        <!-- <select v-model="ostatus">
                            <option :value="ostatus" v-for="(ostatus, index) in list" :key="index" >{{ostatus}}</option> -->
                        <!-- <select id="select" @change="changeValue()"> -->
                        <!-- <select id="select" v-model="order.ostatus"> -->
                        <!-- <select id="select"> -->
                        <!-- <option value="결제완료">결제완료</option>
                        <option value="배송중">배송중</option>
                        <option value="배송완료">배송완료</option>
                        <option value="구매확정">구매확정</option>
                        </select> -->
                        <router-link :to="`/admin/updateoStatus/${order.oid}`" tag="button" class="btn1">변경</router-link>
                    </td>
                  </tr>
                </tbody>
              </table>
        </div>
    </section>
</template>

<script>
import sidemenuAdmin from '../views/sidemenu_admin';
import OrdersDataService from '../services/OrdersDataService';
export default {
    name:"order-management-admin",
    data(){
        return{
            orders:[],
            currentOrder: {
                oid: null,
                oname: "",
                opost: "",
                oaddress: "",
                ophone: "",
                ocount: "",
                opayment: "",
                odate: null,
                pname: "",
                pprice:"",
                pstock:"",
                pimg1:"",
                ototal:"",
                ostatus:""
            },
            currentIndex:-1,
            
            // ostatus:"결제완료",
            // list:[
            //     '결제완료',
            //     '배송중',
            //     '배송완료',
            //     '구매확정'
            //     ],
            // selecte:{
            //     ostatus:""
            // }
        }
    },
    components:{
        'side-menu': sidemenuAdmin
    },
    methods:{
        ordersList(){
            OrdersDataService.getAll_admin()
            .then(response=>{
                this.orders = response.data;
                console.log(response.data);
            }).catch(e=>{
                console.log(e);
            })
        },
        // changeostatus(){
        //     var newOrder = {
        //         ocount: this.currentOrder.ocount,
        //         pname: this.currentOrder.pname,
        //         pprice:this.currentOrder.pprice,
        //         pimg1:this.currentOrder.pimg1,
        //         ototal:this.currentOrder.ototal,
        //         ostatus:this.currentOrder.ostatus,
        //         pid:this.currentOrder.pid
        //     }
        //     OrdersDataService.updateostatus(this.currentOrder.oid, newOrder)
        //     .then(response => {
        //         console(response.data);
        //     })
        // },
        // changeValue(){
        //     var value_str = document.getElementById('select');
        //     console.log(value_str.value);
        //     //this.order.ostatus = value_str.value;
        // }
    },
    mounted(){
        this.ordersList();
        
    }
}
</script>

<style scoped>
#logo {
    text-align: center;
}

#hd_right {
    margin-right: 200px;
}


#side_list ul {

    list-style : none;
    background-color: rgb(22, 160, 133);
    width: 150px;
    padding: 0;
    margin: 0;
    position: fixed;
    height: 260px;
    overflow: auto;

}

#side_list ul li a {
    text-decoration: none;
    display: block;
    color: white;
    padding: 8px 15px 8px 15px;
    font-weight: bold;
    font-size: 14px;
}

#side_list ul li a.side_active {
    background-color: rgb(241, 196, 15);
    color: white;
}

#search {
    position: relative;
    width: 300px;
    margin-top: 15px;
    margin-left: 1160px;
}

#search_img {
    position : absolute;
    width: 17px;
    top: 10px;
    right: 12px;
    margin: 0;
}

input {
    width: 100%;
    border: 1px solid #bbb;
    border-radius: 8px;
    padding: 10px 12px;
    font-size: 14px;
}

.image1{
    width: 60px;
    height: 60px;
}

.btn1{
    width: auto;
    height: auto;
    font-size: 15px;
    background-color: rgb(224, 224, 224);
    color: black;
    border: none;
    border-radius: 5px;
    text-decoration: none;
}

#order-manager tr td {
    vertical-align: middle;
}
</style>
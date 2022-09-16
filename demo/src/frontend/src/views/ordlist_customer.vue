<template>
<section>
    <side-menu></side-menu>
        <div id="ordlist-main">
            <h3>주문 목록</h3>
            <table v-for="(order, index) in orders" :key="index">
                <tbody>
                    <tr>
                        <th><span>{{index+1}}.&nbsp;[주문번호]&nbsp;{{order.oid}}&nbsp;[주문일]&nbsp;{{order.odate}}</span></th>
                        <th ><span style="margin-left:190px">주문상태</span></th>
                    </tr>
                    <tr>
                        <td id="ord-detail">
                                <!-- <img :src="`http://localhost:8090/api/product/files/${order.pimg1}`"> -->
                                <!-- <img :src="require(`../assets/${imgName}.png`)"/> -->
                                <img :src="order.pimg1">
                                <p>
                                    [상품명]&nbsp;{{order.pname}}<br>
                                    [제품가격]&nbsp;{{order.pprice.replaceAll(',','원,')}}원<br>
                                    [주문수량]&nbsp;{{order.ocount}}<br>
                                    [총 결제금액]&nbsp;{{order.ototal}}원<br>
                                </p> 
                        </td>
                        <td id="del-detail">
                            <p>
                                <!-- {{order.ostatus}} -->
                                <button id="btn2" style="color:white; background-color: rgb(22, 160, 133);">{{order.ostatus}}</button>
                                <!-- <button id="btn2">배송중</button>
                                <button id="btn2">배송완료</button>
                                <button id="btn2">구매확정</button> -->
                            </p>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div> 
    </section>
</template>

<script>

import OrdersDataService from '@/services/OrdersDataService';
import sideMenu from '../views/sidemenu_customer.vue';
export default {
    name: "order-list",
    data(){
        return {
            orders:[
                
            ],
            currentOrder: {
                oid: null,
                oname: "",
                opost: "",
                oaddress: "",
                ophone: "",
                ocount: "",
                opayment: "",
                odate: null,
                pid: null,
                pname: "",
                pprice:"",
                pstock:"",
                pimg1:"",
                ototal:""
            },
            currentIndex: -1,
            userid: null,
            i:""
        }
    },
    components: {
        'side-menu': sideMenu,
    },
    methods: {
        orderList(){
        var idToken = window.localStorage.getItem("user");
        var jsonTokenpar = JSON.parse(idToken);
        this.userid = jsonTokenpar.username;
        OrdersDataService.getAll(this.userid)
        .then(response => {
            this.orders = response.data
            console.log(response.data);
            // var i = this.orders[0].pid;
            // console.log(i)
            
        }).catch(()=>{}); 
        },
        // orderstatus(){
        //     console.log(this.orders[0].pid);
        // }
    },
    mounted(){
        this.orderList();
    }
}

</script>

<style scoped>
#ordlist-main {
    width: 800px;
    margin: auto;
    margin-bottom: 300px;
    margin-left: 550px;
}

#ordlist-main h3 {
    font-size: 24px;
    color: #545454;
    margin-top:60px;
    margin-bottom: 20px;
}

/* 테이블 설정 */
#ordlist-main table {
    width: 100%;
    margin: auto;
    border-collapse: collapse;
    font-size: 14px;
    border-top: 2px solid lightgray;
    border-bottom: 2px solid lightgray;
}

#ordlist-main tbody tr {    /* 구분선 */
    border-top: 1px solid lightgray;
}

#ordlist-main tbody tr th span {    /* 이미지와 상세정보 사이 여백 */
    margin-left: 15px;
}

#ordlist-main tbody th {
    text-align: left;
    height: 30px;
}
/* 셀 가로 크기 설정 */
#ordlist-main tbody th:nth-child(1) {   /* 주문 상세정보 */
    width: 45%;
}
#ordlist-main tbody th:nth-child(2) {   /* 주문상태 */
    width: 55%;
}

#ord-detail p {
    display: inline-block;
    vertical-align: middle;
    text-align: left;
    margin-left: 15px;
}

#ord-detail  img {  /* 상품 이미지 크기 */
    width: 100px;
    height: 100px;
}

#del-detail p {
    text-align: center;
    white-space: nowrap;
}

#del-detail button {
    width: 75px;
    height: 70px;
    font-size: 12px;
    margin: 10px;
    border: 0;
    border-radius: 10px;
}
</style>
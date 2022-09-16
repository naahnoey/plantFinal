<template>
  <div>
        <div class="address">
            <h3><strong>주문 상태 변경</strong></h3>
            <hr>
            <table>
                <tbody>
                    <tr>
                        <th>주문번호</th>
                        <td><input style="width:100%" type="text" v-model="currentOrder.oid" disabled></td>
                    </tr>
                    <tr>
                        <th>상품번호</th>
                        <td><input type="text" v-model="currentOrder.pid" disabled></td>
                    </tr>
                    <tr>
                        <th>상품이미지</th>
                        <td><img :src="currentOrder.pimg1" class="image1"></td>
                    </tr>
                    <tr>
                        <th>상품명</th>
                        <td><input type="text" v-model="currentOrder.pname" disabled></td>
                    </tr>
                    <tr>
                        <th>주문수량</th>
                        <td><input type="text" v-model="currentOrder.ocount" disabled></td>
                    </tr>
                    <tr>
                        <th>결제금액</th>
                        <td><input type="text" v-model="currentOrder.ototal" disabled></td>
                    </tr>
                    <tr>
                        <th>구매자</th>
                        <td><input type="text" v-model="currentOrder.username" disabled></td>
                    </tr>
                    <tr>
                        <th>주문 상태</th>
                        <td><select v-model="currentOrder.ostatus" class="form-control w-50">
                                <option disabled value="">주문상태</option>
                                <option>결제완료</option>
                                <option>배송중</option>
                                <option>배송완료</option>
                                <option>구매확정</option>
                            </select></td>
                    </tr>  
                </tbody>
            </table>
            <br><br>
            <button style="margin-left: 90px;" class="btn2" type="button" @click="updateostatus">주문 상태 변경</button>&nbsp;
           
            <button class="btn1" type="button" @click="backtoList"> 돌아가기 </button>
        </div>
  </div>
</template>
<script>
import OrdersDataService from '../services/OrdersDataService';
export default {
    name:"UpdateOstatus",
    data(){
        return{
            currentOrder: {
                oid: null,
                pid: null,
                pname: "",
                pprice: "",
                pimg1: "",
                ostatus:"",
                username: null,
                oname: "",
                opost: "",
                oaddress: "",
                ophone: "",
                ocount: "",
                opayment: "",
                odate: null,
                pstock:"",
                ototal:""
            }
        }
    },
    methods:{
        getOrder(oid) {
            OrdersDataService.update(oid)
            .then(response => {
                this.currentOrder = response.data;
            }).catch(e => console.log(e))

        },
        updateostatus(){
            var orderDate = {
                ostatus:this.currentOrder.ostatus,
                pid:this.currentOrder.pid,
                pimg1:this.currentOrder.pimg1,
                ototal:this.currentOrder.ototal,
                pname:this.currentOrder.pname,
                pprice:this.currentOrder.pprice
            };
            OrdersDataService.updateostatus(this.currentOrder.oid, orderDate)
            .then(response =>{
                console.log(response.data);
                alert("주문 상태가 수정되었습니다.");
                this.$router.go(-1);
            })
        },
        backtoList(){
                this.$router.push({name: 'orders-admin'});
        }
    },
    mounted(){
            this.getOrder(this.$route.params.oid);
        }
}
</script>
<style scoped>


.address{
    width: 530px;
    margin-left: auto;
    margin-right: auto;
}


.address h3{
    font-size: 24px;
    color: #545454;
    margin-top:60px;
    margin-bottom: 20px;
}

.btn2{
    background-color: rgb(22,160,133); 
    border: none; 
    color: white; 
    border-radius: 5px; 
    margin-left: 80px; 
    width: 150px; 
    height: 40px;
}

.side{
    text-decoration: none;
    list-style: none;
}

.btn1{
    background-color: gray; 
    border: none; 
    color: white; 
    border-radius: 5px; 
    margin-left: 80px; 
    width: 150px; 
    height: 40px;
}

.findbtn{
    font-size: 15px;
    background-color: rgb(224, 224, 224);
    color: black;
    border: none;
    border-radius: 5px;
    text-decoration: none;
}

.address table tbody tr th{
    background-color: whitesmoke;
    font-size: 15px;
    font-family: '굴림';
}

.address table tbody tr td input{
    height: 30px;
    width: 400px;
}

.address table{
    border-collapse: collapse;
}

.address table tbody tr td{
    border-bottom: 1px solid rgb(224, 224, 224);
    padding: 10px;
}

.address table tbody tr th{
    border-bottom: 1px solid rgb(224, 224, 224);
    padding: 10px;
}

.image1{
    width: 60px;
    height: 60px;
}
</style>
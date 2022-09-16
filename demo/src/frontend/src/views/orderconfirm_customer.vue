<template>
    <div id="body">
        <h4>주문 상품</h4>
        <hr style="width: 540px;">
        <table style="border: 1px solid black; text-align: center; margin-bottom: 80px;">
            <thead style="background-color: rgb(224,224,224)">
                <th style="border: 1px solid black; width: 40px;"> 번호 </th>
                <th style="border: 1px solid black;"> 제품 이미지 </th>
                <th style="border: 1px solid black; width: 150px;"> 제품명 </th>
                <th style="border: 1px solid black; width: 80px;"> 가격 </th>
                <th style="border: 1px solid black; width: 40px;"> 수량 </th>
                <th style="border: 1px solid black; width: 80px;"> 금액 </th>
            </thead>
            <tbody v-for="(cart, index) in cartList" :key="index">
                <tr >
                    <td style="border: 1px solid black;">{{index+1}}</td>
                    <td style="border: 1px solid black;"><img
              :src="cart.product.pimg1"
              style="width: 140px; height: 140px; float: left"
          /></td>
                    <td style="border: 1px solid black;">{{cart.product.pname}}</td>
                    <td style="border: 1px solid black;">{{cart.product.pprice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')}} 원</td>
                    <td style="border: 1px solid black;">{{cart.pquantity}}</td>
                    <td style="border: 1px solid black;">{{(cart.product.pprice * cart.pquantity).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')}} 원</td>
                </tr>
            </tbody>
        </table>
      
        <h4> 배송지 </h4>
        <hr style="width: 540px;">
        <table style="font-size: 12px; margin-bottom: 80px;">
            <thead>
                <th >
                    배송지를 선택하세요
                </th>
                <th style=""><select style="width: 130px; margin-top: 100px; justify-content: center; margin-top: 0px;" v-model="selectedAddress" >
                    <option disabled selected="selected"> 배송지 선택</option>
                    <option v-for="(address, index) in addressList" :key="index" :value="address">{{address.aname}}</option>
                    <option >직접 입력</option>
                 </select></th>
            </thead>
            <tbody style="border: 1px solid black;" >
                
                <tr style="border: 1px solid black;">
                    <th style="border: 1px solid black; background-color: rgb(224,224,224);">수령인명</th>
                    <td><input type="text" v-model="selectedAddress.areceipt" placeholder="수령인 성함을 입력하세요" style="width: 300px;"></td>
                </tr>
                
                <tr style="border: 1px solid black;">
                    <th style="border: 1px solid black; background-color: rgb(224,224,224);">휴대전화번호</th>
                    <td><input type="text" v-model="selectedAddress.amobile" placeholder="'-' 를 포함하여 연락처를 입력하세요" style="width: 300px;"></td>
                </tr>
                <tr style="border: 1px solid black;">
                    <th style="border-bottom: none; border: 1px solid black; background-color: rgb(224,224,224);">주소</th>
                    <td style="border-bottom: none;"><input type="text" v-model="selectedAddress.apostcode" style="width: 80px;" readonly>&nbsp;<button class="findbtn" type="button" style="font-size: 9px; width: 100px;height: 20px; background-color: rgb(22,160,133); border: none; border-radius: 8px; color: white; font-weight: bold;" @click="showApi">우편번호찾기</button><br>
                        <input type="text" v-model="selectedAddress.aaddress1" style="margin-top: 3px; width: 300px;" readonly placeholder="우편번호찾기를 먼저 이용해주세요"><br> 
                        <input type="text" v-model="selectedAddress.aaddress2" style="margin-top: 3px; width: 300px;" placeholder="상세주소를 입력하세요"></td>
                </tr>
            </tbody>
        </table>
        
        <h4> 주문요약 </h4>
        <hr style="width: 540px;">
        <table>
            <tbody>
                <tr>
                    <th>총 수량</th>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;{{totalQty}}&nbsp;&nbsp;(개/세트)</td>
                </tr>

                <tr>
                    <th>총 결제금액</th>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;{{total.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')}} &nbsp;&nbsp;원</td>
                </tr>
            </tbody>
        </table>
        <hr style="width: 540px;">
            <div style="margin-top: 100px; margin-bottom: 40px;">
                <button type="button" style="background-color: rgb(22,160,133); border: none; color: white; border-radius: 5px; margin-left: 80px; width: 150px; height: 40px;" @click="addOrder">주문하기</button>
                <button type="button" style="background-color: rgb(22,160,133); border: none; color: white; border-radius: 5px; margin-left: 100px; width: 150px; height: 40px;" @click="back">취소하기</button>
            </div>    
    </div>
</template>

<script>
    import CartService from '../services/cart.service';
    import AddressService from '../services/address.service';
    import OrdersDataService from '../services/OrdersDataService';
    export default{
        name: 'order-confirm',
        data(){
            return {
                addressList: [],
                cartList: [],
                currentCart:{
                    cid: null,
                    product: {
                        pid: null,
                        pname: "",
                        ptype: "",
                        pprice: 0,
                        pimg1: ""
                    },
                    pquantity: 0
                },
                selectedAddress: {
                    aid: null,
                    aname: "",
                    areceipt: "", 
                    amobile: "",
                    apostcode: "",
                    aaddress1: "",
                    aaddress2: ""
                },
                
                
                index: -1,
                
                currentIndex: -1,
                username: null,
                total: 0,
                totalQty: 0,
                totalpname: "",
                imgurl:"",
                totalpid:"",
                totalpprice:""
                
            }
        },
        methods: {
            back(){
                this.$router.go(-1);
            },  
            getaddress(){
                var idToken = window.localStorage.getItem("user");
                var jsonTokenpar = JSON.parse(idToken);
                this.username = jsonTokenpar.username;
                AddressService.getAll(this.username)
                .then(response => {
                    this.addressList = response.data
                    console.log(response.data);
                }).catch(e => {
                    console.log(e);
                })
            },
            getCartList() {

                var idToken = window.localStorage.getItem("user");
                var jsonIdToken = JSON.parse(idToken);
                this.username = jsonIdToken.username;

                CartService.getAll(this.username)
                .then((response) => {
                    this.cartList = response.data;
                    console.log(response.data);
                    console.log(response.data.length);
                    var total = 0;
                    var totalQty = 0;
                    var totalpname = "";
                    var pid = "";
                    var pprice = "";

                    for (let i = 0; i < response.data.length; i++){
                    total = total + (response.data[i].pquantity * response.data[i].product.pprice);
                    console.log(response.data[i].pquantity); 
                    console.log(response.data[i].product.pprice);
                    }
                    this.imgurl = response.data[0].product.pimg1;
                    this.total = total;
                    for (let i = 0; i < response.data.length; i++){
                    totalQty = totalQty + response.data[i].pquantity;
                    console.log(response.data[i].pquantity); 
                    }
                    console.log(totalQty);
                    this.totalQty = totalQty;
                    for (let i = 0; i < response.data.length; i++){
                    totalpname = totalpname + "," + response.data[i].product.pname;
                    }
                    totalpname = totalpname.replace(',', '');
                    this.totalpname = totalpname;

                    for (let i = 0; i < response.data.length; i++){
                    pid = pid + ","+ response.data[i].product.pid;
                    console.log(pid); 
                    }
                    // console.log(pid.substr(1));
                    this.totalpid = pid.substr(1);

                    for (let i = 0; i < response.data.length; i++){
                    pprice = pprice + ","+ response.data[i].product.pprice;
                    console.log(pprice); 
                    }
                    var pprice1 = pprice.substr(1);
                    //var pprice2 = pprice1.toFixed(10);
                    console.log(pprice1);
                    this.totalpprice = pprice.substr(1);
                    
                })
                .catch((e) => {
                    console.log(e);
                });
                },
                addOrder(){
                    var idToken = window.localStorage.getItem("user");
                    var jsonTokenpar = JSON.parse(idToken);
                    this.username = jsonTokenpar.username;
                    var orderData = { 
                        pid: this.totalpid,
                        pname: this.totalpname,
                        pprice: this.totalpprice,
                        ocount: this.total,
                        pimg1:this.imgurl,
                        ototal:this.totalQty,
                        username: this.username          
                    }
                    OrdersDataService.create(orderData)
                    .then(response =>{
                    this.products = response.data;
                    console.log(response.data);
                    this.products.pid = response.data.pid;
                    this.submitted = true;
                    alert("구매가 완료되었습니다.");
                    this.$router.push({name: 'orders-list'});
                }).catch(e=>console.log(e));

                },
           
            msgbox(){
                console.log(this.selectedAddress)
            },
            showApi() {
      new window.daum.Postcode({
        oncomplete: (data) => {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            let fullRoadAddr = data.roadAddress; // 도로명 주소 변수
            let extraRoadAddr = ''; // 도로명 조합형 주소 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
              extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }
            // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
            if(fullRoadAddr !== ''){
                fullRoadAddr += extraRoadAddr;
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            this.selectedAddress.apostcode = data.zonecode; //5자리 새우편번호 사용
            this.selectedAddress.aaddress1 = fullRoadAddr;
        }
      }).open()
    },
        },
        mounted(){
            this.getaddress();
            this.getCartList();
        }
    }
</script>

<style scoped>
#body {
    margin-top: 50px;
    margin-left: 700px;
}
input {
    border-radius: 0px;
    border-top: none;
    border-left: none;
    border-right: none;
}
</style>
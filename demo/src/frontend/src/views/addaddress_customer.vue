<template>
<div>
    <side-menu></side-menu>
     <div class="address" style="margin-bottom: 100px;">
            <h3>배송지 등록</h3>
            <hr style="margin-bottom: 20px;">
            <table>
                <tbody>
                    <tr>
                        <th>배송지명</th>
                        <td><input type="text" v-model="address.aname" placeholder="배송지 이름을 입력하세요 ex) 우리집, 사무실"></td>
                    </tr>
                    <tr>
                        <th>수령인명</th>
                        <td><input type="text" v-model="address.areceipt" placeholder="수령인 성함을 입력하세요"></td>
                    </tr>
                    
                    <tr>
                        <th>휴대전화번호</th>
                        <td><input type="text" v-model="address.amobile" placeholder="'-' 를 포함하여 연락처를 입력하세요"></td>
                    </tr>
                    <tr>
                        <th style="border-bottom: none;">주소</th>
                        <td style="border-bottom: none;"><input type="text" v-model="address.apostcode" style="width: 80px;" readonly>&nbsp;<button class="findbtn" type="button" style="width: 120px;height: 50px;" @click="showApi">우편번호찾기</button><br>
                            <input type="text" v-model="address.aaddress1" style="margin-top: 3px;width: 400px;" readonly placeholder="주소를 입력하기 위해서 우편번호찾기를 먼저 이용해주세요"><br>
                            <input type="text" v-model="address.aaddress2" style="margin-top: 3px;width: 400px;" placeholder="상세주소를 입력하세요"></td>
                    </tr>
                </tbody>
            </table>
            <br><br>

            <button style="margin-left: 170px;" class="btn" type="button" @click="addAddress" >배송지 등록하기</button>&nbsp;
            <button class="btn1" type="button" @click="backtoList">등록 취소하기</button>
    </div>
    
</div>
</template>

<script>
    import sideMenu from '../views/sidemenu_customer.vue';
    import AddressService from '../services/address.service.js';

    export default {
        name: "address-add",
        data(){
            return{
                userid: null,
                address: {
                    aid: null,
                    aname: "",
                    areceipt: "",
                    amobile: "",
                    apostcode: "",
                    aaddress1: "",
                    aaddress2: "",
                    username: null
                    
                },
               
            }
        },
        components: {
            'side-menu': sideMenu
        }, 
        
        methods: {
            checkUsername(){
                var idToken = window.localStorage.getItem("user");
                var jsonTokenpar = JSON.parse(idToken);
                this.address.username = jsonTokenpar.username;
                this.userid = jsonTokenpar.username;
           
           
                console.log(jsonTokenpar.username);
            },
            addAddress(){
               
                var idToken = window.localStorage.getItem("user");
                var jsonTokenpar = JSON.parse(idToken);
                this.address.username = jsonTokenpar.username;
                var addressData = {
                    aname: this.address.aname,
                    areceipt: this.address.areceipt,
                    amobile: this.address.amobile,
                    apostcode: this.address.apostcode,
                    aaddress1: this.address.aaddress1,
                    aaddress2: this.address.aaddress2,
                    username: this.address.username
                };
             

                AddressService.create(addressData)
                .then(response => {
                    this.address = response.data;
                    console.log(response.data);
                    alert("배송지가 등록되었습니다.");  
                    this.$router.push({name: 'address-list' });
                }).catch(e => {
                    console.log(e);
                })
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
            this.address.apostcode = data.zonecode; //5자리 새우편번호 사용
            this.address.aaddress1 = fullRoadAddr;
        }
      }).open()
    },
    backtoList(){
        this.$router.go(-1);
    }

        },
        mounted(){
            this.checkUsername();
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

.btn{
    width: auto;
    height: auto;
    font-size: 15px;
    background-color: rgb(22, 160, 133);
    color: white;
    border: none;
    border-radius: 5px;
    text-decoration: none;
}

.side{
    text-decoration: none;
    list-style: none;
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

/* .address table tbody tr td input{
    height: 30px;
    width: 250px;
} */

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

/* 사이드바  */
.sidebar ul{
    list-style : none;
    background-color: rgb(224, 224, 224);
    width: 150px;
    padding: 0;
    margin: 0;
    position: fixed;
    overflow: auto;
    margin-left: 88px;
    text-align: center;
}

.sidebar ul li a {
    text-decoration: none;
    display: block;
    color: black;
    padding: 8px 15px 8px 15px;
    font-weight: bold;
    font-size: 14px;
}

.sidebar ul li a.side_active{
    background-color: rgb(22, 160, 133);
    color: white;
}

.sidebar ul li a:hover{
    background-color: rgb(22, 160, 133);
    color: white;
}

/* 사용자 메인 css 파일 */

#categori { /*categori의 ID를 가진 태그의 스타일 지정*/
    height: 40px; /* categori의 높이를 30px로 설정(제대로 설정 안하면 아래 내용이 깨짐) */
    width: 2000px; /* categori의 넓이를 850px로 설정(제대로 설정 안하면 브라우져 넓이가 좁아지면 깨짐) */
}

#categori ul li {  /* categori의 ID를 가진 태그 안에 <ul> 태그 안에 <li> 태그의 스타일을 지정 */
    list-style: none; /* <li> 태그는 위의 이미지처럼 목록을 나타내는 점을 없앤다 */
    color : black; /* 글씨 색을 흰색으로 설정 */
    background-color: rgb(224, 224, 224); /* 배경색을 회색 RGB(224,224,224)으로 설정 */
    float: left; /* <li>태그들이 왼쪽에 흐르게 설정(그러면 아래있는 내용은 오른쪽으로 옴) */
    line-height: 40px; /* 글씨가 가운데로 오도록 설정하기 위해 한줄의 높이를 30px로 설정 */
    vertical-align: middle; /* 세로 정렬을 가운데로 설정(위의 line-height와 같이 설정 필요함) */
    text-align: center; /* 글씨 정렬을 가운데로 설정 */
}

#categori .menuLink { /* categori 아이디를 가진 태그 안에 있는 menuLink 클래스 태그들의 스타일 설정 */
    text-decoration: none; /* 링크(<a> 태그)가 가지는 기본 꾸밈 효과(밑줄 등)을 없앰 */
    color: black; /* 폰트색을 검정으로 설정  */
    display: block; /* 링크를 글씨가 있는 부분 뿐만아니라 전체 영역을 클릭해도 링크가 걸리게 설정 */
    width: 150px; /* 메뉴링크의 넓이 설정 */
    font-size: 14px; /* 폰트 사이즈 14px로 설정 */
    font-weight: bold; /* 폰트를 굵게 */
    font-family:"Trebuchet MS", Dotum, Arial; /* 기본 폰트 적용, 시스템 폰트를 종류별로 순서대로 */
}

#categori .menuLink:hover { /* categori 아이디를 가진 태그 안에 menuLink클래스를 가진 태그에 마우스가 over되면 스타일 설정 */             
    color: rgb(22, 160, 133); /* 글씨 색을 rgb(22, 160, 133) 으로 설정 */                  
    background-color: rgb(224, 224, 224);  
}

#logo {
    text-align: center;
}

#hd_right {
    margin-right: 200px;
}

#hd_left {
    margin-top: 50px;
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

input, select {
    width: 100%;
    border: 1px solid #bbb;
    border-radius: 8px;
    padding: 10px 12px;
    font-size: 14px;
    height: 40px;
}
</style>

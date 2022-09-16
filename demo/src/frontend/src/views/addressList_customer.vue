<template>
<div>
    <side-menu></side-menu>
    <div id="address" class="address" style="margin-bottom: 100px;">
            <h3>배송지 목록</h3><hr>
            <router-link id="router-link" tag="button" to="/user/address/add" style="float: right; height: 40px; font-size: 14px;">배송지 신규등록하기</router-link>
            <table v-for="(address, index) in addresses" :key="index">
                <thead>
                    <th style="width: 170px;" ></th>
                    <th ></th>
                </thead>
                <tbody style="font-size: 12px;">
                    <tr style="height: 10px;">
                        <td style=" text-align: left; font-weight: bold; background-color: rgb(224,224,224)">[주소 이름]</td>
                        <td>{{address.aname}}</td>
                    </tr>
                    <tr>
                        <td style=" text-align: left; font-weight: bold; background-color: rgb(224,224,224)">[수령인]</td>
                        <td>{{address.areceipt}}</td>
                    </tr>
                    <tr>
                        <td style=" text-align: left; font-weight: bold; background-color: rgb(224,224,224)">[연락처]</td>
                        <td>{{address.amobile}}</td>
                    </tr>
                    <tr>
                        <td style=" text-align: left; font-weight: bold; background-color: rgb(224,224,224)">[우편번호]</td>
                        <td>{{address.apostcode}}</td>
                    </tr>
                    <tr>
                        <td style=" text-align: left; font-weight: bold; background-color: rgb(224,224,224)">[상세 주소]</td>
                        <td>{{address.aaddress1}}&nbsp;{{address.aaddress2}}</td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><button tag="button" id="router-link" @click="deleteAddress(address.aid)" style="float: right; width: 80px; vertical-align: middle; font-size: 14px; height: 30px; width: 80px;">삭제하기</button><router-link tag="button" id="router-link" :to="`/user/address/update/${address.aid}`" style="float: right; width: 80px; vertical-align: middle; font-size: 14px; height: 30px; width: 80px;">수정하기</router-link></td>
                    </tr>
                </tbody>
            </table>
             
            <!-- <router-link class="btn" style="float: right;">배송지 신규등록하기</router-link>
            <hr>
            <div class="shippingAddress_list">
                <div style="font-size: 15px;">
                    [주소 이름] 우리집<br>
                    [수령인명] 홍길동<a href="changeaddress_customer.html"><input type="button" value="수정하기" class="btn" style="float: right;"></a><br>
                    [주소] 서울시 강남구<br>
                    [우편번호] 12345<br>
                    [연락처] 010-1234-1234<a href="#"><input type="button" value="삭제하기" class="btn" style="float: right;"></a><br>
                    [기타]
                </div>
                <hr style="margin-bottom: 20px;">
                <br><br>
                <hr>
                <div style="font-size: 15px;">
                    [주소 이름] 사무실<br>
                    [수령인명] 홍길동<a href="changeaddress_customer.html"><input type="button" value="수정하기" class="btn" style="float: right;"></a><br>
                    [주소] 서울시 송파구<br>
                    [우편번호] 12345<br>
                    [연락처] 010-1234-1234<a href="#"><input type="button" value="삭제하기" class="btn" style="float: right;"></a><br>
                    [기타]
                </div>
                <hr style="margin-bottom: 20px;">
            </div> -->
        </div>

</div>
</template>

<script>
import sideMenu from '../views/sidemenu_customer.vue';
import AddressService from '../services/address.service';

export default {
    name: 'address-list',
    data(){
        return {
            addresses: [

            ],
            index: -1,
            currentAddress: {
                aid: null,
                aname: "",
                areceipt: "",
                amobile: "",
                apostcode: "",
                aaddress1: "",
                aaddress2: ""
            },
            currentIndex: -1,
            userid: null
            
            
        }
    },
    components: {
        'side-menu': sideMenu
    },
    methods: {
       addressList(){
        var idToken = window.localStorage.getItem("user");
        var jsonTokenpar = JSON.parse(idToken);
        this.userid = jsonTokenpar.username;
        AddressService.getAll(this.userid)
        .then(response => {
            this.addresses = response.data
            console.log(response.data);
        }).catch(e => {
            console.log(e);
        })
       },
       deleteAddress(aid){
        var confirm = window.confirm("정말 삭제하시겠습니까?");
        this.currentAddress.aid = aid;
        if(confirm){
        AddressService.delete(this.currentAddress.aid)
        .then(response => {
            console.log(response.data);
            alert("배송지가 삭제되었습니다.");
            this.$router.go(0);
        }).catch(e => {
            console.log(e);
        })
        } else {
            this.$router.go(0);
        }
    }
      
    },
    mounted(){
        this.addressList();
    }
}

</script>

<style scoped>
table {
    width: 795px;
    border: lightgray solid 1px;
    margin-top: 50px;
   
   
}
    .address{
    width: 795px;
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
    font-size: 13px;
    background-color: rgb(22, 160, 133);
    color: white;
    border: none;
    border-radius: 5px;
    text-decoration: none;
}
#router-link {
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
    font-size: 12px;
    background-color: rgb(224, 224, 224);
    color: black;
    border: none;
    border-radius: 5px;
    text-decoration: none;
}

.findbtn{
    font-size: 12px;
    background-color: rgb(224, 224, 224);
    color: black;
    border: none;
    border-radius: 5px;
    text-decoration: none;
}

.address table tbody tr th{
    
    font-size: 12px;
    font-family: '굴림';
}

.address table tbody tr td input{
    height: 30px;
    width: 250px;
}

.address table{
    border-collapse: collapse;
}

.address table tbody tr td{
   
    padding: 5px;
}

.address table tbody tr th{
   
    padding: 5px;
}

/* 사이드바  */


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

input {
    width: 100%;
    border: 1px solid #bbb;
    border-radius: 8px;
    padding: 10px 12px;
    font-size: 14px;
}
</style>

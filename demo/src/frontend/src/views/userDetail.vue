<template>
<div>
        <side-menu></side-menu>
       <div class="root">
        <div id="title">
        <h3>회원 정보</h3><hr>
         </div>
        <div class="inputTable">
            
            <table style="margin-bottom: 300px;">
                <thead>
                    
                </thead>
                <tbody>
                <tr>
                    <td> 아이디 </td> &nbsp;&nbsp;|&nbsp;&nbsp; <input type="text" disabled class="idInput" v-model="currentUser.username" style="width: 200px;"> 
                        
                </tr>
                <tr>
                    <td> 이메일 </td> <td> &nbsp;&nbsp;|&nbsp;&nbsp; <input type="text" disabled class="emailInput" v-model="currentUser.email" style="width: 200px;"> </td>
                </tr>
                </tbody>
            </table>
        </div>
        <br>

        </div>
    
</div>
    
</template>

<script>
    import sideMenu from '../views/sidemenu_customer.vue';
    import userService from '../services/userservice';
    export default {
        name: 'user-detail',
        data(){
            return {
                username: "",
                currentUser: {
                    id: null,
                    username: "",
                    email: "",
                },
            }
        },
        components: {
            'side-menu': sideMenu
        },
        methods: {
            getUser(username){
                
                userService.getbyUsername(username)
                .then(response => {
                    console.log(response.data);
                    this.currentUser = response.data;
                }).catch(e => {
                    console.log(e);
                })
            },
           
        },
        mounted(){
            var usernameToken = window.localStorage.getItem("user");
            var usernameTokenJson = JSON.parse(usernameToken);
            this.username = usernameTokenJson;

            this.getUser(this.$route.params.username);

        }
    }
</script>

<style scoped>

 
 .inputTable {
    
     top: 20px;
     margin-left: -2%;
 }
 #title {
    margin-left: 500px;
    margin-right: 500px;
 }
 
 .idInput {
     width: 55%; 
 }
 .idCheckButton {
     position: absolute;
     width: 120px;
     z-index: 5; 
     padding: 5; 
     height: 7%;
     margin-left: 4%;
     margin-top: 0%; 
     background-color: rgb(22, 160, 133); 
     border: 0px; 
     border-radius: 5px; 
     color: white; 
     font-weight: bold;
 }
 .nameInput {
     width: 100px; 
     
 }
 .passwordInput {
     width: 100px; 
 }
 
 .passwordCheckInput {
     width: 100px; 
 }
 .emailInput{
     width: 130px; 
 }
 .postcode {
     width: 60px; 
 }
 .postcodeCheck {
     position: absolute;
     z-index: 5; 
     padding: 5; 
     height: 30px;
     width: 120px;
     margin-left: 4%;
     margin-top: 1%; 
     background-color: rgb(22, 160, 133); 
     border: 0px; 
     border-radius: 5px; 
     color: white; 
     font-weight: bold;
 }

 .detailreviseBtn {
     width: 160px; 
     position: absolute; 
     left: 25%; 
     z-index: 5; 
     padding: 5; 
     height: 25px;
     margin-top: 130%; 
     background-color: rgb(22, 160, 133); 
     border: 0; 
     border-radius: 5px; 
     color: white; 
     font-weight: bold;      
     display: inline-block;         
 }
 .resignBtn {
     width: 160px; 
     position: absolute; 
     left: 25%; 
     z-index: 5;              
     padding: 5; 
     height: 25px;
     
     margin-top: 140%; 
     background-color: rgb(255, 255, 255); 
     border: 7; 
     border-radius: 5px;
     border-color:rgb(22, 160, 133);
     color: rgb(22, 160, 133); 
     font-weight: bold;   
 }
 table {
     border-collapse: separate;
     border-spacing: 0 17px;
     margin-left: 850px;
     
 }
 
 footer {
     margin-top: 1200px;
     text-align: left;
 }
 
 .title {
     margin-left: 15%;
     width: 64%;
 }
 .add1Input {
     width: 200px;
 }
 .add2Input {
     width: 200px;
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

input {
    width: 100%;
    border: 1px solid #bbb;
    border-radius: 8px;
    padding: 10px 12px;
    font-size: 14px;
}
</style>

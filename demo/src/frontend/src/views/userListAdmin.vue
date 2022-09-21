<template>
    <div>
        <side-menu></side-menu>
        <div id="notice-manager">
            <table class="table text-center">
                <thead >
                    <tr>
                        <th> 번호 </th>
                        <th> 아이디 </th>
                        <th> 이메일 </th>
                        <th> 구분 </th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(user, index) in userList" :key="index">
                        <td>{{index+1}}</td>
                        <td>{{user.username}}</td>
                        <td>{{user.email}}</td>
                        <td v-if="user.roles[0].name == 'ROLE_USER'">사용자</td>
                        <td v-if="user.roles[0].name == 'ROLE_ADMIN'" style="color: red; font-weight: bold">관리자**</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script>
    import userservice from '../services/userservice';
    import sideMenu from '../views/sidemenu_admin';

    export default {
        name: 'userList-admin',
        data() {
            return {
                userList: [],
                user: {
                    id: null,
                    username: null,
                    roles: {
                        id: null,
                        name: null,
                    },
                    email: null,


                }
            }
            
        },
        components: {
            'side-menu': sideMenu
                
        },  
        methods: {
            getUserList(){
                userservice.getAll()
                .then(response => {
                    this.userList = response.data;
                    console.log(response.data);
                    console.log(this.userList[0].roles);
                }).catch(e => {
                    console.log(e);
                })
            }
        },
        mounted(){
            this.getUserList();
            console.log(this.user.roles.name);
        }
    }
</script>

<style scoped>
#notice-manager {
    width: 1500px;
    min-height: 500px;
    margin: auto;
    text-decoration: none;
    margin-bottom: 100px;
    margin-left: 200px;
    padding:1px 16px;
}

#notice-search {    /* 검색칸 화면 가운데 배치 */
    
    width: 100px;
}
#notice-search input {
    width: 300px;
}

#notice-manager button {    /* 추가 버튼을 제외한 모든 버튼 */
    background-color: lightgray;
}

#notice-add button {    /* 추가 버튼 */
    color: white;
    background-color: rgb(22, 160, 133);
    width: 70px;
    height: 40px;
}
#notice-add {
    float: right;
}

/* 테이블 설정 */
#notice-manager thead {
    height: 40px;
    background-color: rgb(224, 224, 224);
}
#notice-manager tbody tr td {
    height: 60px;
    vertical-align: middle;
}

/* 셀 가로 길이 지정 */
#notice-manager tbody tr td:nth-child(1) {  /* 번호 */
    width: 10%;
}
#notice-manager tbody tr td:nth-child(2) {  /* 아이디 */
    width: 25%;
}

#notice-manager tbody tr td:nth-child(3) {  /* 이메일 */
    width: 45%;
}
#notice-manager tbody tr td:nth-child(4) {  /* 구분 */
    width: 20%;
}

/* 페이지 */
#paging {
    text-align: center;
    margin-top: 70px;
}
#paging button {
    width: 30px;
    height: 30px;
    border-radius: 10px;
    border: 0;
    background-color: rgb(240, 240, 240);
}

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

.img-size{
    width: 60px;
    height: 60px;
}
.popup-view {
  opacity: 0;
  display: none;
  visibility: hidden;
}
.active{
  opacity: 1;
  display: block;
  visibility: visible;
}
</style>
<template>
    <div>
        <side-menu></side-menu>
        <table id="userList" style="margin-left: 550px; border: 1px solid black; text-align: center;">
            <thead style="border: 1px solid black; background-color: rgb(224,224,224)">
                <th style="width: 100px;"> 번호 </th>
                <th style="width: 180px;"> 아이디 </th>
                <th style="width: 300px;"> 이메일 </th>
                <th style="width: 180px;"> 구분 </th>
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
    
</style>
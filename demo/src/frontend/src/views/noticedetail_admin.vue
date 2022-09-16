<template>
<div>
    <side-menu></side-menu>
    <div id="notice-manager">
            
            <!-- 공지사항 상세 내역-->
            <table style="width: 1200px; margin-left: 100px;">
                <thead style="border-top: solid 1.5px black; border-bottom: 0.5px solid black;">
                    <tr>
                        <th style="width: 90%;">{{currentNotice.ntitle}}</th>
                        <th style="width: 10%;"></th>
                        
                    </tr>
                </thead>
                <tbody>
                    <tr>

                        <td style="font-weight: bold;text-align: right; border-bottom: solid 1px lightgray;">작성일<br>
                        수정일<br>
                        조회수</td>
                        <td style="text-align: right;; border-bottom: solid 1px lightgray;">{{currentNotice.nrdate}}<br>
                            {{currentNotice.nmodifydate}}<br>
                            {{currentNotice.nhit}}
                        </td>
                        
                    </tr>
                    <tr style="text-align: left; border-bottom: solid 1px lightgray;">
                        <td><pre>{{currentNotice.ncontent}}</pre>
                        </td>
                        
                    </tr>
                   
                </tbody>
            </table>
               <!-- 목록/수정/삭제 버튼 -->
               <p id="notice-add">
                <button class="btn" @click="backtolist">목록</button>
                <router-link :to="`/admin/notice/update/${currentNotice.nid}`" tag="button" class="btn" style="margin-left: 980px;">수정</router-link>
                <button class="btn" @click="deleteNotice">삭제</button>
            </p>

            
        </div>
</div>
</template>

<script>
import sidemenuAdmin from './sidemenu_admin.vue';
import NoticeDataService from "../services/notice.service"
export default {
     name: "noticedetail_admin",
    data(){
        return {
            currentNotice: {
                nid: null,
                ntitle: "",
                ncontent: "",
                
            }
            
        }
    },
    components: {
        'side-menu': sidemenuAdmin
    },
     methods: {
        getNotice(nid){
            NoticeDataService.get(nid)
            .then(response => {
            this.currentNotice = response.data;
            console.log(this.$route.params.nid);
            
            }).catch(e => {
                console.log(e);
            })
        },
        deleteNotice(){
            NoticeDataService.delete(this.currentNotice.nid)
            .then(response => {
                console.log(response.data);
                alert("공지사항을 삭제하였습니다.")
                this.$router.push({name:"notice-admin"})
                
            }).catch(e => {
                console.log(e);
            });
        },
        backtolist(){
            this.$router.go(-1);
        },
        
    },
    mounted(){
        this.getNotice(this.$route.params.nid);
    }
}

</script>

<style scoped>
    /* 공지사항(관리자) */
#notice-manager {
    width: 1500px;
    min-height: 500px;
    margin: auto;
    margin-top: 50px;
    margin-bottom: 300px;
    margin-left: 200px;
    padding:1px 16px;
}

#notice-search {    /* 검색칸 화면 가운데 배치 */
    margin-left: 500px;
}
#notice-search input {
    width: 300px;
}

#notice-manager button {    /* 추가 버튼을 제외한 모든 버튼 */
    background-color: lightgray;
}

#notice-add .btn {    /* 추가 버튼 */
    color: white;
    background-color: rgb(22, 160, 133);
    width: 70px;
    height: 40px;
}
#notice-add {
    float: left;
    margin-left: 100px;
    margin-top: 40px;
}

/* 테이블 설정 */
#notice-manager thead {
    height: 40px;
    background-color: rgb(224, 224, 224);
    text-align: left;
}
#notice-manager tbody tr td {
    height: 60px;
    vertical-align: middle;
}

/* 셀 가로 길이 지정 */
#notice-manager tbody tr td:nth-child(1) {  /* 구분 */
    width: 10%;
}
#notice-manager tbody tr td:nth-child(2) {  /* 제목 */
    width: 45%;
    text-align: left;
}
#notice-manager tbody tr td:nth-child(2) span {
    margin-left: 5%;
}
#notice-manager tbody tr td:nth-child(3) {  /* 기간 */
    width: 25%;
}
#notice-manager tbody tr td:nth-child(4) {  /* 수정, 삭제 */
    width: 20%;
}

/* 페이지 */
#paging {
    text-align: center;
    margin-top: 150px;
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

#order-manager tr td {
    vertical-align: middle;
}
</style>
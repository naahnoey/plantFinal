<template>
<div>
    <side-menu></side-menu>
    <div id="notice-manager" >
        
            <!-- 검색 -->
            <!-- <p id="notice-search" style="margin-top: 100px;" >
                <select style="border: solid rgb(224,224,224) 0.5px; margin-left: -400px;">
                    <option>제목</option>
                    <option>기간</option>
                </select>
                &nbsp;&nbsp;
                <input type="text" placeholder="검색어를 입력하세요">
                &nbsp;&nbsp;
                <router-link :to="`/chatbot`" class="btn">검색</router-link>
            </p> -->

            <!-- 추가 버튼 및 팝업 내용-->
            <p id="notice-add">
                <router-link :to="`/admin/faq/add`" tag="button" class="btn" >추가</router-link>
              
            </p>

            <!-- 공지사항 목록 -->
            <table class="table text-center">
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>제목</th>
                        <th>작성일</th>
                        <th>수정</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(faq, index) in faqs" :key="index" >
                        <td>{{index+1}}</td>
                        <td style="text-decoration: none;"><router-link :to="`/admin/faq/detail_admin/${faq.fid}`">{{faq.ftitle}} </router-link> </td>
                        <td>{{faq.wdate}}</td>
                        <td>
                            <router-link :to="`/admin/faq/update/${faq.fid}`" tag="button" class="btn" >수정</router-link>
                        </td>
                    </tr>
                   
                </tbody>
            </table>
            <!-- 페이지 기능 -->
            <!-- <p style="margin-left:550px">
            <button :disabled="pageNum === 0" @click="prevPage" class="btn">이전</button>
            <span class="page-count">{{ pageNum + 1 }} 페이지</span>
            <button :disabled="pageNum < 10" @click="nextPage" class="btn">다음</button>
            </p> -->
        </div>
        
</div>
</template>
<script>
import sidemenuAdmin from '../views/sidemenu_admin';
// import axios from 'axios';
import FaqDataService from '../services/FaqDataService';
export default {
  name: "faq-management-admin",
  data() {
    return {
            faqs: [],
            currentFaq:  {
                fid: null,
                ftitle: "",
                fcontent: "",
                wdate: null
            },
            currentIndex: -1,
            fid: null,
            pageNum: 0
            }
        },
components: {
            'side-menu': sidemenuAdmin,
            // 'pop-up': popUp
        },  
  prop:{
    listArray:{
        type: Array,
        required:true
    },
    pageSize:{
        type: Number,
        required: false,
        default:10
    }
  },
  methods: {
        listFaq(){
            FaqDataService.getAll()
            .then(response => {
                this.faqs = response.data;
                console.log(response.data);
            }).catch(()=>{}); 
            },
            deleteFaq(){
            FaqDataService.delete(this.faq.fid)
            .then(response => {
                console.log(response.data);
                alert("삭제되었습니다.")
                this.$router.push("/admin/faq").catch(()=>{}); 
            }).catch(()=>{}); 
            },
            nextPage(){
                this.pageNum += 1;
            },
            prevPage(){
                this.pageNum -= 1;
            }
        },
    computed:{
        //  pageCount () {
        //     let listLeng = this.listArray.length,
        //     listSize = this.pageSize,
        //     page = Math.floor(listLeng / listSize);
        //     if (listLeng % listSize > 0) page += 1;

        //     return page;
        //     },  
        paginatedData () {
            const start = this.pageNum * this.pageSize,
            end = start + this.pageSize;
            return this.listArray.slice(start, end);
            }
        },
    mounted(){
        this.listFaq();
    }
}
</script>
<style scoped>
    /* 공지사항(관리자) */
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
#notice-manager tbody tr td:nth-child(1) {  /* 구분 */
    width: 10%;
}
#notice-manager tbody tr td:nth-child(2) {  /* 제목 */
    width: 45%;
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

#order-manager tr td {
    vertical-align: middle;
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
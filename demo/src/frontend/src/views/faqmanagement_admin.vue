<template>
<div>
    <side-menu></side-menu>
    <div id="notice-manager" >
        
            <!-- 검색 -->
            <p id="notice-search" style="margin-top: 100px;" >
                <select style="border: solid rgb(224,224,224) 0.5px; margin-left: -400px;">
                    <option>제목</option>
                    <option>기간</option>
                </select>
                &nbsp;&nbsp;
                <input type="text" placeholder="검색어를 입력하세요">
                &nbsp;&nbsp;
                <router-link :to="`/chatbot`" class="btn">검색</router-link>
            </p>

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
                            <router-link :to="`/admin/faq/update/${faq.fid}`" class="btn" >수정</router-link>
                        </td>
                    </tr>
                   
                </tbody>
            </table><br><br>
            <!-- 페이지 기능 -->
            <p style="margin-left:550px">
            <button :disabled="pageNum === 0" @click="prevPage" class="btn">이전</button>
            <span class="page-count">{{ pageNum + 1 }} 페이지</span>
            <button :disabled="pageNum < 10" @click="nextPage" class="btn">다음</button>
            </p>
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
#notice-manager {
    width: 1500px;
    min-height: 500px;
    margin: auto; 
    margin-top: 50px;
    margin-bottom: 100px;
    margin-left: 200px;
    padding:1px 16px;
}

 #notice-search {    
    margin-left: 850px;
} 
#notice-search input {
    width: 300px;
}

.btn {    /* 추가 버튼을 제외한 모든 버튼 */
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
#notice-manager table {
    width: 100%;
    margin: auto;
    border-collapse: collapse;
    font-size: 14px;
    border-top: 2px solid lightgray;
    border-bottom: 1px solid lightgray;
    vertical-align: middle;
}

/* 셀 가로 길이 지정 */
#notice-manager thead tr th:nth-child(1) {  
    width: 10%;
}
#notice-manager thead tr th:nth-child(2) {  
    width: 45%;
    text-align: center;
}
#notice-manager tbody tr td:nth-child(2) span {
    margin-left: 5%;
}
#notice-manager thead tr th:nth-child(3) {  /* 기간 */
    width: 25%;
}
#notice-manager thead tr th:nth-child(4) {  /* 수정, 삭제 */
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
</style>
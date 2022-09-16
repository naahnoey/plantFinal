<template>
<div>
  <div class="submit-form">
    <section>
        <div id="faq-main">
            <h3 style="text-align: left;"><strong>FAQ</strong></h3>
            <br>
            
            
            <!-- 공지사항 목록 -->
            <table class="text-center">
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>제목</th>
                    </tr>
                </thead>
                <tbody >
                    <tr v-for="(faq, index) in faqs" :key="index">
                        <td> {{index+1}}</td>
                        <td>
                            <router-link :to="`/faq/detail/${faq.fid}`">{{faq.ftitle}} </router-link>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </section>
  </div>
</div>
</template>
<script>
import FaqDataService from '@/services/FaqDataService';

export default {
    name: "faqlist-customer",
    data(){ return{
            faqs: [],
            currentFaq: null,
            currentIndex: -1,
        }
    },
    components: {
        
    },
    methods: {
        listFaq(){
            FaqDataService.getAll()
            .then(response => {
                this.faqs = response.data;
                console.log(response.data);
            }).catch(()=>{}); 
        }
    },
    mounted(){
        this.listFaq();
    }
};
</script>
<style>
#faq-search {    /* 검색칸 화면 가운데 배치 */
    margin-left: 500px;
}
#faq-search input {
    width: 300px;
}
#faq-search button {
    background-color: lightgray;
}
/* faq(사용자) */
#faq-main {
    width: 1100px;
    margin: auto;
    margin-bottom: 300px;
    padding-top: 70px;
}

#faq-main table {
    width: 100%;
    margin: auto;
    border-collapse: collapse;
    font-size: 14px;
    border-top: 2px solid whitesmoke;
    border-bottom: 1px solid lightgray;
}
#faq-main thead {
    background-color: whitesmoke;
    height: 40px;
}
#faq-main tbody tr {
    height: 60px;
}
#faq-main tbody tr td {  /* 게시판 구분선 */
    border-top: 1px solid lightgray;
}

/* 셀 가로 길이 설정 */
#faq-main thead tr th:nth-child(1) { /* 번호 */
    width: 15%;
}
#faq-main thead tr th:nth-child(2) { /* 제목 */
    text-align: center;
    width: 85%;
    padding-top: 15px;
    padding-bottom: 15px;
}
#faq-main tbody tr td:nth-child(2) a {
    text-decoration: none;
    color: black;
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
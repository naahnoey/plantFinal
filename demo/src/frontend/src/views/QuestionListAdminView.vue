<template>
<div>
    <side-menu></side-menu>
    <div id="notice-manager">

        <!-- 공지사항 목록 -->
        
            <table class="table text-center">
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>구분</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>작성일</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(question, index) in questions" :key="index">
                        <td>{{ index+1 }}</td>
                        <td>{{ question.qtype }}</td>
                        <td>
                            <span>
                                <router-link :to="`/admin/question/detail/${question.qid}`"> {{question.qtitle}} </router-link>
                                <span class="badge badge-danger mgb-15" v-if="question.isanswered == '답변필요'">답변필요</span>
                                <span class="badge badge-primary mgb-15" v-if="question.isanswered == '답변완료'">답변완료</span>
                            </span>
                        </td>
                        <td>{{ question.mid }}</td>
                        <td>{{ question.qdate }}</td>
                    </tr>
                </tbody>
            </table>
        

        <!-- 페이지 기능 -->
        <!-- <p id="paging">
            <button style="color:white; background-color:rgb(22, 160, 133)">1</button>
            <button>2</button>
            <button>3</button>
            <button>4</button>
        </p> -->
    </div>
</div>
</template>

<script>
import QuestionDataService from '../services/QuestionDataService';
import sidemenuCustomer from '../views/sidemenu_admin.vue';

export default {
    name: 'question-list-admin',
    data() {
        return {
            questions: [],
            currentQuestion: null,
            currentIndex: -1,
            qtitle: ''
        };
    },
    methods: {
        retrieveQuestions() {
            QuestionDataService.getAll(0)
            .then(response => {
                this.questions = response.data;
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
        },
        refreshList() {
            this.retrieveQuestions();
            this.currentQuestion = null;
            this.currentIndex = -1;
        },
        setActiveQuestion(question, index) {
            this.currentQuestion = question;
            this.currentIndex = index;
        }
    },
    components: {
        'side-menu': sidemenuCustomer
    }, 
    mounted() {
        this.retrieveQuestions();
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
#notice-manager tbody tr td:nth-child(2) {  /* 구분 */
    width: 20%;
}
#notice-manager tbody tr td:nth-child(3) {  /* 제목 */
    width: 30%;
}
#notice-manager tbody tr td:nth-child(4) {  /* 작성자 */
    width: 20%;
}
#notice-manager tbody tr td:nth-child(5) {  /* 작성일 */
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
 /* @import "../css/bootstrap.min.css";
    @import "../css/main_customer.css";
    @import "../css/question_list_admin.css"; */
</style>
   


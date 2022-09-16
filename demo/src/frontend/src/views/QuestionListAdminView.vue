<template>
<div>
    <side-menu></side-menu>
    <div id="root" class="root">
        

        <label class="title">
            <h3>QnA</h3>
        </label>
        <hr>

        <!-- 공지사항 목록 -->
        <p class="table-p">
            <table class="Qna_table">
                <thead>
                    <th>번호</th>
                    <th>구분</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>작성일</th>
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
        </p>

        <!-- 페이지 기능 -->
        <p id="paging">
            <button style="color:white; background-color:rgb(22, 160, 133)">1</button>
            <button>2</button>
            <button>3</button>
            <button>4</button>
        </p>
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


<style scoped lang="css">
    @import "../css/bootstrap.min.css";
    @import "../css/main_customer.css";
    @import "../css/question_list_admin.css";
</style>
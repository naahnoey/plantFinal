<template>
<div>
    <side-menu></side-menu>
    <div id="root" class="root">
        <side-menu></side-menu>

        <label class="title">
            <h3>QnA</h3>
        </label>
        <hr>

        <div id="questionDetail">
            <h4>{{ currentQuestion.qtitle }}</h4>
            {{ currentQuestion.mid }}
            {{ currentQuestion.qdate }}
            <p class="qnaContent"> {{ currentQuestion.qcontent }} </p>
            <p id="answerBtn">
                <button v-if="currentQuestion.isanswered == '답변필요'" @click="registerAnswer(currentQuestion.qid)">답변 등록</button>
                <button v-if="currentQuestion.isanswered == '답변필요'" @click="deleteQuestion(currentQuestion.qid)">삭제</button>
            </p>

            <!-- 답변 내용 -->
            <div v-if="currentQuestion.isanswered == '답변완료'">
                <hr>
                <h4>{{ currentAnswer.atitle }}</h4>
                {{ currentAnswer.adate }}
                <p class="qnaContent"> {{ currentAnswer.acontent }} </p>
                <p id="answerBtn">
                    <button @click="deleteAnswer(currentAnswer.aid)">삭제</button>
                    <button @click="modifyAnswer(currentQuestion.qid)">수정</button>
                </p>
            </div>
        </div>
    </div>
</div>
</template>

<script>
import QuestionDataService from '../services/QuestionDataService';
import AnswerDataService from '../services/AnswerDataService';
import sidemenuCustomer from './sidemenu_admin.vue';

export default {
    name: 'question-detail-admin',
    data() {
        return {
            currentQuestion: null,
            currentAnswer: null,
            username: '',
            currentIndex: -1,
        };
    },
    methods: {
        getQuestion(qid) {
            QuestionDataService.get(qid)
            .then(response => {
                this.currentQuestion = response.data;
                console.log(response.data);
            }).catch(e => {
                console.log(e);
            });
            this.getAnswer(qid);
        },
        getAnswer(qid) {    // 문의글 답변
            AnswerDataService.get(qid)
            .then(response => {
                this.currentAnswer = response.data;
                console.log(response.data);
            }).catch(e => {
                console.log(e);
            })
        },
        registerAnswer(id) {    // 답변 등록
            this.$router.push({ name: 'answer-register', params: {qid: id} })
        },
        deleteQuestion(qid) {
            var confirm = window.confirm("정말 삭제하시겠습니까?")
            if(confirm){
                QuestionDataService.delete(qid)
                .then(response => {
                    console.log(response.data);
                    this.$router.push({ name: 'question-list-admin' });
                })
                .catch(e => {
                    console.log(e);
                });
            }
        },
        modifyAnswer(id) {
            this.$router.push({ name: 'answer-modify', params: {qid: id} })
        },
        deleteAnswer(aid) {
            var confirm = window.confirm("정말 삭제하시겠습니까?")
            if(confirm){
                AnswerDataService.delete(aid)
                .then(response => {
                    console.log(response.data);
                    this.$router.go();
                })
                .catch(e => {
                    console.log(e);
                });
            }
        }
    },
    components: {
        'side-menu': sidemenuCustomer
    }, 
    mounted() {
        this.getQuestion(this.$route.params.qid);

        var idToken = window.localStorage.getItem("user");
        var jsonTokenpar = JSON.parse(idToken);
        this.username = jsonTokenpar.username;
    }
}
</script>


<style scoped lang="css">
    @import "../css/bootstrap.min.css";
    @import "../css/main_customer.css";
    @import "../css/question_list_admin.css";
    @import "../css/question_detail_admin.css";
</style>
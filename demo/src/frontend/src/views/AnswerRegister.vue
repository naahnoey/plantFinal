<template>
    <div id="root" class="root">
        <div class="question-info">
            {{currentQuestion.qtitle}}
            <hr>
            {{currentQuestion.qcontent}}
        </div>
        <hr>
        <div id="product-inquiry">
            <table>
                <thead>
                    <th style="width: 100px;"> </th>
                    <th style="width: 500px;"> </th>
                </thead>
                <tbody class="form-group">
                    <tr>
                        <td>제목</td>
                        <td> <textarea rows="1" v-model="answer.atitle" class="form-control"></textarea> </td>
                    </tr>
                    <tr>
                        <td>내용</td>
                        <td> <textarea rows="15" v-model="answer.acontent" class="form-control" placeholder="답변을 입력하세요"></textarea> </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div id="description-button">
            <button type="button" id="createInquiry" class="inquiryButton" @click="saveAnswer">작성하기</button>
            <button type="button" id="cancelInquiry" class="inquiryButton" @click="closeAnswer">취소하기</button>
        </div>
    </div>
</template>

<script>
import QuestionDataService from '../services/QuestionDataService';
import AnswerDataService from '../services/AnswerDataService';

export default {
    name: 'product-question-customer',
    data() {
        return {
            currentQuestion: null,
            answer: {
                aid: null,
                atitle: '',
                acontent: ''
            },
            submitted: false
        };
    },
    methods: {
        getQuestion() {   // 문의글
            QuestionDataService.get(this.$route.params.qid)
            .then(response => {
                this.currentQuestion = response.data;
                this.answer.atitle = '[Re:] ' + response.data.qtitle;

                console.log(response.data);
            }).catch(e => {
                console.log(e);
            })
        },
        saveAnswer() {    // 답변 저장
            var data = {
                atitle: this.answer.atitle,
                acontent: this.answer.acontent
            };
            AnswerDataService.register(data, this.$route.params.qid)
            .then(response => {
                this.answer.aid = response.data.aid;
                console.log(response.data);
                this.submitted = true;

                alert("답변이 등록되었습니다.");

                this.$router.go(-1);
            })
            .catch(e => {
                console.log(e);
            });
        },
        closeAnswer() {
            this.$router.go(-1);
        }
    },
    mounted() {
        this.getQuestion();
    }
}
</script>

<style scoped lang="css">
    @import "../css/bootstrap.min.css";
    @import "../css/main_customer.css";
    @import "../css/product_question_customer.css";
</style>
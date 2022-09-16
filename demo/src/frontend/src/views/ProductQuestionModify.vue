<template>
    <div id="root" class="root">
        <div class="title"> 
            <h2> 상품문의 </h2>
        </div>

        <div class="product-info">
            <div class="product-pic">
                <img :src="currentProduct.pimg1">
            </div>
            <div class="product-detail">
                <br>
                <table>
                    <tr>
                        <td>상품명</td>
                        <td> : </td>
                        <td>{{ currentProduct.pname }}</td>
                    </tr>
                    <tr>
                        <td>카테고리</td>
                        <td> : </td>
                        <td>{{ currentProduct.ptype }}</td>
                    </tr>
                    <tr>
                        <td>가격</td>
                        <td> : </td>
                        <td>{{ currentProduct.pprice }}원</td>
                    </tr>
                </table>
            </div>
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
                        <td>분류</td>
                        <td>
                            <select v-model="question.qtype" class="form-control w-50">
                                <option disabled value="">카테고리</option>
                                <option>배송</option>
                                <option>교환/환불</option>
                                <option>재입고</option>
                                <option>상품 문의</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>제목</td>
                        <td> <textarea rows="1" v-model="question.qtitle" class="form-control" placeholder="제목을 입력하세요"></textarea> </td>
                    </tr>
                    <tr>
                        <td>내용</td>
                        <td> <textarea rows="15" v-model="question.qcontent" class="form-control" placeholder="문의사항을 입력하세요"></textarea> </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <hr>
        <div id="description-button">
            <p style="font-size: 15px;">상품문의 안내</p>
            <p id="inquiry_direction">상품문의는 재입고, 취급방법, 품종, 배송 등 상품에 대하여 담당자에게 문의하는 게시판입니다.<br>
            욕설, 비방, 거래 글, 분쟁 유발, 명예훼손, 허위사실 유포, 타 쇼핑몰 언급,광고성 등의 부적절한 게시글은 금지합니다.<br>
            </p>

            <button type="button" id="createInquiry" class="inquiryButton" @click="saveQuestion">작성하기</button>
            <button type="button" id="cancelInquiry" class="inquiryButton" @click="closeQuestion">취소하기</button>
        </div>
    </div>
</template>

<script>
import QuestionDataService from '../services/QuestionDataService';
import ProductDataService from '../services/ProductDataService';

export default {
    name: 'product-question-customer',
    data() {
        return {
            productId: null,
            currentProduct: null,
            question: {
                qid: null,
                qtype: '',
                qtitle: '',
                qcontent: ''
            },
            submitted: false
        };
    },
    methods: {
        getProduct(pid) {   // 상품 정보
            ProductDataService.getProduct(pid)
            .then(response => {
                this.currentProduct = response.data;
                console.log(response.data);
            }).catch(e => {
                console.log(e);
            })
        },
        getQuestion(qid) {
            QuestionDataService.get(qid)
            .then(response => {
                this.question.qtype = response.data.qtype;
                this.question.qtitle = response.data.qtitle;
                this.question.qcontent = response.data.qcontent;

                this.productId = response.data.pid;
                this.getProduct(this.productId);

                console.log(response.data);
            }).catch(e => {
                console.log(e);
            })
        },
        saveQuestion() {    // 문의글 저장
            var data = {
                qtype: this.question.qtype,
                qtitle: this.question.qtitle,
                qcontent: this.question.qcontent
            };
            QuestionDataService.modify(data, this.$route.params.qid)
            .then(response => {
                console.log(response.data);
                this.submitted = true;

                alert("문의사항이 수정되었습니다.");

                this.$router.push({ name: 'product-detail', params: {pid: this.productId} });
            })
            .catch(e => {
                console.log(e);
            });
        },
        closeQuestion() {
            this.$router.go(-1);
        }
    },
    mounted() {
        this.getQuestion(this.$route.params.qid);
    }
}
</script>

<style scoped lang="css">
    @import "../css/bootstrap.min.css";
    @import "../css/main_customer.css";
    @import "../css/product_question_customer.css";
</style>
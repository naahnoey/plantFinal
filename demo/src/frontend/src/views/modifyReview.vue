<template>
    <div id="root" class="root">
        <div class="title"> 
            <h2> 리뷰작성 </h2>
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
                        <td>제목</td>
                        <td> <textarea rows="1" v-model="review.rtitle" class="form-control" placeholder="제목을 입력하세요"></textarea> </td>
                    </tr>
                    <tr>
                        <td>내용</td>
                        <td> <textarea rows="15" v-model="review.rcontent" class="form-control" placeholder="리뷰 내용을 입력하세요"></textarea> </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <hr>
        <div id="description-button">
            <p style="font-size: 15px;">리뷰쓰기 안내</p>
            <p id="inquiry_direction">상품 리뷰는 구입하신 상품을 실제로 받아보신 후 후기 등을 작성해주시는 게시판입니다.<br>
                욕설, 비방, 거래 글, 분쟁 유발, 명예훼손, 허위사실 유포, 타 쇼핑몰 언급,광고성 등의 부적절한 게시글은 금지합니다.<br>
            </p>

            <button type="button" id="createInquiry" class="inquiryButton" @click="modifyReview">수정하기</button>
            <button type="button" id="cancelInquiry" class="inquiryButton" @click="closeQuestion">취소하기</button>
        </div>
    </div>
</template>

<script>

import ProductDataService from '../services/ProductDataService';
import reviewService from '../services/reviewservice';

export default {
    name: 'product-question-customer',
    data() {
        return {
            review: {
                rid: null,
                rtitle: "",
                rcontent: "",
                username: "",
                rdate: null,
                rmodifydate: null,
                
            },
            pid: null,
           
            currentProduct: {
                pid: null,
                pname: "",
                ptype: "",
                pimg1: "",
                pdetail: "",
            },
            submitted: false,
            username: "",
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
        getReview(rid){
            reviewService.getbyId(rid)
            .then(response => {
                this.review = response.data;
                console.log(response.data);
                this.pid = response.data.product.pid;
                this.getProduct(this.pid);
            }).catch(e => {
                console.log(e);
            })
        },
        modifyReview(){
            var idToken = window.localStorage.getItem("user");
            var jsonTokenpar = JSON.parse(idToken);
            var username = jsonTokenpar.username;
            var reviewData = {
                rtitle: this.review.rtitle,
                rcontent: this.review.rcontent,
                username: username,
                product: this.currentProduct,
                
            }
            reviewService.modify(this.review.rid, reviewData)
            .then(response => {
                console.log(response.data);
                alert("리뷰가 수정되었습니다.");
                this.$router.go(-1);
            }).catch(e => {
                console.log(e);
            })
        },
        
        closeQuestion() {
            this.$router.go(-1);
        }
    },
    mounted() {
        this.getReview(this.$route.params.rid);

        var idToken = window.localStorage.getItem("user");
        var jsonTokenpar = JSON.parse(idToken);
        this.username = jsonTokenpar.username;
        
    }
}
</script>

<style scoped lang="css">
    @import "../css/bootstrap.min.css";
    @import "../css/main_customer.css";
    @import "../css/product_question_customer.css";
</style>
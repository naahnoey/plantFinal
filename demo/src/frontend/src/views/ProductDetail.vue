<template>
    <div>
           <div class="container container-mt">
                <div class="d-flex detail-mt-md">
                    <div class="repImgDiv">
    
                        <img :src="currentProduct.pimg1" class="img-size">
                    </div>
                    <div class="h4 product_name">{{currentProduct.pname}}</div>
            
                    <div class="h4 product_price" style="margin-top: 40px;">
                        <span>{{currentProduct.pprice}}</span>원
                    </div>
                   <div class="product_qunatity">
                        <span class="input-group-text" style="width:60px; height: 40px;">수량 &nbsp;&nbsp;
                        <input type="number" style="width: 150px;" name="count" id="count" v-model.number="cart.pquantity" class="form-control" value="1" min="1"> </span>
                    </div>
    
                </div>
    
    
                <div class="orderBox">
                    <div class="text-right mgt-50">
                        <h5>결제 금액</h5>
                        <h3 name="totalPrice" id="totalPrice" class="font-weight-bold" >{{ totalPrice }}</h3>
                    </div>
        
                    <div class="text-right">
                        <button type="button" class="btnCart" @click="putintoCart">장바구니 담기</button> &nbsp;
                        <button type="button" class="btnBuy" @click="toPayment">구매하기</button>
                    </div>

                </div>
            </div>
           
           <div>
                             <!-- Detail / review / QnA 탭 구현 시작-->
                <div id="root" class="root">
                    <div class="wrap">
                       
                        <div class="tab_menu">
                            <ul class="list">
                                <li class="is_on">
                                    <!-- Detail 버튼 화면 전환-->
                                    <button type="button" class="btn">Detail</button>
                                    <div id="tab1" class="cont">
                                        <!-- Detail 버튼 화면 구성 시작-->
                                       <div id="prdDetailContent"><br>
                                            <h1> Product Details </h1><br>
                                            {{ currentProduct.pname }} <br><br>
                                            <img :src="currentProduct.pimg1" class="img-size" ><br><br>
                                            <div v-html="parseDetail"></div>

                                            <br><br>
                                            <p style="background-color: rgb(190, 190, 190); color: white; font-weight: bold; width: 800px; margin-left: 150px;"> ※ 시장 상황에 따라서 식물의 크기가 다소 차이가 날 수 있으니 양해 부탁드립니다.  </p>
                                            <br><br>

                                            <h2> MORE INFO </h2><br>

                                            <p style="background-color: rgb(22, 160, 133); color: white; font-weight: bold; width: 800px; margin-left: 150px;"> 
                                                ※ 관심식물에 대한 더 많은 정보가 궁금하다면 트레플 챗봇한테 물어보세요.
                                            </p>

                                            
                                            <router-link to="/chatbot" style="font-weight: bold; margin-top: -30px;" class="nav-link">
                                                ▷ ▶ 챗봇상담 바로가기 ◀ ◁ 
                                            </router-link>
                                            
                                            <br><br>
                                       </div>
                                       <!-- Detail 버튼 화면 구성 끝-->
                                    </div>
                                </li>
                                <li>
                                    <!-- Review 버튼 화면 전환-->
                                    <button type="button" class="btn">Review</button>
                                    <div id="tab2" class="cont">
                                        <!-- Review 화면 구성 시작-->
                                        <div id="reviewContent">
                                            <h3>Review</h3> <hr><div>  <button type="button" id="writeReviewBtn" @click="addReview" >리뷰쓰기</button>
                                                </div>
                                                <div v-if="replyClicked == true" id="reviewDetail">
                                                    <h4> {{currentReview.rtitle}}</h4>
                                                    {{currentReview.username}}
                                                    <p v-if="currentReview.rmodifydate == null">{{currentReview.rdate}}</p>
                                                    <p v-if="currentReview.rmodifydate != null">{{currentReview.rmodifydate}}(수정)</p>
                                                    <p class="reviewContent"> {{ currentReview.rcontent}}</p>
                                                    <div style="margin-bottom: 20px;" id="reviewBtn">
                                                        <button @click="deleteReview" style="background-color: rgb(22,160,133); color: white; font-weight: bold; border:none; border-radius: 5px;">삭제</button>
                                                        <router-link :to="`/review/modify/${currentReview.rid}`" tag="button" style="background-color: rgb(22,160,133); color: white; font-weight: bold; border:none; border-radius: 5px;">수정</router-link>
                                                        
                                                    </div>

                                                    <div id="answerDiv" v-for ="(reviewReply, index) in reviewReplyList" :key="index">
                                                    <hr>
                                                    <h4>댓글</h4>
                                                    {{ reviewReply.rrdate }}
                                                    <p class="qnaContent"> {{ reviewReply.rrcontent }} </p>
                                                    <p id="answerBtn">
                                                        <button v-if="role == 'ROLE_ADMIN'" @click="deleteReply(reviewReply.rrid)">삭제</button>
                                                    </p>
                                                    </div>

                                                    <div id="reviewReply" v-if="role == 'ROLE_ADMIN'">
                                                        <textarea col="200" row="5" v-model="reviewReply.rrcontent" style="width: 850px;" placeholder="댓글을 입력하세요"></textarea><br>
                                                        <button type="button" @click="createReply" style="background-color: rgb(22,160,133); color: white; font-weight: bold; border:none; border-radius: 5px;">댓글 작성</button>
                                                    </div>
                                                </div>
                                                <br><br>
                                                <p v-if="reviewList.length == 0">
                                                작성된 리뷰가 없습니다.
                                                </p>
                                                <table id="reviewTable" v-if="reviewList.length > 0">
                                                    <thead>
                                                        <th style="width: 10%;"> 번호 </th>
                                                        <th style="width: 50%;"> 제목 </th>
                                                        <th style="width: 20%;"> 작성자 </th>
                                                        <th style="width: 20%;"> 등록/수정일 </th>
                                                    </thead>
                                                    <tbody>
                                                        <tr v-for="(review, index) in reviewList" :key="index">
                                                            <td>{{index+1}}</td>
                                                            <td><button id="reviewTitle" @click="getReview(review.rid)">{{review.rtitle}}</button></td>
                                                            <td>{{review.username}}</td>
                                                            <td v-if="review.rmodifydate == null">{{review.rdate}}</td>
                                                            <td v-if="review.rmodifydate != null">{{review.rmodifydate}} (수정)</td>
                                                        </tr>
                                                    </tbody>
                                                </table>

                                            
                                        </div>  
                                         
                                        <!-- Review 화면 구성 끝-->                      
                                    </div>
                                </li>
                                <!-- Q&A 버튼 화면 전환-->
                                <li>
                                    <button type="button" class="btn">Q&A</button>
                                    <div id="tab3" class="cont">
                                        <!-- Q&A 화면 구성 시작 -->
                                        <div id="qnaContent">
                                            <h3>FAQ/Q&A</h3> <hr>
                                            <button type="button" id="createqnaBtn" @click="addqna_customer()">문의하기</button>

                                            <!--faq상세 내용-->
                                            <div v-if="faqShow" id="questionDetail">
                                                <h4>{{ currentFaq.ftitle }}</h4>                                              
                                                <p class="qnaContent"><pre>{{ currentFaq.fcontent }}</pre></p>                                           
                                            </div>
                                            <!-- FAQ 목록 -->
                                            <div class="submit-form">
                                                <section>
                                                    <div id="faq-main">
                                                        <table class="text-center">
                                                            <thead>
                                                                <tr>
                                                                    <th>번호</th>
                                                                    <th>자주 묻는 질문</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody >
                                                                <tr v-for="(faq, index) in faqs" :key="index">
                                                                    <td> {{index+1}}</td>
                                                                    <td>
                                                                        <!-- <router-link :to="`/faq/detail/${faq.fid}`">{{faq.ftitle}} </router-link> -->
                                                                        <button id="questionTitle" @click="getFaq(faq.fid)">{{ faq.ftitle }}</button>
                                                                    </td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </section>
                                            </div>                  
                                            <!-- 문의글 상세 내용 -->
                                            <div v-if="questionShow" id="questionDetail">
                                                <h4>{{ currentQuestion.qtitle }}</h4>
                                                {{ currentQuestion.mid }}
                                                {{ currentQuestion.qdate }}
                                                <p class="qnaContent"> {{ currentQuestion.qcontent }} </p>
                                                <p id="answerBtn">
                                                    <button v-if="username == 'admin' && currentQuestion.isanswered == '답변필요'" @click="registerAnswer(currentQuestion.qid)">답변 등록</button>
                                                    <button v-if="username == currentQuestion.mid && currentQuestion.isanswered == '답변필요'" @click="deleteQuestion(currentQuestion.qid)">삭제</button>
                                                    <button v-if="username == currentQuestion.mid && currentQuestion.isanswered == '답변필요'" @click="modifyQuestion(currentQuestion.qid)">수정</button>
                                                </p>

                                                <!-- 답변 내용 -->
                                                <div id="answerDiv" v-if="currentQuestion.isanswered == '답변완료'">
                                                    <hr>
                                                    <h4>{{ currentAnswer.atitle }}</h4>
                                                    {{ currentAnswer.adate }}
                                                    <p class="qnaContent"> {{ currentAnswer.acontent }} </p>
                                                    <p id="answerBtn">
                                                        <button v-if="username == 'admin'" @click="deleteAnswer(currentAnswer.aid)">삭제</button>
                                                        <button v-if="username == 'admin'" @click="modifyAnswer(currentAnswer.qid)">수정</button>
                                                    </p>
                                                </div>
                                            </div>

                                            <!-- 문의글 목록 -->
                                            <table id="qnaTable" v-if="questions != []">
                                                <thead>
                                                    <th style="width: 10%"> 번호 </th>
                                                    <th style="width: 50%"> 제목 </th>
                                                    <th style="width: 20%"> 작성자 </th>
                                                    <th style="width: 20%"> 등록일 </th>
                                                </thead>
                                                <tbody>
                                                    <tr v-for="(question, index) in questions" :key="index">
                                                        <td> {{ index+1 }} </td>
                                                        <td>
                                                            <button id="questionTitle" @click="getQuestion(question.qid)">{{ question.qtitle }}</button>
                                                            <span class="badge badge-primary mgb-15" v-if="question.isanswered == '답변완료'">답변완료</span>
                                                        </td>
                                                        <td> {{ question.mid }} </td>
                                                        <td> {{ question.qdate}} </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                            <p v-if="questions == []">
                                                문의사항이 없습니다.
                                            </p>
                       
                                        </div>   
                                        <!-- Q&A 화면 구성 끝-->
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

                <!-- 탭 형식 화면구현 종료 -->
                <!-- 여기에 탭해서 상세페이지 / 문의하기 / 상품평 구현-->
           </div>
          
      </div>
</template>
    
<script>
    import ProductDataService from '../services/ProductDataService';
    import cartService from '../services/cart.service';
    import QuestionDataService from '../services/QuestionDataService';
    import AnswerDataService from '../services/AnswerDataService';
    import reviewservice from '../services/reviewservice';
    import reviewreplyservice from '../services/reviewreply.service';
    import FaqDataService from '../services/FaqDataService';

    export default {
      name: 'ProductDetail',
      data() {
        return {
            products: [],
            files: [],
            currentFile: null,
            questions: [],
            reviewList: [],
            currentQuestion: null,
            questionShow: false,
            currentAnswer: null,
            currentProduct: {
                pid: null,
                pname: "",
                ptype: "",
                pprice: "",
                pstock: 0,
                pimg1: "",
                pdetail: "",
            },
            cart: {
                cid: null,
                product: {
                    pid: null,
                    pname: "",
                    ptype: "",
                    pprice: 0,
                    pdetail: "",
                    pimg1: "",
                },
                username: "",
                pquantity:1,
            },
            currentIndex: -1,
            count: 1,
            pquantity: 0,
            username: "",
            currentReview: null,
            reviewReplyList: [],
            replyClicked: false,
            reviewReply: {
                rrid: null,
                rrcontent: "",
            },
            role:"",
            clicked2: false,
        }
      },
      computed : {
        totalPrice: function() {
            return this.currentProduct.pprice * this.cart.pquantity;
        },
        parseDetail: function() {
            return this.currentProduct.pdetail.replace(/\n/g, "<br>")
        }
      },
      methods: {
        toPayment(pid){
            pid = this.$route.params.pid;
                    console.log(pid);
                    ProductDataService.getProduct(pid)
                    .then(response => {
                        this.currentProduct = response.data;
                        console.log(response.data);
                    }).catch(e => {
                        console.log(e);
                    });
                    var idToken = window.localStorage.getItem("user");
                    var jsonIdToken = JSON.parse(idToken);
                    this.cart.username = jsonIdToken.username;
                    console.log(jsonIdToken.username);
                    var newCart = {
                        product : this.currentProduct,
                        username: this.cart.username,
                        pquantity: this.cart.pquantity
                    };
                    cartService.create(newCart)
                    .then(response => {
                        this.cart = response.data;
                        console.log(response.data);
                        console.log(this.cart.pid);
                        alert("결제 페이지로 이동합니다.");
                        this.$router.push("/orderconfirm/" + this.cart.cid).then(cartService.delete(this.cart.cid));
                        
                    }).catch(e => {
                        console.log(e);
    
                    })
        },


        getProduct(pid) {
            ProductDataService.getProduct(pid)
            .then(response => {
                this.currentProduct = response.data;
                console.log(this.$route.params.pid);
            }).catch(e => {
                console.log(e);
            })
        },
        getFile(fid) {
            ProductDataService.getFile(fid)
            .then(response => {
                this.currentFile = response.data;
                console.log(this.$route.params.fid);
            }).catch(e => {
                console.log(e);
            })
        },
        putintoCart(pid){
            pid = this.$route.params.pid;
            console.log(pid);
            ProductDataService.getProduct(pid)
            .then(response => {
                this.currentProduct = response.data;
                console.log(response.data);
            }).catch(e => {
                console.log(e);
            });
            var idToken = window.localStorage.getItem("user");
            var jsonIdToken = JSON.parse(idToken);
            this.cart.username = jsonIdToken.username;
            console.log(jsonIdToken.username);
            var newCart = {
                product : this.currentProduct,
                username: this.cart.username,
                pquantity: this.cart.pquantity
            };
            cartService.create(newCart)
            .then(response => {
                this.cart = response.data;
                console.log(response.data);
                console.log(this.currentProduct.pid);
                var confirm 
                = window.confirm("해당 상품을 장바구니에 추가하였습니다. 장바구니로 이동하시겠습니까?")
                if(confirm){
                    this.$router.push({name: 'cartlist'})
                } else {
                    this.$router.go(0);
                }
            }).catch(e => {
                console.log(e);

            })
        },

        retrieveQuestions() {   // 전체 문의글 목록
            QuestionDataService.getAll(this.$route.params.pid)
            .then(response => {
                this.questions = response.data;
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
        },
        getQuestion(qid) {  // 문의글 상세
            QuestionDataService.get(qid)
            .then(response => {
                this.questionShow = true;

                this.currentQuestion = response.data;
                console.log(response.data);
            }).catch(e => {
                console.log(e);
            });
            this.getAnswer(qid);
        },
        addqna_customer() { // 문의글 작성 페이지로 이동
            this.$router.push({ name: 'product-question-customer', params: {pid: this.$route.params.pid} });
        },
        modifyQuestion(id) {
            this.$router.push({ name: 'product-question-modify', params: {qid: id} })
        },
        deleteQuestion(qid) {
            var confirm = window.confirm("정말 삭제하시겠습니까?")
            if(confirm){
                QuestionDataService.delete(qid)
                .then(response => {
                    console.log(response.data);
                    this.$router.go();
                })
                .catch(e => {
                    console.log(e);
                });
            }
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
        registerAnswer(id) {
            this.$router.push({ name: 'answer-register', params: {qid: id} })
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
        },
        deleteReview(){
                var confirm = window.confirm("정말 삭제하시겠습니까?");
                if(confirm){
                reviewservice.delbyId(this.currentReview.rid)
                .then(response => {
                    console.log(response.data);
                    alert("리뷰가 식제되었습니다.");
                    this.$router.go(0);
                   
                }).catch(e => {
                    console.log(e);
                }) } else {
                    console.log("삭제취소");
                }

            },
       
        createReply(){
                var idToken = window.localStorage.getItem("user");
                var idTokenJson = JSON.parse(idToken);
                this.reviewReply.username = idTokenJson.username;
                console.log(this.currentReview);
                console.log(this.reviewReply.rrcontent);
                console.log(idTokenJson.username);
                var reviewReplyData = {
                    review: this.currentReview,
                    username: this.reviewReply.username,
                    rrcontent: this.reviewReply.rrcontent,
                }
                reviewreplyservice.create(reviewReplyData)
                .then(response => {
                    console.log(response.data);
                    this.reviewReply = response.data;
                    this.$router.go(0);
                    alert("댓글이 작성되었습니다.")
                })
            },
            
        getReview(rid){
            reviewservice.getbyId(rid)
            .then(response => {
                console.log(response.data);
                this.currentReview = response.data;
                this.replyClicked = true;
                
            }).catch(e => {
                console.log(e);
            });
            this.getReply(rid);
            
        },

        getReply(rid){
                reviewreplyservice.getAll(rid)
                .then(response => {
                    console.log(response.data);
                    this.reviewReplyList = response.data;
                }).catch(e => {
                    console.log(e);
                })
            },
        
        getReviewList(pid){
            pid = this.$route.params.pid;
            console.log(pid);
            reviewservice.getAll(pid)
            .then(response => {
                this.reviewList = response.data;
                console.log(response.data);
            }).catch(e => {
                console.log(e);
            })
        },
        
        addReview(){
            this.$router.push("/review/add/" + this.currentProduct.pid)
        },
        deleteReply(rrid){
            var confirm = window.confirm("정말 댓글을 삭제하시겠습니까?");
            if(confirm){
            reviewreplyservice.delete(rrid)
            .then(response => {
                console.log(response.data);
                alert("댓글을 삭제하였습니다.");
                this.$router.go(0);
            }).catch(e => {
                console.log(e);
            })} else {
                console.log("삭제 취소");
                
            }
        },
        listFaq(){//faq목록
            FaqDataService.getAll()
            .then(response => {
                this.faqs = response.data;
                console.log(response.data);
            }).catch(()=>{}); 
        },
        getFaq(fid) {  //faq 상세
            FaqDataService.get(fid)
            .then(response => {
                this.faqShow = true;
                this.currentFaq = response.data;
                console.log(response.data);
            }).catch(e => {
                console.log(e);
            });
        }
      },
      mounted() {
        this.getProduct(this.$route.params.pid);
        this.retrieveQuestions();
        this.getReviewList();
        this.listFaq();

        // 탭 누르면 해당 화면으로 전환하는 JS 코드
        const tabList = document.querySelectorAll('.tab_menu .list li');
        for (let i = 0; i < tabList.length; i++) {
            tabList[i].querySelector('.btn').addEventListener('click', function (e) {
                e.preventDefault();
                for (let j = 0; j < tabList.length; j++) {
                    tabList[j].classList.remove('is_on');
                }
                this.parentNode.classList.add('is_on');
            });
        }
    
        var idToken = window.localStorage.getItem("user");
        var jsonTokenpar = JSON.parse(idToken);
        this.cart.username = jsonTokenpar.username;
        console.log(this.cart.username);
        this.role = jsonTokenpar.roles;
        this.username = jsonTokenpar.username;
        },

    }
</script>
    
    
<style>
    .repImgDiv{
        margin-right:15px;
        height:auto;
        width:40%;
    }
    .repImg{
        width:100%;
        height:400px;
    }
    
    
    .container-mt{
        margin-top: 50px;
    }
    
    .detail-mt-md{
        margin-top: 100px;
        margin-bottom: 100px;
    }
    .img-size{
        width: 400px;
        height: 500px;
    }
    
    .product_name {
        margin-top: 30px;
        margin-left: -50px;
        width: 140px;
    }
    
    .product_price {
        margin-top: 80px;
        margin-left: -140px;
    }
    
    .product_qunatity {
        padding: 0;
        margin-top: 120px;
        margin-left: -90px;
    }
    
    .btnBuy {
        text-decoration: none;
        color: white;
        padding: 10px 30px;
        display: inline-block;
        position: relative;
        border: none;
        border-radius: 4px;
        background-color: rgb(22, 160, 133); 
    }
    
    .btnCart {
        text-decoration: none;
        color: white;
        padding: 10px 30px;
        display: inline-block;
        position: relative;
        border: none;
        border-radius: 4px;
        background-color: rgb(190, 190, 190); 
    }
    
    .orderBox {
        margin-top: -210px;
        margin-left: 480px;
    }
    
    .wrap ul {
        list-style: none;
        margin-top: 100px;
    }
    
    .wrap a {
        text-decoration: none;
        color: #333;
        padding: 50px;
        text-align: center;
        margin-left: 0px;
        position: relative;
    }
    
    .wrap {
        
        margin-top: 10%;
        margin-bottom: 20%;
        height: 800px;
        
        float: center;
    }
    
    .tab_menu {
        position: relative;
    }
    
    .tab_menu .list {
        overflow: hidden;
    }
    
    .tab_menu .list li {
        float: left;
        margin-right: 14px;
    }
    
    .tab_menu .list .btn {
        font-size: 15px;
        border: none; background-color: rgb(224, 224, 224); color: black; font-weight: bold; width: 220px; height: 30px; margin-bottom: 30px; margin-left: 20px;
        
    }
    
    .tab_menu .list .cont {
        display: none;
        position: absolute;
        top: 35px;
        left: 15px;
        background: white;
        color: black;
        
        width: 1200px;
       
        line-height: 30px;
        
        
    }
    
    .tab_menu .list li.is_on .btn {
        font-weight: bold;
        color: white;
        background-color: rgb(22, 160, 133);
        
    }
    
    .tab_menu .list li.is_on .cont {
        display:inline-block;
    }
    
    #prdDetailContent {
        margin-top: 0px; 
        margin-left: -150px; 
        width: 1100px; 
        height: 1000px; 
        background-color: white; 
        text-align: center;
    }
    
    #reviewContent {
        margin-left: 20px; 
        width: 1150px; 
        height: 1000px; 
        background-color: white; 
        margin-top: 20px;
    }
    
    #writeReviewBtn {
        font-weight: bold;
        color: white; 
        border: none; 
        float: right; 
        width: 60px; 
        height: 30px; 
        padding: 0; 
        margin-right: 10px;
        background-color: rgb(22, 160, 133); 
        border-radius: 3px; 
        font-size: 10px; 
        text-align: center;
    }
    
    #reviewTable {
        text-align: center; 
        margin-left: 60px;
    }
    
    #reviewTable thead {
        background-color:rgb(224, 224, 224); 
        border-top: 1px solid black;
    }
    
    #reviewTable tbody {
        background-color: white; 
        border-top: 1px solid black; 
        border-bottom: 1px solid black;
    }
    
    #qnaContent {
        margin-left: 20px; 
        width: 1150px; 
        height: 1000px; 
        background-color: white; 
        margin-top: 20px;
    }
    
    #qnaContent h3 {
        text-align: left;
    }
    
    #createqnaBtn {
        font-weight: bold;
        color: white; 
        border: none; 
        float: right; 
        width: 60px; 
        height: 30px; 
        padding: 0; 
        margin-right: 10px;
        background-color: rgb(22, 160, 133); 
        border-radius: 3px; 
        font-size: 10px; 
        text-align: center;
    }
    
    #qnaTable {
        text-align: center;
        margin: auto;
        width: 85%;
    }
    
    #qnaTable thead {
        background-color:white; 
        border-top: 1px solid black;
    }
    
    #qnaTable tbody {
        background-color: white; 
        border-top: 1px solid black; 
        border-bottom: 1px solid black;
    }
    
    footer {
        width: 100%;
    }
    
    #side_list ul {
    
        list-style : none;
        
        
        padding: 0;
        margin: 0;
        position: fixed;
        width: 180px;
        overflow: auto;
        top: 400px;
        left: 100px;
        height: 200px;
    }
    
    #side_list ul li a {
        text-decoration: none;
        display: block;
        padding: 8px 15px 8px 15px;
        font-size: 14px;
        height: 40px;
        text-align: center;
        width: 120px;
    }
    
    #side_About {
        left: 25%; 
         z-index: 5; 
         height: 25px;
         background-color: rgb(224, 224, 224); 
         border: 0; 
         border-radius: 8px; 
         color: black; 
         font-weight: bold;  
    }
    #side_vaseplant {
        left: 25%; 
         z-index: 5; 
         height: 25px;
         background-color: rgb(22, 160, 133); 
         border: 0; 
         border-radius: 8px; 
         color: white; 
         font-weight: bold;  
    }
    #side_hangingplant {
        left: 25%; 
         z-index: 5; 
         height: 25px;
         background-color: rgb(224, 224, 224); 
         border: 0; 
         border-radius: 8px; 
         color: black; 
         font-weight: bold;  
    }
    
    #side_succulent {
        left: 25%; 
         z-index: 5; 
         height: 25px;
         background-color: rgb(224, 224, 224); 
         border: 0; 
         border-radius: 8px; 
         color: black; 
         font-weight: bold;  
    }
    
    #side_otherproduct {
        left: 25%; 
         z-index: 5; 
         height: 25px;
         background-color: rgb(224, 224, 224); 
         border: 0; 
         border-radius: 8px; 
         color: black; 
         font-weight: bold;  
    }
    
    .side_customer {
        width: 150px;
    }
    
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
    
    .sidebar {
        margin-top: 60px;
    }
     .sidebar ul{
        
        list-style : none;
        background-color: rgb(224, 224, 224);
        width: 150px;
        padding: 0;
        margin: 0;
        position: fixed;
        overflow: auto;
        margin-left: 88px;
        text-align: center;
    }
    
    .sidebar ul li a {
        text-decoration: none;
        display: block;
        color: black;
        padding: 8px 15px 8px 15px;
        font-weight: bold;
        font-size: 14px;
    }
    
    .sidebar ul li a.side_active{
        background-color: rgb(22, 160, 133);
        color: white;
    }
    
    .sidebar ul li a:hover{
        background-color: rgb(22, 160, 133);
        color: white;
    }
    
    #questionDetail {
        margin-bottom: 50px;
        width: 70%;
        margin: auto;
    }
    .qnaContent {
        min-height: 100px;
        margin-top: 20px;
    }

    #questionTitle {
        border: 0;
        outline: 0;
        background-color: white;
    }

    #answerBtn {
        margin-top: 20px;
        height: 50px;
    }
    #answerBtn button {
        font-weight: bold;
        color: white; 
        border: none; 
        float: right; 
        width: 60px; 
        height: 30px; 
        padding: 0; 
        margin-right: 10px;
        background-color: rgb(22, 160, 133); 
        border-radius: 3px; 
        font-size: 10px; 
        text-align: center;
    }

    #reviewTable {
        text-align: center;
        margin: auto;
        width: 85%;
    }
    
    #reviewTable thead {
        background-color:white; 
        border-top: 1px solid black;
    }
    
    #reviewTable tbody {
        background-color: white; 
        border-top: 1px solid black; 
        border-bottom: 1px solid black;
    }

    #reviewTitle {
        border: 0;
        outline: 0;
        background-color: white;
    }

    #reviewBtn {
        margin-top: 20px;
        height: 50px;
    }
    #reviewBtn button {
        font-weight: bold;
        color: white; 
        border: none; 
        float: right; 
        width: 60px; 
        height: 30px; 
        padding: 0; 
        margin-right: 10px;
        background-color: rgb(22, 160, 133); 
        border-radius: 3px; 
        font-size: 10px; 
        text-align: center;
    }
    
</style>
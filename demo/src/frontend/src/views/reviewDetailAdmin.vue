<template>
<div><side-menu></side-menu>
    <div id="root" class="root">
        

        <label class="title">
            <h3>리뷰</h3>
        </label>
        
        <hr>
        <div class="product-detail">
    <table style="text-align: center; font-size: 13px;">
        <thead style="background-color: rgb(224,224,224);">
            <th style="width: 200px;">제품명</th>
            <th>제품이미지</th>
            <th style="width: 130px;">제품타입</th>
            <th style="width: 130px;">제품가격</th>
            <th style="width: 640px;">제품상세</th>
        </thead>
        <tbody>
            <td>{{currentReview.product.pname}}</td>
            <td><img :src="currentReview.product.pimg1" style="width: 130px; height: 130px;"></td>
            <td>{{currentReview.product.ptype}}</td>
            <td>{{currentReview.product.pprice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')}}</td>
            <td>{{currentReview.product.pdetail}}</td>
        </tbody>
    </table>
   <hr>
</div>
        <div id="questionDetail">
            <h4>{{ currentReview.rtitle }}</h4>
            {{ currentReview.username }}
            {{ currentReview.rdate }}
            <p class="qnaContent"> {{ currentReview.rcontent }} </p>
            
            <div v-for="(reply, index) in replyList" :key="index">
                <hr>
                
                {{ reply.rrdate }}
                <p class="qnaContent" v-if="clicked == false"> {{ reply.rrcontent }} </p>
                <p class="qnaContent" v-if="clicked == true"><textarea style="width: 500px;" v-model="reply.rrcontent"></textarea></p>
                
            </div>

            <div id="reviewReply" v-if="role == 'ROLE_ADMIN'">
            <textarea col="200" row="5" v-model="reply.rrcontent" style="width: 850px;" placeholder="댓글을 입력하세요"></textarea>
            <button type="button" @click="createReply" style="background-color: rgb(22,160,133); color: white; font-weight: bold; border:none; border-radius: 5px;">댓글 작성</button>
            </div>
            
            <!-- 답변 내용 -->
            
            <button @click="back" style="margin-left: 400px; margin-top: 50px; background-color: rgb(22,160,133); color: white; font-weight: bold; border:none; border-radius: 5px;"> 뒤로 돌아가기 </button>
        </div>
    </div>
</div>
</template>

<script>
import reviewservice from '../services/reviewservice';
import reviewreplyservice from '../services/reviewreply.service';
import sidemenuCustomer from './sidemenu_admin.vue';

export default {
    name: 'question-detail-admin',
    data() {
        return {
            currentReview: {
                rid: null,
                rtitle: "",
                rcontent: "",
                username: "",
                rdate: null,
                rmodifydate: null,
                product: null,
            },
            replyList: [],
            role: "",
            username: "",
            reply: {
                rrid: null,
                rrcontent: "",
                username: "",
                rrdate: null,
                rrmodifydate: null,
            },
            currentProduct: {
                pid: null,
                pname: "",
                ptype: "",
                pdetail: "",
                pimg1: "",
            },
            clicked: false,
        };
    },
    methods: {
        getReview(rid) {
            reviewservice.getbyId(rid)
            .then(response => {
                this.currentReview = response.data;
                console.log(response.data);
            }).catch(e => {
                console.log(e);
            });
            this.getReply(rid);
        },
        getReply(rid) {    // 문의글 답변
            reviewreplyservice.getAll(rid)
            .then(response => {
                this.replyList = response.data;
                console.log(response.data);
            }).catch(e => {
                console.log(e);
            })
        },
        createReply(){
                var idToken = window.localStorage.getItem("user");
                var idTokenJson = JSON.parse(idToken);
                this.reply.username = idTokenJson.username;
                console.log(this.currentReview);
                console.log(this.reply.rrcontent);
                console.log(idTokenJson.username);
                var reviewReplyData = {
                    review: this.currentReview,
                    username: this.reply.username,
                    rrcontent: this.reply.rrcontent,
                }
                reviewreplyservice.create(reviewReplyData)
                .then(response => {
                    console.log(response.data);
                    this.reply = response.data;
                    this.$router.go(0);
                }).catch(e => {
                    console.log(e);
                })
            },
            back(){
                this.$router.go(-1);
            },  
            modifyReply(rrid){
                var replyData = {
                    rrcontent: this.reply.rrcontent
                }
                reviewreplyservice.modify(rrid, replyData)
                .then(response => {
                    console.log(response.data);
                    alert("댓글이 수정되었습니다.");
                    this.clicked == false;
                    this.$router.go(0);
                })
            }
    },
    components: {
        'side-menu': sidemenuCustomer
    }, 
    mounted() {
        this.getReview(this.$route.params.rid);

        var idToken = window.localStorage.getItem("user");
        var jsonTokenpar = JSON.parse(idToken);
        this.username = jsonTokenpar.username;
        this.role = jsonTokenpar.roles;
    }
}
</script>


<style scoped lang="css">
    @import "../css/bootstrap.min.css";
    @import "../css/main_customer.css";
    @import "../css/question_list_admin.css";
    @import "../css/question_detail_admin.css";
</style>
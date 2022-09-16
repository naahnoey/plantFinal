<template>
    <div>
    <side-menu></side-menu>
    <div id="root" class="root">
        

        <label class="title">
            <h3>리뷰</h3>
        </label>
        <hr>

        <!-- 공지사항 목록 -->
        <p class="table-p">
            <table class="Qna_table">
                <thead>
                    <th>번호</th>
                    <th>상품명</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>작성/수정일</th>
                    
                </thead>
                <tbody>
                    <tr v-for="(review, index) in reviewList" :key="index">
                        <td>{{ index+1 }}</td>
                        <td>{{ review.product.pname }}</td>
                        
                        <td>
                            
                                <router-link :to="`/admin/review/detail/${review.rid}`"> {{review.rtitle}} </router-link>
                            
                        </td>
                        <td>{{ review.username }}</td>
                        <td v-if="review.rmodifydate == null">{{ review.rdate }}</td>
                        <td v-else>{{ review.rmodifydate }}(수정)</td>
                        
                    </tr>
                </tbody>
            </table>
        </p>

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

import sidemenuCustomer from './sidemenu_admin.vue';
import reviewservice from '../services/reviewservice';

export default {
    name: 'question-list-admin',
    data() {
        return {
            reviewList: [],
            username: "",

        };
    },
    methods: {
        retrieveReview() {
            reviewservice.getList()
            .then(response => {
                this.reviewList = response.data;
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
        },
       
    },
    components: {
        'side-menu': sidemenuCustomer
    }, 
    mounted() {
        this.retrieveReview();
    }
}
</script>


<style scoped lang="css">
    @import "../css/bootstrap.min.css";
    @import "../css/main_customer.css";
    @import "../css/question_list_admin.css";
</style>
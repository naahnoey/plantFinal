<template>
  <div>
     <side-menu></side-menu>
       <div id="product-manager">
                <!-- 검색 -->
            <p id="product-search" class="form-inline form-group">
                <select class="form-control">
                    <option>상품명</option>
                    <option>상품번호</option>
                    <option>카테고리</option>
                </select>
                &nbsp;&nbsp;
                <input type="text" class="form-control">
                &nbsp;&nbsp;
                <button class="btn">검색</button>
            </p>
            
            <!-- 상품 목록 -->
            <table class="table text-center">
                <thead>
                    <tr>
                        <th>상품번호</th>
                        <th>상품명</th>
                        <th>가격</th>
                        <th>카테고리</th>
                        <th>재고</th>
                        <th>이미지</th>
                        <th>수정</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(product, index) in products" :key="index">
                        <td>{{product.pid}}</td>
                        <td>{{product.pname}}</td>
                        <td>{{product.pprice}}</td>
                        <td>{{product.ptype}}</td>
                        <td>{{product.pstock}}</td>
                        <td><img :src="product.pimg1"></td>
                        <td>
                            <router-link :to="`/admin/updateProduct/${product.pid}`" tag="button" class="btn">수정</router-link>
                            <!-- <button class="btn">수정</button> -->
                        </td>
                    </tr>
                </tbody>
            </table>

            <!-- 페이지 기능 -->
            <!-- <p id="paging">
                <button>1</button>
                <button>2</button>
                <button style="color:white; background-color:rgb(22, 160, 133)">3</button>
                <button>4</button>
            </p> -->
       </div>
    
  </div>
</template>

<script>
import sidemenuCustomer from '../views/sidemenu_admin.vue';
import ProductDataService from '../services/ProductDataService';
export default {
    name: 'productManage',
    data() {
        return {
            products: [],
            files: [],
            currentFile: null,
            currentProduct: null,
            currentIndex: -1,
        }
    },
    methods: {
        retrieveProducts() {
            // 상품 목록
            ProductDataService.getAllAdmin()
            .then(response => {
                this.products = response.data;
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
        },
              refreshList() {
            this.retrieveProducts();
            this.currentProduct = null;
            this.currentIndex = -1;
        },
        setActiveProduct(product, index) {
            this.currentProduct = product;
            this.currentIndex = index;
        },
    },
    mounted() {
        this.retrieveProducts();
    },

    components: {
        'side-menu': sidemenuCustomer
    }

}
</script>

<style>
/* 공지사항(관리자) */
#product-manager {  /* 전체 설정 */
    width: 80%;
    min-height: 500px;
    margin: auto;
    margin-top: 50px;
    margin-bottom: 100px;
    margin-left: 200px;
    padding:1px 16px;
    text-align: center;
}

/* 검색 부분 */
#product-search {
    margin-left: 500px;
}
#product-search input {
    width: 300px;
}

#product-manager button {    /* 추가 버튼을 제외한 모든 버튼 */
    background-color: lightgray;
}
#product-add button {   /* 추가 버튼 */
    color: white;
    background-color: rgb(22, 160, 133);
    width: 70px;
    height: 40px;
    margin-left: 1300px;
}

#product-add select {   /* 정렬 */
    border: 0;
}

/* 테이블 설정 */
#product-manager thead {
    height: 40px;
    background-color: rgb(224, 224, 224);
}
#product-manager tbody tr {
    height: 90px;
}
#product-manager tbody tr td {
    vertical-align: middle;
}
#product-manager tbody tr td img {  /* 이미지 크기 고정 */
    width: 70px;
    height: 70px;
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
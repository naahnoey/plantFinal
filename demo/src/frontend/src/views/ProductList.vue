<template>
    <div class="products">
        <p class="search-product">
            <input v-model="searchName">
            <button @click="searchProduct">검색</button>
        </p>
        <div class="product_list">
            <li class="product" v-for="(product, index) in products" :key="index">
                <img :src="product.pimg1">
                <p id="product_name">
                    <router-link :to="`/product/detail/${product.pid}`"> {{product.pname}} </router-link>
                </p>
                <p id="product_price">
                    {{ product.pprice }}원
                </p>
            </li>
        </div>
        
        <!-- 페이징 -->
        <!--
        <div class="page_wrap">
            <ul class="page_nation">
                <li><a href="#"></a></li>
                <li><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">></a></li>
            </ul>
        </div>
        -->
    </div>
</template>

<script>
import ProductDataService from '../services/ProductDataService';

export default {
    name: 'product-list',
    data() {
        return {
            products: [],
            currentProduct: null,
            searchProducts: [],
            searchName: '',
            ptype: ''
        };
    },
    methods: {
        retrieveProducts() {
            this.ptype = this.$route.params.ptype;

            // 상품 목록
            ProductDataService.getAll(this.$route.params.ptype)
            .then(response => {
                this.products = response.data;
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
        },
        searchProduct() {
            ProductDataService.searchProduct(this.ptype, this.searchName)
            .then(response => {
                this.products = response.data;
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
        }
    },
    mounted() {
        this.retrieveProducts();
    }
}
</script>

<style scoped lang="css">
    @import "../css/bootstrap.min.css";
    @import "../css/main_customer.css";
    @import "../css/plant.css";
</style>
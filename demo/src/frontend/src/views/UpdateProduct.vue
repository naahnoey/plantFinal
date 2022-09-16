<template>
  <div>
    <!-- <side-menu></side-menu> -->
        <div id="product-add">
            <h3><strong>상품 수정</strong></h3>
            <hr>
            <form>
                <p>
                    <input type="text" required v-model="currentProduct.pname" id="pname" class="form-control w-50" placeholder="상품명">
                </p>
                <p class="form-inline form-group">
                    <select v-model="currentProduct.ptype" class="form-control w-50">
                        <option disabled value="">카테고리</option>
                        <option>화분식물</option>
                        <option>공중식물</option>
                        <option>다육식물</option>
                        <option>기타상품</option>
                    </select>
                </p>         
                <p>
                      <input type="number" required v-model="currentProduct.pprice" id="pprice" class="form-control w-50" placeholder="가격"> 
                </p> 
                <p>
                      <input type="number" id="pstock" v-model="currentProduct.pstock" class="form-control w-50" placeholder="수량"> 
                </p>   
                <p>
                    <!-- <input type="file" ref="file" id="pimg1" @change="selectFile"> -->
                    <img :src="currentProduct.pimg1" class="">
                </p>
                <p>
                    <textarea class="form-control" rows="10" v-model="currentProduct.pdetail" id="pdetail" placeholder="상세정보 입력"></textarea>
                </p>
                <button type="button" class="btn" @click="updateProduct">수정</button>
            </form>
        </div>
  </div>
</template>

<script>
// import sidemenuCustomer from '../views/sidemenu_admin.vue';
import ProductDataService from '../services/ProductDataService';
export default {
    name: "UpdateProduct",
    data() {
        return {
            currentProduct: {
                pid: null,
                pname: "",
                ptype: "",
                pprice: "",
                pstock: 0,
                pimg1: "",
                pdetail: "",
            },
            submitted: false
        }
    },
    // components: {
    //     'side-menu': sidemenuCustomer
    // },
    methods: {
        getProduct(pid) {
            ProductDataService.update(pid)
            .then(response => {
                this.currentProduct = response.data;
            }).catch(e => console.log(e))

        },
        updateProduct() {
            var productData = {
                pname: this.currentProduct.pname,
                ptype: this.currentProduct.ptype,
                pprice: this.currentProduct.pprice,
                pstock: this.currentProduct.pstock,
                // pimg: this.currentProduct.pimg1,
                pdetail: this.currentProduct.pdetail,
            };
            this.pdata = new Blob([JSON.stringify(productData)], {type: "application/json"});
            ProductDataService.updateProduct(this.currentProduct.pid, this.currentProduct)
            .then(response => {
                this.currentProduct.pid = response.data.pid;
                this.submitted = true;

                alert("상품이 수정되었습니다.");
                this.$router.go(-1);
            }).catch(e => console.log(e));
        }
    },
    mounted() {
        this.getProduct(this.$route.params.pid);
    }


}
</script>

<style>
#product-add {
    width: 900px;
    margin: auto;
    margin-bottom: 300px;
    padding-top: 70px;
}

#product-add hr {   /* 구분선 */
    background-color: black;
    border: none;
    height: 2px;
}

#product-add form { /* 전체 작성칸 설정 */
    text-align: center;
    margin: auto;
    width: 80%;
}

#pprice {    /* 가격 입력칸 */
    width: 45%;
}

#pstock {    /* 가격 입력칸 */
    width: 45%;
}


#pimg1 {    /* 파일 추가 input 태그 테두리 삭제 */
    border: 0;
    margin-left: -420px;
}

#product-add button {   /* 등록 버튼 */
    margin-left: 0px;
    background-color: rgb(22, 160, 133);
    color: white;
   
}
</style>
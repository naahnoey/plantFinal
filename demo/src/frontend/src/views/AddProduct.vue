<template>
  <div>
    <side-menu></side-menu>
      <div id="product-add">
            <h3><strong>상품 등록</strong></h3>
            <hr>
            <form enctype="multipart/form-data">
                <p>
                    <input type="text" required v-model="product.pname" id="pname" class="form-control w-50" placeholder="상품명">
                </p>
                <p class="form-inline form-group">
                    <select v-model="product.ptype" class="form-control w-50">
                        <option disabled value="">카테고리</option>
                        <option>화분식물</option>
                        <option>공중식물</option>
                        <option>다육식물</option>
                        <option>기타상품</option>
                    </select>
                </p>         
                <p>
                      <input type="number" required v-model="product.pprice" id="pprice" class="form-control w-50" placeholder="가격"> 
                </p> 
                <p>
                      <input type="number" id="pstock" v-model="product.pstock" class="form-control w-50" placeholder="수량"> 
                </p>   
                <p>
                    <input type="file" ref="file" id="pimg1" @change="selectFile">
                </p>
                <p>
                    <textarea class="form-control" rows="10" v-model="product.pdetail" id="pdetail" placeholder="상세정보 입력"></textarea>
                </p>
                <button type="button" class="btn" @click="addProduct">등록</button>
            </form>
        </div>
    </div>
</template>

<script>
import sidemenuCustomer from '../views/sidemenu_admin.vue';
import ProductDataService from '../services/ProductDataService';
export default {
name:'AddProduct',
data() {
    return {
        product: {
            pid: null,
            pname: "",
            ptype: "",
            pprice: "",
            pstock: "",
            selectedFiles: undefined,
            currentFile: undefined,
            pimg1 : null,
            pdetail: "",
            pdata: null
        },
        submitted: false
    }
},
methods: {
    selectFile() {
      this.selectedFiles = this.$refs.file.files;
    },
    addProduct() {
        var productData = {
            pname: this.product.pname,
            ptype: this.product.ptype,
            pprice: this.product.pprice,
            pstock: this.product.pstock,
            pimg1: 0,
            pdetail: this.product.pdetail,
        };
        this.currentFile = this.selectedFiles.item(0);
        this.pdata = new Blob([JSON.stringify(productData)], {type: "application/json"});   // 상품 정보 JSON
        ProductDataService.create(this.pdata, this.currentFile) // 이미지 파일, 상품 정보 같이 보내기
        .then(response => {
            this.product.pid = response.data.pid;
            this.submitted = true;

            alert("상품이 등록되었습니다.");
        }).catch(e => console.log(e));
    }
},
components: {
    'side-menu': sidemenuCustomer
  }, 
}
</script>

<style scoped>
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
    background-color: rgb(22, 160, 133);
    color: white;
    margin-left: 0px;
}
</style>
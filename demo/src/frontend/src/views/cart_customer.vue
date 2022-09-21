<template>
  <div>
    <div id="root" class="root" style="margin-bottom: 500px;">
      <div v-if="cartlist != []">
      <div
        id="cart_item"
        v-for="(cart, index) in cartlist"
        :key="index"
      >
        <!-- 장바구니 아이템 목록 반복문으로 구현 예정 -->
        <div>
          <label id="cart_image"
            ><img
              :src="cart.product.pimg1"
              style="width: 200px; height: 240px; float: left"
          /></label>
        </div>
        <div class="cart_iteminfo">
          <label id="cart_itemname"
            >&nbsp;&nbsp; [상품명] {{ cart.product.pname }}</label
          >
          <label id="cart_price"
            >&nbsp;&nbsp; [제품가격] {{ cart.product.pprice }}
          </label>

          <label id="cart_qty"
            >&nbsp;&nbsp; [주문수량]
            <input
              type="number"
              style="width: 80px; height: 25px; display: inline-block"
              name="count"
              id="count"
              class="form-control"
              v-model="cart.pquantity"
              
              min="1"
            />

            <button
              type="button"
              @click="updateCart(cart.cid, cart.pquantity)"
              style="height: 30px; font-size: 8px; justify-content: center"
            >
              변경하기
            </button>
          </label>

          <label id="cart_total"
            >&nbsp;&nbsp; [결제 예상 금액]
            {{ cart.product.pprice * cart.pquantity }}</label
          >
          <canvas class="graySquare"> </canvas>
          <router-link tag="button" type="button" id="purchase_btn" :to="`/orderconfirm/${cart.cid}`">구매하기</router-link>
          <button type="button" id="delete_btn" @click="deleteCart(cart.cid)">
            삭제하기
          </button>
        </div>
      </div>
      <div>
        <label id="total_price" style="float: right; margin-right: 110px">
          합계 : {{ total }}
          
        </label>
        <router-link tag="button" :to="`/orderconfirm`" type="button" id="order-all" style="position: relative; margin-top: 100px; float: center; margin-left: 500px; background-color: rgb(22,160, 133); color:white; font-size: 20px; border-radius: 8px; border: none; width: 140px; height: 40px; margin-bottom: 300px;">전체 주문하기</router-link>
      </div>

      <!-- <nav id="paging" style="z-index: -1;">
            <ul class="pagination pagination-sm d-flex justify-content-center">
              <li class="page-item active" aria-current="page">
                <span class="page-link">1</span>
              </li>
              <li class="page-item"><a class="page-link" href="#">2</a></li>
              <li class="page-item"><a class="page-link" href="#">3</a></li>
            </ul>
        </nav>  -->
      </div>

      <div v-else style="text-align: center;"><h3> 현재 장바구니가 비었습니다. </h3></div>
    </div>

    
  </div>
</template>

<script>
import CartService from "../services/cart.service";
export default {
  name: "cart-customer",
  data() {
    return {
      
      cartlist: [],
      currentCart: {
        cid: null,
        pid: null,
        username: "",
        pquantity: 0,
      },
      username: "",
      total: 0,
    };
  },
  methods: {
    
    deleteCart(cid) {
      this.currentCart.cid = cid;
      var confirm = window.confirm("정말 삭제하시겠습니까?")
      if(confirm){
      CartService.delete(this.currentCart.cid)
        .then((response) => {
          console.log(response.data);
          alert("해당 장바구니를 삭제하였습니다.");
          this.$router.go(0);
        })
        .catch((e) => {
          console.log(e);
        });
      } else {
        this.$router.go(0);
      }
    },
    updateCart(cid, pquantity) {
    this.currentCart.cid = cid;
    this.currentCart.pquantity = pquantity;
      var newCart = {
        pquantity: this.currentCart.pquantity,
      };
      this.currentCart.cid = cid;
      CartService.updateCart(this.currentCart.cid, newCart)
        .then((response) => {
          console.log(response.data);
          alert("장바구니 수량이 변경되었습니다.");
          this.$router.go(0);
        })
        .catch((e) => {
          console.log(e);
        });
    },
    getCartList() {

      var idToken = window.localStorage.getItem("user");
      var jsonIdToken = JSON.parse(idToken);
      this.username = jsonIdToken.username;

      CartService.getAll(this.username)
        .then((response) => {
          this.cartlist = response.data;
          console.log(response.data);
          console.log(response.data.length);
          var total = 0;
          for (let i = 0; i < response.data.length; i++){
            total = total + (response.data[i].pquantity * response.data[i].product.pprice);
            console.log(response.data[i].pquantity); 
            console.log(response.data[i].product.pprice);
          }
          this.total = total;
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },

  components: {},
  mounted() {
    this.currentCart.pquantity = this.cartlist.pquantity;
    // this.setTotal();
    var idtoken = window.localStorage.getItem("user");
    var idtokenjson = JSON.parse(idtoken);
    console.log(idtokenjson.username);
    this.username = idtokenjson.username;
    // this.getProductData();
    this.getCartList();
    
  },
  computed: {},
};
</script>

<style scoped>
body {
  margin: 0 auto;
  padding: 0;
  width: 100%;
  height: 500px;
  font-family: "Malgun Gothic";
}

.root {
  position: relative;
  width: 1500px;
  margin-left: 20%;
  display: inline-block;
  top: 100px;
  height: 500px;
}

.graySquare {
  border: 2px solid rgb(224, 224, 224);
  position: absolute;
  width: 1150px;
  height: 300px;
  right: -10%;
  z-index: -1;
  top: -30px;
  left: -60px;
}

footer {
  margin-top: 1200px;
  text-align: left;
 
}

.title {
  margin-left: 5%;
  width: 84%;
}

#cart_item {
  text-align: left;
  margin-top: 40px;
  position: relative;
  display: inline-block;
}
#cart_item label {
  width: 800px;
  display: block;
  height: 40px;
  font-weight: bold;
}
#cart_image {
  padding: 4px;
}
#cart_qty {
  width: 300px;
}
#cart_item button {
  position: relative;
  background-color: rgb(22, 160, 133);
  color: white;
  font-weight: bold;
  border: none;
  border-radius: 5px;
  padding: 10px;
  margin-top: -110px;
  display: inline-block;
}
#purchase_btn {
  margin-left: 850px;
  top: -30px;
}
#delete_btn {
  margin-left: 30px;
  top: -30px;
}

#total_price {
  font-weight: bolder;
  margin-top: 30px;
  background-color: rgb(224, 224, 224);
  position: relative;
  display: inline-block;
}
</style>

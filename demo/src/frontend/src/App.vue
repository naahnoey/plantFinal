<template>
  <div id="app">
    <nav class="navbar navbar-expand navbar-dark" style="background-color: rgb(224, 224, 224); ">
      &nbsp; &nbsp; 
      <a href class="navbar-brand" style="color: rgb(64,64,64)" @click.prevent>trefle</a>
      <div class="navbar-nav mr-auto">
        <li class="nav-item">
          <router-link to="/home" style="color: rgb(64, 64, 64)"  class="nav-link">
            Home
          </router-link>
        </li>
        <li class="nav-item">
          <router-link to="/about" style="color: rgb(64, 64, 64)" class="nav-link">
            About
          </router-link>
        </li>
        <li class="nav-item">
          <router-link to="/product/화분식물" style="color: rgb(64, 64, 64)" class="nav-link">
            화분식물
          </router-link>
        </li>
        <li class="nav-item" >
          <router-link to="/product/공중식물" style="color: rgb(64, 64, 64)" class="nav-link">
            공중식물
          </router-link>
        </li>
        <li class="nav-item" >
          <router-link to="/product/다육식물" style="color: rgb(64, 64, 64)" class="nav-link">
            다육식물
          </router-link>
        </li>
        <li class="nav-item">
          <router-link to="/product/기타상품" style="color: rgb(64, 64, 64); margin-right: 600px;" class="nav-link">
            기타상품
          </router-link>
        </li>
        <li class="nav-item">
          <router-link to="/notice" style="color: rgb(64, 64, 64)" class="nav-link">
            공지사항
          </router-link>
        </li>
        <li class="nav-item">
          <router-link to="/interior" style="color: rgb(64, 64, 64)" class="nav-link">
            인테리어
          </router-link>
        </li>
        <li class="nav-item">
          <router-link to="/chatbot" style="color: rgb(64, 64, 64)" class="nav-link">
            챗봇상담
          </router-link>
        </li>
        <li v-if="showAdminBoard" class="nav-item">
          <router-link to="/admin/user" class="nav-link" style="color: rgb(64, 64, 64)">관리자페이지</router-link>
        </li>
        <li class="nav-item">
          <router-link v-if="showUserBoard" :to="`/user/detail/${username}`" class="nav-link" style="color: rgb(64, 64, 64)">마이페이지</router-link>
        </li>
        <li class="nav-item">
          <router-link v-if="showUserBoard" to="/cart" class="nav-link" style="color: rgb(64, 64, 64)"  class-active="active">장바구니</router-link>
        </li>
      </div>
      <div v-if="!currentUser" class="navbar-nav ml-auto">
        <li class="nav-item">
          <router-link to="/register" style="color: rgb(64,64,64)" class="nav-link">
            회원가입
          </router-link>
        </li>
        <li class="nav-item">
          <router-link to="/login" style="color: rgb(64,64,64)" class="nav-link">
            로그인
          </router-link>
        </li>
      </div>
      <div v-if="currentUser" class="navbar-nav ml-auto">
        <!-- <li class="nav-item">
          <router-link to="/profile" class="nav-link">
            <font-awesome-icon icon="user" />
            {{ currentUser.username }}
          </router-link>
        </li> -->
        <li class="nav-item">
          <a class="nav-link" style="color: rgb(64,64,64)" href @click.prevent="logOut">
            로그아웃
          </a>
        </li>
      </div>
    </nav>

      <router-view />

    <div style="margin-top: 700px;"></div>
    
    <footer>
      <hr>
        <div style="float: left; margin-left: 200px;">
            회사소개<br>
            대표 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| 홍길동 <br>
            주소 | 서울특별시 강남구 언주로 508 14층 <br>
            사업자등록번호 | OOO-OO-OOOOO <br>
            전화 | OOO-OOOO-OOOO <br>
            이메일 | email@gmail.com <br> 
        </div>
        <div style="float: left; margin-left: 200px;">
            고객센터<br>
            전화 | OOO-OOOO-OOOO <br>
            이메일 | email@gmail.com <br>
            이용시간 | 월~금 09:00 - 18:00 <br> 
            점심시간 | 12:00 - 13:00 <br>
            휴무 | 토, 일, 공휴일은 off <br>
        </div>
        <div style="float: left;margin-left: 200px; ">
            계좌정보<br>
            OO은행 | 123-12-12345 통신판매업 신고번호 제2022-서울 강남-00001호 [사업자정보확인] <br>
            예금주 | OOO <br> <br>
            택배사<br>
            한진택배(1588-0011) <br>
            copyright &copy; 트레플 All right reserved.
        </div>
        
    </footer>
     <div style="margin-top: 200px;"></div>

  </div>
</template>
<script>
export default {
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    showAdminBoard() {
      if (this.currentUser && this.currentUser.roles) {
        return this.currentUser.roles.includes('ROLE_ADMIN');
      }
      return false;
    },
    showUserBoard() {
      if (this.currentUser && this.currentUser.roles) {
        return this.currentUser.roles.includes('ROLE_USER');
      }
      return false;
    }
  },
  methods: {
    logOut() {
      this.$store.dispatch('auth/logout');
      this.$router.push('/login');
    },
    refreshList() {
      window.location.reload(true);
    }
  },
  updated(){
    var token = window.localStorage.getItem("user");
    var Jsontoken = JSON.parse(token);
    if(Jsontoken != null){
    this.username = Jsontoken.username;
    }
  }
};
</script>

<style scoped>
  .router-link-active{
   font-weight: bold;
 
}



</style>
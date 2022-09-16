<template>
  <div>
    <side-menu></side-menu>
  <div class="container">
    <header class="jumbotron">
      <h3>{{content}}</h3>
    </header>
  </div>
</div>
</template>

<script>
import sidemenuCustomer from '../views/sidemenu_admin.vue';
import UserService from '../services/user.service';
export default {
  name: 'BoardAdmin',
  data() {
    return {
      content: ''
    };
  },
  components: {
    'side-menu': sidemenuCustomer
  }, 
  mounted() {
    UserService.getAdminBoard().then(
      response => {
        this.content = response.data;
      },
      error => {
        this.content =
          (error.response && error.response.data && error.response.data.message) ||
          error.message ||
          error.toString();
      }
    );
  }
};
</script>
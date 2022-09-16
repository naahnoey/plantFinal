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
  import sidemenuCustomer from './sidemenu_customer.vue';
import UserService from '../services/user.service';
export default {
  name: 'BoardUser',
  data() {
    return {
      content: ''
    };
  },
  components: {
    'side-menu': sidemenuCustomer
  },
  mounted() {
    UserService.getUserBoard().then(
      response => {
        this.content = response.data;
      },
      error => {
        this.content =
          (error.response && error.response.data) ||
          error.message ||
          error.toString();
      }
    );
  }
};
</script>
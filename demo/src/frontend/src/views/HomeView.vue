<template>
  <div class="container">
    <!-- <header class="jumbotron">
      <h3>{{content}}</h3>
    </header> -->
    <div class="slideshow-container">
       <img :src ="banner1">               
    </div>

      <p style="font-size: 30px; font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  margin-top: 150px; margin-left: 50px;">
   <strong>  Plant Shop </strong> <br>
  </p>
  <P style="font-size: 20px; font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  margin-top: 20px; margin-left: 50px;">
  식물에게 자신의 공간 한편을 내어주는 사람들, 식물과 함께하는 사람들, 식물을 사랑하는 사람들을 위한 공간입니다.<br>
  트레플은 반려식물과 함께하는 더욱 건강하고 행복한 삶을 추구합니다.</P> <br>
   <a href="#" style=" float:left; margin-left: 50px; color: white; text-align: center; line-height: 35px; vertical-align: middle; text-decoration: none; width: 100px; height: 35px;  font-size: 14px; font-weight: bold; background-color: rgb(22, 160, 133); margin-top: 15px;">VIEW MORE</a> <br>
   
   <p style="font-size: 30px; font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  margin-top: 150px; margin-left: 50px;">
   <strong>  Planterior </strong> <br></p>
  <P style="font-size: 20px; font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    margin-top: 20px; margin-left: 50px;">
  플랜테리어는 식물(plants)과 인테리어(interior)의 합성어로 식물을 활용한 인테리어를 의미합니다.<br>
  플랜테리어는 '어떤 공간'에 '어떤 식물'을 '어떻게 배치' 하고 '어떻게 관리' 에 따라 다른 무드를 저희와 함께 만들어가세요.</P> <br>
  <a href="#" style="float:left; margin-left: 50px; margin-bottom: 100px; color: white; text-align: center; line-height: 35px; vertical-align: middle; text-decoration: none; width: 100px; height: 35px;  font-size: 14px; font-weight: bold; background-color: rgb(22, 160, 133); margin-top: 15px;">VIEW MORE</a> <br>

  </div>
</template>

<script>
import UserService from '../services/user.service';
export default {
  name: 'HomeView',
  data() {
    return {
      content: '',
      banner1: require(`@/assets/img/banner1.png`),
      // banner2: 'banner2.jpg',
      banners: [require(`@/assets/img/banner1.png`), require(`@/assets/img/banner2.jpg`)],
    }
  },
  methods: {
    callFunction: function() {
      let i = 0;
      setInterval(() => {
        this.banner1 = this.banners[i];
        if(i === this.banners.length-1) {
          i = 0;
        } else {
          i++;
        }
      }, 4000)}

     
  },
  created() {
    
    this.callFunction()
  },

  mounted() {
    UserService.getPublicContent().then(
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
}
  

</script>

<style scoped>
  /* Slideshow container */
.slideshow-container {
    max-width: 1000px;
    position: relative;
    margin-left: 50px;
    margin-top: 120px;
    margin-bottom: 200px;
  }
  
  /* effect */
  .fade {
    -webkit-animation-name: fade;
    -webkit-animation-duration: 2s;
    animation-name: fade;
    animation-duration: 2s;
  }
  
  @-webkit-keyframes fade {
    from {opacity: .4} 
    to {opacity: 1}
  }
  
  @keyframes fade {
    from {opacity: .4} 
    to {opacity: 1}
  }
</style>


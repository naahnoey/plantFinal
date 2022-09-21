<template>
    <div>
        <body>

<div class="container">
  <p style="margin-top: 30px; font-size: 25px;">Interior</p>
    <!--<h1 style="margin-left: 78%;">Product interior</h1>-->
    </div>
    <hr style="width: 1300px; margin-left: 300px;">
<div style="margin-left: 430px;  margin-top: 50px;">   
<table cellpadding="55">
<tr>
<td>
 <!-- <strong p class="lead text-muted">Araucaria heterophylla</strong></p><br>-->
    <img :src="require(`../assets/interior_img/${interior1}.jpg`)" alt="인테리어1" style="height: 600px; width: 400px;" @click="nowRepairing"><br><br>
        <div class="card-body">
            <strong p class="card-text"> Araucaria heterophylla</strong>
            <div class="d-flex justify-content-between align-items-center">
              <!--<div class="btn-group">
              <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
            </div>-->
        </div>
      </div></td>
<td>
   <img :src="require(`../assets/interior_img/${interior2}.png`)" alt="인테리어2" style="height: 600px; width: 400px;" @click="nowRepairing"><br><br>
        <div class="card-body">
            <p><strong p class="card-text"> Olea europaea L.</strong></p>
            <div class="d-flex justify-content-between align-items-center">
        </div>
      </div></td>
</tr>    
</table>
</div> 


<div style="margin-left: 400px;"> 
<table cellpadding="75">
<tr>
<td>
  <div class="container">
      <img :src="require(`../assets/interior_img/${interior3}.png`)" alt="인테리어3" style="height: 600px; width: 400px;" @click="nowRepairing"><br><br>
          <div class="card-body">
            <p><strong p class="card-text"> Myrtillocactus geometrizans</strong></p>
            <div class="d-flex justify-content-between align-items-center">
            </div>
          </div> </div> </td>

<td>
<div class="container">
      <img :src="require(`../assets/interior_img/${interior4}.png`)" alt="인테리어2" style="height: 600px; width: 400px; margin-left: -60px;" @click="nowRepairing"><br><br>
          <div class="card-body">
            <p><strong p class="card-text"> Cereus jamacaru</strong></p>
            <div class="d-flex justify-content-between align-items-center">
            </div>
          </div> </div>
        </td>
        </tr>
</table>
</div>




 <!-- 페이징 -->
 
</body>
    </div>
</template>

<script>
    export default {
        name: 'interior-view',
        data() {
            return {
                interior1: "qqq",
                interior2: "in1",
                interior3: "in2",
                interior4: "in3",
            }
        },
        methods: {
            nowRepairing(){
                alert("해당 페이지는 현재 준비 중입니다.")
            }
        }
    }
</script>

<style scoped>
    img:hover {
        width:200px;
        transform:scale(1.05);
        transition: transform.10s;
        }

    img {
        cursor: pointer;
    }

    @import "../css/main_customer.css";
</style>
<template>
   <section>
      <div id="a1">
         <img src="../assets/chatbotstart.png" id="image"><br>
         <textarea id="start" v-model="mytext"/><br>
         <textarea id="content" rows=20></textarea><br>                
         <input type="text" id="queryin" > 
         <button id="query">질문</button> 
      </div>
   </section>
</template>
<script>
import $ from 'jquery';
$(function() { 
        $("#query").click( function() {
           /*$("#content").append($("#queryin").val());*/
            $.ajax( {
             type: "post",
             dataType: "text",
             async: false,
             url: "http://101.101.209.116:8090/api/chatbot/chatbot/",
             data: 
            {
                query:$('#queryin').val()
                  },
               success: function(data) {
               console.log(data);
               var spt = data.split('http');
               /* console.log(spt); */
               document.getElementById('image').src= "http"+spt[1];
                  $("#content").append("\n");
                  $("#content").append(spt[0]);
                  $("#content").append("\n");
                  $("#content").append("\n");
                  $("#content").append("======================================");
                  $("#content").append("\n");
               }   
           });           
        });  
     }) 
     export default {
      data(){
         return {
            mytext:'안녕하세요.\n트레플 챗봇에 오신 것을 환영합니다.\n\n아래 채팅창을 통해 식물과 관련된 정보를 검색하실 수 있습니다.\n\nex) 동백나무, 고무나무 정보, 공중식물'
         }
      }
   }
</script>

<style>
#a1{
   margin: auto;
   text-align: center;
   margin-top: 50px;
}
#image{
   width: 450px;
   height: 250px;
}

#start{
   width: 450px;
   height: 175px;
}

#content{
   width: 450px;
   height: 380px;
}

#queryin {
   width: 370px;
   height: 33px;
}

#query{
   background-color:rgb(22, 160, 133);
    border: none;
    color: white;
    padding: 10px;
    border-radius: 8px;
    margin-top: 10px;
    height: 40px;
    width: 70px;
    margin-left: 10px;
}
</style>
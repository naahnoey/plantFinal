<template>
    <div>
    


    </div>
</template>

<script>

export default {
    data(){
        return{
            banner1: 'banner1',
            banner2: 'banner2',
        }
    },
    methods: {
//         function prevSlide() {
//       $(".mySlideDiv").hide(); //모든 div 숨김
//       var allSlide = $(".mySlideDiv"); //모든 div 객체를 변수에 저장
//       var currentIndex = 0; //현재 나타난 슬라이드의 인덱스 변수
      
//       //반복문으로 현재 active클래스를 가진 div를 찾아 index 저장
//       $(".mySlideDiv").each(function(index,item){ 
//           if($(this).hasClass("active")) {
//               currentIndex = index;
//           }
          
//       });
      
//       //새롭게 나타낼 div의 index
//       var newIndex = 0;
      
//       if(currentIndex <= 0) {
//           //현재 슬라이드의 index가 0인 경우 마지막 슬라이드로 보냄(무한반복)
//           newIndex = allSlide.length-1;
//       } else {
//           //현재 슬라이드의 index에서 한 칸 만큼 뒤로 간 index 지정
//           newIndex = currentIndex-1;
//       }
  
//       //모든 div에서 active 클래스 제거
//       $(".mySlideDiv").removeClass("active");
      
//       //새롭게 지정한 index번째 슬라이드에 active 클래스 부여 후 show()
//       $(".mySlideDiv").eq(newIndex).addClass("active");
//       $(".mySlideDiv").eq(newIndex).show();
  
//   }
  
//   //다음 슬라이드
//   function nextSlide() {
//       $(".mySlideDiv").hide();
//       var allSlide = $(".mySlideDiv");
//       var currentIndex = 0;
      
//       $(".mySlideDiv").each(function(index,item){
//           if($(this).hasClass("active")) {
//               currentIndex = index;
//           }
          
//       });
      
//       var newIndex = 0;
      
//       if(currentIndex >= allSlide.length-1) {
//           //현재 슬라이드 index가 마지막 순서면 0번째로 보냄(무한반복)
//           newIndex = 0;
//       } else {
//           //현재 슬라이드의 index에서 한 칸 만큼 앞으로 간 index 지정
//           newIndex = currentIndex+1;
//       }
  
//       $(".mySlideDiv").removeClass("active");
//       $(".mySlideDiv").eq(newIndex).addClass("active");
//       $(".mySlideDiv").eq(newIndex).show();
//  } 
 
  }
    
}

// 아래는 JQUERY 코드.

  
</script>

<style scope>
    /* 사용자 메인 css 파일 */

#categori { /*categori의 ID를 가진 태그의 스타일 지정*/
    height: 40px; /* categori의 높이를 30px로 설정(제대로 설정 안하면 아래 내용이 깨짐) */
    width: 2000px; /* categori의 넓이를 850px로 설정(제대로 설정 안하면 브라우져 넓이가 좁아지면 깨짐) */
}

#categori ul li {  /* categori의 ID를 가진 태그 안에 <ul> 태그 안에 <li> 태그의 스타일을 지정 */
    list-style: none; /* <li> 태그는 위의 이미지처럼 목록을 나타내는 점을 없앤다 */
    color : black; /* 글씨 색을 흰색으로 설정 */
    background-color: rgb(224, 224, 224); /* 배경색을 회색 RGB(224,224,224)으로 설정 */
    float: left; /* <li>태그들이 왼쪽에 흐르게 설정(그러면 아래있는 내용은 오른쪽으로 옴) */
    line-height: 40px; /* 글씨가 가운데로 오도록 설정하기 위해 한줄의 높이를 30px로 설정 */
    vertical-align: middle; /* 세로 정렬을 가운데로 설정(위의 line-height와 같이 설정 필요함) */
    text-align: center; /* 글씨 정렬을 가운데로 설정 */
}

#categori .menuLink { /* categori 아이디를 가진 태그 안에 있는 menuLink 클래스 태그들의 스타일 설정 */
    text-decoration: none; /* 링크(<a> 태그)가 가지는 기본 꾸밈 효과(밑줄 등)을 없앰 */
    color: black; /* 폰트색을 검정으로 설정  */
    display: block; /* 링크를 글씨가 있는 부분 뿐만아니라 전체 영역을 클릭해도 링크가 걸리게 설정 */
    width: 150px; /* 메뉴링크의 넓이 설정 */
    font-size: 14px; /* 폰트 사이즈 14px로 설정 */
    font-weight: bold; /* 폰트를 굵게 */
    font-family:"Trebuchet MS", Dotum, Arial; /* 기본 폰트 적용, 시스템 폰트를 종류별로 순서대로 */
}

#categori .menuLink:hover { /* categori 아이디를 가진 태그 안에 menuLink클래스를 가진 태그에 마우스가 over되면 스타일 설정 */             
    color: rgb(22, 160, 133); /* 글씨 색을 rgb(22, 160, 133) 으로 설정 */                  
    background-color: rgb(224, 224, 224);  
}

#logo {
    text-align: center;
}

#hd_right {
    margin-right: 200px;
}

#hd_left {
    margin-top: 50px;
}

#search {
    position: relative;
    width: 300px;
    margin-top: 15px;
    margin-left: 1160px;
}

#search_img {
    position : absolute;
    width: 17px;
    top: 10px;
    right: 12px;
    margin: 0;
}

input {
    width: 100%;
    border: 1px solid #bbb;
    border-radius: 8px;
    padding: 10px 12px;
    font-size: 14px;
}
</style>


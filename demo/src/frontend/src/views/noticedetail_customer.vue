<template>
    
        <div id="notice-main" v-if="currentNotice != null">
            <h3><strong>공지사항</strong></h3>
            <br>
            
            <!-- 공지사항 상세 -->
            <table>
                <thead>
                    <tr style="background-color: rgb(224,224,224); border-top: sold 1.5px black;">
                        <th style="width: 90%;">{{currentNotice.ntitle}}</th>
                        <th style="width: 10%;"></th>
                    </tr>
                </thead>
                <tbody>
                    <tr style="text-align: right; border-top: 0.5px solid black;">
                     <td style="font-weight: bold; text-align: right; border-bottom: solid 1px lightgray;">작성일<br>
                        수정일<br>
                        조회수</td>
                        <td style="text-align: right;; border-bottom: solid 1px lightgray;">{{currentNotice.nrdate}}
                            <br>{{currentNotice.nmodifydate}}
                            <br>{{currentNotice.nhit}}
                        </td>
                        
                        
                    </tr>
                    <tr style="text-align: left;">
                        <td><pre>{{currentNotice.ncontent}}</pre>
                        </td>
                        <td></td>
                        
                    </tr>
                    
                </tbody>
            </table>

            <p id="notice-add">
                <button class="btn" @click="backtolist">목록</button>
               
            </p>

        </div>
    
</template>

<script>


import NoticeDataService from "../services/notice.service"
export default {
    name: "noticedetail_customer",
    data(){
        return {
            currentNotice: {
                nid: null,
                ntitle: "",
                ncontent: "",
                
            }
            
        }
    },
    methods: {
        getNotice(nid){
            NoticeDataService.get(nid)
            .then(response => {
            this.currentNotice = response.data;
            console.log(this.$route.params.nid);
            
            }).catch(e => {
                console.log(e);
            })
        },
        backtolist(){
            this.$router.push({ name: "notice-list"})
        }
    },
    mounted(){
        this.getNotice(this.$route.params.nid);
    }
}
</script>

<style scoped>
    #notice-main {
    width: 1100px;
    margin: auto;
    margin-bottom: 300px;
    padding-top: 70px;
}

#notice-main table {
    width: 100%;
    margin: auto;
    border-collapse: collapse;
    font-size: 14px;
    border-top: 1.5px solid black;
    
}
#notice-main thead {
    height: 40px;
    text-align: left;
}
#notice-main tbody tr {
    height: 60px;
}


/* 셀 가로 길이 설정 */
#notice-main tbody tr td:nth-child(1) { /* 구분 */
    width: 15%;
}
#notice-main tbody tr td:nth-child(2) { /* 제목 */
    width: 60%;
    text-align: left;
    padding-top: 15px;
    padding-bottom: 15px;
}
#notice-main tbody tr td:nth-child(2) a {
    text-decoration: none;
    color: black;
}
#notice-main tbody tr td:nth-child(3) { /* 작성일 */
    width: 25%;
}

/* 페이지 */
#paging {
    text-align: center;
    margin-top: 70px;
}
#paging button {
    width: 30px;
    height: 30px;
    border-radius: 10px;
    border: 0;
    background-color: rgb(240, 240, 240);
}

#notice-add {
    float: left;
    margin-left: 00px;
    margin-top: 40px;
}

#notice-add button {    /* 추가 버튼 */
    color: white;
    background-color: rgb(22, 160, 133);
    width: 70px;
    height: 40px;
}

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

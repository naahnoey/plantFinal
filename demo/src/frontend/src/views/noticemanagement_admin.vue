<template>
<div>
    <side-menu></side-menu>
    <!-- <div class="popup-view" :class="{active : popupView}">
        <pop-up @close-popup = "openPopup()"></pop-up>
    </div> -->
    <div id="notice-manager" >
        
            <!-- 검색 -->
            <p id="notice-search" style="margin-left: 850px; margin-top: 100px;" >
                <select style="border: solid rgb(224,224,224) 0.5px; margin-left: -400px;">
                    <option>제목</option>
                    <option>기간</option>
                </select>
                &nbsp;&nbsp;
                <input type="text" placeholder="검색어를 입력하세요">
                &nbsp;&nbsp;
                <button class="btn">검색</button>
            </p>

            <!-- 추가 버튼 및 팝업 내용-->
            <p id="notice-add">
                <button class="btn" @click="toNoticeform">추가</button>
             
            </p>

            <!-- 공지사항 목록 -->
            <table class="table text-center">
                <thead>
                    <tr>
                        <th>구분</th>
                        <th>제목</th>
                        <th>작성일</th>
                        <th>수정일</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(notice, index) in notices" :key="index" >
                        <td>{{index+1}}</td>
                        <td style="text-decoration: none;"><router-link :to="`/admin/notice/detail/${notice.nid}`">{{notice.ntitle}} </router-link> </td>
                        <td>{{notice.nrdate}}</td>
                        <td style="border-bottom: solid 1px lightgray">
                            {{notice.nmodifydate}}
                        </td>
                    </tr>
                   
                </tbody>
            </table>

            <!-- 페이지 기능 -->
           <pagination :pageSetting="pageDataSetting(total, limit, block, this.page)" @paging="pagingMethod" />
        </div>
        
</div>
</template>

<script>
import sidemenuAdmin from '../views/sidemenu_admin';
// import popUp from '../components/noticeadd_admin';
import NoticeDataService from '../services/notice.service';
export default {
    


  name: "notice-management-admin",
  data() {
    return {
            notices: [],
            currentNotice: null,
            currentIndex: -1,
            nid: null,
            
            }
            
    
        
        },
  
  components: {
            'side-menu': sidemenuAdmin,
            // 'pop-up': popUp
        },  
  methods: {
        toNoticeform(){
            this.$router.push({name: 'noticeadd-admin'});
        },

        listNotice(){
            NoticeDataService.getAll()
            .then(response => {
                this.notices = response.data;
                console.log(response.data);
            }).catch(e => {
                console.log(e);
            })
        },
        pagingMethod(page){
            this.notices = this.notice.slice(
                (page - 1) * this.limit,
                page * this.limit
            )
            this.page = page
            this.pageDataSetting(this.total, this.limit, this.block, page)
        },

        pageDataSetting(total, limit, block, page){
            const totalPage = Math.ceil(total/limit)
            let currentPage = page
            const first = currentPage > 1 ? parseInt(currentPage, 10) - parseInt(1,10) : null
            const end = totalPage !== currentPage
            ? parseInt(currentPage, 10) + parseInt(1,10)
            : null

            let startIndex = (Math.ceil(currentPage / block) - 1) * block + 1
            let endIndex = startIndex + block > totalPage ? totalPage : startIndex + block - 1
            let list = []
            for (let index = startIndex; index <= endIndex; index++){
                list.push(index)

            }
            return (first, end, list, currentPage)
        }
  
        


        },
    mounted(){
    this.listNotice();
    this.pagingMethod(this.page);
    }
}
    // noticeadd_admin(){
    //      window.open("noticeadd_admin.html","a", "width=1080px, height=900px, left=100, top=50");
    // }

    // saveTutorial() {
    //   var data = {
    //     title: this.tutorial.title,
    //     description: this.tutorial.description
    //   };
    //   TutorialDataService.create(data)
    //     .then(response => {
    //       this.tutorial.id = response.data.id;
    //       console.log(response.data);
    //       this.submitted = true;
    //     })
    //     .catch(e => {
    //       console.log(e);
    //     });
    // },
    
    // newTutorial() {
    //   this.submitted = false;
    //   this.tutorial = {};
    // }
   

   
</script>

<style scoped>
    /* 공지사항(관리자) */
#notice-manager {
    width: 1500px;
    min-height: 500px;
    margin: auto;
    text-decoration: none;
    margin-bottom: 100px;
    margin-left: 200px;
    padding:1px 16px;
}

#notice-search {    /* 검색칸 화면 가운데 배치 */
    
    width: 100px;
}
#notice-search input {
    width: 300px;
}

#notice-manager button {    /* 추가 버튼을 제외한 모든 버튼 */
    background-color: lightgray;
}

#notice-add button {    /* 추가 버튼 */
    color: white;
    background-color: rgb(22, 160, 133);
    width: 70px;
    height: 40px;
}
#notice-add {
    float: right;
}

/* 테이블 설정 */
#notice-manager thead {
    height: 40px;
    background-color: rgb(224, 224, 224);
}
#notice-manager tbody tr td {
    height: 60px;
    vertical-align: middle;
}

/* 셀 가로 길이 지정 */
#notice-manager tbody tr td:nth-child(1) {  /* 구분 */
    width: 10%;
}
#notice-manager tbody tr td:nth-child(2) {  /* 제목 */
    width: 45%;
    text-align: left;
}
#notice-manager tbody tr td:nth-child(2) span {
    margin-left: 5%;
}
#notice-manager tbody tr td:nth-child(3) {  /* 기간 */
    width: 25%;
}
#notice-manager tbody tr td:nth-child(4) {  /* 수정, 삭제 */
    width: 20%;
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

#logo {
    text-align: center;
}

#hd_right {
    margin-right: 200px;
}


#side_list ul {

    list-style : none;
    background-color: rgb(22, 160, 133);
    width: 150px;
    padding: 0;
    margin: 0;
    position: fixed;
    height: 260px;
    overflow: auto;

}

#side_list ul li a {
    text-decoration: none;
    display: block;
    color: white;
    padding: 8px 15px 8px 15px;
    font-weight: bold;
    font-size: 14px;
}

#side_list ul li a.side_active {
    background-color: rgb(241, 196, 15);
    color: white;
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

.img-size{
    width: 60px;
    height: 60px;
}

#order-manager tr td {
    vertical-align: middle;
}
.popup-view {
  opacity: 0;
  display: none;
  visibility: hidden;
}
.active{
  opacity: 1;
  display: block;
  visibility: visible;
}
</style>

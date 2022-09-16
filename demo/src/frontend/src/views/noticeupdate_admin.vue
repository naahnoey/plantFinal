<template>

<div>
<side-menu></side-menu>
<div id="root" class="root" v-if="currentNotice">
 
    <div>
    <div class="title"> 
        <h2> 공지사항 </h2>
        <hr>
    </div>
    
    
    <table>
        <thead>
            <th style="width: 100px;"> </th>
            <th style="width: 300px;"> </th>
        </thead>
        <tbody>
            <tr>
                <td>제목</td>
                <td> <textarea required v-model="currentNotice.ntitle" id="ntitle" cols="130" rows="1"></textarea> </td>
            </tr>
            <tr>
                <td>내용</td>
                <td> <textarea required v-model="currentNotice.ncontent" id="ncontent" cols="130" rows="35"></textarea> </td>
            </tr>
        </tbody>
    </table>
    <hr>
   

<button type="button" id="createInquiry" class="reviewButton" @click="updateNotice">수정하기</button>
<button type="button" id="cancelInquiry" class="reviewButton" @click="backtoList">취소하기</button>
    </div>
     
</div>
</div>
</template>

<script>
    import NoticeDataService from '../services/notice.service';
    import sideMenu from '../views/sidemenu_admin.vue'

    export default {
        name: "notice-add",
        data(){
            return {
                currentNotice: {
                    nid: null,
                    ntitle:"",
                    ncontent:"",
                    nfile: "",
                    nrdate: null,
                    nmodifydate: null,
                    nhit: 0
                }
               
            }
        },
        components: {
            'side-menu': sideMenu
        },
     
        methods: {
            getNotice(nid){
             
                NoticeDataService.update(nid)
                .then(response => {
                    this.currentNotice = response.data;
                    
                }).catch(e => console.log(e));
                
            },
            updateNotice(){
                NoticeDataService.updateNotice(this.currentNotice.nid, this.currentNotice)
                .then(response => {
                    console.log(response.data);
                    alert("공지가 수정되었습니다.");
                    this.$router.go(-1);
                }).catch(e => {
                    console.log(e);
                })
            },
            backtoList(){
                this.$router.go(-1);
            }
        },
        mounted(){
            this.getNotice(this.$route.params.nid);
        }
    }
</script>

<style scoped>
        body {
    margin:0 auto;
    padding: 0;
}

.root {
    width: 1050px;
    height: 880px;
    background-color: white;
    margin-left: 400px;
}

.product-info {
    font-weight: bold;
    
}
.product-pic {
    display: inline-block;
    position: absolute;
}
.product-detail {
    display: inline-block;
    margin-left: 120px;
}
#direction {
    margin-left: 20px;
    font-size: 12px;
    margin-right: 20px;
}

.reviewButton {
    background-color:rgb(22, 160, 133);
    border: none;
    color: white;
    padding: 10px;
    border-radius: 8px;
    margin-left: 260px;
    width: 120px;
}

textarea {
    
    border: 1px solid #bbb;
    border-radius: 8px;
    padding: 10px 12px;
    font-size: 11px;
}

</style>

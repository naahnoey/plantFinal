<template>
    <div>
    <side-menu></side-menu>
        <div> 
            <h2 style="text-align:left;margin-left:500px;"> FAQ 수정 </h2>
            <hr style="width:50%;margin-left:500px;">
        </div>
    <table id="table">
        <tbody>
            <tr>
                <th>질문 </th>
                <!-- <td> <textarea required v-model="currentFaq.ftitle" id="ftitle" cols="130" rows="1"></textarea> </td> -->
                <td> <input type="text" required v-model="currentFaq.ftitle" style="width:500px;" id="ftitle" placeholder="질문을 입력하세요"/></td>
            </tr>
            <tr>
                <th>답변</th>
                <td> <textarea required v-model="currentFaq.fcontent" style="width:500px;" id="fcontent"  rows="15" placeholder="답변을 입력하세요"></textarea> </td>
            </tr>
        </tbody>
    </table>
<button style="margin-left:850px;" class="button1" @click="updateFaq()">수정하기</button>
<button class="button1" @click="cancel()">취소하기</button> 
</div>
</template>
<script>
    import sidemenuAdmin from '../views/sidemenu_admin';
    import FaqDataService from '../services/FaqDataService';
    export default {
        name: "faq-update",
        data(){
            return {
                currentFaq: {
                    fid: null,
                    ftitle: "",
                    fcontent: "",
                    wdate: null,
                }
            }
        },
        components: {
            'side-menu': sidemenuAdmin,
            // 'pop-up': popUp
        }, 
        methods: {
            getFaq(fid){   
                FaqDataService.update(fid)
                .then(response => {
                    this.currentFaq = response.data;
                }).catch(e => console.log(e));
            },
            updateFaq(){
                FaqDataService.updateFaq(this.currentFaq.fid, this.currentFaq)
                .then(response => {
                    console.log(response.data);
                    alert("FAQ가 수정되었습니다.");
                    this.$router.push("/admin/faq").catch(()=>{}); 
                }).catch(()=>{}); 
            },
            cancel(){
                this.$router.push("/admin/faq").catch(()=>{}); 
            }
        },
        mounted(){
            this.getFaq(this.$route.params.fid);
        }
    }
</script>
<style>
#table {
    width: auto;
    margin-left: 700px;
}

input{
    border: 1px solid #bbb;
    border-radius: 8px;
    font-size: 11px;
    height: 30px;
}
textarea {
    border: 1px solid #bbb;
    border-radius: 8px;
    font-size: 11px;
}
.button1{
    background-color:rgb(22, 160, 133);
    border: none;
    color: white;
    padding: 10px;
    border-radius: 8px;
    margin-left: 50px;
    width: 120px;
}
</style>
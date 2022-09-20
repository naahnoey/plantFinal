<template>
    <div>
    <side-menu></side-menu>
    
        <div> 
            <h2 style="text-align:left;margin-left:250px;"> FAQ 등록 </h2>
            <hr style="width:50%;margin-left:250px;">
        </div>
    <table id="table" style="margin-left:250px;">
        <tbody>
            <tr>
                <th>질문 </th>
                <td id="title"> <input type="text" required v-model="faq.ftitle" style="width:500px;" id="ftitle" placeholder="질문을 입력하세요"/></td>
            </tr>
            <tr>
                <th>답변</th>
                <td id="fcontent"> <textarea required v-model="faq.fcontent" style="width:500px;" id="fcontent"  rows="15" placeholder="답변을 입력하세요"></textarea> </td>
            </tr>
        </tbody>
    </table>
<button style="margin-left:400px;" class="button1" @click="addFaq()">등록하기</button>
<button class="button1" @click="cancel()">취소하기</button> 
</div>
</template>
<script>
    import sidemenuAdmin from '../views/sidemenu_admin';
    import FaqDataService from '../services/FaqDataService';
    export default {
        name: "faq-add",
        data(){
            return {
                faq: {
                    fid: null,
                    ftitle: "",
                    fcontent: "",
                    wdate: null,
                },
                submitted: false
            }
        },
        components: {
            'side-menu': sidemenuAdmin,
            // 'pop-up': popUp
        },  
        methods: {
            addFaq(){             
                var faqData = {
                    ftitle: this.faq.ftitle,
                    fcontent: this.faq.fcontent,                    
                };
                FaqDataService.create(faqData)
                .then(response => {
                    this.faq.fid = response.data.fid;
                    this.submitted = true;
                    alert("FAQ가 작성되었습니다.");  
                    this.$router.push("/admin/faq").catch(()=>{});               
                }).catch(e => console.log(e));                
            },
            cancel(){
                this.$router.push("/admin/faq").catch(()=>{}); 
            }
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
    /* padding: 10px 12px; */
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
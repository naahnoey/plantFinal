<template>    
        <div id="faq-main" v-if="currentFaq != null">
            <h3><strong>FAQ</strong></h3>
            <br>            
            <table>
                <thead>
                    <tr style="background-color: rgb(224,224,224);">
                        <th >{{currentFaq.ftitle}}</th>
                    </tr>
                </thead>
                <tbody>
                    <tr >
                        <td style="text-align:left"><pre>{{currentFaq.fcontent}}</pre>
                        </td>
                    </tr>
                </tbody>
            </table>
            <br>
            <p id="ptag">
                <button style="float:left;" class="btn" @click="list()">목록</button>
                <button class="btn" @click="deleteFaq()" style="float:right;">삭제</button>
                <router-link :to="`/admin/faq/update/${currentFaq.fid}`" style="float:right;margin-right:10px;" class="btn" >수정</router-link>
            </p>
        </div>   
</template>

<script>
import FaqDataService from '../services/FaqDataService';
export default {
    name: "faqdetail_customer",
    data(){
        return {
            currentFaq: {
                fid: null,
                ftitle: "",
                fcontent: "",
            }
        }
    },
    methods: {
        getFaq(fid){
            FaqDataService.get(fid)
            .then(response => {
            this.currentFaq = response.data;
            }).catch(()=>{});
        },
        list(){
            this.$router.push("/admin/faq").catch(()=>{}); 
        },
        deleteFaq(){
            FaqDataService.delete(this.currentFaq.fid)
            .then(response => {
                console.log(response.data);
                alert("삭제되었습니다.")
                this.$router.push("/admin/faq").catch(()=>{}); 
            })
        }
    },
    mounted(){
        this.getFaq(this.$route.params.fid);
    }
}
</script>

<style scoped>
#faq-main {
    width: 1100px;
    margin: auto;
    margin-bottom: 300px;
    padding-top: 70px;
} 

#faq-main table {
    width: 100%;
    margin: auto;
    border-collapse: collapse;
    font-size: 14px;
    border-top: 1.5px solid black;
    
}
#faq-main thead {
    height: 40px;
    text-align: left;
}
#faq-main tbody tr {
    height: 60px;
}


/* 셀 가로 길이 설정 */
#faq-main tbody tr td:nth-child(1) { 
    width: 15%;
}
#faq-main tbody tr td:nth-child(2) { 
    width: 60%;
    text-align: left;
    padding-top: 15px;
    padding-bottom: 15px;
}
#faq-main tbody tr td:nth-child(2) a {
    text-decoration: none;
    color: black;
}
#faq-add {
    float: left;
    margin-left: 00px;
    margin-top: 40px;
}

.btn {  
    background-color:rgb(22, 160, 133);
    border: none;
    color: white;
    padding: 10px;
    border-radius: 8px;
    width: 100px;
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
</style>
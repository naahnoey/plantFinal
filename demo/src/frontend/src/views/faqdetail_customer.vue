<template>
    
        <div id="notice-main" v-if="currentFaq != null">
            <h3 style="text-align: left;"><strong>FAQ</strong></h3>
            <br>
            
            <!-- faq 상세 -->
            <table>
                <thead>
                    <tr style="background-color: whitesmoke; border-top: sold 1.5px black;">
                        <th>{{currentFaq.ftitle}}</th>
                        
                    </tr>
                </thead>
                <tbody>
                    <tr style="text-align: left;margin-top:1000px">
                        <td><pre>{{currentFaq.fcontent}}</pre>
                        </td>
                    </tr>
                </tbody>
            </table>
            <hr>
            <button class="btn" @click="list">목록</button>
        </div>
    
</template>

<script>
import FaqDataService from "../services/FaqDataService"
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
            console.log(this.$route.params.nid);
            }).catch(()=>{}); 
        },
        list(){
            this.$router.push("/faq").catch(()=>{}); 
        }
    },
    mounted(){
        this.getFaq(this.$route.params.fid);
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
#notice-main tbody tr td:nth-child(1) { 
    width: 15%;
}
#notice-main tbody tr td:nth-child(2) { 
    width: 60%;
    text-align: left;
    padding-top: 15px;
    padding-bottom: 15px;
}
#notice-main tbody tr td:nth-child(2) a {
    text-decoration: none;
    color: black;
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

.btn {    
    background-color: rgb(22, 160, 133);
    width: 70px;
    height: 40px;
    color: white;
    float: left;
} 
</style>
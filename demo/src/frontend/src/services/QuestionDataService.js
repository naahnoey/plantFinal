import http from "../http-common";

class QuestionDataService {
    getAll(pid) {  // 목록
        return http.get(`/question/${pid}`);
    }
    get(qid) {  // 문의글 상세
        return http.get(`/question/detail/${qid}`);
    }
    register(data, pid) {  // 작성
        return http.post(`/question/register/${pid}`, data);
    }
    modify(data, qid) { // 수정
        return http.put(`/question/detail/${qid}`, data);
    }
    delete(qid) {   // 삭제
        return http.delete(`/question/detail/${qid}`);
    }
}

export default new QuestionDataService();
import http from "../http-common";

class AnswerDataService {
    get(qid) {  // 답변 상세
        return http.get(`/answer/detail/${qid}`);
    }
    register(data, qid) {  // 작성
        return http.post(`/answer/register/${qid}`, data);
    }
    modify(data, qid) { // 수정
        return http.put(`/answer/detail/${qid}`, data);
    }
    delete(aid) {
        return http.delete(`/answer/detail/${aid}`);
    }
}

export default new AnswerDataService();
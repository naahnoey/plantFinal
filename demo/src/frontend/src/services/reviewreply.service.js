import http from "../http-common";
class ReviewReplyService {
 
  getAll(rid) {
    return http.get(`/auth/reviewreply/list/${rid}`);
  }
  create(reviewReplyData) {
    return http.post("/auth/reviewreply/add", reviewReplyData);
  }   
  
  modify(rrid, reviewReplyData) {
    return http.put(`/auth/reviewreply/modify/${rrid}`, reviewReplyData);
  }   
  delete(rrid) {
    return http.delete(`/auth/reviewreply/delete/${rrid}`);
  }   
    
}
export default new ReviewReplyService();
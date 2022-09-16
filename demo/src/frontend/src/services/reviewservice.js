import http from "../http-common";
class ReviewService {
  getbyId(rid) {
    return http.get(`/auth/review/detail/${rid}`);
  }
  getAll(pid) {
    return http.get(`/auth/review/list/${pid}`);
  }
  create(reviewData) {
    return http.post("/auth/review/add", reviewData);
  }   
  delbyId(rid) {
    return http.delete(`/auth/review/delete/${rid}`);
  }  
  modify(rid, review) {
    return http.put(`/auth/review/modify/${rid}`, review);
  }        
  getList() {
    return http.get("/auth/review/list");
  }                                         
   
}
export default new ReviewService();
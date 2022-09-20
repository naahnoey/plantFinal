import http from "../http-common";
class FaqDataService {
  getAll_admin() {
    return http.get("/faq/list_admin");
  }

  getAll() {
    return http.get("/faq/list");
  }

  get_admin(fid) {
    return http.get(`/faq/detail_admin/${fid}`);
  }

  get(fid) {
    return http.get(`/faq/detail/${fid}`);
  }
  create(faqData) {
    return http.post("/faq/create", faqData);
  }
  update(fid) {
    return http.get(`/faq/modify/${fid}`);
  }
  updateFaq(fid, faq) {
    return http.put(`/faq/modify/${fid}`, faq);
  }
  delete(fid) {
    return http.delete(`/faq/delete/${fid}`);
  }
 
}
export default new FaqDataService();
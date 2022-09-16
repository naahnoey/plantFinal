import http from "../http-common";
class NoticeDataService {
  getAll_admin() {
    return http.get("/auth/notice/list_admin");
  }

  getAll() {
    return http.get("/auth/notice/list");
  }

  get_admin(nid) {
    return http.get(`/auth/notice/detail_admin/${nid}`);
  }

  get(nid) {
    return http.get(`/auth/notice/detail/${nid}`);
  }
  create(noticeData) {
    return http.post("/auth/notice/register", noticeData);
  }
  update(nid) {
    return http.get(`/auth/notice/modify/${nid}`);
  }
  updateNotice(nid, notice) {
    return http.put(`/auth/notice/modify/${nid}`, notice);
  }
  delete(nid) {
    return http.delete(`/auth/notice/delete/${nid}`);
  }
 
}
export default new NoticeDataService();
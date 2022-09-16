import http from "../http-common";
class AddressService {
  

  getAll(username) {
    return http.get(`/auth/address/list/${username}`, username);
  }
  get(aid) {
    return http.get(`/auth/address/detail/${aid}`);
  }
  create(addressData) {
    return http.post("/auth/address/add", addressData);
  }
  update(aid) {
    return http.get(`/auth/address/modify/${aid}`);
  }                                             
  updateNotice(aid, address) {
    return http.put(`/auth/address/modify/${aid}`, address);
  }
  delete(aid) {
    return http.delete(`/auth/address/delete/${aid}`);
  }
 
}
export default new AddressService();
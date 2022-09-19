import http from "../http-common";
class UserService {
  getAll() {
    return http.get("/auth/user");
  }
  getbyUsername(username) {
    return http.get(`/auth/user/detail/${username}`)
  }
  getbyId(id) {
    return http.get(`/auth/user/detail/${id}`)
  }
 
   
}
export default new UserService();
import http from "../http-common";
class OrdersService {
  
  getAll(username) {
    return http.get(`/orders/list/${username}`, username);
  }
  create(orderData) {
    return http.post("/addorder", orderData);
  }
  getAll_admin(){
    return http.get(`/orders/list`);
  }
  update(oid) {
    return http.get(`/order/modify/${oid}`);
  }   
  updateostatus(oid, order){
    return http.put(`/order/modify/${oid}`, order);
  }
 
}
export default new OrdersService();
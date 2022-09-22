import axios from "axios";
export default axios.create({
  baseURL: "http://49.50.165.169:8090/api",
  headers: {
    "Content-type": "application/json"
  }
});

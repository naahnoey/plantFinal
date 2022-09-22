import axios from "axios";
export default axios.create({
  baseURL: "http://101.101.209.116:8090/api",
  headers: {
    "Content-type": "application/json"
  }
});

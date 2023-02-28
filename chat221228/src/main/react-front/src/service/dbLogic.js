import axios from "axios";

export const jsonDeptList = (params) => {
   return  new Promise((resolve, reject) => {
      try {
         const response = axios({
            method: "GET"
            , url: process.env.REACT_APP_CHAT221228_IP + "dept/jsonDeptList.st1"
            , params: params
         })
         resolve(response);
      } catch (error) {
         reject(error);
      }
   });
}


/*  
rafce 단축어
-> arrow function export default
*/
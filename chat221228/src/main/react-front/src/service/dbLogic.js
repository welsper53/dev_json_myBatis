import axios from "axios";

export const boardInsertDB = (board) => {
   return new Promise((resolve, reject) => {
      try {
         const response = axios({
         method: "post",
         url: process.env.REACT_APP_CHAT221228_IP + "board3/boardInsert.st3",
         headers: {
            "Content-Type": "multipart/form-data",
         },
         data: board,
         });
         resolve(response);
      } catch (error) {
         reject(error);
      }
   });
};

/* 톰캣서버와 연결하기 위한 */
export const boardListDB = (board) => {
   return new Promise((resolve, reject) => {
      try {
         const response = axios({
         method: "get",
         url: process.env.REACT_APP_CHAT221228_IP + "board3/jsonBoardList.st3",
         params: board,
         });
         resolve(response);
      } catch (error) {
         reject(error);
      }
   });
};

export const uploadImageDB = (file) => {
   console.log(file);
   return new Promise((resolve, reject) => {
      try {
         const response = axios({
         method: "post",
         url: process.env.REACT_APP_CHAT221228_IP + "board3/imageUpload.st3",
         headers: {
            "Content-Type": "multipart/form-data",
         },
         processData: false,
         contentType: false,
         data: file,
         });
         resolve(response);
      } catch (error) {
         reject(error);
      }
   });
};

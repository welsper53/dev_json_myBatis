import React from 'react';
import axios from 'axios';
import styled from 'styled-components';

const Dspan = styled.span`
  padding: 2px 5px 2px 5px;
  font-size: 14px;
  cursor: pointer;
  &:hover {
    border-bottom: 1px solid gray;
  }
`
const BoardFileDetail = ({files}) => {
  console.log(files[0]);
  const download = () => {
    axios({
      method: 'GET',
      url: process.env.REACT_APP_CHAT221228_IP+`board3/imageDownload.st3?imageName=${files[0]}`,                 
      responseType: 'blob' 
    }).then(response =>{        
      const url = window.URL.createObjectURL(new Blob([response.data], 
        { type: response.headers['content-type'] }));
      const link = document.createElement('a');
      link.href = url;
      //link.setAttribute('download', 'img.jpg');
      link.setAttribute('download', `${files[0]}`);
      document.body.appendChild(link);
      link.click();
    })    
  }
  return (
    <div style={{display:'block', border:'1px solid lightGray', borderRadius:'10px', minHeight:'60px', padding:'5px'}}>
    <div style={{textAlign:"left", padding: "2px 5px 2px 5px"}}>첨부파일</div>
      {
          <div>
            <Dspan type='text' id='fileUpload'
              onClick={download}
            >
              {files[0]}
            </Dspan>
          </div>
      }
    </div>
  );
};

export default BoardFileDetail;
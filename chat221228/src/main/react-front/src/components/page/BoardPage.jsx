import React, { useEffect,useState } from 'react'
import { Button, Table } from 'react-bootstrap'
import {  useNavigate } from 'react-router-dom'
import Bottom from '../include/Bottom'
import Header from '../include/Header'
import "../css/board.css"
import BoardRow from '../board/BoardRow'
import { boardListDB } from '../../service/dbLogic'

//스프레드연산자로 authLogic 프롭스 받기
const BoardPage = ({authLogic}) => {
  console.log('BoardPage App')
  const [boards,setBoards]=useState([
    //오브젝트 앞 라벨=>리스트, 맵 형태로 받았을 때 패턴 연습

  ])
  //SinglePageApplication컨벤션을 위한 훅
  const navigate=useNavigate()
  const onLogout=()=>{
    console.log("BoardPage onLogout호출")
    authLogic.logout()
  } 
  useEffect(()=>{
    console.log('pojo3서버와 통신하는useEffect호출')
    const boardList = async()=>{
      const gubun=document.querySelector("#gubun").value
      const keyword = document.querySelector("#keyword").value
      const board = {
        gubun:gubun,
        keyword:keyword,
      }
      console.log(board)
      const res = await boardListDB(board)
      console.log(res.data)
      const list =[]
      res.data.forEach((onerow)=>{
        const obj = {
          BM_NO:onerow.BM_NO,
          BM_TITLE:onerow.BM_TITLE,
          BM_WRITER:onerow.BM_WRITER,
        }
        //배열에 넣는 함수 push
        list.push(obj)
      })
      //담기
      setBoards(list)
    }//end of boardList
    boardList()//boardList호출
  },[boards])//end of useEffect
  //빈배열이면 최초 한번만 실행 여기선 조회 결과가 달라질 때마다 호출되어야한다

  useEffect(()=>{
    authLogic.onAuthChange(user=>{
      if(!user){
        navigate("/")
      }
    })
  })
  //조건 검색 구현
  const reactSearch=()=>{

  }
  //전체 검색 구현
  const boardList=()=>{

  }
  return (
    <>
      <Header onLogout={onLogout}/>
      <div className="container">
      <div className="page-header">
        <h2>공지관리 <small>글목록</small></h2>
        <hr />
      </div>

      <div className="row">
        <div className="col-3">
          <select id="gubun" className="form-select" aria-label="분류선택">
            <option defaultValue>분류선택</option>
            <option value="bm_title">제목</option>
            <option value="bm_writer">작성자</option>
            <option value="bm_content">내용</option>
          </select>
        </div>
        <div className="col-6">
          <input type="text" id="keyword" className="form-control" placeholder="검색어를 입력하세요" 
                aria-label="검색어를 입력하세요" aria-describedby="btn_search" />
        </div>
        <div className="col-3">
          <Button variant='danger' id="btn_search" onClick={reactSearch}>검색</Button>
        </div>
      </div>

      <div className='board-list'>
        <Table striped bordered hover>
          <thead>
            <tr>
              <th>#</th>
              <th>제목</th>
              <th>작성자</th>
            </tr>
          </thead>
          <tbody>
            {boards && boards.map((board,index)=>(
              <BoardRow key={index} board={board}/>
            ))}
          </tbody>
        </Table> 
        <hr />    
        <div className='boardlist-footer'>
          <Button variant="warning" onClick={boardList}>
            전체조회
          </Button>&nbsp; 
          <Button variant="success" onClick={()=>{navigate(`/board/write`)}}>
            글쓰기
          </Button> 
        </div>
      </div>
    </div>
    <Bottom/>
  </>
  )
          }

export default BoardPage

import React, { useEffect, useState } from 'react'
import { Table } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom'
import Bottom from '../include/Bottom'
import Header from '../include/Header'

const DeptPage = ({authLogic}) => {
  // Single Page Application컨벤션을 위한 훅
   const navigate = useNavigate()
   const onLogout = () => {
      console.log("Board onLogout called")
      authLogic.logout()
   }

   const [depts, setDepts] = useState([
      {deptno:10, dname:"개발1팀",loc:"인천"},
      {deptno:20, dname:"개발2팀",loc:"경기"},
      {deptno:30, dname:"개발3팀",loc:"강원"},
   ]);
   useEffect(() => {
      setDepts(depts)
      console.log(depts)
      /* 옵션에 별도의 값을 지정하지 않으면 최초 한번만 실행된다 */

      authLogic.onAuthChange(user => {
         if(!user) {
            navigate("/")
         }
      })
   },[]);
   return (
      <>
         <Header onLogout={onLogout}/>
         <div>부서관리 페이지</div>
         <div className="dept-list">
         <Table striped bordered hover>
         <thead>
         <tr>
            <th>#</th>
            <th>부서번호</th>
            <th>부서명</th>
            <th>지역</th>
         </tr>
         </thead>
         <tbody>
         {Object.keys(depts).map(key => (

         <tr key={depts[key].deptno}>
            <td>{depts[key].deptno}</td>
            <td>{depts[key].dname}</td>
            <td>{depts[key].loc}</td>
         </tr>
         ))
      }
         </tbody>
      </Table>
         </div>
         <Bottom/>
      </>
   )
}

export default DeptPage

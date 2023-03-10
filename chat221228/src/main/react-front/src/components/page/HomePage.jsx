import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import { useNavigate } from 'react-router-dom'
import Bottom from '../include/Bottom'
import Header from '../include/Header'

// 로그아웃 처리를 위해서 props에 authLogic 주소번지를 받아온다
const HomePage = ({authLogic}) => {
   let { userId } = useParams();
   console.log(userId);

     // Single Page Application컨벤션을 위한 훅
   const navigate = useNavigate()
   const onLogout = () => {
      console.log("Board onLogout called")
      authLogic.logout()
   }

   useEffect(() => {
      authLogic.onAuthChange(user => {
         if(!user) {
         navigate("/")
         }
      })
   })

   return (
      <React.Fragment>
         <Header onLogout={onLogout}/>
      <div>
         HomePage 페이지
      </div>
      <Bottom/>
      </React.Fragment>
   )
}

export default HomePage

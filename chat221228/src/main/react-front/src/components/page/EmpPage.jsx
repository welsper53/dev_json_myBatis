import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import Bottom from '../include/Bottom'
import Header from '../include/Header'

const EmpPage = ({authLogic}) => {
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
         사원관리 시스템
         <Bottom />
      </React.Fragment>
   )
}

export default EmpPage

import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import Bottom from '../include/Bottom'
import Header from '../include/Header'

const BoardPage = ({authLogic}) => {
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
    <>{/* div로 놔두면 div안에 div가 또 생기기 때문에 불필요한 div를 없애줌 */}
      <Header onLogout={onLogout} />
        BoardPage
      <Bottom />
    </>
  )
}

export default BoardPage

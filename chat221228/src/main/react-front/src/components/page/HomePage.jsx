import React from 'react'
import { useParams } from 'react-router-dom'
import Bottom from '../include/Bottom'
import Header from '../include/Header'

// 로그아웃 처리를 위해서 props에 authLogic 주소번지를 받아온다
const HomePage = ({authLogic}) => {
   let { userId } = useParams();
   console.log(userId);

   const onLogout = () => {
      authLogic.logout();
   }

   return (
      <React.Fragment>
         <Header userId={userId} onLogout={onLogout}/>
      <div>
         HomePage 페이지
      </div>
      <Bottom/>
      </React.Fragment>
   )
}

export default HomePage

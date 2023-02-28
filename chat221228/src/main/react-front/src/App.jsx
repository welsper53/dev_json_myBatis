import { Route, Routes } from 'react-router-dom';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';

// import FireDeptPage from './components/page/FireDeptPage';
import LoginPage from './components/login/LoginPage';
import DeptPage from './components/page/DeptPage';
import EmpPage from './components/page/EmpPage';
import HomePage from './components/page/HomePage';

// index.js에서 브라우저 라우터로 감싸진 App태그 속성값으로 넘어온 주소번지를 받는다
const App = ({authLogic}) => {
  // 사용자 정의 컴포넌트에서 return 다음에 오는 코드가 element의 집합
  // Router 이용하면 SPA(Single Page Application) 누릴 수 있다
  return (
    <>
      <Routes>
        <Route path='/' exact={true} element={<LoginPage authLogic={authLogic}/>} />
        <Route path='/home/:userid' exact={true} element={<HomePage authLogic={authLogic}/>} />
        {/* <Route path='/dept/:id' exact={true} element={<FireDeptPage/>}/> */}
        <Route path='/dept/:id' exact={true} element={<DeptPage/>} />
        <Route path='/emp' exact={true} elemnet={<EmpPage/>} />
      </Routes>
    </>
  );
}

export default App;

/* 
라우터 설정을 여기서 한다
*/
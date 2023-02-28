import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import { BrowserRouter } from 'react-router-dom';
import AuthLogic from './service/authLogic';
import firebaseApp from './service/firebase';

// 공통코드 -> service>authLogic.js -> import외부 js재사용 가능하다 
// -> export default 클래스명 -> module
// 브라우저에서 import하려면 반드시 babel이 필요하다
// 파라미터로 firebaseApp정보 얻어오기
const authLogic = new AuthLogic(firebaseApp); // 인스턴스화(파라미터가 올 수 있다)
// 왜 파라미터가 필요한가? - firebaseApp -> "import firebaseApp from './service/firebase';"
// -> export default firebaseApp
// authLogic.파이어베이스에서 제공하는 함수를 호출하겠다.

const root = ReactDOM.createRoot(document.getElementById('root'));

root.render(
  <>
    <BrowserRouter>
      {/* App 컴포넌트를 렌더링할 때 속성자리에 주소번지를 넘길 수 있다 -> props */}
      {/* 태그와 JS섞어쓰기 가능하다 -> 중괄호 사용 */}
      <App authLogic={authLogic}/>    {/* 자바에서 주소번지 넘기는것과 똑같다 */}
    </BrowserRouter>
  </>
);


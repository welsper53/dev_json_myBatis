import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import { BrowserRouter } from 'react-router-dom';
import AuthLogic from './service/authLogic';
import firebaseApp from './service/firebase';
import "@fortawesome/fontawesome-free/js/all.js";
//import SampleApp from './SampleApp';

// 공통코드 -> service>authLogic.js -> import외부 js재사용 가능하다 
// -> export default 클래스명 -> module
// 브라우저에서 import하려면 반드시 babel이 필요하다
// 파라미터로 firebaseApp정보 얻어오기
const authLogic = new AuthLogic(firebaseApp); // 인스턴스화(파라미터가 올 수 있다)
// 왜 파라미터가 필요한가? - firebaseApp -> "import firebaseApp from './service/firebase';"
// -> export default firebaseApp
// authLogic.파이어베이스에서 제공하는 함수를 호출하겠다.

// document.getElementById("root") => index.html문서에서 DOM정보 수집하는 것임
//const root = ReactDOM.createRoot(document.getElementById('root'));
const root = ReactDOM.createRoot(document.querySelector('#root'));

root.render(
  <>
    <BrowserRouter>
      {/* App 컴포넌트를 렌더링할 때 속성자리에 주소번지를 넘길 수 있다 -> props */}
      {/* 태그와 JS섞어쓰기 가능하다 -> 중괄호 사용 */}
      {/* 자바에서 주소번지 넘기는것과 똑같다 */}
      <App authLogic={authLogic}/>

      {/* <SampleApp /> */}
    </BrowserRouter>
  </>
);


/* 
  index.html에는
  <div id = "root"></div>

  index.js에서는 App컴포넌트를 BrowserRouter로 감싸준다
  왜냐하면 SPA로 처리하면서 화면 이동은 필요하기 때문에
  router를 사용합니다

  구글 로그인에 대한 처리를 담당하는 공통 코드 authLogic
  클래스를 모든 컴포넌트에서 재사용해야 하니까
  index.js(const authLogic = new AuthLogic())에서 객체생성을 하였다

  생성된 객체(AuthLogic.class)는 App컴포넌트에도 전달(props)해야 한다
  <App authLogic={authLogic} item = {item} onLogout={onLogout} />
  props에 여러가지를 담을 수 있다
  이때 props를 활용하여 부모 컴포넌트의 주소번지를
  자손 컴포넌트에 넘길 수 있다
  (주의: 자손 컴포넌트에서 부모 컴포넌트로는 불가함)

  ----------
  App.jsx
  <Routes>
    // path : URL - 파일경로
    // exact : 경로가 명확하게 정해졌을 때
    <Route path="" exact={true} element={<HomePage authLogic={authLogic}}
    <Route path="/board/"
  </Routes>

  WorkoutPage -> Workouts(배열->map, key) -> Workout(요소)
  {}
  workouts.map((item, index) => (
    <Workout workout(1개 열) = {item} />
  ))}

 */
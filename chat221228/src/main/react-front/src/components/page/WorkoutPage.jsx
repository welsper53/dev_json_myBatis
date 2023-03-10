import React, { useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import Bottom from '../include/Bottom'
import Header from '../include/Header'
import Workouts from '../workout/Workouts'

const WorkoutPage = ({authLogic, workouts, onIncrement, onDecrement, onDelete, onAdd}) => {
  // props 사용시 아래 코드로 구조 분해 할당 해야한다
  // const {auth, workouts, onIncrement} = props

  const navigate = useNavigate()
  const onLogout = () => {
    console.log("Board onLogout 호출")
    authLogic.logout()
  }

	const handleIncrement = (item) => {
    // 자바스크립트는 같은 이름의 함수를 여러개 정의 못한다
    // item은 어디서 왔을 까요?
    console.log(item)
    /*  상위 컴포넌트(App.jsx)의 함수를 호출하는 코드임
        WorkoutPage의 props에 App에서 선언된 workouts로 주소번지를 가지고 있는데
        왜? 여기서는 처리를 못하고 다시 상위 컴포넌트로 미루는 걸까요?
        setItems 훅을 사용할 수 없기 때문입니다
        setItems는 파라미터로 넘기지 않습니다. 
        왜냐하면 해당 컴포넌트의 화면 렌더링과 관련된 함수라서 그렇습니다 */
		onIncrement(item);
	}
  const handleDecrement = (item) => {
    onDecrement(item);
  }
  const handleDelete = (item) => {
    onDelete(item);
  }
  const handleAdd = (name) => {
    // 부모에 정의된 함수 호출하기 - 파라미터 값은
    onAdd(name);
  }

	useEffect(() => {
		authLogic.onAuthChange(user => {
			if(!user){
				navigate("/")
			}
		})
	})		

  return (
    <>
      <Header onLogout={onLogout} />
        <Workouts workouts={workouts} 
              onIncrement={handleIncrement}
              onDecrement={handleDecrement}
              onDelete={handleDelete}
              onAdd={handleAdd}/>
      <Bottom />
    </>
  )
}

export default WorkoutPage

/*  index.js -> <App /> : App.jsx(이벤트 처리-증가:array-> 객체)
    -> WorkoutPage.jsx
    -> Workouts.jsx -> 반복문 - map(로우 주소번지, 인덱스, 배열객체 자신
    -> Workout.jsx -. 한 개 로우만 처리
    const Workout = (props) => {
      const {workout} = props
      return (

      )
    }*/
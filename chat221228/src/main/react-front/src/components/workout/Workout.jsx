import React from 'react'

// prpps 방식
// const Workout = (props) => {
// const {workout, onIncrement} = props <- 구조분해 할당 작성

const Workout = ({workout, onIncrement, onDecrement, onDelete, onAdd}) => { // 미리 구조분해 할당으로 받아옴
  console.log(workout)

  const handleIncrement = () => {
    console.log("Workout.handleIncrement 호출")
    // 이벤트 처리가 되어 있지 않고 상위 컴포넌트의 함수를 호출
    // 상위 컴포넌트의 함수는 props를 통해서 접근 가능하다
    // 상위 함수를 호출할 때 파라미터도 넘어갑니다
    onIncrement(workout)
  }
  const handleDecrement = () => {
    console.log("Workout.handleDecrement 호출")
    onDecrement(workout)
  }
  const handleDelete = () => {
    onDelete(workout)
  }

  return (
    <>
      <li className="habit">
        <span className='habit-name'>{workout.name}</span>
        <span className='habit-count'>{workout.count}</span>
        <button className="habit-button habit-increase" onClick={handleIncrement}>
          <i className="fas fa-plus-square"></i>
        </button>
        <button className="habit-button habit-decrease" onClick={handleDecrement}>
          <i className="fas fa-minus-square"></i>
        </button>
        <button className="habit-button habit-delete" onClick={handleDelete}>
          <i className="fas fa-trash"></i>
        </button>
      </li>
    </>
  )
}

export default Workout


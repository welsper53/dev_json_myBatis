import React from 'react'
import AddForm from './AddForm'
import Workout from './Workout'

// props로 넘길 때 파라미터를 따로 작성하지 않아도 넘어간다
// - 동일한 함수 선언 불가
const Workouts = ({workouts, onIncrement, onDecrement, onDelete, onAdd}) => {
  const handleIncrement = (item) => {
    console.log('Workouts ===> ' + item)
    console.log('Workouts.handleIncrement 호출')
    onIncrement(item)
  }
  const handleDecrement = (item) => {
    onDecrement(item)
  }
  const handleDelete = (item) => {
    onDelete(item)
  }
  const handleAdd = (name) => {
    onAdd(name)
  }

  return (
    <>
      <div clasName="habit">
        <AddForm onAdd={handleAdd} />
        <ul>
          {workouts.map((item, index) => (
            <Workout key={index} workout={item} 
                  onIncrement={handleIncrement} 
                  onDecrement={handleDecrement}
                  onAdd={handleAdd}
                  onDelete={handleDelete}/>
          ))}
        </ul>
      </div>
    </>
  )
}

export default Workouts


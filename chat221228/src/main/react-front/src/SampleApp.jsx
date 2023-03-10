import React, {useState} from 'react'
import SamplePage from './sample/SamplePage'

const SampleApp = () => {
  const [num, setNum] = useState(0);

  const handleAdd = () => {
    console.log("SubPage.handleAdd 호출")
    setNum(num +1)
  }
  const handleMinus = () => {
    console.log("SubPage.handleMinus 호출")
    setNum(num <= 0 ? 0 : num -1)
  }

  return (
    <>
      <SamplePage num={num} handleAdd={handleAdd} handleMinus={handleMinus}/>
    </>
  )
}

export default SampleApp

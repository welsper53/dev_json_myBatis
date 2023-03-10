import React from 'react'
import SampleBottom from './SampleBottom'
import SampleHeader from './SampleHeader'
import SubPage from './SubPage'

const SamplePage = (props) => {

  return (
    <>
      <SampleHeader num={props.num}/>
        <div style={{border: '5px solid blue', width: '600px', height: '300px'}}>
          <SubPage num={props.num} handleAdd={props.handleAdd} handleMinus={props.handleMinus}/>
        </div>
      <SampleBottom />
    </>    
  )
}

export default SamplePage

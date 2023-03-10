import React from 'react'

const SubPage = (props) => {


  return (
    <div style={{border: '5px solid gray', width: '300px', height: '150px'}}>
      SubPage<br />
      <button onClick={props.handleAdd}>증가</button>
      &nbsp;
      <button onClick={props.handleMinus}>감소</button>
      <br />
      <br />
      <span style={{border: '1px solid gray', width: '10px', height: '10px'}}>{props.num}</span>
    </div>
  )
}

export default SubPage

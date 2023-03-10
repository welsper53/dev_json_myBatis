import React from 'react'

const SampleHeader = (props) => {

  return (
    <>
      <div style={{border: '3px solid yellowgreen', width: '600px', height: '100px'}}>
        SampleHeader페이지 영역 <br />
        <br />
        {props.num}
      </div>
    </>
  )
}

export default SampleHeader

import React from 'react'

const DeptRow = (props) => {
    console.log(props)
    const {dept}=props

  return (
    <>
      <tr>
        <td>{dept.deptno}</td>
        <td>{dept.dname}</td>
        <td>{dept.loc}</td>
      </tr>
    </>
  )
}

export default DeptRow

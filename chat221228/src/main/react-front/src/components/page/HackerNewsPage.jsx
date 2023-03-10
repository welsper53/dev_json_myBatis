import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import Bottom from '../include/Bottom'
import Header from '../include/Header'
import styled from 'styled-components'

const NewsListUL = styled.ul`
  background-color: gray;
  padding-top: 10px;
  padding-left: 6px;
  padding-right: 6px;
  padding-bottom: 18px;
`

const HackerNewsPage = ({authLogic}) => {
  // Single Page Application컨벤션을 위한 훅
  const [newsList, setNewsList] = useState([]);
  const NEWS_URL = "https://api.hnpwa.com/v0/news/1.json";
  const navigate = useNavigate()
  const onLogout = () => {
    console.log("HackerNewsPage onLogout 호출")
    authLogic.logout()
  }
  
  useEffect(() => {
    authLogic.onAuthChange(user => {
      if(!user) {
        navigate("/")
      }
    })
  })
  useEffect(() => {
    axios.get(NEWS_URL)
    .then(response => {
      console.log(response.data)
      setNewsList(response.data)
    })
  },[])
  
  return (
    <>
      <Header onLogout={onLogout} />
        <ul>
          {newsList.map((news, index) => (
            <li key={news.id}>{news.title}</li>
          ))}
        </ul> 
      <Bottom />
    </>
  )
}

export default HackerNewsPage

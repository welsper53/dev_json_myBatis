import React from 'react'
import { Button } from 'react-bootstrap';
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { Link } from 'react-router-dom';
//싱글페이지 어플리케이션으로 처리하려면 부분적 렌더링 전체 렌더링X
const Header = ({userId, onLogout}) => {
   return (
         <React.Fragment>
         <Navbar bg="light" expand="lg">
            <Container fluid>
               <Navbar.Brand href="#home">React-Bootstrap</Navbar.Brand>
               <Navbar.Toggle aria-controls="basic-navbar-nav" />
               <Navbar.Collapse id="basic-navbar-nav">
               <Nav className="me-auto">
                  <Link to="/" className="nav-link">Home</Link>
                  <Link to="/board" className="nav-link">게시판</Link>
                  <Link to="/workout" className="nav-link">홈트</Link>
                  <Link to="/hackernews" className="nav-link">해커뉴스</Link>
                  <Link to="/youtube" className="nav-link">유튜브</Link>
               </Nav>
            {onLogout && <Button vatiant='primary' onClick={onLogout}>Logout</Button>}
            </Navbar.Collapse>
         </Container>
         </Navbar>
      </React.Fragment>
   )
}

export default Header

import React, { Component } from 'react';
import '../App.css';
import AppNavbar from '../componentes/BarraNavegacao';
import { Link } from 'react-router-dom';
import { Button, Container } from 'reactstrap';

class Home extends Component {
  render() {
    return (
      <div>
        <AppNavbar/>
        <Container fluid>
            <Button color="link"><Link to="/pedidos">Solicitar Pedido</Link></Button>            
            <Button color="link"><Link to="/lanches">Solicitação de Lanches</Link></Button>
        </Container>
      </div>
    );
  }
}

export default Home;
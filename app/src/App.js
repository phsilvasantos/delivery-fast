import React, { Component } from 'react';
import './App.css';
import Home from './componentes/Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import { observer  } from 'mobx-react';
import PedidoList from './pages/pedido/index';
import LancheList from './pages/lanche/index';

//axios.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
class App extends Component {


  render() {
    return (
      <Router>
        <Switch>
          <Route path='/' exact={true} component={Home}/>
          <Route path='/pedidos' exact={true} component={PedidoList}/>
          <Route path='/lanches' exact={true} component={LancheList}/>
        </Switch>
      </Router>
    )
  }  
}

export default observer(App);
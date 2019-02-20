import React, { Component } from 'react';
import './App.css';
import Home from './componentes/Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import { observer  } from 'mobx-react';
import LancheList from './pages/lanche';
import LancheForm from './pages/lanche/form';

class App extends Component {

  render() {
    return (
      <Router>
        <Switch>
          <Route path='/' exact={true} component={Home}/>
          {/* <Route path='/pedidos' exact={true} component={PedidoList}/> */}
          <Route path='/lanches' exact={true} component={LancheList}/>
          <Route path='/lanches/:id' component={LancheForm}/>
        </Switch>
      </Router>
    )
  }  
}

export default observer(App);
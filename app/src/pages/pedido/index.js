import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import BarraNavegacao from '../../componentes/BarraNavegacao';
import { Link } from 'react-router-dom';
import PedidoIndexState from '../../stores/pedido/indexState';
import { observer  } from 'mobx-react';

class PedidoList extends Component {

  constructor(props) {
    super(props);
    this.pageState = new PedidoIndexState();    
    this.remove = this.remove.bind(this);
  }

  async componentDidMount() {    
    this.pageState.loadPedidos();
  }

  async remove(id) {
    await fetch(`/api/pedidos/${id}`, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => {
      let updatedPedidos = [...this.pageState.pedidos].filter(i => i.id !== id);
    });
  }

  render() {
    debugger;

    if (this.pageState.loading) {
      return <p>Loading Pedidos...</p>;
    }

    const pedidoList = this.pageState.pedidos.map(pedido => {
      const pedidoDetalhe = `${pedido.id || ''} ${pedido.cliente || ''} }`;
      return <tr key={pedido.id}>
        <td style={{whiteSpace: 'nowrap'}}>{pedido.id}</td>
        <td style={{whiteSpace: 'nowrap'}}>{pedido.cliente}</td>
        <td style={{whiteSpace: 'nowrap'}}>{pedido.endereco}</td>
        {/* <td style={{whiteSpace: 'nowrap'}}>{pedido.dtPedido}</td> */}
        { <td style={{whiteSpace: 'nowrap'}}>{new Intl.DateTimeFormat('pt-BR', {
          year: 'numeric',
          month: 'long',
          day: '2-digit'}).format(new Date(pedido.dtPedido)) }</td> }        
        <td style={{whiteSpace: 'nowrap'}}>{pedido.valor}</td>
        <td style={{whiteSpace: 'nowrap'}}>{pedido.status}</td>        
        <td>
          <ButtonGroup>
            <Button size="sm" color="primary" tag={Link} to={"/pedidos/" + pedido.id}>Editar</Button>
            <Button size="sm" color="danger" onClick={() => this.remove(pedido.id)}>Remover</Button>
          </ButtonGroup>
        </td>
      </tr>
    });

    return (
      <div>
        <BarraNavegacao/>
        <Container fluid>
          <div className="float-right">
            <Button color="success" tag={Link} to="/pedidos/new">Novo Pedido</Button>
          </div>
          <h3>Delivery Fast</h3>
          <Table className="mt-4">
            <thead>
            <tr>
            <th width="05%">Nº Pedido</th>
              <th width="20%">Nome Solicitante</th>
              <th width="20%">Endereço</th>
              <th width="10%">Data Pedido</th>
              <th width="10%">Valor</th>
              <th width="10%">Status</th>
              <th width="10%">Ações</th>
            </tr>
            </thead>
            <tbody>
            {pedidoList}
            </tbody>
          </Table>
        </Container>
      </div>
    );
  }
}

export default observer(PedidoList);
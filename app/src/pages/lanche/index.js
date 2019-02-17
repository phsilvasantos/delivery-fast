import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import BarraNavegacao from '../../componentes/BarraNavegacao';
import { Link } from 'react-router-dom';
import LancheIndexState from '../../stores/lanche/indexState';
import { observer  } from 'mobx-react';

class LancheList extends Component {

  constructor(props) {
    super(props);
    this.pageState = new LancheIndexState();
    this.state = {lanches: [], isLoading: true};
    this.remove = this.remove.bind(this);
  }

  async componentDidMount() {    
    this.pageState.loadLanches();
  }

  async remove(id) {
    await fetch(`/api/lanche/${id}`, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => {
      let updatedLanches = [...this.state.lanches].filter(i => i.id !== id);
      this.setState({lanches: updatedLanches});
    });
  }

  render() {
    debugger;

    if (this.pageState.loading) {
      return <p>Loading Lanches...</p>;
    }

    const lancheList = this.pageState.lanches.map(lanche => {
      const lancheDetalhe = `${lanche.descricao || ''} ${lanche.tipoLanche || ''} }`;
      return <tr key={lanche.id}>
        <td style={{whiteSpace: 'nowrap'}}>{lanche.descricao}</td>
        <td>{lancheDetalhe}</td>
        <td>
          <ButtonGroup>
            <Button size="sm" color="primary" tag={Link} to={"/lanches/" + lanche.id}>Editar</Button>
            <Button size="sm" color="danger" onClick={() => this.remove(lanche.id)}>Remover</Button>
          </ButtonGroup>
        </td>
      </tr>
    });

    return (
      <div>
        <BarraNavegacao/>
        <Container fluid>
          <div className="float-right">
            <Button color="success" tag={Link} to="/lanches/new">Novo Lanche</Button>
          </div>
          <h3>Delivery Fast</h3>
          <Table className="mt-4">
            <thead>
            <tr>
              <th width="20%">Descrição</th>
              <th width="20%">Tipo do Lanche</th>
              <th width="10%">Ações</th>
            </tr>
            </thead>
            <tbody>
            {lancheList}
            </tbody>
          </Table>
        </Container>
      </div>
    );
  }
}

export default observer(LancheList);
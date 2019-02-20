import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import BarraNavegacao from '../../componentes/BarraNavegacao';
import { Link } from 'react-router-dom';
import LancheIndexState from '../../stores/lanche/indexState';
import { observer  } from 'mobx-react';
import { getValueMoney } from '../../utils/numberFormatter';

class LancheList extends Component {

  constructor(props) {
    super(props);
    this.pageState = new LancheIndexState();
    this.state = {lanches: [], isLoading: true};
    this._handleRemove = this._handleRemove.bind(this);
  }

  async componentDidMount() {    
    this.pageState.loadLanches();
  }

  async _handleRemove(lanche) {
      this.pageState.removerLanche(lanche);
      let updatedLanches = [...this.state.lanches].filter(i => i.lanche.id !== lanche.id);
      this.setState({lanches: updatedLanches});
      this.props.history.push('/lanches');
  }

  render() {
    if (this.pageState.loading) {
      return <p>Loading Lanches...</p>;
    }

    const lancheList = this.pageState.lanches.map(lanche => {
      return <tr key={lanche.id}>
        {/* <td style={{whiteSpace: 'nowrap'}}>{lanche.descricao}</td> */}
        <td>{lanche.id}</td>
        <td>{lanche.descricao}</td>
        <td>{getValueMoney(lanche.valor)}</td>
        <td>{getValueMoney(lanche.valorComDesconto)}</td>
        <td>
          <ButtonGroup>
            <Button size="sm" color="danger" onClick={() => this._handleRemove(lanche)}>Remover</Button>
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
              <th width="10%">Id.</th>              
              <th width="20%">Descrição</th>
              <th width="20%">Valor</th>
              <th width="20%">Valor Promocional</th>
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
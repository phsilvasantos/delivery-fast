import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import BarraNavegacao from '../../componentes/BarraNavegacao';
import LancheFormState from '../../stores/lanche/formState';

class LancheForm extends Component {

  emptyLanche = {
    codigoLanche: '1',
    qtdAlface: '0',
    qtdBacon: '1',
    qtdHamburguer: '1',
    qtdOvo: '0',
    qtdQueijo: '1',
  };

  constructor(props) {
    super(props);
    this.store = new LancheFormState();    
    this.state = {
      item: this.emptyLanche
    };
    this._handleChange = this._handleChange.bind(this);
    this._handleSubmit = this._handleSubmit.bind(this);
    this._handleChangeCodigoLanche = this._handleChangeCodigoLanche.bind(this);
  }

  async componentDidMount() {
    if (this.props.match.params.id !== 'new') {
      const lanche = await (await fetch(`/api/lanches/${this.props.match.params.id}`)).json();
      this.setState({item: lanche});
    }
  }

  _handleChange(event) {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    let item = {...this.state.item};
    item[name] = value;
    this.setState({item});
  }

  _handleChangeCodigoLanche(event) {
    const target = event.target;
    const value = target.value;
    let item = {...this.state.item};
    item['codigoLanche'] = value;
    this.setState({item});

    switch(value){
      case "1":
        item['qtdAlface'] = '0';
        item['qtdBacon'] = '1';
        item['qtdHamburguer'] = '1';
        item['qtdOvo'] = '0';
        item['qtdQueijo'] = '1';
        this.setState({item});
        break;      
      case "2":
        item['qtdAlface'] = '0';
        item['qtdBacon'] = '0';
        item['qtdHamburguer'] = '1';
        item['qtdOvo'] = '0';
        item['qtdQueijo'] = '1';
        this.setState({item});
        break;      
      case "3":
          item['qtdAlface'] = '0';
          item['qtdBacon'] = '0';
          item['qtdHamburguer'] = '1';
          item['qtdOvo'] = '1';
          item['qtdQueijo'] = '1';
          this.setState({item});
        break;  
      case "4":
          item['qtdAlface'] = '0';
          item['qtdBacon'] = '1';
          item['qtdHamburguer'] = '1';
          item['qtdOvo'] = '1';
          item['qtdQueijo'] = '1';
          this.setState({item});
        break; 
      default:
        item['qtdAlface'] = '0';
        item['qtdBacon'] = '0';
        item['qtdHamburguer'] = '0';
        item['qtdOvo'] = '0';
        item['qtdQueijo'] = '0';
        this.setState({item});      
    }
    
  }  

async _handleSubmit(event) {
    event.preventDefault();
    const {item} = this.state;

    this.store.solicitacaoLanche = item;
    this.store.salvar(item);
    this.props.history.push('/lanches');
  }

  render() {
    const {item} = this.state;
    const title = <h2>{item.id ? 'Editar Lanche' : 'Adicionar Lanche'}</h2>;

    return <div>
      <BarraNavegacao/>
      <Container>
        {title}
        <Form onSubmit={this._handleSubmit}>
        <div className="row">
            <FormGroup className="col-md-6 mb-3">
                <Label for="address">Tipo de Lanche</Label>
                    <Input type="select" name="codigoLanche" id="codigoLanche" onChange={this._handleChangeCodigoLanche}>
                    <option value="1">X-Bacon', 'Bacon, hamburguer de carne e queijo'</option>
                    <option value="2">X-Burguer', 'Hamburguer de carne e queijo'</option>
                    <option value="3">X-Egg', 'Ovo, hamburguer de carne e queijo'</option>
                    <option value="4">X-Egg Bacon', 'Ovo, bacon, hamburguer de carne e queijo'</option>
                    <option value="5">X-Personalizado', 'Monte seu prório lanche !!!'</option>
                    </Input>
            </FormGroup>       
          </div>               
          <div className="row">
            <FormGroup className="col-md-2 mb-3">
              <Label for="qtdAlface">Qtd. Alface</Label>
                <Input type="select" pattern="[0-9]*" name="qtdAlface" id="qtdAlface" disabled={this.state.item.codigoLanche !== "5"} value={this.state.item['qtdAlface']}
                        onChange={this._handleChange}>
                    <option value="0">0</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>    
                </Input>                 
            </FormGroup>
          </div>
          <div className="row">
            <FormGroup className="col-md-2 mb-3">
              <Label for="qtdBacon">Qtd. Bacon</Label>
                <Input type="select" pattern="[0-9]*" name="qtdBacon" id="qtdBacon" disabled={this.state.item.codigoLanche !== "5"} value={this.state.item['qtdBacon']}
                    onChange={this._handleChange}>
                <option value="0">0</option>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option> 
                </Input>                                                 
            </FormGroup>
          </div>   
          <div className="row">
            <FormGroup className="col-md-2 mb-3">
              <Label for="qtdHamburguer">Qtd. Hamburguer</Label>
              <Input type="select" pattern="[0-9]*" name="qtdHamburguer" id="qtdHamburguer" disabled={this.state.item.codigoLanche !== "5"} value={this.state.item['qtdHamburguer']}
                     onChange={this._handleChange}>
                    <option value="0">0</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>  
                </Input>                                              
            </FormGroup>
          </div>   
          <div className="row">
            <FormGroup className="col-md-2 mb-3">
              <Label for="qtdOvo">Qtd. Ovo</Label>
              <Input type="select" pattern="[0-9]*" name="qtdOvo" id="qtdOvo" disabled={this.state.item.codigoLanche !== "5"} value={this.state.item['qtdOvo']}
                     onChange={this._handleChange}>
                    <option value="0">0</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>   
                </Input>                                             
            </FormGroup>
          </div>     
          <div className="row">
            <FormGroup className="col-md-2 mb-3">
              <Label for="qtdQueijo">Qtd. Queijo</Label>
              <Input type="select" pattern="[0-9]*" name="qtdQueijo" id="qtdQueijo" disabled={this.state.item.codigoLanche !== "5"} value={this.state.item['qtdQueijo']}
                     onChange={this._handleChange}>
                    <option value="0">0</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>   
            </Input>                                            
            </FormGroup>
          </div>                                                     
          <FormGroup>
            <Button color="primary" type="submit">Salvar</Button>{' '}
            <Button color="secondary" tag={Link} to="/lanches">Cancelar</Button>
          </FormGroup>
        </Form>
      </Container>
    </div>
  }
}

export default withRouter(LancheForm);
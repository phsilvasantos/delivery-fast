import { observable, decorate } from 'mobx';

class Lanche  {
  id;
  descricao;
  tipoLanche;
  valor;

  static getAttributesTable() {
    return [
      'id',
      'descricao',
      'tipoLanche',
      'valor',
      'valorComDesconto'
    ];
  }
}

decorate(Lanche, {
  id: observable,
  descricao: observable,
  tipoLanche: observable,
  valor: observable,
  valorComDesconto: observable,
}) 

export default Lanche;

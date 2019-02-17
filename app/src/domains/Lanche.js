import { observable } from 'mobx';

class Lanche  {
  @observable
  id;
  @observable
  descricao;
  @observable
  tipoLanche;
  @observable
  valor;

  static getAttributesTable() {
    return [
      'id',
      'descricao',
      'tipoLanche',
      'valor',
    ];
  }
}

export default Lanche;

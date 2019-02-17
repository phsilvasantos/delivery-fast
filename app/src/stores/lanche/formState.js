import { observable, action, runInAction, computed, decorate } from 'mobx';
import moment from 'moment';


import CprbEstabelecimento from '../../../domains/CprbEstabelecimento';
import LancheService from '../../services/LancheService';
import Lanche from '../../domains/Lanche';


class LancheFormState {
  @observable
  loading = false;

  @observable
  lanches = [];


  @action
  initialize(params) {
    //this.loading = true;
    //promises.push(EstabelecimentoUsuarioViewService.getFiliais(codigoMatriz, periodo));
  }

  @action
  loadLanches() {
    this.loadingLanches = true;
    LancheService.listarLanches()
      .then(response => {
        runInAction(() => {
          if (response.data) {
            this.lanches = response.data.map(lanche => new Lanche(response.data));
          } else {
            this.lanches = [];
          }
          this.loadingLanches = false;
        });
      })
      .catch(error => {
        runInAction(() => {
          this.loadingAtividades = false;
        });
      });
  }

}

export default LancheFormState;

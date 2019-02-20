import { observable, action, runInAction, decorate } from 'mobx';

import LancheService from '../../services/LancheService';
import Lanche from '../../domains/Lanche';


class LancheFormState {
  loading = false;
  lanches = [];
  solicitacaoLanche;

  initialize(params) {
    this.loading = true;
  }

  loadLanches() {
    this.loading = true;
    LancheService.listarLanches()
      .then(response => {
        runInAction(() => {
          if (response.data) {
            this.lanches = response.data.map(lanche => new Lanche(response.data));
          } else {
            this.lanches = [];
          }
          this.loading = false;
        });
      })
      .catch(error => {
        runInAction(() => {
          this.loading = false;
          console(error);
        });
      });
  }

  salvar(callback) {
      this.loading = true;
      LancheService.save(this.solicitacaoLanche)
        .then(response => {
          runInAction('Salvar Lanche', () => {
            this.loading = false;
            if (callback) {
              this.loadLanches();
            }
          });
        })
        .catch(error => {
          runInAction('Salvar Lanche', () => {            
            this.loading = false;
            console(error);
          });
        });
    }
  }


decorate(LancheFormState, {
  loading: observable,
  lanches: observable,
  solicitacaoLanche: observable,
  initialize: action,
  loadLanches: action,
  salvar: action
}) 

export default LancheFormState;

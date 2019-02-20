import { observable, action, runInAction, decorate } from 'mobx';
import LancheService from '../../services/LancheService';

class LancheIndexState {
    loading = true;
    lanches = [];

    loadLanches() {
        this.loading = true;

        LancheService.listarLanches()
        .then(response => {
            runInAction('Load Lanches', () => {
            this.loading = false;
            this.lanches.replace(response);
            });
        })
        .catch(error => {
            runInAction('Load Lanches Error', () => {
            this.loading = false;
            console.log(error);
            });
        });
    } 
    
    removerLanche(callback) {
          this.loading = true;
          LancheService.remove(callback)
            .then(response => {
              runInAction('Remover Lanche', () => {
                this.loading = false;
                if (callback) {
                  this.loadLanches();
                }
              });
            })
            .catch(error => {
              this.loading = false;
              console(error);
            });
        }    
}

decorate(LancheIndexState, {
    loading: observable,
    lanches: observable,
    loadLanches: action
}) 

export default LancheIndexState;

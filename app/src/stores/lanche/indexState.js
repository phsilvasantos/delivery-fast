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
}

decorate(LancheIndexState, {
    loading: observable,
    lanches: observable,
    loadLanches: action
}) 

export default LancheIndexState;

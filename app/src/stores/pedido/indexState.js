import { observable, action, runInAction, decorate } from 'mobx';
import PedidoService from '../../services/PedidoService';

class PedidoIndexState {
    loading = true;
    pedidos = [];

    loadPedidos() {
        this.loading = true;

        PedidoService.listarPedidos()
        .then(response => {
            runInAction('Load Pedidos', () => {
            this.loading = false;
            this.pedidos.replace(response);
            });
        })
        .catch(error => {
            runInAction('Load Pedidos Error', () => {
            this.loading = false;
            console.log(error);
            });
        });
    }  
}

decorate(PedidoIndexState, {
    loading: observable,
    pedidos: observable,
    loadPedidos: action
}) 

export default PedidoIndexState;

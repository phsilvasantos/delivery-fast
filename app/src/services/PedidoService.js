import endpoints from '../constantes/endpoints'

class PedidoService {

  async listarPedidos() {
    
    const response = await fetch(`${endpoints.host}${endpoints.baseApiUrl}/${endpoints.pedidos}`);
    return response.json();
  }
}

let instance = new PedidoService();

export default instance;

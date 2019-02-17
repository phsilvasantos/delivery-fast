import endpoints from './../constantes/endpoints'

class LancheService {

  async listarLanches() {
    
    const response = await fetch(`${endpoints.host}${endpoints.baseApiUrl}/${endpoints.lanches}`);
    return response.json();
  }
}

let instance = new LancheService();

export default instance;

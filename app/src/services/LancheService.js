import endpoints from '../constantes/endpoints'

class LancheService {

  async listarLanches() {    
    const response = await fetch(`${endpoints.host}${endpoints.baseApiUrl}/${endpoints.lanches}`);
    return response.json();
  }

  async save(object) {    
    await fetch(`${endpoints.host}${endpoints.baseApiUrl}/${endpoints.lanches}`, {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(object),
    });    
  } 
  
  async remove(object) {    
    await fetch(`${endpoints.host}${endpoints.baseApiUrl}/${endpoints.lanches}`, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(object),
    });    
  } 
}

let instance = new LancheService();

export default instance;

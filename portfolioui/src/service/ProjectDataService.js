import axios from "axios";
const API_URL = 'http://localhost:8060/portfolio'

class ProjectDataService {
  getAll() {
    return axios.get(`${API_URL}`+"/projects",{
      headers: {
          'Content-Type': 'application/json',
          'authorization':sessionStorage.getItem("authToken")
      }
    });
  }

  get(id) {
    return axios.get(`${API_URL}`+`/projects/${id}`,{
      headers: {
          'Content-Type': 'application/json',
          'authorization':sessionStorage.getItem("authToken")
      }
    });
  }

  create(data) {
    return axios.post(`${API_URL}`+"/createproject", data, {
      headers: {
          'Content-Type': 'application/json',
          'authorization':sessionStorage.getItem("authToken")
      }
    }
      );
    
  }

  update(id, data) {
    return axios.put(`${API_URL}`+`/updateproject/${id}`, data, {
      headers: {
          'Content-Type': 'application/json',
          'authorization':sessionStorage.getItem("authToken")
      }
    }
    
    );
  }

  delete(id) {
    return axios.delete(`${API_URL}`+`/deleteproject/${id}`
    , {
      headers: {
          'Content-Type': 'application/json',
          'authorization':sessionStorage.getItem("authToken")
      }
    }
    
    );
  }

  deleteAll() {
    return axios.delete(`${API_URL}`+`/projects`);
  }

  findByName(name) {
    return axios.get(`${API_URL}`+`/projects?name=${name}`);
  }
}

export default new ProjectDataService();
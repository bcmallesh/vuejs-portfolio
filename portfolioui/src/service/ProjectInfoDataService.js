import axios from "axios";
const API_URL = 'http://localhost:8060/portfolio'

class ProjectDataService {
  getAll() {
    return axios.get(`${API_URL}`+"/projectsinfo",{
      headers: {
          'Content-Type': 'application/json'
      }
    });
  }
  getLookupData() {
    return axios.get(`${API_URL}`+"/lookupdata",{
      headers: {
          'Content-Type': 'application/json'
          
      }
    });
  }
  get(id) {
    return axios.get(`${API_URL}`+`/projectsinfo/${id}`,{
      headers: {
          'Content-Type': 'application/json',
          'authorization':sessionStorage.getItem("authToken")
      }
    });
  }

  create(file,data) {
    let formData = new FormData();

    /*
        Add the form data we need to submit
    */
    formData.append('file', file);
    console.log(JSON.stringify(data));
    formData.append('projectInfo', new Blob([JSON.stringify(data)], {
      type: "application/json"
  }));

    return axios.post(`${API_URL}`+"/createprojectinfo", formData, {
      headers: {
          //'Content-Type': 'application/json',
          'Content-Type': 'multipart/form-data',
          'authorization':sessionStorage.getItem("authToken")
      }
    }
      );
    
  }

  update(id, data) {
    return axios.put(`${API_URL}`+`/updateprojectinfo/${id}`, data, {
      headers: {
          'Content-Type': 'application/json',
          'authorization':sessionStorage.getItem("authToken")
      }
    }
    
    );
  }

  delete(id) {
    return axios.delete(`${API_URL}`+`/deleteprojectinfo/${id}`
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
    return axios.get(`${API_URL}`+`/projectsinfo?name=${name}`);
  }
}

export default new ProjectDataService();
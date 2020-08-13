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

  create(thumnailimage,heroimage,sectionfiles,data) {
    let formData = new FormData();

    /*
        Add the form data we need to submit
    */
    formData.append('thumnailimage', thumnailimage);
    formData.append('heroimage', heroimage);
    for (let i = 0; i < sectionfiles.length; i++) {
      formData.append("sectionfiles", sectionfiles[i]);
  }


    //formData.append('sectionfiles',sectionfiles);

    console.log(JSON.stringify(data));
    formData.append('projectdata', new Blob([JSON.stringify(data)], {
      type: "application/json"
  }));

    return axios.post(`${API_URL}`+"/createproject", formData, {
      headers: {
          //'Content-Type': 'application/json',
          'Content-Type': 'multipart/form-data',
          'authorization':sessionStorage.getItem("authToken")
      }
    }
      );
    
  }
  update(id,thumnailimage,heroimage,sectionfiles,data) {
    let formData = new FormData();

    /*
        Add the form data we need to submit
    */
    formData.append('thumnailimage', thumnailimage);
    formData.append('heroimage', heroimage);
    for (let i = 0; i < sectionfiles.length; i++) {
      formData.append("sectionfiles", sectionfiles[i]);
  }


    //formData.append('sectionfiles',sectionfiles);

    console.log(JSON.stringify(data));
    formData.append('projectdata', new Blob([JSON.stringify(data)], {
      type: "application/json"
  }));

    return axios.put(`${API_URL}`+`/updateproject/${id}`, formData, {
      headers: {
          //'Content-Type': 'application/json',
          'Content-Type': 'multipart/form-data',
          'authorization':sessionStorage.getItem("authToken")
      }
    }
      );
    
  }
  preview(thumnailimage,heroimage,sectionfiles,data) {
    let formData = new FormData();

    /*
        Add the form data we need to submit
    */
    formData.append('thumnailimage', thumnailimage);
    formData.append('heroimage', heroimage);
    for (let i = 0; i < sectionfiles.length; i++) {
      formData.append("sectionfiles", sectionfiles[i]);
  }


    //formData.append('sectionfiles',sectionfiles);

    console.log(JSON.stringify(data));
    formData.append('projectdata', new Blob([JSON.stringify(data)], {
      type: "application/json"
  }));

    return axios.post(`${API_URL}`+"/previewproject", formData, {
      headers: {
          //'Content-Type': 'application/json',
          'Content-Type': 'multipart/form-data',
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
import axios from 'axios'

const API_URL = 'http://localhost:8200/portfolio/projects'
//const PROJECTS_API_URL = `${API_URL}/projects/`

class ProjectDataService {

    retrieveAllProject() {
        //console.log('executed service')
        return axios.get(`${API_URL}`,
            //{ headers: { authorization: 'Basic ' + window.btoa(INSTRUCTOR + ":" + PASSWORD) } }
        );
    }
}

export default new ProjectDataService()

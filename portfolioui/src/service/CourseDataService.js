import axios from 'axios'

//const INSTRUCTOR = 'in28minutes'
// const PASSWORD = 'dummy'
const API_URL = 'http://localhost:8280/portfolio/projects/'
//const PROJECTS_API_URL = `${API_URL}/projects/`

class CourseDataService {

    retrieveAllCourses() {
        //console.log('executed service')
        return axios.get(`${API_URL}`,
            //{ headers: { authorization: 'Basic ' + window.btoa(INSTRUCTOR + ":" + PASSWORD) } }
        );
    }
}

export default new CourseDataService()

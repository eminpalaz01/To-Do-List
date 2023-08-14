import axios from 'axios';

let axiosBackendApi;

const token = localStorage.getItem('authToken');

if(token){
axiosBackendApi = axios.create({
  baseURL: 'http://localhost:8080/api/v1', // Burada API'nin temel URL'sini belirtin
  headers:{
    Authorization: `Bearer ${token}`
}
  });
}
else{
  axiosBackendApi = axios.create({
    baseURL: 'http://localhost:8080/api/v1', // Burada API'nin temel URL'sini belirtin
  });
}



export default axiosBackendApi;
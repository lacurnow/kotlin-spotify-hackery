import axios from 'axios';

const StarterClient = axios.create({
    baseURL: 'http://localhost:8080',

    headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
    }
});

StarterClient.interceptors.response.use(
    response => response,
    error => {
        console.log('ERROR:', error);
        return Promise.reject(error);
    });

export const pingServer = async (ping) => {
    return await StarterClient.post('ping', ping);
};

import axios from 'axios';


const baseURL = "http://localhost:8081/api/v1";

export const client = axios.create({
    baseURL,
    timeout: 60000,
    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    },
});

// Employee APIs List:
export const EMPLOYEES_PATH = "/employees";
export const EMPLOYEE_PATH = "/employees/:id";

// Department APIs List
export const DEPARTMENTS_PATH = "/departments";
export const DEPARTMENT_PATH = "/department/:code";

export default client;


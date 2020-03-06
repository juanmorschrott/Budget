import axios from 'axios'

const API_URL = 'http://localhost:8081/api/v1';
const ENDPOINT = '/budgets';

class BudgetService {

    retrieveAllBudgets() {
        console.log('GET /budgets')
        return axios.get(API_URL + ENDPOINT);
    }

    retrieveBudget(id) {
        console.log('GET /budgets/:id')
        return axios.get(API_URL + ENDPOINT + "/" + id);
    }

    deleteBudget(id) {
        console.log('DELETE /budgets/:id')
        return axios.delete(API_URL + ENDPOINT + "/" + id);
    }

    updateBudget(id, budget) {
        console.log('POST /budgets/:id')
        return axios.put(API_URL + ENDPOINT + "/" + id, budget);
    }

    createBudget(budget) {
        console.log('POST /budgets')
        return axios.post(API_URL + ENDPOINT, budget);
    }
}

export default new BudgetService()

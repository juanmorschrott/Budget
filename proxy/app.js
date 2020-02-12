const express = require('express')
const cors = require('cors')
const axios = require('axios')
const app = express()
const port = 8082

app.use(cors())

const categories = require('./categories.json');
const locations = require('./locations.json');

/**
 * Get Category List
 */
app.get('/category/list/', function (req, res, next) {
  axios
    .get('https://api.habitissimo.es/category/list')
    .then(response => {
      console.log(response.data);
      res.send(response.data);
    })
    .catch(error => {
      console.log(error);
    });
})

/**
 * Get Category Detail
 */
app.get('/category/list/:id', function (req, res, next) {
  axios
    .get('https://api.habitissimo.es/category/list/' + req.params.id)
    .then(response => {
      console.log(response.data);
      res.send(response.data);
    })
    .catch(error => {
      console.log(error);
    });
})

/**
 * Get All locations
 */
app.get('/location/list', function (req, res, next) {
  res.send(locations)
})

app.listen(port, () => console.log(`Example app listening on port ${port}!`))

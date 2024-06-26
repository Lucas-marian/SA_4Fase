const express = require('express');
const cors = require('cors');
const app = express();

app.use(cors({
  origin: 'localhost',
  methods: 'GET, POST, PUT, DELETE',
  credentials: true
}));

app.use((req, res, next) => {
  res.header('Access-Control-Allow-Origin', '*');
  res.header('Access-Control-Allow-Methods', 'GET, POST, PUT, DELETE, OPTIONS');
  res.header('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept');
  next();
});

app.listen(8080, () => {
  console.log('Server running on port 8080');
});
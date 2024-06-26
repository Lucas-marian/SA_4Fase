
// eslint-disable-next-line no-unused-vars
import React, { useEffect, useState } from 'react';
import axios from 'axios';

const Home = () => {
  const [dadosHome, setDadosHome] = useState([]);

  useEffect(() => {
    async function fetchData() {
      try {
        const response = await axios.get('http://localhost:8080/api/home');
        setDadosHome(response.data);
      } catch (error) {
        console.error('Erro ao buscar dados da Home:', error);
      }
    }

    fetchData();
  }, []);

  return (
    <div>
      <h1>Página Home</h1>
      <p>Dados da Home: {dadosHome}</p>
    </div>
  );
};

export default Home;

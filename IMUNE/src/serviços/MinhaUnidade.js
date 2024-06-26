
// eslint-disable-next-line no-unused-vars
import React, { useEffect, useState } from 'react';
import axios from 'axios';

const MinhaUnidade = () => {
  const [unidade, setUnidade] = useState([]);

  useEffect(() => {
    async function fetchData() {
      try {
        const response = await axios.get('http://localhost:8080/api/unidade');
        setUnidade(response.data);
      } catch (error) {
        console.error('Erro ao buscar unidade:', error);
      }
    }

    fetchData();
  }, []);

  return (
    <div>
      <h1>Minha Unidade</h1>
      <p>Dados da Unidade: {unidade}</p>
    </div>
  );
};

export default MinhaUnidade;

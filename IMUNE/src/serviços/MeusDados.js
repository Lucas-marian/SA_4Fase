// eslint-disable-next-line no-unused-vars
import React, { useEffect, useState } from 'react';
import axios from 'axios';

const MeusDados = () => {
  const [dadosUsuario, setDadosUsuario] = useState([]);

  useEffect(() => {
    async function fetchData() {
      try {
        const response = await axios.get('http://localhost:8080/api/dados');
        setDadosUsuario(response.data);
      } catch (error) {
        console.error('Erro ao buscar dados do usuário:', error);
      }
    }

    fetchData();
  }, []);

  return (
    <div>
      <h1>Meus Dados</h1>
      <p>Dados do Usuário: {dadosUsuario}</p>
    </div>
  );
};

export default MeusDados;

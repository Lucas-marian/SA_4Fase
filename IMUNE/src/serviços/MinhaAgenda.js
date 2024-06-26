
// eslint-disable-next-line no-unused-vars
import React, { useEffect, useState } from 'react';
import axios from 'axios';

const MinhaAgenda = () => {
  const [agenda, setAgenda] = useState([]);

  useEffect(() => {
    async function fetchData() {
      try {
        const response = await axios.get('http://localhost:8080/api/agenda');
        setAgenda(response.data);
      } catch (error) {
        console.error('Erro ao buscar agenda:', error);
      }
    }

    fetchData();
  }, []);

  return (
    <div>
      <h1>Minha Agenda</h1>
      <ul>
        {agenda.map(item => (
          <li key={item.id}>{item.data} - {item.hora} - {item.descricao}</li>
        ))}
      </ul>
    </div>
  );
};

export default MinhaAgenda;

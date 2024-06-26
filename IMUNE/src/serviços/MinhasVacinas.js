
// eslint-disable-next-line no-unused-vars
import React, { useEffect, useState } from 'react';
import axios from 'axios';

const MinhasVacinas = () => {
  const [vacinas, setVacinas] = useState([]);

  useEffect(() => {
    async function fetchData() {
      try {
        const response = await axios.get('http://localhost:8080/api/vacinas');
        setVacinas(response.data);
      } catch (error) {
        console.error('Erro ao buscar vacinas:', error);
      }
    }

    fetchData();
  }, []);

  return (
    <div>
      <h1>Minhas Vacinas</h1>
      <ul>
        {vacinas.map(vacina => (
          <li key={vacina.id}>{vacina.nome} - {vacina.data}</li>
        ))}
      </ul>
    </div>
  );
};

export default MinhasVacinas;

// eslint-disable-next-line no-unused-vars
import React, { useState } from 'react';
import './MinhaUnidade.css';
import axios from 'axios';

const MinhaUnidade = () => {
  const [searchTerm, setSearchTerm] = useState('');
  const [result, setResult] = useState(null);
  const [error, setError] = useState('');

  const handleSearch = async () => {
    if (searchTerm) {
      try {
        const response = await axios.get(`https://viacep.com.br/ws/${searchTerm}/json/`);
        if (response.data.erro) {
          setError('CEP não encontrado.');
          setResult(null);
        } else {
          setResult({
            logradouro: response.data.logradouro,
            bairro: response.data.bairro,
            localidade: response.data.localidade,
            uf: response.data.uf,
          });
          setError('');
        }
      } catch (error) {
        console.error('Erro ao buscar endereço:', error);
        setError('Erro ao buscar endereço');
        setResult(null);
      }
    }
  };

  return (
    <div>
      <video className="video-background" autoPlay loop muted>
        <source src="/public/3197808-hd_1920_1080_25fps.mp4" type="video/mp4" />
      </video>
      <div className="search-container">
      
        <div className="search-bar">
          <h2>Buscar Unidade de Saúde</h2>
          <div>
            <input
              type="text"
              placeholder="Digite o CEP"
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
            />
            <button onClick={handleSearch}>Buscar</button>
          </div>
        </div>
        {error && <p className="error-message">{error}</p>}
        {result && (
          <div className="search-result">
            <h3>Endereço da Unidade de Saúde</h3>
            <p>{result.logradouro}, {result.bairro}, {result.localidade} - {result.uf}</p>
          </div>
        )}
      </div>
    </div>
  );
};

export default MinhaUnidade;


// eslint-disable-next-line no-unused-vars
import React, { useState } from 'react';
import axios from 'axios';

const RecuperacaoSenha = () => {
  const [email, setEmail] = useState('');

  const handleRecuperarSenha = async () => {
    try {
      const response = await axios.post('http://localhost:8080/api/recuperar-senha', { email });
      console.log('Email de recuperação enviado:', response.data);
    
    } catch (error) {
      console.error('Erro ao recuperar senha:', error);
    }
  };

  return (
    <div>
      <h1>Recuperação de Senha</h1>
      <form onSubmit={handleRecuperarSenha}>
        <input type="email" placeholder="Email" value={email} onChange={e => setEmail(e.target.value)} />
        <button type="submit">Recuperar Senha</button>
      </form>
    </div>
  );
};

export default RecuperacaoSenha;

/* eslint-disable no-unused-vars */
import axios from 'axios';
import React, { useState } from 'react';
import { Carousel } from 'react-responsive-carousel';
import 'react-responsive-carousel/lib/styles/carousel.min.css';
import { Link, useNavigate } from 'react-router-dom';
import Botao from '../../components/botao';
import './Cadastro.css';

function Cadastro() {
  const navigate = useNavigate();
  const [nome, setNome] = useState('');
  const [email, setEmail] = useState('');
  const [cpf, setCpf] = useState('');
  const [dataNascimento, setDataNascimento] = useState('');
  const [genero, setGenero] = useState('');
  const [passwordVisible, setPasswordVisible] = useState(false);
  const [password, setPassword] = useState('');
  const [isProfissional, setIsProfissional] = useState(false);
  const [coren, setCoren] = useState('');
  const [error, setError] = useState('');
  const [success, setSuccess] = useState(null);

  const generos = ['MASCULINO', 'FEMININO'];

  const togglePasswordVisibility = () => {
    setPasswordVisible(!passwordVisible);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    try {
      const userData = {
        nome,
        email,
        cpf,
        dataNascimento,
        genero,
        password,
        isProfissional,
        coren: isProfissional ? coren : '',
      };

      let response;
      if (isProfissional) {
        response = await axios.post('http://localhost:8080/api/profissionais', userData, {headers: {'Content-Type': 'application/json'}});
      } else {
        response = await axios.post('http://localhost:8080/api/usuarios', userData, {headers: {'Content-Type': 'application/json'}});
      }
      
      if (response.status !== 201) {
        throw new Error('Erro ao cadastrar usuário');
      }
      
      setSuccess('Cadastro realizado com sucesso!');
      setNome('');
      setEmail('');
      setCpf('');
      setDataNascimento('');
      setGenero('');
      setPassword('');
      setCoren('');
      setError('');
      
      navigate("/login");
      console.log('Usuário cadastrado com sucesso!');
    } catch (error) {
      console.error('Erro ao cadastrar:', error);
      setError('Erro ao cadastrar usuário. Por favor, tente novamente.');
    }
  };

  const handleCheckboxChange = () => {
    setIsProfissional(!isProfissional);
  };

  return (
    <div className="container2" style={{ background: 'rgba(255, 255, 255, 0.0)' }}>
      <Carousel
        showThumbs={false}
        showStatus={false}
        showIndicators={false}
        autoPlay={true}
        interval={5000}
        infiniteLoop={true}
        showArrows={false}
        className="carousel-background"
      >
        <div>
          <img src="/public/idosos2.png" alt="Imagem 1" />
        </div>
        <div>
          <img src="/public/indio.png" alt="Imagem 2" />
        </div>
        <div>
          <img src="/public/familia.png" alt="Imagem 3" />
        </div>
      </Carousel>

      <div className='container-central'>
        <h1 className='titulo-cadastro'>Cadastro</h1>
        <form onSubmit={handleSubmit}>
          <div className="input-container">
            <label className='label'>Nome</label>
            <input
              placeholder='Nome de usuário'
              className='input-nome'
              maxLength={40}
              value={nome}
              onChange={(e) => setNome(e.target.value)}
              required
            />
          </div>

          <div className="input-container">
            <label className='label'>Email</label>
            <input
              type="email"
              placeholder='Gmail'
              className='input-gmail1'
              maxLength={27}
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </div>

          <div className="input-container">
            <label className='label'>Senha</label>
            <div className="input-wrapper">
              <input
                placeholder='Senha'
                type={passwordVisible ? 'text' : 'password'}
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                className='input-senha'
                maxLength={21}
                required
              />
            </div>
          </div>

          <div className="input-container">
            <label className='label'>CPF</label>
            <input
              type="text"
              placeholder='CPF'
              className='input-cpf'
              maxLength={11}
              value={cpf}
              onChange={(e) => setCpf(e.target.value)}
              required
            />
          </div>

          <div className="input-container">
            <label className='label'>Data de Nascimento</label>
            <input
              type="date"
              placeholder='Data de Nascimento'
              className='input-dataNascimento'
              value={dataNascimento}
              onChange={(e) => setDataNascimento(e.target.value)}
              required
            />
          </div>

          <div className="input-container">
            <label className='label'>Gênero</label>
            <select
              className='input-genero'
              value={genero}
              onChange={(e) => setGenero(e.target.value)}
              required
            >
              <option value="">Selecione um Gênero</option>
              {generos.map((gen, index) => (
                <option key={index} value={gen}>{gen}</option>
              ))}
            </select>
          </div>

          <div className="input-container">
            <label className='label'>
              <input
                type="checkbox"
                checked={isProfissional}
                onChange={handleCheckboxChange}
              /> Sou profissional
            </label>
          </div>

          {isProfissional && (
            <div className="input-container">
              <label className='label'>Número do COREN</label>
              <input
                type="text"
                placeholder='Número do COREN'
                className='input-coren'
                value={coren}
                onChange={(e) => setCoren(e.target.value)}
                maxLength={10}
                required={isProfissional}
              />
            </div>
          )}

          <Botao>Cadastrar</Botao>
          <p className='link'><Link to='/login'>Já possui conta? Aperte aqui!</Link></p>

          {error && <p className="error-message">{error}</p>}
          {success && <p className="success-message">{success}</p>}
        </form>
      </div>
    </div>
  );
}

export default Cadastro;

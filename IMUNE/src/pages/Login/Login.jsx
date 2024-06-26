// eslint-disable-next-line no-unused-vars
import React, { useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEye, faEyeSlash, faEnvelope } from '@fortawesome/free-solid-svg-icons';
import { Link, useNavigate } from 'react-router-dom';
import { Carousel } from 'react-responsive-carousel';
import 'react-responsive-carousel/lib/styles/carousel.min.css';
import axios from 'axios';
import './Login.css'; // Importe o arquivo de estilos CSS adequado

function Login() {
  const navigate = useNavigate();
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [passwordVisible, setPasswordVisible] = useState(false);
  const [error, setError] = useState('');

  const togglePasswordVisibility = () => {
    setPasswordVisible(!passwordVisible);
  };

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post('http://localhost:8080/api/login', {
        email,
        password
      });

      if (response.status === 200) {
        console.log('Login bem-sucedido!', response.data);
        // Aqui você pode salvar o token ou outros dados de login, se necessário
        navigate('/home'); // Redirecione para a página home após o login bem-sucedido
      } else {
        setError('Erro ao fazer login. Por favor, tente novamente.');
      }
    } catch (error) {
      console.error('Erro ao fazer login:', error);
      setError('Erro ao fazer login. Por favor, tente novamente.');
    }
  };

  return (
    <div className="container2">
      {/* Carrossel de Imagens */}
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
          <img src="/public/pexels-kampus-8949914.jpg" alt="Imagem 1" />
        </div>
        <div>
          <img src="/public/mulher.jpg" alt="Imagem 2" />
        </div>
        <div>
          <img src="/public/pexels-cdc-library-3997722.jpg" alt="Imagem 3" />
        </div>
      </Carousel>

      <div className='container-central'>
        <h1 className='titulo-login'>Login</h1>
        <form onSubmit={handleLogin}>
          <div className="input-container">
            <label className='label'>Gmail</label>
            <div className="input-wrapper">
              <input
                type="text"
                placeholder='Gmail'
                className='input-gmail'
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                required
              />
              <FontAwesomeIcon icon={faEnvelope} className="icone" />
            </div>
          </div>

          <div className="input-container">
            <label className='label'>Senha</label>
            <div className="input-wrapper">
              <input
                type={passwordVisible ? 'text' : 'password'}
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                placeholder='Senha'
                className='input-senha'
                maxLength={21}
                required
              />
              <FontAwesomeIcon
                icon={passwordVisible ? faEyeSlash : faEye}
                className="icone"
                onClick={togglePasswordVisibility}
              />
            </div>
          </div>

          <button type="submit" className="butao">Login</button>
          <p className='link'><Link to='/recuperacao'>Aperte aqui para recuperar conta</Link></p>
          {error && <p className="error-message">{error}</p>}
        </form>
      </div>
    </div>
  );
}

export default Login;

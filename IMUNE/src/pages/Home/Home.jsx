// eslint-disable-next-line no-unused-vars
import React from 'react';
import { faCalendarAlt, faMapMarkerAlt, faSyringe, faUser } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import './Home.css';

const Home = () => {
  return (
    <div className='home-container'>
      {/* Carrossel de Imagens em primeiro plano */}
      <div className='parte-um'>
        <div className='imagem-container'>
          <img src="/public/Captura de Tela (88).png" alt="Imagem 1" style={{ margin: 0 }} />
        </div>
      </div>

      {/* Segunda parte com os cards de serviço */}
      <div className='parte-dois'>
        <div className='card'>
          <div className='card-content'>
            <FontAwesomeIcon icon={faSyringe} size='3x' />
            <h2>Vacinas</h2>
            <p>Visualize suas vacinas, e tomem cuidado para que suas vacinas não fiquem pendentes.</p>
          </div>
        </div>
        <div className='card'>
          <div className='card-content'>
            <FontAwesomeIcon icon={faCalendarAlt} size='3x' />
            <h2>Agenda</h2>
            <p>Receba lembretes para vacinações futuras, você recebera lembretes registrados no seu calendario.</p>
          </div>
        </div>
        <div className='card'>
          <div className='card-content'>
            <FontAwesomeIcon icon={faMapMarkerAlt} size='3x' />
            <h2>Unidade</h2>
            <p>Encontre a unidade de saúde mais próxima e verifique os serviços disponíveis.</p>
          </div>
        </div>
        <div className='card'>
          <div className='card-content'>
            <FontAwesomeIcon icon={faUser} size='3x' />
            <h2>Perfil</h2>
            <p>Gerencie seu perfil, incluindo informações pessoais e profissionais.</p>
          </div>
        </div>
      </div>

      {/* Terceira parte com imagem e texto */}
      <div className='parte-tres'>
        <div className='lado-esquerdo'>
          <img src='/public/ImuniGirl.png' alt='Imagem Informativa' className='fotos' style={{ maxWidth: '100%'}} />
        </div>
        <div className='lado-direito'>
          <em className="text-destaque">ImuniGirl</em>
          <p className="text-descricao">Olá! Eu sou a ImuneGirl, sua assistente virtual feminina! <br></br> Como ImuneGirl, estou aqui para facilitar sua jornada em manter suas vacinações sempre em dia. Comigo, você pode gerenciar suas vacinas, receber lembretes importantes e encontrar facilmente unidades de saúde próximas. Estou à disposição para ajudar você a cuidar da sua saúde de forma prática e eficiente.</p>
        </div>
      </div>

      {/* Quarta parte com vídeo vertical e aviso */}
      <div className='parte-quatro'>
        <div className='lado-direito'>
          <em className="text-destaque">ImuneBoy</em>
          <p className="text-descricao">Olá a todos! <br></br> Eu sou o ImuneBoy, o assistente virtual masculino aqui para ajudar você! Com uma vasta gama de conhecimentos e habilidades, estou aqui para tornar sua experiência mais fácil e eficiente. De consultas sobre vacinas a informações sobre saúde, estou pronto para ser seu guia confiável. Vamos juntos explorar o mundo da imunização e cuidados preventivos. Conte comigo para estar sempre à disposição!</p>
        </div>
        <div className='aviso-container'>
          <img src='/public/ImuniBoy.png' alt='Imagem Informativa' className='fotos' style={{ maxWidth: '100%'}} />
        </div>
      </div>

      {/* Parte 6 - Rodapé */}
      <footer className='parte-seis'>
        <p>&copy; 2024 ImuniCheck. Todos os direitos reservados.</p>
        <p>
          <a href='/politica-de-privacidade'>Política de Privacidade</a> | <a href='/termos-de-uso'>Termos de Uso</a>
        </p>
      </footer>
    </div>
  );
};

export default Home;

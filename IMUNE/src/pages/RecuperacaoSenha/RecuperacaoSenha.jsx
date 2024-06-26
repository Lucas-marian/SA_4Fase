import { useState } from 'react';
// eslint-disable-next-line no-unused-vars
import { Link } from 'react-router-dom';
import { Carousel } from 'react-responsive-carousel';
import 'react-responsive-carousel/lib/styles/carousel.min.css';
import axios from 'axios';
import './RecuperacaoSenha.css';

function RecuperacaoSenha() {
    const [email, setEmail] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
 

    const handleResetPassword = async () => {
        if (newPassword !== confirmPassword) {
            alert('Passwords do not match');
            return;
        }

        try {   
            const response = await axios.put('/usuarios/senha', { email, newPassword });

            if (response.status === 200) {
                alert('Password reset successfully');
                // Adicione qualquer ação adicional aqui, como redirecionar ou limpar campos
            } else {
                alert('Failed to reset password');
            }
        } catch (error) {
            console.error('Erro ao redefinir a senha:', error);
            alert('Failed to reset password');
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
                    <img src="/homem.jpg" alt="Imagem 1" />
                </div>
                <div>
                    <img src="/pexels-kampus-8949917.jpg" alt="Imagem 2" />
                </div>
                <div>
                    <img src="/menino 2.png" alt="Imagem 3" />
                </div>
            </Carousel>

            <div className='container-central'>
                <h1 className='titulo-recuperacao'>Recuperar Senha</h1>

                <div className="input-container">
                    <label className='label'>Email</label>
                    <input
                        type="email"
                        placeholder='Digite seu email'
                        className='input-email'
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                </div>

                <div className="input-container">
                    <label className='label'>Senha Nova</label>
                    <input
                        type="password"
                        placeholder='Senha nova'
                        className='input-senha-nova'
                        value={newPassword}
                        onChange={(e) => setNewPassword(e.target.value)}
                    />
                </div>

                <div className="input-container">
                    <label className='label'>Confirme a Senha</label>
                    <input
                        type="password"
                        placeholder='Digite de novo'
                        className='input-senha-nova2'
                        value={confirmPassword}
                        onChange={(e) => setConfirmPassword(e.target.value)}
                    />
                </div>

                <button className="botao-recuperar" onClick={handleResetPassword}>Redefinir</button>
            </div>
        </div>
    );
}

export default RecuperacaoSenha;

// eslint-disable-next-line no-unused-vars
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './MeusDados.css';

function MeusDados() {
  const initialState = {
    nomeCompleto: '',
    dataNascimento: '',
    categoria: '',
    cpf: '',
    cep: '',
    endereco: '',
    numeroCasa: '',
    bairro: '',
    cidade: '',
    estado: '',
    profissional: false,
    numeroCoren: '',
    genero: '',
  };

  const [profileData, setProfileData] = useState({ ...initialState, nomeCompleto: 'Usuário' });
  const [isEditing, setIsEditing] = useState(false);
  const [cepError, setCepError] = useState('');

  useEffect(() => {
    // Fetch the profile data from the backend when the component mounts
    const fetchProfileData = async () => {
      try {
        const response = await axios.get('/api/usuarios', 2);
        setProfileData(response.data);
      } catch (error) {
        console.error('Erro ao buscar dados do perfil:', error);
      }
    };

    fetchProfileData();
  }, []);

  const fetchAddressByCep = async (cep) => {
    try {
      const response = await axios.get(`https://viacep.com.br/ws/${cep}/json/`);
      setProfileData((prevData) => ({
        ...prevData,
        endereco: response.data.logradouro || '',
        bairro: response.data.bairro || '',
        cidade: response.data.localidade || '',
        estado: response.data.uf || '',
      }));
      setCepError('');
    } catch (error) {
      console.error('Erro ao buscar CEP:', error.message);
      setCepError('CEP não encontrado');
      setProfileData((prevData) => ({
        ...prevData,
        endereco: '',
        bairro: '',
        cidade: '',
        estado: '',
      }));
    }
  };

  useEffect(() => {
    if (cepError) {
      const timer = setTimeout(() => {
        setCepError('');
      }, 3000);
      return () => clearTimeout(timer);
    }
  }, [cepError]);

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setProfileData((prevData) => ({
      ...prevData,
      [name]: type === 'checkbox' ? checked : value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log('Profile data:', profileData);
    setIsEditing(false);
    setProfileData((prevData) => ({
      ...prevData,
      nomeCompleto: prevData.nomeCompleto || 'Usuário',
    }));
  };

  const handleEdit = () => {
    setIsEditing(true);
  };

  const handleDelete = () => {
    setProfileData({ ...initialState, nomeCompleto: 'Usuário' });
    setIsEditing(false);
  };

  const handleCEPChange = (e) => {
    const cep = e.target.value.replace(/\D/g, '');
    setProfileData((prevData) => ({
      ...prevData,
      cep: e.target.value,
    }));
    if (cep.length === 8) {
      fetchAddressByCep(cep);
    }
  };

  return (
    <div className="profile-page">
      <div className="video-background">
        <video autoPlay loop muted className="bg-video">
          <source src="/4132751-hd_1280_720_25fps.mp4" type="video/mp4" />
        </video>
      </div>
      <div className="profile-container">
        <div className="profile-sidebar">
          <img src="/perfil.png" alt="Perfil" className="profile-pic" />
          <div>
            <h2 className='saudacoes'>Olá, {profileData.nomeCompleto}</h2>
            <h3 className='saudacoes'>Bem-vindo ao seu perfil</h3>
          </div>
        </div>
        <div className="profile-content">
          <h2 className='format'>Informações Pessoais</h2>
          {isEditing ? (
            <form onSubmit={handleSubmit}>
              <div className="form-group">
                <label>Nome Completo</label>
                <input type="text" name="nomeCompleto" value={profileData.nomeCompleto} onChange={handleChange} required />
              </div>
              
              <div className="form-group">
                <label>Categoria</label>
                <select name="categoria" value={profileData.categoria} onChange={handleChange} required>
                  <option value="">Selecione...</option>
                  <option value="Criança">Criança</option>
                  <option value="Adolescente">Adolescente</option>
                  <option value="Adulto">Adulto</option>
                  <option value="Idoso">Idoso</option>
                  <option value="Gestante">Gestante</option>
                </select>
              </div>
              <div className="form-group">
                <label>CPF</label>
                <input type="text" name="cpf" value={profileData.cpf} onChange={handleChange} required />
              </div>
              <div className="form-group">
                <label>Gênero</label>
                <select name="genero" value={profileData.genero} onChange={handleChange} required>
                  <option value="">Selecione...</option>
                  <option value="Masculino">Masculino</option>
                  <option value="Feminino">Feminino</option>
                </select>
              </div>

              <div className="form-group">
                <label>CEP</label>
                <input type="text" name="cep" value={profileData.cep} onChange={handleCEPChange} required />
              </div>
              <div className="form-group">
                <label>Endereço</label>
                <input type="text" name="endereco" value={profileData.endereco} onChange={handleChange} required />
              </div>
              <div className="form-group">
                <label>Bairro</label>
                <input type="text" name="bairro" value={profileData.bairro} onChange={handleChange} required />
              </div>
              <div className="form-group">
                <label>Cidade</label>
                <input type="text" name="cidade" value={profileData.cidade} onChange={handleChange} required />
              </div>
              <div className="form-group">
                <label>Estado</label>
                <input type="text" name="estado" value={profileData.estado} onChange={handleChange} required />
              </div>
              <div className="form-group">
                <label>Número da Casa</label>
                <input type="text" name="numeroCasa" value={profileData.numeroCasa} onChange={handleChange} required />
              </div>
    
              <div className="form-actions">
                <button type="button" className="discard-btn" onClick={() => setIsEditing(false)}>
                  Descartar Alterações
                </button>
                <button type="submit" className="save-btn">
                  Salvar Alterações
                </button>
              </div>
            </form>
          ) : (
            <div>
              <p className='dados'><strong>Nome Completo:</strong> {profileData.nomeCompleto}</p>
              <p className='dados'><strong>Data de Nascimento:</strong> {profileData.dataNascimento}</p>
              <p className='dados'><strong>Categoria:</strong> {profileData.categoria}</p>
              <p className='dados'><strong>CPF:</strong> {profileData.cpf}</p>
              <p className='dados'><strong>Gênero:</strong> {profileData.genero}</p>
              <p className='dados'><strong>CEP:</strong> {profileData.cep}</p>
              <p className='dados'><strong>Endereço:</strong> {profileData.endereco}</p>
              <p className='dados'><strong>Bairro:</strong> {profileData.bairro}</p>
              <p className='dados'><strong>Cidade:</strong> {profileData.cidade}</p>
              <p className='dados'><strong>Estado:</strong> {profileData.estado}</p>
              <p className='dados'><strong>Número da Casa:</strong> {profileData.numeroCasa}</p>
              {profileData.profissional && (
                <p className='dados'><strong>Número do COREN:</strong> {profileData.numeroCoren}</p>
              )}
              <div className="form-actions">
                <button className="edit-btn" onClick={handleEdit}>
                  Editar
                </button>
                <button className="delete-btn" onClick={handleDelete}>
                  Apagar Dados
                </button>
              </div>
            </div>
          )}
          {cepError && <p className="error-message">{cepError}</p>}
        </div>
      </div>
    </div>
  );
}

export default MeusDados;

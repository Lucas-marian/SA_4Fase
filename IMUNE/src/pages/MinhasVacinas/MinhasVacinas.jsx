// eslint-disable-next-line no-unused-vars
import React, { useState } from 'react';
import './MinhasVacinas.css';

function MinhasVacinas() {
  const [vacinas, setVacinas] = useState([]);

  const handleCadastroVacina = (email, lote, data, doses, dataRetorno, status) => {
    const novaVacina = { email, lote, data, doses, dataRetorno, status };
    setVacinas([...vacinas, novaVacina]);
  };

  return (
    <div>
      <video className="video-background" autoPlay loop muted>
        <source src="/public/8492747-hd_1920_1080_30fps.mp4" type="video/mp4" />
      </video>
      <div className="container">
        <h2 className="titulo">Caderno de Vacinação</h2>

        {vacinas.length === 0 ? (
          <p className="mensagem-vazio">Nenhuma vacina cadastrada.</p>
        ) : (
          <div className="vacinas-container">
            {vacinas.map((vacina, index) => (
              <div key={index} className="card-vacina">
                <div className="vacina-info">
                  <p><strong>Email do Usuário:</strong> {vacina.email}</p>
                  <p><strong>Lote da Vacina:</strong> {vacina.lote}</p>
                  <p><strong>Data de Aplicação:</strong> {vacina.data}</p>
                  <p><strong>Doses Aplicadas:</strong> {vacina.doses}</p>
                  <p><strong>Data de Retorno:</strong> {vacina.dataRetorno || 'Não marcado'}</p>
                  <p><strong>Status:</strong> {vacina.status === 'ok' ? <span className="status-ok">OK</span> : <span className="status-pendente">Pendente</span>}</p>
                </div>
              </div>
            ))}
          </div>
        )}
        {/* Formulário para cadastrar nova vacina */}
        <form
          className="form-cadastro"
          onSubmit={(e) => {
            e.preventDefault();
            const formData = new FormData(e.target);
            const email = formData.get('email');
            const lote = formData.get('lote');
            const data = formData.get('data');
            const doses = parseInt(formData.get('doses'));
            const dataRetorno = formData.get('dataRetorno');
            const status = formData.get('status');
            handleCadastroVacina(email, lote, data, doses, dataRetorno, status);
            e.target.reset();
          }}
        >
          <h4>Cadastrar Nova Vacina</h4>
          <label>
            Email do Usuário:
            <input type="email" name="email" required />
          </label>
          <br />
          <label>
            Lote da Vacina:
            <input type="text" name="lote" required />
          </label>
          <br />
          <label>
            Data de Aplicação:
            <input type="date" name="data" required />
          </label>
          <br />
          <label>
            Quantidade de Doses:
            <input type="number" name="doses" min="1" defaultValue="1" required />
          </label>
          <br />
          <label>
            Data de Retorno (opcional):
            <input type="date" name="dataRetorno" />
          </label>
          <br />
          <label>
            Status:
            <select name="status" required>
              <option value="ok">OK</option>
              <option value="pendente">Pendente</option>
            </select>
          </label>
          <br />
          <button type="submit">Cadastrar</button>
        </form>
      </div>
    </div>
  );
}

export default MinhasVacinas;

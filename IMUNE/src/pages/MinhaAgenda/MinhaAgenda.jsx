// eslint-disable-next-line no-unused-vars
import React, { useState } from 'react';
import moment from 'moment';
import 'moment/locale/pt-br';
import { Calendar, momentLocalizer } from 'react-big-calendar';
import 'react-big-calendar/lib/css/react-big-calendar.css';
import { FaPlus, FaEdit, FaTrash } from 'react-icons/fa';
import Modal from 'react-modal';
import './MinhaAgenda.css';

moment.locale('pt-br');
const localizer = momentLocalizer(moment);

Modal.setAppElement('#root');

const MinhaAgenda = () => {
  const [campanhas, setCampanhas] = useState([]);
  const [campanhaSelecionada, setCampanhaSelecionada] = useState(null);
  const [novaCampanha, setNovaCampanha] = useState({ categoria: '', nomeCampanha: '', dataInicio: '', dataTermino: '', descricao: '' });
  const [modalIsOpen, setModalIsOpen] = useState(false);
  const [editIndex, setEditIndex] = useState(null);
  const categorias = ['Criança', 'Adolescente', 'Adulto', 'Idoso', 'Gestante'];

  const openModal = () => setModalIsOpen(true);

  const closeModal = () => {
    setModalIsOpen(false);
    setNovaCampanha({ categoria: '', nomeCampanha: '', dataInicio: '', dataTermino: '', descricao: '' });
    setEditIndex(null);
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNovaCampanha({ ...novaCampanha, [name]: value });
  };

  const adicionarCampanha = () => {
    if (novaCampanha.nomeCampanha.trim() === '') return;

    const novaCampanhaFormatada = {
      ...novaCampanha,
      dataInicio: moment(novaCampanha.dataInicio).startOf('day').toDate(),
      dataTermino: moment(novaCampanha.dataTermino).startOf('day').toDate(),
    };

    if (editIndex !== null) {
      const novasCampanhas = [...campanhas];
      novasCampanhas[editIndex] = novaCampanhaFormatada;
      setCampanhas(novasCampanhas);
    } else {
      setCampanhas([...campanhas, novaCampanhaFormatada]);
    }

    closeModal();
  };

  const editarCampanha = (indice) => {
    const campanhaParaEditar = campanhas[indice];
    setNovaCampanha({
      ...campanhaParaEditar,
      dataInicio: moment(campanhaParaEditar.dataInicio).format('YYYY-MM-DD'),
      dataTermino: moment(campanhaParaEditar.dataTermino).format('YYYY-MM-DD'),
    });
    setEditIndex(indice);
    openModal();
  };

  const apagarCampanha = () => {
    if (campanhaSelecionada === null) return;
    const novasCampanhas = campanhas.filter((_, index) => index !== campanhaSelecionada.id);
    setCampanhas(novasCampanhas);
    setCampanhaSelecionada(null);
  };

  const events = campanhas.flatMap((campanha, index) => [
    {
      title: `${campanha.nomeCampanha} - Início`,
      start: new Date(campanha.dataInicio),
      end: new Date(campanha.dataInicio),
      allDay: true,
      resource: { ...campanha, id: index },
      id: index * 2,
    },
    {
      title: `${campanha.nomeCampanha} - Término`,
      start: new Date(campanha.dataTermino),
      end: new Date(campanha.dataTermino),
      allDay: true,
      resource: { ...campanha, id: index },
      id: index * 2 + 1,
    },
  ]);

  return (
    <div className="minha-agenda">
      <video className="background-video" autoPlay muted loop>
        <source src="8945487-uhd_3840_2160_25fps.mp4" type="video/mp4" />
      </video>
      <div className="main-content">
        <div className="content">
          <div className="calendar">
            <h1 className='titulo'>Meu Calendário</h1>
            <div className="calendar-header">
              <div className="button-group">
                <button className="add-task" onClick={openModal}>
                  <FaPlus /> Adicionar Nova Campanha
                </button>
                {campanhaSelecionada && (
                  <>
                    <button className="edit-task" onClick={() => editarCampanha(campanhaSelecionada.resource.id)}>
                      <FaEdit /> Editar
                    </button>
                    <button className="delete-task" onClick={apagarCampanha}>
                      <FaTrash /> Apagar
                    </button>
                  </>
                )}
              </div>
            </div>
            <Calendar
              localizer={localizer}
              events={events}
              startAccessor="start"
              endAccessor="end"
              style={{ height: 500 }}
              messages={{
                next: "Próximo",
                previous: "Anterior",
                today: "Hoje",
                month: "Mês",
                week: "Semana",
                day: "Dia",
                agenda: "Agenda",
                date: "Data",
                time: "Hora",
                event: "Evento",
                noEventsInRange: "Não há eventos nesta faixa de datas.",
                showMore: total => `+${total} mais`,
              }}
              onSelectEvent={(event) => setCampanhaSelecionada(event)}
            />
          </div>
        </div>
      </div>
      <Modal
        isOpen={modalIsOpen}
        onRequestClose={closeModal}
        contentLabel={editIndex !== null ? "Editar Campanha" : "Adicionar Nova Campanha"}
        className="modal"
        overlayClassName="modal-overlay"
      >
        <h2>{editIndex !== null ? 'Editar Campanha' : 'Adicionar Nova Campanha'}</h2>
        <form>
          <label>
            Categoria:
            <select name="categoria" value={novaCampanha.categoria} onChange={handleInputChange}>
              <option value="">Selecione</option>
              {categorias.map((categoria, index) => (
                <option key={index} value={categoria}>{categoria}</option>
              ))}
            </select>
          </label>
          <label>
            Nome da Campanha:
            <input type="text" name="nomeCampanha" value={novaCampanha.nomeCampanha} onChange={handleInputChange} />
          </label>
          <label>
            Data de Início:
            <input type="date" name="dataInicio" value={novaCampanha.dataInicio} onChange={handleInputChange} />
          </label>
          <label>
            Data de Término:
            <input type="date" name="dataTermino" value={novaCampanha.dataTermino} onChange={handleInputChange} />
          </label>
          <label>
            Descrição:
            <textarea name="descricao" value={novaCampanha.descricao} onChange={handleInputChange} />
          </label>
          <div className="modal-buttons">
            <button type="button" onClick={adicionarCampanha}>{editIndex !== null ? 'Salvar' : 'Adicionar'}</button>
            <button type="button" onClick={closeModal}>Cancelar</button>
          </div>
        </form>
      </Modal>
    </div>
  );
};

export default MinhaAgenda;

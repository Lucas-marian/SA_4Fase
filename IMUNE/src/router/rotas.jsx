import { createBrowserRouter } from "react-router-dom";
import App from '../App.jsx'
import Cadastro from '../pages/Cadastro/Cadastro.jsx';
import Home from "../pages/Home/Home.jsx";
import Login from "../pages/Login/Login.jsx";
import MeusDados from '../pages/MeusDados/MeusDados.jsx';
import MinhaAgenda from '../pages/MinhaAgenda/MinhaAgenda.jsx';
import MinhaUnidade from '../pages/MinhaUnidade/MinhaUnidade.jsx';
import MinhasVacinas from '../pages/MinhasVacinas/MinhasVacinas.jsx';
import RecuperacaoSenha from '../pages/RecuperacaoSenha/RecuperacaoSenha.jsx';

const router = createBrowserRouter([
    {
        path: "/",
        element: <App />,
        children: [
            {path: "/home", element: <Home />},
            {path: '/minhas-vacinas', element: <MinhasVacinas/>},
            {path: '/minha-unidade', element: <MinhaUnidade/>},
            {path: '/recuperacao', element: <RecuperacaoSenha/>},
            {path: '/meus-dados', element: <MeusDados/>},
            {path: '/minha-agenda', element: <MinhaAgenda/>},
    
        ]
    },{

          path: "cadastro",
          element: <Cadastro />,

    },{

        path: "/login",
        element: <Login />,

    }
])

export default router;
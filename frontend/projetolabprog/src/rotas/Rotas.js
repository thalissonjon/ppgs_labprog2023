import React, { useState } from 'react';
import {Route, Routes, HashRouter} from 'react-router-dom'

import Docente from '../views/Docente'
import Inicio from '../views/inicio'
import Programa from '../views/programa'
import Producao from '../views/Producao'
import Login from '../componentes/login/Login';

export default function Rotas() {
    const [token, setToken] = useState();

    if(!token) {
        return <Login setToken={setToken} />
      }

    return (
        <HashRouter>
            <Routes>
                <Route path="/" element={<Programa/>} />
                <Route path="/docente" element={<Docente/>} /> 
                <Route path="/producao" element={<Producao/>} />        
            </Routes>
        </HashRouter>
    )
}


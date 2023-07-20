import React, { useState } from 'react';
import {Route, Routes, HashRouter} from 'react-router-dom'

import Docente from '../views/Docente'
import Inicio from '../views/inicio'
import Programa from '../views/programa'
import Producao from '../views/Producao'
import Login from '../componentes/login/Login';
import Tecnica from '../views/Tecnica' 
import Token from '../componentes/login/Token';

export default function Rotas() {
    const { token, setToken } = Token(); // vamos ser direcionados para a pagina acessada anteriormente pois o usestate está sendo utilizado no hook personalizado
    // function setToken(usuarioToken) {
    //     sessionStorage.setItem('token', JSON.stringify(usuarioToken)); // Pega o token e a string como segundo argumento, tem que converter o token de um objeto pra uma string utilizando o stringify
    // }
    
    // function getToken() {
    //     const tokenString = sessionStorage.getItem('token'); // retorna a string
    //     const usuarioToken = JSON.parse(tokenString); // convertendo
    //     return usuarioToken?.token // valor do token / precisa ser ? pois no primeiro acesso o sessionStorage.getItem('token') vai ser indefinido
    // }

    if(!token) {
        return <Login setToken={setToken} />
    }

    return (
        <HashRouter>
            <Routes>
                <Route path="/" element={<Programa/>} />
                <Route path="/programa" element={<Programa/>} />
                <Route path="/docente/:id" element={<Docente/>} />
                <Route path="/producao" element={<Producao token={token} />} />
                <Route path="/tecnica" element={<Tecnica token={token}/>} />
                        
            </Routes>
        </HashRouter>
    )
}


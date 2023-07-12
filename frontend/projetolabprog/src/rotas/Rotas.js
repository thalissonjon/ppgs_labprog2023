import React from 'react'
import {Route, Routes, HashRouter} from 'react-router-dom'

import Docente from '../views/Docente'
import Inicio from '../views/inicio'
import Programa from '../views/programa'
import Producao from '../views/Producao'

export default function Rotas() {
    return (
        <HashRouter>
            <Routes>
                <Route path="/" element={<Inicio/>} />
                <Route path="/programa" element={<Programa/>} />
                <Route path="/docente" element={<Docente/>} /> 
                <Route path="/producao" element={<Producao/>} />        
            </Routes>
        </HashRouter>
    )
}


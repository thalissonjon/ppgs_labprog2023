import React from 'react'
import {Route, Routes, HashRouter} from 'react-router-dom'

import Docente from '../views/Docente'
import Home from '../views/Producao'

export default function Rotas() {
    return (
        <HashRouter>
            <Routes>
                <Route path="/docente" element={<Docente/>} /> 
                <Route path="/home" element={<Home />} />                
            </Routes>
        </HashRouter>
    )
}


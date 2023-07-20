import { useEffect } from "react";
import {useState} from 'react';

import Header from "../componentes/Header";

import Navbar from "../componentes/Navbar";


import axios from 'axios';
import FiltrosProd from "../componentes/FiltrosProd";

import TableTecOri from "../componentes/TableTecOri"

const client = axios.create({
    baseURL:"http://localhost:8080/api/docente/"
})

export default function Tecnica (props) {
    const {token} = props;

    const [docentes, setDocentes] = useState([])
    const [docSel, setDocSel] = useState([1]);

    const [tecnicas, setTecnicas] = useState([{}]);
    const [orientacoes, setOrientacoes] = useState([])

    function onSearch() {
        client.get(`/obter_tecnicas/${docSel}`)
        .then(                
            (response) => {
                console.log(response.data)
                setTecnicas(response.data)
            }
        ).catch(error => {
            console.log(error.response);
        });
        client.get(`/obter_orientacoes/${docSel}`)
        .then(
            (response) => {
                console.log(response.data)
                setOrientacoes(response.data)
            }
        ).catch(error => {
            console.log(error.response);
        });
    }

    function filter(){
        client.get(`obterDocentes`)
        .then(                
            (response) => {
                console.log(response.data)
                setDocentes(response.data)
            }
        ).catch(error => {
            console.log(error.response);
        });
    }

    useEffect( () =>{
        document.body.classList.add('hold-transition', 'layout-top-nav')    
        onSearch();
        filter();       
        },[]
    ); 
    
    return (
        <div className="wrapper">
            <Navbar />
            <div className="content-wrapper">
                <Header titulo="Técnicas" />
                <div className="content">      
                <div className="container">
                    <div className="container-fluid">
                    <FiltrosProd pagina="Docente:" dados={docentes}
                            filtroDado={docSel} onDadoChange={setDocSel} 
                            onSearch={onSearch}/>
                    <TableTecOri tecnicas={tecnicas} orientacoes={orientacoes} token={token}/>
                    </div>
                </div>
                </div>
            </div>
        </div>        
    );
}
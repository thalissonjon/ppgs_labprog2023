import { useEffect } from "react";
import {useState} from 'react';

import axios from 'axios';

import Filtros from "../componentes/Filtros";
import Header from "../componentes/Header";
import Navbar from "../componentes/Navbar";
import Indicadores from "../componentes/Indicadores";
import GraficoProdvsQualis from "../componentes/GraficoProdvsQualis";
import TableProgDoc from "../componentes/TableProgDoc";

const client = axios.create({
    baseURL:"http://localhost:8080/api/programa/"
})

export default function Programa () {

    const [programas, setProgramas] = useState([])
    const [progSel, setProgSel] = useState([1]);
    const [anoIni, setAnoIni] = useState(2017);
    const [anoFim, setAnoFim] = useState(2023);
    const [indicadores, setIndicadores] = useState({});
    const [grafico, setGrafico] = useState({});
    const [docentes, setDocentes] = useState([{}]);

    function onSearch() {
        client.get(`indicadores/${progSel}/${anoIni}/${anoFim}`)
            .then(                
                (response) => {
                    console.log(response.data)
                    setIndicadores(response.data)
                }
            ).catch(error => {
                console.log(error.response);
            });
        client.get(`obter_docentes/${progSel}`)
            .then(
                (response) => {
                    console.log(response.data)
                    setDocentes(response.data)
                }
            ).catch(error => {
                console.log(error.response);
            });
        client.get(`graficoProdvsQualis/${progSel}/${anoIni}/${anoFim}`)
            .then(
                (response) => {
                    console.log(response.data)
                    setGrafico(response.data)
                }
            ).catch(error => {
                console.log(error.response);
            });

    }

    function filter(){
        client.get(`obterProgramas`)
        .then(                
            (response) => {
                console.log(response.data)
                setProgramas(response.data)
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
                <Header titulo="Programas" />
                <div className="content">      
                <div className="container">
                    <div className="container-fluid">
                        <Filtros pagina = "Programa:" dados={programas}
                            filtroDado={progSel} onDadoChange={setProgSel} 
                            filtroAnoIni={anoIni} onAnoIniChange={setAnoIni}
                            filtroAnoFim={anoFim} onAnoFimChange={setAnoFim}
                            onSearch={onSearch}/>
                        <Indicadores dados = {indicadores}/>
                        <GraficoProdvsQualis dados = {grafico} />
                        <TableProgDoc dados = {docentes}/>    
                    </div>
                </div>
                </div>            
            </div>
        </div> 
    );
}
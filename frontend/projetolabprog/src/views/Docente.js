import { useEffect } from "react";
import {useState} from 'react';
import {useParams} from 'react-router-dom';

import axios from 'axios';

import Filtros from "../componentes/Filtros";
import Header from "../componentes/Header";
import Navbar from "../componentes/Navbar";
import Indicadores from "../componentes/Indicadores";
import GraficoProdPervsQualis from "../componentes/GraficoProdPervsQualis";
import GraficoProdCongvsQualis from "../componentes/GraficoProdCongvsQualis";

import TableDocOri from "../componentes/TableDocOri"
import TableDocProd from "../componentes/TableDocProd"
import TableDocTec from "../componentes/TableDocTec"

const client = axios.create({
    baseURL:"http://localhost:8080/api/docente/"
})

export default function Docente () {

    const { id } = useParams();
    const [docentes, setDocentes] = useState([])
    const [docSel, setDocSel] = useState([id]);
    const [anoIni, setAnoIni] = useState(2017);
    const [anoFim, setAnoFim] = useState(2023);

    const [indicadores, setIndicadores] = useState({});
    const [graficoPeriodico, setGraficoPeriodico] = useState({});
    const [graficoCongresso, setGraficoCongresso] = useState({});
    const [orientacoes, setOrientacoes] = useState([{}]);
    const [producoes, setProducoes] = useState([{}]);
    const [tecnicas, setTecnicas] = useState([{}]);

    function onSearch() {
        client.get(`/indicadores/${docSel}/${anoIni}/${anoFim}`)
        .then(                
            (response) => {
                console.log(response.data)
                setIndicadores(response.data)
            }
        ).catch(error => {
            console.log(error.response);
        });
        client.get(`/obter_orientacoesData/${docSel}/${anoIni}/${anoFim}`)
        .then(                
            (response) => {
                console.log(response.data)
                setOrientacoes(response.data)
            }
        ).catch(error => {
            console.log(error.response);
        });
        client.get(`/obter_producoesData/${docSel}/${anoIni}/${anoFim}`)
        .then(                
            (response) => {
                console.log(response.data)
                setProducoes(response.data)
            }
        ).catch(error => {
            console.log(error.response);
        });
        client.get(`/obter_tecnicasData/${docSel}/${anoIni}/${anoFim}`)
        .then(                
            (response) => {
                console.log(response.data)
                setTecnicas(response.data)
            }
        ).catch(error => {
            console.log(error.response);
        });
        client.get(`/graficoProdPervsQualis/${docSel}/${anoIni}/${anoFim}`)
        .then(                
            (response) => {
                console.log(response.data)
                setGraficoPeriodico(response.data)
            }
        ).catch(error => {
            console.log(error.response);
        });
        client.get(`/graficoProdCongvsQualis/${docSel}/${anoIni}/${anoFim}`)
        .then(                
            (response) => {
                console.log(response.data)
                setGraficoCongresso(response.data)
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
                <Header titulo="Docentes" />
                <div className="content">      
                <div className="container">
                    <div className="container-fluid">
                        <Filtros pagina = "Docente:" dados={docentes}
                            filtroDado={docSel} onDadoChange={setDocSel} 
                            filtroAnoIni={anoIni} onAnoIniChange={setAnoIni}
                            filtroAnoFim={anoFim} onAnoFimChange={setAnoFim}
                            onSearch={onSearch}/>
                        <Indicadores dados = {indicadores}/>
                        <GraficoProdPervsQualis dados = {graficoPeriodico} />
                        <GraficoProdCongvsQualis dados = {graficoCongresso} />
                        <TableDocOri dados = {orientacoes}/>
                        <TableDocProd dados = {producoes}/>
                        <TableDocTec dados = {tecnicas}/>
                    </div>
                </div>
                </div>            
            </div>
        </div> 
    );
}
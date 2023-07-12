import { useEffect } from "react";
import {Helmet} from 'react-helmet'


import Filtros from "../componentes/Filtros";
import Header from "../componentes/Header";
import OrientacaoTable from "../componentes/OrientacaoTable";
import ArtigosTable from "../componentes/ArtigosTable";
import Indicadores from "../componentes/Indicadores";
import GraficoProducaoCongresso from "../componentes/GraficoProducaoCongresso";
import GraficoProducaoPeriodico from "../componentes/GraficoProducaoPeriodico";
import Table from "../componentes/Table";


export default function Docente () {

    useEffect( () =>
        document.body.classList.add('hold-transition', 'layout-top-nav')    
    );

    return (
        <div className="wrapper">                
         
            
            <div className="content-wrapper">
                <Header titulo="Docente" />
                {/*<!-- Main content -->*/}
                <div className="content">      
                <div className="container">
                    <div className="container-fluid">
                    <Indicadores />
                    <GraficoProducaoPeriodico />
                    <GraficoProducaoCongresso />
                    <OrientacaoTable />
                    <ArtigosTable />
                    <Table />

                       

                      

    
                    </div>
                </div>
                </div>
            
            
            </div>
        </div> 
    );
}
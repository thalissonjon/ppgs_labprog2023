import { useEffect } from "react";
import {Helmet} from 'react-helmet'


import Filtros from "../componentes/Filtros";
import Header from "../componentes/Header";
import ProducaoQualis from "../componentes/ProducaoQualis";
import Indicadores from "../componentes/Indicadores";

export default function Docente () {

    useEffect( () =>
        document.body.classList.add('hold-transition', 'layout-top-nav')    
    );

    return (
        <div className="wrapper">                
         
            
            <div className="content-wrapper">
                <Header titulo="Programa" />
                {/*<!-- Main content -->*/}
                <div className="content">      
                <div className="container">
                    <div className="container-fluid">
                    <Indicadores />
                    <ProducaoQualis />

                       

                      

    
                    </div>
                </div>
                </div>
            
            
            </div>
        </div> 
    );
}
import { useEffect } from "react";


import Header from "../componentes/Header";

import Navbar from "../componentes/Navbar";
import Form_Producao from "../componentes/formProducao";
import Modell from "../componentes/modalOri";

export default function Producao () {

    useEffect( () =>
    document.body.classList.add('hold-transition', 'layout-top-nav')    
    );
    
    return (
        <div className="wrapper">
            <Navbar />
            <div className="content-wrapper">
                <Header titulo="Produções" />
                <div className="content">      
                <div className="container">
                    <div className="container-fluid">
                        <Form_Producao />
                    </div>
                </div>
                </div>
            </div>
        </div>        
    );
}
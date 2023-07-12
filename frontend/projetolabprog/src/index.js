import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import reportWebVitals from './reportWebVitals';
import Programa from './views/Producao';

import './admin-lte/plugins/fontawesome-free/css/all.min.css';
import './admin-lte/dist/css/adminlte.min.css';

import Rotas from './rotas/Rotas.js';

import Navbar from './componentes/Navbar';


const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <div className="hold-transition sidebar-mini layout-fixed">
          <div className="wrapper">
              <Navbar />
          </div>
        </div>
    <Rotas />
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();

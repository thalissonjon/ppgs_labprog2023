

import React, { useEffect, useRef } from 'react';
import $ from 'jquery';
import 'datatables.net';



export default function ArtigosTable({dados}) {
  const tableRef = useRef(null);

  useEffect( () => {
    document.body.classList.add('hold-transition', 'layout-top-nav');

    }, []
  )

  function onSearchs(tipo) {
    if(tipo=="P"){
      tipo = "Periódico"
    } else {
      tipo = "Congresso"
    }
    return tipo
  }

  const linhas = dados.map((i) => (
    <tr key={i.titulo}>
      <td>{i.titulo}</td>
            <td>{i.nomeLocal}</td>
            <td>{onSearchs(i.tipo)}</td>
            <td>{i.qualis}</td>
            <td>{i.ano}</td>
    </tr>
  ));

  return (
    <div className="card">
      <div className="card-header">
        <h3 className="card-title">Artigos</h3>
      </div>
      <div className="card-body">
        <table ref={tableRef} className="table table-bordered table-striped">
          <thead>
            <tr>
            <th>Título</th>
                    <th>Local</th>
                    <th>Tipo</th>
                    <th>Qualis</th>
                    <th>Ano</th>
            </tr>
          </thead>
          <tbody>{linhas}</tbody>
        </table>
      </div>
    </div>
  );
}


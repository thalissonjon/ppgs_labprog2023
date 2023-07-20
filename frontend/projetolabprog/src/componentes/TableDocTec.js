

import React, { useEffect, useRef } from 'react';
import $ from 'jquery';
import 'datatables.net';



export default function TecnicasTable({dados}) {
  const tableRef = useRef(null);

  useEffect( () => {
    document.body.classList.add('hold-transition', 'layout-top-nav');

    }, []
  )

  const linhas = dados.map((i) => (
    <tr key={i.titulo}>
      <td>{i.titulo}</td>
            <td>{i.tipo}</td>
            <td>{i.ano}</td>
    </tr>
  ));

  return (
    <div className="card">
      <div className="card-header">
        <h3 className="card-title">Tecnicas</h3>
      </div>
      <div className="card-body">
        <table ref={tableRef} className="table table-bordered table-striped">
          <thead>
            <tr>
            <th>Título</th>
                    <th>Tipo</th>
                    <th>Ano</th>
            </tr>
          </thead>
          <tbody>{linhas}</tbody>
        </table>
      </div>
    </div>
  );
}


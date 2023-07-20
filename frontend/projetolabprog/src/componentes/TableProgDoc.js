import React, { useEffect, useRef } from 'react';
import $ from 'jquery';
import 'datatables.net';


export default function TableProgDoc({dados}) {
  const tableRef = useRef(null);

  useEffect( () => {
    document.body.classList.add('hold-transition', 'layout-top-nav');
    }, []
  )

  const linhas = dados.map((i) => (
    <tr key={i.docente}>
      <td>{i.docente}</td>
            <td>{i.a1}</td>
            <td>{i.a2}</td>
            <td>{i.a3}</td>
            <td>{i.a4}</td>
            <td>{i.b1}</td>
            <td>{i.b2}</td>
            <td>{i.b3}</td>
            <td>{i.b4}</td>
            <td> <a href={`/#/docente/${i.id}`}>Mais</a> </td>
    </tr>
  ));

  return (
    <div className="card">
      <div className="card-header">
        <h3 className="card-title">Docentes</h3>
      </div>
      <div className="card-body">
        <table ref={tableRef} className="table table-bordered table-striped">
          <thead>
            <tr>
            <th>Docente</th>
                    <th>A1</th>
                    <th>A2</th>
                    <th>A3</th>
                    <th>A4</th>
                    <th>B1</th>
                    <th>B2</th>
                    <th>B3</th>
                    <th>B4</th>
                    <th>Detalhar</th>
            </tr>
          </thead>
          <tbody>{linhas}</tbody>
        </table>
      </div>
    </div>
  );
}

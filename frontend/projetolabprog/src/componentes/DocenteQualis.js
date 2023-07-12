import React, { useEffect, useRef } from 'react';
import $ from 'jquery';
import 'datatables.net';

const dados = [
  {docente: 'Alexandre César Muniz de Oliveira', A1:1, A2:0, A3:1, A4:0, B1:2, B2:0, B3:0, B4:0}
]

export default function OrientacaoTable() {
  const tableRef = useRef(null);

  useEffect(() => {
    const dataTable = $(tableRef.current).DataTable({
      searching: true, 
      paging: false,
      lengthChange: false,
      autoWidth: false,
      bDestroy: true,
      language: {
    
        search: 'Pesquisar: ',
        searchPlaceholder: 'Digite aqui para pesquisar...'
      }
    });

    // remover funçao padrao no cabeçalho
    $('.dataTables_filter input').unbind().bind('keyup', function () {
      dataTable.search(this.value).draw();
    });
  }, []);

  const linhas = dados.map((i) => (
    <tr key={i.docente}>
      <td>{i.docente}</td>
            <td>{i.A1}</td>
            <td>{i.A2}</td>
            <td>{i.A3}</td>
            <td>{i.A4}</td>
            <td>{i.B1}</td>
            <td>{i.B2}</td>
            <td>{i.B3}</td>
            <td>{i.B4}</td>
            <td> <a href="/#/programa">Mais</a> </td>
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
          <tfoot>
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
          </tfoot>
        </table>
      </div>
    </div>
  );
}

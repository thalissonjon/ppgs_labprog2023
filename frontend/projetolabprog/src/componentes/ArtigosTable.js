

import React, { useEffect, useRef } from 'react';
import $ from 'jquery';
import 'datatables.net';

const dados = [
    {titulo: 'Sobre otimização e desafios', local: 'Congresso brasileiro de', tipo: 'Evento', qualis: 'B1', ano: '2023'},
    {titulo: 'Sobre otimização e desafios', local: 'Congresso brasileiro de', tipo: 'Evento', qualis: 'B1', ano: '2023'},
    {titulo: 'Sobre otimização e desafios', local: 'Congresso brasileiro de', tipo: 'Evento', qualis: 'B1', ano: '2023'}

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
    <tr key={i.titulo}>
      <td>{i.titulo}</td>
            <td>{i.local}</td>
            <td>{i.tipo}</td>
            <td>{i.qualis}</td>
            <td>{i.ano}</td>
            <td> <a href="docente.html">Mais</a> </td>
    </tr>
  ));

  return (
    <div className="card">
      <div className="card-header">
        <h3 className="card-title">Orientações</h3>
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
                    <th>Detalhar</th>
            </tr>
          </thead>
          <tbody>{linhas}</tbody>
          <tfoot>
            <tr>
            <th>Título</th>
                    <th>Local</th>
                    <th>Tipo</th>
                    <th>Qualis</th>
                    <th>Ano</th>
            </tr>
          </tfoot>
        </table>
      </div>
    </div>
  );
}


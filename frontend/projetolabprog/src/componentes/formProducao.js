import React, { useEffect, useRef } from 'react';
import $ from 'jquery';
import 'datatables.net';
import ModalOrientacao from './modalOri';

import ModalEstatistica from './modalEst';

const dados = [
  {docente: 'Geraldo Braz Junior', ano:2021, titulo:'TituloEx0', local:'LocalEx0', orientacao:'Não', estatisticas:33},
  {docente: 'Mateus Abreu Basconcelos', ano:2002, titulo:'TituloEx1', local:'LocalEx1', orientacao:'Sim', estatisticas:35},
  {docente: 'Simara Rocha Castelo', ano:2017, titulo:'TituloEx2', local:'LocalEx2', orientacao:'Não', estatisticas:21},
  {docente: 'Simara Rocha Castelo', ano:2015, titulo:'TituloEx3', local:'LocalEx3', orientacao:'Sim', estatisticas:17},
  {docente: 'Mateus Abreu Basconcelos', ano:2012, titulo:'TituloEx4', local:'LocalEx4', orientacao:'Não', estatisticas:14},
  {docente: 'Mateus Abreu Basconcelos', ano:2012, titulo:'TituloEx4', local:'LocalEx4', orientacao:'Não', estatisticas:14}
];

export default function FormProducao() {
  const tableRef = useRef(null);

  useEffect(() => {
    const dataTable = $(tableRef.current).DataTable({
      searching: true, 
      paging: false,
      lengthChange: false,
      bDestroy: true,
      autoWidth: false,
      language: {

        search: 'Pesquisar: ',
        searchPlaceholder: 'Digite aqui para pesquisar...'
      }
    })

    // remover funçao padrao no cabeçalho
    $('.dataTables_filter input').unbind().bind('keyup', function () {
      dataTable.search(this.value).draw();
    });
  }, []);

  const linhas = dados.map (i =>
    <tr>
        <td>{i.ano}</td>
        <td>{i.docente}</td>
        <td>{i.titulo}</td>
        <td>{i.local}</td>
        <td>{i.orientacao}</td>
        <td>{i.estatisticas}</td>
        <td> <a><ModalOrientacao/></a> </td>
        <td> <a><ModalEstatistica/></a> </td>

    </tr>
    )

  return (
    <div className="card">
      <div className="card-body">
        <table ref={tableRef} className="table table-bordered table-striped">
          <thead>
            <tr>
                <th>Ano</th>
                <th>Docente</th>
                <th>Título</th>
                <th>Local</th>
                <th>Orientação</th>
                <th>Estatisticas</th>
                <th>Editar orientação</th>
                <th>Editar estatística</th>
            </tr>
          </thead>
          <tbody>{linhas}</tbody>
          <tfoot>
            <tr>
                <th>Ano</th>
                <th>Docente</th>
                <th>Título</th>
                <th>Local</th>
                <th>Orientação</th>
                <th>Estatisticas</th>
                <th>Editar orientação</th>
                <th>Editar estatística</th>
            </tr>
          </tfoot>
        </table>
      </div>
    </div>
  );
}

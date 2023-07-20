import React, { useEffect, useRef } from 'react';
import $ from 'jquery';
import 'datatables.net';

import ModalOrientacao from './modalOri';

import ModalEstatistica from './modalEst';


export default function TableProducoes({producoes, orientacoes, token}) {
  const tableRef = useRef(null);
  useEffect( () => {
    document.body.classList.add('hold-transition', 'layout-top-nav');

    }, []
  )

  const linhas = producoes.map((i) => (
    <tr key={i.ano}>
      <td>{i.ano}</td>
      <td>{i.docente}</td>
      <td>{i.titulo}</td>
      <td>{i.local}</td>
      <td>{i.orientacao}</td>
      <td>{i.qtdGraduado +"G "+ i.qtdMestrado+"M "+i.qtdDoutorado+"D "}</td>
      <td><a><ModalOrientacao orientacoes = {orientacoes} idProducao={i.id} token={token}/></a></td>
      <td><a><ModalEstatistica idProducao={i.id} token={token}/></a></td>
    </tr>
  ));

  return (
    <div className="card">
      <div className="card-header">
        <th className="d-flex justify-content-center" >Gerenciar Produções</th>
      </div>
      <div className="card-body">
        <table ref={tableRef} className="table table-bordered table-striped">
          <thead>
            <tr>
              <th >Ano</th>
              <th >Nome</th>
              <th >Título</th>
              <th >Local</th>
              <th >Orientação?</th>
              <th >Estatísticas</th>
              <th >Editar orientação</th>
              <th >Editar estatística</th>
            </tr>
          </thead>
          <tbody>{linhas}</tbody>
        </table>
      </div>
    </div>
  );
}

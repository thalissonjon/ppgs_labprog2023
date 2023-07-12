const dados = [
    {discente: 'Aluno 1', titulo: 'Dissertação 1', tipo: 'Mestrado', ano: '2022'},
    {discente: 'Aluno 2', titulo: 'TCC 1', tipo: 'TCC', ano: '2022'},
    {discente: 'Aluno 3', titulo: 'Pibic 1', tipo: 'IC', ano: '2022'}

]


export default function OrientacaoTable() {
    const linhas = dados.map (i =>
        <tr>
            <td>{i.discente}</td>
            <td>{i.titulo}</td>
            <td>{i.tipo}</td>
            <td>{i.ano}</td>
        </tr>
        )

    return (
            <div className="card">
              <div className="card-header">
                <h3 className="card-title">Orientações</h3>
              </div>
              
              <div className="card-body">
                <table id="example1" className="table table-bordered table-striped">
                  <thead>
                  <tr>
                    <th>Discente</th>
                    <th>Título</th>
                    <th>Tipo</th>
                    <th>Ano</th>
                  </tr>
                  </thead>
                  <tbody>
                  
                    {linhas}

                  </tbody>
                  <tfoot>
                  <tr>
                  <th>Discente</th>
                    <th>Título</th>
                    <th>Tipo</th>
                    <th>Ano</th>
                  </tr>
                  </tfoot>
                </table>
              </div>
            </div>  
    );
}
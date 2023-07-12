const dados = [
    {titulo: 'Sobre otimização e desafios', local: 'Congresso brasileiro de', tipo: 'Evento', qualis: 'B1', ano: '2023'},
    {titulo: 'Sobre otimização e desafios', local: 'Congresso brasileiro de', tipo: 'Evento', qualis: 'B1', ano: '2023'},
    {titulo: 'Sobre otimização e desafios', local: 'Congresso brasileiro de', tipo: 'Evento', qualis: 'B1', ano: '2023'}

]


export default function ArtigosTable() {
    const linhas = dados.map (i =>
        <tr>
            <td>{i.titulo}</td>
            <td>{i.local}</td>
            <td>{i.tipo}</td>
            <td>{i.qualis}</td>
            <td>{i.ano}</td>
        </tr>
        )

    return (
            <div className="card">
              <div className="card-header">
                <h3 className="card-title">Artigos</h3>
              </div>
              
              <div className="card-body">
                <table id="example1" className="table table-bordered table-striped">
                  <thead>
                  <tr>
                    <th>Título</th>
                    <th>Local</th>
                    <th>Tipo</th>
                    <th>Qualis</th>
                    <th>Ano</th>
                  </tr>
                  </thead>
                  <tbody>
                  
                    {linhas}

                  </tbody>
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
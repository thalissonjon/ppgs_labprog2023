const dados = [
    {docente: 'Alexandre César Muniz de Oliveira', A1:1, A2:0, A3:1, A4:0, B1:2, B2:0, B3:0, B4:0}
]


export default function DocenteQualis() {
    const linhas = dados.map (i =>
        <tr>
            <td>{i.docente}</td>
            <td>{i.A1}</td>
            <td>{i.A2}</td>
            <td>{i.A3}</td>
            <td>{i.A4}</td>
            <td>{i.B1}</td>
            <td>{i.B2}</td>
            <td>{i.B3}</td>
            <td>{i.B4}</td>
            <td> <a href="docente.html">Mais</a> </td>
        </tr>
        )

    return (
            <div className="card">
              <div className="card-header">
                <h3 className="card-title">Docentes</h3>
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
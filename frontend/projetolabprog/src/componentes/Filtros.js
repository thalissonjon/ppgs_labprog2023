export default function Filtros({pagina , dados,
                                filtroDado, onDadoChange, 
                                filtroAnoIni, onAnoIniChange,
                                filtroAnoFim, onAnoFimChange,
                                onSearch}) {

    const lstSelect = dados.map((dado) => (
        <option value={dado.id}>{dado.nome}</option>
        )
    )

    return (
        <>
        <h5 className="mb-2">Filtros</h5>
        <form action="#">
            <div className="row">
                <div className="col-md-10">
                    <div className="row">
                        <div className="col-3">
                            <div className="form-group">
                                <label>{pagina}</label>
                                <select className="form-control" style={{width:'100%'}}
                                    onChange={(e) => onDadoChange(e.target.value)} 
                                    value={filtroDado}>
                                    {lstSelect}
                                </select>
                            </div>
                        </div>
                        <div className="col-3">
                            <div className="form-group">
                                <label>Ano inicial:</label>
                                <input className="form-control" value={filtroAnoIni}
                                onChange={ (e) => onAnoIniChange(e.target.value)}/>
                            </div>
                        </div>
                        <div className="col-3">
                            <div className="form-group">
                                <label>Ano Final:</label>
                                <input className="form-control" value={filtroAnoFim}
                                onChange={ (e) => onAnoFimChange(e.target.value)}/>
                            </div>
                        </div>
                        <div className="col-2">
                            <div className="form-group">                                                                
                                <label></label>
                                <button type="button" className="btn btn-block btn-primary btn-lg" 
                                    onClick={onSearch}>Filtrar
                                </button>                              
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        </>
    );
}
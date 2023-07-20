export default function Indicadores({dados}) {

    return (
        <>
        <h5 class="mb-2">Indicadores Capes</h5>
        <div class="row">
            <InfoBox titulo="Total Produções" valor={dados.producoes} bg="gray" icon="fa-copy" />
            <InfoBox titulo="I Geral" valor={dados.igeral} bg="info" icon="fa-envelope" />
            <InfoBox titulo="I Restrito" valor={dados.irestrito} bg="success" icon="fa-flag" />
            <InfoBox titulo="I Não Restrito" valor={dados.inrestrito} bg="warning" icon="fa-copy" />
        </div>
        </>
    );
}

function InfoBox({titulo, valor, bg, icon}){
    return (
        <div className="col-md-3 col-sm-6 col-12">
            <div className="info-box">
                <span className={"info-box-icon bg-"+ bg}>
                  <i className={"far " + icon}></i>
                </span>
                <div className="info-box-content">
                    <span className="info-box-text">{titulo}</span>
                    <span className="info-box-number">{valor}</span>
                </div>                
            </div>            
        </div> 
    );
}
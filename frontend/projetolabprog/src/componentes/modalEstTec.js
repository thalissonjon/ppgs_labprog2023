import React from 'react';
import Popup from 'reactjs-popup';
import 'reactjs-popup/dist/index.css';

import "../views/modal.css";

import axios from 'axios';

import {useState} from 'react';
import { useRef } from 'react';

const client = axios.create({
  baseURL:"http://localhost:8080/api/docente/"
})


export default function ModalEst({idTecnica, token}){


  const [qtdGrad, setQtdGrad] = useState(0);
  const [qtdMest, setQtdMest] = useState(0);
  const [qtdDout, setQtdDout] = useState(0);

  const graduado = "graduado"
  const mestrado = "mestrado"
  const doutorado = "doutorado"

  function enviarEst(grad, mestrado, doutorado,idTecnica,){
    if(token == 'admin'){
    client.put(`attEstatisticasTec`, {qtdGraduado: grad,qtdMestrado: mestrado,qtdDoutorado: doutorado,idProducao: idTecnica})
            .then(
                (response) => {
                    alert("Itens adicionados com sucesso!")
                    console.log(response.data)
                }
            ).catch(error => {
                alert("erro" + grad+" "+ mestrado+" "+ doutorado+" "+idTecnica)
                console.log(error.response);
            })
    }
    else{
        alert("Você não tem permissão para realizar essa operação.")
    }
    setQtdGrad(0);
    setQtdMest(0);
    setQtdDout(0);
  }

  const handleChange = (event) => {
    if(event.target.value < 0){
        if(event.target.name === graduado){
            setQtdGrad(0);
        }else{
            if(event.target.name === mestrado){
                setQtdMest(0);
            }else{
                setQtdDout(0);
            }
        }
    }else{
        if(event.target.name === graduado){
            setQtdGrad(event.target.value);
        }else{
            if(event.target.name === mestrado){
                setQtdMest(event.target.value);
            }else{
                setQtdDout(event.target.value);
            }
        }
    }
    console.log(event.target.value);
  }

  const popupRef = useRef();
  
  const closePopup = () => {
        popupRef.current.close();
    };


  return (

  <Popup ref={popupRef} trigger={<button> Editar</button>} modal>
  <span> 
  <div className='Modal'>
      
      <from>
        <div class="modal-header">						
          <h4 class="modal-title">Informar Estatísticas</h4>
        </div>
        <div class="to-do-form">					
          <div class="form-group">
              <label>Qtd. alunos de Graduação:</label>
              <input name={graduado} type="number" value={ qtdGrad } className="form-control" onChange={handleChange} />
          </div>
          <div className="form-group">
              <label>Qtd. Alunos do Mestrado:</label>
              <input name={mestrado} type="number" value={ qtdMest } className="form-control" onChange={handleChange} />
          </div>
          <div className="form-group">
              <label>Qtd. Alunos do Doutorado:</label>
              <input name={doutorado} type="number" value={ qtdDout } className="form-control" onChange={handleChange} />
          </div>	
          <button type="submit" onClick={() => {enviarEst(qtdGrad, qtdMest, qtdDout,idTecnica); closePopup();} } >Salvar</button>						
        </div>
      </from>
    </div>
  </span> 
  
  </Popup>
  )
}
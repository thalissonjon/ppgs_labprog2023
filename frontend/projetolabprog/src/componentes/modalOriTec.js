import React from 'react';
import Popup from 'reactjs-popup';
import 'reactjs-popup/dist/index.css';

import "../views/modal.css";
import axios from 'axios';

import { useState } from 'react';
import { useRef } from 'react';

const client = axios.create({
  baseURL:"http://localhost:8080/api/docente/"
})


export default function ModalOri({orientacoes, idTecnica, token}){

  const [idsOrientacoesSalvar, setIdsOrientacoesSalvar] = useState([]);

  function enviarOri(oriePEnviar, idTecnica){
    if(token == 'admin'){
      client.put(`addOrientacaoTec`, {id: oriePEnviar, idProducao: idTecnica})
            .then(
                (response) => {
                    alert("Item " + oriePEnviar +" adicionado com sucesso!")
                    console.log(response.data)
                }
            ).catch(error => {
                alert("Um item já está adicionado. Tente novamente!")
                console.log(error.response);
            })
          }
    else{
      alert("Você não tem permissão para realizar essa operação.")
    }
    setIdsOrientacoesSalvar([]);
  }



  function check(idOrientacao){
    if(idsOrientacoesSalvar.includes(idOrientacao)){
      setIdsOrientacoesSalvar(idsOrientacoesSalvar.filter(id => id !== idOrientacao));
      console.log("removi" + idOrientacao + " " + idsOrientacoesSalvar);
    } else {
      setIdsOrientacoesSalvar([...idsOrientacoesSalvar, idOrientacao]);
      console.log("add" + idOrientacao + " " + idsOrientacoesSalvar);
    }
  }

  const lstSelect = orientacoes.map((item) => (
    
    <div className="topping">
      <input type="checkbox" id="topping" name="topping" value="Discente" 
        onClick={() => check(item.id)}
      />{item.discente} | {item.titulo} | (Tecnica: {idTecnica}) | (Orientação: {item.id})
    </div>

    )
  )

  const popupRef = useRef();

  const closePopup = () => {
    popupRef.current.close();
  };

  return (
  
  <Popup ref={popupRef} trigger={<button className="buttonEdit"> Editar </button>} modal>
    <span> 
      <from>
       <div class="modal-header">
          <h4 clas-s="modal-title" style={{textAlign: "center"}}>Selecione:</h4>
        </div>
        <div class="modal-body">	
          <from id="to-do-form">
              {lstSelect}
              <br />
              <button type='submit ' onClick={() => {enviarOri(idsOrientacoesSalvar,idTecnica); closePopup();} }>Salvar</button>
          </from>
        </div>

      </from>
    </span>
    </Popup>
    )
}
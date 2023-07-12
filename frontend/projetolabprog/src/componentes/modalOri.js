import React from 'react';
import Popup from 'reactjs-popup';
import 'reactjs-popup/dist/index.css';

import "../views/modal.css";


export default () => (

  
  <Popup trigger={<button className="button"> Editar </button>} modal>
    <span> 
      <from>
       <div class="modal-header">
          <h4 class="modal-title" style={{textAlign: "center"}}>Selecione:</h4>
        </div>
        <div class="modal-body">	
          <from id="to-do-form">
              <input type="checkbox" name="aluno1"/>
              <label for="aluno1"> José da Silva</label>
              <br />
              <input type="checkbox" name="aluno2"/> 
              <label for="aluno2"> Ribamar José</label>
              <br />
              <input type="checkbox" name="aluno3"/> 
              <label for="aluno3"> Marcio Silva</label>
              <br />
              <button type="submit">Salvar</button>
          </from>
        </div>

      </from>
    </span>
  </Popup>

);
import React from 'react';
import Popup from 'reactjs-popup';
import 'reactjs-popup/dist/index.css';

import "../views/modal.css";


export default () => (
    <Popup trigger={<button> Editar</button>} modal>
    <span> 
    <div className='Modal'>
        
        <from>
          <div class="modal-header">						
            <h4 class="modal-title">Informar Estatísticas</h4>
          </div>
          <div class="to-do-form">					
            <div class="form-group">
              <label>Qtd. alunos de Graduação:</label>
              <input type="text" class="form-control"/>
            </div>
            <div class="form-group">
              <label>Qtd. Alunos do Mestrado:</label>
              <input type="text" class="form-control"/>
            </div>
            <div class="form-group">
              <label>Qtd. Alunos do Doutorado:</label>
              <input type="text" class="form-control"/>
            </div>	
            <button type="submit">Salvar</button>						
          </div>
        </from>
      </div>
    </span> 
    
    </Popup>
  
);
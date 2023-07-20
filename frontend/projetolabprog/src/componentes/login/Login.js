import React, { useState } from 'react';
import PropTypes from 'prop-types';
import './Login.css';

async function loginUser(credentials) {
 return fetch('http://localhost:3030/login', {
   method: 'POST',
   headers: {
     'Content-Type': 'application/json'
   },
   body: JSON.stringify(credentials)
 })
   .then(data => data.json())
}

export default function Login({ setToken }) {
  const [usuario, setUsuario] = useState();
  const [senha, setSenha] = useState();

  const handleSubmit = async e => {
    
    e.preventDefault();
    const token = await loginUser({
      usuario,
      senha
    });
    

    
    setToken(token);
  }

  return(
    <div className="login-wrapper">
      <div className="sppg-container" >
      <h2 style={{textAlign: "center"}}>SPPG </h2>
      </div>
      <form onSubmit={handleSubmit}>
      <div className="input-container">
          <label >Usuario</label>
          <input type="text" onChange={e => setUsuario(e.target.value)} />
        </div>
        <div className="input-container">
      
          <label>Senha</label>
          <input type="password" onChange={e => setSenha(e.target.value)} />

        </div>
        <div className="button-container">
          <button type="submit">Entrar</button>
        </div>
      </form>
    </div>
  )
}

Login.propTypes = {
  setToken: PropTypes.func.isRequired
};
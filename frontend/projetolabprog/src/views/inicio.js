import React, { useState } from "react";

import "./inicio.css";

import { useNavigate } from "react-router-dom";

function Inicio() {

  const navigate = useNavigate();

  // 
  const [errorMessages, setErrorMessages] = useState({});
  const [isSubmitted, setIsSubmitted] = useState(false);

  // login
  const database = [
    {
      username: "a",
      password: "b"
    },
    {
      username: "c",
      password: "d"
    }
  ];

  const errors = {
    uname: "Usuário inválido",
    pass: "Senha inválida"
  };

  const handleSubmit = (event) => {
    //
    event.preventDefault();

    var { uname, pass } = document.forms[0];

    // encontrar login
    const userData = database.find((user) => user.username === uname.value);

    // comparar login
    if (userData) {
      if (userData.password !== pass.value) {
        setErrorMessages({ name: "pass", message: errors.pass });
      } else {
        setIsSubmitted(true);
      }
    } else {
      setErrorMessages({ name: "uname", message: errors.uname });
    }
  };

  // erro
  const renderErrorMessage = (name) =>
    name === errorMessages.name && (
      <div className="error">{errorMessages.message}</div>
    );


  const renderForm = (
    <div className="form">
      
      <form onSubmit={handleSubmit}>
        <div className="sppg-container">
          <h2 style={{textAlign: "center"}}>SPPG </h2>
        </div>
        <div className="input-container">
          <label>Usuário </label>
          <input type="text" name="uname" required />
          {renderErrorMessage("uname")}
        </div>
        <div className="input-container">
          <label>Senha </label>
          <input type="password" name="pass" required />
          {renderErrorMessage("pass")}
        </div>
        <div className="button-container">
          <input type="submit" />
        </div>
      </form>
    </div>
  );

  return (
    <div className="Inicio">
      <div className="login-form">
        {isSubmitted ? navigate("/programa")  : renderForm}
      </div>
    </div>
  );
}

export default Inicio;
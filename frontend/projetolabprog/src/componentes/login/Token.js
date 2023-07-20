import { useState } from 'react';

export default function Token() { // reutilizar em componentes
  
    const getToken = () => {
        const tokenString = sessionStorage.getItem('token'); // retorna a string
        const usuarioToken = JSON.parse(tokenString); // convertendo
        return usuarioToken?.token // valor do token / precisa ser ? pois no primeiro acesso o sessionStorage.getItem('token') vai ser indefinido
    };

    const [token, setToken] = useState(getToken());

    const salvarToken = usuarioToken => {
        sessionStorage.setItem('token', JSON.stringify(usuarioToken)); // Pega o token e a string como segundo argumento, tem que converter o token de um objeto pra uma string utilizando o stringify
        setToken(usuarioToken.token);
    };
    
    return {
        setToken: salvarToken,
        token
      }

}
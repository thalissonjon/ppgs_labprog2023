const express = require('express');
const cors = require('cors')
const app = express();

app.use(cors());
app.use(express.json());

const rand = () => {
  return Math.random().toString(36).substr(2);
};

const token = () => {
  return rand() + rand();
};

const usuarios = [
  { usuario: 'user', senha: 'user' },
];

const superUsuarios = [
  { usuario: 'admin', senha: 'admin'}
]



app.use('/login', (req, res) => {
  const {usuario, senha} = req.body;
  
  const superUsuarioEncontrado = superUsuarios.find(user => user.usuario === usuario && user.senha === senha);
 

  if (superUsuarioEncontrado) {
    console.log('Super Usuário autenticado', 'admin')
    return res.status(200).send({ message: 'Superusuário autenticado', token: 'admin'});
  }
  
  const usuarioEncontrado = usuarios.find(user => user.usuario === usuario && user.senha === senha);
  if (usuarioEncontrado) {
    console.log('Usuário autenticado', token())
    return res.status(200).send({ message: 'Usuário autenticado', token: token() });
  }

  // Se não encontrou na lista de usuários nem na de superusuários, enviar uma mensagem de erro
  console.log('Não encontrou', token())
  return res.status(401).send({ error: 'Usuário ou senha incorretos' });

  // res.send({
  //   token: token()
  // });
});
app.listen(3030, () => console.log('Api rodando em http://localhost:3030/login'));

# MentalMED

Api que será desenvolvida para o challenge da plusoft:

https://github.com/Challenge-Plusoft/mentalmed-api

## Endpoints

- Login do usuário
  - [Logar](#Logar)
- Cadastro de usuário
  - [Criar usuário](#Criar-usuário)
- Conversa do chat
  - [Salvar conversa](#Salvar-conversa)
  - [Ver conversa](#Ver-conversa)
  - [Deletar conversa](#Deletar-conversa)
  - [Mostrar conversa](#Mostrar-conversa)
  
 ---
 
 ### Autenticar
 
 `POST`/mentalmed/login


| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|---
| id | id | sim | id cadastrado do usuário
| email | string | sim | email que o usuário cadastrou
| senha | string | sim | senha que o usuário cadastrou

**Exemplo de corpo de requisição**

```js
{
  id: 1,
  email: 'vyoda4604@gmail.com',
  senha: 'fiap123'
 }
 ```
 
 **Códigos de Respostas**
 
| código | descrição
|-|-
| 200 | usuário encontrado com sucesso
| 401 | algum campo com erro de autenticação
| 500 | erro no servidor

---

### Criar usuário

`POST` /mentalmed/cadastrar

**Campos de requisições**

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|---
| id | id | sim | id gerado para cada usuário cadastrado 
| nome | string | sim | nome do usuário
| gênero | string | sim | gênero do usuário
| email | string | sim | Endereço de email do usuário
| senha | string | sim | Senha do usuário
| confirmar senha | string | sim | Confirmação da senha do usuário

**Exemplo de corpo de requisição**

```js
{
  id: 1,
  nome: 'Vinicius',
  gênero: 'M',
  email: 'fiappaulista@fiap.com.br',
  senha: 'fiap123',
  confirmarSenha: 'fiap123'
}
```

**Códigos de Respostas**

| código | descrição
|-|-
| 201 | Usuário cadastrado com sucesso
| 400 | Solicitação inválida
| 500 | erro no servidor

---

### Salvar conversa

`POST` /mentalmed/conversa

**Campos de requisições**

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|---
| userId | id | sim | id da conversa do usuário
| chatId | id | sim | id da conversa do chatGPT
| userMensagem | string | sim | parte da conversa do usuário com o chatGPT
| chatMensagem | string | sim | parte da conversa e resposta com usuário


**Exemplo de corpo de requisição**

```js
{
  userId: 1,
  chatId: 1,
  userMensagem: 'Estou querendo conversar agora',
  chatMensagem: 'Sim, estou aqui para conversar com você a qualquer momento'
}
```

**Códigos de Respostas**

| código | descrição
|-|-
| 201 | conversa salva
| 404 | conversa não salvo
| 500 | erro no servidor

---

### Ver conversa

`GET` /mentalmed/conversa/{id}

**Exemplo de corpo de resposta**

```js
{
  userId: 1,
  chatId: 1,
  userMensagem: 'Estou querendo conversar agora',
  chatMensagem: 'Sim, estou aqui para conversar com você a qualquer momento'
}
```

**Códigos de Respostas**

| código | descrição
|-|-
| 200 | conversa não retornado com sucesso
| 404 | conversa não encontrado
| 500 | erro no servidor

---

### Deletar conversa

`DELETE` /mentalmed/conversa/{id}
 
| código | descrição
|-|-
| 200 | conversa apagado com sucesso
| 404 | conversa não encontrado
| 500 | erro no servidor

---

### Mostrar conversa

`GET` /mentalmed/conversa



<img src="https://github.com/thays-gama/desafio_spring/blob/main/src/main/resources/images/dh.png" alt="logotipo Digital House" width="140px" align="right">
<img src="https://github.com/thays-gama/desafio_spring/blob/main/src/main/resources/images/meli.png" alt="logotipo Mercado Livre" width="100px" align="right">

# 🍃 Desafio Testing 🧪 - DigitalHouse

Projeto feito no bootcamp [Java BackEnd - MercadoLivre](https://www.mercadolibre.com.ar/itacademy) com objetivo de criar um [CRUD](https://developer.mozilla.org/pt-BR/docs/Glossary/CRUD) a partir da criação de uma API RESTful utilizando a estrutura MSC e a implementação de Testes Unitários e de Integração.

## ⭐ Habilidades

- Utilizar as habilidades ministradas em aula pela DigitalHouse.
  - POO
  - Java
  - Spring
  - REST e RESTful
  - JSON
  - Arquitetura MVC
  - Testes Unitários
  - Testes de Integração 
- Criar uma API utilizando o Spring
- Criar os Testes de Unidade e de Integração.

## 💻 Como iniciar na IDE

1. Faça o clone do projeto:
```shell
git clone git@github.com:Wave-7-Grupo-6/Desafio-2-Testes.git
```
2. Abra o projeto na sua IDE de escolha:
```shell
mvn spring-boot:run
```

## 🧑🏻‍🚀 Como testar no Postman

1. Importar o arquivo:
```shell
src/main/resources/MELI - DH.postman_collection.json
```

# 💡 Documentação da API

### Cadastro de Bairro

```http
 POST /bairros/salvar
```

| Corpo da requisição   | Tipo       | Descrição                                               |
| :-------------------- | :--------- | :------------------------------------------------------ |
| `nome, valorMetro`.   |   `json`   | **Obrigatório**. Todos os campos no corpo da requisição |

**Formato do corpo da requisição**
```json
[
    {
        "nome": "Jardim teste",
        "valorMetro": 24692.00
    }
]
```

**Retorno em caso de sucesso**

```json
[
    {
        "id": 14,
        "nome": "Jardim teste 2",
        "valorMetro": 24692.00
    }
]
```
> O retorno acima é apenas fictício.

### Retorna todos os Bairros

```http
  GET /bairros/
```

**Retorno em caso de sucesso**

```json
[
    {
        "id": 1,
        "nome": "Jardim Panorama",
        "valorMetro": 24692.00
    },
    {
        "id": 2,
        "nome": "Jardim América",
        "valorMetro": 24171.00
    },
    {
        "id": 3,
        "nome": "Jardim Europa",
        "valorMetro": 22079.00
    }
    ...
]
```

| Descrição                                                                      |
| :----------------------------------------------------------------------------- |
| Será retornado um array com todos os bairros do arquivo resources/bairros.json |

### Retorna o bairro pelo id.

```http
  GET /bairros/pelo_id/{id}
```

| Parâmetro   | Tipo       | Descrição                                             |
| :---------- | :--------- | :---------------------------------------------------- |
| `id`        | `int`      | **Obrigatório**. Parâmetros devem ser passados na URL |

**Retorno em caso de sucesso**

```json
{
    "id": 3,
    "nome": "Jardim Europa",
    "valorMetro": 22079.00
}
```

| Descrição                                                  |
| :--------------------------------------------------------- |
| Será retornado o bairro com o id correspondente ao enviado |

### Salvar um Imóvel

```http
 POST /imoveis/salvar
```

| Corpo da requisição                                     | Tipo       | Descrição                                               |
| :------------------------------------------------------ | :--------- | :------------------------------------------------------ |
| `nome, idBairro, comodos -> nome, largura, comprimento` | `json`     | **Obrigatório**. Todos os campos no corpo da requisição |

**Formato do corpo da requisição**
```json
{
    "nome" : "teste",
    "idBairro" : 1,
    "comodos" : [{
        "nome" : "Cozinha",
        "largura" : 5.5,
        "comprimento" : 3.4
    }]
}
```

**Retorno em caso de sucesso**

```json
{
    "id": 3,
    "nome": "teste",
    "comodos": [
        {
            "nome": "Cozinha",
            "largura": 5.5,
            "comprimento": 3.4
        }
    ],
    "idBairro": 1
}
```
> O retorno acima é apenas fictício.

### Retorna todos os Imóveis

```http
  GET /imoveis/
```

**Retorno em caso de sucesso**

```json
[
    {
        "id": 1,
        "nome": "PalmeiraLoftIII",
        "comodos": [
            {
                "nome": "SalaEstar",
                "largura": 12.5,
                "comprimento": 20.4
            }
        ],
        "idBairro": 3
    },
    {
        "id": 2,
        "nome": "PalmeiraLoftII",
        "comodos": [
            {
                "nome": "Cozinha",
                "largura": 12.5,
                "comprimento": 20.4
            },
            {
                "nome": "Quarto",
                "largura": 13.0,
                "comprimento": 21.0
            }
        ],
        "idBairro": 3
    },
    ...
]
```

| Descrição                                                                      |
| :----------------------------------------------------------------------------- |
| Será retornado um array com todas as compras do arquivo resources/imoveis.json |

### Retorna o imóvel pelo id

```http
 POST /imoveis/pelo_id/{id}
```

| Corpo da requisição  | Tipo      | Descrição                                             |
| :------------------- | :-------- | :---------------------------------------------------- |
| `id`                 | `int`     | **Obrigatório**. Parâmetros devem ser passados na URL |

**Retorno em caso de sucesso**

```json
{
    "id": 1,
    "nome": "PalmeiraLoftIII",
    "comodos": [
        {
            "nome": "SalaEstar",
            "largura": 12.5,
            "comprimento": 20.4
        }
    ],
    "idBairro": 3
}
```
> O retorno acima é apenas fictício.

### Retorna o valor do imóvel pelo id

```http
  GET /imoveis/valor_imovel/{id}
```

| Corpo da requisição  | Tipo      | Descrição                                             |
| :------------------- | :-------- | :---------------------------------------------------- |
| `id`                 | `int`     | **Obrigatório**. Parâmetros devem ser passados na URL |

**Retorno em caso de sucesso**

```json
5630145.00
```

| Descrição                                                                |
| :----------------------------------------------------------------------- |
| Será retornado o valor do imóvel, com arredondamento de 2 casas decimais |

### Retorna a área do imóvel, em metros quadrados

```http
  GET /imoveis/area/{id}
```

**Retorno em caso de sucesso**

```json
255,00
```

| Descrição                                                                        |
| :------------------------------------------------------------------------------- |
| Será retornado o valor da área do imóvel, com arredondamento de 2 casas decimais |

## Feito Com:

[![IDE](https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)](https://www.jetbrains.com/idea/) 
[![IDE](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.java.com/pt-BR/) 
[![IDE](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/) 

## Devs:

[![Giovanna Almeida](https://img.shields.io/badge/Giovanna_Almeida-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/giovanna-souza-70bbb41b4/) 
[![Mauro Correia](https://img.shields.io/badge/Mauro_Correia-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/mauro-correia/) 
[![Michael Caxias](https://img.shields.io/badge/Michael_Caxias-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/michaelcaxias/) 
[![Paula Santos](https://img.shields.io/badge/Paula_Santos-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/paula-libia-santos/)
[![Thays Gama ](https://img.shields.io/badge/Thays_Gama-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/thaysgama/) 
[![Williamns Belo](https://img.shields.io/badge/Williamns_Belo-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/williamns-belo/) 


<p align="center">Copyright © 2021 Michael Caxias</p>

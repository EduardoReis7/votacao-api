# Votação Api

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=EduardoReis7_votacao-api&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=EduardoReis7_votacao-api)

Esta aplicação tem como intuito gerenciar votações
de associados em pautas, realizando a apuração dos votos e gerando o resultado da votação.

## Ambiente

Para rodar a aplicação é necessário apenas que você possua o
<a href="https://www.docker.com/products/docker-desktop">Docker</a> instalado na sua máquina.

### Comandos

Acesse o diretório da aplicação e abra um terminal. </br>
Nele, digite o comando abaixo

```shell
docker-compose up
```

Após isso, todos os containers necessários serão inicializados.

### Utilização

Por padrão, foi configurado que a api fosse exposta na porta 8035.
Para fazer as requisições é necessário acessar o Swagger da aplicação, disponível no link abaixo.

<a href="http://localhost:8035/swagger-ui.html">Swagger</a>

Para verificar que as mensagens estão chegando corretamente no tópico "voto-results" é necessário acessar o Kafka-ui, disponível no link abaixo.

<a href="http://localhost:8500">Kafka-ui</a>

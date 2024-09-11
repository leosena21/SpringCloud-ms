# SpringCloud-ms
Projeto criado para estudos contendo 7 microsserviços, sendo eles:
1. hr-api-gateway-zuul
2. hr-config-server
3. hr-eureka-server
4. hr-oauth
5. hr-payroll
6. hr-user
7. hr-worker

Alguma das implementações/características:
1. Java 8
2. Spring Boot
3. Feign para requisições de API entre microsserviços
4. OAuth e JWT para autenticação e autorização
5. Servidor Eureka para registro dos microsserviços
6. API Gateway Zuul para roteamento e autorização
7. Config Server - Servidor de configuração centralizada com dados em repositório Git
8. Docker - Criação de Dockerfile para cada projeto

## Profiles

Alguns projetos utilizam profiles contendo características diferentes. O profile que será executado pode ser alterado dentro do application.properties de cada projeto. Os profiles de cada ambiente são acessados a partir do Config Server. Esses arquivos estão na banch master desse projeto com o nome: [nome-do-microsservico]-[profile].properties, sendo os profiles:

1. test: Utiliza o banco de dadosem memória h2
2. dev: Utiliza o banco de dados postgresql local
3. prod: Utiliza o banco de dados postgresql com base no nome do container docker

## Docker

Foi realizada a criação de uma rede docker chamada hr-net. Para criar a mesma, execute o seguinte comando:

```
docker network create hr-net
```

Configuração docker Postgresql 
```
docker pull postgres:12-alpine

docker run -p 5432:5432 --name hr-worker-pg12 --network hr-net -e POSTGRES_PASSWORD=1234567 -e POSTGRES_DB=db_hr_worker postgres:12-alpine
docker run -p 5433:5432 --name hr-user-pg12 --network hr-net -e POSTGRES_PASSWORD=1234567 -e POSTGRES_DB=db_hr_user postgres:12-alpine
```


Cada microsserviço contém um Dockerfile com configurações diferentes. Para gerar um imagem docker, é necessário realizar a build do projeto e rodar o seguinte comando: 

```
docker build -t [nome-do-microsservico]:v1 .
```

Para executar a imagem, rodar os seguintes comandos:

```
docker run -p 8888:8888 --name hr-config-server --network hr-net hr-config-server:v1
docker run -p 8761:8761 --name hr-eureka-server --network hr-net hr-eureka-server:v1
docker run -p 8765:8765 --name hr-api-gateway-zuul --network hr-net hr-api-gateway-zuul:v1

docker run -P --network hr-net hr-worker:v1
docker run -P --network hr-net hr-user:v1
docker run -P --network hr-net hr-payroll:v1
docker run -P --network hr-net hr-oauth:v1
```

Para o correto funcionamento das aplicações em docker, é necessário que os serviços(hr-config, hr-eureka e hr-api-gateway, além dos bancos de dados postgresql)  rodem nas portas especificadas, pois estão configuradas as mesmas portas dentro da aplicação.



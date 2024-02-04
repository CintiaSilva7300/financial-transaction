## Java Spring Boot

## Requisitos

* Docker
* MSQL
* Java - Spting Boot

# Em um terminal de sua escolha Executar os comando na sequencia para baixar a imagem o RabbitMQ e execuatr:

```
docker pull rabbitmq
docker run --rm -d -it -p 15672:15672 -p 5672:5672 rabbitmq:3-management
```

## Acesso do RabbitMQ

```
http://localhost:15672/#/queues
```
Username: 
```
guest
```
Password:
```
 guest
```

Clone do repositorio, executar projeto na sua ide

```
- git clone https://github.com/CintiaSilva7300/financial-transaction.git
- cd financial-transaction
```

## Baixar projeto Tyranitar
* È importante baixar o projeto Tyranitar para realizar os envios das mensagens para o RabbitMQ

```
- git clone https://github.com/CintiaSilva7300/Tyranitar.git
- cd Tyranitar
```

## Com o projeto 'Tyranitar' clonado e em execução Utilizar o postman para fazer uma requisição http

## obs: 
* Nessa requição enviar um arquivo no formato csv para que ele seja processado e enviado para uma fila no Rabbitmq
* Acessar o caminho para fazer a requicição o tipo 'POST' no postman http://localhost:8080/api/financial-upload

Local:
```
http://localhost:8080/api/financial-upload
```

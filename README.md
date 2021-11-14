# Spring WebSocket STOMP [![CI](https://github.com/daggerok/spring-websocket-stomp/actions/workflows/ci.yaml/badge.svg)](https://github.com/daggerok/spring-websocket-stomp/actions/workflows/ci.yaml)

Spring WebSocker STOMP app using simple in-memory, RabbitMQ and ActiveMQ message brokers

## simple-in-memory-broker

```bash
./mvnw -f simple-in-memory-broker clean compile spring-boot:start

open http://127.0.0.1:8001 # click connect, type message and press enter

./mvnw -f simple-in-memory-broker spring-boot:stop
```

## rabbitmq-broker

```bash
docker compose -f ./rabbitmq-broker/docker-compose.yml up -d --build 
./mvnw -f rabbitmq-broker clean compile spring-boot:start

open http://127.0.0.1:8002 # click connect, type message and press enter

./mvnw -f rabbitmq-broker spring-boot:stop
docker compose -f ./rabbitmq-broker/docker-compose.yml down -v --rmi local
```

## activemq-broker

```bash
docker compose -f ./activemq-broker/docker-compose.yml up -d --build 
./mvnw -f activemq-broker clean compile spring-boot:start

open http://127.0.0.1:8003 # click connect, type message and press enter

./mvnw -f activemq-broker spring-boot:stop
docker compose -f ./activemq-broker/docker-compose.yml down -v --rmi local
```

## other repos
* https://github.com/daggerok/spring-boot-rest-jms-activemq
* https://github.com/daggerok/sockjs-stomp-websocket-react-and-vanilla
* https://github.com/daggerok/spring-streaming
* https://github.com/daggerok/wsock
* https://github.com/daggerok/spring-security-rest-auth-token

## rtfm

* https://djeison.dev/2017/11/04/spring-websocket-rabbitmq/
* https://spring.io/guides/gs/messaging-stomp-websocket/
* https://www.rabbitmq.com/getstarted.html
* 1 "Hello World!" [Spring]: https://www.rabbitmq.com/tutorials/tutorial-one-spring-amqp.html
* 1 "Hello World!" [Java]: https://www.rabbitmq.com/tutorials/tutorial-one-java.html
* 2 Work queues [Spring]: https://www.rabbitmq.com/tutorials/tutorial-two-spring-amqp.html
* 2 Work queues [Java]: https://www.rabbitmq.com/tutorials/tutorial-two-java.html
* 3 Publish/Subscribe [Spring]: https://www.rabbitmq.com/tutorials/tutorial-three-spring-amqp.html
* 3 Publish/Subscribe [Java]: https://www.rabbitmq.com/tutorials/tutorial-three-java.html
* 4 Routing [Spring]: https://www.rabbitmq.com/tutorials/tutorial-four-spring-amqp.html
* 4 Routing [Java]: https://www.rabbitmq.com/tutorials/tutorial-four-java.html
* 5 Topics [Spring]: https://www.rabbitmq.com/tutorials/tutorial-five-spring-amqp.html
* 5 Topics [Java]: https://www.rabbitmq.com/tutorials/tutorial-five-java.html
* 6 RPC [Spring]: https://www.rabbitmq.com/tutorials/tutorial-six-spring-amqp.html
* 6 RPC [Java]: https://www.rabbitmq.com/tutorials/tutorial-six-java.html
* 7 Publisher Confirms [Java]: https://www.rabbitmq.com/tutorials/tutorial-seven-java.html
* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.6/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.6/maven-plugin/reference/html/#build-image)
* [Liquibase Migration](https://docs.spring.io/spring-boot/docs/2.5.6/reference/htmlsingle/#howto-execute-liquibase-database-migrations-on-startup)
* [WebSocket](https://docs.spring.io/spring-boot/docs/2.5.6/reference/htmlsingle/#boot-features-websockets)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.5.6/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Using WebSocket to build an interactive web application](https://spring.io/guides/gs/messaging-stomp-websocket/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

# **Spring RabbitMQ Example**

### ![Build](http://www.iconfinder.com/icons/1608651/download/png/24) Build Projects

```
./build.sh compile
```

This command run compile command. This will compile all projects.

### ![Start-Docker](http://www.iconfinder.com/icons/298878/download/png/24) Start Docker Compose

```
./build.sh start-d9
```

This command start `docker-compose` file. It will run rabbitmq and other projects.

### ![Stop-Docker](http://www.iconfinder.com/icons/106221/download/png/24) Stop Docker Compose


```
./build.sh stop-d9
```

This command stop docker-compose file and remove `containers`.

## ![Kubernetes](http://www.iconfinder.com/icons/372922/download/png/24) Kubernetes

For running in Kubernetes you have to download `Minikube` to local machine. If you don't have minikube in your machine please check [Minikube)(#knowledge--requirements) section.

```
minikube start
```

### ![Start-Kubernetes](http://www.iconfinder.com/icons/298878/download/png/24) Start Kubernetes

```
./build.sh start-k8
```

This command start `kubernetes yaml` file. It will run rabbitmq and other projects.

### ![Stop-Kubernetes](http://www.iconfinder.com/icons/106221/download/png/24) Stop Kubernetes

```
./build.sh stop-k8
```

This command stop kubernetes `pods`, `services` and `deployments`.

### ![Knowledge-Requirements](http://www.iconfinder.com/icons/809475/download/png/24)  Knowledge & Requirements

* [Spring](https://spring.io/)
  * [Spring Boot](https://projects.spring.io/spring-boot/)
* [RabbitMQ](https://www.rabbitmq.com/)
* [LOG](https://www.slf4j.org/manual.html)
* [Gradle](https://gradle.org/)
* [Docker](https://www.docker.com/)
  * [Docker Tutorial](https://docs.docker.com/get-started/#setup)
  * [Docker File](https://docs.docker.com/engine/userguide/eng-image/dockerfile_best-practices/#label)
* [Kubernetes](https://kubernetes.io/)  
  * [Minikube](https://kubernetes.io/docs/tutorials/stateless-application/hello-minikube/)
* [Project Lombok](https://projectlombok.org/)

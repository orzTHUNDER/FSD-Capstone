
# Dynamic Integration Test Suite

This project solely focuses on the creation of dynamic and configurable integration test cases. Deployed in a single node Kubernetes cluster ( Minikube ) locally. Docker for shipping the application in containers.




## Features

- Micro-services Architecture
- Configurable test cases using TEST-FACTORY
- Dynamic test cases using TEST-CONTAINER and External test case file created in run-time
- Deployed in minikube with docker as the run-time.
- Mimics actual production test with full integration test for all services


## Tech-Stack 

- React & Material UI
- JAVA Spring Boot
- MySQL
- Docker
- Minikube
- Kubectl


## Screenshots

##### Demonstration of the test-container for mimicing the DB in the container 

![Test-Container](https://github.com/orzTHUNDER/FSD-Capstone/blob/master/ScreenShots/Test-Container.png)

![External test logging](https://github.com/orzTHUNDER/FSD-Capstone/blob/master/ScreenShots/External-test-log.png)

![External test case soruce](https://github.com/orzTHUNDER/FSD-Capstone/blob/master/ScreenShots/External-test-scripts.png)

![Test case results](https://github.com/orzTHUNDER/FSD-Capstone/blob/master/ScreenShots/Test-case-results.png)

![Docker images](https://github.com/orzTHUNDER/FSD-Capstone/blob/master/ScreenShots/Docker%20Images.png)


![Sample UI](https://github.com/orzTHUNDER/FSD-Capstone/blob/master/ScreenShots/Sample-UI.png)

![Eureka Server without kubernetes](https://github.com/orzTHUNDER/FSD-Capstone/blob/master/ScreenShots/Eureka-Server.png)

![Eureka Server with kubernetes](https://github.com/orzTHUNDER/FSD-Capstone/blob/master/ScreenShots/ServiceRegistry-Minikube.png)

![DockerFile](https://github.com/orzTHUNDER/FSD-Capstone/blob/master/ScreenShots/DockerFile.png)

![Starting Minikube](https://github.com/orzTHUNDER/FSD-Capstone/blob/master/ScreenShots/Starting-minikube.png)

![Minikube clusters](https://github.com/orzTHUNDER/FSD-Capstone/blob/master/ScreenShots/Minikube-Clusters.png)

![Running a Service in the cluster](https://github.com/orzTHUNDER/FSD-Capstone/blob/master/ScreenShots/Running%20Service.png)

![Demo of the running service](https://github.com/orzTHUNDER/FSD-Capstone/blob/master/ScreenShots/ServiceRegistry-Minikube.png)

![MySQL in Kubernetes](https://github.com/orzTHUNDER/FSD-Capstone/blob/master/ScreenShots/Contanerized-MySQL.png)

![Mounting the MySQL](https://github.com/orzTHUNDER/FSD-Capstone/blob/master/ScreenShots/Mounting-Deployed-Mysql-Auth-Service.png)

![Accessing the Mysql in Kuberenetes - 1](https://github.com/orzTHUNDER/FSD-Capstone/blob/master/ScreenShots/Accessing-MySQL-kubernetes-1.png)

![Accessing the Mysql in Kuberenetes - 2](https://github.com/orzTHUNDER/FSD-Capstone/blob/master/ScreenShots/Accessing-MySQL-kubernetes-2.png)





## Spring-Micro-Service

Micro-Service-Architecture style is used for creation of the backend services using the EUREKA-SERVER (Service-Registry) and API-Gateway for the single endpoint routing. Used FEIGN-CLINET for interaction between the micro-services instead of rest-templates.

## TEST CONTAINERS

Integration testing is done using the TEST-CONTAINERS for creation of the MySQL container in the docker which mimics the actual database that will be used in the production. Which helps in testing the DB Integration without actually manipulating the actual database with the test values

## TEST Factory

Test Factory helps in creation of test-suite (Collection of test-cases) which is dynamically fetched in run-time from the test-data.json file and the logs of the test is logged in the external file for better insights of the test-cases.

## Micro-Services

- Authentication Service:

    - Allows User to Sign-up / Login.
    - Validation for edge cases such as same email, invalid credentials, empty password (or) email, valid email etc..

- Service-Registry

    - A Discovery Server (Eureka Server) for the other micro-services (Eureka clients) for registering themselves and communicate with other services. 
    - This helps in dynamically discovering services at runtime without hardcoding their locations. 
    - Which also checks the health of the registered clients.

- API Gateway

    - Acts as SINGLE Entry-Point for the clients
    - Reduces the load on backend services by caching frequently requested responses.

- Rate and Conversion 

    - Provides the Rate for the conversion of Currencies and conversion based on the amount.
    - Uses FEIGN for simplifying the process of calling remote services (RATE <---> CONVERSION). Instead of Rest-template to remove the overhead of mentioning the URL and port correctly.


## Deployment

- All the micro-services has a docker-file. which is used for creating docker images.
- The docker image contains the JAR file of the JAVA Micro-Service.
- A YAML file is created for Kubernetes Deployment and Service. Which contains the docker image, number of replicas, port and container port and etc..
- Using Kubectl ( CLI for configuration of the clusters ) for applying the YAML file to create a service in Minikube.
- Using Minikube we can start the service with the service name which will then tunnel a connection for accessing the service.



### Building the docker file

This build a docker image with the name auth 

```bash
  docker build -t spring-docker-auth .
```

### Starting the minikube

```bash
  minikube start
```

### Creating a Deployment and Service YAML file


##### Deployment.yaml

```bash
apiVersion: apps/v1
kind: Deployment
metadata:
  name: spring-docker-auth
spec:
  replicas: 1
  selector:
    matchLabels:
      app: spring-docker-auth
  template:
    metadata:
      labels:
        app: spring-docker-auth
    spec:
      containers:
      - name: spring-docker-auth
        image: spring-docker-auth
        imagePullPolicy: IfNotPresent
        ports:
        - containerPort: 8082
        env:
        - name: SPRING_DATASOURCE_URL
          value: jdbc:mysql://mysql:3306/mydatabase
        - name: SPRING_DATASOURCE_USERNAME
          value: myuser
        - name: SPRING_DATASOURCE_PASSWORD
          value: mypassword
        - name: EUREKA_CLIENT_SERVICEURL_DEFAULTZONE
          value: http://spring-docker-serviceregistry:8761/eureka/
        - name: SPRING_APPLICATION_NAME
          value: Authentication
        - name: SERVER_PORT
          value: "8082"

```

Service.yaml

```bash
apiVersion: v1
kind: Service
metadata:
  name: spring-docker-auth
spec:
  selector:
    app: spring-docker-auth
  ports:
    - protocol: TCP
      port: 8082
      targetPort: 8082
  type: NodePort
```

### Deploying the yaml files to kubernetes cluster using kubectl

```bash
kubectl apply -f deployment.yaml
kubectl apply -f service.yaml
```

### Accessing the app in the cluster

```bash
  minikube service spring-docker-auth --url
```


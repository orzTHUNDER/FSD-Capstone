
# Dynamic Integration Test Suite

This project solely focuses on the creation of dynamic and configurable integration test cases.




## Features

- Micro-services Architecture
- Configurable test cases using TEST-FACTORY
- Dynamic test cases using TEST-CONTAINER and External test case file created in run-time
- Mimics actual production test with full integration test for all services


## Tech-Stack 

- React & Material UI
- JAVA Spring Boot
- MySQL
- Docker


## Screenshots

![Test-Container](https://github.com/orzTHUNDER/FSD-Capstone/blob/master/Test-Container.png)

![External test logging](https://github.com/orzTHUNDER/FSD-Capstone/blob/master/External-test-log.png)

![External test case soruce](https://github.com/orzTHUNDER/FSD-Capstone/blob/master/External-test-scripts.png)

![Test case results](https://github.com/orzTHUNDER/FSD-Capstone/blob/master/Test-case-results.png)

![Docker images](https://github.com/orzTHUNDER/FSD-Capstone/blob/master/Containerizing.png)


![Sample UI](https://github.com/orzTHUNDER/FSD-Capstone/blob/master/Sample-UI.png)

![Eureka Server](https://github.com/orzTHUNDER/FSD-Capstone/blob/master/Eureka-Server.png)





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





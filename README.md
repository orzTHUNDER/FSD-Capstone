
# Dynamic Integration Test Suite

This project solely focuses on the creation of dynamic and configurable integration test cases.




## Features

- Micro-services Architecture
- Configurable test cases using TEST-FACTORY
- Dynamic test cases using TEST-CONTAINER and External test case file created in run-time
- Mimics actual production test with full integration test for all services




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

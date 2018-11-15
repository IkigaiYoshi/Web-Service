# Service for sensor data input

Let's assume we have a sensor that sends temperature and location data (coordinates).

Server validates the data and stores it. The user is able to get the list of last 10 inputs.

Required:

  - Using Spring Framework (v>=4) implement two endpoints (POST and GET), validation controller, database layer (repository).
  - Validation shall include a check of the coordinates format and temperature interval.
  - Provide REST API for the client to get json with the list of last 10 inputs
  - Cover functionality with unit- and integration tests
  - Provide scripts to populate DB
  
Optional:

   - Basic authorization for both client and sensor
   - Extend the application by adding a feature to filter by city. The city should be retrieved automatically from the given coordinates
   - Implement basic UI (use your favorite JS framework)
   
## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system

### Prerequisites

To start, you need a instaled MySQl server and an installed driver.

Installation example for Ubuntu:

>sudo apt update
>
>sudo apt install mysql-server
>
>sudo mysql_secure_installation

### First project start

It is necessary before starting the application to change the file *application.properties* located in:

>/src/main/resources/application.properties

Add username and password of your database user.


```properties
spring.profiles.active=

server.port=8080
server.context-path=

spring.datasource.initialize=true
#spring.datasource.schema=classpath*:database/initDB.sql
#spring.datasource.data=classpath*:database/populateDB.sql

spring.datasource.url=jdbc:mysql://localhost:3306/service

spring.datasource.username=root   #need to change
spring.datasource.password=1      #need to change

spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.jpa.database=mysql
spring.jpa.database-platform=org.hibernate.dialect.MySQL5Dialect

logging.level.= INFO
```

It is also necessary to uncomment the fields in 


```properties
#spring.datasource.schema=classpath*:database/initDB.sql
#spring.datasource.data=classpath*:database/populateDB.sql
```

when you first start the project. 
After the first start it is necessary to comment back.

### Running

The web-service is launched by a file *ServiceWeatherDemoApp.java* located in

>/src/main/java/com/siemens/ServiceWeatherDemoApp.java


The sensor starts the file *Sensore.java* located in 

> /src/main/java/com/siemens/sensor/Sensore.java

After starting the service, to see the result go to the link [http://localhost:8080/]().

## Built With

 - [Maven](https://maven.apache.org/) - Dependency Management

 - [Spring](https://spring.io/) - Application framework used
 
 - [Postman](https://www.getpostman.com/) - API Testing
 
 - [api.sypexgeo](https://sypexgeo.net/ru/) - API determination of coordinates
 
 - [API Яндекс.Погоды](https://tech.yandex.ru/weather/) - API determination of temperature
 
 - [MySQL](https://www.mysql.com/) - Relational database management system.



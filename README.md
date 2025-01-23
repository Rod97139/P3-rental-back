# Estate

This project was generated with [Spring Initializr](https://start.spring.io/) version 3.4.1 and Java 17

## Start the project

Git clone:

> https://github.com/Rod97139/P3-rental-back

Go into the project folder:

> cd P3-rental-back

Rename the file `application-example.properties` to `application.properties` and set the correct values for the database connection.

> src/main/resources/application.properties

Install dependencies:

> mvn clean install

Start the project (JDK 17 should be installed):

> mvn spring-boot:run;

> or use your preferred IDE;

Run as a Standalone Java Application

>  mvn clean package spring-boot:repackage

> java -jar target/rental-0.0.1-SNAPSHOT.jar

### Documentation

For OpenApi documentation, you could use the following file in root folder:

> rental.json

and copy it to this link:

> https://editor-next.swagger.io/


### MySQL

SQL script for creating the schema is available 

`https://github.com/Rod97139/P3-rental-front`

`ressources/sql/script.sql`

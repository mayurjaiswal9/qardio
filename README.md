**Project Title:**
Java Project to provide API's to save/fetch continuous stream of temperature data from sensor. 

Technology/Framework Used: Java, SpringBoot, Hibernate, Maven, JUnit 5

Features:
Application exposes endpoints to do the following activities. 
1. Save the continous stream of Temperature data from temperature Sensor.
2. Provide hourly average of the Temperature data.
3. Provide daily average of the Temperature data.

The Application Read performance is enhanced by using Materialized Views.

Installation:

1. Install JAVA JDK 11, MAVEN in your machine.
2. Install PostMan client to test the Endpoints.
3. Install POSTGRESQL and PGAdmin Client.

API Reference:
POST : http://localhost:8080/saveTemperature
GET  : http://localhost:8080/getHourlyData 
GET  : http://localhost:8080/getDailyData


Tests:
Please run 'mvn install' or 'mvn test' from the root folder to execute the Test Cases. 

How to use:

1. Clone this repository in your local machine by using GIT tool.

2. Setup the Database and configure the JDBC URL in application.properties.

![image](https://user-images.githubusercontent.com/91427581/134825829-dae2de6b-f6ae-4334-add9-b94ed82d5634.png)

3. Create the schema as per the scripts uploaded **qardio_schema.sql** , using PGAdmin 4 client.
4. Run the Test Cases from the root directory using command line 'mvn test'
5. Create JAR using command line from the root directory. 'mvn install' (JAR is created in Target folder of the application)
6. Run the Spring boot application using java -jar <jarname>


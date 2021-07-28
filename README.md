# banking-app-example

## Quickstart

### Requirements
    Java Development Kit 1.8
    Apache Maven 3.5
    Docker CE with Docker Compose
    Lombok Plug-in

### Development

Run the following command in the root directory of the project to start the MySQL server instance in a Docker container.

    docker-compose up

Run the following command in a separate terminal to deploy the **atm-service** into an embedded Tomcat servlet container.  
    
    mvn clean spring-boot:run

### IntelliJ IDEA

1. File > Settings > Build, Execution, Deployment > Compiler > Annotation Processors > Enable Annotation Processing
2. File > Settings > Plugins > Add Lombok Plugin
3. Restart
4. File > Invalidate Caches/Restart... > Invalidate and Restart > Restart

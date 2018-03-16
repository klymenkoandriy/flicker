# Flicker (*simple messenger*)

**Experimental project to try SpringBoot, SpringData, Docker, OAuth2, Zuul, Eureka, Feign, Swagger**

It is a simple messenger (single page application) that allows:
 - register new user
 - user login
 - user logout
 - read messages
 - write a message
 - use tag in the message (#tag)
 - filter messages by tag
 - select tag from 10 most popular
 - delete your own message

RESTful services are described by Swagger. Swagger maven plugin generates swagger-files on compile phase.
You can find them in the folders **'\user-service\generated\swagger-ui'** and **'\user-service\generated\swagger-ui'**.
There is a convenient online service for displaying them: https://editor.swagger.io

You can see the scheme of communication between microservices in the file '\resources\Flicker.pdf'.

See also the **'flicker-client'** project that uses this server and provides UI.

### **Deployment and start locally**

- **1. Setup MongoDB**

        hostname: localhost
        port: 27017
        authentication: none

- **2. Install the project using a 'dev' (default) profile.**

        mvn clean install

- **3. Run all services**

        java -jar eureka.jar
        java -jar user-service.jar
        java -jar message-service.jar
        java -jar authorization.jar
        java -jar zuul.jar

### **Deployment and start in the Docker**

- **1. Install the project using a 'test' profile.**

        mvn clean install -Ptest

- **1. Build and run using Docker CLI.**

        docker-compose up --build -d





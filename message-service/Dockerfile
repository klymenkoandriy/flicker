FROM openjdk:8
EXPOSE 8080
ADD target/message-service.jar app.jar
ENTRYPOINT ["java","-Dspring.data.mongodb.host=message-db","-Dspring.data.mongodb.port=27017", "-jar","/app.jar"]

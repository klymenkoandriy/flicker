FROM openjdk:8
EXPOSE 8761
ADD target/eureka.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

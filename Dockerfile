FROM openjdk:17-jdk-alpine3.14
ADD target/MovieService-0.0.1-SNAPSHOT.jar .
EXPOSE 8000
CMD java -jar MovieService-0.0.1-SNAPSHOT.jar

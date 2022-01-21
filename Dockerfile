# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY ./src /home/app/src
COPY ./pom.xml /home/app
RUN mvn clean install -f /home/app/pom.xml -DskipTests

#
# Package stage
#
FROM openjdk:8-jdk-alpine
COPY --from=build /home/app/target/estoque-rest.jar /usr/local/lib/estoque-rest.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/usr/local/lib/estoque-rest.jar"]
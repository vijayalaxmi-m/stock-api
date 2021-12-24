# maven stage to build jar using maven
FROM maven:3.6.3 AS maven
LABEL MAINTAINER="vijayalaxmi.manoharan@gmail.com"

WORKDIR /usr/src/app
COPY . /usr/src/app
# Compile and package the application to an executable JAR
RUN mvn package

# For Java 11,
FROM adoptopenjdk/openjdk11:alpine-jre

ARG JAR_FILE=stock-api-0.0.1-SNAPSHOT.jar

WORKDIR /opt/app

# Copy the stock-api-0.0.1-SNAPSHOT.jar from the maven stage to the /opt/app directory of the current stage.
COPY --from=maven /usr/src/app/target/${JAR_FILE} /opt/app/

ENTRYPOINT ["java","-jar","stock-api-0.0.1-SNAPSHOT.jar"]
ARG MSVC_NAME=msvc-k8-users

FROM eclipse-temurin:21-alpine AS builder
ARG MSVC_NAME
WORKDIR /app/$MSVC_NAME

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app
RUN mkdir ./logs
ARG MSVC_NAME
ARG TARGET_FOLDER=/app/$MSVC_NAME/target
COPY --from=builder $TARGET_FOLDER/msvc-k8-users-0.0.1-SNAPSHOT.jar msvc-k8-users.jar
ARG PORT_APP=8001
ENV PORT=$PORT_APP
EXPOSE $PORT

#CMD [ "java", "-jar", "msvc-k8-users.jar" ]
CMD sleep 20 && java -jar msvc-k8-users.jar
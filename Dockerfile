FROM eclipse-temurin:21-alpine AS builder

WORKDIR /app/msvc-k8-users

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app
RUN mkdir ./logs
COPY --from=builder /app/msvc-k8-users/target/msvc-k8-users-0.0.1-SNAPSHOT.jar msvc-k8-users.jar

ENV PORT=8000
EXPOSE $PORT

CMD [ "java", "-jar", "msvc-k8-users.jar" ]
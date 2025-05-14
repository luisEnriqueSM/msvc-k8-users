FROM amazoncorretto:21-alpine-jdk AS builder

WORKDIR /app/msvc-k8-users

COPY ./.mvn ./.mvn
COPY ./mvnw .
COPY ./pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target
# RUN ./mvnw dependency:go-offline
COPY ./src ./src

RUN ./mvnw clean package -DskipTests

FROM amazoncorretto:21-alpine-jdk

WORKDIR /app
COPY --from=builder /app/msvc-k8-users/target/msvc-k8-users-0.0.1-SNAPSHOT.jar msvc-k8-users.jar

EXPOSE 8001

ENTRYPOINT [ "java", "-jar", "msvc-k8-users.jar" ]
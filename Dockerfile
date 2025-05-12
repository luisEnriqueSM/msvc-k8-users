FROM amazoncorretto:21-alpine-jdk

WORKDIR /app
COPY ./target/msvc-k8-users-0.0.1-SNAPSHOT.jar msvc-k8-users.jar
EXPOSE 8001

ENTRYPOINT [ "java", "-jar", "msvc-k8-users.jar" ]
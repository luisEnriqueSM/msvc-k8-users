# msvc-k8-users

## Contenedores

```bash

# Contenedor MySQL
docker run -d -p 3306:3306 --network springcloud -v mysql_data_k8:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=sasa1234 -e MYSQL_DATABASE=msvc_k8_users  --restart=always --name mysql8 mysql:8.0.42

# Crear imagen
docker build -t msvc-k8-users:v1 .

# Crear imagen con buil Arg
docker build -t msvc-k8-users . --build-arg PORT_APP=8080

# Crear contenedor para msvc-k8-users
docker run -d -p 8001:8001 --name msvc-k8-users --network springcloud --restart=always msvc-k8-users:v1

# Crear contenedor para msvc-k8-users leyendo archivo de variables de entorno
docker run -p 8001:8001 --env-file .env -d --rm --name msvc-k8-users --network springcloud msvc-k8-users

# Crear contenedor para msvc-k8-users agregando variable de entorno PORT
docker run -p 8001:8089 -d --rm --name msvc-k8-users --network springcloud -e PORT=8089 msvc-k8-users
```
## Integración con otros servicios:

Este servicio se comunica directamente con el servicio msvc-k8-courses:
####  https://github.com/luisEnriqueSM/msvc-k8-courses

## Docker Hub:

#### luisenriquesm/msvc-k8-users: https://hub.docker.com/repository/docker/luisenriquesm/msvc-k8-users/general

#### luisenriquesm/msvc-k8-courses: https://hub.docker.com/repository/docker/luisenriquesm/msvc-k8-courses/general

## Docker Compose: 
#### https://github.com/luisEnriqueSM/msvc-k8-docker-compose
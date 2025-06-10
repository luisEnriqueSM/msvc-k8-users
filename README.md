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

## Docker version in details:

#### - [luisenriquesm/msvc-k8-users:v1](https://hub.docker.com/repository/docker/luisenriquesm/msvc-k8-users/tags/v1/sha256:4be5f4fbbc3c9963cd2676450d4e7580cb08b2a25521240ebe19dac742bca4b3)

OS/ARCH: linux/arm64

Version to be able to run on Local.

#### - [luisenriquesm/msvc-k8-users:v2](https://hub.docker.com/repository/docker/luisenriquesm/msvc-k8-users/tags/v2/sha256:d4b4b44318c5db057ea25b73a55c1dc92dc1f9bc6f3acd17a9d1efe5323e194b)

OS/ARCH: linux/amd64

Version to be able to run on  AWS EC2 and ECS.

#### - [luisenriquesm/msvc-k8-users:v3](https://hub.docker.com/repository/docker/luisenriquesm/msvc-k8-users/tags/v3/sha256:073a0c53e33f7afc6678e52a055fcfff2960f6db7ac1edc374e96d7a5ddc8958)

OS/ARCH: linux/amd64

Version to be able to run on AWS ECS without an Startup dependency ordering for mysql8 container. Sleep 20 command was added on Entrypoint on Dockerimage.

#### - [luisenriquesm/msvc-k8-users:v4](https://hub.docker.com/repository/docker/luisenriquesm/msvc-k8-users/tags/v4/sha256:e39a3f62f80de8d2436b9d6514368803f9591abb6349de23b78358ab5a726180)

OS/ARCH: linux/arm64

The purpose of this version is to cause an app crash to demonstrate K8s is able to restore the pod doing a restart.

#### - [luisenriquesm/msvc-k8-users:v5](https://hub.docker.com/repository/docker/luisenriquesm/msvc-k8-users/tags/v5/sha256:c630909603a71a0a1a5a887c4511de11ebc2483cd9e66e379853b024df9b242e)

OS/ARCH: linux/arm64

This version includes the integration with Spring Cloud Kubernetes. No more hard coded url all are handled by Kubernetes.
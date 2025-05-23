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
ocker run -p 8001:8089 -d --rm --name msvc-k8-users --network springcloud -e PORT=8089 msvc-k8-users
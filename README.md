# msvc-k8-users

## Contenedores

```bash

# Contenedor MySQL
docker run -d -p 3306:3306 --network springcloud -v mysql_data_k8:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=sasa1234 -e MYSQL_DATABASE=msvc_k8_users --name mysql8 mysql:8.0.42

# Crear imagen
docker build -t msvc-k8-users:v1 .

# Crear contenedor para msvc-k8-users
docker run -d -p 8001:8001 --name msvc-k8-users --network springcloud msvc-k8-users:v1
# msvc-k8-users

## Contenedor Mysql

```bash
docker run -d -p 3306:3306 --network springcloud -v mysql_data_k8:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=sasa1234 -e MYSQL_DATABASE=db_springboot_cloud --name mysql8 mysql:8.0.42

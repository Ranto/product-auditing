docker run --name stock-db -e MYSQL_ROOT_PASSWORD=admin -e MYSQL_USER=stock -e MYSQL_PASSWORD=stock -e MYSQL_DATABASE=stock -p 3308:3306 -d mysql:8.0.26

# RUN APPLICATION STEP BY STEP
## Database
### Using Docker

 1. [Install Docker](https://docs.docker.com/get-docker/)

 2. Run on terminal:

```bash
docker run --name mysqldb \
-v mysqldbvol:/var/lib/mysql \
--net=host \
-e MYSQL_ROOT_PASSWORD=<MYSQL-PASSWORD> \
-e MYSQL_ROOT_HOST='%' \
--rm -d mysql/mysql-server:latest
```

3. Check if your Docker process has started with command:
```bash
docker ps
```
Database has started!


## Database Tables

1. from terminal change directory(cd) to project's folder ```/assets/db```. <br />
   "mysql-fanclub.sql" file is located there with all the necessary command for automatically: <br />
   * Creating a new Database named "fanclub_db"
   * Creating the necessary tables
   * Inserting  values on tables

3. Run command:
```bash
mysql -h 127.0.0.1 -u root -p < mysql-fanclub.sql
```

Project's Database has been created!

## Application Properties

Update file named application.properties with your Database USERNAME(jdbc.user) and PASSWORD(jdbc.password) in ``src/main/resources/application.properties``.



## Application Server

Project has empedded a tomcat server.


##  Run Application

Enjoy!

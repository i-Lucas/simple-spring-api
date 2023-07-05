<h1> Spring Boot Simple API </h1>


<h2>Create Database</h2>

```bash
    sudo -i -u postgres
    psql
    CREATE DATABASE postgres_db_name; #change name
```


<h4>application.properties</h4>

```bash
    spring.datasource.url=jdbc:postgresql://localhost:5432/postgres_db_name #change name
```

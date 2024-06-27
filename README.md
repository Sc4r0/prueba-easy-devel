# Prueba Técnica EasyDevel

## Requisitos
- Java 17
- PostgreSQL 15
- Redis 7.2.5

## Estructura de la tabla

```sql
DROP TABLE IF EXISTS t_section;
CREATE TABLE t_section (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL
);
```

## Configuración del entorno

Para gestionar las configuraciones de la base de datos y Redis según el entorno, se utiliza el archivo `secrets.properties` dentro de la carpeta `resources`. Este archivo debe contener los valores específicos para cada entorno.

### Cómo Utilizar `secrets.properties`
- Copia el archivo `secrets.properties.example` y renómbralo a `secrets.properties`, asegúrate de que esté dentro de la carpeta `resources`.
- Rellena los valores según el entorno en el que estés trabajando (desarrollo, staging, producción, etc.).


### Ejemplo de `secrets.properties` para Desarrollo

```properties
JDBC_DATABASE_URL=jdbc:postgresql://localhost:5432/mydb
JDBC_DATABASE_USERNAME=DEV_USER
JDBC_DATABASE_PASSWORD=DEV_PASSWORD
REDIS_URL=localhost
REDIS_PORT=6379
```

### Ejemplo de `secrets.properties` para Producción

```properties
JDBC_DATABASE_URL=jdbc:postgresql://prod-db-server:5432/prod_db
JDBC_DATABASE_USERNAME=PROD_USER
JDBC_DATABASE_PASSWORD=PROD_PASSWORD
REDIS_URL=localhost
REDIS_PORT=6379
```

## Endpoints de la API

| Método | Endpoint         | Descripción                                                                                                                                                                                                                       |
|--------|------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `GET`  | `/sections/{id}` | Primero se consulta en Redis la existencia del registro. <br/>Si existe, lo devuelve. Si no existe en Redis, lo busca en la base de datos, lo inserta en Redis y lo devuelve.<br/>Si no existe el id, se devuelve un `Not Found`. |
| `POST` | `/sections`      | Crea una nueva sección                                                                                                                                                                                                            |
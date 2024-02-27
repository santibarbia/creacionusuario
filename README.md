# creacionusuario
Api RESTful de creacion de usuarios

# 1 Step
Clonarse el repositorio

# 2 Step
Levantar la instancia en algun ide de preferencia.

# 3 Step
Para probarlo, se puede acceder al swagger http://localhost:8080/api/swagger-ui/index.html#/

# 4 Step
Para validar la persistencia de datos en H2 se puede acceder a http://localhost:8080/api/h2-console/

# Importante
Para el acceso a la base de datos importante verificar usuario y contraseña, y ademas que la JDBC URL sea la siguiente "jdbc:h2:mem:testdb".
El diagrama se encuentra en el directorio de diagrama.
Por ultimo, el script de base de datos se encuentra en la siguiente ruta **src/main/resources/data.sql**

**JSON PARA PRUEBA DE REGISTRO**:

{
"name": "Santiago Ibarbia",
"email": "santiago@ibarbia.org",
"password": "prueba123",
"phones": [
{
"number": "1234567",
"cityCode": "1",
"countryCode": "57"
}
]
}
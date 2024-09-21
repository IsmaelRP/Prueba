# Shop
Demo de API para una tienda.

## Metodología
Se ha aplicado la metodología API First para el desarrollo, implementado primeramente el contrato OpenAPI y generando el Skeleton de las clases a extender.
Posteriormente, creamos las implementaciones de estas clases y proseguimos el desarrollo.

## Arquitectura
Se ha implementado una arquitectura de puertos y adaptadores (hexagonal) con sus correspondientes capas Infraestructure, Application y Domain.
En adición a esto, se aplica Vertical Slicing para separar por entidades de negocio.
Se aplica arquitectura CQRS para dividir entre consultas y comandos (actualmente sólo se han implementado consultas).

## Dependencias
Se ha utilizado Lombok para generar clases más limpias y organizadas.
Además se ha utilizado Tomcat como servidor y drivers JDBC para la conexión a la base de datos en memoria H2.

## Seguridad
IMPORTANTE:
Se ha utilizado Spring Security, concretamente una implementación con la que buscamos restringir el acceso a nuestra API a menos que se haga asociando un bearer token como autorización, dónde dicho token será obtenido a través del endpoint de login.

## Endpoints (puerto 8080)
El primer punto es obligatorio para poder realizar el resto de peticiones, ya que se deberá añadir el Bearer token obtenido a la autenticación de la colección en Postman (si se testea desde Postman), para que todos los endpoints hereden de este Bearer y se habilite el uso del segundo endpoint.

1º Obtener el bearer token de autorización (caduca a los 10 días y es único para la aplicación, por simplicidad)
	Parámetros en el payload: user, password
http://localhost:8080/users/login

2º Obtener un precio
	Parámetros en la URL: fecha de aplicación, id del producto, id de la cadena del grupo
http://localhost:8080/prices/findPrice



## Pruebas

### Testing unitario
Se ha usado Mockito y JUnit 4.
100% coverage en el controller y el service (ObtainPriceUseCase && PricesController).

### Testing de integración
Se ha usado MockMVC para atacar contra H2 y comprobar los 5 escenarios que se plantean en el enunciado.

### Swagger
Se incluye Swagger UI:
http://localhost:8080/swagger-ui/index.html

### Postman
Se incluye fichero json con múltiples peticiones para probar, ya sea los 5 tests del enunciado, el login para obtener el bearer y los distintos casos de error posibles.
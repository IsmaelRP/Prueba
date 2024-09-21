# Shop
Demo de API para una tienda.

## Arquitectura
Se ha implementado una arquitectura de puertos y adaptadores (hexagonal) con sus correspondientes capas Infraestructure, Application y Domain.
En adición a esto, se aplica Vertical Slicing para separar por entidades de negocio (se descarta usar Screaming Architecture por la simplicidad de la aplicación).
Se aplica arquitectura CQRS para dividir entre consultas y comandos (actualmente sólo se han implementado consultas).
TODO: se ha utilizado un micro reactivo

## Dependencias
Se ha utilizado Lombok para generar clases más limpias y organizadas, por lo que para poder arrancar el proyecto, será necesario disponer del .jar de Lombok instalado en el IDE.
Además se ha utilizado Tomcat como servidor y drivers JDBC para la conexión a la base de datos en memoria H2.
TODO: webflux para no bloqueante

## Seguridad
Se ha utilizado Spring Security, concretamente una implementación con la que buscamos restringir el acceso a nuestra API a menos que se haga asociando un bearer token como autorización, dónde dicho token será obtenido a través de un endpoint específico.

## Endpoints (puerto 8080)
1º Obtener un precio
	Parámetros en la URL: fecha de aplicación, id del producto, id de la cadena del grupo
http://localhost:8080/prices/findPrice

2º Obtener el bearer token de autorización (no caduca y es único para la aplicación, por simplicidad)
	Parámetros en el payload: user, password
http://localhost:8080/users/login

## Pruebas

### Testing unitario
Se ha usado Mockito y JUnit 4.
100% coverage en el controller y el service (ObtainPriceUseCase && PricesController).

Relativo al enunciado "Desarrollar unos test al endpoint rest que validen las siguientes peticiones al servicio con los datos del ejemplo..", no queda claro si se quiere un test de integración que ataque a la base de datos en sí, o en unos tests unitarios que mockeen estas peticiones, por lo que se ha optado por incluir los tests unitarios básicos para cubrir el coverage de la aplicación y añadir una colección de Postman con todas las peticiones ya mapeadas.

### Swagger
http://localhost:8080/swagger-ui/index.html

### Postman
Se incluye fichero json con múltiples peticiones para probar, ya sea los 5 tests del enunciado, el login para obtener el bearer y los distintos casos de error posibles.
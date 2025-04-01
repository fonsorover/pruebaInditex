# Price Service Application

Este proyecto es una API REST construida con **Spring Boot** para consultar los precios de productos aplicables según una marca, un producto y una fecha determinada. Utiliza una base de datos para almacenar los precios y los detalles de la tarifa de los productos. La arquitectura utilizada es **Hexagonal**, separando claramente la lógica de negocio de las capas de infraestructura y la presentación.

## Características

- Consultar el precio de un producto específico de una marca para una fecha determinada.
- Excepciones personalizadas para manejar los errores de forma adecuada.
- Uso de **Spring Boot**, **JPA** y **H2 Database** para la persistencia.
- **JUnit** y **MockMvc** para pruebas unitarias e integradas.
- **JaCoCo** para la cobertura de pruebas.

## Requisitos

- **JDK 17 o superior**
- **Maven** 3.6 o superior
- **Spring Boot** 3.4.4

## Estructura del Proyecto

La arquitectura del proyecto sigue el patrón **Hexagonal**, con las siguientes capas:

- **Application**: Capa de aplicacion.
- **Domain**: Contiene la lógica de negocio pura. Entity Price.
- **Infraestructura**: Controladores REST, excepcciones.


## Endpoints

### `GET /prices`

Este endpoint devuelve el precio aplicable de un producto en una fecha determinada para una marca específica.

#### Parámetros

- `brandId` (int) - El ID de la marca.
- `productId` (int) - El ID del producto.
- `applicationDate` (String) - La fecha en formato `yyyy-MM-dd'T'HH:mm:ss`.

#### Ejecucion del proyecto

`mvn spring-boot:run
`

#### Ejemplo de solicitud

- `GET http://localhost:8080/prices?brandId=1&productId=35455&applicationDate=2020-06-14T10:00:00`
#### Ejemplo de respuesta
{
"productId": 35455,
"brandId": 1,
"priceList": 1,
"startDate": "2020-06-14T00:00:00",
"endDate": "2020-12-31T23:59:59",
"price": 35.50,
"currency": "EUR"
}

#### Pruebas Unitarias y de integración

`mvn clean test
`
## Repository

### `https://github.com/fonsorover/pruebaInditex.git`


#### created by Alfonso Rodriguez Verdera

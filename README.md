# Proyecto de Microservicios con Spring Boot y Spring Cloud

Este proyecto implementa una arquitectura de microservicios utilizando **Spring Boot** y **Spring Cloud**. Se enfoca en el uso de principios **SOLID** y patrones de diseño como **Event-Driven**, **Strategy**, **Singleton** e **Inyección de Dependencias** para construir servicios altamente escalables y mantenibles. La persistencia de datos se gestiona con **MongoDB** reactivo, mientras que **WebFlux** y **WebClient** facilitan la comunicación asíncrona.

## Arquitectura

- **Servidor de Configuración**: Centraliza y gestiona las configuraciones de los microservicios.
- **Servidor de Descubrimiento (Eureka)**: Permite registrar y descubrir servicios de forma dinámica.
- **Comunicación Asíncrona con Kafka**: Para enviar mensajes entre microservicios de forma eficiente y desacoplada.
- **Infraestructura y Herramientas con Docker y Docker Compose**: Para contenerización y orquestación de servicios.
- **Persistencia de Datos**: Con MongoDB reactivo.
- **Comunicación Síncrona con WebClient**: A través de **Spring WebFlux**, para solicitudes HTTP entre microservicios.

## Tecnologías Utilizadas

- **Java** (JDK 17)
- **Spring Boot**
- **Spring Cloud (Eureka, Config Server)**
- **Spring WebFlux**
- **Kafka**
- **MongoDB**
- **Docker y Docker Compose**

## Principios y Patrones Aplicados

- **Principios SOLID**: Asegura que cada clase y componente siga prácticas de diseño que faciliten la mantenibilidad.
- **Patrón Event-Driven**: Implementado para manejar eventos asíncronos a través de Kafka.
- **Patrón Strategy**: Para manejar lógica específica sin usar estructuras `switch` o `if-else`.
- **Patrón Singleton**: Utilizado en servicios que deben tener una única instancia en el ciclo de vida.
- **Inyección de Dependencias**: A través del contexto de Spring, facilita el desacoplamiento y la prueba de unidades.

## Estructura del Proyecto

```plaintext
├── config-server         # Servidor de configuración centralizada
├── discovery-server      # Servidor de descubrimiento Eureka
├── client-server         # Microservicio centrado en el cliente
├── debitcard-server      # Microservicio centrado en la tarjeta de débito
├── transaction-server    # Microservicio centrado en la transacción (Depósito, retiro o transferencia)
└── docker-compose.yml    # Archivo para la orquestación con Docker Compose

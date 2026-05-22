# Parque turistico de dinosaurios 

Este proyecto es un simulador de gestión para un parque turístico de dinosaurios, desarrollado en Java. El sistema permite administrar configuraciones globales del parque y manejar diversos eventos dinámicos que ocurren dentro de las instalaciones.

## Arquitectura y Patrones de Diseño

Para garantizar un código limpio, escalable y mantenible, se han implementado los siguientes patrones de diseño:

### 1. Singleton (en `ParkConfig`)
El patrón **Singleton** se utiliza en la clase `ParkConfig` por las siguientes razones:

*   **Punto único de acceso:** La configuración del parque (cantidad maxima de personas, numero de dinosaurios, numero de turistas, etc) debe ser consistente en toda la aplicación.Al asegurar que solo exista una instancia de la configuración, evitamos que diferentes partes del sistema operen con datos contradictorios.
.

### 2. Strategy (en los Eventos)
El patrón **Strategy** se ha implementado para el manejo de eventos (emergencias) debido a:

*   **Encapsulamiento de algoritmos:** Cada tipo de evento tiene una lógica de ejecución distinta. El patrón permite encapsular estas variantes en clases separadas que implementan una interfaz común.
*   **Flexibilidad en tiempo de ejecución:** Permite cambiar el comportamiento del sistema dinámicamente según el evento que se esté disparando en el parque.

## Estructura del Proyecto

*   `config/`: Contiene la implementación del Singleton para los ajustes globales.
*   `events/`: Contiene la interfaz de estrategia y las implementaciones concretas para los comportamientos del parque.
*   `model/`: Clases de dominio como Dinosaurios, Visitantes y Guías.

## Requisitos

*   Java 17 o superior.
*   Maven.

## Compilación y Ejecución

Para compilar el proyecto y generar el artefacto, utiliza el siguiente comando de Maven:

```bash
mvn clean install
```

Para ejecutar la aplicación principal:

```bash
mvn exec:java
```


## Pruebas

Se han implementado pruebas unitarias para validar la correcta implementación de los patrones de diseño (verificando que el Singleton devuelva la misma instancia y que las estrategias se ejecuten según el evento).

Para ejecutar los tests:

```bash
mvn test
```

## Diagrama UML 
Enlace del digrama: https://drive.google.com/file/d/1MKo04n-bF2DyjhFr5T2SZwjTtaC3Z312/view?usp=sharing
<img src="Diagrama sin título.drawio.png" alt="Logo" width="1200">

---
*Proyecto desarrollado para el Laboratorio Java 360.*

# Proyecto Final - Programacion 3 Grupo 1 UMG

## Sistema Gestor de Árboles de Categorías E-Commerce (Multi-módulo)

## Descripción del Proyecto
Este proyecto es una aplicación empresarial desarrollada en **Java** utilizando el framework **Spring Boot**, diseñada específicamente para modelar, gestionar y analizar estructuras de datos arbóreas aplicadas a **catálogos y categorías de comercio electrónico (E-Commerce)**. La aplicación está estructurada bajo una arquitectura multi-módulo moderna, separando de manera estricta la lógica del negocio de los componentes del motor algorítmico.

El sistema permite organizar de forma jerárquica las categorías de productos (Categorías Raíz, Subcategorías y Productos Finales) y ejecutar algoritmos avanzados de recorrido, cálculo de niveles de profundidad y validación de integridad para evitar errores en la navegación de la tienda en línea.

## Desarrolladores / Colaboradores (Grupo 1)
Celso Gabriel Sarceño Corado - Carné: 0905-24-4036 (Desarrollo de Arquitectura, JPA y Contrato OpenAPI)

Mario David Tereta Sapalun - Carné:0905-15-***

Luis Emilio Flores Castillo - Carné:0905-23-**

---

## Arquitectura del Sistema (Multi-módulo)
El proyecto se encuentra dividido en dos módulos independientes gestionados a través de **Maven**:

1. **`tree-engine` (Motor de Árboles):** Módulo core de Java puro, libre de dependencias de frameworks, encargado estrictamente de la lógica algorítmica de los árboles y de las estrategias de almacenamiento (`CollectionsTreeStrategy` y `CustomTreeStrategy`).
2. **`app` (Capa de Aplicación):** Módulo basado en **Spring Boot** que expone los servicios web RESTful, gestiona la persistencia de datos (JPA/PostgreSQL) y procesa los controladores del negocio.

---

##  Tecnologías Utilizadas
* **Java 17** (Lenguaje principal de desarrollo)
* **Spring Boot 3.2.0** (Framework backend para la API REST)
* **Spring Data JPA** (Abstracción de persistencia y mapeo de datos)
* **PostgreSQL / H2 In-Memory Database** (Almacenamiento de las categorías)
* **Maven** (Gestor de dependencias multi-módulo)
* **OpenAPI 3.0 / Swagger UI** (Diseño, documentación y pruebas de la API)

---

## Requisitos e Instalación

### Prerrequisitos
* Java JDK 17 instalado correctamente.
* Apache Maven 3.8+ o superior.
* IDE recomendado: **Eclipse IDE** (con soporte de Spring Tools Suite) o IntelliJ IDEA.

### Pasos para Ejecutar Localmente
1. Clona el repositorio oficial de tu rama de trabajo:
   ```bash
   git clone -b caracteristica/estructura-y-jpa [https://github.com/Celso33Gabriel/Proyecto_Final_Progra3_grupo1.git](https://github.com/Celso33Gabriel/Proyecto_Final_Progra3_grupo1.git)

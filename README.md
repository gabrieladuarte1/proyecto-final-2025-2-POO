# proyecto-final-2025-2-POO
# Sistema Integral de Gestión de Inventario (SIGIA) - AgroStore

## Descripción del Proyecto

Sistema de gestión de inventario desarrollado en Java para la empresa AgroStore, que permite optimizar el control de productos, compras y ventas de insumos agrícolas.

## Características Principales

- Gestión completa de productos (CRUD)
- Gestión de compras a proveedores
- Gestión de ventas a clientes
- Carga masiva de datos desde Excel
- Generación de reportes (Excel)
- Interfaz gráfica intuitiva con Java Swing
- Persistencia de datos con MySQL
- Implementación de principios POO (Encapsulamiento, Herencia, Polimorfismo)

## Requisitos del Sistema

### Software Necesario
- **Java JDK 8 o superior**
- **MySQL 8.0 o superior**
- **Maven** (para gestión de dependencias) - Opcional, pero recomendado

### Dependencias
- MySQL Connector/J 8.0.33
- Apache POI 5.2.4 (para manejo de archivos Excel)
- Apache POI-OOXML 5.2.4

## Estructura del Proyecto

```
proyecto-final-2025-2-POO/
├── src/
│   └── main/
│       └── java/
│           └── co/
│               └── edu/
│                   └── sigia/
│                       ├── modelo/          # Clases del modelo de datos
│                       ├── dao/             # Data Access Object (acceso a BD)
│                       ├── gui/             # Interfaz gráfica (Swing)
│                       ├── util/            # Utilidades (Conexión BD, Excel)
│                       └── Main.java        # Clase principal
├── database/
│   └── schema.sql       # Script de creación de base de datos
├── pom.xml              # Configuración Maven
└── README.md
```

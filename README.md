
# proyecto-final-2025-2-POO
=======

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
=======
- Gestión completa de productos (CRUD)
- Gestión de compras a proveedores
- Gestión de ventas a clientes
- Carga masiva de datos desde Excel
- Generación de reportes (Excel)
- Interfaz gráfica intuitiva con Java Swing
- Persistencia de datos con MySQL
- Implementación de principios POO (Encapsulamiento, Herencia, Polimorfismo)


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
=======


## Uso de la Aplicación

### Gestión de Productos
- **Registrar**: Agregar nuevos productos al inventario
- **Editar**: Modificar información de productos existentes
- **Eliminar**: Remover productos del sistema
- **Buscar**: Buscar por nombre o categoría

### Gestión de Compras
- Registrar compras de productos a proveedores
- Las compras actualizan automáticamente el inventario
- Consultar historial de compras

### Gestión de Ventas
- Registrar ventas a clientes
- Las ventas disminuyen automáticamente el inventario
- Validación de stock disponible
- Consultar historial de ventas

### Carga Masiva
- Importar productos desde archivos Excel
- Importar compras desde archivos Excel
- Importar ventas desde archivos Excel

### Reportes
- Generar reporte de productos en inventario
- Generar reporte de compras por rango de fechas
- Generar reporte de ventas por rango de fechas
- Exportar reportes en formato Excel (.xlsx)

## Formato de Archivos Excel para Carga Masiva

### Productos
Columnas: Nombre | Descripción | Categoría | Cantidad | Precio | Precio Compra

### Compras
Columnas: ID Proveedor | ID Producto | Cantidad | Precio Unitario | Fecha (YYYY-MM-DD)

### Ventas
Columnas: ID Cliente | ID Producto | Cantidad | Precio Unitario | Fecha (YYYY-MM-DD)

## Arquitectura del Sistema

### Principios POO Aplicados

1. **Encapsulamiento**: Todas las clases del modelo tienen atributos privados con getters y setters
2. **Herencia**: 
   - `Proveedor` y `Cliente` heredan de `Persona`
   - `Compra` y `Venta` heredan de `Transaccion`
3. **Polimorfismo**: 
   - Métodos abstractos en `Persona` y `Transaccion`
   - Diferentes implementaciones según el tipo de objeto

### Patrones de Diseño

- **DAO (Data Access Object)**: Separación de la lógica de acceso a datos
- **Singleton**: Para la conexión a la base de datos




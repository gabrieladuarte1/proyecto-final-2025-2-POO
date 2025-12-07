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

## Instalación y Configuración

### 1. Clonar el Repositorio
```bash
git clone https://github.com/gabrieladuarte1/proyecto-final-2025-2-POO.git
cd proyecto-final-2025-2-POO
```

### 2. Configurar la Base de Datos MySQL

1. Asegúrate de tener MySQL instalado y ejecutándose
2. Ejecuta el script SQL para crear la base de datos:
   ```bash
   mysql -u root -p < database/schema.sql
   ```
   O desde MySQL Workbench o cliente MySQL, ejecuta el contenido del archivo `database/schema.sql`

3. Verifica que la base de datos `sigia_agrostore` se haya creado correctamente

### 3. Configurar la Conexión a la Base de Datos

Edita el archivo `src/main/java/co/edu/sigia/util/ConexionBD.java` y ajusta las siguientes variables según tu configuración:

```java
private static final String URL = "jdbc:mysql://localhost:3306/sigia_agrostore?useSSL=false&serverTimezone=UTC";
private static final String USUARIO = "root";  // Tu usuario de MySQL
private static final String PASSWORD = "";     // Tu contraseña de MySQL
```

### 4. Instalar Dependencias

#### Opción A: Usando Maven (Recomendado)
```bash
mvn clean install
```

#### Opción B: Descarga Manual de Librerías

Si no usas Maven, descarga las siguientes librerías y agrégalas al classpath:

1. **MySQL Connector/J**: https://dev.mysql.com/downloads/connector/j/
2. **Apache POI**: https://poi.apache.org/download.html
   - poi-5.2.4.jar
   - poi-ooxml-5.2.4.jar
   - poi-ooxml-lite-5.2.4.jar (si es necesario)
   - xmlbeans-5.x.x.jar (dependencia de POI)

## Compilación y Ejecución

### Usando Maven
```bash
# Compilar
mvn compile

# Ejecutar
mvn exec:java -Dexec.mainClass="co.edu.sigia.Main"
```

### Usando javac y java (sin Maven)

1. Compilar todos los archivos Java:
   ```bash
   javac -cp ".:path/to/mysql-connector.jar:path/to/poi/*" -d build src/main/java/co/edu/sigia/**/*.java
   ```

2. Ejecutar la aplicación:
   ```bash
   java -cp "build:path/to/mysql-connector.jar:path/to/poi/*" co.edu.sigia.Main
   ```

### Usando un IDE (IntelliJ IDEA, Eclipse, NetBeans)

1. Importa el proyecto como proyecto Maven (si usas Maven)
2. O crea un nuevo proyecto Java y configura las librerías manualmente
3. Ejecuta la clase `co.edu.sigia.Main`

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

## Solución de Problemas

### Error de Conexión a la Base de Datos
- Verifica que MySQL esté ejecutándose
- Confirma que las credenciales en `ConexionBD.java` sean correctas
- Asegúrate de que la base de datos `sigia_agrostore` exista

### Error al Importar desde Excel
- Verifica que el formato del archivo Excel coincida con el especificado
- Asegúrate de que las dependencias de Apache POI estén en el classpath

### Error de Compilación
- Verifica que todas las dependencias estén correctamente configuradas
- Asegúrate de usar Java 8 o superior

## Autor

Desarrollado para el curso de Programación Orientada a Objetos

## Licencia

Este proyecto es de uso educativo.

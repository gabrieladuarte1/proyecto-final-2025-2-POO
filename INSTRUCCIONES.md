# Instrucciones de Configuración y Uso - SIGIA

## Configuración Inicial Rápida

### Paso 1: Base de Datos MySQL

1. Abre MySQL Workbench o el cliente MySQL de tu preferencia
2. Ejecuta el script `database/schema.sql` para crear la base de datos y las tablas
3. Verifica que la base de datos `sigia_agrostore` se haya creado correctamente

### Paso 2: Configurar Conexión

1. Abre el archivo: `src/main/java/co/edu/sigia/util/ConexionBD.java`
2. Modifica las siguientes líneas según tu configuración:
   ```java
   private static final String USUARIO = "root";        // Tu usuario MySQL
   private static final String PASSWORD = "tuPassword";  // Tu contraseña MySQL
   ```

### Paso 3: Dependencias

#### Si usas Maven:
```bash
mvn clean install
```

#### Si NO usas Maven:
Necesitas descargar y agregar al classpath:

1. **mysql-connector-java-8.0.33.jar**
   - Descargar de: https://dev.mysql.com/downloads/connector/j/

2. **Apache POI** (archivos necesarios):
   - poi-5.2.4.jar
   - poi-ooxml-5.2.4.jar
   - poi-ooxml-lite-5.2.4.jar
   - xmlbeans-5.x.x.jar
   - Descargar de: https://poi.apache.org/download.html

### Paso 4: Ejecutar la Aplicación

**Con Maven:**
```bash
mvn exec:java -Dexec.mainClass="co.edu.sigia.Main"
```

**Sin Maven (desde IDE):**
- Configura las librerías en el classpath
- Ejecuta la clase `co.edu.sigia.Main`

## Uso Básico

### 1. Gestión de Productos

- **Agregar Producto**: Click en "Nuevo Producto" → Llenar formulario → Guardar
- **Editar Producto**: Seleccionar producto en la tabla → Click "Editar" → Modificar → Guardar
- **Eliminar Producto**: Seleccionar producto → Click "Eliminar" → Confirmar
- **Buscar**: Usar el campo de búsqueda para filtrar por nombre o categoría

### 2. Gestión de Compras

- **Registrar Compra**: Click "Nueva Compra" → Seleccionar proveedor y producto → Ingresar cantidad y precio → Guardar
- La compra automáticamente actualiza el inventario

### 3. Gestión de Ventas

- **Registrar Venta**: Click "Nueva Venta" → Seleccionar cliente y producto → Ingresar cantidad y precio → Guardar
- El sistema valida que haya stock disponible antes de registrar la venta
- La venta automáticamente reduce el inventario

### 4. Carga Masiva

#### Formato de Excel para Productos:
```
Nombre | Descripción | Categoría | Cantidad | Precio | Precio Compra
```

#### Formato de Excel para Compras:
```
ID Proveedor | ID Producto | Cantidad | Precio Unitario | Fecha (YYYY-MM-DD)
```

#### Formato de Excel para Ventas:
```
ID Cliente | ID Producto | Cantidad | Precio Unitario | Fecha (YYYY-MM-DD)
```

**Nota:** La primera fila debe contener los encabezados. Los datos empiezan en la segunda fila.

### 5. Generar Reportes

- Selecciona el tipo de reporte (Productos, Compras o Ventas)
- Para Compras y Ventas, ingresa el rango de fechas
- Selecciona la ubicación donde guardar el archivo Excel
- El reporte se genera automáticamente

## Solución de Problemas Comunes

### Error: "No se puede conectar a la base de datos"
- Verifica que MySQL esté ejecutándose
- Revisa usuario y contraseña en `ConexionBD.java`
- Verifica que la base de datos `sigia_agrostore` exista

### Error: "ClassNotFoundException: com.mysql.cj.jdbc.Driver"
- Asegúrate de tener mysql-connector-java en el classpath
- Si usas Maven, ejecuta `mvn clean install` nuevamente

### Error: "Cannot find symbol: class Workbook" (POI)
- Verifica que las librerías de Apache POI estén en el classpath
- Si usas Maven, las dependencias se descargan automáticamente

### Error: "Stock insuficiente" al vender
- Verifica que el producto tenga cantidad suficiente en inventario
- Registra una compra para aumentar el stock

## Estructura de la Base de Datos

El sistema crea automáticamente las siguientes tablas:
- `categorias` - Categorías de productos
- `proveedores` - Información de proveedores
- `clientes` - Información de clientes
- `productos` - Productos en inventario
- `compras` - Registro de compras
- `ventas` - Registro de ventas

## Notas Importantes

1. **Backup**: Realiza backups periódicos de la base de datos
2. **Validaciones**: El sistema valida stock antes de registrar ventas
3. **Formato de Fechas**: Usa formato YYYY-MM-DD para fechas
4. **ID Auto-generados**: Los IDs se generan automáticamente, no es necesario especificarlos

## Contacto y Soporte

Para problemas o dudas sobre el sistema, consulta la documentación en el README.md principal.



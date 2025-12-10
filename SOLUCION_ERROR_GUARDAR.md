# Solución: Error al Guardar Producto

## El Problema

Cuando intentas guardar un producto, aparece el mensaje:
**"Error al guardar el producto"**

Esto ocurre porque **no hay conexión a la base de datos MySQL**.

## Solución Rápida

### Opción 1: Configurar MySQL (Recomendado)

Para que la aplicación funcione completamente, necesitas:

#### Paso 1: Instalar MySQL

Si no tienes MySQL instalado:

**En Mac:**
```bash
brew install mysql
brew services start mysql
```

O descarga desde: https://dev.mysql.com/downloads/mysql/

#### Paso 2: Crear la Base de Datos

1. Abre una terminal
2. Ejecuta:
```bash
cd /Users/gabriela/Desktop/proyecto-final-2025-2-POO
mysql -u root -p < database/schema.sql
```

O desde MySQL Workbench:
- Abre MySQL Workbench
- Conéctate a tu servidor MySQL
- Abre el archivo `database/schema.sql`
- Ejecuta el script completo

#### Paso 3: Configurar Credenciales

Edita el archivo:
`src/main/java/co/edu/sigia/util/ConexionBD.java`

Cambia estas líneas según tu configuración:
```java
private static final String USUARIO = "root";        // Tu usuario MySQL
private static final String PASSWORD = "tuPassword";  // Tu contraseña MySQL
```

#### Paso 4: Agregar el Driver MySQL

**Opción A: Con Maven (Recomendado)**
```bash
mvn clean install
```

**Opción B: Manualmente**
1. Descarga MySQL Connector/J desde:
   https://dev.mysql.com/downloads/connector/j/
2. Extrae el archivo `mysql-connector-java-8.0.33.jar`
3. Colócalo en una carpeta `lib` dentro del proyecto
4. Ejecuta con:
```bash
java -cp "build/classes:lib/mysql-connector-java-8.0.33.jar" co.edu.sigia.Main
```

#### Paso 5: Recompilar y Ejecutar

```bash
cd /Users/gabriela/Desktop/proyecto-final-2025-2-POO
javac -d build/classes -sourcepath src/main/java:build/classes $(find src/main/java -name "*.java")
java -cp "build/classes:lib/mysql-connector-java-8.0.33.jar" co.edu.sigia.Main
```

### Opción 2: Usar la Aplicación Sin Base de Datos (Solo Visualización)

Si solo quieres ver la interfaz sin guardar datos:

- La aplicación se abrirá normalmente
- Podrás ver todos los menús y botones
- No podrás guardar productos, compras o ventas
- Los reportes no funcionarán

## Verificar que Funciona

Después de configurar MySQL:

1. **Abre la aplicación**
2. **Intenta crear un producto:**
   - Haz clic en "Nuevo Producto"
   - Llena el formulario:
     - Nombre: "Producto de Prueba"
     - Cantidad: 10
     - Precio: 10000
   - Haz clic en "Guardar"

3. **Si funciona:**
   - Verás el mensaje: "Producto creado exitosamente"
   - El producto aparecerá en la tabla

4. **Si aún falla:**
   - Revisa los mensajes de error en la terminal
   - Verifica que MySQL esté ejecutándose: `mysql -u root -p`
   - Verifica que la base de datos exista: `SHOW DATABASES;`

## Mensajes de Error Comunes

### "No suitable driver found"
- **Solución:** Agrega el driver MySQL Connector al classpath

### "Access denied for user"
- **Solución:** Verifica usuario y contraseña en `ConexionBD.java`

### "Unknown database 'sigia_agrostore'"
- **Solución:** Ejecuta el script `database/schema.sql`

### "Can't connect to MySQL server"
- **Solución:** Asegúrate de que MySQL esté ejecutándose

## ¿Necesitas Ayuda?

- Revisa `README.md` para instrucciones completas
- Revisa `INSTRUCCIONES.md` para configuración paso a paso
- Verifica los logs en la terminal para más detalles del error




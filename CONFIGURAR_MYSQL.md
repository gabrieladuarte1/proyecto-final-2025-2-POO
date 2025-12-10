# Guía para Configurar MySQL en Mac

## Paso 1: Instalar MySQL

### Opción A: Con Homebrew (Recomendado)

Si tienes Homebrew instalado:

```bash
# Instalar MySQL
brew install mysql

# Iniciar MySQL
brew services start mysql
```

### Opción B: Descargar desde el sitio oficial

1. Ve a: https://dev.mysql.com/downloads/mysql/
2. Descarga MySQL para macOS
3. Instala el paquete .dmg
4. Sigue el asistente de instalación
5. Anota la contraseña temporal que te da

## Paso 2: Verificar que MySQL está funcionando

```bash
mysql --version
```

Deberías ver algo como: `mysql  Ver 8.0.x`

## Paso 3: Crear la Base de Datos

Una vez que MySQL esté instalado y funcionando:

```bash
cd /Users/gabriela/Desktop/proyecto-final-2025-2-POO
mysql -u root -p < database/schema.sql
```

Te pedirá la contraseña de root. Si es la primera vez:
- Si instalaste con Homebrew: la contraseña puede estar vacía (presiona Enter)
- Si instalaste desde el sitio: usa la contraseña temporal que te dieron

## Paso 4: Configurar Credenciales en la Aplicación

Edita el archivo: `src/main/java/co/edu/sigia/util/ConexionBD.java`

Cambia estas líneas:

```java
private static final String USUARIO = "root";        // Tu usuario MySQL
private static final String PASSWORD = "";           // Tu contraseña MySQL (déjala vacía si no tienes)
```

Si tienes contraseña:
```java
private static final String PASSWORD = "tuPassword";  // Tu contraseña aquí
```

## Paso 5: Descargar el Driver MySQL Connector

### Opción A: Con Maven (si lo tienes instalado)

```bash
mvn clean install
```

### Opción B: Descarga Manual

1. Ve a: https://dev.mysql.com/downloads/connector/j/
2. Descarga: **Platform Independent** → **mysql-connector-j-8.0.33.tar.gz**
3. Extrae el archivo
4. Copia `mysql-connector-j-8.0.33.jar` a una carpeta `lib` en tu proyecto:

```bash
mkdir -p /Users/gabriela/Desktop/proyecto-final-2025-2-POO/lib
# Copia el archivo .jar a esta carpeta
```

## Paso 6: Ejecutar la Aplicación con el Driver

Si descargaste el driver manualmente:

```bash
cd /Users/gabriela/Desktop/proyecto-final-2025-2-POO
java -cp "build/classes:lib/mysql-connector-j-8.0.33.jar" co.edu.sigia.Main
```

O crea un script `ejecutar_con_mysql.sh`:

```bash
#!/bin/bash
cd /Users/gabriela/Desktop/proyecto-final-2025-2-POO
java -cp "build/classes:lib/mysql-connector-j-8.0.33.jar" co.edu.sigia.Main
```

## Verificar que Todo Funciona

1. Ejecuta la aplicación
2. Intenta crear un producto
3. Si funciona, verás: "Producto creado exitosamente" ✅

## Solución de Problemas

### "Access denied for user 'root'@'localhost'"

**Solución:**
```bash
# Cambiar contraseña de root
mysql -u root -p
ALTER USER 'root'@'localhost' IDENTIFIED BY 'nuevaPassword';
FLUSH PRIVILEGES;
exit;
```

Luego actualiza `ConexionBD.java` con la nueva contraseña.

### "Can't connect to MySQL server"

**Solución:**
```bash
# Iniciar MySQL
brew services start mysql
# O si instalaste desde el sitio:
sudo /usr/local/mysql/support-files/mysql.server start
```

### "Unknown database 'sigia_agrostore'"

**Solución:**
```bash
mysql -u root -p < database/schema.sql
```

## ¿Necesitas Ayuda?

- Revisa los logs en la terminal para más detalles
- Verifica que MySQL esté ejecutándose: `brew services list`
- Prueba conectarte manualmente: `mysql -u root -p`




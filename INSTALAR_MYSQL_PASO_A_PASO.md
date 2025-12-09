# 📋 Instalación de MySQL - Paso a Paso

## ✅ El mensaje de error es correcto

El mensaje que ves ("no hay conexión a la base de datos") es correcto. Ahora necesitamos instalar y configurar MySQL.

---

## 🚀 Opción 1: Instalar MySQL desde el sitio oficial (Más fácil)

### Paso 1: Descargar MySQL

1. Ve a: **https://dev.mysql.com/downloads/mysql/**
2. Selecciona: **macOS** → **macOS 12 (ARM, 64-bit)** o **macOS 12 (x86, 64-bit)** según tu Mac
3. Descarga el archivo `.dmg`
4. Haz doble clic para abrirlo
5. Ejecuta el instalador `.pkg`
6. Sigue el asistente de instalación
7. **IMPORTANTE:** Anota la contraseña temporal que te muestra al final

### Paso 2: Verificar la instalación

Abre una terminal y escribe:
```bash
/usr/local/mysql/bin/mysql --version
```

O agrega MySQL al PATH:
```bash
echo 'export PATH="/usr/local/mysql/bin:$PATH"' >> ~/.zshrc
source ~/.zshrc
mysql --version
```

### Paso 3: Iniciar MySQL

```bash
sudo /usr/local/mysql/support-files/mysql.server start
```

### Paso 4: Crear la base de datos

```bash
cd /Users/gabriela/Desktop/proyecto-final-2025-2-POO
/usr/local/mysql/bin/mysql -u root -p < database/schema.sql
```

Usa la contraseña temporal que te dio el instalador.

---

## 🍺 Opción 2: Instalar Homebrew y luego MySQL

### Paso 1: Instalar Homebrew

Pega esto en la terminal:
```bash
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```

### Paso 2: Instalar MySQL

```bash
brew install mysql
brew services start mysql
```

### Paso 3: Crear la base de datos

```bash
cd /Users/gabriela/Desktop/proyecto-final-2025-2-POO
mysql -u root -p < database/schema.sql
```

(Presiona Enter si no hay contraseña)

---

## ⚙️ Paso 5: Configurar la Aplicación

### Editar ConexionBD.java

Abre el archivo:
`src/main/java/co/edu/sigia/util/ConexionBD.java`

Busca estas líneas (alrededor de la línea 15-17):
```java
private static final String USUARIO = "root";
private static final String PASSWORD = ""; // Ajustar según tu configuración
```

**Si instalaste MySQL desde el sitio oficial:**
- Usa la contraseña temporal que te dio el instalador
- O cambia la contraseña primero (ver abajo)

**Si instalaste con Homebrew:**
- Puede que no tenga contraseña (déjala vacía `""`)
- O crea una contraseña nueva

### Cambiar contraseña de MySQL (Opcional)

Si quieres cambiar la contraseña:

```bash
mysql -u root -p
# Ingresa tu contraseña actual
ALTER USER 'root'@'localhost' IDENTIFIED BY 'nuevaPassword123';
FLUSH PRIVILEGES;
exit;
```

Luego actualiza `ConexionBD.java` con la nueva contraseña.

---

## 📦 Paso 6: Descargar el Driver MySQL Connector

### Opción A: Descarga Manual (Recomendado)

1. Ve a: **https://dev.mysql.com/downloads/connector/j/**
2. Selecciona: **Platform Independent**
3. Descarga: **mysql-connector-j-8.0.33.tar.gz**
4. Extrae el archivo
5. Busca el archivo: `mysql-connector-j-8.0.33.jar`
6. Crea una carpeta `lib` en tu proyecto:
   ```bash
   mkdir -p /Users/gabriela/Desktop/proyecto-final-2025-2-POO/lib
   ```
7. Copia el archivo `.jar` a esa carpeta

### Opción B: Con Maven (si lo tienes)

```bash
mvn clean install
```

---

## ▶️ Paso 7: Ejecutar la Aplicación

### Si descargaste el driver manualmente:

```bash
cd /Users/gabriela/Desktop/proyecto-final-2025-2-POO
java -cp "build/classes:lib/mysql-connector-j-8.0.33.jar" co.edu.sigia.Main
```

### Crear un script para facilitar:

Crea un archivo `ejecutar_con_mysql.sh`:

```bash
#!/bin/bash
cd /Users/gabriela/Desktop/proyecto-final-2025-2-POO
java -cp "build/classes:lib/mysql-connector-j-8.0.33.jar" co.edu.sigia.Main
```

Hazlo ejecutable:
```bash
chmod +x ejecutar_con_mysql.sh
```

Ejecuta:
```bash
./ejecutar_con_mysql.sh
```

---

## ✅ Verificar que Funciona

1. Abre la aplicación
2. Ve a "Gestión de Productos"
3. Haz clic en "Nuevo Producto"
4. Llena el formulario:
   - Nombre: "Producto de Prueba"
   - Cantidad: 10
   - Precio: 10000
5. Haz clic en "Guardar"

**Si funciona:** Verás "Producto creado exitosamente" ✅

**Si aún falla:** Revisa los mensajes de error en la terminal

---

## 🔧 Solución de Problemas Comunes

### "Access denied for user 'root'@'localhost'"
- Verifica la contraseña en `ConexionBD.java`
- O cambia la contraseña de MySQL (ver arriba)

### "Can't connect to MySQL server"
- Inicia MySQL:
  - Si instalaste desde sitio: `sudo /usr/local/mysql/support-files/mysql.server start`
  - Si instalaste con Homebrew: `brew services start mysql`

### "Unknown database 'sigia_agrostore'"
- Ejecuta: `mysql -u root -p < database/schema.sql`

### "No suitable driver found"
- Verifica que el archivo `mysql-connector-j-8.0.33.jar` esté en la carpeta `lib`
- Verifica que estés usando el classpath correcto al ejecutar

---

## 📞 ¿Necesitas Ayuda?

1. Revisa los mensajes de error en la terminal
2. Verifica que MySQL esté ejecutándose
3. Verifica que la base de datos exista: `mysql -u root -p -e "SHOW DATABASES;"`



# 🚀 Pasos Manuales para Configurar SIGIA

## Paso 1: Iniciar MySQL ⚡

Abre una terminal y ejecuta:

```bash
sudo /usr/local/mysql/support-files/mysql.server start
```

Te pedirá tu contraseña de administrador de Mac. Ingrésala.

**Verificar que funciona:**
```bash
/usr/local/mysql/bin/mysql --version
```

---

## Paso 2: Crear la Base de Datos 📊

En la misma terminal:

```bash
cd /Users/gabriela/Desktop/proyecto-final-2025-2-POO
/usr/local/mysql/bin/mysql -u root -p < database/schema.sql
```

**Te pedirá la contraseña de MySQL:**
- Si no tienes contraseña: presiona Enter
- Si tienes contraseña: ingrésala

**Verificar que se creó:**
```bash
/usr/local/mysql/bin/mysql -u root -p -e "SHOW DATABASES;"
```

Deberías ver `sigia_agrostore` en la lista.

---

## Paso 3: Descargar MySQL Connector/J 📦

### Opción A: Desde el navegador

1. Ve a: **https://dev.mysql.com/downloads/connector/j/**
2. En "Select Operating System" elige: **Platform Independent**
3. Descarga: **mysql-connector-j-8.0.33.tar.gz** (o la versión más reciente)
4. Extrae el archivo `.tar.gz`
5. Busca el archivo: `mysql-connector-j-8.0.33.jar`
6. Copia ese archivo a:
   ```
   /Users/gabriela/Desktop/proyecto-final-2025-2-POO/lib/
   ```

### Opción B: Desde la terminal (si tienes curl)

```bash
cd /Users/gabriela/Desktop/proyecto-final-2025-2-POO/lib
# Descargar (necesitas la URL directa)
# O descarga manualmente desde el navegador
```

**Verificar:**
```bash
ls -la /Users/gabriela/Desktop/proyecto-final-2025-2-POO/lib/*.jar
```

---

## Paso 4: Configurar Credenciales (si es necesario) 🔐

### Si MySQL tiene contraseña:

Abre el archivo:
```
/Users/gabriela/Desktop/proyecto-final-2025-2-POO/src/main/java/co/edu/sigia/util/ConexionBD.java
```

Busca la línea 17 y cambia:
```java
private static final String PASSWORD = "";  // ← Cambia esto
```

Por tu contraseña:
```java
private static final String PASSWORD = "tuPassword";  // Tu contraseña aquí
```

**Recompilar:**
```bash
cd /Users/gabriela/Desktop/proyecto-final-2025-2-POO
javac -d build/classes -sourcepath src/main/java:build/classes $(find src/main/java -name "*.java")
```

### Si MySQL NO tiene contraseña:
- No necesitas cambiar nada
- Déjala vacía: `""`

---

## Paso 5: Ejecutar la Aplicación ▶️

### Opción A: Con el script automático

```bash
cd /Users/gabriela/Desktop/proyecto-final-2025-2-POO
./ejecutar_con_mysql.sh
```

### Opción B: Manualmente

```bash
cd /Users/gabriela/Desktop/proyecto-final-2025-2-POO
java -cp "build/classes:lib/mysql-connector-j-8.0.33.jar" co.edu.sigia.Main
```

---

## ✅ Verificar que Todo Funciona

1. **La aplicación se abre** ✅
2. **Ve a "Gestión de Productos"**
3. **Haz clic en "Nuevo Producto"**
4. **Llena el formulario:**
   - Nombre: "Producto Prueba"
   - Descripción: "Test"
   - Cantidad: 10
   - Precio: 10000
5. **Haz clic en "Guardar"**

**Si ves:** "Producto creado exitosamente" ✅  
**¡Todo funciona perfectamente!** 🎉

---

## 🔧 Solución de Problemas

### "Access denied for user 'root'@'localhost'"
- Verifica la contraseña en `ConexionBD.java`
- O cambia la contraseña de MySQL (ver abajo)

### "Can't connect to MySQL server"
- Asegúrate de que MySQL esté ejecutándose:
  ```bash
  sudo /usr/local/mysql/support-files/mysql.server start
  ```

### "Unknown database 'sigia_agrostore'"
- Crea la base de datos:
  ```bash
  /usr/local/mysql/bin/mysql -u root -p < database/schema.sql
  ```

### "No suitable driver found"
- Verifica que el archivo `.jar` esté en `lib/`
- Verifica el nombre exacto del archivo
- Usa el script `ejecutar_con_mysql.sh`

---

## 📝 Cambiar Contraseña de MySQL

Si necesitas cambiar o crear una contraseña:

```bash
/usr/local/mysql/bin/mysql -u root -p
# Ingresa tu contraseña actual (o Enter si no tiene)

ALTER USER 'root'@'localhost' IDENTIFIED BY 'nuevaPassword123';
FLUSH PRIVILEGES;
exit;
```

Luego actualiza `ConexionBD.java` con la nueva contraseña.

---

## 🎯 Resumen Rápido

1. `sudo /usr/local/mysql/support-files/mysql.server start`
2. `cd proyecto && mysql -u root -p < database/schema.sql`
3. Descarga connector desde: https://dev.mysql.com/downloads/connector/j/
4. Colócalo en: `lib/mysql-connector-j-8.0.33.jar`
5. `./ejecutar_con_mysql.sh`

**¡Y listo!** 🚀



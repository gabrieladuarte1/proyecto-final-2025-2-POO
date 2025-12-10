# 📋 Requisitos Completos para que SIGIA Funcione Correctamente

## ✅ Lo que YA tienes

- ✓ Java JDK instalado (versión 24.0.1)
- ✓ Código fuente compilado
- ✓ Interfaz gráfica funcionando
- ✓ Estructura del proyecto completa

---

## ❌ Lo que FALTA para que funcione completamente

### 1. 🔴 MySQL Server (CRÍTICO)

**Estado actual:** ❌ NO instalado

**Qué hace:** Almacena todos los datos (productos, compras, ventas)

**Cómo instalarlo:**

**Opción A - Desde el sitio oficial (Recomendado):**
1. Ve a: https://dev.mysql.com/downloads/mysql/
2. Selecciona: macOS → Descarga el `.dmg`
3. Instala siguiendo el asistente
4. Anota la contraseña temporal que te muestra

**Opción B - Con Homebrew:**
```bash
brew install mysql
brew services start mysql
```

**Verificar instalación:**
```bash
mysql --version
```

---

### 2. 🔴 Base de Datos `sigia_agrostore` (CRÍTICO)

**Estado actual:** ❌ NO existe

**Qué hace:** Contiene las tablas para productos, compras, ventas, etc.

**Cómo crearla:**

```bash
cd /Users/gabriela/Desktop/proyecto-final-2025-2-POO
mysql -u root -p < database/schema.sql
```

O si MySQL está en otra ubicación:
```bash
/usr/local/mysql/bin/mysql -u root -p < database/schema.sql
```

**Verificar:**
```bash
mysql -u root -p -e "SHOW DATABASES;"
```
Deberías ver `sigia_agrostore` en la lista.

---

### 3. 🔴 MySQL Connector/J Driver (CRÍTICO)

**Estado actual:** ❌ NO instalado

**Qué hace:** Permite que Java se conecte a MySQL

**Cómo instalarlo:**

**Opción A - Descarga manual:**
1. Ve a: https://dev.mysql.com/downloads/connector/j/
2. Selecciona: **Platform Independent**
3. Descarga: `mysql-connector-j-8.0.33.tar.gz`
4. Extrae el archivo
5. Busca: `mysql-connector-j-8.0.33.jar`
6. Crea carpeta `lib` en tu proyecto:
   ```bash
   mkdir -p /Users/gabriela/Desktop/proyecto-final-2025-2-POO/lib
   ```
7. Copia el archivo `.jar` a la carpeta `lib`

**Opción B - Con Maven:**
```bash
mvn clean install
```

**Ubicación esperada:**
```
proyecto-final-2025-2-POO/
└── lib/
    └── mysql-connector-j-8.0.33.jar
```

---

### 4. ⚠️ Configuración de Credenciales (IMPORTANTE)

**Estado actual:** ⚠️ Necesita ajuste

**Qué hace:** Le dice a la aplicación cómo conectarse a MySQL

**Archivo a editar:**
`src/main/java/co/edu/sigia/util/ConexionBD.java`

**Líneas a modificar (alrededor de línea 15-17):**
```java
private static final String USUARIO = "root";
private static final String PASSWORD = "";  // ← Cambia esto con tu contraseña
```

**Si instalaste MySQL desde el sitio:**
- Usa la contraseña temporal que te dio el instalador
- O cambia la contraseña primero (ver instrucciones abajo)

**Si instalaste con Homebrew:**
- Puede que no tenga contraseña (déjala vacía `""`)
- O crea una nueva contraseña

**Cómo cambiar contraseña de MySQL:**
```bash
mysql -u root -p
# Ingresa tu contraseña actual
ALTER USER 'root'@'localhost' IDENTIFIED BY 'nuevaPassword123';
FLUSH PRIVILEGES;
exit;
```

---

## 📝 Checklist de Instalación

Marca cada paso cuando lo completes:

- [ ] 1. MySQL Server instalado
- [ ] 2. MySQL ejecutándose
- [ ] 3. Base de datos `sigia_agrostore` creada
- [ ] 4. MySQL Connector/J descargado
- [ ] 5. MySQL Connector/J colocado en carpeta `lib/`
- [ ] 6. Credenciales configuradas en `ConexionBD.java`
- [ ] 7. Aplicación ejecutada con el driver en el classpath

---

## ▶️ Cómo Ejecutar la Aplicación con Todo Configurado

### Método 1: Ejecución manual

```bash
cd /Users/gabriela/Desktop/proyecto-final-2025-2-POO
java -cp "build/classes:lib/mysql-connector-j-8.0.33.jar" co.edu.sigia.Main
```

### Método 2: Crear script de ejecución

Crea un archivo `ejecutar_final.sh`:

```bash
#!/bin/bash
cd /Users/gabriela/Desktop/proyecto-final-2025-2-POO
java -cp "build/classes:lib/mysql-connector-j-8.0.33.jar" co.edu.sigia.Main
```

Hazlo ejecutable:
```bash
chmod +x ejecutar_final.sh
```

Ejecuta:
```bash
./ejecutar_final.sh
```

---

## ✅ Verificar que Todo Funciona

### Paso 1: Verificar MySQL
```bash
mysql -u root -p -e "SHOW DATABASES;"
```
Debe mostrar `sigia_agrostore`.

### Paso 2: Verificar tablas
```bash
mysql -u root -p -e "USE sigia_agrostore; SHOW TABLES;"
```
Debe mostrar: categorias, clientes, compras, productos, proveedores, ventas.

### Paso 3: Probar la aplicación
1. Ejecuta la aplicación
2. Ve a "Gestión de Productos"
3. Haz clic en "Nuevo Producto"
4. Llena el formulario:
   - Nombre: "Producto Prueba"
   - Cantidad: 10
   - Precio: 10000
5. Haz clic en "Guardar"

**Si ves:** "Producto creado exitosamente" ✅
**Entonces:** ¡Todo funciona correctamente!

---

## 🔧 Resumen Rápido

Para que SIGIA funcione completamente necesitas:

1. **MySQL instalado y ejecutándose**
2. **Base de datos creada** (ejecutar `schema.sql`)
3. **MySQL Connector/J** en la carpeta `lib/`
4. **Credenciales configuradas** en `ConexionBD.java`
5. **Ejecutar con el classpath correcto** (incluyendo el driver)

---

## 📚 Archivos de Ayuda Disponibles

- `INSTALAR_MYSQL_PASO_A_PASO.md` - Guía detallada de instalación
- `CONFIGURAR_MYSQL.md` - Guía de configuración
- `SOLUCION_ERROR_GUARDAR.md` - Solución al error de guardado
- `README.md` - Documentación completa del proyecto
- `verificar_mysql.sh` - Script para verificar configuración

---

## ⚡ Orden de Instalación Recomendado

1. **Instalar MySQL** (15-20 minutos)
2. **Crear base de datos** (1 minuto)
3. **Descargar MySQL Connector** (2-3 minutos)
4. **Configurar credenciales** (1 minuto)
5. **Probar la aplicación** (2 minutos)

**Tiempo total estimado:** ~25 minutos

---

## 💡 Nota Importante

**La aplicación YA funciona parcialmente:**
- Puedes ver la interfaz gráfica ✅
- Puedes navegar por los menús ✅
- Puedes ver todos los módulos ✅

**Lo que NO funciona sin MySQL:**
- Guardar productos ❌
- Guardar compras ❌
- Guardar ventas ❌
- Generar reportes con datos ❌
- Carga masiva ❌

¡Pero una vez configures MySQL, todo funcionará perfectamente! 🚀



# ¿Dónde ver la ventana de la aplicación SIGIA?

## La ventana debería aparecer automáticamente

Cuando ejecutas la aplicación, deberías ver una ventana con el título:

**"SIGIA - Sistema Integral de Gestión de Inventario AgroStore"**

### ¿Dónde buscar la ventana?

1. **Revisa la barra de tareas (Dock en Mac)**
   - Busca el ícono de Java o una ventana minimizada
   - Haz clic en ella para restaurarla

2. **Revisa todas las ventanas abiertas**
   - Presiona `Cmd + Tab` (Mac) o `Alt + Tab` (Windows/Linux) para cambiar entre ventanas
   - Busca la ventana de SIGIA

3. **Si la ventana está minimizada**
   - Busca en la barra de tareas
   - Haz clic derecho para restaurar

4. **Si no ves la ventana**
   - La aplicación puede estar mostrando un mensaje de error
   - Revisa la terminal/consola donde ejecutaste el programa
   - Puede haber un error de conexión a la base de datos

## Ejecutar la aplicación

### Opción 1: Desde la terminal

```bash
cd /Users/gabriela/Desktop/proyecto-final-2025-2-POO
./ejecutar.sh
```

O directamente:
```bash
java -cp build/classes co.edu.sigia.Main
```

### Opción 2: Desde un IDE

1. Abre el proyecto en IntelliJ IDEA, Eclipse o NetBeans
2. Ejecuta la clase `co.edu.sigia.Main`
3. La ventana aparecerá automáticamente

## Características de la ventana

La ventana principal tiene:
- **Barra de menú superior** con opciones:
  - Inicio → Gestión de Productos
  - Compras → Gestión de Compras
  - Ventas → Gestión de Ventas
  - Reportes → Generar Reportes
  - Utilidades → Carga Masiva de Datos
  - Ayuda → Acerca de

- **Panel principal** que muestra diferentes módulos según la opción seleccionada

## Solución de problemas

### Si la ventana no aparece:

1. **Error de conexión a MySQL:**
   - La ventana debería abrirse de todas formas
   - Verás un mensaje de advertencia sobre la conexión
   - Configura MySQL antes de usar las funciones

2. **Revisa la terminal:**
   - Busca mensajes de error
   - Los errores comunes son sobre la conexión a la base de datos

3. **Verifica que Java esté instalado:**
   ```bash
   java -version
   ```

### Si ves errores de conexión:

La aplicación puede funcionar sin MySQL para ver la interfaz, pero necesitarás:

1. Instalar MySQL
2. Crear la base de datos: ejecutar `database/schema.sql`
3. Configurar credenciales en `src/main/java/co/edu/sigia/util/ConexionBD.java`

## Tamaño de la ventana

- Ancho: 1200 píxeles
- Alto: 700 píxeles
- Se centra automáticamente en la pantalla

## ¿Necesitas ayuda?

- Revisa el archivo `README.md` para instrucciones completas
- Revisa `INSTRUCCIONES.md` para configuración paso a paso




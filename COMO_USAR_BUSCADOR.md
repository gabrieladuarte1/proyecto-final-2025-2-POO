# Cómo usar el buscador de productos en SIGIA

## Ubicación del buscador

El buscador está en el panel de **"Gestión de Productos"** que es el módulo que se muestra por defecto al abrir la aplicación.

## Formas de buscar productos

### 1. Buscar por Nombre

1. **Escribe en el campo "Buscar":**
   - Puedes escribir parte del nombre del producto
   - Por ejemplo: `fertilizante`, `semilla`, `pesticida`
   - La búsqueda es parcial (no necesitas escribir el nombre completo)

2. **Haz clic en el botón "Buscar por Nombre"**
   - Mostrará todos los productos que contengan ese texto en el nombre

**Ejemplos de búsquedas:**
- `fert` → Encontrará productos con "fertilizante", "fertil", etc.
- `sem` → Encontrará productos con "semilla", "semillas", etc.
- `pesti` → Encontrará productos con "pesticida", "pesticidas", etc.

### 2. Buscar por Categoría

1. **Haz clic en el botón "Buscar por Categoría"**
   - Se abrirá un cuadro de diálogo con una lista de categorías

2. **Selecciona una categoría de la lista:**
   - Fertilizantes
   - Pesticidas
   - Semillas
   - Herramientas
   - Riego

3. **Haz clic en "OK"**
   - Mostrará solo los productos de esa categoría

### 3. Mostrar Todos los Productos

- Haz clic en el botón **"Mostrar Todos"**
- Esto carga todos los productos registrados en el sistema

## Qué puedes probar ahora

### Si NO hay productos (base de datos vacía):

1. **Prueba escribir algo en el buscador:**
   - Escribe cualquier texto, por ejemplo: `test`
   - Haz clic en "Buscar por Nombre"
   - Verás un mensaje: "No se encontraron productos con ese nombre"

2. **Prueba buscar por categoría:**
   - Haz clic en "Buscar por Categoría"
   - Selecciona cualquier categoría
   - La tabla quedará vacía si no hay productos en esa categoría

### Si hay productos en la base de datos:

Puedes buscar:
- **Por nombre:** Cualquier parte del nombre del producto
- **Por categoría:** Todos los productos de un tipo específico

## Pasos para probar completamente

1. **Primero, agrega algunos productos:**
   - Haz clic en el botón "Nuevo Producto"
   - Completa el formulario con:
     - Nombre: "Fertilizante Orgánico"
     - Descripción: "Fertilizante para cultivos"
     - Categoría: Selecciona "Fertilizantes"
     - Cantidad: 100
     - Precio: 25000
   - Guarda el producto

2. **Luego prueba el buscador:**
   - Escribe `fertilizante` en el campo buscar
   - Haz clic en "Buscar por Nombre"
   - Deberías ver el producto que acabas de crear

3. **Prueba buscar por categoría:**
   - Haz clic en "Buscar por Categoría"
   - Selecciona "Fertilizantes"
   - Verás el producto que creaste

## Nota importante

⚠️ **Si ves un mensaje de error sobre la conexión a la base de datos:**

El buscador necesita que la base de datos MySQL esté configurada y funcionando. Si aún no está configurada:

1. Los mensajes de error sobre conexión son normales
2. Puedes ver la interfaz y los botones funcionan
3. Pero no habrá datos para buscar hasta configurar MySQL

Para configurar MySQL, sigue las instrucciones en `README.md` o `INSTRUCCIONES.md`.

## Características del buscador

- ✅ Búsqueda parcial (encuentra productos que contengan el texto)
- ✅ No distingue entre mayúsculas y minúsculas
- ✅ Búsqueda instantánea al hacer clic en el botón
- ✅ Muestra resultados en una tabla organizada
- ✅ Puedes ver: ID, Nombre, Descripción, Categoría, Cantidad, Precio



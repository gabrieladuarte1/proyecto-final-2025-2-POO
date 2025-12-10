# 📄 Archivos de Ejemplo para Carga Masiva

## 📁 Archivos Disponibles

En la carpeta `ejemplos/` encontrarás 3 archivos CSV de ejemplo que puedes usar para probar la carga masiva:

### 1. `productos_ejemplo.csv`
**Formato:** Nombre, Descripción, Categoría, Cantidad, Precio, Precio Compra

**Contiene:** 10 productos de ejemplo con diferentes categorías:
- Fertilizantes
- Semillas
- Pesticidas
- Riego
- Herramientas

**Cómo usar:**
1. Abre la aplicación SIGIA
2. Ve a "Utilidades" → "Carga Masiva de Datos"
3. Haz clic en "Importar Productos desde Excel"
4. Selecciona el archivo `productos_ejemplo.csv`

### 2. `compras_ejemplo.csv`
**Formato:** ID Proveedor, ID Producto, Cantidad, Precio Unitario, Fecha

**Contiene:** 10 compras de ejemplo

**Nota:** Los IDs de proveedor y producto deben existir en la base de datos. Los archivos usan:
- Proveedores: ID 1 (AgroSuministros S.A.) y ID 2 (Fertilizantes del Valle)
- Productos: IDs del 1 al 10 (deben crearse primero con el archivo de productos)

**Cómo usar:**
1. Primero importa los productos
2. Ve a "Utilidades" → "Carga Masiva de Datos"
3. Haz clic en "Importar Compras desde Excel"
4. Selecciona el archivo `compras_ejemplo.csv`

### 3. `ventas_ejemplo.csv`
**Formato:** ID Cliente, ID Producto, Cantidad, Precio Unitario, Fecha

**Contiene:** 10 ventas de ejemplo

**Nota:** Los IDs de cliente y producto deben existir en la base de datos. Los archivos usan:
- Clientes: ID 1 (Finca Los Alpes) y ID 2 (Agricultura Moderna)
- Productos: IDs del 1 al 10 (deben crearse primero)

**Cómo usar:**
1. Primero importa los productos
2. Ve a "Utilidades" → "Carga Masiva de Datos"
3. Haz clic en "Importar Ventas desde Excel"
4. Selecciona el archivo `ventas_ejemplo.csv`

---

## 📝 Notas Importantes

### Formato de Archivos

Los archivos están en formato **CSV (Comma Separated Values)** que:
- ✅ Pueden abrirse en Excel
- ✅ Pueden abrirse en Google Sheets
- ✅ Son compatibles con la aplicación SIGIA

### Orden de Importación Recomendado

1. **Primero:** Importa productos (`productos_ejemplo.csv`)
2. **Segundo:** Importa compras (`compras_ejemplo.csv`)
3. **Tercero:** Importa ventas (`ventas_ejemplo.csv`)

### Verificar IDs

Antes de importar compras o ventas, verifica que:
- Los IDs de proveedores existan (1 y 2 están en la BD por defecto)
- Los IDs de clientes existan (1 y 2 están en la BD por defecto)
- Los IDs de productos existan (deben importarse primero)

### Fechas

Las fechas están en formato: `YYYY-MM-DD` (ejemplo: 2024-12-01)

---

## 🔧 Abrir en Excel

Para abrir estos archivos en Excel:

1. **Doble clic** en el archivo `.csv`
2. Excel se abrirá automáticamente
3. Si Excel pregunta sobre el formato, selecciona:
   - Delimitado
   - Delimitador: Coma
   - Codificación: UTF-8

O desde Excel:
1. Abre Excel
2. Archivo → Abrir
3. Selecciona el archivo `.csv`
4. En el asistente de importación, selecciona "Delimitado" y "Coma"

---

## ✅ Verificar Importación

Después de importar:

1. **Productos:** Ve a "Gestión de Productos" y verifica que aparezcan
2. **Compras:** Ve a "Compras" → "Consultar Historial"
3. **Ventas:** Ve a "Ventas" → "Consultar Historial"

---

## 📊 Datos de Ejemplo Incluidos

### Productos (10 productos)
- Fertilizantes: 2 productos
- Semillas: 2 productos
- Pesticidas: 2 productos
- Riego: 2 productos
- Herramientas: 2 productos

### Compras (10 compras)
- Diferentes proveedores
- Diferentes productos
- Fechas del 1 al 10 de diciembre 2024

### Ventas (10 ventas)
- Diferentes clientes
- Diferentes productos
- Fechas del 15 al 24 de diciembre 2024

---

## 🎯 Uso Rápido

1. Abre SIGIA
2. Ve a "Utilidades" → "Carga Masiva"
3. Haz clic en "Importar Productos desde Excel"
4. Selecciona: `ejemplos/productos_ejemplo.csv`
5. Espera el mensaje de confirmación
6. ¡Listo! Los productos están importados

---

## ⚠️ Solución de Problemas

### "No se pudieron importar productos"
- Verifica que el archivo tenga el formato correcto
- Verifica que las categorías existan en la base de datos
- Revisa que no haya caracteres especiales problemáticos

### "Error al procesar el archivo"
- Asegúrate de que el archivo esté en formato CSV
- Verifica que la codificación sea UTF-8
- Revisa que no haya filas vacías al final

### "IDs no encontrados" (para compras/ventas)
- Asegúrate de haber importado los productos primero
- Verifica que los IDs de proveedores y clientes sean correctos (1 y 2)

---

¡Listo para usar! 🚀


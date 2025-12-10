# 📂 Cómo Abrir los Archivos de Ejemplo

## 🔧 Si los archivos CSV no se abren correctamente

He creado versiones alternativas en formato de texto separado por tabuladores (TSV) que Excel puede abrir mejor.

---

## 📄 Archivos Disponibles

### Opción 1: Archivos CSV (originales)
- `productos_ejemplo.csv`
- `compras_ejemplo.csv`
- `ventas_ejemplo.csv`

### Opción 2: Archivos TXT (separados por tabuladores) - **RECOMENDADO**
- `productos_ejemplo.txt`
- `compras_ejemplo.txt`
- `ventas_ejemplo.txt`

---

## 💡 Cómo Abrir en Excel

### Método 1: Abrir directamente (TXT)

1. **Doble clic** en el archivo `.txt`
2. Excel se abrirá automáticamente
3. Si aparece el "Asistente para importar texto":
   - Selecciona: **Delimitado**
   - Siguiente
   - Marca: **Tabulación**
   - Siguiente
   - Finalizar

### Método 2: Desde Excel

1. Abre Excel
2. **Archivo** → **Abrir**
3. Selecciona el archivo `.txt` o `.csv`
4. Si aparece el asistente:
   - **Delimitado** (para CSV usa "Coma", para TXT usa "Tabulación")
   - Siguiente
   - Selecciona el delimitador
   - Finalizar

### Método 3: Arrastrar y soltar

1. Abre Excel
2. Arrastra el archivo desde el Finder a Excel
3. Sigue el asistente de importación

---

## 🔄 Convertir a Excel Real (.xlsx)

Si quieres archivos Excel reales:

1. Abre el archivo `.txt` o `.csv` en Excel
2. **Archivo** → **Guardar como**
3. Selecciona formato: **Libro de Excel (.xlsx)**
4. Guarda

---

## ✅ Verificar el Formato

Los archivos deben tener estas columnas:

### Productos:
- Nombre
- Descripción
- Categoría
- Cantidad
- Precio
- Precio Compra

### Compras:
- ID Proveedor
- ID Producto
- Cantidad
- Precio Unitario
- Fecha

### Ventas:
- ID Cliente
- ID Producto
- Cantidad
- Precio Unitario
- Fecha

---

## ⚠️ Solución de Problemas

### "El archivo no se abre"
- Intenta con los archivos `.txt` en lugar de `.csv`
- O abre Excel primero y luego el archivo desde dentro

### "Los datos se ven mal"
- Usa el asistente de importación de Excel
- Selecciona el delimitador correcto (coma para CSV, tabulación para TXT)

### "Caracteres raros"
- Asegúrate de que Excel use codificación UTF-8
- En el asistente, selecciona "UTF-8" como codificación

---

## 🎯 Uso en SIGIA

**IMPORTANTE:** La aplicación SIGIA acepta archivos CSV, así que:

1. Si abres el archivo en Excel y lo editas
2. **Guárdalo como CSV** (no como .xlsx)
3. Luego úsalo en SIGIA

O simplemente usa los archivos `.csv` directamente en SIGIA sin abrirlos en Excel.

---

## 📝 Nota

Los archivos `.txt` son más fáciles de abrir en Excel, pero SIGIA necesita archivos `.csv`. 

**Recomendación:**
- Usa `.txt` para ver/editar en Excel
- Usa `.csv` para importar en SIGIA
- O convierte el `.txt` a `.csv` después de editarlo


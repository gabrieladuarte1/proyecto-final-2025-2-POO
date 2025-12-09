#!/bin/bash

echo "=========================================="
echo "Reiniciando SIGIA con la última versión"
echo "=========================================="
echo ""

cd /Users/gabriela/Desktop/proyecto-final-2025-2-POO

# Cerrar cualquier instancia anterior
echo "Cerrando instancias anteriores..."
pkill -f "java.*Main\|java.*sigia" 2>/dev/null
sleep 2

# Recompilar
echo "Recompilando proyecto..."
rm -rf build
mkdir -p build/classes
javac -d build/classes -sourcepath src/main/java:build/classes $(find src/main/java -name "*.java") 2>&1 | grep -v "^Note:" | head -5

if [ $? -eq 0 ]; then
    echo "✓ Compilación exitosa"
    echo ""
    echo "Iniciando aplicación..."
    echo ""
    java -cp build/classes co.edu.sigia.Main &
    echo "La aplicación debería abrirse en unos segundos."
    echo "Si intentas guardar un producto, verás un mensaje de error más detallado."
else
    echo "✗ Error en la compilación"
    exit 1
fi



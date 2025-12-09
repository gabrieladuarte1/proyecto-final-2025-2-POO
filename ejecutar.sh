#!/bin/bash

echo "=========================================="
echo "Iniciando SIGIA - Sistema de Gestión"
echo "=========================================="
echo ""

cd /Users/gabriela/Desktop/proyecto-final-2025-2-POO

# Recompilar si es necesario
if [ ! -d "build/classes" ]; then
    echo "Compilando proyecto..."
    javac -d build/classes -sourcepath src/main/java:build/classes $(find src/main/java -name "*.java")
fi

echo "Ejecutando aplicación..."
echo ""
echo "La ventana de la aplicación debería abrirse en unos segundos."
echo "Si aparece un error de conexión a MySQL, no te preocupes,"
echo "la ventana se abrirá de todas formas."
echo ""
echo "Presiona Ctrl+C para cerrar la aplicación cuando termines."
echo ""

java -cp build/classes co.edu.sigia.Main



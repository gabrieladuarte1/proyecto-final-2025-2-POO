#!/bin/bash

# Script para compilar y ejecutar SIGIA
# Este script intenta compilar y ejecutar la aplicación

echo "=========================================="
echo "SIGIA - Sistema de Gestión de Inventario"
echo "=========================================="
echo ""

# Directorio del proyecto
PROJECT_DIR="/Users/gabriela/Desktop/proyecto-final-2025-2-POO"
cd "$PROJECT_DIR"

# Crear directorio de salida
mkdir -p build/classes

# Directorio de librerías (si existen)
LIB_DIR="lib"

echo "1. Verificando Java..."
java -version
if [ $? -ne 0 ]; then
    echo "ERROR: Java no está instalado o no está en el PATH"
    exit 1
fi
echo ""

echo "2. Compilando clases del modelo..."
javac -d build/classes -sourcepath src/main/java src/main/java/co/edu/sigia/modelo/*.java
if [ $? -ne 0 ]; then
    echo "ERROR: Fallo al compilar las clases del modelo"
    exit 1
fi
echo "✓ Clases del modelo compiladas"
echo ""

echo "3. Compilando clases de utilidad..."
javac -d build/classes -sourcepath src/main/java:build/classes src/main/java/co/edu/sigia/util/*.java 2>&1 | grep -v "error: package org.apache.poi" | grep -v "error: cannot find symbol.*POI"
if [ $? -ne 0 ]; then
    echo "⚠ ADVERTENCIA: Algunas clases de utilidad pueden tener errores (normal si faltan dependencias)"
fi
echo ""

echo "4. Compilando clases DAO..."
javac -d build/classes -sourcepath src/main/java:build/classes src/main/java/co/edu/sigia/dao/*.java 2>&1 | grep -v "error:"
if [ $? -ne 0 ]; then
    echo "⚠ ADVERTENCIA: Algunas clases DAO pueden tener errores"
fi
echo ""

echo "5. Compilando interfaz gráfica..."
javac -d build/classes -sourcepath src/main/java:build/classes src/main/java/co/edu/sigia/gui/*.java 2>&1 | grep -v "error:"
if [ $? -ne 0 ]; then
    echo "⚠ ADVERTENCIA: Algunas clases GUI pueden tener errores"
fi
echo ""

echo "6. Compilando clase principal..."
javac -d build/classes -sourcepath src/main/java:build/classes src/main/java/co/edu/sigia/Main.java 2>&1 | grep -v "error:"
echo ""

echo "=========================================="
echo "⚠ IMPORTANTE: Para ejecutar completamente necesitas:"
echo "  - MySQL Connector/J (mysql-connector-java.jar)"
echo "  - Apache POI (poi-*.jar, poi-ooxml-*.jar)"
echo ""
echo "Para instalar dependencias con Maven:"
echo "  mvn clean install"
echo ""
echo "O descarga manualmente desde:"
echo "  MySQL: https://dev.mysql.com/downloads/connector/j/"
echo "  POI: https://poi.apache.org/download.html"
echo "=========================================="
echo ""

# Intentar ejecutar de todas formas
echo "Intentando ejecutar la aplicación..."
echo ""

CLASSPATH="build/classes"

# Buscar librerías en directorio lib si existe
if [ -d "$LIB_DIR" ]; then
    for jar in "$LIB_DIR"/*.jar; do
        if [ -f "$jar" ]; then
            CLASSPATH="$CLASSPATH:$jar"
        fi
    done
fi

java -cp "$CLASSPATH" co.edu.sigia.Main 2>&1




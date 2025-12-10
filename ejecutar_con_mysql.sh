#!/bin/bash

cd /Users/gabriela/Desktop/proyecto-final-2025-2-POO

# Verificar que el driver existe
if [ ! -f "lib/mysql-connector-j-8.0.33.jar" ] && [ ! -f "lib/mysql-connector-java-8.0.33.jar" ]; then
    echo "⚠️ ADVERTENCIA: MySQL Connector no encontrado"
    echo ""
    echo "Por favor descarga el driver:"
    echo "1. Ve a: https://dev.mysql.com/downloads/connector/j/"
    echo "2. Descarga: Platform Independent → mysql-connector-j-8.0.33.tar.gz"
    echo "3. Extrae y copia el .jar a: lib/mysql-connector-j-8.0.33.jar"
    echo ""
    echo "Ejecutando sin driver (puede mostrar errores de conexión)..."
    java -cp build/classes co.edu.sigia.Main
else
    # Ejecutar con el driver
    if [ -f "lib/mysql-connector-j-8.0.33.jar" ]; then
        DRIVER="lib/mysql-connector-j-8.0.33.jar"
    else
        DRIVER="lib/mysql-connector-java-8.0.33.jar"
    fi
    
    echo "Ejecutando SIGIA con MySQL..."
    java -cp "build/classes:$DRIVER" co.edu.sigia.Main
fi



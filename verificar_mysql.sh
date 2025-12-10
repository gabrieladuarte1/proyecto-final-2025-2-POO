#!/bin/bash

echo "=========================================="
echo "Verificador de Configuración MySQL"
echo "=========================================="
echo ""

# Verificar si MySQL está instalado
echo "1. Verificando instalación de MySQL..."
if [ -f "/usr/local/mysql/bin/mysql" ]; then
    echo "   ✓ MySQL encontrado en /usr/local/mysql/bin/mysql"
    MYSQL_PATH="/usr/local/mysql/bin/mysql"
elif command -v mysql &> /dev/null; then
    echo "   ✓ MySQL encontrado en PATH"
    MYSQL_PATH="mysql"
else
    echo "   ✗ MySQL NO está instalado"
    echo ""
    echo "   Para instalar MySQL:"
    echo "   1. Ve a: https://dev.mysql.com/downloads/mysql/"
    echo "   2. Descarga e instala MySQL para macOS"
    echo "   3. O instala Homebrew y luego: brew install mysql"
    exit 1
fi

# Verificar si MySQL está ejecutándose
echo ""
echo "2. Verificando si MySQL está ejecutándose..."
if $MYSQL_PATH -u root -e "SELECT 1;" &> /dev/null; then
    echo "   ✓ MySQL está ejecutándose"
else
    echo "   ✗ MySQL NO está ejecutándose"
    echo ""
    echo "   Para iniciar MySQL:"
    if [ -f "/usr/local/mysql/support-files/mysql.server" ]; then
        echo "   sudo /usr/local/mysql/support-files/mysql.server start"
    else
        echo "   brew services start mysql"
    fi
    exit 1
fi

# Verificar si la base de datos existe
echo ""
echo "3. Verificando base de datos 'sigia_agrostore'..."
if $MYSQL_PATH -u root -e "USE sigia_agrostore;" &> /dev/null; then
    echo "   ✓ Base de datos existe"
else
    echo "   ✗ Base de datos NO existe"
    echo ""
    echo "   Para crear la base de datos:"
    echo "   cd /Users/gabriela/Desktop/proyecto-final-2025-2-POO"
    echo "   $MYSQL_PATH -u root -p < database/schema.sql"
    exit 1
fi

# Verificar driver MySQL
echo ""
echo "4. Verificando driver MySQL Connector..."
if [ -f "lib/mysql-connector-j-8.0.33.jar" ] || [ -f "lib/mysql-connector-java-8.0.33.jar" ]; then
    echo "   ✓ Driver encontrado"
else
    echo "   ⚠ Driver NO encontrado"
    echo ""
    echo "   Para descargar el driver:"
    echo "   1. Ve a: https://dev.mysql.com/downloads/connector/j/"
    echo "   2. Descarga: Platform Independent → mysql-connector-j-8.0.33.tar.gz"
    echo "   3. Extrae y copia el .jar a la carpeta lib/"
fi

echo ""
echo "=========================================="
echo "Verificación completada"
echo "=========================================="

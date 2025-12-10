#!/bin/bash

echo "=========================================="
echo "Configuración Automática de SIGIA"
echo "=========================================="
echo ""

cd /Users/gabriela/Desktop/proyecto-final-2025-2-POO

# Paso 1: Iniciar MySQL
echo "1. Iniciando MySQL..."
echo "   (Necesitarás ingresar tu contraseña de administrador)"
sudo /usr/local/mysql/support-files/mysql.server start

if [ $? -eq 0 ]; then
    echo "   ✓ MySQL iniciado correctamente"
else
    echo "   ✗ Error al iniciar MySQL"
    echo "   Intenta manualmente: sudo /usr/local/mysql/support-files/mysql.server start"
    exit 1
fi

echo ""
echo "2. Verificando conexión a MySQL..."
/usr/local/mysql/bin/mysql -u root -e "SELECT 1;" 2>&1
if [ $? -eq 0 ]; then
    echo "   ✓ Conexión exitosa"
else
    echo "   ⚠ Puede que necesites contraseña"
    echo "   Continuando con creación de BD (te pedirá contraseña si es necesario)..."
fi

echo ""
echo "3. Creando base de datos sigia_agrostore..."
echo "   (Si te pide contraseña, ingrésala ahora)"
/usr/local/mysql/bin/mysql -u root -p < database/schema.sql

if [ $? -eq 0 ]; then
    echo "   ✓ Base de datos creada correctamente"
else
    echo "   ✗ Error al crear base de datos"
    echo "   Intenta manualmente: /usr/local/mysql/bin/mysql -u root -p < database/schema.sql"
    exit 1
fi

echo ""
echo "4. Verificando base de datos..."
/usr/local/mysql/bin/mysql -u root -p -e "USE sigia_agrostore; SHOW TABLES;" 2>&1 | grep -v "Enter password"

echo ""
echo "=========================================="
echo "Configuración de MySQL completada"
echo "=========================================="
echo ""
echo "Próximos pasos:"
echo "1. Descarga MySQL Connector desde: https://dev.mysql.com/downloads/connector/j/"
echo "2. Colócalo en: lib/mysql-connector-j-8.0.33.jar"
echo "3. Ejecuta: ./ejecutar_con_mysql.sh"
echo ""



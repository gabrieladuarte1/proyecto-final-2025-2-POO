#!/bin/bash

echo "=========================================="
echo "Configuración Rápida de SIGIA"
echo "=========================================="
echo ""

cd /Users/gabriela/Desktop/proyecto-final-2025-2-POO

echo "📋 INSTRUCCIONES:"
echo ""
echo "Necesitas ejecutar estos comandos en tu terminal:"
echo ""
echo "1️⃣  Iniciar MySQL:"
echo "   sudo /usr/local/mysql/support-files/mysql.server start"
echo ""
echo "2️⃣  Crear la base de datos:"
echo "   /usr/local/mysql/bin/mysql -u root -pMichiEdward < database/schema.sql"
echo ""
echo "3️⃣  Verificar que se creó:"
echo "   /usr/local/mysql/bin/mysql -u root -pMichiEdward -e 'SHOW DATABASES;'"
echo ""
echo "=========================================="
echo ""

# Verificar si MySQL está ejecutándose
echo "Verificando si MySQL está ejecutándose..."
if /usr/local/mysql/bin/mysql -u root -pMichiEdward -e "SELECT 1;" 2>&1 | grep -q "ERROR"; then
    echo "❌ MySQL NO está ejecutándose o la contraseña es incorrecta"
    echo ""
    echo "Ejecuta esto primero:"
    echo "sudo /usr/local/mysql/support-files/mysql.server start"
else
    echo "✅ MySQL está ejecutándose"
    echo ""
    echo "Creando base de datos..."
    /usr/local/mysql/bin/mysql -u root -pMichiEdward < database/schema.sql 2>&1
    
    if [ $? -eq 0 ]; then
        echo "✅ Base de datos creada exitosamente"
        echo ""
        echo "Verificando tablas..."
        /usr/local/mysql/bin/mysql -u root -pMichiEdward -e "USE sigia_agrostore; SHOW TABLES;" 2>&1 | grep -v "Warning"
        echo ""
        echo "=========================================="
        echo "✅ MySQL configurado correctamente"
        echo "=========================================="
    else
        echo "❌ Error al crear base de datos"
    fi
fi

echo ""
echo "📦 Próximo paso: Descargar MySQL Connector"
echo ""
echo "1. Ve a: https://dev.mysql.com/downloads/connector/j/"
echo "2. Descarga: Platform Independent → mysql-connector-j-8.0.33.tar.gz"
echo "3. Extrae y copia mysql-connector-j-8.0.33.jar a: lib/"
echo ""
echo "Luego ejecuta: ./ejecutar_con_mysql.sh"
echo ""


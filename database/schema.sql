-- Base de datos SIGIA para AgroStore
-- Sistema de Gestión de Inventario

CREATE DATABASE IF NOT EXISTS sigia_agrostore;
USE sigia_agrostore;

-- Tabla de Categorías
CREATE TABLE IF NOT EXISTS categorias (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    descripcion TEXT,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de Proveedores
CREATE TABLE IF NOT EXISTS proveedores (
    id_proveedor INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(200) NOT NULL,
    contacto VARCHAR(100),
    telefono VARCHAR(20),
    email VARCHAR(100),
    direccion TEXT,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de Clientes
CREATE TABLE IF NOT EXISTS clientes (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(200) NOT NULL,
    contacto VARCHAR(100),
    telefono VARCHAR(20),
    email VARCHAR(100),
    direccion TEXT,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de Productos
CREATE TABLE IF NOT EXISTS productos (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(200) NOT NULL,
    descripcion TEXT,
    id_categoria INT,
    cantidad INT NOT NULL DEFAULT 0,
    precio DECIMAL(10, 2) NOT NULL,
    precio_compra DECIMAL(10, 2),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_categoria) REFERENCES categorias(id_categoria) ON DELETE SET NULL,
    INDEX idx_nombre (nombre),
    INDEX idx_categoria (id_categoria)
);

-- Tabla de Compras
CREATE TABLE IF NOT EXISTS compras (
    id_compra INT AUTO_INCREMENT PRIMARY KEY,
    id_proveedor INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10, 2) NOT NULL,
    precio_total DECIMAL(10, 2) NOT NULL,
    fecha_compra DATE NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_proveedor) REFERENCES proveedores(id_proveedor) ON DELETE RESTRICT,
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto) ON DELETE RESTRICT,
    INDEX idx_fecha_compra (fecha_compra),
    INDEX idx_proveedor (id_proveedor)
);

-- Tabla de Ventas
CREATE TABLE IF NOT EXISTS ventas (
    id_venta INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10, 2) NOT NULL,
    precio_total DECIMAL(10, 2) NOT NULL,
    fecha_venta DATE NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente) ON DELETE RESTRICT,
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto) ON DELETE RESTRICT,
    INDEX idx_fecha_venta (fecha_venta),
    INDEX idx_cliente (id_cliente)
);

-- Datos iniciales de ejemplo
INSERT INTO categorias (nombre, descripcion) VALUES
('Fertilizantes', 'Abonos y fertilizantes para cultivos'),
('Pesticidas', 'Productos para control de plagas'),
('Semillas', 'Semillas de diferentes cultivos'),
('Herramientas', 'Herramientas agrícolas'),
('Riego', 'Sistemas y componentes de riego');

INSERT INTO proveedores (nombre, contacto, telefono, email) VALUES
('AgroSuministros S.A.', 'Juan Pérez', '3001234567', 'contacto@agrosuministros.com'),
('Fertilizantes del Valle', 'María García', '3009876543', 'ventas@fertilizantesvalle.com');

INSERT INTO clientes (nombre, contacto, telefono, email) VALUES
('Finca Los Alpes', 'Carlos Rodríguez', '3101234567', 'admin@fincalosalpes.com'),
('Agricultura Moderna', 'Ana Martínez', '3209876543', 'info@agrimoderna.com');



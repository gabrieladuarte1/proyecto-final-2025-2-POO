package co.edu.sigia.dao;
import co.edu.sigia.modelo.Compra;
import co.edu.sigia.modelo.Proveedor;
import co.edu.sigia.modelo.Producto;
import co.edu.sigia.util.ConexionBD;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO para gestionar las operaciones CRUD de Compras
 */
public class CompraDAO {
    private Connection conexion;
    private ProveedorDAO proveedorDAO;
    private ProductoDAO productoDAO;
    
    public CompraDAO() {
        try {
            conexion = ConexionBD.getInstancia().getConexion();
        } catch (SQLException e) {
            System.err.println("Error al obtener conexión: " + e.getMessage());
            conexion = null;
        }
        proveedorDAO = new ProveedorDAO();
        productoDAO = new ProductoDAO();
    }
    
    /**
     * Crea una nueva compra en la base de datos
     */
    public boolean crear(Compra compra) {
        String sql = "INSERT INTO compras (id_proveedor, id_producto, cantidad, precio_unitario, precio_total, fecha_compra) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, compra.getProveedor().getId());
            ps.setInt(2, compra.getProducto().getIdProducto());
            ps.setInt(3, compra.getCantidad());
            ps.setDouble(4, compra.getPrecioUnitario());
            ps.setDouble(5, compra.getPrecioTotal());
            ps.setDate(6, Date.valueOf(compra.getFecha()));
            
            int filas = ps.executeUpdate();
            if (filas > 0) {
                // Obtener el ID generado
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        compra.setId(rs.getInt(1));
                    }
                }
                // Actualizar el inventario del producto
                compra.procesar();
                productoDAO.actualizar(compra.getProducto());
            }
            return filas > 0;
        } catch (SQLException e) {
            System.err.println("Error al crear compra: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Obtiene todas las compras
     */
    public List<Compra> obtenerTodas() {
        List<Compra> compras = new ArrayList<>();
        if (conexion == null) {
            return compras;
        }
        String sql = "SELECT * FROM compras ORDER BY fecha_compra DESC";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                compras.add(mapearCompra(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener compras: " + e.getMessage());
        }
        return compras;
    }
    
    /**
     * Obtiene compras en un rango de fechas
     */
    public List<Compra> obtenerPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        List<Compra> compras = new ArrayList<>();
        String sql = "SELECT * FROM compras WHERE fecha_compra BETWEEN ? AND ? ORDER BY fecha_compra DESC";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(fechaInicio));
            ps.setDate(2, Date.valueOf(fechaFin));
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                compras.add(mapearCompra(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener compras por rango de fechas: " + e.getMessage());
        }
        return compras;
    }
    
    /**
     * Mapea un ResultSet a un objeto Compra
     */
    private Compra mapearCompra(ResultSet rs) throws SQLException {
        Compra compra = new Compra();
        compra.setId(rs.getInt("id_compra"));
        
        Proveedor proveedor = proveedorDAO.obtenerPorId(rs.getInt("id_proveedor"));
        compra.setProveedor(proveedor);
        
        Producto producto = productoDAO.obtenerPorId(rs.getInt("id_producto"));
        compra.setProducto(producto);
        
        compra.setCantidad(rs.getInt("cantidad"));
        compra.setPrecioUnitario(rs.getDouble("precio_unitario"));
        compra.setPrecioTotal(rs.getDouble("precio_total"));
        compra.setFecha(rs.getDate("fecha_compra").toLocalDate());
        
        return compra;
    }
}


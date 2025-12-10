package co.edu.sigia.dao;
import co.edu.sigia.modelo.Venta;
import co.edu.sigia.modelo.Cliente;
import co.edu.sigia.modelo.Producto;
import co.edu.sigia.util.ConexionBD;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO para gestionar las operaciones CRUD de Ventas
 */
public class VentaDAO {
    private Connection conexion;
    private ClienteDAO clienteDAO;
    private ProductoDAO productoDAO;
    
    public VentaDAO() {
        try {
            conexion = ConexionBD.getInstancia().getConexion();
        } catch (SQLException e) {
            System.err.println("Error al obtener conexión: " + e.getMessage());
            conexion = null;
        }
        clienteDAO = new ClienteDAO();
        productoDAO = new ProductoDAO();
    }
    
    /**
     * Crea una nueva venta en la base de datos
     */
    public boolean crear(Venta venta) {
        String sql = "INSERT INTO ventas (id_cliente, id_producto, cantidad, precio_unitario, precio_total, fecha_venta) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, venta.getCliente().getId());
            ps.setInt(2, venta.getProducto().getIdProducto());
            ps.setInt(3, venta.getCantidad());
            ps.setDouble(4, venta.getPrecioUnitario());
            ps.setDouble(5, venta.getPrecioTotal());
            ps.setDate(6, Date.valueOf(venta.getFecha()));
            
            int filas = ps.executeUpdate();
            if (filas > 0) {
                // Obtener el ID generado
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        venta.setId(rs.getInt(1));
                    }
                }
                // Actualizar el inventario del producto
                if (venta.procesar()) {
                    productoDAO.actualizar(venta.getProducto());
                } else {
                    // Si no hay stock suficiente, revertir la inserción
                    eliminar(venta.getId());
                    return false;
                }
            }
            return filas > 0;
        } catch (SQLException e) {
            System.err.println("Error al crear venta: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Obtiene todas las ventas
     */
    public List<Venta> obtenerTodas() {
        List<Venta> ventas = new ArrayList<>();
        if (conexion == null) {
            return ventas;
        }
        String sql = "SELECT * FROM ventas ORDER BY fecha_venta DESC";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                ventas.add(mapearVenta(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener ventas: " + e.getMessage());
        }
        return ventas;
    }
    
    /**
     * Obtiene ventas en un rango de fechas
     */
    public List<Venta> obtenerPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        List<Venta> ventas = new ArrayList<>();
        String sql = "SELECT * FROM ventas WHERE fecha_venta BETWEEN ? AND ? ORDER BY fecha_venta DESC";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(fechaInicio));
            ps.setDate(2, Date.valueOf(fechaFin));
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                ventas.add(mapearVenta(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener ventas por rango de fechas: " + e.getMessage());
        }
        return ventas;
    }
    
    /**
     * Elimina una venta (para revertir transacciones)
     */
    public boolean eliminar(int idVenta) {
        String sql = "DELETE FROM ventas WHERE id_venta = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idVenta);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar venta: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Mapea un ResultSet a un objeto Venta
     */
    private Venta mapearVenta(ResultSet rs) throws SQLException {
        Venta venta = new Venta();
        venta.setId(rs.getInt("id_venta"));
        
        Cliente cliente = clienteDAO.obtenerPorId(rs.getInt("id_cliente"));
        venta.setCliente(cliente);
        
        Producto producto = productoDAO.obtenerPorId(rs.getInt("id_producto"));
        venta.setProducto(producto);
        
        venta.setCantidad(rs.getInt("cantidad"));
        venta.setPrecioUnitario(rs.getDouble("precio_unitario"));
        venta.setPrecioTotal(rs.getDouble("precio_total"));
        venta.setFecha(rs.getDate("fecha_venta").toLocalDate());
        
        return venta;
    }
}


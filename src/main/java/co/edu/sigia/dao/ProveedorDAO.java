package co.edu.sigia.dao;
import co.edu.sigia.modelo.Proveedor;
import co.edu.sigia.util.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO para gestionar las operaciones CRUD de Proveedores
 */
public class ProveedorDAO {
    private Connection conexion;
    
    public ProveedorDAO() {
        try {
            conexion = ConexionBD.getInstancia().getConexion();
        } catch (SQLException e) {
            System.err.println("Error al obtener conexión: " + e.getMessage());
            conexion = null;
        }
    }
    
    /**
     * Obtiene todos los proveedores
     */
    public List<Proveedor> obtenerTodos() {
        List<Proveedor> proveedores = new ArrayList<>();
        String sql = "SELECT * FROM proveedores ORDER BY nombre";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                proveedores.add(mapearProveedor(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener proveedores: " + e.getMessage());
        }
        return proveedores;
    }
    
    /**
     * Obtiene un proveedor por ID
     */
    public Proveedor obtenerPorId(int idProveedor) {
        String sql = "SELECT * FROM proveedores WHERE id_proveedor = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idProveedor);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return mapearProveedor(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener proveedor: " + e.getMessage());
        }
        return null;
    }
    
    /**
     * Mapea un ResultSet a un objeto Proveedor
     */
    private Proveedor mapearProveedor(ResultSet rs) throws SQLException {
        Proveedor proveedor = new Proveedor();
        proveedor.setId(rs.getInt("id_proveedor"));
        proveedor.setNombre(rs.getString("nombre"));
        proveedor.setContacto(rs.getString("contacto"));
        proveedor.setTelefono(rs.getString("telefono"));
        proveedor.setEmail(rs.getString("email"));
        proveedor.setDireccion(rs.getString("direccion"));
        return proveedor;
    }
}


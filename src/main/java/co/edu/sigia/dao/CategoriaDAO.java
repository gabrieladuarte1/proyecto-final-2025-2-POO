package co.edu.sigia.dao;
import co.edu.sigia.modelo.Categoria;
import co.edu.sigia.util.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO para gestionar las operaciones CRUD de Categorías
 */
public class CategoriaDAO {
    private Connection conexion;
    
    public CategoriaDAO() {
        try {
            conexion = ConexionBD.getInstancia().getConexion();
        } catch (SQLException e) {
            System.err.println("Error al obtener conexión: " + e.getMessage());
            conexion = null;
        }
    }
    
    /**
     * Obtiene todas las categorías
     */
    public List<Categoria> obtenerTodas() {
        List<Categoria> categorias = new ArrayList<>();
        if (conexion == null) {
            return categorias;
        }
        String sql = "SELECT * FROM categorias ORDER BY nombre";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setNombre(rs.getString("nombre"));
                categoria.setDescripcion(rs.getString("descripcion"));
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener categorías: " + e.getMessage());
        }
        return categorias;
    }
    
    /**
     * Obtiene una categoría por ID
     */
    public Categoria obtenerPorId(int idCategoria) {
        String sql = "SELECT * FROM categorias WHERE id_categoria = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idCategoria);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setNombre(rs.getString("nombre"));
                categoria.setDescripcion(rs.getString("descripcion"));
                return categoria;
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener categoría: " + e.getMessage());
        }
        return null;
    }
}


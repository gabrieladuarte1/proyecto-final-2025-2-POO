package co.edu.sigia.dao;
import co.edu.sigia.modelo.Producto;
import co.edu.sigia.modelo.Categoria;
import co.edu.sigia.util.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO para gestionar las operaciones CRUD de Productos
 */
public class ProductoDAO {
    private Connection conexion;
    
    public ProductoDAO() {
        try {
            conexion = ConexionBD.getInstancia().getConexion();
        } catch (SQLException e) {
            System.err.println("Error al obtener conexión: " + e.getMessage());
            conexion = null;
        }
    }
    
    /**
     * Crea un nuevo producto en la base de datos
     */
    public boolean crear(Producto producto) {
        if (conexion == null) {
            System.err.println("No hay conexión a la base de datos");
            return false;
        }
        String sql = "INSERT INTO productos (nombre, descripcion, id_categoria, cantidad, precio, precio_compra) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            if (producto.getCategoria() != null && producto.getCategoria().getIdCategoria() > 0) {
                ps.setInt(3, producto.getCategoria().getIdCategoria());
            } else {
                ps.setNull(3, java.sql.Types.INTEGER);
            }
            ps.setInt(4, producto.getCantidad());
            ps.setDouble(5, producto.getPrecio());
            ps.setDouble(6, producto.getPrecioCompra());
            
            int filas = ps.executeUpdate();
            if (filas > 0) {
                // Obtener el ID generado
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        producto.setIdProducto(rs.getInt(1));
                    }
                }
            }
            return filas > 0;
        } catch (SQLException e) {
            System.err.println("Error al crear producto: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Obtiene un producto por su ID
     */
    public Producto obtenerPorId(int idProducto) {
        String sql = "SELECT p.*, c.id_categoria, c.nombre as nombre_categoria, c.descripcion as desc_categoria " +
                    "FROM productos p LEFT JOIN categorias c ON p.id_categoria = c.id_categoria " +
                    "WHERE p.id_producto = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idProducto);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return mapearProducto(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener producto: " + e.getMessage());
        }
        return null;
    }
    
    /**
     * Obtiene todos los productos
     */
    public List<Producto> obtenerTodos() {
        List<Producto> productos = new ArrayList<>();
        if (conexion == null) {
            System.err.println("No hay conexión a la base de datos");
            return productos;
        }
        String sql = "SELECT p.*, c.id_categoria, c.nombre as nombre_categoria, c.descripcion as desc_categoria " +
                    "FROM productos p LEFT JOIN categorias c ON p.id_categoria = c.id_categoria " +
                    "ORDER BY p.nombre";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                productos.add(mapearProducto(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener productos: " + e.getMessage());
        }
        return productos;
    }
    
    /**
     * Busca productos por nombre
     */
    public List<Producto> buscarPorNombre(String nombre) {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT p.*, c.id_categoria, c.nombre as nombre_categoria, c.descripcion as desc_categoria " +
                    "FROM productos p LEFT JOIN categorias c ON p.id_categoria = c.id_categoria " +
                    "WHERE p.nombre LIKE ? ORDER BY p.nombre";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, "%" + nombre + "%");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                productos.add(mapearProducto(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar productos: " + e.getMessage());
        }
        return productos;
    }
    
    /**
     * Busca productos por categoría
     */
    public List<Producto> buscarPorCategoria(int idCategoria) {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT p.*, c.id_categoria, c.nombre as nombre_categoria, c.descripcion as desc_categoria " +
                    "FROM productos p LEFT JOIN categorias c ON p.id_categoria = c.id_categoria " +
                    "WHERE p.id_categoria = ? ORDER BY p.nombre";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idCategoria);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                productos.add(mapearProducto(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar productos por categoría: " + e.getMessage());
        }
        return productos;
    }
    
    /**
     * Actualiza un producto existente
     */
    public boolean actualizar(Producto producto) {
        String sql = "UPDATE productos SET nombre = ?, descripcion = ?, id_categoria = ?, " +
                    "cantidad = ?, precio = ?, precio_compra = ? WHERE id_producto = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setInt(3, producto.getCategoria() != null ? producto.getCategoria().getIdCategoria() : 0);
            ps.setInt(4, producto.getCantidad());
            ps.setDouble(5, producto.getPrecio());
            ps.setDouble(6, producto.getPrecioCompra());
            ps.setInt(7, producto.getIdProducto());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar producto: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Elimina un producto
     */
    public boolean eliminar(int idProducto) {
        String sql = "DELETE FROM productos WHERE id_producto = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idProducto);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar producto: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Mapea un ResultSet a un objeto Producto
     */
    private Producto mapearProducto(ResultSet rs) throws SQLException {
        Producto producto = new Producto();
        producto.setIdProducto(rs.getInt("id_producto"));
        producto.setNombre(rs.getString("nombre"));
        producto.setDescripcion(rs.getString("descripcion"));
        producto.setCantidad(rs.getInt("cantidad"));
        producto.setPrecio(rs.getDouble("precio"));
        producto.setPrecioCompra(rs.getDouble("precio_compra"));
        
        // Mapear categoría si existe
        if (rs.getInt("id_categoria") > 0) {
            Categoria categoria = new Categoria();
            categoria.setIdCategoria(rs.getInt("id_categoria"));
            categoria.setNombre(rs.getString("nombre_categoria"));
            categoria.setDescripcion(rs.getString("desc_categoria"));
            producto.setCategoria(categoria);
        }
        
        return producto;
    }
}


package co.edu.sigia.util;
import co.edu.sigia.dao.ProductoDAO;
import co.edu.sigia.dao.CompraDAO;
import co.edu.sigia.dao.VentaDAO;
import co.edu.sigia.dao.ProveedorDAO;
import co.edu.sigia.dao.ClienteDAO;
import co.edu.sigia.dao.CategoriaDAO;
import co.edu.sigia.modelo.*;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Clase utilitaria para importar datos desde archivos CSV
 * Versión simplificada que importa desde CSV
 */
public class ImportadorExcel {
    private ProductoDAO productoDAO;
    private CompraDAO compraDAO;
    private VentaDAO ventaDAO;
    private ProveedorDAO proveedorDAO;
    private ClienteDAO clienteDAO;
    private CategoriaDAO categoriaDAO;
    
    public ImportadorExcel() {
        productoDAO = new ProductoDAO();
        compraDAO = new CompraDAO();
        ventaDAO = new VentaDAO();
        proveedorDAO = new ProveedorDAO();
        clienteDAO = new ClienteDAO();
        categoriaDAO = new CategoriaDAO();
    }
    
    /**
     * Importa productos desde un archivo CSV
     * Formato: Nombre,Descripción,Categoría,Cantidad,Precio,Precio Compra
     */
    public int importarProductos(String rutaArchivo) {
        int contador = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea = reader.readLine(); // Leer encabezados
            if (linea == null) return 0;
            
            while ((linea = reader.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                
                try {
                    String[] campos = parseCSVLine(linea);
                    if (campos.length < 5) continue;
                    
                    Producto producto = new Producto();
                    producto.setNombre(campos[0]);
                    producto.setDescripcion(campos.length > 1 ? campos[1] : "");
                    
                    // Buscar categoría
                    if (campos.length > 2 && !campos[2].isEmpty()) {
                        List<Categoria> categorias = categoriaDAO.obtenerTodas();
                        for (Categoria cat : categorias) {
                            if (cat.getNombre().equalsIgnoreCase(campos[2])) {
                                producto.setCategoria(cat);
                                break;
                            }
                        }
                    }
                    
                    producto.setCantidad(Integer.parseInt(campos[3]));
                    producto.setPrecio(Double.parseDouble(campos[4]));
                    producto.setPrecioCompra(campos.length > 5 ? Double.parseDouble(campos[5]) : 0);
                    
                    if (productoDAO.crear(producto)) {
                        contador++;
                    }
                } catch (Exception e) {
                    System.err.println("Error al procesar línea: " + e.getMessage());
                }
            }
            
            return contador;
        } catch (IOException e) {
            System.err.println("Error al importar productos: " + e.getMessage());
            JOptionPane.showMessageDialog(null, 
                "Error al importar productos. Asegúrese de que el archivo esté en formato CSV.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }
    
    /**
     * Importa compras desde un archivo CSV
     */
    public int importarCompras(String rutaArchivo) {
        int contador = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            reader.readLine(); // Leer encabezados
            
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                
                try {
                    String[] campos = parseCSVLine(linea);
                    if (campos.length < 5) continue;
                    
                    int idProveedor = Integer.parseInt(campos[0]);
                    int idProducto = Integer.parseInt(campos[1]);
                    int cantidad = Integer.parseInt(campos[2]);
                    double precioUnitario = Double.parseDouble(campos[3]);
                    LocalDate fecha = LocalDate.parse(campos[4]);
                    
                    Proveedor proveedor = proveedorDAO.obtenerPorId(idProveedor);
                    Producto producto = productoDAO.obtenerPorId(idProducto);
                    
                    if (proveedor != null && producto != null) {
                        Compra compra = new Compra(proveedor, producto, cantidad, precioUnitario, fecha);
                        if (compraDAO.crear(compra)) {
                            contador++;
                        }
                    }
                } catch (Exception e) {
                    System.err.println("Error al procesar línea: " + e.getMessage());
                }
            }
            
            return contador;
        } catch (IOException e) {
            System.err.println("Error al importar compras: " + e.getMessage());
            return -1;
        }
    }
    
    /**
     * Importa ventas desde un archivo CSV
     */
    public int importarVentas(String rutaArchivo) {
        int contador = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            reader.readLine(); // Leer encabezados
            
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                
                try {
                    String[] campos = parseCSVLine(linea);
                    if (campos.length < 5) continue;
                    
                    int idCliente = Integer.parseInt(campos[0]);
                    int idProducto = Integer.parseInt(campos[1]);
                    int cantidad = Integer.parseInt(campos[2]);
                    double precioUnitario = Double.parseDouble(campos[3]);
                    LocalDate fecha = LocalDate.parse(campos[4]);
                    
                    Cliente cliente = clienteDAO.obtenerPorId(idCliente);
                    Producto producto = productoDAO.obtenerPorId(idProducto);
                    
                    if (cliente != null && producto != null) {
                        Venta venta = new Venta(cliente, producto, cantidad, precioUnitario, fecha);
                        if (ventaDAO.crear(venta)) {
                            contador++;
                        }
                    }
                } catch (Exception e) {
                    System.err.println("Error al procesar línea: " + e.getMessage());
                }
            }
            
            return contador;
        } catch (IOException e) {
            System.err.println("Error al importar ventas: " + e.getMessage());
            return -1;
        }
    }
    
    /**
     * Parsea una línea CSV simple
     */
    private String[] parseCSVLine(String linea) {
        // Parsing CSV simple - maneja comillas básicas
        return linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
    }
}

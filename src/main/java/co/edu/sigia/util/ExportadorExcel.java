package co.edu.sigia.util;
import co.edu.sigia.modelo.Producto;
import co.edu.sigia.modelo.Compra;
import co.edu.sigia.modelo.Venta;
import javax.swing.JOptionPane;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase utilitaria para exportar datos a archivos Excel/CSV
 * Versión simplificada que exporta a CSV si Apache POI no está disponible
 */
public class ExportadorExcel {
    
    /**
     * Exporta una lista de productos a un archivo Excel/CSV
     */
    public boolean exportarProductos(List<Producto> productos, String rutaArchivo) {
        // Intentar exportar como CSV si no hay POI
        if (rutaArchivo.endsWith(".xlsx")) {
            rutaArchivo = rutaArchivo.replace(".xlsx", ".csv");
        }
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(rutaArchivo))) {
            // Escribir encabezados
            writer.println("ID,Nombre,Descripción,Categoría,Cantidad,Precio,Precio Compra");
            
            // Escribir datos
            for (Producto producto : productos) {
                writer.printf("%d,\"%s\",\"%s\",\"%s\",%d,%.2f,%.2f%n",
                    producto.getIdProducto(),
                    producto.getNombre(),
                    producto.getDescripcion() != null ? producto.getDescripcion().replace("\"", "\"\"") : "",
                    producto.getCategoria() != null ? producto.getCategoria().getNombre() : "",
                    producto.getCantidad(),
                    producto.getPrecio(),
                    producto.getPrecioCompra()
                );
            }
            
            return true;
        } catch (IOException e) {
            System.err.println("Error al exportar productos: " + e.getMessage());
            JOptionPane.showMessageDialog(null, 
                "Error al exportar productos: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    /**
     * Exporta una lista de compras a un archivo Excel/CSV
     */
    public boolean exportarCompras(List<Compra> compras, String rutaArchivo) {
        if (rutaArchivo.endsWith(".xlsx")) {
            rutaArchivo = rutaArchivo.replace(".xlsx", ".csv");
        }
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(rutaArchivo))) {
            writer.println("ID,Proveedor,Producto,Cantidad,Precio Unitario,Precio Total,Fecha");
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            for (Compra compra : compras) {
                writer.printf("%d,\"%s\",\"%s\",%d,%.2f,%.2f,%s%n",
                    compra.getId(),
                    compra.getProveedor().getNombre(),
                    compra.getProducto().getNombre(),
                    compra.getCantidad(),
                    compra.getPrecioUnitario(),
                    compra.getPrecioTotal(),
                    compra.getFecha().format(formatter)
                );
            }
            
            return true;
        } catch (IOException e) {
            System.err.println("Error al exportar compras: " + e.getMessage());
            JOptionPane.showMessageDialog(null, 
                "Error al exportar compras: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    /**
     * Exporta una lista de ventas a un archivo Excel/CSV
     */
    public boolean exportarVentas(List<Venta> ventas, String rutaArchivo) {
        if (rutaArchivo.endsWith(".xlsx")) {
            rutaArchivo = rutaArchivo.replace(".xlsx", ".csv");
        }
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(rutaArchivo))) {
            writer.println("ID,Cliente,Producto,Cantidad,Precio Unitario,Precio Total,Fecha");
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            for (Venta venta : ventas) {
                writer.printf("%d,\"%s\",\"%s\",%d,%.2f,%.2f,%s%n",
                    venta.getId(),
                    venta.getCliente().getNombre(),
                    venta.getProducto().getNombre(),
                    venta.getCantidad(),
                    venta.getPrecioUnitario(),
                    venta.getPrecioTotal(),
                    venta.getFecha().format(formatter)
                );
            }
            
            return true;
        } catch (IOException e) {
            System.err.println("Error al exportar ventas: " + e.getMessage());
            JOptionPane.showMessageDialog(null, 
                "Error al exportar ventas: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}

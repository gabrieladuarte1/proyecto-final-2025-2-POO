package co.edu.sigia.modelo;
import java.time.LocalDate;

/**
 * Clase que representa una compra a un proveedor
 * Hereda de Transaccion aplicando herencia
 */
public class Compra extends Transaccion {
    private Proveedor proveedor;
    
    // Constructor por defecto
    public Compra() {
        super();
    }
    
    // Constructor con parámetros
    public Compra(Proveedor proveedor, Producto producto, int cantidad, 
                 double precioUnitario, LocalDate fecha) {
        super(producto, cantidad, precioUnitario, fecha);
        this.proveedor = proveedor;
    }
    
    // Constructor completo
    public Compra(int id, Proveedor proveedor, Producto producto, int cantidad, 
                 double precioUnitario, double precioTotal, LocalDate fecha) {
        super(id, producto, cantidad, precioUnitario, precioTotal, fecha);
        this.proveedor = proveedor;
    }
    
    // Getter y Setter
    public Proveedor getProveedor() {
        return proveedor;
    }
    
    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
    
    /**
     * Procesa la compra aumentando el inventario
     * Implementación del método abstracto (polimorfismo)
     */
    @Override
    public boolean procesar() {
        if (producto != null && cantidad > 0 && precioUnitario > 0) {
            producto.aumentarCantidad(cantidad);
            return true;
        }
        return false;
    }
    
    /**
     * Retorna el tipo de transacción
     * Implementación del método abstracto (polimorfismo)
     */
    @Override
    public String getTipoTransaccion() {
        return "Compra";
    }
    
    @Override
    public String toString() {
        return "Compra #" + id + " - " + producto.getNombre() + " x" + cantidad;
    }
}



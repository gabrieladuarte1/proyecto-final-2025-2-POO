package co.edu.sigia.modelo;
import java.time.LocalDate;

/**
 * Clase que representa una venta a un cliente
 * Hereda de Transaccion aplicando herencia
 */
public class Venta extends Transaccion {
    private Cliente cliente;
    
    // Constructor por defecto
    public Venta() {
        super();
    }
    
    // Constructor con parámetros
    public Venta(Cliente cliente, Producto producto, int cantidad, 
                double precioUnitario, LocalDate fecha) {
        super(producto, cantidad, precioUnitario, fecha);
        this.cliente = cliente;
    }
    
    // Constructor completo
    public Venta(int id, Cliente cliente, Producto producto, int cantidad, 
                double precioUnitario, double precioTotal, LocalDate fecha) {
        super(id, producto, cantidad, precioUnitario, precioTotal, fecha);
        this.cliente = cliente;
    }
    
    // Getter y Setter
    public Cliente getCliente() {
        return cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    /**
     * Procesa la venta disminuyendo el inventario
     * Implementación del método abstracto (polimorfismo)
     */
    @Override
    public boolean procesar() {
        if (producto != null && producto.tieneStock(cantidad) && cantidad > 0 && precioUnitario > 0) {
            return producto.disminuirCantidad(cantidad);
        }
        return false;
    }
    
    /**
     * Retorna el tipo de transacción
     * Implementación del método abstracto (polimorfismo)
     */
    @Override
    public String getTipoTransaccion() {
        return "Venta";
    }
    
    @Override
    public String toString() {
        return "Venta #" + id + " - " + producto.getNombre() + " x" + cantidad;
    }
}




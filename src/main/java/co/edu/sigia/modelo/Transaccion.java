package co.edu.sigia.modelo;
import java.time.LocalDate;

/**
 * Clase abstracta que representa una transacción
 * Aplica herencia - será la clase base para Compra y Venta
 */
public abstract class Transaccion {
    protected int id;
    protected Producto producto;
    protected int cantidad;
    protected double precioUnitario;
    protected double precioTotal;
    protected LocalDate fecha;
    
    // Constructor por defecto
    public Transaccion() {
    }
    
    // Constructor con parámetros
    public Transaccion(Producto producto, int cantidad, double precioUnitario, LocalDate fecha) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.precioTotal = cantidad * precioUnitario;
        this.fecha = fecha;
    }
    
    // Constructor completo
    public Transaccion(int id, Producto producto, int cantidad, double precioUnitario, 
                      double precioTotal, LocalDate fecha) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.precioTotal = precioTotal;
        this.fecha = fecha;
    }
    
    // Getters y Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Producto getProducto() {
        return producto;
    }
    
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    public int getCantidad() {
        return cantidad;
    }
    
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        calcularTotal();
    }
    
    public double getPrecioUnitario() {
        return precioUnitario;
    }
    
    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
        calcularTotal();
    }
    
    public double getPrecioTotal() {
        return precioTotal;
    }
    
    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }
    
    public LocalDate getFecha() {
        return fecha;
    }
    
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
    /**
     * Calcula el total de la transacción
     */
    protected void calcularTotal() {
        this.precioTotal = cantidad * precioUnitario;
    }
    
    /**
     * Método abstracto para procesar la transacción
     * Aplica polimorfismo
     */
    public abstract boolean procesar();
    
    /**
     * Método abstracto para obtener el tipo de transacción
     * Aplica polimorfismo
     */
    public abstract String getTipoTransaccion();
}




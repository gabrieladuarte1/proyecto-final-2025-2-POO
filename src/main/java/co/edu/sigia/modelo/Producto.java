package co.edu.sigia.modelo;

/**
 * Clase que representa un producto en el inventario
 * Aplica principios de encapsulamiento
 */
public class Producto {
    private int idProducto;
    private String nombre;
    private String descripcion;
    private Categoria categoria;
    private int cantidad;
    private double precio;
    private double precioCompra;
    
    // Constructor por defecto
    public Producto() {
    }
    
    // Constructor con parámetros básicos
    public Producto(String nombre, String descripcion, Categoria categoria, int cantidad, double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.precio = precio;
    }
    
    // Constructor completo
    public Producto(int idProducto, String nombre, String descripcion, Categoria categoria, 
                   int cantidad, double precio, double precioCompra) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.precio = precio;
        this.precioCompra = precioCompra;
    }
    
    // Getters y Setters (Encapsulamiento)
    public int getIdProducto() {
        return idProducto;
    }
    
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public Categoria getCategoria() {
        return categoria;
    }
    
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    public int getCantidad() {
        return cantidad;
    }
    
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public double getPrecio() {
        return precio;
    }
    
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public double getPrecioCompra() {
        return precioCompra;
    }
    
    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }
    
    /**
     * Aumenta la cantidad del producto en inventario
     */
    public void aumentarCantidad(int cantidad) {
        this.cantidad += cantidad;
    }
    
    /**
     * Disminuye la cantidad del producto en inventario
     */
    public boolean disminuirCantidad(int cantidad) {
        if (this.cantidad >= cantidad) {
            this.cantidad -= cantidad;
            return true;
        }
        return false;
    }
    
    /**
     * Verifica si hay stock disponible
     */
    public boolean tieneStock(int cantidadSolicitada) {
        return this.cantidad >= cantidadSolicitada;
    }
    
    @Override
    public String toString() {
        return nombre + " (Stock: " + cantidad + ")";
    }
}




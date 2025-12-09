package co.edu.sigia.modelo;

/**
 * Clase que representa un proveedor
 * Hereda de Persona aplicando herencia
 */
public class Proveedor extends Persona {
    
    // Constructor por defecto
    public Proveedor() {
        super();
    }
    
    // Constructor con parámetros
    public Proveedor(String nombre, String contacto, String telefono, String email, String direccion) {
        super(nombre, contacto, telefono, email, direccion);
    }
    
    // Constructor completo
    public Proveedor(int id, String nombre, String contacto, String telefono, String email, String direccion) {
        super(id, nombre, contacto, telefono, email, direccion);
    }
    
    // Implementación del método abstracto (polimorfismo)
    @Override
    public String getTipoPersona() {
        return "Proveedor";
    }
}



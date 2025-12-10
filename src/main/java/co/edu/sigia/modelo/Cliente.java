package co.edu.sigia.modelo;

/**
 * Clase que representa un cliente
 * Hereda de Persona aplicando herencia
 */
public class Cliente extends Persona {
    
    // Constructor por defecto
    public Cliente() {
        super();
    }
    
    // Constructor con parámetros
    public Cliente(String nombre, String contacto, String telefono, String email, String direccion) {
        super(nombre, contacto, telefono, email, direccion);
    }
    
    // Constructor completo
    public Cliente(int id, String nombre, String contacto, String telefono, String email, String direccion) {
        super(id, nombre, contacto, telefono, email, direccion);
    }
    
    // Implementación del método abstracto (polimorfismo)
    @Override
    public String getTipoPersona() {
        return "Cliente";
    }
}




package co.edu.sigia.modelo;

/**
 * Clase abstracta que representa una persona
 * Aplica herencia - será la clase base para Proveedor y Cliente
 */
public abstract class Persona {
    protected int id;
    protected String nombre;
    protected String contacto;
    protected String telefono;
    protected String email;
    protected String direccion;
    
    // Constructor por defecto
    public Persona() {
    }
    
    // Constructor con parámetros básicos
    public Persona(String nombre, String contacto, String telefono, String email, String direccion) {
        this.nombre = nombre;
        this.contacto = contacto;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
    }
    
    // Constructor completo
    public Persona(int id, String nombre, String contacto, String telefono, String email, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.contacto = contacto;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
    }
    
    // Getters y Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getContacto() {
        return contacto;
    }
    
    public void setContacto(String contacto) {
        this.contacto = contacto;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    // Método abstracto para obtener el tipo de persona (polimorfismo)
    public abstract String getTipoPersona();
    
    @Override
    public String toString() {
        return nombre;
    }
}




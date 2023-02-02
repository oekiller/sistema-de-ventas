
package Modelo;


public class Proveedor {
    
    private int id;
    private int dni;
    private String nombre;
    private String telefono;
    private String direccion;
    private String razon;

    public Proveedor() {
    }

    public Proveedor(int id, int ruc, String nombre, String telefono, String direccion, String razon) {
        this.id = id;
        this.dni = ruc;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.razon = razon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRuc() {
        return dni;
    }

    public void setRuc(int ruc) {
        this.dni = ruc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }
}

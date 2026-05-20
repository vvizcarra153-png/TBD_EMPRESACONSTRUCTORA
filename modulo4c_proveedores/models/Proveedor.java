package modulo4c_proveedores.models;

import java.io.Serializable;

/**
 * Modelo de Proveedor
 */
public class Proveedor implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int idProveedor;
    private String nombreProveedor;
    private String telefonoProveedor;
    private String direccionProveedor;
    private int idCategoriaProveedor;
    
    public Proveedor() {
    }
    
    public Proveedor(String nombreProveedor, String telefonoProveedor, String direccionProveedor, int idCategoriaProveedor) {
        this.nombreProveedor = nombreProveedor;
        this.telefonoProveedor = telefonoProveedor;
        this.direccionProveedor = direccionProveedor;
        this.idCategoriaProveedor = idCategoriaProveedor;
    }
    
    public int getIdProveedor() { return idProveedor; }
    public void setIdProveedor(int idProveedor) { this.idProveedor = idProveedor; }
    
    public String getNombreProveedor() { return nombreProveedor; }
    public void setNombreProveedor(String nombreProveedor) { this.nombreProveedor = nombreProveedor; }
    
    public String getTelefonoProveedor() { return telefonoProveedor; }
    public void setTelefonoProveedor(String telefonoProveedor) { this.telefonoProveedor = telefonoProveedor; }
    
    public String getDireccionProveedor() { return direccionProveedor; }
    public void setDireccionProveedor(String direccionProveedor) { this.direccionProveedor = direccionProveedor; }
    
    public int getIdCategoriaProveedor() { return idCategoriaProveedor; }
    public void setIdCategoriaProveedor(int idCategoriaProveedor) { this.idCategoriaProveedor = idCategoriaProveedor; }
    
    @Override
    public String toString() {
        return nombreProveedor + " - " + telefonoProveedor;
    }
}

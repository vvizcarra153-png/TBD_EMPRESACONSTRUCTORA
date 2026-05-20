package modulo4c_proveedores.models;

import java.io.Serializable;

/**
 * Modelo de Categoría de Proveedor
 */
public class CategoriaProveedor implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int idCategoriaProveedor;
    private String nombreCategoriaProveedor;
    private String descripcionCategoriaProveedor;
    
    public CategoriaProveedor() {
    }
    
    public CategoriaProveedor(String nombreCategoriaProveedor, String descripcionCategoriaProveedor) {
        this.nombreCategoriaProveedor = nombreCategoriaProveedor;
        this.descripcionCategoriaProveedor = descripcionCategoriaProveedor;
    }
    
    public int getIdCategoriaProveedor() { return idCategoriaProveedor; }
    public void setIdCategoriaProveedor(int idCategoriaProveedor) { this.idCategoriaProveedor = idCategoriaProveedor; }
    
    public String getNombreCategoriaProveedor() { return nombreCategoriaProveedor; }
    public void setNombreCategoriaProveedor(String nombreCategoriaProveedor) { this.nombreCategoriaProveedor = nombreCategoriaProveedor; }
    
    public String getDescripcionCategoriaProveedor() { return descripcionCategoriaProveedor; }
    public void setDescripcionCategoriaProveedor(String descripcionCategoriaProveedor) { this.descripcionCategoriaProveedor = descripcionCategoriaProveedor; }
    
    @Override
    public String toString() {
        return nombreCategoriaProveedor;
    }
}

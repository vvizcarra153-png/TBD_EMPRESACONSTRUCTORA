package modulo4b_inventario.models;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Modelo de Material para el inventario
 */
public class Material implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int idMaterial;
    private String nombreMaterial;
    private String unidadMedida;
    private int idCategoriaMaterial;
    private int idProveedor;
    private double precioUnitario;
    private LocalDateTime fechaActualizacion;
    
    public Material() {
    }
    
    public Material(String nombreMaterial, String unidadMedida, int idCategoriaMaterial, int idProveedor) {
        this.nombreMaterial = nombreMaterial;
        this.unidadMedida = unidadMedida;
        this.idCategoriaMaterial = idCategoriaMaterial;
        this.idProveedor = idProveedor;
        this.fechaActualizacion = LocalDateTime.now();
    }
    
    // Getters y Setters
    public int getIdMaterial() { return idMaterial; }
    public void setIdMaterial(int idMaterial) { this.idMaterial = idMaterial; }
    
    public String getNombreMaterial() { return nombreMaterial; }
    public void setNombreMaterial(String nombreMaterial) { this.nombreMaterial = nombreMaterial; }
    
    public String getUnidadMedida() { return unidadMedida; }
    public void setUnidadMedida(String unidadMedida) { this.unidadMedida = unidadMedida; }
    
    public int getIdCategoriaMaterial() { return idCategoriaMaterial; }
    public void setIdCategoriaMaterial(int idCategoriaMaterial) { this.idCategoriaMaterial = idCategoriaMaterial; }
    
    public int getIdProveedor() { return idProveedor; }
    public void setIdProveedor(int idProveedor) { this.idProveedor = idProveedor; }
    
    public double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(double precioUnitario) { this.precioUnitario = precioUnitario; }
    
    public LocalDateTime getFechaActualizacion() { return fechaActualizacion; }
    public void setFechaActualizacion(LocalDateTime fechaActualizacion) { this.fechaActualizacion = fechaActualizacion; }
    
    @Override
    public String toString() {
        return nombreMaterial + " (" + unidadMedida + ")";
    }
}

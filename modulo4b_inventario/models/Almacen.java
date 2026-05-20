package modulo4b_inventario.models;

import java.io.Serializable;

/**
 * Modelo de Almacén
 */
public class Almacen implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int idAlmacen;
    private String nombreAlmacen;
    private String ubicacionAlmacen;
    
    public Almacen() {
    }
    
    public Almacen(String nombreAlmacen, String ubicacionAlmacen) {
        this.nombreAlmacen = nombreAlmacen;
        this.ubicacionAlmacen = ubicacionAlmacen;
    }
    
    // Getters y Setters
    public int getIdAlmacen() { return idAlmacen; }
    public void setIdAlmacen(int idAlmacen) { this.idAlmacen = idAlmacen; }
    
    public String getNombreAlmacen() { return nombreAlmacen; }
    public void setNombreAlmacen(String nombreAlmacen) { this.nombreAlmacen = nombreAlmacen; }
    
    public String getUbicacionAlmacen() { return ubicacionAlmacen; }
    public void setUbicacionAlmacen(String ubicacionAlmacen) { this.ubicacionAlmacen = ubicacionAlmacen; }
    
    @Override
    public String toString() {
        return nombreAlmacen + " - " + ubicacionAlmacen;
    }
}

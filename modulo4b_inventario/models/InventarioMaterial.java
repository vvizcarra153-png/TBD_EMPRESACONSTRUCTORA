package modulo4b_inventario.models;

import java.io.Serializable;

/**
 * Modelo de Inventario de Material
 */
public class InventarioMaterial implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int idInventario;
    private int idMaterial;
    private int idAlmacen;
    private double stockActualMaterial;
    private double stockMinimoMaterial;
    
    public InventarioMaterial() {
    }
    
    public InventarioMaterial(int idMaterial, int idAlmacen, double stockActualMaterial, double stockMinimoMaterial) {
        this.idMaterial = idMaterial;
        this.idAlmacen = idAlmacen;
        this.stockActualMaterial = stockActualMaterial;
        this.stockMinimoMaterial = stockMinimoMaterial;
    }
    
    // Getters y Setters
    public int getIdInventario() { return idInventario; }
    public void setIdInventario(int idInventario) { this.idInventario = idInventario; }
    
    public int getIdMaterial() { return idMaterial; }
    public void setIdMaterial(int idMaterial) { this.idMaterial = idMaterial; }
    
    public int getIdAlmacen() { return idAlmacen; }
    public void setIdAlmacen(int idAlmacen) { this.idAlmacen = idAlmacen; }
    
    public double getStockActualMaterial() { return stockActualMaterial; }
    public void setStockActualMaterial(double stockActualMaterial) { this.stockActualMaterial = stockActualMaterial; }
    
    public double getStockMinimoMaterial() { return stockMinimoMaterial; }
    public void setStockMinimoMaterial(double stockMinimoMaterial) { this.stockMinimoMaterial = stockMinimoMaterial; }
    
    public boolean estaAlertar() {
        return stockActualMaterial <= stockMinimoMaterial;
    }
}

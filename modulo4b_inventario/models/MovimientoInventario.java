package modulo4b_inventario.models;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Modelo de Movimiento de Inventario
 */
public class MovimientoInventario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int idMovimiento;
    private int idMaterial;
    private String tipoMovimiento; // ENTRADA, SALIDA
    private double cantidad;
    private LocalDateTime fecha;
    private int idProyecto;
    
    public MovimientoInventario() {
        this.fecha = LocalDateTime.now();
    }
    
    public MovimientoInventario(int idMaterial, String tipoMovimiento, double cantidad, int idProyecto) {
        this.idMaterial = idMaterial;
        this.tipoMovimiento = tipoMovimiento;
        this.cantidad = cantidad;
        this.idProyecto = idProyecto;
        this.fecha = LocalDateTime.now();
    }
    
    // Getters y Setters
    public int getIdMovimiento() { return idMovimiento; }
    public void setIdMovimiento(int idMovimiento) { this.idMovimiento = idMovimiento; }
    
    public int getIdMaterial() { return idMaterial; }
    public void setIdMaterial(int idMaterial) { this.idMaterial = idMaterial; }
    
    public String getTipoMovimiento() { return tipoMovimiento; }
    public void setTipoMovimiento(String tipoMovimiento) { this.tipoMovimiento = tipoMovimiento; }
    
    public double getCantidad() { return cantidad; }
    public void setCantidad(double cantidad) { this.cantidad = cantidad; }
    
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
    
    public int getIdProyecto() { return idProyecto; }
    public void setIdProyecto(int idProyecto) { this.idProyecto = idProyecto; }
}

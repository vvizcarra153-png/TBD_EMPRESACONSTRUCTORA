package modulo4c_proveedores.models;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Modelo de Pago a Proveedor
 */
public class PagoProveedor implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int idPagoProveedor;
    private int idProveedor;
    private int idProyecto;
    private int idEstadoPago;
    private double monto;
    private LocalDate fechaPago;
    
    public PagoProveedor() {
    }
    
    public PagoProveedor(int idProveedor, int idProyecto, int idEstadoPago, double monto) {
        this.idProveedor = idProveedor;
        this.idProyecto = idProyecto;
        this.idEstadoPago = idEstadoPago;
        this.monto = monto;
        this.fechaPago = LocalDate.now();
    }
    
    public int getIdPagoProveedor() { return idPagoProveedor; }
    public void setIdPagoProveedor(int idPagoProveedor) { this.idPagoProveedor = idPagoProveedor; }
    
    public int getIdProveedor() { return idProveedor; }
    public void setIdProveedor(int idProveedor) { this.idProveedor = idProveedor; }
    
    public int getIdProyecto() { return idProyecto; }
    public void setIdProyecto(int idProyecto) { this.idProyecto = idProyecto; }
    
    public int getIdEstadoPago() { return idEstadoPago; }
    public void setIdEstadoPago(int idEstadoPago) { this.idEstadoPago = idEstadoPago; }
    
    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }
    
    public LocalDate getFechaPago() { return fechaPago; }
    public void setFechaPago(LocalDate fechaPago) { this.fechaPago = fechaPago; }
}

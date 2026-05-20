package modulo4d_subcontratistas.models;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Modelo de Pago a Subcontratista
 */
public class PagoSubcontratista implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int idPagoSub;
    private int idContratoSub;
    private double monto;
    private LocalDate fechaPago;
    private String estadoPago;
    
    public PagoSubcontratista() {
    }
    
    public PagoSubcontratista(int idContratoSub, double monto, LocalDate fechaPago) {
        this.idContratoSub = idContratoSub;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.estadoPago = "PENDIENTE";
    }
    
    public int getIdPagoSub() { return idPagoSub; }
    public void setIdPagoSub(int idPagoSub) { this.idPagoSub = idPagoSub; }
    
    public int getIdContratoSub() { return idContratoSub; }
    public void setIdContratoSub(int idContratoSub) { this.idContratoSub = idContratoSub; }
    
    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }
    
    public LocalDate getFechaPago() { return fechaPago; }
    public void setFechaPago(LocalDate fechaPago) { this.fechaPago = fechaPago; }
    
    public String getEstadoPago() { return estadoPago; }
    public void setEstadoPago(String estadoPago) { this.estadoPago = estadoPago; }
}

package modulo4d_subcontratistas.models;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Modelo de Contrato con Subcontratista
 */
public class ContratoSubcontratista implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int idContratoSub;
    private int idSubcontratista;
    private int idProyecto;
    private String descripcionTrabajo;
    private double montoContratado;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estadoContrato;
    
    public ContratoSubcontratista() {
    }
    
    public ContratoSubcontratista(int idSubcontratista, int idProyecto, String descripcionTrabajo,
                                 double montoContratado, LocalDate fechaInicio, LocalDate fechaFin) {
        this.idSubcontratista = idSubcontratista;
        this.idProyecto = idProyecto;
        this.descripcionTrabajo = descripcionTrabajo;
        this.montoContratado = montoContratado;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estadoContrato = "ACTIVO";
    }
    
    public int getIdContratoSub() { return idContratoSub; }
    public void setIdContratoSub(int idContratoSub) { this.idContratoSub = idContratoSub; }
    
    public int getIdSubcontratista() { return idSubcontratista; }
    public void setIdSubcontratista(int idSubcontratista) { this.idSubcontratista = idSubcontratista; }
    
    public int getIdProyecto() { return idProyecto; }
    public void setIdProyecto(int idProyecto) { this.idProyecto = idProyecto; }
    
    public String getDescripcionTrabajo() { return descripcionTrabajo; }
    public void setDescripcionTrabajo(String descripcionTrabajo) { this.descripcionTrabajo = descripcionTrabajo; }
    
    public double getMontoContratado() { return montoContratado; }
    public void setMontoContratado(double montoContratado) { this.montoContratado = montoContratado; }
    
    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }
    
    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }
    
    public String getEstadoContrato() { return estadoContrato; }
    public void setEstadoContrato(String estadoContrato) { this.estadoContrato = estadoContrato; }
}

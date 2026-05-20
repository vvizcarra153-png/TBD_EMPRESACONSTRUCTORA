/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo1_seguridad.models;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Modelo de Auditoría para registrar operaciones
 * Integrante: 1 - Seguridad y Usuarios
 */
public class Auditoria implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int idAuditoria;
    private String tablaAfectada;
    private String operacion; // INSERT, UPDATE, DELETE
    private String usuario;
    private LocalDateTime fecha;
    private String datosAnteriores;
    private String datosNuevos;
    
    /**
     * Constructor vacío
     */
    public Auditoria() {
    }
    
    /**
     * Constructor completo
     */
    public Auditoria(int idAuditoria, String tablaAfectada, String operacion,
                     String usuario, LocalDateTime fecha, String datosAnteriores,
                     String datosNuevos) {
        this.idAuditoria = idAuditoria;
        this.tablaAfectada = tablaAfectada;
        this.operacion = operacion;
        this.usuario = usuario;
        this.fecha = fecha;
        this.datosAnteriores = datosAnteriores;
        this.datosNuevos = datosNuevos;
    }
    
    // ===== GETTERS Y SETTERS =====
    public int getIdAuditoria() {
        return idAuditoria;
    }
    
    public void setIdAuditoria(int idAuditoria) {
        this.idAuditoria = idAuditoria;
    }
    
    public String getTablaAfectada() {
        return tablaAfectada;
    }
    
    public void setTablaAfectada(String tablaAfectada) {
        this.tablaAfectada = tablaAfectada;
    }
    
    public String getOperacion() {
        return operacion;
    }
    
    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }
    
    public String getUsuario() {
        return usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public LocalDateTime getFecha() {
        return fecha;
    }
    
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    
    public String getDatosAnteriores() {
        return datosAnteriores;
    }
    
    public void setDatosAnteriores(String datosAnteriores) {
        this.datosAnteriores = datosAnteriores;
    }
    
    public String getDatosNuevos() {
        return datosNuevos;
    }
    
    public void setDatosNuevos(String datosNuevos) {
        this.datosNuevos = datosNuevos;
    }
    
    @Override
    public String toString() {
        return "Auditoria{" +
                "operacion='" + operacion + '\'' +
                ", tabla='" + tablaAfectada + '\'' +
                ", usuario='" + usuario + '\'' +
                ", fecha=" + fecha +
                '}';
    }
}

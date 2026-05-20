/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo4a_proyectos.models;

import java.io.Serializable;
import java.time.LocalDate;

public class FaseProyecto implements Serializable {

    private int idFase;
    private int idProyecto;
    private String nombreFase;
    private String descripcionFase;
    private int orden;
    private LocalDate fechaInicioFase;
    private LocalDate fechaFinFase;
    private double progreso;
    private String estado;
    private double porcentajeAsignado;
    private double costoEstimado;
    private double costoReal;

    public FaseProyecto() {
    }

    public FaseProyecto(
            int idFase,
            int idProyecto,
            String nombreFase,
            String descripcionFase,
            int orden,
            LocalDate fechaInicioFase,
            LocalDate fechaFinFase,
            double progreso,
            String estado,
            double porcentajeAsignado,
            double costoEstimado,
            double costoReal
    ) {

        this.idFase = idFase;
        this.idProyecto = idProyecto;
        this.nombreFase = nombreFase;
        this.descripcionFase = descripcionFase;
        this.orden = orden;
        this.fechaInicioFase = fechaInicioFase;
        this.fechaFinFase = fechaFinFase;
        this.progreso = progreso;
        this.estado = estado;
        this.porcentajeAsignado = porcentajeAsignado;
        this.costoEstimado = costoEstimado;
        this.costoReal = costoReal;
    }

    public int getIdFase() {
        return idFase;
    }

    public void setIdFase(int idFase) {
        this.idFase = idFase;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombreFase() {
        return nombreFase;
    }

    public void setNombreFase(String nombreFase) {
        this.nombreFase = nombreFase;
    }

    public String getDescripcionFase() {
        return descripcionFase;
    }

    public void setDescripcionFase(String descripcionFase) {
        this.descripcionFase = descripcionFase;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public LocalDate getFechaInicioFase() {
        return fechaInicioFase;
    }

    public void setFechaInicioFase(LocalDate fechaInicioFase) {
        this.fechaInicioFase = fechaInicioFase;
    }

    public LocalDate getFechaFinFase() {
        return fechaFinFase;
    }

    public void setFechaFinFase(LocalDate fechaFinFase) {
        this.fechaFinFase = fechaFinFase;
    }

    public double getProgreso() {
        return progreso;
    }

    public void setProgreso(double progreso) {
        this.progreso = progreso;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getPorcentajeAsignado() {
        return porcentajeAsignado;
    }

    public void setPorcentajeAsignado(double porcentajeAsignado) {
        this.porcentajeAsignado = porcentajeAsignado;
    }

    public double getCostoEstimado() {
        return costoEstimado;
    }

    public void setCostoEstimado(double costoEstimado) {
        this.costoEstimado = costoEstimado;
    }

    public double getCostoReal() {
        return costoReal;
    }

    public void setCostoReal(double costoReal) {
        this.costoReal = costoReal;
    }
}
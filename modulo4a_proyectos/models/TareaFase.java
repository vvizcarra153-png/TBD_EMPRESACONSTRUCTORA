/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo4a_proyectos.models;

import java.io.Serializable;
import java.time.LocalDate;


public class TareaFase implements Serializable {

    private int idTarea;
    private int idFase;
    private int idEmpleadoResponsable;
    private String nombreTarea;
    private String descripcionTarea;
    private LocalDate fechaInicioTarea;
    private LocalDate fechaFinTarea;
    private String prioridadTarea;
    private String estado;
    private double porcentajeAvance;

    public TareaFase() {
    }

    public TareaFase(
            int idTarea,
            int idFase,
            int idEmpleadoResponsable,
            String nombreTarea,
            String descripcionTarea,
            LocalDate fechaInicioTarea,
            LocalDate fechaFinTarea,
            String prioridadTarea,
            String estado,
            double porcentajeAvance
    ) {

        this.idTarea = idTarea;
        this.idFase = idFase;
        this.idEmpleadoResponsable = idEmpleadoResponsable;
        this.nombreTarea = nombreTarea;
        this.descripcionTarea = descripcionTarea;
        this.fechaInicioTarea = fechaInicioTarea;
        this.fechaFinTarea = fechaFinTarea;
        this.prioridadTarea = prioridadTarea;
        this.estado = estado;
        this.porcentajeAvance = porcentajeAvance;
    }

    // GETTERS Y SETTERS

    public int getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }

    public int getIdFase() {
        return idFase;
    }

    public void setIdFase(int idFase) {
        this.idFase = idFase;
    }

    public int getIdEmpleadoResponsable() {
        return idEmpleadoResponsable;
    }

    public void setIdEmpleadoResponsable(int idEmpleadoResponsable) {
        this.idEmpleadoResponsable = idEmpleadoResponsable;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public String getDescripcionTarea() {
        return descripcionTarea;
    }

    public void setDescripcionTarea(String descripcionTarea) {
        this.descripcionTarea = descripcionTarea;
    }

    public LocalDate getFechaInicioTarea() {
        return fechaInicioTarea;
    }

    public void setFechaInicioTarea(LocalDate fechaInicioTarea) {
        this.fechaInicioTarea = fechaInicioTarea;
    }

    public LocalDate getFechaFinTarea() {
        return fechaFinTarea;
    }

    public void setFechaFinTarea(LocalDate fechaFinTarea) {
        this.fechaFinTarea = fechaFinTarea;
    }

    public String getPrioridadTarea() {
        return prioridadTarea;
    }

    public void setPrioridadTarea(String prioridadTarea) {
        this.prioridadTarea = prioridadTarea;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getPorcentajeAvance() {
        return porcentajeAvance;
    }

    public void setPorcentajeAvance(double porcentajeAvance) {
        this.porcentajeAvance = porcentajeAvance;
    }
}
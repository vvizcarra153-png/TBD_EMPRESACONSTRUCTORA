/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo4a_proyectos.models;

import java.io.Serializable;
import java.time.LocalDate;

public class AvanceProyecto implements Serializable {

    private int idAvance;
    private int idProyecto;
    private LocalDate fecha;
    private double porcentajeAvance;
    private String observaciones;

    public AvanceProyecto() {
    }

    public AvanceProyecto(
            int idAvance,
            int idProyecto,
            LocalDate fecha,
            double porcentajeAvance,
            String observaciones
    ) {

        this.idAvance = idAvance;
        this.idProyecto = idProyecto;
        this.fecha = fecha;
        this.porcentajeAvance = porcentajeAvance;
        this.observaciones = observaciones;
    }

    public int getIdAvance() {
        return idAvance;
    }

    public void setIdAvance(int idAvance) {
        this.idAvance = idAvance;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getPorcentajeAvance() {
        return porcentajeAvance;
    }

    public void setPorcentajeAvance(double porcentajeAvance) {
        this.porcentajeAvance = porcentajeAvance;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo4a_proyectos.models;

import java.io.Serializable;
import java.time.LocalDate;

public class Proyecto implements Serializable {

    private int idProyecto;
    private int idCliente;
    private int idEstadoProyecto;
    private int idCentroCosto;
    private Integer idCotizacion;
    private String nombreProyecto;
    private String ubicacionProyecto;
    private LocalDate fechaInicioProyecto;
    private LocalDate fechaFinProyecto;
    private double costoRealProyecto;
    private String prioridadProyecto;

    public Proyecto() {
    }

    public Proyecto(
            int idProyecto,
            int idCliente,
            int idEstadoProyecto,
            int idCentroCosto,
            Integer idCotizacion,
            String nombreProyecto,
            String ubicacionProyecto,
            LocalDate fechaInicioProyecto,
            LocalDate fechaFinProyecto,
            double costoRealProyecto,
            String prioridadProyecto
    ) {

        this.idProyecto = idProyecto;
        this.idCliente = idCliente;
        this.idEstadoProyecto = idEstadoProyecto;
        this.idCentroCosto = idCentroCosto;
        this.idCotizacion = idCotizacion;
        this.nombreProyecto = nombreProyecto;
        this.ubicacionProyecto = ubicacionProyecto;
        this.fechaInicioProyecto = fechaInicioProyecto;
        this.fechaFinProyecto = fechaFinProyecto;
        this.costoRealProyecto = costoRealProyecto;
        this.prioridadProyecto = prioridadProyecto;
    }

    // GETTERS Y SETTERS

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdEstadoProyecto() {
        return idEstadoProyecto;
    }

    public void setIdEstadoProyecto(int idEstadoProyecto) {
        this.idEstadoProyecto = idEstadoProyecto;
    }

    public int getIdCentroCosto() {
        return idCentroCosto;
    }

    public void setIdCentroCosto(int idCentroCosto) {
        this.idCentroCosto = idCentroCosto;
    }

    public Integer getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(Integer idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getUbicacionProyecto() {
        return ubicacionProyecto;
    }

    public void setUbicacionProyecto(String ubicacionProyecto) {
        this.ubicacionProyecto = ubicacionProyecto;
    }

    public LocalDate getFechaInicioProyecto() {
        return fechaInicioProyecto;
    }

    public void setFechaInicioProyecto(LocalDate fechaInicioProyecto) {
        this.fechaInicioProyecto = fechaInicioProyecto;
    }

    public LocalDate getFechaFinProyecto() {
        return fechaFinProyecto;
    }

    public void setFechaFinProyecto(LocalDate fechaFinProyecto) {
        this.fechaFinProyecto = fechaFinProyecto;
    }

    public double getCostoRealProyecto() {
        return costoRealProyecto;
    }

    public void setCostoRealProyecto(double costoRealProyecto) {
        this.costoRealProyecto = costoRealProyecto;
    }

    public String getPrioridadProyecto() {
        return prioridadProyecto;
    }

    public void setPrioridadProyecto(String prioridadProyecto) {
        this.prioridadProyecto = prioridadProyecto;
    }
}
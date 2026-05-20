/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo3_finanzas.models;

import java.io.Serializable;
import java.time.LocalDate;

public class Cotizacion implements Serializable {

    private int idCotizacion;
    private int idClienteCotizacion;
    private double costosMateriales;
    private double costoManoObra;
    private double otrosCostos;
    private double metrosCuadrados;
    private int numeroPisos;
    private String ubicacionObra;
    private int tiempoEstimado;
    private double margenGanancia;
    private double precioFinal;
    private LocalDate fechaCotizacion;

    public Cotizacion() {
    }

    public Cotizacion(
            int idCotizacion,
            int idClienteCotizacion,
            double costosMateriales,
            double costoManoObra,
            double otrosCostos,
            double metrosCuadrados,
            int numeroPisos,
            String ubicacionObra,
            int tiempoEstimado,
            double margenGanancia,
            double precioFinal,
            LocalDate fechaCotizacion
    ) {

        this.idCotizacion = idCotizacion;
        this.idClienteCotizacion = idClienteCotizacion;
        this.costosMateriales = costosMateriales;
        this.costoManoObra = costoManoObra;
        this.otrosCostos = otrosCostos;
        this.metrosCuadrados = metrosCuadrados;
        this.numeroPisos = numeroPisos;
        this.ubicacionObra = ubicacionObra;
        this.tiempoEstimado = tiempoEstimado;
        this.margenGanancia = margenGanancia;
        this.precioFinal = precioFinal;
        this.fechaCotizacion = fechaCotizacion;
    }

    public int getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(int idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    public int getIdClienteCotizacion() {
        return idClienteCotizacion;
    }

    public void setIdClienteCotizacion(int idClienteCotizacion) {
        this.idClienteCotizacion = idClienteCotizacion;
    }

    public double getCostosMateriales() {
        return costosMateriales;
    }

    public void setCostosMateriales(double costosMateriales) {
        this.costosMateriales = costosMateriales;
    }

    public double getCostoManoObra() {
        return costoManoObra;
    }

    public void setCostoManoObra(double costoManoObra) {
        this.costoManoObra = costoManoObra;
    }

    public double getOtrosCostos() {
        return otrosCostos;
    }

    public void setOtrosCostos(double otrosCostos) {
        this.otrosCostos = otrosCostos;
    }

    public double getMetrosCuadrados() {
        return metrosCuadrados;
    }

    public void setMetrosCuadrados(double metrosCuadrados) {
        this.metrosCuadrados = metrosCuadrados;
    }

    public int getNumeroPisos() {
        return numeroPisos;
    }

    public void setNumeroPisos(int numeroPisos) {
        this.numeroPisos = numeroPisos;
    }

    public String getUbicacionObra() {
        return ubicacionObra;
    }

    public void setUbicacionObra(String ubicacionObra) {
        this.ubicacionObra = ubicacionObra;
    }

    public int getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setTiempoEstimado(int tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }

    public double getMargenGanancia() {
        return margenGanancia;
    }

    public void setMargenGanancia(double margenGanancia) {
        this.margenGanancia = margenGanancia;
    }

    public double getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(double precioFinal) {
        this.precioFinal = precioFinal;
    }

    public LocalDate getFechaCotizacion() {
        return fechaCotizacion;
    }

    public void setFechaCotizacion(LocalDate fechaCotizacion) {
        this.fechaCotizacion = fechaCotizacion;
    }
}
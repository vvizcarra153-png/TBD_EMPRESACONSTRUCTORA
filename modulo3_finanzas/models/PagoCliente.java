/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo3_finanzas.models;

import java.io.Serializable;
import java.time.LocalDate;

public class PagoCliente implements Serializable {

    private int idPagoCliente;
    private int idCliente;
    private int idProyecto;
    private int idEstadoPago;
    private double monto;
    private LocalDate fechaPago;
    private String metodoPago;

    public PagoCliente() {
    }

    public PagoCliente(
            int idPagoCliente,
            int idCliente,
            int idProyecto,
            int idEstadoPago,
            double monto,
            LocalDate fechaPago,
            String metodoPago
    ) {

        this.idPagoCliente = idPagoCliente;
        this.idCliente = idCliente;
        this.idProyecto = idProyecto;
        this.idEstadoPago = idEstadoPago;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.metodoPago = metodoPago;
    }

    public int getIdPagoCliente() {
        return idPagoCliente;
    }

    public void setIdPagoCliente(int idPagoCliente) {
        this.idPagoCliente = idPagoCliente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public int getIdEstadoPago() {
        return idEstadoPago;
    }

    public void setIdEstadoPago(int idEstadoPago) {
        this.idEstadoPago = idEstadoPago;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
}
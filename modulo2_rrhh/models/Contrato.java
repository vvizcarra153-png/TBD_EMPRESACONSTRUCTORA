/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo2_rrhh.models;

import java.io.Serializable;
import java.time.LocalDate;

public class Contrato implements Serializable {
    private int idContrato;
    private int idEmpleado;
    private int idTipoContrato;
    private int idTipoPago;
    private double tarifa;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado;

    public Contrato() {}

    public Contrato(int idContrato, int idEmpleado, int idTipoContrato, int idTipoPago, double tarifa, LocalDate fechaInicio, LocalDate fechaFin, String estado) {
        this.idContrato = idContrato;
        this.idEmpleado = idEmpleado;
        this.idTipoContrato = idTipoContrato;
        this.idTipoPago = idTipoPago;
        this.tarifa = tarifa;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
    }

    // Getters y setters...
}
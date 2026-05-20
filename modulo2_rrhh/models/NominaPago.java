/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo2_rrhh.models;

import java.io.Serializable;
import java.time.LocalDate;

public class NominaPago implements Serializable {
    private int idNomina;
    private int idEmpleado;
    private int idPeriodoPago;
    private LocalDate fechaPago;
    private LocalDate periodoInicio;
    private LocalDate periodoFin;
    private int diasTrabajados;
    private double horasTrabajadas;
    private double horasExtra;
    private double montoPago;

    public NominaPago() {}

    public NominaPago(int idNomina, int idEmpleado, int idPeriodoPago, LocalDate fechaPago, LocalDate periodoInicio,
                      LocalDate periodoFin, int diasTrabajados, double horasTrabajadas, double horasExtra, double montoPago) {
        this.idNomina = idNomina;
        this.idEmpleado = idEmpleado;
        this.idPeriodoPago = idPeriodoPago;
        this.fechaPago = fechaPago;
        this.periodoInicio = periodoInicio;
        this.periodoFin = periodoFin;
        this.diasTrabajados = diasTrabajados;
        this.horasTrabajadas = horasTrabajadas;
        this.horasExtra = horasExtra;
        this.montoPago = montoPago;
    }

    // Getters y setters...
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo2_rrhh.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Asistencia implements Serializable {
    private int idAsistencia;
    private int idEmpleado;
    private LocalDate fecha;
    private LocalTime horaEntrada;
    private LocalTime horaSalida;
    private double horasTrabajadas;
    private double horasExtra;
    private String estado;

    public Asistencia() {}

    public Asistencia(int idAsistencia, int idEmpleado, LocalDate fecha, LocalTime horaEntrada, LocalTime horaSalida,
                      double horasTrabajadas, double horasExtra, String estado) {
        this.idAsistencia = idAsistencia;
        this.idEmpleado = idEmpleado;
        this.fecha = fecha;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.horasTrabajadas = horasTrabajadas;
        this.horasExtra = horasExtra;
        this.estado = estado;
    }

    // Getters y setters...
}

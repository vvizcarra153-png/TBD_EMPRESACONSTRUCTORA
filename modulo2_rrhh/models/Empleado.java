/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo2_rrhh.models;

import java.io.Serializable;
import java.time.LocalDate;

public class Empleado implements Serializable {

    private int idEmpleado;
    private String nombreEmpleado;
    private String apellidoEmpleado;
    private String sexo;
    private String telefonoEmpleado;
    private String emailEmpleado;
    private String ciEmpleado;
    private String direccionEmpleado;
    private LocalDate fechaNacimientoEmpleado;
    private int idCategoriaEmpleado;
    private LocalDate fechaIngresoEmpleado;

    public Empleado() {
    }

    public Empleado(int idEmpleado, String nombreEmpleado, String apellidoEmpleado, String sexo,
                    String telefonoEmpleado, String emailEmpleado, String ciEmpleado,
                    String direccionEmpleado, LocalDate fechaNacimientoEmpleado,
                    int idCategoriaEmpleado, LocalDate fechaIngresoEmpleado) {

        this.idEmpleado = idEmpleado;
        this.nombreEmpleado = nombreEmpleado;
        this.apellidoEmpleado = apellidoEmpleado;
        this.sexo = sexo;
        this.telefonoEmpleado = telefonoEmpleado;
        this.emailEmpleado = emailEmpleado;
        this.ciEmpleado = ciEmpleado;
        this.direccionEmpleado = direccionEmpleado;
        this.fechaNacimientoEmpleado = fechaNacimientoEmpleado;
        this.idCategoriaEmpleado = idCategoriaEmpleado;
        this.fechaIngresoEmpleado = fechaIngresoEmpleado;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getApellidoEmpleado() {
        return apellidoEmpleado;
    }

    public void setApellidoEmpleado(String apellidoEmpleado) {
        this.apellidoEmpleado = apellidoEmpleado;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefonoEmpleado() {
        return telefonoEmpleado;
    }

    public void setTelefonoEmpleado(String telefonoEmpleado) {
        this.telefonoEmpleado = telefonoEmpleado;
    }

    public String getEmailEmpleado() {
        return emailEmpleado;
    }

    public void setEmailEmpleado(String emailEmpleado) {
        this.emailEmpleado = emailEmpleado;
    }

    public String getCiEmpleado() {
        return ciEmpleado;
    }

    public void setCiEmpleado(String ciEmpleado) {
        this.ciEmpleado = ciEmpleado;
    }

    public String getDireccionEmpleado() {
        return direccionEmpleado;
    }

    public void setDireccionEmpleado(String direccionEmpleado) {
        this.direccionEmpleado = direccionEmpleado;
    }

    public LocalDate getFechaNacimientoEmpleado() {
        return fechaNacimientoEmpleado;
    }

    public void setFechaNacimientoEmpleado(LocalDate fechaNacimientoEmpleado) {
        this.fechaNacimientoEmpleado = fechaNacimientoEmpleado;
    }

    public int getIdCategoriaEmpleado() {
        return idCategoriaEmpleado;
    }

    public void setIdCategoriaEmpleado(int idCategoriaEmpleado) {
        this.idCategoriaEmpleado = idCategoriaEmpleado;
    }

    public LocalDate getFechaIngresoEmpleado() {
        return fechaIngresoEmpleado;
    }

    public void setFechaIngresoEmpleado(LocalDate fechaIngresoEmpleado) {
        this.fechaIngresoEmpleado = fechaIngresoEmpleado;
    }
}
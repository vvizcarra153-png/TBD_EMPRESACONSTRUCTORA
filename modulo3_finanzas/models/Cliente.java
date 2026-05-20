/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo3_finanzas.models;

import java.io.Serializable;
import java.time.LocalDate;

public class Cliente implements Serializable {

    private int idCliente;
    private String nombreCliente;
    private String telefonoCliente;
    private String emailCliente;
    private String direccionCliente;
    private int idTipoDocumento;
    private String nroDocumentoCliente;
    private LocalDate fechaRegistroCliente;

    public Cliente() {
    }

    public Cliente(
            int idCliente,
            String nombreCliente,
            String telefonoCliente,
            String emailCliente,
            String direccionCliente,
            int idTipoDocumento,
            String nroDocumentoCliente,
            LocalDate fechaRegistroCliente
    ) {

        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.telefonoCliente = telefonoCliente;
        this.emailCliente = emailCliente;
        this.direccionCliente = direccionCliente;
        this.idTipoDocumento = idTipoDocumento;
        this.nroDocumentoCliente = nroDocumentoCliente;
        this.fechaRegistroCliente = fechaRegistroCliente;
    }

    // GETTERS Y SETTERS

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public int getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(int idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getNroDocumentoCliente() {
        return nroDocumentoCliente;
    }

    public void setNroDocumentoCliente(String nroDocumentoCliente) {
        this.nroDocumentoCliente = nroDocumentoCliente;
    }

    public LocalDate getFechaRegistroCliente() {
        return fechaRegistroCliente;
    }

    public void setFechaRegistroCliente(LocalDate fechaRegistroCliente) {
        this.fechaRegistroCliente = fechaRegistroCliente;
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo2_rrhh.models;

import java.io.Serializable;

public class CategoriaEmpleado implements Serializable {
    private int idCategoriaEmpleado;
    private String nombreCategoriaEmpleado;
    private String descripcion;

    public CategoriaEmpleado() {}

    public CategoriaEmpleado(int idCategoriaEmpleado, String nombreCategoriaEmpleado, String descripcion) {
        this.idCategoriaEmpleado = idCategoriaEmpleado;
        this.nombreCategoriaEmpleado = nombreCategoriaEmpleado;
        this.descripcion = descripcion;
    }

    // Getters y setters...
}
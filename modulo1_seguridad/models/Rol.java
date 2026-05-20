/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo1_seguridad.models;

import java.io.Serializable;

/**
 * Modelo de Rol para autorización
 * Integrante: 1 - Seguridad y Usuarios
 */
public class Rol implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int idRol;
    private String nombreRol;
    
    /**
     * Constructor vacío
     */
    public Rol() {
    }
    
    /**
     * Constructor completo
     */
    public Rol(int idRol, String nombreRol) {
        this.idRol = idRol;
        this.nombreRol = nombreRol;
    }
    
    // ===== GETTERS Y SETTERS =====
    public int getIdRol() {
        return idRol;
    }
    
    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
    
    public String getNombreRol() {
        return nombreRol;
    }
    
    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
    
    @Override
    public String toString() {
        return nombreRol;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Rol rol = (Rol) obj;
        return idRol == rol.idRol;
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(idRol);
    }
}

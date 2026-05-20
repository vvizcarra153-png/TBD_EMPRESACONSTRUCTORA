/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo1_seguridad.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Modelo de Usuario para autenticación
 * Integrante: 1 - Seguridad y Usuarios
 */
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int idUsuario;
    private String username;
    private String passwordHash;
    private String estado;
    private int idEmpleado;
    private List<Rol> roles;
    
    /**
     * Constructor vacío
     */
    public Usuario() {
        this.roles = new ArrayList<>();
    }
    
    /**
     * Constructor completo
     */
    public Usuario(int idUsuario, String username, String passwordHash, String estado, int idEmpleado) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.passwordHash = passwordHash;
        this.estado = estado;
        this.idEmpleado = idEmpleado;
        this.roles = new ArrayList<>();
    }
    
    // ===== GETTERS Y SETTERS =====
    public int getIdUsuario() {
        return idUsuario;
    }
    
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPasswordHash() {
        return passwordHash;
    }
    
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public int getIdEmpleado() {
        return idEmpleado;
    }
    
    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    
    public List<Rol> getRoles() {
        return roles;
    }
    
    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }
    
    public void agregarRol(Rol rol) {
        if (!roles.contains(rol)) {
            roles.add(rol);
        }
    }
    
    public void removerRol(Rol rol) {
        roles.remove(rol);
    }
    
    public boolean estaActivo() {
        return "activo".equalsIgnoreCase(estado);
    }
    
    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", username='" + username + '\'' +
                ", estado='" + estado + '\'' +
                ", roles=" + roles.size() +
                '}';
    }
}

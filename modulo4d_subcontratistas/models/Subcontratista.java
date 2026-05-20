package modulo4d_subcontratistas.models;

import java.io.Serializable;

/**
 * Modelo de Subcontratista
 */
public class Subcontratista implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int idSubcontratista;
    private String nombreSubcontratista;
    private String representante;
    private String ciSubcontratista;
    private String telefonoSubcontratista;
    private String emailSubcontratista;
    private String direccionSubcontratista;
    private String especialidad;
    
    public Subcontratista() {
    }
    
    public Subcontratista(String nombreSubcontratista, String representante, String ciSubcontratista,
                         String telefonoSubcontratista, String emailSubcontratista, 
                         String direccionSubcontratista, String especialidad) {
        this.nombreSubcontratista = nombreSubcontratista;
        this.representante = representante;
        this.ciSubcontratista = ciSubcontratista;
        this.telefonoSubcontratista = telefonoSubcontratista;
        this.emailSubcontratista = emailSubcontratista;
        this.direccionSubcontratista = direccionSubcontratista;
        this.especialidad = especialidad;
    }
    
    public int getIdSubcontratista() { return idSubcontratista; }
    public void setIdSubcontratista(int idSubcontratista) { this.idSubcontratista = idSubcontratista; }
    
    public String getNombreSubcontratista() { return nombreSubcontratista; }
    public void setNombreSubcontratista(String nombreSubcontratista) { this.nombreSubcontratista = nombreSubcontratista; }
    
    public String getRepresentante() { return representante; }
    public void setRepresentante(String representante) { this.representante = representante; }
    
    public String getCiSubcontratista() { return ciSubcontratista; }
    public void setCiSubcontratista(String ciSubcontratista) { this.ciSubcontratista = ciSubcontratista; }
    
    public String getTelefonoSubcontratista() { return telefonoSubcontratista; }
    public void setTelefonoSubcontratista(String telefonoSubcontratista) { this.telefonoSubcontratista = telefonoSubcontratista; }
    
    public String getEmailSubcontratista() { return emailSubcontratista; }
    public void setEmailSubcontratista(String emailSubcontratista) { this.emailSubcontratista = emailSubcontratista; }
    
    public String getDireccionSubcontratista() { return direccionSubcontratista; }
    public void setDireccionSubcontratista(String direccionSubcontratista) { this.direccionSubcontratista = direccionSubcontratista; }
    
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    
    @Override
    public String toString() {
        return nombreSubcontratista + " (" + especialidad + ")";
    }
}

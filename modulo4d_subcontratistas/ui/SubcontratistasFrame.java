package modulo4d_subcontratistas.ui;

import modulo4d_subcontratistas.models.*;
import modulo4d_subcontratistas.dao.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Frame para gestión de Subcontratistas
 */
public class SubcontratistasFrame extends JFrame {
    private JTable tablaSubcontratistas;
    private DefaultTableModel modeloTabla;
    private SubcontratistDAO daoSubcontratista;
    
    public SubcontratistasFrame() {
        setTitle("Gestión de Subcontratistas");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        daoSubcontratista = new SubcontratistDAO();
        
        inicializarUI();
        cargarDatos();
    }
    
    private void inicializarUI() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnNuevo = new JButton("Nuevo Subcontratista");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");
        
        btnNuevo.addActionListener(e -> abrirNuevoSubcontratista());
        btnActualizar.addActionListener(e -> cargarDatos());
        btnEditar.addActionListener(e -> editarSubcontratista());
        btnEliminar.addActionListener(e -> eliminarSubcontratista());
        
        panelBotones.add(btnNuevo);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        
        // Tabla
        String[] columnas = {"ID", "Nombre", "Representante", "CI", "Teléfono", "Email", "Especialidad"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaSubcontratistas = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaSubcontratistas);
        
        panel.add(panelBotones, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        add(panel);
    }
    
    private void cargarDatos() {
        modeloTabla.setRowCount(0);
        List<Subcontratista> subcontratistas = daoSubcontratista.obtenerTodos();
        
        for (Subcontratista s : subcontratistas) {
            modeloTabla.addRow(new Object[]{
                s.getIdSubcontratista(),
                s.getNombreSubcontratista(),
                s.getRepresentante(),
                s.getCiSubcontratista(),
                s.getTelefonoSubcontratista(),
                s.getEmailSubcontratista(),
                s.getEspecialidad()
            });
        }
    }
    
    private void abrirNuevoSubcontratista() {
        JDialog dialog = new JDialog(this, "Nuevo Subcontratista", true);
        dialog.setSize(500, 350);
        dialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();
        
        JLabel lblRepresentante = new JLabel("Representante:");
        JTextField txtRepresentante = new JTextField();
        
        JLabel lblCI = new JLabel("CI:");
        JTextField txtCI = new JTextField();
        
        JLabel lblTelefono = new JLabel("Teléfono:");
        JTextField txtTelefono = new JTextField();
        
        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();
        
        JLabel lblDireccion = new JLabel("Dirección:");
        JTextField txtDireccion = new JTextField();
        
        JLabel lblEspecialidad = new JLabel("Especialidad:");
        JTextField txtEspecialidad = new JTextField();
        
        panel.add(lblNombre);
        panel.add(txtNombre);
        panel.add(lblRepresentante);
        panel.add(txtRepresentante);
        panel.add(lblCI);
        panel.add(txtCI);
        panel.add(lblTelefono);
        panel.add(txtTelefono);
        panel.add(lblEmail);
        panel.add(txtEmail);
        panel.add(lblDireccion);
        panel.add(txtDireccion);
        panel.add(lblEspecialidad);
        panel.add(txtEspecialidad);
        
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            Subcontratista s = new Subcontratista(
                txtNombre.getText(),
                txtRepresentante.getText(),
                txtCI.getText(),
                txtTelefono.getText(),
                txtEmail.getText(),
                txtDireccion.getText(),
                txtEspecialidad.getText()
            );
            daoSubcontratista.crear(s);
            cargarDatos();
            dialog.dispose();
            JOptionPane.showMessageDialog(this, "Subcontratista guardado correctamente");
        });
        
        panel.add(btnGuardar);
        dialog.add(panel);
        dialog.setVisible(true);
    }
    
    private void editarSubcontratista() {
        int fila = tablaSubcontratistas.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un subcontratista");
            return;
        }
        
        int id = (Integer) modeloTabla.getValueAt(fila, 0);
        Subcontratista s = daoSubcontratista.obtener(id);
        
        JDialog dialog = new JDialog(this, "Editar Subcontratista", true);
        dialog.setSize(500, 350);
        dialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField(s.getNombreSubcontratista());
        
        JLabel lblRepresentante = new JLabel("Representante:");
        JTextField txtRepresentante = new JTextField(s.getRepresentante());
        
        JLabel lblCI = new JLabel("CI:");
        JTextField txtCI = new JTextField(s.getCiSubcontratista());
        
        JLabel lblTelefono = new JLabel("Teléfono:");
        JTextField txtTelefono = new JTextField(s.getTelefonoSubcontratista());
        
        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField(s.getEmailSubcontratista());
        
        JLabel lblDireccion = new JLabel("Dirección:");
        JTextField txtDireccion = new JTextField(s.getDireccionSubcontratista());
        
        JLabel lblEspecialidad = new JLabel("Especialidad:");
        JTextField txtEspecialidad = new JTextField(s.getEspecialidad());
        
        panel.add(lblNombre);
        panel.add(txtNombre);
        panel.add(lblRepresentante);
        panel.add(txtRepresentante);
        panel.add(lblCI);
        panel.add(txtCI);
        panel.add(lblTelefono);
        panel.add(txtTelefono);
        panel.add(lblEmail);
        panel.add(txtEmail);
        panel.add(lblDireccion);
        panel.add(txtDireccion);
        panel.add(lblEspecialidad);
        panel.add(txtEspecialidad);
        
        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(e -> {
            s.setNombreSubcontratista(txtNombre.getText());
            s.setRepresentante(txtRepresentante.getText());
            s.setCiSubcontratista(txtCI.getText());
            s.setTelefonoSubcontratista(txtTelefono.getText());
            s.setEmailSubcontratista(txtEmail.getText());
            s.setDireccionSubcontratista(txtDireccion.getText());
            s.setEspecialidad(txtEspecialidad.getText());
            daoSubcontratista.actualizar(s);
            cargarDatos();
            dialog.dispose();
            JOptionPane.showMessageDialog(this, "Subcontratista actualizado");
        });
        
        panel.add(btnActualizar);
        dialog.add(panel);
        dialog.setVisible(true);
    }
    
    private void eliminarSubcontratista() {
        int fila = tablaSubcontratistas.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un subcontratista");
            return;
        }
        
        int id = (Integer) modeloTabla.getValueAt(fila, 0);
        daoSubcontratista.eliminar(id);
        cargarDatos();
        JOptionPane.showMessageDialog(this, "Subcontratista eliminado");
    }
}

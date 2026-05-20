package modulo4c_proveedores.ui;

import modulo4c_proveedores.models.*;
import modulo4c_proveedores.dao.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Frame para gestión de Proveedores
 */
public class ProveedorFrame extends JFrame {
    private JTable tablaProveedores;
    private DefaultTableModel modeloTabla;
    private ProveedorDAO daoProveedor;
    
    public ProveedorFrame() {
        setTitle("Gestión de Proveedores");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        daoProveedor = new ProveedorDAO();
        
        inicializarUI();
        cargarDatos();
    }
    
    private void inicializarUI() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnNuevo = new JButton("Nuevo Proveedor");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");
        
        btnNuevo.addActionListener(e -> abrirNuevoProveedor());
        btnActualizar.addActionListener(e -> cargarDatos());
        btnEditar.addActionListener(e -> editarProveedor());
        btnEliminar.addActionListener(e -> eliminarProveedor());
        
        panelBotones.add(btnNuevo);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        
        // Tabla
        String[] columnas = {"ID", "Nombre", "Teléfono", "Dirección", "Categoría"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaProveedores = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaProveedores);
        
        panel.add(panelBotones, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        add(panel);
    }
    
    private void cargarDatos() {
        modeloTabla.setRowCount(0);
        List<Proveedor> proveedores = daoProveedor.obtenerTodos();
        
        for (Proveedor p : proveedores) {
            modeloTabla.addRow(new Object[]{
                p.getIdProveedor(),
                p.getNombreProveedor(),
                p.getTelefonoProveedor(),
                p.getDireccionProveedor(),
                p.getIdCategoriaProveedor()
            });
        }
    }
    
    private void abrirNuevoProveedor() {
        JDialog dialog = new JDialog(this, "Nuevo Proveedor", true);
        dialog.setSize(400, 280);
        dialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();
        
        JLabel lblTelefono = new JLabel("Teléfono:");
        JTextField txtTelefono = new JTextField();
        
        JLabel lblDireccion = new JLabel("Dirección:");
        JTextField txtDireccion = new JTextField();
        
        JLabel lblCategoria = new JLabel("Categoría ID:");
        JTextField txtCategoria = new JTextField();
        
        panel.add(lblNombre);
        panel.add(txtNombre);
        panel.add(lblTelefono);
        panel.add(txtTelefono);
        panel.add(lblDireccion);
        panel.add(txtDireccion);
        panel.add(lblCategoria);
        panel.add(txtCategoria);
        
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            Proveedor p = new Proveedor(
                txtNombre.getText(),
                txtTelefono.getText(),
                txtDireccion.getText(),
                Integer.parseInt(txtCategoria.getText())
            );
            daoProveedor.crear(p);
            cargarDatos();
            dialog.dispose();
            JOptionPane.showMessageDialog(this, "Proveedor guardado correctamente");
        });
        
        panel.add(btnGuardar);
        dialog.add(panel);
        dialog.setVisible(true);
    }
    
    private void editarProveedor() {
        int fila = tablaProveedores.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un proveedor");
            return;
        }
        
        int id = (Integer) modeloTabla.getValueAt(fila, 0);
        Proveedor p = daoProveedor.obtener(id);
        
        JDialog dialog = new JDialog(this, "Editar Proveedor", true);
        dialog.setSize(400, 280);
        dialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField(p.getNombreProveedor());
        
        JLabel lblTelefono = new JLabel("Teléfono:");
        JTextField txtTelefono = new JTextField(p.getTelefonoProveedor());
        
        JLabel lblDireccion = new JLabel("Dirección:");
        JTextField txtDireccion = new JTextField(p.getDireccionProveedor());
        
        JLabel lblCategoria = new JLabel("Categoría ID:");
        JTextField txtCategoria = new JTextField(String.valueOf(p.getIdCategoriaProveedor()));
        
        panel.add(lblNombre);
        panel.add(txtNombre);
        panel.add(lblTelefono);
        panel.add(txtTelefono);
        panel.add(lblDireccion);
        panel.add(txtDireccion);
        panel.add(lblCategoria);
        panel.add(txtCategoria);
        
        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(e -> {
            p.setNombreProveedor(txtNombre.getText());
            p.setTelefonoProveedor(txtTelefono.getText());
            p.setDireccionProveedor(txtDireccion.getText());
            p.setIdCategoriaProveedor(Integer.parseInt(txtCategoria.getText()));
            daoProveedor.actualizar(p);
            cargarDatos();
            dialog.dispose();
            JOptionPane.showMessageDialog(this, "Proveedor actualizado");
        });
        
        panel.add(btnActualizar);
        dialog.add(panel);
        dialog.setVisible(true);
    }
    
    private void eliminarProveedor() {
        int fila = tablaProveedores.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un proveedor");
            return;
        }
        
        int id = (Integer) modeloTabla.getValueAt(fila, 0);
        daoProveedor.eliminar(id);
        cargarDatos();
        JOptionPane.showMessageDialog(this, "Proveedor eliminado");
    }
}

package modulo4b_inventario.ui;

import modulo4b_inventario.models.*;
import modulo4b_inventario.dao.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Frame para gestión de Movimientos de Inventario
 */
public class MovimientosFrame extends JFrame {
    private JTable tablaMovimientos;
    private DefaultTableModel modeloTabla;
    private MovimientoInventarioDAO daoMovimiento;
    private MaterialDAO daoMaterial;
    
    public MovimientosFrame() {
        setTitle("Movimientos de Inventario");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        daoMovimiento = new MovimientoInventarioDAO();
        daoMaterial = new MaterialDAO();
        
        inicializarUI();
        cargarDatos();
    }
    
    private void inicializarUI() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnNuevoMovimiento = new JButton("Nuevo Movimiento");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEntradas = new JButton("Ver Entradas");
        JButton btnSalidas = new JButton("Ver Salidas");
        
        btnNuevoMovimiento.addActionListener(e -> abrirNuevoMovimiento());
        btnActualizar.addActionListener(e -> cargarDatos());
        btnEntradas.addActionListener(e -> filtrarPorTipo("ENTRADA"));
        btnSalidas.addActionListener(e -> filtrarPorTipo("SALIDA"));
        
        panelBotones.add(btnNuevoMovimiento);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEntradas);
        panelBotones.add(btnSalidas);
        
        // Tabla
        String[] columnas = {"ID", "Material", "Tipo", "Cantidad", "Fecha", "Proyecto"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaMovimientos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaMovimientos);
        
        panel.add(panelBotones, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        add(panel);
    }
    
    private void cargarDatos() {
        modeloTabla.setRowCount(0);
        List<MovimientoInventario> movimientos = daoMovimiento.obtenerTodos();
        
        for (MovimientoInventario mov : movimientos) {
            Material mat = daoMaterial.obtener(mov.getIdMaterial());
            modeloTabla.addRow(new Object[]{
                mov.getIdMovimiento(),
                mat != null ? mat.getNombreMaterial() : "N/A",
                mov.getTipoMovimiento(),
                mov.getCantidad(),
                mov.getFecha(),
                mov.getIdProyecto()
            });
        }
    }
    
    private void filtrarPorTipo(String tipo) {
        modeloTabla.setRowCount(0);
        List<MovimientoInventario> movimientos = daoMovimiento.obtenerPorTipo(tipo);
        
        for (MovimientoInventario mov : movimientos) {
            Material mat = daoMaterial.obtener(mov.getIdMaterial());
            modeloTabla.addRow(new Object[]{
                mov.getIdMovimiento(),
                mat != null ? mat.getNombreMaterial() : "N/A",
                mov.getTipoMovimiento(),
                mov.getCantidad(),
                mov.getFecha(),
                mov.getIdProyecto()
            });
        }
    }
    
    private void abrirNuevoMovimiento() {
        JDialog dialog = new JDialog(this, "Nuevo Movimiento", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel lblMaterial = new JLabel("Material:");
        JComboBox<String> cbMateriales = new JComboBox<>();
        List<Material> materiales = daoMaterial.obtenerTodos();
        for (Material m : materiales) {
            cbMateriales.addItem(m.getNombreMaterial() + " (" + m.getIdMaterial() + ")");
        }
        
        JLabel lblTipo = new JLabel("Tipo Movimiento:");
        JComboBox<String> cbTipo = new JComboBox<>(new String[]{"ENTRADA", "SALIDA"});
        
        JLabel lblCantidad = new JLabel("Cantidad:");
        JTextField txtCantidad = new JTextField();
        
        JLabel lblProyecto = new JLabel("Proyecto (ID):");
        JTextField txtProyecto = new JTextField();
        
        panel.add(lblMaterial);
        panel.add(cbMateriales);
        panel.add(lblTipo);
        panel.add(cbTipo);
        panel.add(lblCantidad);
        panel.add(txtCantidad);
        panel.add(lblProyecto);
        panel.add(txtProyecto);
        
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            MovimientoInventario mov = new MovimientoInventario();
            mov.setIdMaterial(materiales.get(cbMateriales.getSelectedIndex()).getIdMaterial());
            mov.setTipoMovimiento((String) cbTipo.getSelectedItem());
            mov.setCantidad(Double.parseDouble(txtCantidad.getText()));
            mov.setIdProyecto(Integer.parseInt(txtProyecto.getText()));
            mov.setFecha(LocalDateTime.now());
            
            daoMovimiento.crear(mov);
            cargarDatos();
            dialog.dispose();
            JOptionPane.showMessageDialog(this, "Movimiento registrado correctamente");
        });
        
        panel.add(btnGuardar);
        dialog.add(panel);
        dialog.setVisible(true);
    }
}

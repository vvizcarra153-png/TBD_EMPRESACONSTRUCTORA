package modulo4b_inventario.ui;

import modulo4b_inventario.models.*;
import modulo4b_inventario.dao.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Frame para gestión de Inventario de Materiales
 */
public class InventarioFrame extends JFrame {
    private JTable tablaInventario;
    private DefaultTableModel modeloTabla;
    private InventarioMaterialDAO daoInventario;
    private AlmacenDAO daoAlmacen;
    private MaterialDAO daoMaterial;
    
    public InventarioFrame() {
        setTitle("Gestión de Inventario de Materiales");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        daoInventario = new InventarioMaterialDAO();
        daoAlmacen = new AlmacenDAO();
        daoMaterial = new MaterialDAO();
        
        inicializarUI();
        cargarDatos();
    }
    
    private void inicializarUI() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnNuevo = new JButton("Nuevo Inventario");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnAlertas = new JButton("Ver Alertas");
        JButton btnEliminar = new JButton("Eliminar");
        
        btnNuevo.addActionListener(e -> abrirNuevoInventario());
        btnActualizar.addActionListener(e -> cargarDatos());
        btnAlertas.addActionListener(e -> mostrarAlertas());
        btnEliminar.addActionListener(e -> eliminarInventario());
        
        panelBotones.add(btnNuevo);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnAlertas);
        panelBotones.add(btnEliminar);
        
        // Tabla
        String[] columnas = {"ID", "Material", "Almacén", "Stock Actual", "Stock Mínimo", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaInventario = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaInventario);
        
        panel.add(panelBotones, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        add(panel);
    }
    
    private void cargarDatos() {
        modeloTabla.setRowCount(0);
        List<InventarioMaterial> inventarios = daoInventario.obtenerTodos();
        
        for (InventarioMaterial inv : inventarios) {
            Material mat = daoMaterial.obtener(inv.getIdMaterial());
            Almacen alm = new Almacen();
            alm.setIdAlmacen(inv.getIdAlmacen());
            
            String estado = inv.estaAlertar() ? "⚠ BAJO STOCK" : "OK";
            
            modeloTabla.addRow(new Object[]{
                inv.getIdInventario(),
                mat != null ? mat.getNombreMaterial() : "N/A",
                inv.getIdAlmacen(),
                inv.getStockActualMaterial(),
                inv.getStockMinimoMaterial(),
                estado
            });
        }
    }
    
    private void abrirNuevoInventario() {
        JDialog dialog = new JDialog(this, "Nuevo Inventario", true);
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
        
        JLabel lblAlmacen = new JLabel("Almacén:");
        JComboBox<String> cbAlmacenes = new JComboBox<>();
        List<Almacen> almacenes = daoAlmacen.obtenerTodos();
        for (Almacen a : almacenes) {
            cbAlmacenes.addItem(a.getNombreAlmacen() + " (" + a.getIdAlmacen() + ")");
        }
        
        JLabel lblStockActual = new JLabel("Stock Actual:");
        JTextField txtStockActual = new JTextField();
        
        JLabel lblStockMinimo = new JLabel("Stock Mínimo:");
        JTextField txtStockMinimo = new JTextField();
        
        panel.add(lblMaterial);
        panel.add(cbMateriales);
        panel.add(lblAlmacen);
        panel.add(cbAlmacenes);
        panel.add(lblStockActual);
        panel.add(txtStockActual);
        panel.add(lblStockMinimo);
        panel.add(txtStockMinimo);
        
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            InventarioMaterial inv = new InventarioMaterial();
            inv.setIdMaterial(materiales.get(cbMateriales.getSelectedIndex()).getIdMaterial());
            inv.setIdAlmacen(almacenes.get(cbAlmacenes.getSelectedIndex()).getIdAlmacen());
            inv.setStockActualMaterial(Double.parseDouble(txtStockActual.getText()));
            inv.setStockMinimoMaterial(Double.parseDouble(txtStockMinimo.getText()));
            
            daoInventario.crear(inv);
            cargarDatos();
            dialog.dispose();
            JOptionPane.showMessageDialog(this, "Inventario guardado correctamente");
        });
        
        panel.add(btnGuardar);
        dialog.add(panel);
        dialog.setVisible(true);
    }
    
    private void mostrarAlertas() {
        List<InventarioMaterial> alertas = daoInventario.obtenerAlertasStock();
        if (alertas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay alertas de stock bajo");
            return;
        }
        
        StringBuilder mensaje = new StringBuilder("ALERTAS DE STOCK BAJO:\n\n");
        for (InventarioMaterial inv : alertas) {
            Material mat = daoMaterial.obtener(inv.getIdMaterial());
            mensaje.append("- ").append(mat.getNombreMaterial())
                   .append("\n  Stock: ").append(inv.getStockActualMaterial())
                   .append(" / Mínimo: ").append(inv.getStockMinimoMaterial())
                   .append("\n\n");
        }
        
        JOptionPane.showMessageDialog(this, mensaje.toString(), "Alertas", JOptionPane.WARNING_MESSAGE);
    }
    
    private void eliminarInventario() {
        int fila = tablaInventario.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un inventario");
            return;
        }
        
        int id = (Integer) modeloTabla.getValueAt(fila, 0);
        daoInventario.eliminar(id);
        cargarDatos();
        JOptionPane.showMessageDialog(this, "Inventario eliminado");
    }
}

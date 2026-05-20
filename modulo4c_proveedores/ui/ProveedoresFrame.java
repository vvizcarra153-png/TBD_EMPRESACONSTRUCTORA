package modulo4c_proveedores.ui;

import utils.UITheme;
import utils.NotificationManager;
import utils.ChartUtils;
import modulo4c_proveedores.dao.ProveedorDAO;
import modulo4c_proveedores.models.Proveedor;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Frame principal del módulo de Proveedores
 */
public class ProveedoresFrame extends JFrame {
    private JTabbedPane tabbedPane;
    private ProveedorDAO proveedorDAO;
    private JPanel notificationPanel;
    
    public ProveedoresFrame() {
        setTitle("Módulo Proveedores - Constructora Empresa");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200, 750);
        setLocationRelativeTo(null);
        
        proveedorDAO = new ProveedorDAO();
        
        initComponents();
    }
    
    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        UITheme.applyTheme(mainPanel);
        
        // Panel de encabezado
        JPanel headerPanel = createHeaderPanel();
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Panel de notificaciones
        notificationPanel = new JPanel();
        notificationPanel.setLayout(new BoxLayout(notificationPanel, BoxLayout.Y_AXIS));
        UITheme.applyTheme(notificationPanel);
        NotificationManager.setContainerPanel(notificationPanel);
        mainPanel.add(notificationPanel, BorderLayout.PAGE_END);
        
        // Panel con pestañas
        tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(UITheme.COLOR_BG);
        tabbedPane.setForeground(UITheme.COLOR_TEXT);
        tabbedPane.setFont(UITheme.FONT_LABEL);
        
        tabbedPane.addTab("🏢 Proveedores", createProveedoresPanel());
        tabbedPane.addTab("📋 Órdenes", createOrdenesPanel());
        tabbedPane.addTab("📊 Análisis", createAnalisisPanel());
        
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        
        setContentPane(mainPanel);
    }
    
    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        UITheme.applyThemeDark(panel);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setPreferredSize(new Dimension(0, 70));
        
        JLabel titleLabel = new JLabel("🏢 Gestión de Proveedores");
        UITheme.styleTitleLabel(titleLabel);
        panel.add(titleLabel, BorderLayout.WEST);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        UITheme.applyThemeDark(buttonPanel);
        
        JButton btnActualizar = new JButton("🔄 Actualizar");
        UITheme.styleButton(btnActualizar);
        btnActualizar.addActionListener(e -> recargarDatos());
        buttonPanel.add(btnActualizar);
        
        JButton btnReportes = new JButton("📊 Reportes");
        UITheme.styleButton(btnReportes);
        buttonPanel.add(btnReportes);
        
        panel.add(buttonPanel, BorderLayout.EAST);
        
        return panel;
    }
    
    private JPanel createProveedoresPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        UITheme.applyTheme(panel);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel de controles
        JPanel controlPanel = createControlPanel();
        panel.add(controlPanel, BorderLayout.NORTH);
        
        // Tabla de proveedores
        JTable table = new JTable();
        UITheme.styleTable(table);
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"ID", "Nombre", "Teléfono", "Dirección", "Categoría"});
        table.setModel(model);
        
        // Cargar datos
        List<Proveedor> proveedores = proveedorDAO.obtenerTodos();
        for (Proveedor p : proveedores) {
            model.addRow(new Object[]{p.getIdProveedor(), p.getNombreProveedor(), p.getTelefonoProveedor(),
                p.getDireccionProveedor(), p.getIdCategoriaProveedor()});
        }
        
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createOrdenesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        UITheme.applyTheme(panel);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel de controles
        JPanel controlPanel = createControlPanel();
        panel.add(controlPanel, BorderLayout.NORTH);
        
        // Tabla de órdenes
        JTable table = new JTable();
        UITheme.styleTable(table);
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Número", "Proveedor", "Fecha", "Monto", "Estado"});
        table.setModel(model);
        
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createAnalisisPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        UITheme.applyTheme(panel);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Gráfico de proveedores
        Map<String, Integer> dataProveedores = new HashMap<>();
        dataProveedores.put("Aceros SA", 450);
        dataProveedores.put("Concreto Plus", 380);
        dataProveedores.put("Madera Fina", 290);
        dataProveedores.put("Distribuidora General", 200);
        
        JPanel chartPanel = ChartUtils.createPieChart("Compras por Proveedor", dataProveedores);
        panel.add(chartPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        UITheme.applyTheme(panel);
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        UITheme.applyTheme(buttonPanel);
        
        JButton btnNuevo = new JButton("➕ Nuevo");
        UITheme.styleButton(btnNuevo);
        btnNuevo.addActionListener(e -> NotificationManager.showSuccess("Nuevo proveedor agregado"));
        buttonPanel.add(btnNuevo);
        
        JButton btnEditar = new JButton("✏️ Editar");
        UITheme.styleButton(btnEditar);
        buttonPanel.add(btnEditar);
        
        JButton btnEliminar = new JButton("🗑️ Eliminar");
        UITheme.styleButtonDanger(btnEliminar);
        buttonPanel.add(btnEliminar);
        
        JTextField searchField = new JTextField(20);
        UITheme.styleTextField(searchField);
        searchField.setPreferredSize(new Dimension(250, 30));
        buttonPanel.add(new JLabel("🔍 Buscar:"));
        buttonPanel.add(searchField);
        
        panel.add(buttonPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private void recargarDatos() {
        NotificationManager.showInfo("Datos recargados");
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProveedoresFrame frame = new ProveedoresFrame();
            frame.setVisible(true);
        });
    }
}

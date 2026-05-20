package constructoraempresa;

import modulo1_seguridad.model.Usuario;
import modulo1_seguridad.ui.LoginFrame;
import modulo1_seguridad.dao.ProyectoDAO;
import modulo1_seguridad.dao.EmpleadoDAO;
import modulo1_seguridad.dao.ClienteDAO;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Map;

public class ConstructoraEmpresa extends JFrame {

    private Usuario usuarioActual;

    // Colores
    private static final Color BG_SIDEBAR  = new Color(248, 249, 250);
    private static final Color BG_MAIN     = new Color(255, 255, 255);
    private static final Color BG_CONTENT  = new Color(243, 244, 246);
    private static final Color AZUL        = new Color(26, 86, 219);
    private static final Color TEXTO       = new Color(17, 24, 39);
    private static final Color TEXTO_MUTED = new Color(107, 114, 128);
    private static final Color BORDE       = new Color(229, 231, 235);
    private static final Color VERDE       = new Color(29, 158, 117);
    private static final Color AMARILLO    = new Color(186, 117, 23);
    private static final Color ROJO        = new Color(226, 75, 74);

    private JPanel panelContenido;
    private JLabel lblTopbarTitulo;
    private JLabel lblTopbarSub;
    private JLabel lblUsuarioTopbar;
    private JPanel itemActivo;

    public ConstructoraEmpresa(Usuario usuario) {
        this.usuarioActual = usuario;

        setTitle("ConstruSys - Sistema de Gestión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 800);
        setLocationRelativeTo(null);
        setResizable(true);

        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored) {}

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(BG_MAIN);
        root.add(crearSidebar(), BorderLayout.WEST);

        JPanel mainArea = new JPanel(new BorderLayout());
        mainArea.setBackground(BG_CONTENT);
        mainArea.add(crearTopbar(), BorderLayout.NORTH);

        panelContenido = new JPanel(new BorderLayout());
        panelContenido.setBackground(BG_CONTENT);
        panelContenido.add(crearVistaDashboard(), BorderLayout.CENTER);
        mainArea.add(panelContenido, BorderLayout.CENTER);

        root.add(mainArea, BorderLayout.CENTER);
        add(root);
    }

    // =========================================================
    // SIDEBAR
    // =========================================================
    private JPanel crearSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(210, 0));
        sidebar.setBackground(BG_SIDEBAR);
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, BORDE));

        // Logo
        JPanel logo = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 14));
        logo.setBackground(BG_SIDEBAR);
        logo.setMaximumSize(new Dimension(210, 58));
        logo.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, BORDE));
        JLabel ico = new JLabel("HH");
        ico.setFont(new Font("Segoe UI", Font.BOLD, 14));
        ico.setForeground(Color.WHITE);
        ico.setBackground(AZUL);
        ico.setOpaque(true);
        ico.setPreferredSize(new Dimension(28, 28));
        ico.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel tl = new JPanel();
        tl.setLayout(new BoxLayout(tl, BoxLayout.Y_AXIS));
        tl.setBackground(BG_SIDEBAR);
        JLabel nm = new JLabel("ConstruSys");
        nm.setFont(new Font("Segoe UI", Font.BOLD, 14));
        nm.setForeground(TEXTO);
        JLabel sb2 = new JLabel("Panel de gestion");
        sb2.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        sb2.setForeground(TEXTO_MUTED);
        tl.add(nm); tl.add(sb2);
        logo.add(ico); logo.add(tl);
        sidebar.add(logo);

        sidebar.add(sec("PRINCIPAL"));
        JPanel itemDash = item(">> ", "Dashboard", () -> navegar("Dashboard general", "Resumen ejecutivo del sistema", crearVistaDashboard()));
        marcarActivo(itemDash);
        sidebar.add(itemDash);

        sidebar.add(sec("PROYECTOS"));
        sidebar.add(item("[] ", "Proyectos", () -> {
            try {
                Object[][] data = ProyectoDAO.obtenerProyectosTabla();
                String[] cols = {"ID", "Nombre", "Estado", "Centro Costo", "Inicio", "Fin", "Costo (Bs)"};
                navegar("Proyectos", "Gestión de proyectos desde BD", crearVistaTabla("Proyectos", cols, data));
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cargar proyectos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }));

        sidebar.add(sec("CLIENTES"));
        sidebar.add(item("@ ", "Clientes", () -> {
            try {
                Object[][] data = ClienteDAO.obtenerClientesTabla();
                String[] cols = {"ID", "Nombre", "CI/NIT", "Teléfono", "Correo", "Ciudad", "Estado"};
                navegar("Clientes", "Gestión de clientes desde BD", crearVistaTabla("Clientes", cols, data));
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cargar clientes", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }));

        sidebar.add(sec("RRHH"));
        sidebar.add(item("OO ", "Empleados", () -> {
            try {
                Object[][] data = EmpleadoDAO.obtenerEmpleadosTabla();
                String[] cols = {"ID", "Nombre", "Cargo", "Departamento", "Teléfono", "Salario (Bs)", "Estado"};
                navegar("Empleados", "Gestión de recursos humanos desde BD", crearVistaTabla("Empleados", cols, data));
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cargar empleados", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }));

        sidebar.add(Box.createVerticalGlue());
        return sidebar;
    }

    private JLabel sec(String texto) {
        JLabel l = new JLabel(texto);
        l.setFont(new Font("Segoe UI", Font.BOLD, 10));
        l.setForeground(TEXTO_MUTED);
        l.setBorder(BorderFactory.createEmptyBorder(14, 14, 4, 0));
        l.setMaximumSize(new Dimension(210, 30));
        return l;
    }

    private JPanel item(String prefix, String texto, Runnable accion) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 7));
        p.setMaximumSize(new Dimension(210, 36));
        p.setBackground(BG_SIDEBAR);
        p.setCursor(new Cursor(Cursor.HAND_CURSOR));
        p.setBorder(BorderFactory.createEmptyBorder(0, 4, 0, 4));
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lbl.setForeground(TEXTO_MUTED);
        p.add(lbl);
        p.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { if (itemActivo != p) p.setBackground(new Color(240,242,245)); }
            public void mouseExited(MouseEvent e)  { if (itemActivo != p) p.setBackground(BG_SIDEBAR); }
            public void mouseClicked(MouseEvent e) { marcarActivo(p); accion.run(); }
        });
        return p;
    }

    private void marcarActivo(JPanel nuevo) {
        if (itemActivo != null) {
            itemActivo.setBackground(BG_SIDEBAR);
            for (Component c : itemActivo.getComponents())
                if (c instanceof JLabel) ((JLabel)c).setForeground(TEXTO_MUTED);
        }
        itemActivo = nuevo;
        nuevo.setBackground(BG_MAIN);
        for (Component c : nuevo.getComponents())
            if (c instanceof JLabel) { ((JLabel)c).setForeground(TEXTO); ((JLabel)c).setFont(new Font("Segoe UI",Font.BOLD,13)); }
    }

    // =========================================================
    // TOPBAR
    // =========================================================
    private JPanel crearTopbar() {
        JPanel bar = new JPanel(new BorderLayout());
        bar.setBackground(BG_MAIN);
        bar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0,0,1,0,BORDE),
            BorderFactory.createEmptyBorder(12,20,12,20)
        ));
        bar.setPreferredSize(new Dimension(0, 62));

        JPanel izq = new JPanel();
        izq.setLayout(new BoxLayout(izq, BoxLayout.Y_AXIS));
        izq.setBackground(BG_MAIN);
        lblTopbarTitulo = new JLabel("Dashboard general");
        lblTopbarTitulo.setFont(new Font("Segoe UI", Font.BOLD, 17));
        lblTopbarTitulo.setForeground(TEXTO);
        lblTopbarSub = new JLabel("Resumen ejecutivo del sistema");
        lblTopbarSub.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblTopbarSub.setForeground(TEXTO_MUTED);
        izq.add(lblTopbarTitulo);
        izq.add(lblTopbarSub);

        JPanel der = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        der.setBackground(BG_MAIN);
        
        lblUsuarioTopbar = new JLabel(usuarioActual.getNombre() + " (" + usuarioActual.getRol() + ")");
        lblUsuarioTopbar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblUsuarioTopbar.setForeground(TEXTO_MUTED);
        der.add(lblUsuarioTopbar);
        
        JButton btnCerrar = crearBoton("Cerrar Sesión", false);
        btnCerrar.addActionListener(e -> cerrarSesion());
        der.add(btnCerrar);

        bar.add(izq, BorderLayout.WEST);
        bar.add(der, BorderLayout.EAST);
        return bar;
    }

    private void cerrarSesion() {
        int option = JOptionPane.showConfirmDialog(this, "¿Deseas cerrar sesión?", "Cerrar Sesión", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            SwingUtilities.invokeLater(() -> {
                new LoginFrame().setVisible(true);
                dispose();
            });
        }
    }

    private JButton crearBoton(String texto, boolean primario) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        if (primario) {
            btn.setBackground(AZUL);
            btn.setForeground(Color.WHITE);
            btn.setOpaque(true);
            btn.setBorderPainted(false);
            btn.setBorder(BorderFactory.createEmptyBorder(7,14,7,14));
        } else {
            btn.setBackground(BG_MAIN);
            btn.setForeground(TEXTO);
            btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDE),
                BorderFactory.createEmptyBorder(6,12,6,12)
            ));
        }
        return btn;
    }

    // =========================================================
    // NAVEGACION INTERNA
    // =========================================================
    private void navegar(String titulo, String sub, JPanel vista) {
        lblTopbarTitulo.setText(titulo);
        lblTopbarSub.setText(sub);
        panelContenido.removeAll();
        panelContenido.add(vista, BorderLayout.CENTER);
        panelContenido.revalidate();
        panelContenido.repaint();
    }

    // =========================================================
    // VISTA DASHBOARD
    // =========================================================
    private JPanel crearVistaDashboard() {
        JPanel dash = new JPanel();
        dash.setLayout(new BoxLayout(dash, BoxLayout.Y_AXIS));
        dash.setBackground(BG_CONTENT);
        dash.setBorder(BorderFactory.createEmptyBorder(20,24,20,24));

        try {
            Map<String, String> stats = ProyectoDAO.obtenerEstadisticas();
            
            JPanel metricas = new JPanel(new GridLayout(1,4,14,0));
            metricas.setBackground(BG_CONTENT);
            metricas.setMaximumSize(new Dimension(Integer.MAX_VALUE, 115));
            metricas.add(metrica("PROYECTOS ACTIVOS", stats.get("proyectos_activos"), "Datos en tiempo real", AZUL, new Color(230,241,251)));
            metricas.add(metrica("INGRESOS COBRADOS", stats.get("ingresos_cobrados"), "Últimos 30 días", VERDE, new Color(225,245,238)));
            metricas.add(metrica("EMPLEADOS ACTIVOS", stats.get("empleados_activos"), "Datos en tiempo real", new Color(83,74,183), new Color(238,237,254)));
            metricas.add(metrica("MATERIALES BAJO STOCK", stats.get("materiales_bajo_stock"), "Requiere reposición", AMARILLO, new Color(250,238,218)));
            dash.add(metricas);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar estadísticas", "Error", JOptionPane.ERROR_MESSAGE);
        }

        dash.add(Box.createVerticalStrut(18));
        return dash;
    }

    private JPanel metrica(String label, String valor, String sub, Color colorSub, Color bgIco) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(BG_MAIN);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDE),
            BorderFactory.createEmptyBorder(14,16,14,16)
        ));
        JPanel icoPanel = new JPanel();
        icoPanel.setBackground(bgIco);
        icoPanel.setMaximumSize(new Dimension(36,36));
        icoPanel.setPreferredSize(new Dimension(36,36));
        icoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lLbl = new JLabel(label);
        lLbl.setFont(new Font("Segoe UI",Font.BOLD,10));
        lLbl.setForeground(TEXTO_MUTED);
        lLbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel lVal = new JLabel(valor);
        lVal.setFont(new Font("Segoe UI",Font.BOLD,22));
        lVal.setForeground(TEXTO);
        lVal.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel lSub = new JLabel(sub);
        lSub.setFont(new Font("Segoe UI",Font.PLAIN,11));
        lSub.setForeground(colorSub);
        lSub.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(icoPanel);
        card.add(Box.createVerticalStrut(8));
        card.add(lLbl);
        card.add(Box.createVerticalStrut(3));
        card.add(lVal);
        card.add(Box.createVerticalStrut(4));
        card.add(lSub);
        return card;
    }

    // =========================================================
    // VISTA TABLA REUTILIZABLE
    // =========================================================
    private JPanel crearVistaTabla(String titulo, String[] cols, Object[][] data) {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(BG_CONTENT);
        wrapper.setBorder(BorderFactory.createEmptyBorder(20,24,20,24));

        JPanel card = new JPanel(new BorderLayout(0,12));
        card.setBackground(BG_MAIN);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDE),
            BorderFactory.createEmptyBorder(16,16,16,16)
        ));

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(BG_MAIN);
        JLabel lTit = new JLabel(titulo);
        lTit.setFont(new Font("Segoe UI",Font.BOLD,15));
        lTit.setForeground(TEXTO);

        JTextField buscar = new JTextField(20);
        buscar.setFont(new Font("Segoe UI",Font.PLAIN,12));
        buscar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDE),
            BorderFactory.createEmptyBorder(5,10,5,10)
        ));
        JLabel lBuscar = new JLabel("  Buscar: ");
        lBuscar.setForeground(TEXTO_MUTED);
        JPanel hDer = new JPanel(new FlowLayout(FlowLayout.RIGHT,4,0));
        hDer.setBackground(BG_MAIN);
        hDer.add(lBuscar); hDer.add(buscar);
        header.add(lTit, BorderLayout.WEST);
        header.add(hDer, BorderLayout.EAST);

        // Tabla
        DefaultTableModel model = new DefaultTableModel(data, cols) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable tabla = new JTable(model);
        tabla.setFont(new Font("Segoe UI",Font.PLAIN,12));
        tabla.setRowHeight(28);
        tabla.setShowGrid(true);
        tabla.setGridColor(BORDE);
        tabla.setSelectionBackground(new Color(230,241,251));
        tabla.setSelectionForeground(TEXTO);
        tabla.setForeground(TEXTO);
        tabla.setBackground(BG_MAIN);
        tabla.setIntercellSpacing(new Dimension(10,0));

        JTableHeader th = tabla.getTableHeader();
        th.setFont(new Font("Segoe UI",Font.BOLD,12));
        th.setBackground(BG_CONTENT);
        th.setForeground(TEXTO_MUTED);
        th.setBorder(BorderFactory.createMatteBorder(0,0,1,0,BORDE));
        th.setReorderingAllowed(false);

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        tabla.setRowSorter(sorter);
        buscar.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String t = buscar.getText().trim();
                sorter.setRowFilter(t.isEmpty() ? null : RowFilter.regexFilter("(?i)"+t));
            }
        });

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createLineBorder(BORDE));
        scroll.getViewport().setBackground(BG_MAIN);

        card.add(header, BorderLayout.NORTH);
        card.add(scroll,  BorderLayout.CENTER);
        wrapper.add(card, BorderLayout.CENTER);
        return wrapper;
    }

    // =========================================================
    // MAIN
    // =========================================================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Para prueba rápida sin login
                Usuario usuarioTemp = new Usuario(1, "admin", "Administrador", "admin@constructora.com", "admin123", "Administrador", "Activo");
                new ConstructoraEmpresa(usuarioTemp).setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

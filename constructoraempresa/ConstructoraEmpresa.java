/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package constructoraempresa;

import modulo1_seguridad.ui.LoginFrame;
import modulo2_rrhh.ui.EmpleadoFrame;
import modulo3_finanzas.ui.ClienteFrame;
import modulo3_finanzas.ui.CotizacionFrame;
import modulo3_finanzas.ui.PagoClienteFrame;
import modulo4a_proyectos.ui.ProyectoFrame;
import modulo4b_inventario.ui.InventarioFrame;
import modulo4c_proveedores.ui.ProveedoresFrame;
import modulo4d_subcontratistas.ui.SubcontratistasFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

/**
 * Ventana Principal - Sistema de Gestión Empresarial
 * Tema: Azul Oscuro
 * @author Melani
 */
public class ConstructoraEmpresa extends JFrame {

    // COLORES AZUL OSCURO
    private static final Color AZUL_OSCURO = new Color(15, 32, 60);
    private static final Color AZUL_MEDIO = new Color(25, 50, 90);
    private static final Color AZUL_CLARO = new Color(41, 84, 140);
    private static final Color AZUL_ACENTO = new Color(52, 152, 219);
    private static final Color BLANCO = Color.WHITE;
    private static final Color TEXTO_OSCURO = new Color(50, 50, 50);

    public ConstructoraEmpresa() {
        // CONFIGURAR FRAME
        setTitle("CONSTRUCTORA EMPRESA - Sistema de Gestión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1400, 850);
        setLocationRelativeTo(null);
        setResizable(true);
        setUndecorated(false);

        // PANEL PRINCIPAL
        JPanel panelPrincipal = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                        RenderingHints.VALUE_ANTIALIAS_ON);
                
                GradientPaint gradient = new GradientPaint(
                        0, 0, AZUL_OSCURO,
                        0, getHeight(), AZUL_MEDIO
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        panelPrincipal.setLayout(new BorderLayout(15, 15));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // ========================
        // PANEL DE ENCABEZADO
        // ========================
        JPanel headerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                        RenderingHints.VALUE_ANTIALIAS_ON);
                
                GradientPaint gradient = new GradientPaint(
                        0, 0, AZUL_CLARO,
                        getWidth(), 0, AZUL_ACENTO
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        headerPanel.setPreferredSize(new Dimension(0, 80));

        JLabel lblTitulo = new JLabel("🏢 CONSTRUCTORA EMPRESA - MÓDULOS");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setForeground(BLANCO);

        JLabel lblSubtitulo = new JLabel("Sistema Integrado de Gestión Empresarial");
        lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 12));
        lblSubtitulo.setForeground(new Color(200, 200, 200));

        JPanel panelTitulos = new JPanel(new GridLayout(2, 1));
        panelTitulos.setOpaque(false);
        panelTitulos.add(lblTitulo);
        panelTitulos.add(lblSubtitulo);

        headerPanel.add(panelTitulos, BorderLayout.WEST);

        panelPrincipal.add(headerPanel, BorderLayout.NORTH);

        // ========================
        // PANEL DE MÓDULOS (GRID)
        // ========================
        JPanel panelModulos = new JPanel(new GridLayout(3, 3, 15, 15));
        panelModulos.setOpaque(false);
        panelModulos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // MÓDULOS
        panelModulos.add(crearModulo("🔐 SEGURIDAD", "Login y Autenticación", 
                () -> new LoginFrame().setVisible(true), new Color(30, 60, 100)));
        
        panelModulos.add(crearModulo("👥 EMPLEADOS", "Gestión de RR.HH.", 
                () -> new EmpleadoFrame().setVisible(true), new Color(35, 70, 110)));
        
        panelModulos.add(crearModulo("👤 CLIENTES", "Gestión de Clientes", 
                () -> new ClienteFrame().setVisible(true), new Color(40, 75, 120)));
        
        panelModulos.add(crearModulo("📋 COTIZACIONES", "Gestión de Cotizaciones", 
                () -> new CotizacionFrame().setVisible(true), new Color(30, 70, 110)));
        
        panelModulos.add(crearModulo("💰 PAGOS", "Gestión de Pagos", 
                () -> new PagoClienteFrame().setVisible(true), new Color(35, 75, 115)));
        
        panelModulos.add(crearModulo("🏢 PROYECTOS", "Gestión de Proyectos", 
                () -> new ProyectoFrame().setVisible(true), new Color(40, 70, 110)));
        
        panelModulos.add(crearModulo("📦 INVENTARIO", "Gestión de Materiales", 
                () -> new InventarioFrame().setVisible(true), new Color(30, 65, 105)));
        
        panelModulos.add(crearModulo("🏭 PROVEEDORES", "Gestión de Proveedores", 
                () -> new ProveedoresFrame().setVisible(true), new Color(35, 70, 110)));
        
        panelModulos.add(crearModulo("🔧 SUBCONTRATISTAS", "Gestión de Subcontratistas", 
                () -> new SubcontratistasFrame().setVisible(true), new Color(40, 75, 120)));

        panelPrincipal.add(panelModulos, BorderLayout.CENTER);

        // ========================
        // PANEL DE PIE
        // ========================
        JPanel footerPanel = new JPanel();
        footerPanel.setOpaque(false);
        footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JLabel lblFooter = new JLabel("© 2026 Constructora Empresa - Sistema Integrado v1.0");
        lblFooter.setFont(new Font("Arial", Font.ITALIC, 11));
        lblFooter.setForeground(new Color(150, 150, 150));
        footerPanel.add(lblFooter);

        panelPrincipal.add(footerPanel, BorderLayout.SOUTH);

        add(panelPrincipal);
    }

    /**
     * Crea un módulo (panel clickeable)
     */
    private JPanel crearModulo(String titulo, String descripcion, Runnable accion, Color colorBase) {
        
        JPanel modulo = new JPanel() {
            private boolean hovered = false;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                        RenderingHints.VALUE_ANTIALIAS_ON);

                // FONDO CON GRADIENTE
                GradientPaint gradient = new GradientPaint(
                        0, 0, colorBase,
                        0, getHeight(), new Color(colorBase.getRed() - 10, 
                                colorBase.getGreen() - 10, colorBase.getBlue() - 10)
                );
                g2d.setPaint(gradient);

                RoundRectangle2D rounded = new RoundRectangle2D.Float(
                        0, 0, getWidth() - 1, getHeight() - 1, 20, 20
                );
                g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

                // BORDE
                if (hovered) {
                    g2d.setColor(new Color(255, 193, 7)); // Amarillo hover
                    g2d.setStroke(new BasicStroke(3));
                } else {
                    g2d.setColor(AZUL_ACENTO);
                    g2d.setStroke(new BasicStroke(2));
                }
                g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
            }
        };

        modulo.setOpaque(false);
        modulo.setLayout(new BoxLayout(modulo, BoxLayout.Y_AXIS));
        modulo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        modulo.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // EVENTOS DE MOUSE
        modulo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                ((JPanel) e.getSource()).repaint();
                modulo.setBackground(new Color(50, 100, 150, 20));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ((JPanel) e.getSource()).repaint();
                modulo.setBackground(new Color(0, 0, 0, 0));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                accion.run();
            }
        });

        // TÍTULO
        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(BLANCO);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // DESCRIPCIÓN
        JLabel lblDesc = new JLabel(descripcion);
        lblDesc.setFont(new Font("Arial", Font.PLAIN, 12));
        lblDesc.setForeground(new Color(200, 200, 200));
        lblDesc.setAlignmentX(Component.CENTER_ALIGNMENT);

        modulo.add(Box.createVerticalStrut(10));
        modulo.add(lblTitulo);
        modulo.add(Box.createVerticalStrut(15));
        modulo.add(lblDesc);
        modulo.add(Box.createVerticalGlue());

        return modulo;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConstructoraEmpresa app = new ConstructoraEmpresa();
            app.setVisible(true);
        });
    }
}

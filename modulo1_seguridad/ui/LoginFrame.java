package modulo1_seguridad.ui;

import config.DatabaseConnection;
import modulo1_seguridad.dao.UsuarioDAO;
import modulo1_seguridad.model.Usuario;
import constructoraempresa.ConstructoraEmpresa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class LoginFrame extends JFrame {

    private static final Color AZUL = new Color(26, 86, 219);
    private static final Color GRIS_CLARO = new Color(248, 249, 250);
    private static final Color BLANCO = Color.WHITE;
    private static final Color TEXTO = new Color(17, 24, 39);
    private static final Color TEXTO_MUTED = new Color(107, 114, 128);
    private static final Color BORDE = new Color(229, 231, 235);
    private static final Color VERDE = new Color(29, 158, 117);
    private static final Color ROJO = new Color(226, 75, 74);

    private JTabbedPane tabPane;
    private JTextField txtUsuarioLogin, txtUsuarioRegistro, txtNombreRegistro, txtEmailRegistro;
    private JPasswordField txtPasswordLogin, txtPasswordRegistro, txtPasswordConfirm;
    private JLabel lblMensajeLogin, lblMensajeRegistro;

    public LoginFrame() {
        setTitle("ConstruSys - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 550);
        setLocationRelativeTo(null);
        setResizable(false);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        // Panel principal con BorderLayout
        JPanel main = new JPanel(new BorderLayout());
        main.setBackground(BLANCO);

        // Panel izquierdo (Branding)
        main.add(crearPanelIzquierdo(), BorderLayout.WEST);

        // Panel derecho (Login/Registro)
        main.add(crearPanelDerecho(), BorderLayout.CENTER);

        add(main);
    }

    private JPanel crearPanelIzquierdo() {
        JPanel panel = new JPanel();
        panel.setBackground(AZUL);
        panel.setPreferredSize(new Dimension(350, 0));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(60, 40, 60, 40));

        // Logo
        JLabel logo = new JLabel("CS");
        logo.setFont(new Font("Segoe UI", Font.BOLD, 48));
        logo.setForeground(BLANCO);
        logo.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Título
        JLabel titulo = new JLabel("ConstruSys");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titulo.setForeground(BLANCO);
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Subtítulo
        JLabel subtitulo = new JLabel("Sistema de Gestión");
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitulo.setForeground(new Color(200, 220, 255));
        subtitulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Descripción
        JLabel desc = new JLabel("<html><body style='width:250px; margin-top:30px;'>"
                + "Gestiona tu empresa constructora de forma eficiente con nuestro "
                + "sistema integral."
                + "</body></html>");
        desc.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        desc.setForeground(new Color(200, 220, 255));
        desc.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(logo);
        panel.add(Box.createVerticalStrut(10));
        panel.add(titulo);
        panel.add(Box.createVerticalStrut(5));
        panel.add(subtitulo);
        panel.add(Box.createVerticalStrut(30));
        panel.add(desc);
        panel.add(Box.createVerticalGlue());

        // Pie de página
        JLabel pie = new JLabel("© 2026 ConstruSys. Todos los derechos reservados.");
        pie.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        pie.setForeground(new Color(150, 180, 230));
        pie.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(pie);

        return panel;
    }

    private JPanel crearPanelDerecho() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(GRIS_CLARO);
        panel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));

        tabPane = new JTabbedPane();
        tabPane.setBackground(GRIS_CLARO);
        tabPane.addTab("Login", crearPestanaLogin());
        tabPane.addTab("Registro", crearPestanaRegistro());

        panel.add(tabPane, BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearPestanaLogin() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(GRIS_CLARO);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

        // Título
        JLabel titulo = new JLabel("Inicia sesión");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(TEXTO);
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(titulo);
        panel.add(Box.createVerticalStrut(10));

        JLabel subtitulo = new JLabel("Accede con tus credenciales");
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subtitulo.setForeground(TEXTO_MUTED);
        subtitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(subtitulo);
        panel.add(Box.createVerticalStrut(25));

        // Usuario
        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblUsuario.setForeground(TEXTO);
        lblUsuario.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lblUsuario);
        panel.add(Box.createVerticalStrut(5));

        txtUsuarioLogin = new JTextField(20);
        txtUsuarioLogin.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtUsuarioLogin.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDE),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        txtUsuarioLogin.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        panel.add(txtUsuarioLogin);
        panel.add(Box.createVerticalStrut(15));

        // Contraseña
        JLabel lblPassword = new JLabel("Contraseña");
        lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblPassword.setForeground(TEXTO);
        lblPassword.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lblPassword);
        panel.add(Box.createVerticalStrut(5));

        txtPasswordLogin = new JPasswordField(20);
        txtPasswordLogin.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtPasswordLogin.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDE),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        txtPasswordLogin.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        panel.add(txtPasswordLogin);
        panel.add(Box.createVerticalStrut(20));

        // Botón Login
        JButton btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnLogin.setBackground(AZUL);
        btnLogin.setForeground(BLANCO);
        btnLogin.setOpaque(true);
        btnLogin.setBorderPainted(false);
        btnLogin.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnLogin.addActionListener(e -> realizarLogin());
        panel.add(btnLogin);
        panel.add(Box.createVerticalStrut(15));

        // Mensaje
        lblMensajeLogin = new JLabel("");
        lblMensajeLogin.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblMensajeLogin.setForeground(ROJO);
        lblMensajeLogin.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lblMensajeLogin);

        panel.add(Box.createVerticalGlue());
        return panel;
    }

    private JPanel crearPestanaRegistro() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(GRIS_CLARO);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

        // Título
        JLabel titulo = new JLabel("Crear cuenta");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(TEXTO);
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(titulo);
        panel.add(Box.createVerticalStrut(10));

        JLabel subtitulo = new JLabel("Completa tus datos para registrarte");
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subtitulo.setForeground(TEXTO_MUTED);
        subtitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(subtitulo);
        panel.add(Box.createVerticalStrut(20));

        // Nombre
        JLabel lblNombre = new JLabel("Nombre Completo");
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblNombre.setForeground(TEXTO);
        lblNombre.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lblNombre);
        panel.add(Box.createVerticalStrut(5));

        txtNombreRegistro = new JTextField(20);
        txtNombreRegistro.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtNombreRegistro.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDE),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        txtNombreRegistro.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        panel.add(txtNombreRegistro);
        panel.add(Box.createVerticalStrut(12));

        // Email
        JLabel lblEmail = new JLabel("Correo Electrónico");
        lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblEmail.setForeground(TEXTO);
        lblEmail.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lblEmail);
        panel.add(Box.createVerticalStrut(5));

        txtEmailRegistro = new JTextField(20);
        txtEmailRegistro.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtEmailRegistro.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDE),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        txtEmailRegistro.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        panel.add(txtEmailRegistro);
        panel.add(Box.createVerticalStrut(12));

        // Usuario
        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblUsuario.setForeground(TEXTO);
        lblUsuario.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lblUsuario);
        panel.add(Box.createVerticalStrut(5));

        txtUsuarioRegistro = new JTextField(20);
        txtUsuarioRegistro.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtUsuarioRegistro.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDE),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        txtUsuarioRegistro.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        panel.add(txtUsuarioRegistro);
        panel.add(Box.createVerticalStrut(12));

        // Contraseña
        JLabel lblPassword = new JLabel("Contraseña");
        lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblPassword.setForeground(TEXTO);
        lblPassword.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lblPassword);
        panel.add(Box.createVerticalStrut(5));

        txtPasswordRegistro = new JPasswordField(20);
        txtPasswordRegistro.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtPasswordRegistro.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDE),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        txtPasswordRegistro.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        panel.add(txtPasswordRegistro);
        panel.add(Box.createVerticalStrut(12));

        // Confirmar Contraseña
        JLabel lblConfirm = new JLabel("Confirmar Contraseña");
        lblConfirm.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblConfirm.setForeground(TEXTO);
        lblConfirm.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lblConfirm);
        panel.add(Box.createVerticalStrut(5));

        txtPasswordConfirm = new JPasswordField(20);
        txtPasswordConfirm.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtPasswordConfirm.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDE),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        txtPasswordConfirm.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        panel.add(txtPasswordConfirm);
        panel.add(Box.createVerticalStrut(15));

        // Botón Registrar
        JButton btnRegistrar = new JButton("Crear Cuenta");
        btnRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnRegistrar.setBackground(VERDE);
        btnRegistrar.setForeground(BLANCO);
        btnRegistrar.setOpaque(true);
        btnRegistrar.setBorderPainted(false);
        btnRegistrar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnRegistrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegistrar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnRegistrar.addActionListener(e -> realizarRegistro());
        panel.add(btnRegistrar);
        panel.add(Box.createVerticalStrut(12));

        // Mensaje
        lblMensajeRegistro = new JLabel("");
        lblMensajeRegistro.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblMensajeRegistro.setForeground(ROJO);
        lblMensajeRegistro.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lblMensajeRegistro);

        panel.add(Box.createVerticalGlue());
        return panel;
    }

    private void realizarLogin() {
        String usuario = txtUsuarioLogin.getText().trim();
        String password = new String(txtPasswordLogin.getPassword()).trim();

        if (usuario.isEmpty() || password.isEmpty()) {
            lblMensajeLogin.setText("⚠ Usuario y contraseña requeridos");
            lblMensajeLogin.setForeground(ROJO);
            return;
        }

        try {
            Usuario usuarioAutenticado = UsuarioDAO.autenticar(usuario, password);

            if (usuarioAutenticado != null) {
                lblMensajeLogin.setText("✓ Login exitoso. Abriendo dashboard...");
                lblMensajeLogin.setForeground(VERDE);

                SwingUtilities.invokeLater(() -> {
                    new ConstructoraEmpresa(usuarioAutenticado).setVisible(true);
                    dispose();
                });
            } else {
                lblMensajeLogin.setText("✗ Usuario o contraseña incorrectos");
                lblMensajeLogin.setForeground(ROJO);
            }
        } catch (SQLException ex) {
            lblMensajeLogin.setText("✗ Error de conexión a base de datos");
            lblMensajeLogin.setForeground(ROJO);
            ex.printStackTrace();
        }
    }

    private void realizarRegistro() {
        String nombre = txtNombreRegistro.getText().trim();
        String email = txtEmailRegistro.getText().trim();
        String usuario = txtUsuarioRegistro.getText().trim();
        String password = new String(txtPasswordRegistro.getPassword()).trim();
        String confirm = new String(txtPasswordConfirm.getPassword()).trim();

        if (nombre.isEmpty() || email.isEmpty() || usuario.isEmpty() || password.isEmpty()) {
            lblMensajeRegistro.setText("⚠ Todos los campos son requeridos");
            lblMensajeRegistro.setForeground(ROJO);
            return;
        }

        if (!email.contains("@")) {
            lblMensajeRegistro.setText("⚠ Email inválido");
            lblMensajeRegistro.setForeground(ROJO);
            return;
        }

        if (!password.equals(confirm)) {
            lblMensajeRegistro.setText("⚠ Las contraseñas no coinciden");
            lblMensajeRegistro.setForeground(ROJO);
            return;
        }

        if (password.length() < 4) {
            lblMensajeRegistro.setText("⚠ La contraseña debe tener al menos 4 caracteres");
            lblMensajeRegistro.setForeground(ROJO);
            return;
        }

        try {
            if (UsuarioDAO.usuarioExiste(usuario)) {
                lblMensajeRegistro.setText("✗ El usuario ya existe");
                lblMensajeRegistro.setForeground(ROJO);
                return;
            }

            if (UsuarioDAO.emailExiste(email)) {
                lblMensajeRegistro.setText("✗ El email ya está registrado");
                lblMensajeRegistro.setForeground(ROJO);
                return;
            }

            if (UsuarioDAO.registrar(usuario, nombre, email, password, "Usuario")) {
                lblMensajeRegistro.setText("✓ Cuenta creada exitosamente. Cambia a Login");
                lblMensajeRegistro.setForeground(VERDE);

                // Limpiar campos
                txtNombreRegistro.setText("");
                txtEmailRegistro.setText("");
                txtUsuarioRegistro.setText("");
                txtPasswordRegistro.setText("");
                txtPasswordConfirm.setText("");

                // Cambiar a tab de login
                tabPane.setSelectedIndex(0);
            } else {
                lblMensajeRegistro.setText("✗ Error al crear la cuenta");
                lblMensajeRegistro.setForeground(ROJO);
            }
        } catch (SQLException ex) {
            lblMensajeRegistro.setText("✗ Error de conexión a base de datos");
            lblMensajeRegistro.setForeground(ROJO);
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            DatabaseConnection.getConnection();
            SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: No se pudo conectar a la base de datos",
                    "Error de Conexión", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}

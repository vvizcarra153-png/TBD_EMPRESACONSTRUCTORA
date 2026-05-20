/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo3_finanzas.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.RenderingHints;
import java.util.List;

import modulo3_finanzas.dao.ClienteDAO;
import modulo3_finanzas.models.Cliente;

public class ClienteFrame extends JFrame {

    private JTable tableClientes;
    private JButton btnNuevo, btnEditar, btnEliminar, btnActualizar;
    private ClienteDAO clienteDAO = new ClienteDAO();

    public ClienteFrame() {

        setTitle("Gestión de Clientes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;

                // ✅ CORRECCIÓN DEL ERROR
                g2d.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(155, 89, 182),
                        0, getHeight(), new Color(52, 73, 94)
                );

                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        panelPrincipal.setLayout(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // TABLA
        tableClientes = new JTable();
        JScrollPane scrollTabla = new JScrollPane(tableClientes);

        // BOTONES
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        panelBotones.setOpaque(false);

        btnNuevo = new JButton("Nuevo");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        btnActualizar = new JButton("Actualizar");

        panelBotones.add(btnNuevo);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnActualizar);

        // TITULO
        JLabel titulo = new JLabel("CLIENTES", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setForeground(Color.WHITE);

        // AGREGAR COMPONENTES
        panelPrincipal.add(titulo, BorderLayout.NORTH);
        panelPrincipal.add(scrollTabla, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);

        // EVENTO
        btnActualizar.addActionListener(e -> cargarTabla());

        cargarTabla();
    }

    private void cargarTabla() {

        try {

            List<Cliente> clientes = clienteDAO.obtenerTodos();

            String[] cols = {
                    "ID", "Nombre", "Teléfono", "Email",
                    "Dirección", "Tipo Doc", "Documento", "Registro"
            };

            Object[][] data = new Object[clientes.size()][cols.length];

            for (int i = 0; i < clientes.size(); i++) {

                Cliente c = clientes.get(i);

                data[i][0] = c.getIdCliente();
                data[i][1] = c.getNombreCliente();
                data[i][2] = c.getTelefonoCliente();
                data[i][3] = c.getEmailCliente();
                data[i][4] = c.getDireccionCliente();
                data[i][5] = c.getIdTipoDocumento();
                data[i][6] = c.getNroDocumentoCliente();
                data[i][7] = c.getFechaRegistroCliente();
            }

            tableClientes.setModel(new DefaultTableModel(data, cols));

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error cargando clientes:\n" + ex.getMessage()
            );
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new ClienteFrame().setVisible(true);
        });
    }
}
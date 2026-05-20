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

import modulo3_finanzas.dao.PagoClienteDAO;
import modulo3_finanzas.models.PagoCliente;

public class PagoClienteFrame extends JFrame {

    private JTable tablePagosCliente;
    private JButton btnNuevo, btnEditar, btnEliminar, btnActualizar;
    private PagoClienteDAO pagoClienteDAO = new PagoClienteDAO();

    public PagoClienteFrame() {

        setTitle("Gestión de Pagos de Clientes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;

                // ✅ CORRECCIÓN
                g2d.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(22, 160, 133),
                        0, getHeight(), new Color(44, 62, 80)
                );

                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        panelPrincipal.setLayout(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // TABLA
        tablePagosCliente = new JTable();
        JScrollPane scrollTabla = new JScrollPane(tablePagosCliente);

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
        JLabel titulo = new JLabel("PAGOS DE CLIENTE", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setForeground(Color.WHITE);

        panelPrincipal.add(titulo, BorderLayout.NORTH);
        panelPrincipal.add(scrollTabla, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);

        btnActualizar.addActionListener(e -> cargarTabla());

        cargarTabla();
    }

    private void cargarTabla() {

        try {

            List<PagoCliente> pagos = pagoClienteDAO.obtenerTodos();

            String[] cols = {
                    "ID", "Cliente", "Proyecto", "Estado Pago",
                    "Monto", "Fecha", "Método"
            };

            Object[][] data = new Object[pagos.size()][cols.length];

            for (int i = 0; i < pagos.size(); i++) {

                PagoCliente p = pagos.get(i);

                data[i][0] = p.getIdPagoCliente();
                data[i][1] = p.getIdCliente();
                data[i][2] = p.getIdProyecto();
                data[i][3] = p.getIdEstadoPago();
                data[i][4] = p.getMonto();
                data[i][5] = p.getFechaPago();
                data[i][6] = p.getMetodoPago();
            }

            tablePagosCliente.setModel(
                    new DefaultTableModel(data, cols)
            );

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error cargando pagos cliente:\n" + ex.getMessage()
            );
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new PagoClienteFrame().setVisible(true);
        });
    }
}
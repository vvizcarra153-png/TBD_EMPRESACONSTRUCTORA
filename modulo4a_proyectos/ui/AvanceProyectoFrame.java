/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo4a_proyectos.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.RenderingHints;
import java.util.List;

import modulo4a_proyectos.dao.AvanceProyectoDAO;
import modulo4a_proyectos.models.AvanceProyecto;

public class AvanceProyectoFrame extends JFrame {

    private JTable tableAvances;
    private JButton btnNuevo, btnEditar, btnEliminar, btnActualizar;
    private AvanceProyectoDAO avanceDAO = new AvanceProyectoDAO();

    public AvanceProyectoFrame() {

        setTitle("Gestión de Avances de Proyecto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 600);
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
                        0, 0, new Color(85, 239, 196),
                        0, getHeight(), new Color(255, 234, 167)
                );

                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        panelPrincipal.setLayout(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        tableAvances = new JTable();
        JScrollPane scrollTabla = new JScrollPane(tableAvances);

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

        JLabel titulo = new JLabel("AVANCES DE PROYECTO", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(Color.DARK_GRAY);

        panelPrincipal.add(titulo, BorderLayout.NORTH);
        panelPrincipal.add(scrollTabla, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);

        btnActualizar.addActionListener(e -> cargarTabla());

        cargarTabla();
    }

    private void cargarTabla() {

        try {

            List<AvanceProyecto> avances = avanceDAO.obtenerTodos();

            String[] cols = {
                    "ID", "Proyecto", "Fecha", "Avance (%)", "Observaciones"
            };

            Object[][] data = new Object[avances.size()][cols.length];

            for (int i = 0; i < avances.size(); i++) {

                AvanceProyecto a = avances.get(i);

                data[i][0] = a.getIdAvance();
                data[i][1] = a.getIdProyecto();
                data[i][2] = a.getFecha();
                data[i][3] = a.getPorcentajeAvance();
                data[i][4] = a.getObservaciones();
            }

            tableAvances.setModel(new DefaultTableModel(data, cols));

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error cargando avances: " + ex.getMessage()
            );
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new AvanceProyectoFrame().setVisible(true);
        });
    }
}
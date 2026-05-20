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

import modulo4a_proyectos.dao.ProyectoDAO;
import modulo4a_proyectos.models.Proyecto;

public class ProyectoFrame extends JFrame {

    private JTable tableProyectos;
    private JButton btnNuevo, btnEditar, btnEliminar, btnActualizar;
    private ProyectoDAO proyectoDAO = new ProyectoDAO();

    public ProyectoFrame() {

        setTitle("Gestión de Proyectos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1100, 600);
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
                        0, 0, new Color(214, 48, 49),
                        0, getHeight(), new Color(9, 132, 227)
                );

                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        panelPrincipal.setLayout(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        tableProyectos = new JTable();
        JScrollPane scrollTabla = new JScrollPane(tableProyectos);

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

        JLabel titulo = new JLabel("PROYECTOS", SwingConstants.CENTER);
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

            List<Proyecto> proys = proyectoDAO.obtenerTodos();

            String[] cols = {
                    "ID", "Nombre", "Cliente", "Estado", "Centro Costo",
                    "Cotización", "Prioridad", "Inicio", "Fin", "Costo"
            };

            Object[][] data = new Object[proys.size()][cols.length];

            for (int i = 0; i < proys.size(); i++) {

                Proyecto p = proys.get(i);

                data[i][0] = p.getIdProyecto();
                data[i][1] = p.getNombreProyecto();
                data[i][2] = p.getIdCliente();
                data[i][3] = p.getIdEstadoProyecto();
                data[i][4] = p.getIdCentroCosto();
                data[i][5] = p.getIdCotizacion();
                data[i][6] = p.getPrioridadProyecto();
                data[i][7] = p.getFechaInicioProyecto();
                data[i][8] = p.getFechaFinProyecto();
                data[i][9] = p.getCostoRealProyecto();
            }

            tableProyectos.setModel(new DefaultTableModel(data, cols));

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error cargando proyectos: " + ex.getMessage()
            );
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new ProyectoFrame().setVisible(true);
        });
    }
}
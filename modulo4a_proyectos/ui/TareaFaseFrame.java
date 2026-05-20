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

import modulo4a_proyectos.dao.TareaFaseDAO;
import modulo4a_proyectos.models.TareaFase;

public class TareaFaseFrame extends JFrame {

    private JTable tableTareas;
    private JButton btnNuevo, btnEditar, btnEliminar, btnActualizar;
    private TareaFaseDAO tareaDAO = new TareaFaseDAO();

    public TareaFaseFrame() {

        setTitle("Gestión de Tareas de Fase");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200, 600);
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
                        0, 0, new Color(232, 67, 147),
                        0, getHeight(), new Color(56, 103, 214)
                );

                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        panelPrincipal.setLayout(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        tableTareas = new JTable();
        JScrollPane scrollTabla = new JScrollPane(tableTareas);

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

        JLabel titulo = new JLabel("TAREAS DE FASE", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
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

            List<TareaFase> tareas = tareaDAO.obtenerTodos();

            String[] cols = {
                    "ID", "Fase", "Empleado Resp.", "Nombre", "Descripción",
                    "Inicio", "Fin", "Prioridad", "Estado", "Avance %"
            };

            Object[][] data = new Object[tareas.size()][cols.length];

            for (int i = 0; i < tareas.size(); i++) {

                TareaFase t = tareas.get(i);

                data[i][0] = t.getIdTarea();
                data[i][1] = t.getIdFase();
                data[i][2] = t.getIdEmpleadoResponsable();
                data[i][3] = t.getNombreTarea();
                data[i][4] = t.getDescripcionTarea();
                data[i][5] = t.getFechaInicioTarea();
                data[i][6] = t.getFechaFinTarea();
                data[i][7] = t.getPrioridadTarea();
                data[i][8] = t.getEstado();
                data[i][9] = t.getPorcentajeAvance();
            }

            tableTareas.setModel(new DefaultTableModel(data, cols));

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error cargando tareas: " + ex.getMessage()
            );
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new TareaFaseFrame().setVisible(true);
        });
    }
}
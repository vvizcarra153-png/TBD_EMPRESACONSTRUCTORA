package modulo4a_proyectos.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.RenderingHints;
import java.util.List;

import modulo4a_proyectos.dao.FaseProyectoDAO;
import modulo4a_proyectos.models.FaseProyecto;

public class FaseProyectoFrame extends JFrame {

    private JTable tableFases;
    private JButton btnNuevo, btnEditar, btnEliminar, btnActualizar;
    private FaseProyectoDAO faseDAO = new FaseProyectoDAO();

    public FaseProyectoFrame() {

        setTitle("Gestión de Fases de Proyecto");
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
                        0, 0, new Color(234, 181, 67),
                        0, getHeight(), new Color(54, 54, 54)
                );

                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        panelPrincipal.setLayout(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Tabla
        tableFases = new JTable();
        JScrollPane scrollTabla = new JScrollPane(tableFases);

        // Botones
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

        JLabel titulo = new JLabel("FASES DE PROYECTO", SwingConstants.CENTER);
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

            List<FaseProyecto> fases = faseDAO.obtenerTodos();

            String[] cols = {
                    "ID", "Proyecto", "Nombre", "Descripción", "Orden",
                    "Inicio", "Fin", "Progreso", "Estado",
                    "Asignado", "Estimado", "Real"
            };

            Object[][] data = new Object[fases.size()][cols.length];

            for (int i = 0; i < fases.size(); i++) {

                FaseProyecto f = fases.get(i);

                data[i][0] = f.getIdFase();
                data[i][1] = f.getIdProyecto();
                data[i][2] = f.getNombreFase();
                data[i][3] = f.getDescripcionFase();
                data[i][4] = f.getOrden();
                data[i][5] = f.getFechaInicioFase();
                data[i][6] = f.getFechaFinFase();
                data[i][7] = f.getProgreso();
                data[i][8] = f.getEstado();
                data[i][9] = f.getPorcentajeAsignado();
                data[i][10] = f.getCostoEstimado();
                data[i][11] = f.getCostoReal();
            }

            tableFases.setModel(new DefaultTableModel(data, cols));

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error cargando fases: " + ex.getMessage()
            );
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new FaseProyectoFrame().setVisible(true);
        });
    }
}
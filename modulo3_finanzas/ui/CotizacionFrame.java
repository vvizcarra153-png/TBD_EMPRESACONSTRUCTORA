/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo3_finanzas.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.RenderingHints;
import java.util.List;

import modulo3_finanzas.dao.CotizacionDAO;
import modulo3_finanzas.models.Cotizacion;

public class CotizacionFrame extends JFrame {

    private JTable tableCotizaciones;
    private JButton btnNuevo, btnEditar, btnEliminar, btnActualizar;
    private CotizacionDAO cotizacionDAO = new CotizacionDAO();

    public CotizacionFrame() {

        setTitle("Gestión de Cotizaciones");
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
                        0, 0, new Color(39, 174, 96),
                        0, getHeight(), new Color(41, 128, 185)
                );

                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        panelPrincipal.setLayout(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        tableCotizaciones = new JTable();
        JScrollPane scrollTabla = new JScrollPane(tableCotizaciones);

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

        panelPrincipal.add(new JLabel("COTIZACIONES", SwingConstants.CENTER), BorderLayout.NORTH);
        panelPrincipal.add(scrollTabla, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);

        btnActualizar.addActionListener(e -> cargarTabla());

        cargarTabla();
    }

    private void cargarTabla() {

        try {

            List<Cotizacion> cotizaciones = cotizacionDAO.obtenerTodos();

            String[] cols = {
                    "ID", "Cliente", "Materiales", "Mano Obra", "Otros",
                    "m²", "Pisos", "Ubicación", "Tiempo", "Margen", "Final", "Fecha"
            };

            Object[][] data = new Object[cotizaciones.size()][cols.length];

            for (int i = 0; i < cotizaciones.size(); i++) {

                Cotizacion c = cotizaciones.get(i);

                data[i][0] = c.getIdCotizacion();
                data[i][1] = c.getIdClienteCotizacion();
                data[i][2] = c.getCostosMateriales();
                data[i][3] = c.getCostoManoObra();
                data[i][4] = c.getOtrosCostos();
                data[i][5] = c.getMetrosCuadrados();
                data[i][6] = c.getNumeroPisos();
                data[i][7] = c.getUbicacionObra();
                data[i][8] = c.getTiempoEstimado();
                data[i][9] = c.getMargenGanancia();
                data[i][10] = c.getPrecioFinal();
                data[i][11] = c.getFechaCotizacion();
            }

            tableCotizaciones.setModel(new javax.swing.table.DefaultTableModel(data, cols));

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error cargando cotizaciones: " + ex.getMessage()
            );
        }
    }
}
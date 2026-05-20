/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo4b_inventario.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.RenderingHints;
import java.util.List;

import modulo4b_inventario.dao.MaterialDAO;
import modulo4b_inventario.models.Material;

public class MaterialFrame extends JFrame {

    private JTable tableMateriales;
    private JButton btnNuevo, btnEditar, btnEliminar, btnActualizar;
    private MaterialDAO materialDAO = new MaterialDAO();

    public MaterialFrame() {

        setTitle("Gestión de Materiales");
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
                        0, 0, new Color(116, 185, 255),
                        0, getHeight(), new Color(162, 155, 254)
                );

                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        panelPrincipal.setLayout(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        tableMateriales = new JTable();
        JScrollPane scrollTabla = new JScrollPane(tableMateriales);

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

        JLabel titulo = new JLabel("MATERIALES", SwingConstants.CENTER);
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

            List<Material> materiales = materialDAO.obtenerTodos();

            String[] cols = {"ID", "Nombre", "Unidad", "Categoría", "Proveedor"};

            Object[][] data = new Object[materiales.size()][cols.length];

            for (int i = 0; i < materiales.size(); i++) {

                Material m = materiales.get(i);

                data[i][0] = m.getIdMaterial();
                data[i][1] = m.getNombreMaterial();
                data[i][2] = m.getUnidadMedida();
                data[i][3] = m.getIdCategoriaMaterial();
                data[i][4] = m.getIdProveedor();
            }

            tableMateriales.setModel(new DefaultTableModel(data, cols));

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error cargando materiales: " + ex.getMessage()
            );
        }
    }
}
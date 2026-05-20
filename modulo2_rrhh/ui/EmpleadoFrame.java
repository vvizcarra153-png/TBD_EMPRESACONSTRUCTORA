/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo2_rrhh.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.RenderingHints;
import java.util.List;

import modulo2_rrhh.dao.EmpleadoDAO;
import modulo2_rrhh.models.Empleado;

public class EmpleadoFrame extends JFrame {

    private JTable tableEmpleados;
    private JButton btnNuevo, btnEditar, btnEliminar, btnActualizar;

    private EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    public EmpleadoFrame() {

        setTitle("Gestión de Empleados");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;

                g2d.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                GradientPaint gradient = new GradientPaint(
                        0,
                        0,
                        new Color(52, 152, 219),
                        0,
                        getHeight(),
                        new Color(41, 128, 185)
                );

                g2d.setPaint(gradient);

                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        panelPrincipal.setLayout(new BorderLayout(10, 10));

        panelPrincipal.setBorder(
                BorderFactory.createEmptyBorder(30, 30, 30, 30)
        );

        // TITULO
        JLabel lblTitulo = new JLabel(
                "EMPLEADOS",
                SwingConstants.CENTER
        );

        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);

        // TABLA
        tableEmpleados = new JTable();

        JScrollPane scrollTabla = new JScrollPane(tableEmpleados);

        // BOTONES
        JPanel panelBotones = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 15, 0)
        );

        panelBotones.setOpaque(false);

        btnNuevo = new JButton("Nuevo");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        btnActualizar = new JButton("Actualizar");

        panelBotones.add(btnNuevo);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnActualizar);

        // AGREGAR COMPONENTES
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        panelPrincipal.add(scrollTabla, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);

        // EVENTOS

        btnActualizar.addActionListener(e -> cargarTabla());

        btnNuevo.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                    this,
                    "Botón Nuevo presionado"
            );
        });

        btnEditar.addActionListener(e -> {

            int fila = tableEmpleados.getSelectedRow();

            if (fila == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Seleccione un empleado"
                );

                return;
            }

            JOptionPane.showMessageDialog(
                    this,
                    "Botón Editar presionado"
            );
        });

        btnEliminar.addActionListener(e -> {

            int fila = tableEmpleados.getSelectedRow();

            if (fila == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Seleccione un empleado"
                );

                return;
            }

            int confirmacion = JOptionPane.showConfirmDialog(
                    this,
                    "¿Eliminar empleado?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmacion == JOptionPane.YES_OPTION) {

                JOptionPane.showMessageDialog(
                        this,
                        "Empleado eliminado"
                );
            }
        });

        // CARGAR TABLA
        cargarTabla();
    }

    private void cargarTabla() {

        try {

            List<Empleado> empleados = empleadoDAO.obtenerTodos();

            String[] columnas = {
                    "ID",
                    "Nombre",
                    "Apellido",
                    "Sexo",
                    "CI",
                    "Email",
                    "Telefono",
                    "Categoria",
                    "Ingreso"
            };

            Object[][] datos = new Object[empleados.size()][columnas.length];

            for (int i = 0; i < empleados.size(); i++) {

                Empleado e = empleados.get(i);

                datos[i][0] = e.getIdEmpleado();
                datos[i][1] = e.getNombreEmpleado();
                datos[i][2] = e.getApellidoEmpleado();
                datos[i][3] = e.getSexo();
                datos[i][4] = e.getCiEmpleado();
                datos[i][5] = e.getEmailEmpleado();
                datos[i][6] = e.getTelefonoEmpleado();
                datos[i][7] = e.getIdCategoriaEmpleado();
                datos[i][8] = e.getFechaIngresoEmpleado();
            }

            DefaultTableModel modelo = new DefaultTableModel(
                    datos,
                    columnas
            );

            tableEmpleados.setModel(modelo);

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error cargando empleados:\n" + ex.getMessage()
            );
        }
    }

    // MAIN
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new EmpleadoFrame().setVisible(true);

        });
    }
}
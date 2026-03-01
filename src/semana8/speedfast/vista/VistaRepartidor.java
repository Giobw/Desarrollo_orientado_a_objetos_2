package semana8.speedfast.vista;

import semana8.speedfast.dao.RepartidorDAO;
import semana8.speedfast.modelo.Repartidor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VistaRepartidor extends JFrame {
    private RepartidorDAO dao;
    private JTextField txtNombre;
    private JTable tablaRepartidores;
    private DefaultTableModel modeloTabla;
    private int idSeleccionado = -1;

    public VistaRepartidor() {
        dao = new RepartidorDAO();
        configurarVentana();
        inicializarComponentes();
        cargarTabla();
    }

    private void configurarVentana() {
        setTitle("Gestión de Repartidores - SpeedFast");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar en la pantalla
        setLayout(new BorderLayout());
    }

    private void inicializarComponentes() {
        JPanel panelFormulario = new JPanel(new FlowLayout());
        panelFormulario.add(new JLabel("Nombre del Repartidor:"));
        txtNombre = new JTextField(15);
        panelFormulario.add(txtNombre);

        String[] columnas = {"ID", "Nombre"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaRepartidores = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaRepartidores);

        tablaRepartidores.getSelectionModel().addListSelectionListener(e -> {
            if (tablaRepartidores.getSelectedRow() != -1) {
                int fila = tablaRepartidores.getSelectedRow();
                idSeleccionado = (int) tablaRepartidores.getValueAt(fila, 0);
                txtNombre.setText((String) tablaRepartidores.getValueAt(fila, 1));
            }
        });

        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnAgregar = new JButton("Agregar");
        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnLimpiar = new JButton("Limpiar");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        // --- ACCIONES DE LOS BOTONES ---

        btnAgregar.addActionListener(e -> {
            if (!txtNombre.getText().trim().isEmpty()) {
                Repartidor nuevo = new Repartidor(txtNombre.getText().trim());
                if (dao.create(nuevo)) {
                    JOptionPane.showMessageDialog(this, "Repartidor guardado en MySQL!");
                    limpiarFormulario();
                    cargarTabla();
                }
            } else {
                JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío");
            }
        });

        btnEditar.addActionListener(e -> {
            if (idSeleccionado != -1 && !txtNombre.getText().trim().isEmpty()) {
                Repartidor editado = new Repartidor(idSeleccionado, txtNombre.getText().trim());
                if (dao.update(editado)) {
                    JOptionPane.showMessageDialog(this, "Repartidor actualizado!");
                    limpiarFormulario();
                    cargarTabla();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un repartidor de la tabla para editar");
            }
        });

        btnEliminar.addActionListener(e -> {
            if (idSeleccionado != -1) {
                int confirmacion = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas eliminarlo?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirmacion == JOptionPane.YES_OPTION) {
                    if (dao.delete(idSeleccionado)) {
                        JOptionPane.showMessageDialog(this, "Repartidor eliminado!");
                        limpiarFormulario();
                        cargarTabla();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un repartidor de la tabla para eliminar");
            }
        });

        btnLimpiar.addActionListener(e -> limpiarFormulario());

        add(panelFormulario, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void cargarTabla() {
        modeloTabla.setRowCount(0); // Limpiar la tabla visual
        List<Repartidor> lista = dao.readAll(); // Traer de MySQL
        for (Repartidor r : lista) {
            modeloTabla.addRow(new Object[]{r.getId(), r.getNombre()});
        }
    }

    private void limpiarFormulario() {
        txtNombre.setText("");
        idSeleccionado = -1;
        tablaRepartidores.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VistaRepartidor().setVisible(true);
        });
    }
}
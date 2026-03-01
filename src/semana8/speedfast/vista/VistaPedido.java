package semana8.speedfast.vista;

import semana8.speedfast.dao.PedidoDAO;
import semana8.speedfast.modelo.Pedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VistaPedido extends JFrame {
    private PedidoDAO dao;
    private JTextField txtDireccion;
    private JComboBox<String> cbTipo;
    private JComboBox<String> cbEstado;
    private JTable tablaPedidos;
    private DefaultTableModel modeloTabla;
    private int idSeleccionado = -1;

    public VistaPedido() {
        dao = new PedidoDAO();
        configurarVentana();
        inicializarComponentes();
        cargarTabla();
    }

    private void configurarVentana() {
        setTitle("Gestión de Pedidos - SpeedFast");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    private void inicializarComponentes() {
        // --- Panel superior ---
        JPanel panelFormulario = new JPanel(new GridLayout(3, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelFormulario.add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        panelFormulario.add(txtDireccion);

        panelFormulario.add(new JLabel("Tipo de Pedido:"));
        cbTipo = new JComboBox<>(new String[]{"COMIDA", "ENCOMIENDA", "EXPRESS"});
        panelFormulario.add(cbTipo);

        panelFormulario.add(new JLabel("Estado:"));
        cbEstado = new JComboBox<>(new String[]{"PENDIENTE", "EN REPARTO", "ENTREGADO"});
        panelFormulario.add(cbEstado);

        // --- Panel central ---
        String[] columnas = {"ID", "Dirección", "Tipo", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaPedidos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaPedidos);

        tablaPedidos.getSelectionModel().addListSelectionListener(e -> {
            if (tablaPedidos.getSelectedRow() != -1) {
                int fila = tablaPedidos.getSelectedRow();
                idSeleccionado = (int) tablaPedidos.getValueAt(fila, 0);
                txtDireccion.setText((String) tablaPedidos.getValueAt(fila, 1));
                cbTipo.setSelectedItem(tablaPedidos.getValueAt(fila, 2));
                cbEstado.setSelectedItem(tablaPedidos.getValueAt(fila, 3));
            }
        });

        // --- Panel inferior ---
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
            if (!txtDireccion.getText().trim().isEmpty()) {
                Pedido nuevo = new Pedido(
                        txtDireccion.getText().trim(),
                        cbTipo.getSelectedItem().toString(),
                        cbEstado.getSelectedItem().toString()
                );
                if (dao.create(nuevo)) {
                    JOptionPane.showMessageDialog(this, "Pedido registrado!");
                    limpiarFormulario();
                    cargarTabla();
                }
            } else {
                JOptionPane.showMessageDialog(this, "La dirección no puede estar vacía");
            }
        });

        btnEditar.addActionListener(e -> {
            if (idSeleccionado != -1 && !txtDireccion.getText().trim().isEmpty()) {
                Pedido editado = new Pedido(
                        idSeleccionado,
                        txtDireccion.getText().trim(),
                        cbTipo.getSelectedItem().toString(),
                        cbEstado.getSelectedItem().toString()
                );
                if (dao.update(editado)) {
                    JOptionPane.showMessageDialog(this, "Pedido actualizado!");
                    limpiarFormulario();
                    cargarTabla();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un pedido para editar");
            }
        });

        btnEliminar.addActionListener(e -> {
            if (idSeleccionado != -1) {
                if (dao.delete(idSeleccionado)) {
                    JOptionPane.showMessageDialog(this, "Pedido eliminado!");
                    limpiarFormulario();
                    cargarTabla();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un pedido para eliminar");
            }
        });

        btnLimpiar.addActionListener(e -> limpiarFormulario());

        add(panelFormulario, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void cargarTabla() {
        modeloTabla.setRowCount(0);
        List<Pedido> lista = dao.readAll();
        for (Pedido p : lista) {
            modeloTabla.addRow(new Object[]{p.getId(), p.getDireccion(), p.getTipo(), p.getEstado()});
        }
    }

    private void limpiarFormulario() {
        txtDireccion.setText("");
        cbTipo.setSelectedIndex(0);
        cbEstado.setSelectedIndex(0);
        idSeleccionado = -1;
        tablaPedidos.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VistaPedido().setVisible(true));
    }
}
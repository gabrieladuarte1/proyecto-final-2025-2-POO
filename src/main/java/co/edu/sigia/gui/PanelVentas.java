package co.edu.sigia.gui;
import co.edu.sigia.dao.VentaDAO;
import co.edu.sigia.dao.ClienteDAO;
import co.edu.sigia.dao.ProductoDAO;
import co.edu.sigia.modelo.Venta;
import co.edu.sigia.modelo.Cliente;
import co.edu.sigia.modelo.Producto;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Panel para la gestión de ventas
 */
public class PanelVentas extends JPanel {
    private VentaDAO ventaDAO;
    private ClienteDAO clienteDAO;
    private ProductoDAO productoDAO;
    private JTable tablaVentas;
    private DefaultTableModel modeloTabla;
    
    public PanelVentas() {
        ventaDAO = new VentaDAO();
        clienteDAO = new ClienteDAO();
        productoDAO = new ProductoDAO();
        inicializarComponentes();
        try {
            cargarVentas();
        } catch (Exception e) {
            System.err.println("Error al cargar ventas: " + e.getMessage());
        }
    }
    
    private void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel superior con botones
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnNuevaVenta = new JButton("Nueva Venta");
        btnNuevaVenta.addActionListener(e -> nuevaVenta());
        panelSuperior.add(btnNuevaVenta);
        
        JButton btnConsultar = new JButton("Consultar Historial");
        btnConsultar.addActionListener(e -> consultarHistorial());
        panelSuperior.add(btnConsultar);
        
        // Tabla de ventas
        String[] columnas = {"ID", "Cliente", "Producto", "Cantidad", "Precio Unit.", "Precio Total", "Fecha"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaVentas = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaVentas);
        
        add(panelSuperior, BorderLayout.NORTH);
        add(scrollTabla, BorderLayout.CENTER);
    }
    
    private void cargarVentas() {
        List<Venta> ventas = ventaDAO.obtenerTodas();
        modeloTabla.setRowCount(0);
        
        for (Venta venta : ventas) {
            Object[] fila = {
                venta.getId(),
                venta.getCliente().getNombre(),
                venta.getProducto().getNombre(),
                venta.getCantidad(),
                venta.getPrecioUnitario(),
                venta.getPrecioTotal(),
                venta.getFecha().toString()
            };
            modeloTabla.addRow(fila);
        }
    }
    
    private void nuevaVenta() {
        JDialog dialogo = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Nueva Venta", true);
        dialogo.setSize(500, 350);
        dialogo.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Cliente
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Cliente:*"), gbc);
        gbc.gridx = 1;
        JComboBox<Cliente> comboCliente = new JComboBox<>();
        List<Cliente> clientes = clienteDAO.obtenerTodos();
        for (Cliente c : clientes) {
            comboCliente.addItem(c);
        }
        panel.add(comboCliente, gbc);
        
        // Producto
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Producto:*"), gbc);
        gbc.gridx = 1;
        JComboBox<Producto> comboProducto = new JComboBox<>();
        List<Producto> productos = productoDAO.obtenerTodos();
        for (Producto p : productos) {
            comboProducto.addItem(p);
        }
        panel.add(comboProducto, gbc);
        
        // Cantidad
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Cantidad:*"), gbc);
        gbc.gridx = 1;
        JTextField txtCantidad = new JTextField(20);
        panel.add(txtCantidad, gbc);
        
        // Precio Unitario
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Precio Unitario:*"), gbc);
        gbc.gridx = 1;
        JTextField txtPrecioUnitario = new JTextField(20);
        panel.add(txtPrecioUnitario, gbc);
        
        // Fecha
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Fecha:*"), gbc);
        gbc.gridx = 1;
        JTextField txtFecha = new JTextField(20);
        txtFecha.setText(LocalDate.now().toString());
        panel.add(txtFecha, gbc);
        
        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            if (guardarVenta(comboCliente, comboProducto, txtCantidad, txtPrecioUnitario, txtFecha)) {
                dialogo.dispose();
            }
        });
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dialogo.dispose());
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        
        dialogo.add(panel, BorderLayout.CENTER);
        dialogo.add(panelBotones, BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }
    
    private boolean guardarVenta(JComboBox<Cliente> comboCliente, JComboBox<Producto> comboProducto,
                                JTextField txtCantidad, JTextField txtPrecioUnitario, JTextField txtFecha) {
        try {
            Cliente cliente = (Cliente) comboCliente.getSelectedItem();
            Producto producto = (Producto) comboProducto.getSelectedItem();
            int cantidad = Integer.parseInt(txtCantidad.getText().trim());
            double precioUnitario = Double.parseDouble(txtPrecioUnitario.getText().trim());
            LocalDate fecha = LocalDate.parse(txtFecha.getText().trim());
            
            if (cliente == null || producto == null) {
                JOptionPane.showMessageDialog(this, "Por favor seleccione cliente y producto", "Validación", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            
            // Verificar stock
            if (!producto.tieneStock(cantidad)) {
                JOptionPane.showMessageDialog(this, 
                    "Stock insuficiente. Stock disponible: " + producto.getCantidad(), 
                    "Error de Stock", 
                    JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            Venta venta = new Venta(cliente, producto, cantidad, precioUnitario, fecha);
            
            if (ventaDAO.crear(venta)) {
                JOptionPane.showMessageDialog(this, "Venta registrada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarVentas();
                return true;
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar la venta. Verifique el stock disponible.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    private void consultarHistorial() {
        JOptionPane.showMessageDialog(this, 
            "Para consultar por rango de fechas, use el módulo de Reportes", 
            "Información", 
            JOptionPane.INFORMATION_MESSAGE);
    }
}


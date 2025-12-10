package co.edu.sigia.gui;
import co.edu.sigia.dao.CompraDAO;
import co.edu.sigia.dao.ProveedorDAO;
import co.edu.sigia.dao.ProductoDAO;
import co.edu.sigia.modelo.Compra;
import co.edu.sigia.modelo.Proveedor;
import co.edu.sigia.modelo.Producto;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Panel para la gestión de compras
 */
public class PanelCompras extends JPanel {
    private CompraDAO compraDAO;
    private ProveedorDAO proveedorDAO;
    private ProductoDAO productoDAO;
    private JTable tablaCompras;
    private DefaultTableModel modeloTabla;
    
    public PanelCompras() {
        compraDAO = new CompraDAO();
        proveedorDAO = new ProveedorDAO();
        productoDAO = new ProductoDAO();
        inicializarComponentes();
        try {
            cargarCompras();
        } catch (Exception e) {
            System.err.println("Error al cargar compras: " + e.getMessage());
        }
    }
    
    private void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel superior con botones
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnNuevaCompra = new JButton("Nueva Compra");
        btnNuevaCompra.addActionListener(e -> nuevaCompra());
        panelSuperior.add(btnNuevaCompra);
        
        JButton btnConsultar = new JButton("Consultar Historial");
        btnConsultar.addActionListener(e -> consultarHistorial());
        panelSuperior.add(btnConsultar);
        
        // Tabla de compras
        String[] columnas = {"ID", "Proveedor", "Producto", "Cantidad", "Precio Unit.", "Precio Total", "Fecha"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaCompras = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaCompras);
        
        add(panelSuperior, BorderLayout.NORTH);
        add(scrollTabla, BorderLayout.CENTER);
    }
    
    private void cargarCompras() {
        List<Compra> compras = compraDAO.obtenerTodas();
        modeloTabla.setRowCount(0);
        
        for (Compra compra : compras) {
            Object[] fila = {
                compra.getId(),
                compra.getProveedor().getNombre(),
                compra.getProducto().getNombre(),
                compra.getCantidad(),
                compra.getPrecioUnitario(),
                compra.getPrecioTotal(),
                compra.getFecha().toString()
            };
            modeloTabla.addRow(fila);
        }
    }
    
    private void nuevaCompra() {
        JDialog dialogo = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Nueva Compra", true);
        dialogo.setSize(500, 350);
        dialogo.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Proveedor
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Proveedor:*"), gbc);
        gbc.gridx = 1;
        JComboBox<Proveedor> comboProveedor = new JComboBox<>();
        List<Proveedor> proveedores = proveedorDAO.obtenerTodos();
        for (Proveedor p : proveedores) {
            comboProveedor.addItem(p);
        }
        panel.add(comboProveedor, gbc);
        
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
            if (guardarCompra(comboProveedor, comboProducto, txtCantidad, txtPrecioUnitario, txtFecha)) {
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
    
    private boolean guardarCompra(JComboBox<Proveedor> comboProveedor, JComboBox<Producto> comboProducto,
                                  JTextField txtCantidad, JTextField txtPrecioUnitario, JTextField txtFecha) {
        try {
            Proveedor proveedor = (Proveedor) comboProveedor.getSelectedItem();
            Producto producto = (Producto) comboProducto.getSelectedItem();
            int cantidad = Integer.parseInt(txtCantidad.getText().trim());
            double precioUnitario = Double.parseDouble(txtPrecioUnitario.getText().trim());
            LocalDate fecha = LocalDate.parse(txtFecha.getText().trim());
            
            if (proveedor == null || producto == null) {
                JOptionPane.showMessageDialog(this, "Por favor seleccione proveedor y producto", "Validación", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            
            Compra compra = new Compra(proveedor, producto, cantidad, precioUnitario, fecha);
            
            if (compraDAO.crear(compra)) {
                JOptionPane.showMessageDialog(this, "Compra registrada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarCompras();
                return true;
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar la compra", "Error", JOptionPane.ERROR_MESSAGE);
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


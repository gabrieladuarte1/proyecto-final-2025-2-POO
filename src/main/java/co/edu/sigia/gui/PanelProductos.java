package co.edu.sigia.gui;
import co.edu.sigia.dao.ProductoDAO;
import co.edu.sigia.dao.CategoriaDAO;
import co.edu.sigia.modelo.Producto;
import co.edu.sigia.modelo.Categoria;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Panel para la gestión de productos (CRUD)
 */
public class PanelProductos extends JPanel {
    private ProductoDAO productoDAO;
    private CategoriaDAO categoriaDAO;
    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;
    private JTextField txtNombre, txtDescripcion, txtCantidad, txtPrecio, txtPrecioCompra;
    private JComboBox<Categoria> comboCategoria;
    private JTextField txtBuscar;
    private Producto productoSeleccionado;
    
    public PanelProductos() {
        productoDAO = new ProductoDAO();
        categoriaDAO = new CategoriaDAO();
        inicializarComponentes();
        try {
            cargarProductos();
        } catch (Exception e) {
            System.err.println("Error al cargar productos: " + e.getMessage());
            JOptionPane.showMessageDialog(this, 
                "No se pudo conectar a la base de datos.\n\n" +
                "Por favor verifica:\n" +
                "1. Que MySQL esté ejecutándose\n" +
                "2. Que la base de datos 'sigia_agrostore' exista\n" +
                "3. Las credenciales en ConexionBD.java\n" +
                "4. Que el driver MySQL Connector esté disponible",
                "Error de Conexión",
                JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel superior - Búsqueda y botones
        JPanel panelSuperior = new JPanel(new BorderLayout(10, 10));
        
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.add(new JLabel("Buscar:"));
        txtBuscar = new JTextField(20);
        panelBusqueda.add(txtBuscar);
        JButton btnBuscar = new JButton("Buscar por Nombre");
        btnBuscar.addActionListener(e -> buscarProductos());
        panelBusqueda.add(btnBuscar);
        JButton btnBuscarCategoria = new JButton("Buscar por Categoría");
        btnBuscarCategoria.addActionListener(e -> buscarPorCategoria());
        panelBusqueda.add(btnBuscarCategoria);
        JButton btnMostrarTodos = new JButton("Mostrar Todos");
        btnMostrarTodos.addActionListener(e -> cargarProductos());
        panelBusqueda.add(btnMostrarTodos);
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnNuevo = new JButton("Nuevo Producto");
        btnNuevo.addActionListener(e -> nuevoProducto());
        panelBotones.add(btnNuevo);
        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(e -> editarProducto());
        panelBotones.add(btnEditar);
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(e -> eliminarProducto());
        panelBotones.add(btnEliminar);
        
        panelSuperior.add(panelBusqueda, BorderLayout.WEST);
        panelSuperior.add(panelBotones, BorderLayout.EAST);
        
        // Tabla de productos
        String[] columnas = {"ID", "Nombre", "Descripción", "Categoría", "Cantidad", "Precio", "Precio Compra"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaProductos = new JTable(modeloTabla);
        tablaProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaProductos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int filaSeleccionada = tablaProductos.getSelectedRow();
                if (filaSeleccionada >= 0) {
                    int idProducto = (Integer) modeloTabla.getValueAt(filaSeleccionada, 0);
                    productoSeleccionado = productoDAO.obtenerPorId(idProducto);
                }
            }
        });
        
        JScrollPane scrollTabla = new JScrollPane(tablaProductos);
        
        add(panelSuperior, BorderLayout.NORTH);
        add(scrollTabla, BorderLayout.CENTER);
    }
    
    private void cargarProductos() {
        List<Producto> productos = productoDAO.obtenerTodos();
        modeloTabla.setRowCount(0);
        
        if (productos == null || productos.isEmpty()) {
            return; // No hay productos o no hay conexión
        }
        
        for (Producto producto : productos) {
            Object[] fila = {
                producto.getIdProducto(),
                producto.getNombre(),
                producto.getDescripcion() != null ? producto.getDescripcion() : "",
                producto.getCategoria() != null ? producto.getCategoria().getNombre() : "Sin categoría",
                producto.getCantidad(),
                producto.getPrecio(),
                producto.getPrecioCompra()
            };
            modeloTabla.addRow(fila);
        }
    }
    
    private void buscarProductos() {
        String nombre = txtBuscar.getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un nombre para buscar", "Búsqueda", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        List<Producto> productos = productoDAO.buscarPorNombre(nombre);
        modeloTabla.setRowCount(0);
        
        for (Producto producto : productos) {
            Object[] fila = {
                producto.getIdProducto(),
                producto.getNombre(),
                producto.getDescripcion() != null ? producto.getDescripcion() : "",
                producto.getCategoria() != null ? producto.getCategoria().getNombre() : "Sin categoría",
                producto.getCantidad(),
                producto.getPrecio(),
                producto.getPrecioCompra()
            };
            modeloTabla.addRow(fila);
        }
        
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron productos con ese nombre", "Búsqueda", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void buscarPorCategoria() {
        List<Categoria> categorias = categoriaDAO.obtenerTodas();
        if (categorias.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay categorías registradas", "Búsqueda", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Categoria categoriaSeleccionada = (Categoria) JOptionPane.showInputDialog(
            this,
            "Seleccione una categoría:",
            "Buscar por Categoría",
            JOptionPane.QUESTION_MESSAGE,
            null,
            categorias.toArray(),
            categorias.get(0)
        );
        
        if (categoriaSeleccionada != null) {
            List<Producto> productos = productoDAO.buscarPorCategoria(categoriaSeleccionada.getIdCategoria());
            modeloTabla.setRowCount(0);
            
            for (Producto producto : productos) {
                Object[] fila = {
                    producto.getIdProducto(),
                    producto.getNombre(),
                    producto.getDescripcion() != null ? producto.getDescripcion() : "",
                    producto.getCategoria() != null ? producto.getCategoria().getNombre() : "Sin categoría",
                    producto.getCantidad(),
                    producto.getPrecio(),
                    producto.getPrecioCompra()
                };
                modeloTabla.addRow(fila);
            }
        }
    }
    
    private void nuevoProducto() {
        mostrarDialogoProducto(null);
    }
    
    private void editarProducto() {
        if (productoSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione un producto para editar", "Editar Producto", JOptionPane.WARNING_MESSAGE);
            return;
        }
        mostrarDialogoProducto(productoSeleccionado);
    }
    
    private void eliminarProducto() {
        if (productoSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione un producto para eliminar", "Eliminar Producto", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirmacion = JOptionPane.showConfirmDialog(
            this,
            "¿Está seguro de eliminar el producto: " + productoSeleccionado.getNombre() + "?",
            "Confirmar Eliminación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            if (productoDAO.eliminar(productoSeleccionado.getIdProducto())) {
                JOptionPane.showMessageDialog(this, "Producto eliminado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarProductos();
                productoSeleccionado = null;
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar el producto", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void mostrarDialogoProducto(Producto producto) {
        JDialog dialogo = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), 
            producto == null ? "Nuevo Producto" : "Editar Producto", true);
        dialogo.setSize(500, 400);
        dialogo.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Nombre
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Nombre:*"), gbc);
        gbc.gridx = 1;
        txtNombre = new JTextField(20);
        panel.add(txtNombre, gbc);
        
        // Descripción
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Descripción:"), gbc);
        gbc.gridx = 1;
        txtDescripcion = new JTextField(20);
        panel.add(txtDescripcion, gbc);
        
        // Categoría
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Categoría:"), gbc);
        gbc.gridx = 1;
        comboCategoria = new JComboBox<>();
        List<Categoria> categorias = categoriaDAO.obtenerTodas();
        for (Categoria cat : categorias) {
            comboCategoria.addItem(cat);
        }
        panel.add(comboCategoria, gbc);
        
        // Cantidad
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Cantidad:*"), gbc);
        gbc.gridx = 1;
        txtCantidad = new JTextField(20);
        panel.add(txtCantidad, gbc);
        
        // Precio
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Precio:*"), gbc);
        gbc.gridx = 1;
        txtPrecio = new JTextField(20);
        panel.add(txtPrecio, gbc);
        
        // Precio Compra
        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(new JLabel("Precio Compra:"), gbc);
        gbc.gridx = 1;
        txtPrecioCompra = new JTextField(20);
        panel.add(txtPrecioCompra, gbc);
        
        // Cargar datos si es edición
        if (producto != null) {
            txtNombre.setText(producto.getNombre());
            txtDescripcion.setText(producto.getDescripcion() != null ? producto.getDescripcion() : "");
            if (producto.getCategoria() != null) {
                comboCategoria.setSelectedItem(producto.getCategoria());
            }
            txtCantidad.setText(String.valueOf(producto.getCantidad()));
            txtPrecio.setText(String.valueOf(producto.getPrecio()));
            txtPrecioCompra.setText(String.valueOf(producto.getPrecioCompra()));
        }
        
        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            if (guardarProducto(producto)) {
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
    
    private boolean guardarProducto(Producto productoExistente) {
        // Validaciones
        if (txtNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio", "Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        try {
            Producto producto;
            if (productoExistente == null) {
                producto = new Producto();
            } else {
                producto = productoExistente;
            }
            
            producto.setNombre(txtNombre.getText().trim());
            producto.setDescripcion(txtDescripcion.getText().trim());
            producto.setCategoria((Categoria) comboCategoria.getSelectedItem());
            producto.setCantidad(Integer.parseInt(txtCantidad.getText().trim()));
            producto.setPrecio(Double.parseDouble(txtPrecio.getText().trim()));
            
            String precioCompraText = txtPrecioCompra.getText().trim();
            if (!precioCompraText.isEmpty()) {
                producto.setPrecioCompra(Double.parseDouble(precioCompraText));
            }
            
            boolean exito;
            if (productoExistente == null) {
                exito = productoDAO.crear(producto);
            } else {
                exito = productoDAO.actualizar(producto);
            }
            
            if (exito) {
                JOptionPane.showMessageDialog(this, 
                    productoExistente == null ? "Producto creado exitosamente" : "Producto actualizado exitosamente",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarProductos();
                return true;
            } else {
                // Mensaje de error mejorado
                String mensajeError = "❌ ERROR AL GUARDAR EL PRODUCTO\n\n";
                mensajeError += "⚠️ NO HAY CONEXIÓN A LA BASE DE DATOS MySQL\n\n";
                mensajeError += "Para poder guardar productos necesitas configurar MySQL:\n\n";
                mensajeError += "1️⃣ Instalar MySQL (si no lo tienes)\n";
                mensajeError += "2️⃣ Crear la base de datos:\n";
                mensajeError += "   mysql -u root -p < database/schema.sql\n\n";
                mensajeError += "3️⃣ Configurar credenciales en:\n";
                mensajeError += "   ConexionBD.java\n\n";
                mensajeError += "4️⃣ Agregar MySQL Connector al classpath\n\n";
                mensajeError += "📖 Consulta SOLUCION_ERROR_GUARDAR.md para más detalles.";
                
                JOptionPane.showMessageDialog(this, mensajeError, "Error - Sin Conexión a Base de Datos", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese valores numéricos válidos", "Error de formato", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}


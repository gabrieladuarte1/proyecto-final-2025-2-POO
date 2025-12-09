package co.edu.sigia.gui;
import co.edu.sigia.dao.ProductoDAO;
import co.edu.sigia.dao.CompraDAO;
import co.edu.sigia.dao.VentaDAO;
import co.edu.sigia.modelo.Producto;
import co.edu.sigia.modelo.Compra;
import co.edu.sigia.modelo.Venta;
import co.edu.sigia.util.ExportadorExcel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Panel para la generación de reportes
 */
public class PanelReportes extends JPanel {
    private ProductoDAO productoDAO;
    private CompraDAO compraDAO;
    private VentaDAO ventaDAO;
    private ExportadorExcel exportadorExcel;
    
    public PanelReportes() {
        productoDAO = new ProductoDAO();
        compraDAO = new CompraDAO();
        ventaDAO = new VentaDAO();
        exportadorExcel = new ExportadorExcel();
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 10, 10));
        
        JButton btnReporteProductos = new JButton("Generar Reporte de Productos");
        btnReporteProductos.addActionListener(e -> generarReporteProductos());
        panelBotones.add(btnReporteProductos);
        
        JButton btnReporteCompras = new JButton("Generar Reporte de Compras");
        btnReporteCompras.addActionListener(e -> generarReporteCompras());
        panelBotones.add(btnReporteCompras);
        
        JButton btnReporteVentas = new JButton("Generar Reporte de Ventas");
        btnReporteVentas.addActionListener(e -> generarReporteVentas());
        panelBotones.add(btnReporteVentas);
        
        add(panelBotones, BorderLayout.CENTER);
    }
    
    private void generarReporteProductos() {
        List<Producto> productos = productoDAO.obtenerTodos();
        
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay productos para generar el reporte", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Reporte de Productos");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos Excel", "xlsx"));
        
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            String ruta = fileChooser.getSelectedFile().getAbsolutePath();
            if (!ruta.endsWith(".xlsx")) {
                ruta += ".xlsx";
            }
            
            if (exportadorExcel.exportarProductos(productos, ruta)) {
                JOptionPane.showMessageDialog(this, "Reporte generado exitosamente en: " + ruta, "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error al generar el reporte", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void generarReporteCompras() {
        // Solicitar rango de fechas
        String fechaInicioStr = JOptionPane.showInputDialog(this, "Fecha inicio (YYYY-MM-DD):", LocalDate.now().minusMonths(1).toString());
        if (fechaInicioStr == null) return;
        
        String fechaFinStr = JOptionPane.showInputDialog(this, "Fecha fin (YYYY-MM-DD):", LocalDate.now().toString());
        if (fechaFinStr == null) return;
        
        try {
            LocalDate fechaInicio = LocalDate.parse(fechaInicioStr);
            LocalDate fechaFin = LocalDate.parse(fechaFinStr);
            
            List<Compra> compras = compraDAO.obtenerPorRangoFechas(fechaInicio, fechaFin);
            
            if (compras.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay compras en el rango de fechas especificado", "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar Reporte de Compras");
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos Excel", "xlsx"));
            
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                String ruta = fileChooser.getSelectedFile().getAbsolutePath();
                if (!ruta.endsWith(".xlsx")) {
                    ruta += ".xlsx";
                }
                
                if (exportadorExcel.exportarCompras(compras, ruta)) {
                    JOptionPane.showMessageDialog(this, "Reporte generado exitosamente en: " + ruta, "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Error al generar el reporte", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error en las fechas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void generarReporteVentas() {
        // Solicitar rango de fechas
        String fechaInicioStr = JOptionPane.showInputDialog(this, "Fecha inicio (YYYY-MM-DD):", LocalDate.now().minusMonths(1).toString());
        if (fechaInicioStr == null) return;
        
        String fechaFinStr = JOptionPane.showInputDialog(this, "Fecha fin (YYYY-MM-DD):", LocalDate.now().toString());
        if (fechaFinStr == null) return;
        
        try {
            LocalDate fechaInicio = LocalDate.parse(fechaInicioStr);
            LocalDate fechaFin = LocalDate.parse(fechaFinStr);
            
            List<Venta> ventas = ventaDAO.obtenerPorRangoFechas(fechaInicio, fechaFin);
            
            if (ventas.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay ventas en el rango de fechas especificado", "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar Reporte de Ventas");
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos Excel", "xlsx"));
            
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                String ruta = fileChooser.getSelectedFile().getAbsolutePath();
                if (!ruta.endsWith(".xlsx")) {
                    ruta += ".xlsx";
                }
                
                if (exportadorExcel.exportarVentas(ventas, ruta)) {
                    JOptionPane.showMessageDialog(this, "Reporte generado exitosamente en: " + ruta, "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Error al generar el reporte", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error en las fechas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}



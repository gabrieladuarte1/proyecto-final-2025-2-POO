package co.edu.sigia.gui;
import co.edu.sigia.util.ImportadorExcel;
import javax.swing.*;
import java.awt.*;

/**
 * Panel para la carga masiva de datos desde archivos Excel
 */
public class PanelCargaMasiva extends JPanel {
    private ImportadorExcel importadorExcel;
    
    public PanelCargaMasiva() {
        importadorExcel = new ImportadorExcel();
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 10, 10));
        
        JButton btnCargarProductos = new JButton("Importar Productos desde Excel");
        btnCargarProductos.addActionListener(e -> importarProductos());
        panelBotones.add(btnCargarProductos);
        
        JButton btnCargarCompras = new JButton("Importar Compras desde Excel");
        btnCargarCompras.addActionListener(e -> importarCompras());
        panelBotones.add(btnCargarCompras);
        
        JButton btnCargarVentas = new JButton("Importar Ventas desde Excel");
        btnCargarVentas.addActionListener(e -> importarVentas());
        panelBotones.add(btnCargarVentas);
        
        JLabel lblInfo = new JLabel("<html><center><h3>Nota:</h3><p>Los archivos Excel deben seguir el formato especificado.<br>" +
            "Para más información, consulte la documentación.</p></center></html>", 
            SwingConstants.CENTER);
        
        add(new JLabel("Carga Masiva de Datos", SwingConstants.CENTER), BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);
        add(lblInfo, BorderLayout.SOUTH);
    }
    
    private void importarProductos() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos Excel", "xlsx", "xls"));
        
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            String ruta = fileChooser.getSelectedFile().getAbsolutePath();
            
            int resultado = importadorExcel.importarProductos(ruta);
            if (resultado > 0) {
                JOptionPane.showMessageDialog(this, 
                    "Se importaron " + resultado + " productos exitosamente", 
                    "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else if (resultado == 0) {
                JOptionPane.showMessageDialog(this, 
                    "No se pudieron importar productos. Verifique el formato del archivo.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Error al procesar el archivo", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void importarCompras() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos Excel", "xlsx", "xls"));
        
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            String ruta = fileChooser.getSelectedFile().getAbsolutePath();
            
            int resultado = importadorExcel.importarCompras(ruta);
            if (resultado > 0) {
                JOptionPane.showMessageDialog(this, 
                    "Se importaron " + resultado + " compras exitosamente", 
                    "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else if (resultado == 0) {
                JOptionPane.showMessageDialog(this, 
                    "No se pudieron importar compras. Verifique el formato del archivo.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Error al procesar el archivo", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void importarVentas() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos Excel", "xlsx", "xls"));
        
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            String ruta = fileChooser.getSelectedFile().getAbsolutePath();
            
            int resultado = importadorExcel.importarVentas(ruta);
            if (resultado > 0) {
                JOptionPane.showMessageDialog(this, 
                    "Se importaron " + resultado + " ventas exitosamente", 
                    "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else if (resultado == 0) {
                JOptionPane.showMessageDialog(this, 
                    "No se pudieron importar ventas. Verifique el formato del archivo.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Error al procesar el archivo", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}




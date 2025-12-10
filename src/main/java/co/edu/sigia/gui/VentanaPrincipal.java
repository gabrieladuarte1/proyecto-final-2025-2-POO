package co.edu.sigia.gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ventana principal de la aplicación SIGIA
 * Interfaz gráfica desarrollada con Java Swing
 */
public class VentanaPrincipal extends JFrame {
    private JPanel panelPrincipal;
    private CardLayout cardLayout;
    
    // Paneles de los diferentes módulos
    private PanelProductos panelProductos;
    private PanelCompras panelCompras;
    private PanelVentas panelVentas;
    private PanelReportes panelReportes;
    private PanelCargaMasiva panelCargaMasiva;
    
    public VentanaPrincipal() {
        inicializarComponentes();
        configurarVentana();
    }
    
    private void inicializarComponentes() {
        setTitle("SIGIA - Sistema Integral de Gestión de Inventario AgroStore");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        
        // Crear el layout de tarjetas para cambiar entre paneles
        cardLayout = new CardLayout();
        panelPrincipal = new JPanel(cardLayout);
        
        // Crear los paneles de cada módulo
        panelProductos = new PanelProductos();
        panelCompras = new PanelCompras();
        panelVentas = new PanelVentas();
        panelReportes = new PanelReportes();
        panelCargaMasiva = new PanelCargaMasiva();
        
        // Agregar paneles al card layout
        panelPrincipal.add(panelProductos, "PRODUCTOS");
        panelPrincipal.add(panelCompras, "COMPRAS");
        panelPrincipal.add(panelVentas, "VENTAS");
        panelPrincipal.add(panelReportes, "REPORTES");
        panelPrincipal.add(panelCargaMasiva, "CARGA_MASIVA");
        
        // Crear la barra de menú
        crearBarraMenu();
        
        // Mostrar el panel de productos por defecto
        cardLayout.show(panelPrincipal, "PRODUCTOS");
        
        // Agregar el panel principal a la ventana
        add(panelPrincipal, BorderLayout.CENTER);
    }
    
    private void crearBarraMenu() {
        JMenuBar menuBar = new JMenuBar();
        
        // Menú Principal
        JMenu menuPrincipal = new JMenu("Inicio");
        JMenuItem itemProductos = new JMenuItem("Gestión de Productos");
        itemProductos.addActionListener(e -> cardLayout.show(panelPrincipal, "PRODUCTOS"));
        menuPrincipal.add(itemProductos);
        
        // Menú Compras
        JMenu menuCompras = new JMenu("Compras");
        JMenuItem itemCompras = new JMenuItem("Gestión de Compras");
        itemCompras.addActionListener(e -> cardLayout.show(panelPrincipal, "COMPRAS"));
        menuCompras.add(itemCompras);
        
        // Menú Ventas
        JMenu menuVentas = new JMenu("Ventas");
        JMenuItem itemVentas = new JMenuItem("Gestión de Ventas");
        itemVentas.addActionListener(e -> cardLayout.show(panelPrincipal, "VENTAS"));
        menuVentas.add(itemVentas);
        
        // Menú Reportes
        JMenu menuReportes = new JMenu("Reportes");
        JMenuItem itemReportes = new JMenuItem("Generar Reportes");
        itemReportes.addActionListener(e -> cardLayout.show(panelPrincipal, "REPORTES"));
        menuReportes.add(itemReportes);
        
        // Menú Utilidades
        JMenu menuUtilidades = new JMenu("Utilidades");
        JMenuItem itemCargaMasiva = new JMenuItem("Carga Masiva de Datos");
        itemCargaMasiva.addActionListener(e -> cardLayout.show(panelPrincipal, "CARGA_MASIVA"));
        menuUtilidades.add(itemCargaMasiva);
        
        // Menú Ayuda
        JMenu menuAyuda = new JMenu("Ayuda");
        JMenuItem itemAcerca = new JMenuItem("Acerca de");
        itemAcerca.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                "SIGIA - Sistema Integral de Gestión de Inventario\n" +
                "Versión 1.0\n" +
                "Desarrollado para AgroStore\n\n" +
                "Gestión de productos, compras y ventas de insumos agrícolas.",
                "Acerca de SIGIA",
                JOptionPane.INFORMATION_MESSAGE);
        });
        menuAyuda.add(itemAcerca);
        
        // Agregar menús a la barra
        menuBar.add(menuPrincipal);
        menuBar.add(menuCompras);
        menuBar.add(menuVentas);
        menuBar.add(menuReportes);
        menuBar.add(menuUtilidades);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(menuAyuda);
        
        setJMenuBar(menuBar);
    }
    
    private void configurarVentana() {
        setVisible(true);
    }
    
    public static void main(String[] args) {
        // Establecer el Look and Feel del sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Ejecutar la aplicación en el Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal();
        });
    }
}




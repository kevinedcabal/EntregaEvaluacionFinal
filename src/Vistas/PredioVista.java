package Vistas;

import Modelos.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class PredioVista extends JFrame {
    private JComboBox<String> cbFiltro;
    private JTextField txtCronometro,txtBusqueda;
    private Label lblCronometro, lblBusqueda;
    private JButton btnBuscar;
    private JTable tablaPredios;
    private DefaultTableModel modeloTabla;

    public PredioVista() {
        setTitle("Ordenamiento Predios");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new BorderLayout());

        JPanel pnlSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        
        cbFiltro = new JComboBox<>(new String[]{"NPN", "Municipio", "Direccion", "Ficha"});
        
        txtCronometro = new JTextField("00:00:00.000");
        txtCronometro.setEditable(false);
        txtCronometro.setPreferredSize(new Dimension(100, 25));

        btnBuscar = new JButton("\uD83D\uDD0D"); 
        btnBuscar.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
        txtBusqueda = new JTextField(20);
        lblCronometro = new Label("Cronómetro:");
        lblBusqueda = new Label("Búsqueda:");

        pnlSuperior.add(lblBusqueda);
        pnlSuperior.add(cbFiltro);
        pnlSuperior.add(txtBusqueda);
        pnlSuperior.add(btnBuscar);
        pnlSuperior.add(lblCronometro);
        pnlSuperior.add(txtCronometro);

        add(pnlSuperior, BorderLayout.NORTH);

        String[] columnas = {"#", "NPN", "Municipio", "Dirección", "Ficha"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        
        tablaPredios = new JTable(modeloTabla);
        tablaPredios.getColumnModel().getColumn(0).setPreferredWidth(50);
        tablaPredios.getColumnModel().getColumn(1).setPreferredWidth(250);
        
        JScrollPane scrollPane = new JScrollPane(tablaPredios);
        add(scrollPane, BorderLayout.CENTER);
    }


    public String getColumnaSeleccionada() {
        return cbFiltro.getSelectedItem().toString();
    }

    public String getCriterioBusqueda() {
        return txtBusqueda.getText().trim();
    }

    public void setCronometro(String tiempo) {
        txtCronometro.setText(tiempo);
    }

    public void agregarListenerBotonBuscar(ActionListener listener) {
        btnBuscar.addActionListener(listener);
        txtBusqueda.addActionListener(listener); 
    }

    public void actualizarTabla(List<Predio> lista) {
        modeloTabla.setRowCount(0); 
        int index = 1;
        for (Predio prd : lista) {
            modeloTabla.addRow(new Object[]{
                index++,
                prd.getNpn(),
                prd.getMunicipio(),
                prd.getDireccion(),
                prd.getFicha()
            });
        }
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
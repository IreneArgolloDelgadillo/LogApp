/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logapp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ireene
 */
public class PanelDeFormularios extends JPanel {

    private final ConectorBD bdConector;

    private final int rol;
    private final JTabbedPane formularios;
    private JButton cerrarSecion;

    private PanelDeFormularios(ConectorBD conectorBD, int rol) {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        bdConector = conectorBD;
        this.rol = rol;
        formularios = new JTabbedPane();
        cargarFormularios();
        this.add(formularios, BorderLayout.CENTER);
    }

    private void cargarFormularios() {
        String tabTitle;

        if (rol == 1) {
            tabTitle = "plantel empleados ";
            JPanel funcionPanel = obtenerFormularioDeSalario(bdConector.getHistorialDeSueldosDeEmpleados(), tabTitle);
            funcionPanel.add(new PanelDeFunciones(bdConector));
            formularios.add(funcionPanel);
        }
        tabTitle = "tu historial de salario";
        formularios.add(tabTitle, obtenerFormularioDeSalario(bdConector.getHistorialDeSalario(), tabTitle));
    }

    private JPanel obtenerFormularioDeSalario(List<Salario> bdSalario, String title) {
        JPanel formularyContent = new JPanel();
        formularyContent.setLayout(new BoxLayout(formularyContent, BoxLayout.PAGE_AXIS));

        Object[] header = {"ci empleado", "Fecha", "descuentos", "bonos", "total"};
        DefaultTableModel tableModel = new DefaultTableModel(header, 0);
        recharge(bdSalario, tableModel);
        tableModel.insertRow(0, header);
        JTable table = new JTable(tableModel);

        formularyContent.add(table);
        return formularyContent;
    }

    private void recharge(List<Salario> bdSalario, DefaultTableModel tableModel) {

        int rowSize = bdSalario.size();
        for (int index = 0; index < rowSize; index++) {
            Salario actual = bdSalario.get(index);
            Object ci = actual.getCi_usuario();
            Object fecha = actual.getFecha_salario();
            Object descuentos = actual.getDescuentos();
            Object bonos = actual.getBonos();
            Object total = actual.getTotal();
            Object[] rowdates = {ci, fecha, descuentos, bonos, total};
            tableModel.addRow(rowdates);
        }
    }

    public static JFrame getInstance(ConectorBD c, int id_rol) {
        JFrame window = new JFrame();
        JPanel contenedorDeFormularios = new PanelDeFormularios(c, id_rol);
        contenedorDeFormularios.setBorder(BorderFactory.createMatteBorder(20, 20, 20, 20, Color.BLUE));

        JMenuBar menuBarClose = new JMenuBar();
        JMenuItem salir = new JMenuItem("Salir");
        salir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c.registrarSecion(false, -1, c.getUser());
                System.exit(0);
            }
        });
        JMenu opciones = new JMenu("Secion");
        opciones.add(salir);
        menuBarClose.add(opciones);
        window.setJMenuBar(menuBarClose);
        window.add(contenedorDeFormularios);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setLocationRelativeTo(null);
        return window;
    }
}

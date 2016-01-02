/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

/**
 *
 * @author ireene
 */
public class IntroducirMasDetalle extends JDialog {

    private final JTextField textDate;
    private final JTextField id_usuario;
    private final JTextField panelDeDescunetos;
    private final JTextField panelDeBonos;
    private final JTextField panelTotal;
    private final JButton save;
    private final ConectorBD conector;

    public IntroducirMasDetalle(ConectorBD conector) {
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.conector = conector;
        textDate = new JTextField(20);
        id_usuario = new JTextField("Ci Empleado");
        panelDeDescunetos = new JTextField("Total Descuentos");
        panelDeBonos = new JTextField("Total Bonos");
        panelTotal = new JTextField("total Sueldo");
        save = new JButton("Save");
        iniciar();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
    }
//conecciones persistentes

    private void iniciar() {

        add(textDate);
        add(id_usuario);
        add(panelDeDescunetos);
        add(panelDeBonos);
        add(panelTotal);
        add(save);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conector.editarSalarios(getId_usuario(), getTextDate(),
                                                            getPanelDeBonos(), getPanelDeDescunetos(), getPanelTotal());
                
                
            }

        });
    }

    public Date getTextDate() {
        return Date.valueOf(textDate.getText());//yyyy-mm-dd
    }

    public int getId_usuario() {
        return Integer.parseInt(id_usuario.getText());
    }

    public int getPanelDeDescunetos() {
        return Integer.parseInt(panelDeDescunetos.getText());
    }

    public int getPanelDeBonos() {
        return Integer.parseInt(panelDeBonos.getText());
    }

    public int getPanelTotal() {
        return Integer.parseInt(panelTotal.getText());
    }

}

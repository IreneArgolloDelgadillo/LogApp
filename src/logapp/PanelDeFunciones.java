/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 *
 * @author ireene
 */
public class PanelDeFunciones extends JPanel {

    private final JDialog enter;
    private final JButton agregar;

    public PanelDeFunciones(ConectorBD conector) {
        enter = new IntroducirMasDetalle(conector);
        agregar = new JButton("Agregar Detalle");
        add(agregar);
        agregarFuncion();
    }

    public final void agregarFuncion() {
        agregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enter.setVisible(true);
            }
        });
    }
}

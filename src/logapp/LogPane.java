/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logapp;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Irene Argollo
 */
public class LogPane extends JPanel {

    private JPasswordField passwordText;
    private JTextField userNameText;

    public LogPane(JFrame content) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JLabel iconLabel = new JLabel(new ImageIcon("log.jpg"));

        JPanel userDatesPane = new JPanel(new GridLayout(0, 1));
        userNameText = new JTextField(20);
        passwordText = new JPasswordField(20);

        userDatesPane.add(new JLabel("User Name :"));
        userDatesPane.add(userNameText);
        userDatesPane.add(new JLabel("Password :"));
        userDatesPane.add(passwordText);

        JButton entryButton = new JButton("Ingresar");

        userDatesPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(iconLabel);
        add(userDatesPane);
        add(entryButton);

        entryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ConectorBD c = new ConectorBD();
                String userName = userNameText.getText();
                String userPassword = String.valueOf(passwordText.getPassword());
                int id_rol = c.autenticarUsuario(userName, userPassword);
                if (id_rol != -1) {
                    content.setVisible(false);
                    JFrame window = PanelDeFormularios.getInstance(c , id_rol);

                    JMenuBar menuBarClose = new JMenuBar();
                    JMenuItem salir = new JMenuItem("Salir");
                    salir.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            c.desconectar();
                            System.exit(0);
                        }
                    });
                    JMenu opciones = new JMenu("Secion");
                    opciones.add(salir);
                    menuBarClose.add(opciones);
                    window.setJMenuBar(menuBarClose);
                    window.setVisible(true);
                }
            }
        });
    }

}

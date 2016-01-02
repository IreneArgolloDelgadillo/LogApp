/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logapp;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author ireene
 */
public class LogApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> {
           JFrame window = new JFrame();
            LogPane logAppPane = new LogPane(window);
            logAppPane.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
           window.add(logAppPane);
           window.setResizable(false);
           window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           window.setLocationRelativeTo(null);
           window.pack();
           window.setVisible(true);
        });
    }
    
}

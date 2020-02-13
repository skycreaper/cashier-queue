/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.Container;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Estudiantes
 */
public class Ventana extends JFrame{

    JLabel lblTitulo = new JLabel("SIMULACION DE CAJERO");
    public Ventana() {
        Container c = getContentPane();
        c.setLayout(null);
        this.setTitle("");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(lblTitulo);
        lblTitulo.setBounds(50, 50, 250, 25);
        
        setSize(500, 500);
        setVisible(true);
    }
    
    
}

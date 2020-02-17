/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Estudiantes
 */
public class Ventana extends JFrame{
    public Color c1 = new Color(72, 52, 212);
    public Color c2 = new Color(104, 109, 224);
    public Color c3 = new Color(223, 249, 251);
    public Color c4 = new Color(48, 51, 107); 
    public Font font = new Font("Agency FB", Font.BOLD, 34);
    public Font font2 = new Font("Agency FB", Font.BOLD, 20);
    public JPanel header = new JPanel();
    public JPanel content = new JPanel();
    public JButton btnStart = new JButton();
    public JLabel lblCashier = new JLabel();
    public JLabel lblClient = new JLabel();
    public JLabel lblClientCounter = new JLabel();
    public JLabel lblTitulo = new JLabel("SIMULACION DE CAJERO");
    public JLabel lblAutorD = new JLabel("DAVID STEVEN SANTOS SANTOS");
    public JLabel lblAutorJ = new JLabel("JUAN CAMILO SARMIENTO REYES");
    
    
    
    public Ventana() {
        Container c = getContentPane();
        c.setLayout(null);
        this.setTitle("FIRST COME FIRST SERVED");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(lblTitulo);                             //Label del titulo y sus propiedades respectivas
        lblTitulo.setBounds(420, 50, 1000, 50);
        lblTitulo.setFont(font);
        add(lblAutorD);                             //Label para autor y sus propiedades respectivas
        lblAutorD.setBounds(970, 5, 300, 30);
        lblAutorD.setFont(font2);
        lblAutorD.setForeground(c3);
        add(lblAutorJ);                             //Label para autor y sus propiedades respectivas
        lblAutorJ.setBounds(970, 28, 300, 30);
        lblAutorJ.setFont(font2);
        lblAutorJ.setForeground(c3);
        add(lblCashier);                            //Label para el cajero y sus propiedades respectivas
        lblCashier.setBounds(50, 350, 50, 35);
        lblCashier.setFont(font);
        add(lblClient);
        lblClient.setBounds(150, 350, 350, 35);
        lblClient.setFont(font);
        add(lblClientCounter);                      //Label provisional que muestra la cantidad de clientes que ingresan
        lblClientCounter.setBounds(830, 230, 150, 35);
        lblClientCounter.setFont(font2);
        add(header);                                //Panel para el encabezado y sus propiedades respectivas
        header.setBounds(0, 0, 1200, 150);
        header.setBackground(c1);
        add(content);                               //Panel para el conenido y sus propiedades respectivas
        content.setBounds(0, 150, 1200, 650);
        content.setBackground(c2);
        add(btnStart);                              //Boton para dar inicio a la ejecucion y sus propiedades respectivas
        btnStart.setBounds(870, 170, 150, 45);
        btnStart.setBackground(c4);
        btnStart.setForeground(c3);
        btnStart.setFont(font2);
        btnStart.setText("INICIAR");
        btnStart.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        setSize(1200, 800);
        setVisible(true);
    }
}

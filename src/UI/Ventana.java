/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import edu.co.cashierqueue.logic.Queue;
import edu.co.cashierqueue.models.Node;
import javax.swing.SwingConstants;

/**
 *
 * @author Estudiantes
 */
public class Ventana extends JFrame{
    
    public Color c1 = new Color(72, 52, 212); // Bien morado fuerte
    public Color c2 = new Color(104, 109, 224); // Bien morado ligero
    public Color c3 = new Color(223, 249, 251); // Como un gris wn
    public Color c4 = new Color(48, 51, 107);  // Azul grisaceo
    public Font font = new Font("Agency FB", Font.BOLD, 34);
    public Font font2 = new Font("Agency FB", Font.BOLD, 20);
    
    public JPanel header = new JPanel();
    public JPanel subHeader = new JPanel();
    public JPanel footer = new JPanel();
    public JPanel content = new JPanel();
    
    public JButton btnStart = new JButton();
    public JLabel lblCashier = new JLabel();
    public JLabel lblClient = new JLabel();
    public JLabel lblClientCounter = new JLabel();
    public JLabel lblTitulo = new JLabel("SIMULACION DE CAJERO");
    public JLabel lblAutorD = new JLabel("DAVID STEVEN SANTOS SANTOS");
    public JLabel lblAutorJ = new JLabel("JUAN CAMILO SARMIENTO REYES");
    
    public HashMap<String, JLabel> nodeLabels;
    
    int screenWidth = 1024;
    int screenHeight = 720;
    
    public Ventana() {
        Container c = getContentPane();
        c.setLayout(null);
        this.setTitle("FIRST COME FIRST SERVED");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        NodeUI node = new NodeUI();
        
        drawHeader();
        drawSubHeader();
        
        add(lblClient);
        lblClient.setBounds(150, 350, 350, 35);
        lblClient.setFont(font);
        
        
        add(content);                               //Panel para el conenido y sus propiedades respectivas
        content.setBounds(0, 200, screenWidth, 400);
        content.setBackground(c2);
        
        content.setBorder(BorderFactory.createLineBorder(Color.black));
        
        node.setBounds(10, 100, 200, 200);
        
        content.setLayout(null);
        
        subHeader.add(btnStart);                              //Boton para dar inicio a la ejecucion y sus propiedades respectivas
        btnStart.setBounds((screenWidth/2)-(150/2), 10, 150, 45);
        btnStart.setBackground(c4);
        btnStart.setForeground(c3);
        btnStart.setFont(font2);
        btnStart.setText("INICIAR");
        
        setSize(screenWidth, screenHeight);
        setVisible(true);
    }
    
    public void drawsNodes(Node cashierNode) {
        JLabel nodeLabel, nodeConection;
        content.removeAll();
        //content.revalidate();
        content.repaint();
        this.nodeLabels = new HashMap<>();
        
        content.add(lblClientCounter);                      //Label provisional que muestra la cantidad de clientes que ingresan
        lblClientCounter.setBounds(800, 10, 150, 35);
        lblClientCounter.setFont(font2);
        
        int init = 62;
        int nodeWidth = 120;
        int nodeHeight = 50;
        
        Node _node = cashierNode;
        do {
            nodeLabel = new JLabel("<html>"+_node.getName()+
                    "<br>Recibos: "+_node.getReceipts()+"</html>", SwingConstants.CENTER);
            nodeLabel.setName(_node.getType());
            content.add(nodeLabel);
            nodeLabel.setBounds(init, content.getHeight()/2, nodeWidth, nodeHeight);
            if (_node.getNext() != cashierNode) {
                nodeConection = new JLabel();
                content.add(nodeConection);
                nodeConection.setText("<-");
                nodeConection.setFont(font2);
                nodeConection.setBounds((init+nodeWidth), content.getHeight()/2+13, 40, 20);
            }
                             
            nodeLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            this.nodeLabels.put(_node.getType(), nodeLabel);
               
            init += nodeWidth+40;
            
            _node = _node.getNext();
        } while(_node != cashierNode);
    }
    
    private void drawHeader() {
        add(header);                                //Panel para el encabezado y sus propiedades respectivas
        header.setBounds(0, 0, screenWidth, 150);
        header.setBackground(c1);
        
        header.add(lblTitulo);                             //Label del titulo y sus propiedades respectivas
        lblTitulo.setBounds(420, 50, 1000, 50);
        lblTitulo.setFont(font);
    }
    
    private void drawSubHeader() {
        add(subHeader);                                //Panel para el encabezado y sus propiedades respectivas
        subHeader.setBounds(0, 150, screenWidth, 50);
        subHeader.setBackground(c1);
        
        subHeader.add(lblAutorD);                             //Label para autor y sus propiedades respectivas
        lblAutorD.setBounds(970, 5, 300, 30);
        lblAutorD.setFont(font2);
        lblAutorD.setForeground(c3);
        
        subHeader.add(lblAutorJ);                             //Label para autor y sus propiedades respectivas
        lblAutorJ.setBounds(970, 28, 300, 30);
        lblAutorJ.setFont(font2);
        lblAutorJ.setForeground(c3);
    }
}

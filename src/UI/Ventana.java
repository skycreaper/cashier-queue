package UI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import edu.co.cashierqueue.models.Node;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

/**
 *
 * @author skcreaper
 */
public class Ventana extends JFrame{
    
    public Color c1 = new Color(72, 52, 212); // Bien morado fuerte
    public Color c2 = new Color(104, 109, 224); // Bien morado ligero
    public Color c3 = new Color(223, 249, 251); // Como un gris wn
    public Color c4 = new Color(48, 51, 107);  // Azul grisaceo
    public Color c5 = new Color(43, 203, 186);  // ?
    
    public Font font = new Font("Agency FB", Font.BOLD, 34);
    public Font font2 = new Font("Agency FB", Font.BOLD, 20);
    
    public JPanel header = new JPanel();
    public JPanel subHeader = new JPanel();
    public JPanel buttonsPanel = new JPanel();
    public JPanel content = new JPanel();
    
    public JButton btnStart = new JButton();
    public JButton btnIcon = new JButton();

    
    public JLabel lblCashier = new JLabel();
    public JLabel lblClient = new JLabel();
    public JLabel lblClientCounter = new JLabel();
    public JLabel lblTitulo = new JLabel("SIMULACION DE CAJERO");
    public JLabel lblAutorD = new JLabel("<html>DAVID STEVEN SANTOS SANTOS<br>"+
            "JUAN CAMILO SARMIENTO REYES</html>");
    
    public HashMap<String, JLabel> nodeLabels;
    
    int screenWidth = 1024;
    int screenHeight = 600;
    
    public Ventana() {
        Container c = getContentPane();
        c.setLayout(null);
        this.setTitle("FIRST COME FIRST SERVED");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        NodeUI node = new NodeUI();
        
        drawHeader();
        drawSubHeader();
        drawButtons();
        
        add(content);                               //Panel para el conenido y sus propiedades respectivas
        content.setBounds(0, 250, screenWidth, 600);
        content.setBackground(c2);
        
        content.setBorder(BorderFactory.createLineBorder(Color.black));
        
        node.setBounds(10, 100, 200, 200);
        
        content.setLayout(null);
        
        super.setSize(screenWidth, screenHeight);
        super.setVisible(true);
        super.setResizable(false);
    }
    
    public void testDraw(Node cashierNode) {
        
        JLabel nodeLabel, nodeConection;
        content.removeAll();
        content.repaint();
        
        int init = 62;
        int nodeWidth = 120;
        int nodeHeight = 50;
        
        Node _node = cashierNode;
        do {
            nodeLabel = new JLabel("<html>"+_node.getName()+
                    "<br>Recibos: "+_node.getReceipts()+"</html>", SwingConstants.CENTER);
            nodeLabel.setName(_node.getType());
            paintNode(_node.getType(), nodeLabel);
            content.add(nodeLabel);
            nodeLabel.setBounds(init, content.getHeight()/2-200, nodeWidth, nodeHeight);
            
            if (_node.getNext() != cashierNode) {
                nodeConection = new JLabel();
                content.add(nodeConection);
                nodeConection.setText("<-");
                nodeConection.setFont(font2);
                nodeConection.setBounds((init+nodeWidth), content.getHeight()/2+13-200, 40, 20);
            }
                             
            nodeLabel.setBorder(BorderFactory.createLineBorder(Color.black));
               
            init += nodeWidth+40;
            
            _node = _node.getNext();
        } while(_node != cashierNode);
    }
    
    private void paintNode(String type, JLabel nodeLabel) {
        nodeLabel.setOpaque(true);
        switch(type) {
                case "C":
                    nodeLabel.setBackground(Color.WHITE);
                    break;
                case "c1":
                    nodeLabel.setBackground(Color.yellow);
                    break;
                case "c2":
                    nodeLabel.setBackground(Color.PINK);
                    break;
                case "c3":
                    nodeLabel.setBackground(Color.cyan);
                    break;
                case "c4":
                    nodeLabel.setBackground(Color.magenta);
                    break;
                default:
                    nodeLabel.setBackground(Color.orange);
                break;
            }
    }
    
    public void drawsNodes(Node cashierNode) {
        JLabel nodeLabel, nodeConection;
        content.removeAll();
        content.repaint();
        nodeLabels = new HashMap<>();
        
        
        int init = 62;
        int nodeWidth = 120;
        int nodeHeight = 50;
        
        Node _node = cashierNode;
        do {
            nodeLabel = new JLabel("<html>"+_node.getName()+
                    "<br>Recibos: "+_node.getReceipts()+"</html>", SwingConstants.CENTER);
            nodeLabel.setName(_node.getType());
            
            paintNode(_node.getType(), nodeLabel);
            
            content.add(nodeLabel);
            nodeLabel.setBounds(init, content.getHeight()/2-200, nodeWidth, nodeHeight);
            
            
            
            if (_node.getNext() != cashierNode) {
                nodeConection = new JLabel();
                content.add(nodeConection);
                nodeConection.setText("<-");
                nodeConection.setFont(font2);
                nodeConection.setBounds((init+nodeWidth), content.getHeight()/2+13-200, 40, 20);
            }
                             
            nodeLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            nodeLabels.put(_node.getType(), nodeLabel);
               
            init += nodeWidth+40;
            
            _node = _node.getNext();
        } while(_node != cashierNode);
    }
    
    private void drawHeader() {
        
        add(btnIcon);
        btnIcon.setBounds(0,0,200,200);
        Icon icon = new ImageIcon("ud.png");
        btnIcon.setIcon(icon);
        
        add(header);                                //Panel para el encabezado y sus propiedades respectivas
        header.setBounds(0, 0, screenWidth, 100);
        header.setBackground(c1);
        header.setLayout(null);
        
        header.add(lblTitulo);                             //Label del titulo y sus propiedades respectivas
        lblTitulo.setBounds(300, 10, 1000, 100);
        lblTitulo.setFont(font);
    }
    
    private void drawSubHeader() {
        add(subHeader);                                //Panel para el encabezado y sus propiedades respectivas
        subHeader.setBounds(0, 100, screenWidth, 100);
        subHeader.setBackground(c1);
        
        subHeader.add(lblAutorD);                             //Label para autor y sus propiedades respectivas
        lblAutorD.setBounds(970, 5, 300, 30);
        lblAutorD.setFont(font2);
        lblAutorD.setForeground(c3);
    }
    
    private void drawButtons() {
        add(buttonsPanel);
        buttonsPanel.setBounds(0, 200, screenWidth, 70);
        buttonsPanel.setBackground(c1);
        buttonsPanel.setLayout(null);
        
        buttonsPanel.add(btnStart);                              //Boton para dar inicio a la ejecucion y sus propiedades respectivas
        btnStart.setBounds(screenWidth/2-100, 0, 150, 45);
        btnStart.setBackground(c5);
        btnStart.setForeground(c3);
        btnStart.setFont(font2);
        btnStart.setBorder(new RoundedBorder(8));
        btnStart.setText("INICIAR");
        
        buttonsPanel.add(lblClientCounter);                      //Label provisional que muestra la cantidad de clientes que ingresan
        lblClientCounter.setBounds(700, 10, 150, 35);
        lblClientCounter.setFont(font2);
        lblClientCounter.setForeground(c3);
    }
}

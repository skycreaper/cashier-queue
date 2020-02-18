package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/**
 *
 * @author juancamilo
 */
public class NodeUI  extends JComponent {
    @Override    
    public void paint(Graphics g) {
        g.drawRect(0, 0, 10, 1);
        //g.setColor(Color.red);
    }
//    @Override
//  public void paintComponent(Graphics g) {
//    super.paintComponent(g);
//    Graphics2D g2d = (Graphics2D) g;
//
//    g2d.setColor(new Color(212, 212, 212));
//    g2d.drawRect(10, 15, 90, 60);
//
//
//    g2d.setColor(new Color(31, 21, 1));
//    g2d.fillRect(250, 195, 90, 60);
//
//  }
}

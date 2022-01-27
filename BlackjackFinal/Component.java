import javax.swing.JFrame;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Color;
/**
 * Component.java  
 *
 * @author: Noah, Vik, and Joel
 * Assignment #: Graphics
 * 
 * Brief Program Description:
 * Graphics for blackjack
 *
 */
public class Component extends JComponent
{
    public static void main(String [] args)
    {
        JFrame frame = new JFrame();
        Component component = new Component();
        frame.add(component);
        frame.setVisible(true);
        frame.setSize(500, 500);
        
        Dealer game= new Dealer(100,"Dealer");
        game.start();
        
    }
    public void paintComponent(Graphics g)
    {
        
    }
}

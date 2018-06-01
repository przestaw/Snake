/*
 * First app used to learn the very basic of java programming
 * language.
 * ~przestaw
 */

package firtstapp;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

/**
 *
 * @author przemek
 */


public class FirtstApp 
{
    static JFrame window;
    static int sizeX = 600;
    static int sizeY = 400;
    static int screenHeight;
    static int screenWidth;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        screenHeight = screenSize.height;
        screenWidth = screenSize.width;
        
        window = new JFrame("Snake");
        window.setSize(sizeX, sizeY+26);
        window.setLocation((screenWidth-sizeX)/2,(screenHeight-sizeY)/2);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        window.setVisible(true);
        
        Game game = new Game(sizeX, sizeY);
        
        window.add(game);
    }
}
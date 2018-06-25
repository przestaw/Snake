/*
 * First app used to learn the very basic of java programming
 * language.
 * ~przestaw
 */

package firtstapp;

import firtstapp.Controller.GameControl;
import firtstapp.Model.GamePlay;
import firtstapp.View.GamePlayView;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 *
 * @author przemek
 */


public class FirtstApp 
{
    static JFrame window;
    static int sizeX = 600;
    static int sizeY = 600;
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
        
        //??
        
        GamePlay game = new GamePlay(sizeX/10, sizeY/10);
        GameControl gameControl = new GameControl(game, 300, 5);
        GamePlayView gameView = new GamePlayView(game, 10);
        
        game.addObserver(gameView);
        
        window.add(gameView);
        
    }
}
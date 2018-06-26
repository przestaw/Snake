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
    static int sizeX = 900;
    static int sizeY = 900;
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
        
        GamePlay game = new GamePlay(sizeX/30, sizeY/30, 300, 5);
        GameControl gameControl = new GameControl(game);
        GamePlayView gameView = new GamePlayView(game, gameControl, 30);
        
        game.addObserver(gameView);
        
        gameView.repaint();
        
        window.add(gameView);
    }
}
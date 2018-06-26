/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firtstapp.View;

import firtstapp.Controller.GameControl;
import firtstapp.Model.GameObject;
import firtstapp.Model.GamePlay;
import firtstapp.Model.SnakeBlock;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author przemek
 */
public class GamePlayView extends JPanel implements Observer, ActionListener, KeyListener
{
    private final GamePlay game;
    
    private final GameControl gameControl;
    
    private Timer timer;
    
    
    private final int sizeX;
    private final int sizeY;
    
    private final int pixSize;
    
    public GamePlayView(GamePlay game, GameControl gameControl, int pixSize) 
    {
        this.sizeX = game.getSizeX();
        this.sizeY = game.getSizeY();
        
        this.pixSize = pixSize;
        
        this.gameControl = gameControl;
        addKeyListener(this);
        
        this.timer = new Timer(game.getDelay(), this);
        
        this.game = game;
        
        game.addObserver(this);
        timer.start();
        
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }
    
    @Override
    public void paint(Graphics g)
    {
        
        //super.paint(g);
        //backgound
        g.setColor(new Color(230, 230, 240));
        g.fillRect(0, 0, sizeX*pixSize, sizeY*pixSize);
        
        //cake
        g.setColor(new Color(250,69,0));
        g.fillOval(game.getCakePosX()*pixSize, game.getCakePosY()*pixSize, pixSize, pixSize);
        
        //snake
        g.setColor(new Color(0, 200, 30));
        SnakeBlock temp;
        for(int i = 0; i < game.getSnakeLenght() ; ++i)
        {
            temp = game.getSnakeBlock(i);
            g.fillRect((int)(temp.getPosX()+0.1)*pixSize, (int)(temp.getPosY()+0.1)*pixSize, (int)(pixSize*0.8), (int)(pixSize*0.8));     
        }
        
        //snake-head
        g.setColor(new Color(230, 20, 230));
        g.fillRect((int)(game.getSnakePosX()+0.09)*pixSize, (int)(game.getSnakePosY()+0.09)*pixSize, (int)(pixSize*0.82), (int)(pixSize*0.82));
            //eyes?
        
        //borders
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, sizeX*pixSize, pixSize);
        g.fillRect(0, 0, pixSize, sizeY*pixSize);
        g.fillRect(0, sizeY*pixSize-pixSize, sizeX*pixSize, pixSize);
        g.fillRect(sizeX*pixSize-pixSize, 0, pixSize, sizeY*pixSize);
        
        //Text: score
        g.setColor(new Color(0, 0, 200));
        g.setFont(new Font("Consolans", Font.BOLD, 2*pixSize));
        g.drawString("Score:"+game.getScore(), (int)(pixSize*1.5), (pixSize*3));        
            //draw prees enter to play
        if(game.isPlay() == false)
        {
            g.setColor(new Color(0, 0, 200));
            g.setFont(new Font("Consolans", Font.BOLD, 2*pixSize));
            g.drawString("Press Enter to Play", (sizeX/2 -11)*pixSize, (sizeY/2 - 3)*pixSize);
        }
        //
        if(game.isGameover() == true)
        {
            g.setColor(new Color(255,15,40));
            g.setFont(new Font("Consolans", Font.BOLD, 2*pixSize));
            g.drawString("GameOver", (sizeX/2 -6)*pixSize, (sizeY/2 - 5)*pixSize);
        }
        
        g.dispose();
    }

    @Override
    public void update(Observable o, Object o1) 
    {
        timer.setDelay(game.getDelay());
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        gameControl.update();
    }

    @Override
    public void keyTyped(KeyEvent ke) 
    {
        //nothing to do
    }

    @Override
    public void keyPressed(KeyEvent ke) 
    {
        switch(ke.getKeyCode())
        {
            case KeyEvent.VK_UP:
                gameControl.directionOccured(GameObject.Direction.up);
                break;
            case KeyEvent.VK_DOWN:
                gameControl.directionOccured(GameObject.Direction.down);
                break;
            case KeyEvent.VK_RIGHT:
                gameControl.directionOccured(GameObject.Direction.right);
                break;
            case KeyEvent.VK_LEFT:
                gameControl.directionOccured(GameObject.Direction.left);
                break;
            case KeyEvent.VK_ENTER:
                gameControl.enterOccured();
                break;
            case KeyEvent.VK_ESCAPE:
                gameControl.escapeOccured();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) 
    {
        //nothing to do
    }
    
}

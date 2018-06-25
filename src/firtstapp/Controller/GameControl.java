/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firtstapp.Controller;

import firtstapp.Model.GameObject;
import firtstapp.Model.GamePlay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Timer;

/**
 *
 * @author przemek
 */
public class GameControl implements Observer, ActionListener, KeyListener
{
    private Timer timer;
    
    private int delay;
    private static int startDelay;
    private static int rampUp;
    
    private GamePlay game;
    
    public GameControl(GamePlay game, int startDelay, int rampUp) 
    {
        this.delay = startDelay;
        this.startDelay = this.delay;
        this.rampUp = rampUp;
        this.timer = new Timer(delay, this);
        
        this.game = game;
        
        timer.start();
        
    }
    
    public void rampUpTime()
    {
        if(delay > rampUp+1)
        {
            delay -= rampUp;
            timer.setDelay(delay);
        }
    }    
    
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        if(game.isPlay())
        {
            if(game.isMovePossible() )
            {
                game.moveSnake();
            }else
            {
                game.gameover();
            }
        }
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
                game.setSnakeDirection(GameObject.Direction.up);
                break;
            case KeyEvent.VK_DOWN:
                game.setSnakeDirection(GameObject.Direction.down);
                break;
            case KeyEvent.VK_RIGHT:
                game.setSnakeDirection(GameObject.Direction.right);
                break;
            case KeyEvent.VK_LEFT:
                game.setSnakeDirection(GameObject.Direction.left);
                break;
            case KeyEvent.VK_ENTER:
                if(game.isGameover())
                {
                    game.restart();
                }else
                {
                    game.start();
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) 
    {
        //nothing to do 
    }

    @Override
    public void update(Observable o, Object o1) 
    {
        
    }
}

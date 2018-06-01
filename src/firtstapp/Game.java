/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firtstapp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author przemek
 */
public class Game extends  JPanel implements KeyListener, ActionListener
{
    static int sizeX;
    static int sizeY;
    
    private boolean play;
    private int score;
    
    private Timer timer;
    
    private static int startDelay = 350;
    private int delay;
    
    private int snakePosX;
    private int snakePosY;
    private enum Direction{up, down, left, right};
    private Direction snakeDirection = Direction.up;
    private int[] tailX;
    private int[] tailY;
    private int snakeLenght;
    
    private int cakePosX = 12;
    private int cakePosY = 12;
    
    Game(int sizeX, int sizeY)
    {
        this.sizeX = sizeX/10;
        this.sizeY = sizeY/10;
        
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
        timer = new Timer(startDelay,this);
        timer.start();
        
        tailX = new int[this.sizeX*this.sizeY];
        tailY = new int[this.sizeX*this.sizeY];
        
        play = false;
    }

    public void paint(Graphics g)
    {
        //backgound
        g.setColor(Color.PINK);
        g.fillRect(0, 0, sizeX*10, sizeY*10);
        
        //cake
        g.setColor(Color.RED);
        g.fillOval(cakePosX*10, cakePosY*10, 10, 10);
        
        //snake
        g.setColor(Color.YELLOW);
        for(int i = 0; i < snakeLenght ; ++i)
        {
            g.fillRect(tailX[i]*10+1, tailY[i]*10+1, 8, 8);     
        }
        
        //snake-head
        g.setColor(Color.GREEN);
        g.fillRect(snakePosX*10+1, snakePosY*10+1, 8, 8);
            //eyes?
        
        //borders
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, sizeX*10, 10);
        g.fillRect(0, 0, 10, sizeY*10);
        g.fillRect(0, sizeY*10-10, sizeX*10, 10);
        g.fillRect(sizeX*10-10, 0, 10, sizeY*10);
        
        //Text: score
        g.setColor(Color.WHITE);
        //g.setFont(Font.getFont("Bold"));
        g.drawString("Score:"+score, (sizeX*5), 10 );
        
        //draw prees enter to play
        if(play == false)
        {
            g.drawString("Press Enter to play", sizeX*5, sizeY*5);
        }
        g.dispose();
    }
    
    private void gameover()
    {
        play = false;
    }
    
    
    private void start()
    {
        delay = startDelay;
        score = 0;
        snakeLenght = 3;
        
        snakePosX = this.sizeX/2;
        snakePosY = this.sizeY/2;
        
        for(int i = 0; i < snakeLenght; ++i)
        {
            tailX[i] = snakePosX;
            tailY[i] = snakePosY + i;
        }
        
        play = true;
    }
    
    private void eaten()
    {
        ++score;
        genCake();
        delay -= 2;
        timer.setDelay(delay);
        ++snakeLenght;
        tailX[snakeLenght-1] = tailX[snakeLenght-2];
        tailY[snakeLenght-1] = tailY[snakeLenght-2];
    }
    
    private void genCake()
    {
        do{
            cakePosX = (int)(Math.random() * (sizeX-2) +1);
            cakePosY = (int)(Math.random() * (sizeY-2) +1);
        }while(cakePosX == snakePosX);
    }
    
    private void snakeMove()
    {
        if(play == true)
        {
            switch(snakeDirection)
            {
                case up:
                    --snakePosY;
                    break;
                case down:
                    ++snakePosY;
                    break;
                case right:
                    ++snakePosX;
                    break;
                case left:
                    --snakePosX;
                    break;
            }
            for(int i = snakeLenght-1 ; i > 0 ; --i)
            {
                tailX[i] = tailX[i-1];
                tailY[i] = tailY[i-1];
            }
        
            tailX[0] = snakePosX;
            tailY[0] = snakePosY;
        }
    }
    
    private boolean isSnake(int X, int Y)
    {
        for(int i = 1; i < snakeLenght ; ++i)
        {
            if(tailX[i] == X && tailY[i] == Y)
                return true;
        }
        return false;
    }
    @Override
    public void keyTyped(KeyEvent ke) 
    {    /*Nothing*/    }

    @Override
    public void keyPressed(KeyEvent ke) 
    {
        switch(ke.getKeyCode())
        {
            case KeyEvent.VK_UP:
                if(snakeDirection != Direction.down)
                snakeDirection = Direction.up;
                break;
            case KeyEvent.VK_DOWN:
                if(snakeDirection != Direction.up)
                snakeDirection = Direction.down;
                break;
            case KeyEvent.VK_RIGHT:
                if(snakeDirection != Direction.left)
                snakeDirection = Direction.right;
                break;
            case KeyEvent.VK_LEFT:
                if(snakeDirection != Direction.right)
                snakeDirection = Direction.left;
                break;
        }
        
        if(play != true)
        {
            if(ke.getKeyCode() == KeyEvent.VK_ENTER)
            {
                start();
            }
        }
    }
    
    @Override
    public void keyReleased(KeyEvent ke) 
    {    /*Nothing*/    }

    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        if(snakePosX == 0 || snakePosY == 0 || snakePosX == sizeX || snakePosY == sizeY || isSnake(snakePosX, snakePosY))
        {
            gameover();
        }else
        {   
            snakeMove();
            if(snakePosX == cakePosX && snakePosY == cakePosY)
            {
                eaten();
            } 
        }
        repaint();
    }
}

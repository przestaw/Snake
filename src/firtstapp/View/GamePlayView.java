/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firtstapp.View;

import firtstapp.Model.GamePlay;
import firtstapp.Model.SnakeBlock;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

/**
 *
 * @author przemek
 */
public class GamePlayView extends JPanel implements Observer
{
    private final GamePlay game;
    
    private final int sizeX;
    private final int sizeY;
    
    private final int pixSize;
    
    public GamePlayView(GamePlay game, int pixSize) 
    {
        this.sizeX = game.getSizeX();
        this.sizeY = game.getSizeY();
        
        this.pixSize = pixSize;
        
        this.game = game;
        
        game.addObserver(this);
        
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }
    
    @Override
    public void paint(Graphics g)
    {
        
        //super.paint(g);
        //backgound
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, sizeX*pixSize, sizeY*pixSize);
        
        //cake
        g.setColor(Color.RED);
        g.fillOval(game.getCakePosX()*pixSize, game.getCakePosY()*pixSize, pixSize, pixSize);
        
        //snake
        g.setColor(Color.YELLOW);
        SnakeBlock temp;
        for(int i = 0; i < game.getSnakeLenght() ; ++i)
        {
            temp = game.getSnakeBlock(i);
            g.fillRect(temp.getPosX()*pixSize, temp.getPosY()*pixSize, (int)pixSize, (int)pixSize);     
        }
        
        //snake-head
        g.setColor(Color.GREEN);
        g.fillRect((int)(game.getSnakePosX())*pixSize, (int)(game.getSnakePosY())*pixSize, (int)pixSize, (int)pixSize);
            //eyes?
        
        //borders
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, sizeX*pixSize, pixSize);
        g.fillRect(0, 0, pixSize, sizeY*pixSize);
        g.fillRect(0, sizeY*pixSize-pixSize, sizeX*pixSize, pixSize);
        g.fillRect(sizeX*pixSize-pixSize, 0, pixSize, sizeY*pixSize);
        
        //Text: score
        g.setColor(Color.BLUE);
        g.setFont(new Font("Consolans", Font.BOLD, 2*pixSize));
        g.drawString("Score:"+game.getScore(), (int)(pixSize*1.5), (pixSize*3));        
            //draw prees enter to play
        if(game.isPlay() == false)
        {
            g.setColor(Color.CYAN);
            g.setFont(new Font("Consolans", Font.BOLD, 2*pixSize));
            g.drawString("Press Enter to play", sizeX*pixSize, sizeY*pixSize);
        }
        g.dispose();
    }

    @Override
    public void update(Observable o, Object o1) 
    {
        repaint();
    }
    
}

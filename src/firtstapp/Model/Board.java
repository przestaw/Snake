/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firtstapp.Model;

import java.util.Observable;

/**
 *
 * @author przemek
 */
public abstract class Board extends Observable
{
    protected int score;
    
    protected boolean play;
    protected boolean gameover;
    
    protected static int sizeX;
    protected static int sizeY;
    
    Board(int sizeX, int sizeY)
    {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        
        this.score = 0;
        
        this.play = false;
        this.gameover = false;
    }

    public int getScore() 
    {
        return score;
    }

    public void upScore(int value) 
    {
        score += value;
    }
    
    public void upScore() 
    {
        this.upScore(5);
        this.setChanged();
        notifyObservers();
    }
    
    public void resetScore() 
    {
        score = 0;
    }
    
    public boolean isPlay() 
    {
        return play;
    }

    public void start() 
    {
        play = true;
        gameover = false;
        this.setChanged();
        notifyObservers();
    }
    
    public void pause() 
    {
        play = false;
    }
    
    public int getSizeX() 
    {
        return sizeX;
    }

    public int getSizeY() 
    {
        return sizeY;
    }
    
    public boolean isGameover() 
    {
        return gameover;
    }
    
    public void gameover() 
    {
        gameover = true;
        play = false;
        this.setChanged();
        notifyObservers();
    }
}

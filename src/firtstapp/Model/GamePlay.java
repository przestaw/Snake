/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firtstapp.Model;

import firtstapp.Model.GameObject.Direction;


/**
 *
 * @author przemek
 */
public class GamePlay extends Board 
{
    private SnakeHead snake;
    private Direction nextSnakeDirection;
    
    private final Cake cake;
    
    private int delay;
    private static int startDelay;
    private static int rampUp;
    
    public GamePlay(int sizeX, int sizeY, int startDelay, int rampUp)
    {
        super(sizeX, sizeY);
        snake = new SnakeHead(sizeX/2, sizeY/2, sizeX*sizeY, 4);
        nextSnakeDirection = Direction.up;
        cake = new Cake(sizeX, sizeY);
        
        this.delay = startDelay;
        this.startDelay = this.delay;
        this.rampUp = rampUp;
    }

    public int getCakePosX() 
    {
        return cake.getPosX();
    }
    
    public int getCakePosY() 
    {
        return cake.getPosY();
    }

    public int getDelay() 
    {
        return delay;
    }
    
    public boolean isSnake(int posX, int posY)
    {
        return snake.isSnake(posX, posY);
    }
    
    public int getSnakeLenght() 
    {
        return snake.getLenght();
    }
    
    public SnakeBlock getSnakeBlock(int which) 
    {
        return snake.getBlock(which);
    }
    
    public Direction getNextSnakeDirection()
    {
        return nextSnakeDirection;
    }
    
    public int getSnakePosX() 
    {
        return snake.getPosX();
    }
    
    public int getSnakePosY() 
    {
        return snake.getPosY();
    }
    
    public void setNextSnakeDirection(Direction direction)
    {
        nextSnakeDirection = direction;
    }
    
    public void moveSnake()
    {
        snake.setDirection(nextSnakeDirection);
        if(snake.getNextPosX() == 0 || snake.getNextPosX() == sizeX
           || snake.getNextPosY() == 0 || snake.getNextPosY() == sizeY
           || snake.isSnake(snake.getNextPosX(), snake.getNextPosY()))
        {
            gameover();
            return;
        }
        snake.move();
        this.setChanged();
        notifyObservers();
    }
    
    public void cakeEaten()
    {
        this.upScore();
        snake.ateCake();
        rampUpDelay();
        updateCake();
    }
    
    public void updateCake()
    {
        do{
            cake.eaten();
        }while( (snake.getNextPosX() == cake.getPosX() && snake.getNextPosY() == cake.getPosY())
                || isSnake(cake.getPosX(), cake.getPosY()) );
        
        this.setChanged();
        notifyObservers();
    }
    
    public void restart()
    {
        this.start();
        snake = new SnakeHead(sizeX/2, sizeY/2, sizeX*sizeY, 4);
        this.updateCake();
        this.resetScore();
        this.resetDelay();
    }

    private void resetDelay() 
    {
        delay = startDelay;
    }
    
    private void rampUpDelay()
    {
        if(delay > rampUp+1)
        {
            delay -= rampUp;
        }
    }
}

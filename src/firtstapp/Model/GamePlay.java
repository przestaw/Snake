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
    private final Cake cake;
    
    private boolean eaten;
    
    public GamePlay(int sizeX, int sizeY)
    {
        super(sizeX, sizeY);
        snake = new SnakeHead(sizeX/2, sizeY/2, sizeX*sizeY, 4);
        cake = new Cake(sizeX/2 + 2, sizeY/2 + 2, sizeX, sizeY);
        eaten = false;
    }

    public SnakeHead getSnake() 
    {
        return snake;
    }

    public int getCakePosX() 
    {
        return cake.getPosX();
    }
    
    public int getCakePosY() 
    {
        return cake.getPosY();
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
    
    public int getSnakePosX() 
    {
        return snake.getPosX();
    }
    
    public int getSnakePosY() 
    {
        return snake.getPosY();
    }
    
    public void setSnakeDirection(Direction direction)
    {
        snake.setDirection(direction);
    }
    
    private boolean willEat()
    {
        return (snake.getNextPosX() == cake.getPosX() && snake.getNextPosY() == cake.getPosY());  
    }
    
    public boolean isMovePossible()
    {
        return (snake.getNextPosX() != 0 && snake.getNextPosX() != this.getSizeX()
           && snake.getNextPosY() != 0 && snake.getNextPosX() != this.getSizeY());
    }
    
    public void moveSnake()
    {
        if (willEat()) 
        {
            cakeEaten();
        }else
        {
            cakeNotEaten();
        }
        snake.move();
        this.setChanged();
        notifyObservers();
    }
    
    public void cakeEaten()
    {
        this.upScore();
        updateCake();
        eaten = true;
    }
    
    private void cakeNotEaten() 
    {
        eaten = false;
    }
    
    public boolean isEaten()
    {
        return eaten;
    }
    
    public void updateCake()
    {
        while( (snake.getNextPosX() == cake.getPosX() && snake.getNextPosY() == cake.getPosY())
                || isSnake(cake.getPosX(), cake.getPosY()) )
        {
            cake.eaten();
        }
    }
    
    public void restart()
    {
        this.start();
        snake = new SnakeHead(sizeX/2, sizeY/2, sizeX*sizeY, 4);
        this.updateCake();
        this.resetScore();
    }
}

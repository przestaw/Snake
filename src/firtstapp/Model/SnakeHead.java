/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firtstapp.Model;

/**
 *
 * @author przemek
 */
public class SnakeHead extends SnakeBlock
{
    private SnakeBlock[] tail;
    
    protected Direction snakeDirection;
    
    private int snakeLenght;
    
    SnakeHead(int posX, int posY, int maxSize, int lenght)
    {
        super(posX, posY);
        this.snakeDirection = Direction.up;
        snakeLenght = lenght;
        tail = new SnakeBlock[maxSize];
        genSnake();
    }
    
    SnakeHead(int posX, int posY, int maxSize)
    {
        this(posX, posY, maxSize, 3);
    }
    
    public Direction getDirection()
    {
        return snakeDirection;
    }
    
    public int getLenght()
    {
        return snakeLenght;
    }
    
    public int getNextPosX()
    {
        switch (snakeDirection) {
            case up:
            case down:
                return (this.getPosX());
            case right:
                return (this.getPosX()+1);
            case left:
                return (this.getPosX()-1);
        }
        return -1;
    }
    
    public int getNextPosY()
    {
        switch (snakeDirection) {
            case up:
                return (this.getPosY()-1);
            case down:
                return (this.getPosY()+1);
            case right:
            case left:
                return (this.getPosY());
        }
        return -1;
    }
    
    public SnakeBlock getBlock(int which)
    {
        if(which >= 0 || which < snakeLenght)
        {
            return tail[which];
        }else
        {
            return new SnakeBlock(-1,-1);
        }
    }
    
    public void ateCake()
    {
        ++snakeLenght;
        tail[snakeLenght-1] = tail[snakeLenght-2];
    }
    
    private void genSnake()
    {
        int X = this.getPosX();
        int Y = this.getPosY();
        for(int i = 0; i < snakeLenght; ++i)
        {
            tail[i] = new SnakeBlock(X, Y);
            switch (snakeDirection) 
            {
                case up:
                    ++Y;
                    break;
                case left:
                    ++X;
                    break;
                case right:
                    --X;
                    break;
                case down:
                    --Y;
                    break;
            }
        }
    }
     
    public boolean isSnake(int X, int Y)
    {
        for(int i = 0; i < snakeLenght ; ++i)
        {
            if(tail[i].getPosX() == X && tail[i].getPosY() == Y)
                return true;
        }
        return false;
    }

    public void setDirection(Direction direction)
    {
        switch(direction)
        {
            
            case up:
                if(snakeDirection != direction.down)
                {
                     this.snakeDirection = direction;
                }
                break;
            case left:
                if(snakeDirection != direction.right)
                {
                     this.snakeDirection = direction;
                }
                break;
            case right:
                if(snakeDirection != direction.left)
                {
                     this.snakeDirection = direction;
                }
                break;
            case down:
                if(snakeDirection != direction.up)
                {
                     this.snakeDirection = direction;
                }
                break;
        }
    }
    
    public void move()
    {
        switch (snakeDirection) 
        {
            case up:
                this.modifyPosY(-1);
                break;
            case down:
                this.modifyPosY(1);
                break;
            case right:
                this.modifyPosX(1);
                break;
            case left:
                this.modifyPosX(-1);
                break;
        }
        for (int i = snakeLenght - 1; i > 0; --i)
        {
            tail[i] = new SnakeBlock(tail[i - 1]);
        }
        tail[0] = new SnakeBlock((SnakeBlock)this);
    }
}

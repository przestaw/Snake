/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firtstapp.Controller;

import firtstapp.Model.GameObject.Direction;
import firtstapp.Model.GamePlay;

/**
 *
 * @author przemek
 */
public class GameControl
{
    private GamePlay game;
    
    public GameControl(GamePlay game) 
    {
        this.game = game; 
    }

    private boolean isGameOver()
    {
        return false;
    }
    
    public void update()
    {
        if(game.isPlay())
        {
            if(!isGameOver())
            {
                game.moveSnake();
                if(game.getSnakePosX() == game.getCakePosX() && game.getSnakePosY() == game.getCakePosY())
                {
                    game.cakeEaten();
                }
            }else
            {
                game.gameover();
            }
        }
    }
    
    public void enterOccured()
    {
        if (game.isGameover()) 
        {
            game.restart();
        } else 
        {
            game.start();
        }
    }
    
    public void directionOccured(Direction direction)
    {
        game.setNextSnakeDirection(direction);
    }

    public void escapeOccured() 
    {
        if(game.isPlay())
        {
            game.pause();
        }else
        {
            game.restart();
        }
    }
}

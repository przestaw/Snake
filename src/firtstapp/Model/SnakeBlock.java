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
public class SnakeBlock extends GameObject
{
    SnakeBlock()
    {
        this(0,0);
    }
    
    SnakeBlock(int posX, int posY)
    {
        super(posX, posY);
    }
}

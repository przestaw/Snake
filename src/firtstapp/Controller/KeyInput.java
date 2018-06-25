/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firtstapp.Controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/**
 *
 * @author przemek
 */
public class KeyInput extends KeyAdapter
{
    @Override
    public void keyTyped(KeyEvent ke) 
    {    /*Nothing*/    }

    @Override
    public void keyPressed(KeyEvent ke) 
    {
        switch(ke.getKeyCode())
        {
            case KeyEvent.VK_UP:
                
                break;
            case KeyEvent.VK_DOWN:
                
                break;
            case KeyEvent.VK_RIGHT:
                
                break;
            case KeyEvent.VK_LEFT:
                
                break;
            case KeyEvent.VK_ENTER:
            
                break;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent ke) 
    {    /*Nothing*/    }
}

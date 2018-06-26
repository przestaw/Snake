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
public class Cake extends GameObject
{
    private static int rangeX; 
    private static int rangeY;
            
    
    Cake(int posX, int posY, int rangeX, int rangeY)
    {
        super(posX, posY);
        this.rangeX = rangeX;
        this.rangeY = rangeY;
    }
    
    Cake(int rangeX, int rangeY)
    {
        super(0,0);
        
        //to avoid problems in deriviated classes
        ((Cake)this).setPosX((int)(Math.random() * (rangeX-2) +1));
        ((Cake)this).setPosY((int)(Math.random() * (rangeY-2) +1));
        
        this.rangeX = rangeX;
        this.rangeY = rangeY;
    }
    
    public void eaten()
    {
        this.setPosX((int)(Math.random() * (rangeX-2)) +1);
        this.setPosY((int)(Math.random() * (rangeY-2)) +1);
    }
}

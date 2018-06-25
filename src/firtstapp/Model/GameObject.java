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
public abstract class GameObject 
{
    private int sizeX;
    private int sizeY;
    
    private int posX;
    private int posY;
    
    public enum Direction{up, down, left, right};
    
    GameObject(int posX, int posY)
    {
        this.posX = posX;
        this.posY = posY;
    
        this.sizeX = 1;
        this.sizeY = 1;
    }
    
    GameObject(int posX, int posY, int sizeX, int sizeY)
    {
        this.posX = posX;
        this.posY = posY;
    
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }
    
    public final int getPosX()
    {
        return posX;
    }
    
    public final int getPosY()
    {
        return posY;
    }
    
    public final int getSizeX()
    {
        return sizeX;
    }
    
    public final int getSizeY()
    {
        return sizeY;
    }
    
    public void setPosX(int posX)
    {
        if(posX > 0)
        this.posX = posX;
    }
    
    public void setPosY(int posY)
    {
        if(posY > 0)
        this.posY = posY;
    }
    
    public void setSizeX(int sizeX)
    {
        if(sizeX > 0)
        this.sizeX = sizeX;
    }
    
    public void setSizeY(int sizeY)
    {
        if(sizeY > 0)
        this.sizeY = sizeY;
    }
    
    public void modifyPosX(int posX)
    {
        this.posX += posX;
    }
    
    public void modifyPosY(int posY)
    {
        this.posY += posY;
    }
    
    public void modifySizeX(int sizeX)
    {
        this.sizeX += sizeX;
    }
    
    public void modifySizeY(int sizeY)
    {
        this.sizeY += sizeY;
    }
}

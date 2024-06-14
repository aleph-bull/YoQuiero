import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GiveButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GiveButton extends Actor
{
    private Color opaqueRed = new Color(220, 5, 10, 200);
    
    public void act()
    {
        GreenfootImage image = new GreenfootImage(60, 30);
        image.setColor(opaqueRed);
        image.fill();
        image.setColor(Color.BLACK);
        image.drawRect(0, 0, image.getWidth()-1, image.getHeight()-1);
        setImage(image);
        image.drawString("serve", 10, 20);
    }
}

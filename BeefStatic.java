import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BeefStatic here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BeefStatic extends Actor
{
    private GreenfootImage image = getImage();
    
    private int resizedImageWidth = image.getWidth()*11/10;
    private int resizedImageHeight = image.getHeight()*11/10;
    
    public BeefStatic() {
        image.scale(resizedImageWidth,resizedImageHeight);
    }
    
    public void act()
    {
        // Add your action code here.
    }
}

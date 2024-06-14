import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BeefStatic here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IngredientStatic extends Actor
{
    private GreenfootImage image = getImage();
    
    private int resizedImageWidth = image.getWidth()*11/10;
    private int resizedImageHeight = image.getHeight()*11/10;
    
    public IngredientStatic() {
        image.scale(resizedImageWidth,resizedImageHeight);
    }
    
    public void act()
    {
        // Add your action code here.
    }
}

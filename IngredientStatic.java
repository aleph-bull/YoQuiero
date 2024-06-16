import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class IngredientStatic extends Actor
{
    private GreenfootImage image = getImage();
    
    private int resizedImageWidth = image.getWidth()*11/10;
    private int resizedImageHeight = image.getHeight()*11/10;
    
    public IngredientStatic() {
        image.scale(resizedImageWidth,resizedImageHeight);
    }
}

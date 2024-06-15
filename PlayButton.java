import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PlayButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayButton extends Actor
{
    private OrderingWorld orderTab;
    private GreenfootImage image = getImage();
    
    private int resizedImageWidth = image.getWidth()*12/10;
    private int resizedImageHeight = image.getHeight()*12/10;
    
    public PlayButton(OrderingWorld orderTab){
        this.orderTab = orderTab;
        image.scale(resizedImageWidth,resizedImageHeight);
    }
    
    public void act()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        
        if (mouse != null) {
            if (Greenfoot.mouseClicked(this)) {
                Greenfoot.setWorld(orderTab);
            }
        }
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Nametag here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Nametag extends Actor
{
    private String name;
    /**
     * Act - do whatever the Nametag wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private Color opaqueWhite = new Color(255, 255, 255, 200);
    
    public Nametag(String name){
        this.name = name;
        GreenfootImage image = new GreenfootImage(130, 30);
        image.setColor(opaqueWhite);
        image.fill();
        image.setColor(Color.BLACK);
        image.drawRect(0, 0, image.getWidth()-1, image.getHeight()-1);
        setImage(image);
        image.drawString(name, 10, 20);
    }
    
    public void act()
    {
        // Add your action code here.
    }
}

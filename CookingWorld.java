import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * All background images are created by me using images from:
 * 
 */
public class CookingWorld extends World
{
    OrderingWorld orderTab;
    private PlateBack plate = new PlateBack();
    /**
     * Constructor for objects of class CookingWorld.
     * 
     */
    private boolean beefSpawned = false; 
    public CookingWorld(OrderingWorld orderTab)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        addObject(plate, 300, 320);
        addObject(new Beef(plate), 125, 123);
        this.orderTab = orderTab;
    }
    
    public void act() {
        grabBeef();
        if(Greenfoot.isKeyDown("g")) {
            Greenfoot.setWorld(orderTab);
        }
    }
    
    //spawns beef when container is pressed
    private void grabBeef() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) {
            if(mouse.getX() > 60 && mouse.getY() > 75 
            && mouse.getX() < 200 && mouse.getY() < 165 && Greenfoot.mousePressed(null)) {
                if(!beefSpawned) {
                    beefSpawned = true;
                    System.out.println("Object Spawned!");
                    addObject(new Beef(plate), 125, 123);
                }
            } else {
                beefSpawned = false;
            }
        }
    }
}

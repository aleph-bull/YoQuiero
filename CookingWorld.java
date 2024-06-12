import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * All background images are created by me using images from:
 * 
 */
public class CookingWorld extends World
{
    OrderingWorld orderTab;
    private PlateBack plate;
    /**
     * Constructor for objects of class CookingWorld.
     * 
     */
    private boolean beefSpawned = false; 
    public CookingWorld(OrderingWorld orderTab)
    {     
        super(600, 400, 1);
        if(plate == null){ 
            plate = new PlateBack();
        }
        setPaintOrder(Beef.class, PlateFront.class, BeefStatic.class);
        addObject(plate, 300, 320);
        addObject(new Beef(plate), 125, 120);
        addObject(new PlateFront(), 300, 320);
        
        this.orderTab = orderTab;
    }
    
    public void act() {
        grabBeef();
        if(Greenfoot.isKeyDown("g")) {
            orderTab.movedWorld(true);
            Greenfoot.setWorld(orderTab);
        }
        
        if(orderTab.didWorldMove()) {
            addObject(plate, 300, 320);
            orderTab.movedWorld(false);
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
    
    public PlateBack getPlate(){
        return plate;
    }
}

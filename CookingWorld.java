import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * All background images are created by me using images from:
 * 
 */
public class CookingWorld extends World
{
    private OrderingWorld orderTab;
    private PlateBack plate;
    /**
     * Constructor for objects of class CookingWorld.
     * 
     */
    private boolean beefSpawned = false; 
    public CookingWorld(OrderingWorld orderTab)
    {     
        super(600, 400, 1);
        plate = orderTab.getPlate();
        setPaintOrder(Beef.class, PlateFront.class, BeefStatic.class);
        
        addObject(new PlateFront(), 310, 320);
        
        this.orderTab = orderTab;
    }
    
    public void act() {
        grabBeef();
        
        if(orderTab.didWorldSwitch()){
            plate = orderTab.getPlate();
            addObject(plate, 310, 320);
            addObject(new Beef(plate), 125, 120);
            orderTab.SwitchedWorld(false);
        }
        
        if(Greenfoot.isKeyDown("g")) {
            removeObject(plate);
            orderTab.SwitchedWorld(true);
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
    
    public PlateBack getPlate(){
        return plate;
    }
}

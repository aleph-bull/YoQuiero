import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BuildingWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BuildingWorld extends World
{
    private CookingWorld cookTab;
    private OrderingWorld orderTab;
    private CuttingBoard cuttingBoard = new CuttingBoard();
    private PlateBack plate;
    private int plateDisplacementX = 150;
    private int plateDisplacementY = -55;
    
    public BuildingWorld(CookingWorld cookTab, OrderingWorld orderTab, PlateBack plate)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        this.cookTab = cookTab;
        this.orderTab = orderTab;
        this.plate = plate;
        
        setPaintOrder(CuttingKnife.class, Beef.class, PlateFront.class);
        
        addObject(new PlateFront(), 310 + plateDisplacementX, 320 + plateDisplacementY);
        
        addObject(cuttingBoard, 470, 150);
    }
    
    public void act() {
        if(orderTab.didWorldSwitch()){
            plate = orderTab.getPlate();
            addObject(plate, 310, 320);
            orderTab.SwitchedWorld(false);
        }
        
        if(Greenfoot.isKeyDown("h")) {
            plate.removeBeefs();
            removeObject(plate);
            cookTab.addObject(plate, 310, 320);
            orderTab.SwitchedWorld(true);
            Greenfoot.setWorld(cookTab);
        }
    }
    
    public int getPlateDisplacement(boolean getX) {
        if(getX) {
            return plateDisplacementX;
        }
        return plateDisplacementY;
    }
}

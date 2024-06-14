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
    private CuttingKnife cuttingKnife = new CuttingKnife();
    private PlateBack plate;
    
    private boolean firstWorldSwitch = true;
    private boolean pumpkinSpawned = false;
    private boolean watermelonSpawned = false; 
    private boolean eggShellSpawned = false; 
    private boolean peanutButterSpawned = false; 
    private int plateDisplacementX = 150;
    private int plateDisplacementY = -55;
    
    public BuildingWorld(CookingWorld cookTab, OrderingWorld orderTab, PlateBack plate)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        this.cookTab = cookTab;
        this.orderTab = orderTab;
        this.plate = plate;
        
        setPaintOrder(CuttingKnife.class, Pumpkin.class, Watermelon.class, PlateFront.class);
        
        addObject(new PlateFront(), 310 + plateDisplacementX, 320 + plateDisplacementY);
        
        addObject(cuttingBoard, 470, 150);
        addObject(cuttingKnife, 550, 160);
    }
    
    public void act() {
        grabPumpkin();
        grabWatermelon();
        grabEggShell();
        grabPeanutButter();
        
        if(orderTab.didWorldSwitch()){
            plate = orderTab.getPlate();
            addObject(plate, 310, 320);
            if(firstWorldSwitch) {
                addObject(new Pumpkin(plate, cuttingBoard, cuttingKnife), 100, 120);
                addObject(new Watermelon(plate, cuttingBoard, cuttingKnife), 260, 120);
                addObject(new EggShell(plate, cuttingBoard, cuttingKnife), 100, 255);
                addObject(new PeanutButter(plate, cuttingBoard, cuttingKnife), 260, 255);
                firstWorldSwitch = false;
            }
            orderTab.switchedWorld(false);
        }
        
        if(Greenfoot.isKeyDown("h")) {
            plate.removeBeefs();
            removeObject(plate);
            cookTab.addObject(plate, 310, 320);
            orderTab.switchedWorld(true);
            Greenfoot.setWorld(cookTab);
        }
    }
    
    private void grabPumpkin() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) {
            if(mouse.getX() > 40 && mouse.getY() > 70 
            && mouse.getX() < 170 && mouse.getY() < 170 && Greenfoot.mousePressed(null)) {
                if(!pumpkinSpawned) {
                    pumpkinSpawned = true;
                    System.out.println("Object Spawned!");
                    addObject(new Pumpkin(plate, cuttingBoard, cuttingKnife), 100, 120);
                }
            } else {
                pumpkinSpawned = false;
            }
        }
    }
    private void grabWatermelon() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) {
            if(mouse.getX() > 200 && mouse.getY() > 70 
            && mouse.getX() < 320 && mouse.getY() < 170 && Greenfoot.mousePressed(null)) {
                if(!watermelonSpawned) {
                    watermelonSpawned = true;
                    System.out.println("Object Spawned!");
                    addObject(new Watermelon(plate, cuttingBoard, cuttingKnife), 260, 120);
                }
            } else {
                watermelonSpawned = false;
            }
        }
    }
    
    private void grabEggShell() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) {
            if(mouse.getX() > 40 && mouse.getY() > 200 
            && mouse.getX() < 170 && mouse.getY() < 300 && Greenfoot.mousePressed(null)) {
                if(!eggShellSpawned) {
                    eggShellSpawned = true;
                    System.out.println("Object Spawned!");
                    addObject(new EggShell(plate, cuttingBoard, cuttingKnife), 100, 255);
                }
            } else {
                eggShellSpawned = false;
            }
        }
    }
    
    private void grabPeanutButter() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) {
            if(mouse.getX() > 200 && mouse.getY() > 200 
            && mouse.getX() < 320 && mouse.getY() < 300 && Greenfoot.mousePressed(null)) {
                if(!peanutButterSpawned) {
                    peanutButterSpawned = true;
                    System.out.println("Object Spawned!");
                    addObject(new PeanutButter(plate, cuttingBoard, cuttingKnife), 260, 255);
                }
            } else {
                peanutButterSpawned = false;
            }
        }
    }
    
    public int getPlateDisplacement(boolean getX) {
        if(getX) {
            return plateDisplacementX;
        }
        return plateDisplacementY;
    }
}

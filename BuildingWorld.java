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
    private WalletText walletText;
    private MusicPlayer musicPlayer = MusicPlayer.getInstance();
    private CuttingBoard cuttingBoard = new CuttingBoard();
    private CuttingKnife cuttingKnife = new CuttingKnife();
    private PlateBack plate;
    
    private boolean firstWorldSwitch = true;
    private boolean pumpkinSpawned = false;
    private boolean watermelonSpawned = false; 
    private boolean eggShellSpawned = false; 
    private boolean peanutButterSpawned = false; 
    private int plateDisplacementX = 165;
    private int plateDisplacementY = -10;
    
    public BuildingWorld(CookingWorld cookTab, OrderingWorld orderTab, PlateBack plate) {
        super(600, 400, 1);
        this.cookTab = cookTab;
        this.orderTab = orderTab;
        this.plate = plate;
        musicPlayer.play();
        walletText = new WalletText(orderTab);
        
        setPaintOrder(WalletText.class, CuttingKnife.class, Pumpkin.class, Watermelon.class, PeanutButter.class, EggShell.class, PlateFront.class, IngredientStatic.class, BeefStatic.class);
        
        addObject(new PlateFront(), 310 + plateDisplacementX, 320 + plateDisplacementY);
        addObject(walletText, 35, 385);
        
        addObject(cuttingBoard, 470, 150);
        addObject(cuttingKnife, 550, 160);
    }
    
    public void started() {
        musicPlayer.play();
    }
    
    public void stopped() {
        musicPlayer.pause();
    }
    
    public void act() {
        musicPlayer.checkAndPlayNext();
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
            plate.removeIngredients();
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
    
    public void resetPlate() {
        plate = orderTab.getPlate();
    }
}

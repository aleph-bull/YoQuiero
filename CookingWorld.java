import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class CookingWorld extends World
{
    private OrderingWorld orderTab;
    private BuildingWorld buildTab;
    private WalletText walletText;
    private PlateBack plate;
    //flags to prevent instant skipping
    private boolean kWasDown = false;
    private boolean hWasDown = false;
    private boolean firstWorldSwitch = true;
    private boolean beefSpawned = false; 
    public CookingWorld(OrderingWorld orderTab)
    {     
        super(600, 400, 1);
        this.orderTab = orderTab;
        plate = orderTab.getPlate();
        walletText = new WalletText(orderTab);
        setPaintOrder(WalletText.class, Beef.class, PlateFront.class, IngredientStatic.class, BeefStatic.class);
        
        addObject(new PlateFront(), 310, 320);
        addObject(walletText, 35, 385);
        
        buildTab = new BuildingWorld(this, orderTab, plate);
    }
    
    public void act() {
        grabBeef();
        
        if(orderTab.didWorldSwitch()){
            plate = orderTab.getPlate();
            if(firstWorldSwitch) {
                addObject(new Beef(plate, this), 125, 120);
                firstWorldSwitch = false;
            }
            kWasDown = true;
            hWasDown = true;
            orderTab.switchedWorld(false);
        }
        
        if(Greenfoot.isKeyDown("h") && !hWasDown) {
            stopAllSizzleSounds();
            plate.removeIngredients();
            plate.removeBeefs();
            removeObject(plate);
            orderTab.switchedWorld(true);
            orderTab.addObject(plate, 310 + orderTab.getPlateDisplacement(true), 
                                      320 + orderTab.getPlateDisplacement(false));
            Greenfoot.setWorld(orderTab);
        } else if (Greenfoot.isKeyDown("k") && !kWasDown) {
            stopAllSizzleSounds();
            plate.removeIngredients();
            plate.removeBeefs();
            removeObject(plate);
            orderTab.switchedWorld(true);
            buildTab.addObject(plate, 310 + buildTab.getPlateDisplacement(true), 
                                      320 + buildTab.getPlateDisplacement(false));
            Greenfoot.setWorld(buildTab);
        }
        
        kWasDown = Greenfoot.isKeyDown("k");
        hWasDown = Greenfoot.isKeyDown("h");
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
                    addObject(new Beef(plate, this), 125, 123);
                }
            } else {
                beefSpawned = false;
            }
        }
    }
    
    private void stopAllSizzleSounds() {
        List<Beef> beefs = getObjects(Beef.class);
        for (Beef beef : beefs) {
            beef.stopSizzle();
        }
    }
        
    public PlateBack getPlate(){
        return plate;
    }
    
    public BuildingWorld getBuildTab(){
        return buildTab;
    }
    
    public boolean getHWasDown() {
        return hWasDown;
    }
    
    public boolean getKWasDown() {
        return kWasDown;
    }
    
    public void resetPlate() {
        plate = orderTab.getPlate();
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class CookingWorld extends World
{
    private OrderingWorld orderTab;
    private BuildingWorld buildTab;
    private WalletText walletText;
    private PlateBack plate;
    private MusicPlayer musicPlayer = MusicPlayer.getInstance();
    //flags to prevent instant skipping
    private boolean leftWasDown = false;
    private boolean rightWasDown = false;
    private boolean firstWorldSwitch = true;
    private boolean beefSpawned = false; 
    public CookingWorld(OrderingWorld orderTab)
    {     
        super(600, 400, 1);
        this.orderTab = orderTab;
        plate = orderTab.getPlate();
        walletText = new WalletText(orderTab);
        setPaintOrder(WalletText.class, Beef.class, PlateFront.class, IngredientStatic.class, BeefStatic.class);
        musicPlayer.play();
        addObject(new PlateFront(), 310, 320);
        addObject(walletText, 35, 385);
        
        buildTab = new BuildingWorld(this, orderTab, plate);
    }
    
    public void act() {
        grabBeef();
        musicPlayer.checkAndPlayNext();
        if(orderTab.didWorldSwitch()){
            plate = orderTab.getPlate();
            if(firstWorldSwitch) {
                addObject(new Beef(plate, this), 125, 120);
                firstWorldSwitch = false;
            }
            leftWasDown = true;
            rightWasDown = true;
            orderTab.switchedWorld(false);
        }
        
        if(Greenfoot.isKeyDown("Left") && !leftWasDown) {
            stopAllSizzleSounds();
            plate.removeIngredients();
            plate.removeBeefs();
            removeObject(plate);
            orderTab.switchedWorld(true);
            orderTab.addObject(plate, 310 + orderTab.getPlateDisplacement(true), 
                                      320 + orderTab.getPlateDisplacement(false));
            Greenfoot.setWorld(orderTab);
        } else if (Greenfoot.isKeyDown("Right") && !rightWasDown) {
            stopAllSizzleSounds();
            plate.removeIngredients();
            plate.removeBeefs();
            removeObject(plate);
            orderTab.switchedWorld(true);
            buildTab.addObject(plate, 310 + buildTab.getPlateDisplacement(true), 
                                      320 + buildTab.getPlateDisplacement(false));
            Greenfoot.setWorld(buildTab);
        }
        
        leftWasDown = Greenfoot.isKeyDown("Left");
        rightWasDown = Greenfoot.isKeyDown("Right");
    }
    
    public void started() {
        musicPlayer.play();
    }
    
    public void stopped() {
        musicPlayer.pause();
    }
    
    //spawns beef when container is pressed
    private void grabBeef() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) {
            boolean isBeefPresent = !getObjectsAt(mouse.getX(), mouse.getY(), Beef.class).isEmpty();
            if(mouse.getX() > 83 && mouse.getY() > 78 
            && mouse.getX() < 167 && mouse.getY() < 168 && isBeefPresent && Greenfoot.mousePressed(null)) {
                if(!beefSpawned) {
                    beefSpawned = true;
                    System.out.println("Object Spawned!");
                    addObject(new Beef(plate, this), 125, 123);
                    orderTab.addToWallet(-4);
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
        return rightWasDown;
    }
    
    public boolean getKWasDown() {
        return leftWasDown;
    }
    
    public void resetPlate() {
        plate = orderTab.getPlate();
    }
}

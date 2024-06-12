import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Beef here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Beef extends Actor
{
    private GreenfootImage image = getImage();
    GreenfootSound sizzleSFX = new GreenfootSound("sizzle0.wav");
    private String[] rarityArray = {"raw", "rare", "medium", "well", "ash"};
    private String rarity = rarityArray[0];
    private int rarityCounter = 0;
    private int cookingTime = 0; //in seconds
    private int timeToCook = 3;
    private int cookingFramesElapsed = 0;
    private int resizedImageWidth = image.getWidth();
    private int resizedImageHeight = image.getHeight();
    private int imageScalePercent = 120; //will scale image up 20%
    private boolean isDragging = false;
    private boolean firstDrag = false;
    private boolean alreadyCooked = false;
    private boolean onGrill = false;
    private static final int FPS = 60;  // 60fps is the default on time slider
    
    private PlateBack plate;
 
    public Beef(PlateBack plate) {
        image.scale(resizedImageWidth,resizedImageHeight);
        this.plate = plate;
    }
    
    public void act()
    {
        checkLocation();
        cook();
        checkMouse();
    }
    
    private void checkMouse() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) {
            if (Greenfoot.mousePressed(this)) {
                isDragging = true;
                System.out.println(mouse.getX() + ", " + mouse.getY());
            }

            if (Greenfoot.mouseDragged(this) && isDragging) {
                setLocation(mouse.getX(), mouse.getY());
                
                if(!firstDrag) {
                    image.scale(image.getWidth()*imageScalePercent/100, 
                                image.getHeight()*imageScalePercent/100);
                    setImage(image); 
                    /* 
                     * makes image larger once to appear as though
                     * the player is lifting the object
                     */
                    sizzleSFX.stop();
                    firstDrag = true;
                } 
            }
            
            if (Greenfoot.mouseDragEnded(this)) {
                isDragging = false;
                firstDrag = false;
                
                if (getWorld().getObjectsAt(getX(), getY(), PlateBack.class).contains(plate)) {
                    plate.storeBeef(rarityCounter, getX(), getY());
                    getWorld().removeObject(this);
                }
                
                image.scale(resizedImageWidth,resizedImageHeight);
                setImage(image);
            }
        }
    }
    
    private void checkLocation() {
        //checks whether Beef is on the grill
        if(getX() > 360 && getY() > 110 
        && getX() < 510 && getY() < 227 && !isDragging) {
            onGrill = true; 
        } else {
            onGrill = false;
        }
    }
    
    private void cook() {
        if(onGrill) {
            cookingFramesElapsed++;
            sizzleSFX.playLoop();
            if (cookingFramesElapsed >= FPS) {
            cookingTime++;
            cookingFramesElapsed = 0;
            alreadyCooked = false;
            }
            
            if(cookingTime % timeToCook == 0 && cookingTime != 0 && !alreadyCooked) {
                alreadyCooked = true;
                if(rarityCounter < 4){
                    rarityCounter++;
                }
                rarity = rarityArray[rarityCounter];
                setImage("beef" + rarityCounter + ".png");
                image = getImage();
                image.scale(resizedImageWidth,resizedImageHeight);
            }
        }
    }    
}

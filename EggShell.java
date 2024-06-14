import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EggShell here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EggShell extends Actor
{
    private GreenfootImage image = getImage();
    private PlateBack plate;
    private CuttingBoard cuttingBoard;
    private CuttingKnife cuttingKnife;
    private int resizedImageWidth = image.getWidth()*11/10;
    private int resizedImageHeight = image.getHeight()*11/10;
    private int numChopsReq = 1; // chops it takes to become diced;
    private int numChopped = 0;
    private int imageScalePercent = 120; //will scale image up 20%
    private int framesElapsed = 0;
    private boolean isChopped = false;
    private boolean isDragging = false;
    private boolean firstDrag = false;
    private boolean onCuttingBoard = false;
    private boolean knifeOnEggShell = false;
    
    private boolean chopped = false; 
    GreenfootSound chopSFX = new GreenfootSound("chop.wav");
    
    public EggShell(PlateBack plate, CuttingBoard cuttingBoard, CuttingKnife cuttingKnife) {
        this.plate = plate;
        this.cuttingBoard = cuttingBoard;
        this.cuttingKnife = cuttingKnife;
    }
    
    public void act()
    {
        checkIfOnCuttingBoard();
        cutEggShell();        
        checkMouse();
    }
    
    private void checkMouse(){
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
                    
                    /* 
                     * makes image larger once to appear as though
                     * the player is lifting the object
                     */
                    firstDrag = true;
                } 
            }
            
            if (Greenfoot.mouseDragEnded(this)) {
                isDragging = false;
                firstDrag = false;
                
                if (getWorld().getObjectsAt(getX(), getY(), PlateBack.class).contains(plate)) {
                    plate.storeIngredient(2, isChopped, getX(), getY());
                    getWorld().removeObject(this);
                }
                
                image.scale(resizedImageWidth,resizedImageHeight);
                setImage(image);
            }
        }
    }
    
    private void checkIfOnCuttingBoard() {
        //checks whether EggShell is on the cuttingboard
        if(getWorld().getObjectsAt(getX(), getY(), CuttingBoard.class).contains(cuttingBoard)) {
            onCuttingBoard = true; 
        } else {
            onCuttingBoard = false;
        }
    }
    
    private void cutEggShell() {
        if (Greenfoot.mouseClicked(this) && onCuttingBoard && !knifeOnEggShell) {
            cuttingKnife.setLocation(getX()+50, getY()+35);
            cuttingKnife.setRotation(-30);
            chopSFX.play();
            numChopped++;
            knifeOnEggShell = true;
        }  
        if (knifeOnEggShell) {
            framesElapsed++;
            if(framesElapsed >= 10) {
                framesElapsed = 0;
                cuttingKnife.setLocation(550, 160);
                cuttingKnife.setRotation(0);
                knifeOnEggShell = false;
            }
        }
        if(numChopped >= numChopsReq && !isChopped) {
            setImage("eggshell1.png");
            image.scale(resizedImageWidth,resizedImageHeight);
            image = getImage();
            isChopped = true;
        }
    }
}

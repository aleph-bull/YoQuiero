import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class PeanutButter extends Actor
{
    private GreenfootImage image = getImage();
    private PlateBack plate;
    private CuttingBoard cuttingBoard;
    private CuttingKnife cuttingKnife;
    private BuildingWorld buildTab;
    private int imageWidth = image.getWidth();
    private int imageHeight = image.getHeight();
    private int numChopsReq = 8; // chops it takes to become diced;
    private int numChopped = 0;
    private int imageScalePercent = 120; //will scale image up 20%
    private int framesElapsed = 0;
    private boolean isChopped = false;
    private boolean isDragging = false;
    private boolean firstDrag = false;
    private boolean onCuttingBoard = false;
    private boolean localKnifeIsCutting = false;
    
    private boolean chopped = false; 
    GreenfootSound chopSFX = new GreenfootSound("chop.wav");
    
    public PeanutButter(PlateBack plate, CuttingBoard cuttingBoard, CuttingKnife cuttingKnife, BuildingWorld buildTab) {
        this.plate = plate;
        this.cuttingBoard = cuttingBoard;
        this.cuttingKnife = cuttingKnife;
        this.buildTab = buildTab;
    }
    
    public void act()
    {
        checkIfOnCuttingBoard();
        checkMouse();
        cutPeanutButter();   
    }
    
    private void checkMouse(){
        MouseInfo mouse = Greenfoot.getMouseInfo();
        boolean knifeAtObject = getWorld().getObjectsAt(getX(), getY(), CuttingKnife.class).contains(cuttingKnife);
        if (mouse != null) {
            if (Greenfoot.mousePressed(this) || (!buildTab.getKnifeIsCutting() && !localKnifeIsCutting && knifeAtObject && Greenfoot.mousePressed(cuttingKnife))) {
                isDragging = true;
            }
            
            if ((Greenfoot.mouseDragged(this) || (!buildTab.getKnifeIsCutting() && !localKnifeIsCutting && Greenfoot.mouseDragged(cuttingKnife))) && isDragging) {
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
            
            if (Greenfoot.mouseDragEnded(this) || (Greenfoot.mouseDragEnded(cuttingKnife))) {
                isDragging = false;
                firstDrag = false;
                
                if (getWorld().getObjectsAt(getX(), getY(), PlateBack.class).contains(plate)) {
                    plate.storeIngredient(3, isChopped, getX(), getY());
                    getWorld().removeObject(this);
                }
                
                image.scale(imageWidth,imageHeight);
            }
        }
    }
    
    private void checkIfOnCuttingBoard() {
        //checks whether PeanutButter is on the cuttingboard
        if(getWorld().getObjectsAt(getX(), getY(), CuttingBoard.class).contains(cuttingBoard)) {
            onCuttingBoard = true; 
        } else {
            onCuttingBoard = false;
        }
    }
    
    private void cutPeanutButter() {
        if (Greenfoot.mouseClicked(this) && onCuttingBoard && !buildTab.getKnifeIsCutting() && !localKnifeIsCutting) {
            cuttingKnife.setLocation(getX()+50, getY()+35);
            cuttingKnife.setRotation(-30);
            chopSFX.play();
            numChopped++;
            buildTab.setKnifeIsCutting(true);
            localKnifeIsCutting = true;
        }  
        if (localKnifeIsCutting && buildTab.getKnifeIsCutting()) {
            framesElapsed++;
            if(framesElapsed >= 10) {
                framesElapsed = 0;
                cuttingKnife.setLocation(550, 160);
                cuttingKnife.setRotation(0);
                buildTab.setKnifeIsCutting(false);
                localKnifeIsCutting = false;
            }
        }
        if(numChopped == numChopsReq && !isChopped) {
            setImage("peanutbutter1.png");
            image.scale(imageWidth,imageHeight);
            image = getImage();
            isChopped = true;
        }
    }
}

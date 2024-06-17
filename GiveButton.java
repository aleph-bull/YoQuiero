import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GiveButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GiveButton extends Actor
{
    private Color opaqueGreen = new Color(40, 255, 10, 200);
    private OrderingWorld orderTab;
    private PlateBack plate;
    private Customer customer;

    private boolean eating = false;
    private boolean finishedEating = false;
    private boolean animStage1Active = false;
    private boolean animStage2Active = false;
    private boolean animStage3Active = false;
    
    private int score;
    
    private int count = 0;
    private int framesElapsed = 0;
    
    public GiveButton(Customer customer, OrderingWorld orderTab, PlateBack plate) {
        this.orderTab = orderTab;
        this.plate = plate;
        this.customer = customer;
        GreenfootImage image = new GreenfootImage(66, 30);
        image.setColor(opaqueGreen);
        image.fill();
        image.setColor(Color.BLACK);
        image.drawRect(0, 0, image.getWidth()-1, image.getHeight()-1);
        setImage(image);
        image.drawString("Serve it!", 10, 20);
    }
    
    public void act()
    {
        checkClicked();
        if(finishedEating){
            gradeFood();
            customer.setOpinionStatement();
            plate.resetValues();
            finishedEating = false;
        }
        playEatingAnimation();
    }
    
    private void gradeFood() {
        ScoringSystem scoringSystem = new ScoringSystem(plate.getBeefRarity(), plate.getIngredientType(), plate.getIngredientIsChopped(), customer.getDesiredRarity(), customer.getDesiredBeefAmnt(), customer.getDesiredIngredient(), customer.getDesiredIngredientAmnt());
        
        score = scoringSystem.calculateScore();
        customer.setScore(score);
    }
    
    private void checkClicked() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) {
            if (Greenfoot.mousePressed(this)) {
                eating = true;
                animStage1Active = true;
                customer.hideTexts();
            }
        }
    }
    
    private void playEatingAnimation() {
        if(eating) {
            if(animStage1Active) {
                if(customer.getX() < plate.getX()){
                    customer.setLocation(customer.getX() + 4, customer.getY() + 1);
                } else {
                    animStage1Active = false;
                    animStage2Active = true;
                }
            }
            if(animStage2Active) {
                framesElapsed++;
                if(framesElapsed >= 10 && count <= 10) {
                    framesElapsed = 0;
                    if(count % 2 == 0) {
                        customer.setLocation(customer.getX(), customer.getY()+10);
                    } else {
                        customer.setLocation(customer.getX(), customer.getY()-10);
                    }
                    Greenfoot.playSound("dogTalking" + (count % 20) + ".wav");
                    count++;
                } else if (count >= 10){
                    animStage2Active = false;
                    finishedEating = true;
                    animStage3Active = true;
                }
            }
            if(animStage3Active) {
                if(customer.getX() >= 200){
                    customer.setLocation(customer.getX() - 4, customer.getY() - 1);
                } else {
                    animStage3Active = false;
                    customer.customerDidEatFood();
                    customer.removeTexts();
                }
            }
        }
    }
}

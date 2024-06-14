import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

/**
 * Write a description of class Customer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Customer extends Actor
{
    private static Random rand = new Random();
    
    private String[] rarityArray = {"raw", "rare", "medium", "well", "ash"};
        
    //dialog
    private String[] introDialog = {"Hi! I'd like ", "Yo yo yo! Gimme ", "What up! I want ", "I'm starving! I want ", 
                                    "Salutations good sir, I would be delighted to have ", "I'm so hungry, I could eat ", 
                                    "Mmmm I really need ", "Jeez, I could really go for some ", "Hey tubby, give me ", 
                                    "Hey Joe, fetch me ", "Woof. I am a dog who likes ", "I got an itching for some ",
                                    "Yum yum yum! I'm excited for ", "Yo quiero Taco Bell! No? Fine I'll just get ",
                                    "I am a dog. I like ", "Do you serve vegan options? I'll have ", "Hola! Yo quiero "};
    private String[][][] beefDialog = 
    {
        {//beef
            {"raw beef", " beef, still mooing", "the rarest beef you have", "fresh beef from the fridge", "some raw carne asada"},         //raw
            {"rare beef", "beef, still bleeding please", "beef with a light sear", "barely cooked beef" , "some rare carne asada"},        //rare
            {"medium beef", "beef, cooked to perfection", "beef that's not too rare, but not too well", "medium carne asada"},        //medium
            {"well done beef", "beef, cooked beyond perfection", "chewy rough beef", "beef, no blood at all", "well done carne asada"},     //well
            {"ashy beef", "non-vegan coal", "animal carbon", "cancer causing beef", "combustible beef rock", "carbón de res"}   //ash
        },
        { //veggie
            {}
        }
    };
    private String [][] ingredientDialog =
    {
        {//watermelon
            "watermelon", "green outside fruit", "red inside fruit", "that thing that is mostly water", "sweet summer fruit"
        },
        {//pumpkin
            "pumpkin", "orange outside fruit", "orange inside fruit", "orange squash", "autumn fruit", "halloween decor"
        },
        {//egg shells
            "egg shell", "white crunchy stuff", "dairy product", "calcium carbonate", "chicken birth pod", "breakfast garbage"
        },
        {//peanut butter
            "peanut butter", "squeezed oils from plant", "legumenade", "middle school contraband", "any legume item you have"
        }
    };
    
    private int desiredMeat = 0;
    private int desiredRarity = rand.nextInt(0, 5);
    private int desiredBeefAmnt = rand.nextInt(1, 4);
    private int desiredIngredient = rand.nextInt(0, 4);
    private int desiredIngredientAmnt = rand.nextInt(1, 6);
                                    
    private GreenfootImage image = getImage();
    private String name;
    private String completeDialog = introDialog[rand.nextInt(0, introDialog.length)] + 
                                    beefDialog[desiredMeat][desiredRarity][rand.nextInt(0, beefDialog[desiredMeat][desiredRarity].length)] +
                                    ". " + desiredBeefAmnt + " piece" + (desiredBeefAmnt == 1 ?"": "s") + " of that, and give me " + 
                                    desiredIngredientAmnt + " piece" + (desiredIngredientAmnt == 1 ?"": "s") +" of " +
                                    ingredientDialog[desiredIngredient][rand.nextInt(0, ingredientDialog[desiredIngredient].length)] + ".";
    private boolean movingUp = true;
    private boolean isSpeaking = false;
    private boolean textSpawned = false;
    private int resizedImageWidth = image.getWidth()/2;
    private int resizedImageHeight = image.getHeight()/2;
    
    public Customer(int customerTypeNumber, String name) {
        this.name = name;
        setImage("customer" + customerTypeNumber + ".png");
        image = getImage();
        System.out.println("customerSpawned");
        image.scale(resizedImageWidth,resizedImageHeight);
    }
    
    public void act()
    {
        moveToInitPosition();
        sayOrder();
    }
    
    //moves the customer to y 170 when the customer spawns 
    private void moveToInitPosition() {
        if(movingUp && getY() >= 170) {
            setLocation(getX(), getY() - 3);
        } else {
            movingUp = false;
            isSpeaking = true;
        }
    }
    
    private void sayOrder() {
        if(isSpeaking) {
            if(!textSpawned) {
                getWorld().addObject(new Textbox(completeDialog, this), 440, 120);
                getWorld().addObject(new Nametag(name), 365, 54);
                
                textSpawned = true;
            }
        }
    }
    
    public void changeIsSpeaking(boolean isSpeaking) {
         this.isSpeaking = isSpeaking;
    }
    
    public int getDesiredMeat(){
        return desiredMeat;
    }
}

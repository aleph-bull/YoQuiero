import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * Write a description of class orderingWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OrderingWorld extends World
{
    private static Random rand = new Random();
    private CookingWorld cookingTab = new CookingWorld(this);
    private PlateBack plate;
    
    private String[] customers = {"Taco Dog", "Walter Dog", "Melon Dog", "Not a Dog", "Stoop Dog", "Destroyer Dog", "Princess Dog", "Auraless Dog", "Suspicious Dog", "Hot Dog", "Imposter Dog"};
    private int customerTypeNumber;
    private String customerTypeName;
    private boolean customerOrdering = false; 
    private boolean plateDisplayed = false;
    private boolean justMovedWorld = false;
    
    private Register counter = new Register();
    
    public OrderingWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        setPaintOrder(PlateBack.class, Register.class,Customer.class);
        addObject(counter, 300, 200);
    }
    
    public void act(){
        if(Greenfoot.isKeyDown("k")){
            movedWorld(true);
            Greenfoot.setWorld(cookingTab);
        }
        
        newCustomer();
    }
    
    private void newCustomer(){
        if(!customerOrdering){
            customerOrdering = true;
            customerTypeNumber = rand.nextInt(0, customers.length);
            customerTypeName = customers[customerTypeNumber];
            
            addObject(new Customer(customerTypeNumber, customerTypeName), 200, 500);
            
        }
    }
    
    public void movedWorld(boolean justMovedWorld) {
        this.justMovedWorld = justMovedWorld;
    }
    
    public boolean didWorldMove() {
        return justMovedWorld;
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

public class OrderingWorld extends World
{
    private static Random rand = new Random();
    private MusicPlayer musicPlayer = MusicPlayer.getInstance();
    private WalletText walletText;
    private CookingWorld cookTab = new CookingWorld(this);
    private PlateBack plate;
    
    private int playerWallet = 35;
    
    private int plateDisplacementX = 40;
    private int plateDisplacementY = -35;
    
    private String[] customers = {"Taco Dog", "Walter Dog", "Melon Dog", "Not a Dog", "Stoop Dog", "Destroyer Dog", "Princess Dog", "Auraless Dog", "Skeptical Dog", "Hot Dog", "Canine-ibal Dog", "Blooming Dog"};
    private int customerTypeNumber;
    private String customerTypeName;
    private boolean customerOrdering = false; 
    private boolean justSwitchedWorld = false;
    private boolean justSwitchedWorldActor = false; //same thing but for actors since an actor's act() method runs after a world's
    
    private Register counter = new Register();
    
    public OrderingWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        plate = new PlateBack(this);
        walletText = new WalletText(this);
        setPaintOrder(WalletText.class, GiveButton.class, PlateFront.class, IngredientStatic.class, BeefStatic.class, PlateBack.class, Register.class,Customer.class);
        
        addObject(plate, 310 + plateDisplacementX, 320 + plateDisplacementY);
        addObject(walletText, 35, 385);
        addObject(new PlateFront(), 310 + plateDisplacementX, 320 + plateDisplacementY);
        addObject(counter, 300, 200);
    }
    
    public void act(){
        // if(plate == null) {
            // System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            // plate = new PlateBack(this);
            // addObject(plate, 310 + plateDisplacementX, 320 + plateDisplacementY);
        // }
        musicPlayer.checkAndPlayNext();
        if(didWorldSwitch()){
            plate = getPlate();
            switchedWorld(false);
        }
        
        if(Greenfoot.isKeyDown("k")){
            plate.removeBeefs();
            plate.removeIngredients();
            removeObject(plate);
            cookTab.addObject(plate, 310, 320);
            switchedWorld(true);
            Greenfoot.setWorld(cookTab);
        }
        
        newCustomer();
    }
    
    public void started() {
        musicPlayer.play();
    }
    
    public void stopped() {
        musicPlayer.pause();
    }
    
    private void newCustomer(){
        if(!customerOrdering){
            customerOrdering = true;
            customerTypeNumber = rand.nextInt(0, customers.length);
            customerTypeName = customers[customerTypeNumber];
            
            addObject(new Customer(customerTypeNumber, customerTypeName, this, plate), 200, 500);
        }
    }
    
    public void switchedWorld(boolean justSwitchedWorld) {
        this.justSwitchedWorld = justSwitchedWorld;
        if(justSwitchedWorld) {
            justSwitchedWorldActor = justSwitchedWorld;
        }
    }
    
    public void switchedWorldActor(boolean justSwitchedWorld) {
        justSwitchedWorldActor = justSwitchedWorld;
    }
    
    public boolean didWorldSwitch() {
        return justSwitchedWorld;
    }
    
    public boolean didWorldSwitchActor() {
        return justSwitchedWorldActor;
    }
    
    public int getPlateDisplacement(boolean getX) {
        if(getX) {
            return plateDisplacementX;
        }
        return plateDisplacementY;
    }
    
    public void makeCustomerOrderingFalse() {
        customerOrdering = false;
    }
    
    public PlateBack getPlate(){
        return plate;
    }
    
    public BuildingWorld getBuildTab(){
        return cookTab.getBuildTab();
    }
    
    public CookingWorld getCookTab() {
        return cookTab;
    }
    
    public int getWallet() {
        return playerWallet;
    }
    
    public void addToWallet(int money) {
        playerWallet += money;
    }
}

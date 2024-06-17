import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

public class Customer extends Actor
{
    private static Random rand = new Random();
    
    private OrderingWorld orderTab;
    private PlateBack plate;
    
    private String[] rarityArray = {"raw", "rare", "medium", "well", "ash"};
        
    // dialog arrays for various customer interactions
    private String[] introDialog = {"Hi! I'd like ", "Yo yo yo! Gimme ", "What up! I want ", "I'm starving! I want ", 
                                    "Salutations good sir, I would be delighted to have ", "I'm so hungry, I could eat ", 
                                    "Mmmm I really need ", "Jeez, I could really go for some ", "Hey tubby, give me ", 
                                    "Hey Joe, fetch me ", "Woof. I am a dog who likes ", "I got an itching for some ",
                                    "Yum yum yum! I'm excited for ", "Yo quiero Taco Bell! No? Fine I'll just get ",
                                    "I am a dog. I like ", "Do you serve vegan options? I'll have ", "Hola! Yo quiero ",
                                    "Hey there! I'll have ", "Feed me! I need ", "Greetings! I'd love ",
                                    "Surprise me with ", "What do you have? I'll take ", "Hungry as a wolf! Give me ",
                                    "Feeling peckish! I'll take ", "Craving something tasty! ", "Bring me something yummy, like ",
                                    "What’s cooking? I'll have ", "Need a quick bite! How about ", "Feeling fancy, I want ",
                                    "Need fuel! Give me ", "Hurry up! I’ll take ", "Feeling adventurous! How about ",
                                    "Feeling daring! Give me ", "I’m ravenous! Serve me "};
                                    
    private String[][] beefDialog = 
    {//beef
        {"raw beef", " beef, still mooing", "the rarest beef you have", "fresh beef from the fridge", "some raw carne asada", "raw steak", "uncooked beef", "meat straight from the cow"},         //raw
        {"rare beef", "beef, still bleeding please", "beef with a light sear", "barely cooked beef" , "some rare carne asada", "lightly cooked steak", "beef, just kissed by heat", "barely seared meat"},        //rare
        {"medium beef", "beef, cooked to perfection", "beef that's not too rare, but not too well", "medium carne asada", "medium rare steak", "properly grilled beef", "meat, cooked just right"},        //medium
        {"well done beef", "beef, cooked beyond perfection", "chewy rough beef", "beef, no blood at all", "well done carne asada", "well cooked steak", "thoroughly grilled beef", "meat, no pink"},     //well
        {"ashy beef", "non-vegan coal", "animal carbon", "cancer causing beef", "combustible beef rock", "carbón de res", "beef, burnt to a crisp", "blackened steak"}   //ash
    };

    private String [][] ingredientDialog =
    {
        {//pumpkin
            "pumpkin", "orange outside fruit", "orange inside fruit", "orange squash", "autumn fruit", "halloween decor", "squash", "pie fruit"
        },
        {//watermelon
            "watermelon", "green outside fruit", "red inside fruit", "that thing that is mostly water", "sweet summer fruit", "summer fruit", "hydrating fruit"
        },
        {//egg shells
            "egg shell", "white crunchy stuff", "dairy product", "calcium carbonate", "chicken birth pod", "breakfast garbage", "crunchy shells", "breakfast remains", "white scraps"
        },
        {//peanut butter
            "peanut butter", "squeezed oils from plant", "legumenade", "middle school contraband", "any legume item you have", "yummy spread", "sticky stuff"
        }
    };
    
    private String[] choppingOpinionDialog = {"Could use some chopping... ", "Use a cutting board for once! ", "These toppings hurt to eat, chop it up! ",
                                              "It barely fit into my mouth! ", "I think I dislocated my jaw! I may sue! ", "Chop it finer! ", "Could use more chopping. ", 
                                              "These chunks are huge! ", "Please dice it! ", "It’s too chunky! ", "Chop it better next time. ", "Can you mince it? Jeez. ", 
                                              "Needs finer chopping. ", "Too big to chew! "};
                                    
    private String[] ingredientOpinionDialog = {"I did not ask for these toppings! ", "Taco Bell would've done my toppings better... ", "Did you mess up my toppings? ",
                                                "Wrong topping my friend. ", "I don't think I asked for these toppings ", "I do not like how these toppings taste. ", 
                                                "I am not the father to these toppings. ", "These toppings aren't mine. ", "WRONG! Wrong topping! ", "INCORRECT!! Wrong topping! ",
                                                "EHH!! Wrong toppings! "};
    
    private String[] noIngredientDialog = {"No toppings? ", "Give me my toppings! ", "I guess you don't like me enough to give me toppings. ", "This is so sad, where are my toppings. ",
                                           "Are these toppings invisible? ", "Do I have to protest for toppings? ", "I think you forgot something. ", "No toppings?", 
                                           "Where are my toppings? ", "I need my toppings! ", "Forgot the toppings? ", "Toppings missing! ", "No toppings at all? ", 
                                           "This needs toppings. ", "I wanted toppings! ", "Where are my TOPPINGS! "};
    
    private String[] noBeefDialog = {"No beef? What is even the point. ", "Did I just eat nothing? ", "Where is my beef! I will riot! ", "If it were up to me I would use you for beef... ",
                                     "It's not a full meal without meat! ", "Dogs like meat! Where is my meat! ", "Where’s the beef? ", "No meat? ", "Missing the main part! ", 
                                     "No beef here! ", "I'm a dog, I need my protein! ", "I wanted meat! ", "Meat is missing! ", "No beef? Really? "};
                                    
    private String[] rarityOpinionDialog = {"Mmmm, wrong rarity... ", "This is not the perfect sear. ", "Why does this meat taste funny? ", "I don't like how my meat is cooked. ",
                                            "I didn't ask for this rarity. ", "WRONG! Wrong rarity! ", "Wrong cook level! ", "Not cooked right. ",
                                            "You're INCORRECT! Wrong rarity! ", "I wanted different rarity! ", "Not what I wanted. "};
    
    private String[] goodOpinionDialog = {"You did great! This tasted amazing", "Yummy! That was the BOMB.COM!", "Absolutely delicous, I am delighted to be served by such a talented chef.",
                                          "Superb! Ultra tasty! I'll come again!", "Gracias! Muy bien!", "Wow this tastes like Taco Bell! Amazing! I don't quiero Taco Bell anymore.",
                                          "This tastes SO GOOD! Will you be my owner?", "This is the greatest meal I have eaten!", "Yummy! I feel jealous of myself 5 seconds ago!",
                                          "Bone apple teeth levels of good!", "The chef needs a raise! Heres a tip!", "Delicious food! Yum yum yum!", "This is so Taco Bell esque!", "This is one of the BEST! Thanks!",
                                          "I don't think anything can top that meal!", "I have ascended after this meal. Thank you for enlightening me to what this world has to offer.",
                                          "This is SUPER DUPER TASTY! Excellent!"};
    
    private String[] mediumOpinionDialog = {"This was alright I guess...", "You did just OK. ", "Wow this tastes mid...", "I almost like it... not really.", "Jeez... it's really mediocre",
                                            "Not terrible, but no compliments to the chef.", "It was okay... I still quiero Taco Bell.", "Just okay.", "Not bad, not good.",
                                            "Meh.", "Could be better.", "It’s fine.", "It's alright.", "Not your best.", "Pretty average.", "This is one of the meals of all time."};
                                    
    private String[] badOpinionDialog = {"Horrible!", "You did NOT do good in the SLIGHTEST!", "I wish I went to Taco Bell...", "Is this even safe to consume?", "I HATED eating this!",
                                         "Fire the chef! Please!", "Not good at all.", "What is wrong with you?", "I don't like you.", "My taste buds suffer...", "Horrible taste.", 
                                         "Not good at all.", "Never again."};
                                    
    private String[] horribleOpinionDialog = {"You make me want to commit crime", "This meal gave grounds to sue.", "I'm stealing your money.", "I'm grabbing the tip jar.",
                                              "I'm stealing 5 bucks, hope it was worth it.", "You stole my joy so I'll steal your money.", "Your 5 bucks is now mine!"};
    
    // integer variables for customer preferences
    private int desiredRarity = rand.nextInt(5);
    private int desiredBeefAmnt = rand.nextInt(1, 6);
    private int desiredIngredient = rand.nextInt(4);
    private int desiredIngredientAmnt = rand.nextInt(1, 6);
    
    private int score;
                                    
    private GreenfootImage image = getImage();
    private String name;
    private String completeOrderDialog = introDialog[rand.nextInt(introDialog.length)] + 
                                    beefDialog[desiredRarity][rand.nextInt(beefDialog[desiredRarity].length)] +
                                    ". " + desiredBeefAmnt + " serving" + (desiredBeefAmnt == 1 ?"": "s") + " of that, and give me " + 
                                    desiredIngredientAmnt + " serving" + (desiredIngredientAmnt == 1 ?"": "s") +" of " +
                                    ingredientDialog[desiredIngredient][rand.nextInt(ingredientDialog[desiredIngredient].length)] + ".";

    private String completeOpinionDialog = "";
    private GiveButton giveButton;
    private Textbox textbox;
    private Nametag nametag;
    
    private boolean movingUp = true;
    private boolean movingDown = false;
    private boolean isSpeaking = false;
    private boolean finishedSpeaking = false;
    private boolean textSpawned1 = false;
    private boolean textSpawned2 = false;
    private boolean atInitPosition = false;
    private boolean customerAteFood = false;
    private boolean opinionStatementMade = false;
    private boolean isWaitingDone = false;
    private int resizedImageWidth = image.getWidth()/2;
    private int resizedImageHeight = image.getHeight()/2;
    private int waitFrames = 0;
    

    public Customer(int customerTypeNumber, String name, OrderingWorld orderTab, PlateBack plate) {
        this.orderTab = orderTab;
        this.plate = plate;
        this.name = name;
        giveButton = new GiveButton(this, orderTab, plate);
        setImage("customer" + customerTypeNumber + ".png");
        image = getImage();
        image.scale(resizedImageWidth,resizedImageHeight);
    }
    
    // main act method
    public void act()
    {
        moveToInitPosition();
        sayOrder();
        sayOpinion();
    }
    
    // moves the customer to y 170 when the customer spawns 
    private void moveToInitPosition() {
        if(!atInitPosition){
            if(movingUp && getY() >= 170) {
                setLocation(getX(), getY() - 3);
            } else {
                movingUp = false;
                isSpeaking = true;
                atInitPosition = true;
            }
        }
    }
    
    private void sayOrder() {
        if(isSpeaking && !customerAteFood) {
            if(!textSpawned1) {
                textbox = new Textbox(completeOrderDialog, this);
                nametag = new Nametag(name);
                getWorld().addObject(textbox, 440, 120);
                getWorld().addObject(nametag, 365, 54);
                
                textSpawned1 = true;
            }
        }
        if(finishedSpeaking && !isSpeaking && textSpawned1){
            getWorld().addObject(giveButton, 333, 187);
            finishedSpeaking = false;
        }
    }
    
    private void sayOpinion() {
        if(customerAteFood && !textSpawned2) {
            textSpawned1 = false;
            textbox = new Textbox(completeOpinionDialog, this);
            isSpeaking = true;
            textSpawned2 = true;
            getWorld().addObject(textbox, 440, 120);
        }
        if(customerAteFood && finishedSpeaking) {
            waitFrames++;
            if(!movingDown && waitFrames >= 20){
                orderTab.addToWallet(score);
                getWorld().removeObject(nametag);
                getWorld().removeObject(textbox);
                movingDown = true;
                isWaitingDone = true;
            }
            
            if(isWaitingDone) {
                if(movingDown && getY() < 390) {
                    setLocation(getX(), getY() + 3);
                } else {
                    movingDown = false;
                    orderTab.makeCustomerOrderingFalse();
                    getWorld().removeObject(this);
                }
            }
        }
    }
    
    public void removeTexts () {
        getWorld().removeObject(textbox);
        getWorld().removeObject(giveButton);
    }
    
    public void hideTexts () {
        textbox.getImage().setTransparency(0);
        giveButton.getImage().setTransparency(0);
        giveButton.setLocation(0,0);
    }
    
    public void changeIsSpeaking(boolean isSpeaking) {
         this.isSpeaking = isSpeaking;
         if(!isSpeaking){
             finishedSpeaking = true;
         }
    }
    
    //method to set opinion statement before food disapears
    public void setOpinionStatement(){
        if(!opinionStatementMade) {
            int choppedIngredientAmnt = 0;
            int correctIngredientAmnt = 0;
            int correctRarityAmnt = 0;
            
            boolean hasCritque = false;
            
            //counts various stats
            for(int i = 0; i < plate.getIngredientIsChopped().size(); i++){
                choppedIngredientAmnt++;
            }
            for(int i = 0; i < plate.getIngredientType().size(); i++){
                if(plate.getIngredientType().get(i) == desiredIngredient) {
                    correctIngredientAmnt++;
                }
            }
            for(int i = 0; i < plate.getBeefRarity().size(); i++) {
                if(plate.getBeefRarity().get(i) == desiredRarity) {
                    correctRarityAmnt++;
                }
            }
            
            if(plate.getIngredientType().size() > 0) {
                if(100*choppedIngredientAmnt/desiredIngredientAmnt < 50){
                    int randomIndex = rand.nextInt(choppingOpinionDialog.length);
                    completeOpinionDialog = completeOpinionDialog + choppingOpinionDialog[randomIndex];
                    hasCritque = true;
                }
                if(100*correctIngredientAmnt/desiredIngredientAmnt < 50){
                    int randomIndex = rand.nextInt(ingredientOpinionDialog.length);
                    completeOpinionDialog = completeOpinionDialog + ingredientOpinionDialog[randomIndex];
                    hasCritque = true;
                }
            } else {
                int randomIndex = rand.nextInt(noIngredientDialog.length);
                completeOpinionDialog = completeOpinionDialog + noIngredientDialog[randomIndex];
                hasCritque = true;
            }
            
            if(plate.getBeefRarity().size() > 0) {
                if(100*correctRarityAmnt/desiredBeefAmnt < 50){
                    int randomIndex = rand.nextInt(rarityOpinionDialog.length);
                    completeOpinionDialog = completeOpinionDialog + rarityOpinionDialog[randomIndex];
                    hasCritque = true;
                }
            } else {
                int randomIndex = rand.nextInt(noBeefDialog.length);
                completeOpinionDialog = completeOpinionDialog + noBeefDialog[randomIndex];
                hasCritque = true;
            }
            opinionStatementMade = true;
            
            if(score >= 23) {
                if(hasCritque){
                    completeOpinionDialog = completeOpinionDialog + "Yet... ";
                }
                int randomIndex = rand.nextInt(goodOpinionDialog.length);
                completeOpinionDialog = completeOpinionDialog + goodOpinionDialog[randomIndex];
                score += 5;
            } else if (score >= 15) {
                int randomIndex = rand.nextInt(mediumOpinionDialog.length);
                completeOpinionDialog = completeOpinionDialog + mediumOpinionDialog[randomIndex];
            } else if (score >= 8){
                int randomIndex = rand.nextInt(badOpinionDialog.length);
                completeOpinionDialog = completeOpinionDialog + badOpinionDialog[randomIndex];
                score -= 5;
            } else {
                int randomIndex = rand.nextInt(horribleOpinionDialog.length);
                completeOpinionDialog = completeOpinionDialog + horribleOpinionDialog[randomIndex];
                score = -5; //makes it so you lose money
            }
        }
    }
    
    public int getDesiredRarity() {
        return desiredRarity;
    }
    
    public int getDesiredBeefAmnt(){
        return desiredBeefAmnt;
    }
    
    public int getDesiredIngredient() {
        return desiredIngredient;
    }
    
    public int getDesiredIngredientAmnt() {
        return desiredIngredientAmnt;
    }
    
    public void setScore(int score) {
        this.score = score;
    }
    
    public void customerDidEatFood(){
        customerAteFood = true;
    }
}

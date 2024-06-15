import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

public class PlateBack extends Actor
{
    private Customer customer;
    private OrderingWorld orderTab;
    private BuildingWorld buildTab;
    private CookingWorld cookTab;
    
    private ArrayList <Integer> beefRarity = new ArrayList <>();
    private ArrayList <BeefStatic> beefPicList = new ArrayList <BeefStatic>();
    private ArrayList <Object> beefPosition = new ArrayList <Object>();
    
    private ArrayList <Integer> ingredientType = new ArrayList <>();
    private ArrayList <Boolean> ingredientIsChopped = new ArrayList <>();
    private ArrayList <IngredientStatic> ingredientPicList = new ArrayList <IngredientStatic>();
    private ArrayList <Object> ingredientPosition = new ArrayList <Object>();
    
    private int beefAmount;
    private int ingredientAmount;
    
    private boolean valuesReset = false;
    
    
    public PlateBack(OrderingWorld orderTab) {
        this.orderTab = orderTab;
        this.buildTab = orderTab.getBuildTab();
        this.cookTab = orderTab.getCookTab();
    }
    
    public void act()
    {
        if(!valuesReset) {
            
            if(orderTab.didWorldSwitchActor()){
                int displacementY = 0;
                int displacementX = 0;
                
                if(getWorld() == buildTab) {
                    displacementY = buildTab.getPlateDisplacement(false);
                    displacementX = buildTab.getPlateDisplacement(true);
                } else if(getWorld() == orderTab) {
                    displacementY = orderTab.getPlateDisplacement(false);
                    displacementX = orderTab.getPlateDisplacement(true);
                }
                
                
                for(int i = 0; i < beefAmount; i++) {
                    BeefStatic beefImage = beefPicList.get(i);
                    
                    int[] pos = (int[]) beefPosition.get(i);
                    getWorld().addObject(beefImage, pos[0] + displacementX, 
                                                    pos[1] + displacementY);
                    beefImage.setImage("beef" + beefRarity.get(i) + ".png");
                    
                    
                }
                for(int i = 0; i < ingredientAmount; i++) {
                    IngredientStatic ingredientImage = ingredientPicList.get(i);
                    
                    int[] pos = (int[]) ingredientPosition.get(i);
                    getWorld().addObject(ingredientImage, pos[0] + displacementX, 
                                                          pos[1] + displacementY);
                    if(ingredientType.get(i) == 0) {
                        ingredientImage.setImage("pumpkin0.png");
                        if(ingredientIsChopped.get(i)) {
                                ingredientImage.setImage("pumpkin1.png");
                        }
                        orderTab.switchedWorldActor(false);
                    } else if(ingredientType.get(i) == 1) {
                        ingredientImage.setImage("watermelon0.png");
                        if(ingredientIsChopped.get(i)) {
                                ingredientImage.setImage("watermelon1.png");
                        }
                        orderTab.switchedWorldActor(false);
                    } else if(ingredientType.get(i) == 2) {
                        ingredientImage.setImage("eggshell0.png");
                        if(ingredientIsChopped.get(i)) {
                                ingredientImage.setImage("eggshell1.png");
                        }
                        orderTab.switchedWorldActor(false);
                    } else if(ingredientType.get(i) == 3) {
                        ingredientImage.setImage("peanutbutter0.png");
                        if(ingredientIsChopped.get(i)) {
                                ingredientImage.setImage("peanutbutter1.png");
                        }
                    }
                    
                }
                orderTab.switchedWorldActor(false);   
            }
            
        } else if(valuesReset){
            valuesReset = false;
        }
    }
    public void storeBeef(int rarity, int xCoord, int yCoord){
        orderTab.addToWallet(-5);
        BeefStatic beefImage = new BeefStatic();
        beefRarity.add(rarity);
        beefPosition.add(new int[] {xCoord, yCoord});
        beefAmount = beefRarity.size();
        
        int[] pos = (int[]) beefPosition.get(beefAmount - 1);
        
        
        beefPicList.add(beefImage);
        getWorld().addObject(beefImage, pos[0], pos[1]);
        beefImage.setImage("beef" + rarity + ".png");
    }
    
    public void storeIngredient(int type, boolean isChopped, int xCoord, int yCoord){
        orderTab.addToWallet(-2);
        IngredientStatic ingredientImage = new IngredientStatic();
        ingredientPicList.add(ingredientImage);
        int displacementY = buildTab.getPlateDisplacement(false);
        int displacementX = buildTab.getPlateDisplacement(true);
        ingredientType.add(type);
        ingredientIsChopped.add(isChopped);
        ingredientPosition.add(new int[] {xCoord - displacementX, yCoord - displacementY});
        ingredientAmount = ingredientType.size();
        
        //System.out.println(beefRarity.get(beefAmount - 1));
        int[] pos = (int[]) ingredientPosition.get(ingredientAmount - 1);
        
        
        
        getWorld().addObject(ingredientImage, xCoord, yCoord);
        if(type == 0) {
            ingredientImage.setImage("pumpkin0.png");
            if(isChopped){
                ingredientImage.setImage("pumpkin1.png");
            }
        } else if(type == 1) {
            ingredientImage.setImage("watermelon0.png");
            if(isChopped){
                ingredientImage.setImage("watermelon1.png");
            }
        } else if(type == 2) {
            ingredientImage.setImage("eggshell0.png");
            if(isChopped){
                ingredientImage.setImage("eggshell1.png");
            }
        } else if(type == 3) {
            ingredientImage.setImage("peanutbutter0.png");
            if(isChopped){
                ingredientImage.setImage("peanutbutter1.png");
            }
        } 
    }
    
    //removes beef to prevent stacking inf. beef
    public void removeBeefs() {
        for(BeefStatic bs : beefPicList) {
            getWorld().removeObject(bs);
        }
    }
    
    public void removeIngredients() {
        for(IngredientStatic is : ingredientPicList){
            getWorld().removeObject(is);
        }
    }
    
    public void resetValues() {
        removeIngredients();
        removeBeefs();
        
        beefRarity = new ArrayList <>();
        beefPicList = new ArrayList <BeefStatic>();
        beefPosition = new ArrayList <Object>();
    
        ingredientType = new ArrayList <>();
        ingredientIsChopped = new ArrayList <>();
        ingredientPicList = new ArrayList <IngredientStatic>();
        ingredientPosition = new ArrayList <Object>();
        
        beefAmount = 0;
        ingredientAmount = 0;
        
        valuesReset = true;
    }
    
    public ArrayList <Integer> getBeefRarity() {
        return beefRarity;
    }
    
    public ArrayList <Integer> getIngredientType() {
        return ingredientType;
    }
    
    public ArrayList <Boolean> getIngredientIsChopped() {
        return ingredientIsChopped;
    }
}

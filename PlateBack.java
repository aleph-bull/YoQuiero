import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

public class PlateBack extends Actor
{
    private Customer customer;
    private ArrayList <Integer> beefArray = new ArrayList <>();
    
    public PlateBack() {
        
    }
    
    public void act()
    {
        // Add your action code here.
    }
    
    public void storeBeef(int rarity, int xCoord, int yCoord){
        BeefStatic beefImage = new BeefStatic();
        beefArray.add(rarity);
        System.out.println(beefArray.get(beefArray.size() - 1));
        getWorld().addObject(beefImage, xCoord, yCoord);
        beefImage.setImage("beef" + rarity + ".png");
    }
}

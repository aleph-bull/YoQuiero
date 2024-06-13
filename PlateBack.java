import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

public class PlateBack extends Actor
{
    private Customer customer;
    private OrderingWorld orderTab;
    
    private ArrayList <BeefStatic> beefPicList = new ArrayList <BeefStatic>();
    private ArrayList <Integer> beefArray = new ArrayList <>();
    private ArrayList <Object> beefPosition = new ArrayList <Object>();
    
    private int beefAmount;
    
    
    public PlateBack(OrderingWorld orderTab) {
        this.orderTab = orderTab;
    }
    
    public void act()
    {
        //System.out.println(beefArray);
        
        if(getWorld() == orderTab && orderTab.didWorldSwitchActor()){
            System.out.println("XXXXX");
            
            
            for(int i = 0; i < beefAmount; i++) {
                BeefStatic beefImage = new BeefStatic();
                beefPicList.add(beefImage);
                int[] pos = (int[]) beefPosition.get(i);
                getWorld().addObject(beefImage, pos[0], pos[1]);
                beefImage.setImage("beef" + beefArray.get(i) + ".png");
                orderTab.SwitchedWorldActor(false);
                
            }
        }
        orderTab.SwitchedWorldActor(false);
    }
    
    public void storeBeef(int rarity, int xCoord, int yCoord){
        BeefStatic beefImage = new BeefStatic();
        beefArray.add(rarity);
        beefPosition.add(new int[] {xCoord, yCoord});
        beefAmount = beefArray.size();
        
        //System.out.println(beefArray.get(beefAmount - 1));
        int[] pos = (int[]) beefPosition.get(beefAmount - 1);
        
        System.out.println("This is: " + pos[0]);
        
        getWorld().addObject(beefImage, pos[0], pos[1]);
        beefImage.setImage("beef" + rarity + ".png");
    }
    
    //removes beef to prevent stacking inf. beef
    public void removeBeefs() {
        for(BeefStatic bs : beefPicList) {
            getWorld().removeObject(bs);
        }
    }
}

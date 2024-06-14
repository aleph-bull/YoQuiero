import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

public class PlateBack extends Actor
{
    private Customer customer;
    private OrderingWorld orderTab;
    private BuildingWorld buildTab;
    
    private ArrayList <BeefStatic> beefPicList = new ArrayList <BeefStatic>();
    private ArrayList <Integer> beefArray = new ArrayList <>();
    private ArrayList <Object> beefPosition = new ArrayList <Object>();
    
    private int beefAmount;
    
    
    public PlateBack(OrderingWorld orderTab) {
        this.orderTab = orderTab;
        this.buildTab = orderTab.getBuildTab();
    }
    
    public void act()
    {
        if((getWorld() == orderTab || getWorld() == buildTab) && orderTab.didWorldSwitchActor()){
            System.out.println("XXXXX");
            int displacementY = orderTab.getPlateDisplacement(false);
            int displacementX = orderTab.getPlateDisplacement(true);
            
            if(getWorld() == buildTab) {
                displacementY = buildTab.getPlateDisplacement(false);
                displacementX = buildTab.getPlateDisplacement(true);
            }
            
            for(int i = 0; i < beefAmount; i++) {
                BeefStatic beefImage = new BeefStatic();
                beefPicList.add(beefImage);
                int[] pos = (int[]) beefPosition.get(i);
                getWorld().addObject(beefImage, pos[0] + displacementX, 
                                                pos[1] + displacementY);
                beefImage.setImage("beef" + beefArray.get(i) + ".png");
                orderTab.switchedWorldActor(false);
                
            }
        }
        orderTab.switchedWorldActor(false);
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

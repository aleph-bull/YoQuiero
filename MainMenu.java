import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MainMenu extends World
{
    OrderingWorld orderTab = new OrderingWorld();
    PlayButton playButton = new PlayButton(orderTab);
    InstructionsButton instructionsButton = new InstructionsButton();
    
    public MainMenu()
    {    
        super(600, 400, 1); 
        addObject(playButton, 110, 190);
        addObject(instructionsButton, 190, 280);
    }
}

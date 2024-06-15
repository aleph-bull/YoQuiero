import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class LosingScreen extends World
{
    private MusicPlayer musicPlayer = MusicPlayer.getInstance();
    public LosingScreen()
    {    
        super(600, 400, 1); 
        Greenfoot.playSound("lose.mp3");
    }
    public void act() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        musicPlayer.checkAndPlayNext();
        if (Greenfoot.mousePressed(this)) {
            Greenfoot.setWorld(new MainMenu());
        }
    }  
    
    public void started() {
        musicPlayer.play();
    }
    
    public void stopped() {
        musicPlayer.pause();
    }
}

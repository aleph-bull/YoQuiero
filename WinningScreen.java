import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class WinningScreen extends World
{
    private MusicPlayer musicPlayer = MusicPlayer.getInstance();
    public WinningScreen()
    {    
        super(600, 400, 1); 
        Greenfoot.playSound("win.mp3");
        musicPlayer.stop();
    }
    public void act() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (Greenfoot.mousePressed(this)) {
            Greenfoot.setWorld(new MainMenu());
        }
    }  
    
    // public void started() {
        // musicPlayer.play();
    // }
    
    // public void stopped() {
        // musicPlayer.pause();
    // }
}

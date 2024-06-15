import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class WalletText extends Actor
{
    private OrderingWorld orderTab;
    private Color opaqueWhite = new Color(255, 255, 255, 200);
    
    public WalletText(OrderingWorld orderTab)
    {
        this.orderTab = orderTab;
        GreenfootImage image = new GreenfootImage(70, 30);
        image.setColor(opaqueWhite);
        image.fill();
        image.setColor(Color.BLACK);
        image.drawRect(0, 0, image.getWidth()-1, image.getHeight()-1);
        setImage(image);
        String money = Integer.toString(orderTab.getWallet());
        image.drawString("$" + money, 10, 20);
    }
    
    public void act(){
        GreenfootImage image = getImage();
        image.clear();
        
        image.setColor(opaqueWhite);
        image.fill();
        image.setColor(Color.BLACK);
        image.drawRect(0, 0, image.getWidth()-1, image.getHeight()-1);
        setImage(image);
        String money = Integer.toString(orderTab.getWallet());
        image.drawString("$" + money, 10, 20);
        if(orderTab.getWallet() >= 500) {
            Greenfoot.setWorld(new MainMenu());
        }
        if(orderTab.getWallet() < 0) {
            Greenfoot.setWorld(new MainMenu());
        }
    }
}

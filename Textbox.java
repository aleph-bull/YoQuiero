import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Textbox extends Actor
{
    private String dialog;
    private int textIndex = 0;
    private int frameCount = 0;
    private int speed = 3; // Controls the speed of typewriting effect
    private int spacePauseDuration = 2; // Pause for spaces
    private int lineHeight = 20; // Height of each line of text
    private int margin = 10; // Margin from the edges
    private int maxWidth = 290; // Maximum width of text area
    private int letterCount = 0; // Count of letters typed, used for animation
    private Customer customer;
    private Color opaqueWhite = new Color(255, 255, 255, 200);
    private boolean isSpacePause = false;

    public Textbox(String dialog, Customer customer) {
        this.customer = customer;
        this.dialog = dialog;
        GreenfootImage image = new GreenfootImage(280, 100);
        image.setColor(opaqueWhite);
        image.fill();
        image.setColor(Color.BLACK);
        image.drawRect(0, 0, image.getWidth()-1, image.getHeight()-1);
        setImage(image);
    }

    public void act()
    {
        if (isSpacePause) {
            if (frameCount >= spacePauseDuration) {
                isSpacePause = false;
                frameCount = 0;
            }
        } else {
            if (frameCount % speed == 0 && textIndex < dialog.length()) {
                char currentChar = dialog.charAt(textIndex);
                typeWrite(currentChar);
                textIndex++;
                if (currentChar == ' ') {
                    isSpacePause = true;
                    frameCount = 0;
                }
            }
        }
        frameCount++;
    }

    private void typeWrite(char letter) {
        GreenfootImage image = getImage();
        String currentText = dialog.substring(0, textIndex + 1);

        image.clear();
        image.setColor(opaqueWhite);
        image.fill();
        image.setColor(Color.BLACK);
        image.drawRect(0, 0, image.getWidth()-1, image.getHeight()-1);

        
        // Wrap text
        String[] words = currentText.split(" ");
        String line = "";
        int y = 4 + lineHeight;
        
        for (String word : words) {
            if (getTextWidth(line + word) < maxWidth) {
                line += word + " ";                 // Add word to line if fit
            } else {
                image.drawString(line, margin, y);  // Draw the current line
                y += lineHeight;                    // Move to next line
                line = word + " ";                  // Start new line
            }
        }
        image.drawString(line, margin, y); // writes last line
        
        if(letter != ' ') {
            animateCustomerSpeaking(letterCount);
            letterCount++;
        }

        setImage(image);
    }

    // Approximate text width by creating a temporary image
    private int getTextWidth(String text) {
        GreenfootImage tempImage = new GreenfootImage(text, 18, Color.BLACK, null);
        return tempImage.getWidth();
    }
    
    //moves customer up and down
    private void animateCustomerSpeaking(int count) {
        if(count % 2 == 0) {
            customer.setLocation(customer.getX(), customer.getY()-10);
        } else {
            customer.setLocation(customer.getX(), customer.getY()+10);
        }
        System.out.println(count % 20);
        Greenfoot.playSound("dogTalking" + (count % 20) + ".wav");
    }
}
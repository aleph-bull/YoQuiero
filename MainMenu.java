import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/** Yo Quiero!
    Image Credits:
        https://steelmadeusa.com/products/flat-top-grill-custom-size
        https://www.istockphoto.com/photos/frying-pan-top-view
        https://www.istockphoto.com/photos/silver-metal-lunch-box
        https://stock.adobe.com/ca/search?k=marble+texture
        https://project-delta-a.fandom.com/wiki/Cash_Register
        https://www.textures.com/browse/fine-wood/14119
        https://www.istockphoto.com/illustrations/store-window
        https://stock.adobe.com/search?k=seamless+brick+texture
        https://minecraft.fandom.com/wiki/Java_Edition_history_of_textures/Paintings
        https://www.artsy.net/article/artsy-editorial-story-worlds-famous-desktop-background
        https://www.canadiantire.ca/en/pdp/naturae-decor-artificial-indoor-fiddle-leaf-plant-in-black-pot-27-in-0595909p.html
        https://www.istockphoto.com/photos/wood-table-top-side-view
        https://www.deviantart.com/chrisnoakumu/art/MMD-Mustard-and-Ketchup-bottles-DL-868899905
        https://www.magicstarsupermarket.com/products/beef-cubes-500gm
        https://www.istockphoto.com/photos/aluminum-foil-container
        https://minecraft.fandom.com/wiki/Coal
        https://freepngimg.com/png/2405-kitchen-knife-png-image
        https://www.doyondespres.com/en/13-5-x-10-5-bamboo-cutting-board.html
        https://dayz.fandom.com/wiki/Pumpkin
        https://bivianodirect.com.au/product/diced-pumpkin/
        https://www.traderjoes.com/home/products/pdp/seedless-watermelon-091327
        https://pngtree.com/freepng/fresh-watermelon-slice_8129295.html
        https://rotarynorthbalwyn.com.au/stories/egg-shell-membrane-benefits
        https://buybc.gov.bc.ca/commodities/eggs/
        https://www.hamptonfarms.com/collections/bulk-products
        https://uvc.co.il/?y=organic-natural-peanut-butter-crunch-rr-9zWbmmSP
        https://www.denverpost.com/2009/07/22/chihuahua-whose-yo-quiero-taco-bell-ad-sold-a-lot-of-chalupas-dies-at-15/
        https://www.reddit.com/r/whowouldcirclejerk/comments/12faey4/pitbull_named_princess_vs_giorno_giovanna_with/
        https://x.com/cute_vs_goblin/status/1745103499669508353
        https://create.roblox.com/store/asset/16452498800/Watermelon-Cat
        https://www.reddit.com/r/aww/comments/byq0gg/melon_dog_defender_of_the_seeds/
        https://www.redbubble.com/i/magnet/Tired-cute-dog-meme-by-los-memes/98799319.TBCTK
        https://www.shutterstock.com/search/cow-face-front
        https://www.dairyqueen.com/en-us/menu/hot-dog/
        https://floppapedia-revamped.fandom.com/wiki/Cheems_(Character)
        https://www.buzzfeednews.com/article/eimiyamamitsu/the-dog-likely-to-make-most-number-of-people-in-t
        https://makersplace.com/product/side-eye-dog-1-of-1-487101/
        https://the-wiki-camp.fandom.com/wiki/Walter_(White_Dog)
        https://www.istockphoto.com/photo/arrow-keys-gm173938180-9718383?searchscope=image%2Cfilm
        https://www.istockphoto.com/photo/business-dog-gm518246356-89921317
        https://x.com/eth_dogwifhat/status/1754924678555017588
        
        
    Sound Credits: 
        https://pixabay.com/sound-effects/sizzling-frying-pan-201293/
        https://pixabay.com/sound-effects/knife-throw-1-105221/
        https://pixabay.com/sound-effects/fail-144746/
        https://pixabay.com/sound-effects/success-fanfare-trumpets-6185/
        https://www.youtube.com/watch?v=rqnpacsOQAQ&list=PLbw_k1jIZPSd4Qj57YVCyPWdJAeakqf8M&index=9
        https://www.youtube.com/watch?v=dhKAcYPWonE&list=PLbw_k1jIZPSd4Qj57YVCyPWdJAeakqf8M&index=10
        https://www.youtube.com/watch?v=0d668Y6Oqfg&list=PLbw_k1jIZPSd4Qj57YVCyPWdJAeakqf8M&index=29
        https://www.youtube.com/watch?v=pnfHyC1nfCs&list=PLbw_k1jIZPSd4Qj57YVCyPWdJAeakqf8M&index=42
        https://www.youtube.com/watch?v=YPDSYCloaNI&list=PLbw_k1jIZPSd4Qj57YVCyPWdJAeakqf8M&index=144&ab_channel=ClubPenguinHighQualitySoundtrack
        https://www.youtube.com/watch?v=yuRBJCJDXns&list=PLbw_k1jIZPSd4Qj57YVCyPWdJAeakqf8M&index=147&ab_channel=ClubPenguinHighQualitySoundtrack
        https://www.youtube.com/watch?v=uhbeG632ufc&list=PLbw_k1jIZPSd4Qj57YVCyPWdJAeakqf8M&index=201&ab_channel=ClubPenguinHighQualitySoundtrack
    Instructions:
        Make customer's requested orders, typically will have a misleading/quiry request.
        Use 'left' and 'right' arrowkeys to switch tabs.
        Drag beef onto pan area to cook.
        Drag ingredients onto cutting board area and click to chop.
        Drag items onto wooden bowl to plate.
        Click serve it! to give the food to the customer.
        If your money goes below 0, you lose.
        If your money goes to or above 500, you win.
 */
public class MainMenu extends World {
    PlayButton playButton = new PlayButton();
    InstructionsButton instructionsButton = new InstructionsButton();
    private MusicPlayer musicPlayer = MusicPlayer.getInstance();
    
    public MainMenu() {    
        super(600, 400, 1); 
        addObject(playButton, 110, 190);
        addObject(instructionsButton, 190, 280);
    }
    
    public void act() {
        musicPlayer.checkAndPlayNext();
    }

    public void started() {
        musicPlayer.play();
    }

    public void stopped() {
        musicPlayer.pause();
    }
}

import java.util.ArrayList;

public class ScoringSystem {
    private ArrayList<Integer> beefRarity = new ArrayList<>();
    int beefAmnt;
    private ArrayList<Integer> ingredientType = new ArrayList<>();
    int ingredientAmnt;
    private ArrayList<Boolean> ingredientIsChopped = new ArrayList<>();
    
    private int desiredRarity;
    private int desiredBeefAmnt;
    private int desiredIngredient;
    private int desiredIngredientAmnt;
    
    private int score = 0;
    
    private int[] scoreGains = {5, 4, 3, 2, 1, 0};
    
    public ScoringSystem(ArrayList<Integer> beefRarity, ArrayList<Integer> ingredientType, ArrayList<Boolean> ingredientIsChopped,
                         int desiredRarity, int desiredBeefAmnt, int desiredIngredient, int desiredIngredientAmnt) {
        this.beefRarity = beefRarity;
        beefAmnt = beefRarity.size(); // set beef amount based on beefRarity size
        this.ingredientType = ingredientType;
        ingredientAmnt = ingredientType.size(); // set ingredient amount based on ingredientType size
        this.ingredientIsChopped = ingredientIsChopped;
        
        this.desiredRarity = desiredRarity;
        this.desiredBeefAmnt = desiredBeefAmnt;
        this.desiredIngredient = desiredIngredient;
        this.desiredIngredientAmnt = desiredIngredientAmnt;
    }
    
    public int calculateScore() {
        if(beefAmnt == 0 && ingredientAmnt == 0) {
            return 0; // if no beef or ingredients, score is 0
        }
        if(beefAmnt != 0) {
            // calculate score for beef based on rarity difference
            for(int i = 0; i < desiredBeefAmnt; i++) {
                if(i >= beefAmnt) {
                    break;
                }
                int rarityDifference = Math.abs(beefRarity.get(i) - desiredRarity);
                score += scoreGains[rarityDifference];
            }
        
            // calculate score for beef amount difference
            int beefAmntDifference = Math.abs(beefAmnt - desiredBeefAmnt);
            if(beefAmntDifference > 5) {
                beefAmntDifference = 5; // limit maximum difference to 5
            }
            score += scoreGains[beefAmntDifference];
        }
        
        if(ingredientAmnt != 0) {
            // calculate score for ingredients based on type and chopping status
            for(int i = 0; i < desiredIngredientAmnt; i++) {
                if(i >= ingredientAmnt) {
                    break;
                }
                if(ingredientType.get(i) == desiredIngredient && ingredientIsChopped.get(i)) {
                    score += scoreGains[0]; // full score for correct type and chopped
                } else if(ingredientType.get(i) == desiredIngredient) {
                    score += scoreGains[2]; // partial score for correct type but not chopped
                }
            }
            
            // calculate score for ingredient amount difference
            int ingredientAmntDifference = Math.abs(ingredientAmnt - desiredIngredientAmnt);
            if(ingredientAmntDifference > 5) {
                ingredientAmntDifference = 5; // limit maximum difference to 5
            }
            score += scoreGains[ingredientAmntDifference];
        }
        return score; // return the final score
    }
}
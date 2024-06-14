import java.util.ArrayList;

public class ScoringSystem  
{
    private ArrayList <Integer> beefRarity = new ArrayList <>();
    int beefAmnt;
    private ArrayList <Integer> ingredientType = new ArrayList <>();
    int ingredientAmnt;
    private ArrayList <Boolean> ingredientIsChopped = new ArrayList <>();
    int numIngredientsChopped = 0;
    
    private int desiredMeat;
    private int desiredRarity;
    private int desiredBeefAmnt;
    private int desiredIngredient;
    private int desiredIngredientAmnt;
    
    private int score = 0;
    
    private int[] scoreGains = {5, 4, 3, 2, 1, 0};
    
    public ScoringSystem(ArrayList <Integer> beefRarity, ArrayList <Integer> ingredientType, ArrayList <Boolean> ingredientIsChopped,
    int desiredMeat,int desiredRarity,int desiredBeefAmnt,int desiredIngredient,int desiredIngredientAmnt)
    {
        this.beefRarity = beefRarity;
        beefAmnt = beefRarity.size();
        this.ingredientType = ingredientType;
        ingredientAmnt = ingredientType.size();
        this.ingredientIsChopped = ingredientIsChopped;
        for(int i = 0; i < ingredientAmnt; i++) {
            if(ingredientIsChopped.get(i)) {
                numIngredientsChopped++;
            }
        }
        
        this.desiredMeat = desiredMeat;
        this.desiredRarity = desiredRarity;
        this.desiredBeefAmnt = desiredBeefAmnt;
        this.desiredIngredient = desiredIngredient;
        this.desiredIngredientAmnt = desiredIngredientAmnt; 
    }
    
    public int calculateScore() {
        if(beefAmnt == 0 && ingredientAmnt == 0) {
            return 0;
        }
        if(beefAmnt != 0) {
            for(int i = 0; i < desiredBeefAmnt; i++) {
                if(i >= beefAmnt) {
                    break;
                }
                int rarityDifference = Math.abs(beefRarity.get(i)-desiredMeat);
                score += scoreGains[rarityDifference];
            }
        
            int beefAmntDifference = Math.abs(beefAmnt - desiredBeefAmnt);
            if(beefAmntDifference > 5){
                beefAmntDifference = 5;
            }
            score += scoreGains[beefAmntDifference];
        }
        
        if(ingredientAmnt != 0) {
            for(int i = 0; i < desiredIngredientAmnt; i++) {
                if(i >= ingredientAmnt) {
                    break;
                }
                if(ingredientType.get(i) == desiredIngredient && ingredientIsChopped.get(i)) {
                    score += scoreGains[0];
                } else if(ingredientType.get(i) == desiredIngredient) {
                    score += scoreGains[2];
                }
            }
            
            int ingredientAmntDifference = Math.abs(ingredientAmnt - desiredIngredientAmnt);
            if(ingredientAmntDifference > 5) {
                ingredientAmntDifference = 5;
            }
            score += scoreGains[ingredientAmntDifference];
        }
        System.out.println("score:" + score);
        return score;
    }

}

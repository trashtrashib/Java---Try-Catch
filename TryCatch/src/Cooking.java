import java.util.ArrayList;
import java.util.Random;

public class Cooking {
    
    private final ArrayList<Food> POT = new ArrayList<Food>();
    private final ArrayList<Food> FRIDGE = new ArrayList<Food>();
    private final Random RNG = new Random();
    private int score = 0;
    private int[] cals = new int[5]; // Fruit, Veg, Grain, Protein, Dairy
    private int stomach = 0;
    private int stomachMax = 50;
    private int turn = 0;
    private int turnMax = 5;

    public ArrayList<Food> getPot() {return POT;}
    public ArrayList<Food> getFridge() {return FRIDGE;}

    public void addFood(Food food) {
        POT.add(food);
        FRIDGE.remove(food);
        refillFridge();
    }

    public void refillFridge() {
        while (FRIDGE.size() < 5) {

            Food newfood;

            switch (RNG.nextInt()) {
                ////////////////////////////////////////////////////
            }

            FRIDGE.add(newfood);
        }
    }

    public int valuePot() {
        double score = 0;

        for (int i = 0; i<POT.size(); i++) {
            score += POT.get(i).getNut();
        }

        return (int)(score * calmult());
    }

    public double calmult() { // Calculate a score multiplier.
        double mult = 1.0;

        int[] calstemp = new int[5];

        calstemp[0] = calPot(FoodGroup.Protein);
        calstemp[1] = calPot(FoodGroup.Dairy);
        calstemp[2] = calPot(FoodGroup.Vegetable);
        calstemp[3] = calPot(FoodGroup.Fruit);
        calstemp[4] = calPot(FoodGroup.Grain);

        for (int i = 0; i<5; i++) { // Grant 0.1 mult for each element with value above 0.
            if (calstemp[i] != 0) {mult += 0.1;}
        }
        for (int i = 0; i<5; i++) { // Loop over each element and add 0.1 to mult for each other element with the same value and each element within 15% of that value. Excluding values equaling 0.
            for (int j = 0; j<5; j++) {
                if (i != j && calstemp[j] != 0 && calstemp[i] != 0) {
                    if (calstemp[i] == calstemp[j]) {mult += 0.1;}
                    if (calstemp[i] * 1.15 > calstemp[j] && calstemp[i] * 0.85 < calstemp[j]) {mult += 0.1;}
                }
            }
        }

        // Repeat using total values with halved rewards.
        calstemp[0] = cals[0];
        calstemp[1] = cals[1];
        calstemp[2] = cals[2];
        calstemp[3] = cals[3];
        calstemp[4] = cals[4];

        for (int i = 0; i<5; i++) { // Grant 0.05 mult for each element with value above 0.
            if (calstemp[i] != 0) {mult += 0.05;}
        }
        for (int i = 0; i<5; i++) { // Loop over each element and add 0.05 to mult for each other element with the same value and each element within 15% of that value. Excluding values equaling 0.
            for (int j = 0; j<5; j++) {
                if (i != j && calstemp[j] != 0 && calstemp[i] != 0) {
                    if (calstemp[i] == calstemp[j]) {mult += 0.05;}
                    if (calstemp[i] * 1.15 > calstemp[j] && calstemp[i] * 0.85 < calstemp[j]) {mult += 0.05;}
                }
            }
        }

        return mult;
    }

    public int calPot(FoodGroup filter) { // Loop through the pot to get the total calories for a given group, or all if specified.
        int cal = 0;
        for (int i = 0; i<POT.size(); i++) {
            if (POT.get(i).getGroup() == filter || filter == FoodGroup.All) {cal += POT.get(i).getCal();}
        }
        return cal;
    }
    public int sizePot() { // loop through pot to get how much to add to stomach.
        int size = 0;
        for (int i = 0; i<POT.size(); i++) {
            size += POT.get(i).getCal();
        }
        return size;}

        public void printStats() {
            System.out.printf("Score: %d.%nStomach: %d/%d.%nTurn%d/%d%n%nFruit Calories: %d.%nVeggtable Calories: %d.%nGrain Calories: %d.%nProtein Calories: %d.%nDairy Calories: %d.%n",
                score,stomach,stomachMax,turn,turnMax,cals[0],cals[1],cals[2],cals[3],cals[4]);
        }

        public void endRound() {
            turn++;
            POT.clear();
            FRIDGE.clear();
            printStats();
        }

    public void eatPot() { // Add pot stats and end the round if it fits, otherwise warn the player.
        if (stomach+sizePot() <= stomachMax) {
        score += valuePot();
        stomach += sizePot();

        // Not sure if it will be needed yet but.
        cals[0] += calPot(FoodGroup.Protein);
        cals[1] += calPot(FoodGroup.Dairy);
        cals[2] += calPot(FoodGroup.Vegetable);
        cals[3] += calPot(FoodGroup.Fruit);
        cals[4] += calPot(FoodGroup.Grain);

        endRound();
    }
    else {System.out.println("The pot is too big!!!");}
    }

}

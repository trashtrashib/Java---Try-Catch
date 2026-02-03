import java.util.ArrayList;
import java.util.Random;

public class Cooking {
    
    private final ArrayList<Food> POT = new ArrayList<Food>();
    private final ArrayList<Food> FRIDGE = new ArrayList<Food>();
    private final Random RNG = new Random();
    private int score = 0;
    private int[] cals = new int[5]; // Fruit, Veg, Grain, Protein, Dairy
    private int stomach = 0;
    private int stomachMax = 80;
    private int turn = 0;
    private int turnMax = 5;
    private int prepares = 0;
    private int preparesMax = 2;

    public ArrayList<Food> getPot() {return POT;}
    public ArrayList<Food> getFridge() {return FRIDGE;}
    public int getScore() {return score;}

    public void setScore(int newScore) {score = newScore;}

    public void addFood(Food food) {
        POT.add(food);
        FRIDGE.remove(food);
        refillFridge();
        food.special(Trigger.added, false);
        if (food.getGroup() == FoodGroup.Fruit) {triggers(Trigger.addFruit);}
        triggers(Trigger.add);
    }

    public void triggers(Trigger cause) {
        for (int i = 0; i < FRIDGE.size(); i++) {FRIDGE.get(i).special(cause,true);}
        for (int i = 0; i < POT.size(); i++) {POT.get(i).special(cause,false);}
    }

    public void cook(Food food) {
        if (prepares<preparesMax) {
        food.cook();
        prepares++;
    } else {System.out.println("You cannot prepare any more ingredients this turn.");}
    }

    public void turnPrint() {
        System.out.printf("Turn: %d/%d.%nMeal Score: %d(%.2f)%nMeal Fruit Calories: %d.%nMeal Vegetable Calories: %d.%nMeal Grain Calories: %d.%nMeal Protein Calories: %d.%nMeal Dairy Calories: %d.%nMeal Size: %d.%n",
            turn,turnMax,valuePot(),calmult(),calPot(FoodGroup.Fruit),calPot(FoodGroup.Vegetable),calPot(FoodGroup.Grain),calPot(FoodGroup.Protein),calPot(FoodGroup.Dairy),sizePot()
        );
    }

    public void refillFridge() {
        while (FRIDGE.size() < 5) {

            Food newfood = new Broccoli(this);

            switch (RNG.nextInt(5)) {
                case 0:
                    /// Broccoli
                break;
                case 1:
                    newfood = new Beef(this);
                break;
                case 2:
                    newfood = new Loaf(this);
                break;
                case 3:
                    newfood = new Strawberry(this);
                break;
                case 4:
                    newfood = new Cream(this);
                break;
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
            mult -= 0.1 * (calstemp[i] / 500); // Reduce mult for each multiple of 200 calories in a single catagory. Used to encourage using multiple turns.s
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
            System.out.printf("Score: %d.%nStomach: %d/%d.%nTurn%d/%d%n%nFruit Calories: %d.%nVegetable Calories: %d.%nGrain Calories: %d.%nProtein Calories: %d.%nDairy Calories: %d.%n",
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

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Cooking {
    
    private final ArrayList<Food> POT = new ArrayList<Food>();
    private final ArrayList<Food> FRIDGE = new ArrayList<Food>();
    private final Random RNG = new Random();
    private int score = 0;
    private int[] cals = new int[5]; // Fruit, Veg, Grain, Protein, Dairy
    private int stomach = 0;
    private int stomachMax = 120;
    private int turn = 0;
    private int turnMax = 5;
    private int prepares = 0;
    private int preparesMax = 2;
    private Scanner in;
    private int fridgeSize = 5;

    public Cooking(Scanner scan) {in = scan;}

    public ArrayList<Food> getPot() {return POT;}
    public ArrayList<Food> getFridge() {return FRIDGE;}
    public Random getRandom() {return RNG;}
    public int getScore() {return score;}
    public int getFridgeSize() {return fridgeSize;}
    public int[] getCalArray() {return cals;}

    public void setScore(int newScore) {score = newScore;}
    public void setFridge(int newSize) {fridgeSize = newSize;};

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
            if (!food.getCooked()) {
        food.cook();
        prepares++;} else {food.cookinfo();}
    } else {System.out.printf("You cannot prepare any more ingredients this turn.%n%n");}
    }

    public void turnPrint() {
        System.out.printf("Turn: %d/%d.%nMeal Score: %d(%.2fx)%nCan Prepare %d ingrediants.%nMeal Fruit Calories: %d.%nMeal Vegetable Calories: %d.%nMeal Grain Calories: %d.%nMeal Protein Calories: %d.%nMeal Dairy Calories: %d.%nMeal Size: %d.%n%n",
            turn+1,turnMax,valuePot(),calmult(),preparesMax-prepares,calPot(FoodGroup.Fruit),calPot(FoodGroup.Vegetable),calPot(FoodGroup.Grain),calPot(FoodGroup.Protein),calPot(FoodGroup.Dairy),sizePot()
        );
    }

    public void refillFridge() {
        while (FRIDGE.size() < fridgeSize) {

            Food newfood = new Broccoli(this);

            switch (RNG.nextInt(15)) {
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
                case 5:
                    newfood = new Almond(this);
                break;
                case 6:
                    newfood = new Carrot(this);
                break;
                case 7:
                    newfood = new Milk(this);
                break;
                case 8:
                    newfood = new Watermelon(this);
                break;
                case 9:
                    newfood = new Rice(this);
                break;
                case 10:
                    newfood = new Blueberry(this);
                break;
                case 11:
                    newfood = new Chicken(this);
                break;
                case 12:
                    newfood = new Yogurt(this);
                break;
                case 13:
                    newfood = new Squash(this);
                break;
                case 14:
                    newfood = new Oats(this);
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

        calstemp[0] = calPot(FoodGroup.Fruit);
        calstemp[1] = calPot(FoodGroup.Vegetable);
        calstemp[2] = calPot(FoodGroup.Grain);
        calstemp[3] = calPot(FoodGroup.Protein);
        calstemp[4] = calPot(FoodGroup.Dairy);

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

        // Repeat using total values with halved rewards and more linient conditions.
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
                    if (calstemp[i] * 1.05 > calstemp[j] && calstemp[i] * 0.95 < calstemp[j]) {mult += 0.05;}
                    if (calstemp[i] * 1.2 > calstemp[j] && calstemp[i] * 0.8 < calstemp[j]) {mult += 0.05;}
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
            size += POT.get(i).getSize();
        }
        return size;}

        public void printStats() {
            System.out.printf("Score: %d.%nStomach: %d/%d.%nTurn: %d/%d%n%nFruit Calories: %d.%nVegetable Calories: %d.%nGrain Calories: %d.%nProtein Calories: %d.%nDairy Calories: %d.%n%n",
                score,stomach,stomachMax,turn+1,turnMax,cals[0],cals[1],cals[2],cals[3],cals[4]);
        }

        public void endRound() {
            if (turn+1 < turnMax) {turn++;} else {System.out.print("Final "); printStats(); turn++;}
            POT.clear();
            FRIDGE.clear();
        }

        public void printPot() {
            System.out.println("Meal includes:");
            for (int i = 0; i < POT.size(); i++) {
                System.out.printf("(%d) %s%n",i+1,POT.get(i).getName());
            }
            System.out.println();
        }

        public void printFridge() {
            System.out.println("Avalible Ingrediants:");
            for (int i = 0; i < FRIDGE.size(); i++) {
                System.out.printf("(%d) %s%n",i+POT.size()+1,FRIDGE.get(i).getName());
            }
            System.out.println();
        }

        public void round() {

            while (turn < turnMax) {
                turn();
            }

        }

        public void turn() {

            prepares = 0;
            refillFridge();

            printStats();
            turnPrint();

            printPot();
            printFridge();

            System.out.printf("Select from meal to read info, select from ingrediants for more options. Type '0' to end turn.%n%n");

            int input = getInput(in);

            while (input != 0) {

                if (input > 0 && input <= POT.size()+FRIDGE.size()) {

                if (input <= POT.size()) {

                    POT.get(input-1).info();

                } else {

                    FRIDGE.get((input-POT.size())-1).info();
                    System.out.printf("(0) Back.%n(1) Add to meal.%n(2) Prepare info.%n(3) Prepare Ingrediant.%n%n");

                    int saveInput = (input-POT.size())-1;
                    input = getInput(in);

                    while (input != 0)
                    {
                        switch (input)
                        {
                            default:
                            System.out.printf("Invalid.%n%n");
                            break;
                            case 1:
                            addFood(FRIDGE.get(saveInput));
                            break;
                            case 2:
                            FRIDGE.get(saveInput).cookinfo();
                            break;
                            case 3:
                            cook(FRIDGE.get(saveInput));
                            FRIDGE.get(saveInput).info();
                            break;
                        }
                        if (input == 1) {
                            turnPrint();

                            printPot();
                            printFridge();

                            System.out.printf("Select from meal to read info, select from ingrediants for more options. Type '0' to end turn.%n%n");
                            break;
                        }
                        input = getInput(in);
                    }
                    if (input == 0) {
                            turnPrint();

                            printPot();
                            printFridge();

                            System.out.printf("Select from meal to read info, select from ingrediants for more options. Type '0' to end turn.%n%n");
                        }

                }

                } else {System.out.printf("Invalid.%n%n");}
                input = getInput(in);
            }

            eatPot();

        }

        public void setUp() {
            System.out.printf("Would you like to customize settings?%n(0) No%n(1+) Yes%n%n");
            if (getInput(in) == 0) {return;}

            System.out.printf("Number of turns (Default is 5): ");
            int input = getInput(in);
            while (input < 1) {
            System.out.printf("Invalid%n%nNumber of turns (Default is 5): "); input = getInput(in);}
            System.out.printf("%n");
            turnMax = input;
            
            System.out.printf("Number of ingrediants that can be prepared each turn (Default is 2): ");
            input = getInput(in);
            while (input < 0) {
            System.out.printf("Invalid%n%nNumber of ingrediants that can be prepared each turn (Default is 2): "); input = getInput(in);}
            System.out.printf("%n");
            preparesMax = input;
            
            System.out.printf("Size of stomach (Default is 120): ");
            input = getInput(in);
            while (input < 10) {
            System.out.printf("Invalid%n%nSize of stomach (Default is 120): "); input = getInput(in);}
            System.out.printf("%n");
            stomachMax = input;
        }

        public int getInput(Scanner in) {

            int input = -999;

            while (input == -999) {
            try {
                input = in.nextInt();
                if (input == -999) {System.out.printf("Invalid.%n%n");} // Still print invalid if user inputs -999.
            } catch(Exception e) {
                System.out.printf("Invalid.%n%n");
            }}

            return input;
        }

    public void eatPot() { // Add pot stats and end the round if it fits, otherwise warn the player.
        if (stomach+sizePot() <= stomachMax) {
        score += valuePot();
        stomach += sizePot();

        // Not sure if it will be needed yet but.
        cals[0] += calPot(FoodGroup.Fruit);
        cals[1] += calPot(FoodGroup.Vegetable);
        cals[2] += calPot(FoodGroup.Grain);
        cals[3] += calPot(FoodGroup.Protein);
        cals[4] += calPot(FoodGroup.Dairy);

        triggers(Trigger.eat);

        endRound();
    }
    else {System.out.printf("The pot is too big!!!%n%n"); endRound();}
    }

}

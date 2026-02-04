import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Cooking {
    
    private final ArrayList<Food> POT = new ArrayList<Food>(); // Items in the meal.
    private final ArrayList<Food> FRIDGE = new ArrayList<Food>(); // Items not in the meal.
    private final Random RNG = new Random();
    private int score = 0;
    private int[] cals = new int[5]; // Fruit, Veg, Grain, Protein, Dairy. Could have made foodgroup enum have values that worked here but didn't feel like it.
    private int stomach = 0;
    private int stomachMax = 120; // Stomach is essentially how much food can be eaten max.
    private int turn = 0;
    private int turnMax = 5; // How many turns does the game allow.
    private int prepares = 0;
    private int preparesMax = 2; // How many times can you cook in a turn.
    private Scanner in;
    private int fridgeSize = 5; // How many items are visable at a time.

    public Cooking(Scanner scan) {in = scan;} // Constructer grabs scanner from Main.

    // Getters and setters.
    public ArrayList<Food> getPot() {return POT;}
    public ArrayList<Food> getFridge() {return FRIDGE;}
    public Random getRandom() {return RNG;}
    public int getScore() {return score;}
    public int getFridgeSize() {return fridgeSize;}
    public int[] getCalArray() {return cals;}

    public void setScore(int newScore) {score = newScore;}
    public void setFridge(int newSize) {fridgeSize = newSize;};

    public void addFood(Food food) { // Add item to pot and remove it from the fridge if applicable. Triggers effects that care about this.
        POT.add(food);
        FRIDGE.remove(food);
        refillFridge(); // Replace the item.

        food.special(Trigger.added, false);
        if (food.getGroup() == FoodGroup.Fruit) {triggers(Trigger.addFruit);}
        triggers(Trigger.add);
    }

    public void triggers(Trigger cause) { // Loop over all ingrediants and run their special methods with the given trigger variable. Also tells the item if they are in the meal or fridge.
        for (int i = 0; i < FRIDGE.size(); i++) {FRIDGE.get(i).special(cause,true);}
        for (int i = 0; i < POT.size(); i++) {POT.get(i).special(cause,false);}
    }

    public void cook(Food food) { // If player still has prepares left tell the item to cook itself.
        if (prepares<preparesMax) {
            if (!food.getCooked()) {
        food.cook();
        prepares++;} else {food.cookInfo();}
    } else {System.out.printf("You cannot prepare any more ingredients this turn.%n%n");}
    }

    public void turnPrint() { // Print info about current meal and item list.
        System.out.printf("Turn: %d/%d.%nMeal Score: %d(%.2fx)%nCan Prepare %d ingrediants.%nMeal Fruit Calories: %d.%nMeal Vegetable Calories: %d.%nMeal Grain Calories: %d.%nMeal Protein Calories: %d.%nMeal Dairy Calories: %d.%nMeal Size: %d.%n%n",
            turn+1,turnMax,valuePot(),calmult(),preparesMax-prepares,calPot(FoodGroup.Fruit),calPot(FoodGroup.Vegetable),calPot(FoodGroup.Grain),calPot(FoodGroup.Protein),calPot(FoodGroup.Dairy),sizePot()
        );
    }

    public void refillFridge() { // for each missing item in the fridge create a random food item and add it to the fridge.
        while (FRIDGE.size() < fridgeSize) {

            Food newfood;

            switch (RNG.nextInt(15)) {
                default:
                    newfood = new Broccoli(this);
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
                    if (calPot(FoodGroup.Grain) < 1000 && sizePot() < stomachMax-stomach) { // Prevent abuse of Oats to get potentially infinite score for free. Oats are still frankly broken so I might have to nerf them.
                    newfood = new Oats(this);} else {newfood = new Loaf(this);}
                break;
            }

            FRIDGE.add(newfood);
        }
    }

    public int valuePot() { // loop over each food in the pot, add up their nutrition values, then return the result multiplied by the multiplier.
        double score = 0;

        for (int i = 0; i<POT.size(); i++) {
            score += POT.get(i).getNut();
        }

        return (int)(score * calmult());
    }

    public double calmult() { // Calculate a score multiplier.
        double mult = 1.0;

        int[] calstemp = new int[5];

        // Get the values of each food groups calorie count and store it in a format I can loop over.
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
            mult -= 0.1 * (calstemp[i] / 500); // Reduce mult for each multiple of 500 calories in a single catagory. Used to encourage using multiple turns.
        }

        // Repeat using saved total values with halved rewards and more linient conditions.
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

        public void printStats() { // Print info about the current round. Including running calorie counts and score.
            System.out.printf("Score: %d.%nStomach: %d/%d.%nTurn: %d/%d%n%nFruit Calories: %d.%nVegetable Calories: %d.%nGrain Calories: %d.%nProtein Calories: %d.%nDairy Calories: %d.%n%n",
                score,stomach,stomachMax,turn+1,turnMax,cals[0],cals[1],cals[2],cals[3],cals[4]);
        }

        public void endRound() { // Clear pot and fridge, increment turn count and print round results if the game ends.
            if (turn+1 < turnMax) {turn++;} else {System.out.print("Final "); printStats(); turn++;}
            POT.clear();
            FRIDGE.clear();
        }

        public void printPot() { // Print all items in the pot.
            System.out.println("Meal includes:");
            for (int i = 0; i < POT.size(); i++) {
                System.out.printf("(%d) %s%n",i+1,POT.get(i).getName());
            }
            System.out.println();
        }

        public void printFridge() { // Print all items in the fridge. Uses pot size to determine what number to start from.
            System.out.println("Avalible Ingrediants:");
            for (int i = 0; i < FRIDGE.size(); i++) {
                System.out.printf("(%d) %s%n",i+POT.size()+1,FRIDGE.get(i).getName());
            }
            System.out.println();
        }

        public void round() { // Do turns until the player runs out of them :3

            while (turn < turnMax) {
                turn();
            }

        }

        public void turn() { // Run a single turn of the game.

            prepares = 0; // Reset ability to cook.
            refillFridge(); // Fill the fridge with new items.

            printStats();
            turnPrint();

            printPot();
            printFridge();

            System.out.printf("Select from meal to read info, select from ingrediants for more options. Type '0' to end turn.%n%n");

            int input = getInput(in);

            while (input != 0) { // Repeat until you end the turn.

                if (input > 0 && input <= POT.size()+FRIDGE.size()) {  // Check if input is valid.

                if (input <= POT.size()) { // If input is in the pot, print info and continue.

                    POT.get(input-1).info();

                } else { // Otherwise print info and continue to inner menu.

                    FRIDGE.get((input-POT.size())-1).info();
                    System.out.printf("(0) Back.%n(1) Add to meal.%n(2) Prepare info.%n(3) Prepare Ingrediant.%n%n");

                    int saveInput = (input-POT.size())-1; // Remember which item we're talking about because input is going to be overwritten.
                    input = getInput(in);

                    while (input != 0) // Repeat until player returns to outer turn menu.
                    {
                        switch (input)
                        {
                            default:
                            System.out.printf("Invalid.%n%n");
                            break;
                            case 1:
                            addFood(FRIDGE.get(saveInput)); // Add item.
                            break;
                            case 2:
                            FRIDGE.get(saveInput).cookInfo(); // Read cook information.
                            break;
                            case 3:
                            cook(FRIDGE.get(saveInput)); // Cook item and then print the new info.
                            FRIDGE.get(saveInput).info();
                            break;
                        }
                        if (input == 1) {
                            break; // Break from menu when adding item.
                        }
                        input = getInput(in);
                    }
                    // Print outer menu info when returning.
                            turnPrint();

                            printPot();
                            printFridge();

                            System.out.printf("Select from meal to read info, select from ingrediants for more options. Type '0' to end turn.%n%n");

                }

                } else {System.out.printf("Invalid.%n%n");}
                input = getInput(in);
            }

            eatPot(); // End the turn.

        }

        public void setUp() { // Ask if the player would like to play with custom settings and allow them to set the values if they do.
            System.out.printf("Would you like to customize settings?%n(0) No%n(1+) Yes%n%n");
            if (getInput(in) == 0) {return;} // Ngl kinda expected java to get mad at me for this for some reason.

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
            
            System.out.printf("Number of ingrediants per turn (Default is 5): ");
            input = getInput(in);
            while (input < 1) {
            System.out.printf("Invalid%n%nNumber of ingrediants per turn (Default is 5): "); input = getInput(in);}
            System.out.printf("%n");
            fridgeSize = input;
        }

        public int getInput(Scanner in) { // Take user input with exception handling, since all input is numerical this is used for all input.

            int input = -999;

            while (input == -999) {
            try {
                input = in.nextInt();
                if (input == -999) {System.out.printf("Invalid.%n%n");} // Still print invalid if user inputs -999.
            } catch(Exception e) {
                System.out.printf("Invalid.%n%n");
                in.nextLine();
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
    else {System.out.printf("The pot is too big!!!%n%n"); endRound();} // If food don't fit, skip round without eating it.
    }

}

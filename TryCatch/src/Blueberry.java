public class Blueberry extends Food {

    public Blueberry(Cooking game) { // Constructer.
        this.game = game;
        this.name = "Blueberry";
        this.nutrition = 5;
        this.calories = 25;
        this.size = 4;
        this.cooked = false;
        this.group = FoodGroup.Fruit;
    }
    
    public void special(Trigger cause, boolean inFridge) { // Creates 2 or 4 clone objects when added.
        if (cause == Trigger.added) {
            for (int i = 0; i<(this.cooked ? 4:2); i++) {
                game.addFood(new BlueberryClone(game,this));
            }
        }
    }

    public void cook() { // Apply effects of cooking.
        this.name = "Lots of Blueberries";
        this.cooked = true;
    }
    public void cookInfo() { // Print what happens what cooked, or tell the player they already have been.
        if (!this.cooked) {
        System.out.printf("Get more blueberries:%nSplits into 5 berries instead of 3.%n%n");} else {
            System.out.printf("No, that's enough.%n%n");
        }
    }
    public void info() { // Print Ingrediant stats.
        System.out.printf("%s:%nNutrition Value: %d.%nCalories: %d.%nSize: %d.%nGroup: Fruit%n%n",this.name,this.nutrition,this.calories,this.size);
        if (this.cooked) {System.out.printf("Special: When added to meal, it splits into 5 berries.%n%n");} else {
            System.out.printf("Special: When added to meal, it splits into 3 berries.%n%n");
        }
    }

}

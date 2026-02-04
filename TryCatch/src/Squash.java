public class Squash extends Food {

    public Squash(Cooking game) { // Constructer.
        this.game = game;
        this.name = "Squash";
        this.nutrition = 30;
        this.calories = 180;
        this.size = 10;
        this.cooked = false;
        this.group = FoodGroup.Vegetable;
    }
    
    public void special(Trigger cause, boolean inFridge) { // Whenever an item is added reduce nutrition, since adding the squash itself triggers this add 1 nutrition when added to cancel it out.
        if (!this.cooked && cause == Trigger.added) {this.nutrition++;}
        if (!this.cooked && cause == Trigger.add) {this.nutrition--;}
    }

    public void cook() { // Apply effects of cooking.
        this.calories += 50;
        this.name = "Buttered Squash";
        this.cooked = true;
    }
    public void cookInfo() { // Print what happens what cooked, or tell the player they already have been.
        if (!this.cooked) {
        System.out.printf("Butter the squash:%n+50 Calories.%nNo longer loses nutrition when adding ingrediants.%n%n");} else {
            System.out.printf("It's all buttered up!%n%n");
        }
    }
    public void info() { // Print Ingrediant stats.
        System.out.printf("%s:%nNutrition Value: %d.%nCalories: %d.%nSize: %d.%nGroup: Vegetable%n%n",this.name,this.nutrition,this.calories,this.size);
        if (!this.cooked) {System.out.printf("Special: Loses 1 nutrition whenever you add another ingrediant to the meal.%n%n");} else {System.out.println();}
    }

}

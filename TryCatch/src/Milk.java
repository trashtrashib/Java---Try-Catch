public class Milk extends Food {
    
    public Milk(Cooking game) { // Constructer.
        this.game = game;
        this.name = "Milk";
        this.nutrition = 8;
        this.calories = 50;
        this.size = 2;
        this.cooked = false;
        this.group = FoodGroup.Dairy;
    }
    
    public void special(Trigger cause, boolean inFridge) { // Loop over each other item in the pot when added and increase their nutrition.
        if (this.cooked && cause == Trigger.added) {
            for (int i = 0; i<game.getPot().size(); i++) {
                if (!game.getPot().get(i).equals(this)) {game.getPot().get(i).setNut(game.getPot().get(i).getNut() + 1);}
            }
        }
    }

    public void cook() { // Apply effects of cooking.
        this.calories += 60;
        this.name = "Chocolate Milk";
        this.cooked = true;
    }
    public void cookInfo() { // Print what happens what cooked, or tell the player they already have been.
        if (!this.cooked) {
        System.out.printf("Add chocolate:%n+60 Calories.%nWhen added to meal, each other ingrediant gets +1 nutrition.%n%n");} else {
            System.out.printf("It's already peak.%n%n");
        }
    }
    public void info() { // Print Ingrediant stats.
        System.out.printf("%s:%nNutrition Value: %d.%nCalories: %d.%nSize: %d.%nGroup: Dairy%n",this.name,this.nutrition,this.calories,this.size);
        if (this.cooked) {System.out.printf("Special: When added to meal, each other ingrediant gets +1 nutrition.%n%n");} else {System.out.println();}
    }

}

public class Loaf extends Food {

    public Loaf(Cooking game) { // Constructer.
        this.game = game;
        this.name = "Loaf of Bread";
        this.nutrition = 8;
        this.calories = 265;
        this.size = 8;
        this.cooked = false;
        this.group = FoodGroup.Grain;
    }
    
    public void special(Trigger cause, boolean inFridge) { // Add to game score when added based on current multiplier.
        if (cause == Trigger.added && this.cooked) {game.setScore(game.getScore() + (int)(game.calmult() * 25));}
    }

    public void cook() { // Apply effects of cooking.
        this.size += 2;
        this.name = "Leavened Bread";
        this.cooked = true;
    }
    public void cookInfo() { // Print what happens what cooked, or tell the player they already have been.
        if (!this.cooked) {
        System.out.printf("Leaven bread:%n+2 Size.%nImmediately gain score equal to 25x your current multiplier when added.%n%n");} else {
            System.out.printf("Bread is already leavened.%n%n");
        }
    }
    public void info() { // Print Ingrediant stats.
        System.out.printf("%s:%nNutrition Value: %d.%nCalories: %d.%nSize: %d.%nGroup: Grain%n",this.name,this.nutrition,this.calories,this.size);
        if (this.cooked) {System.out.printf("Special: Immediately gain score equal to 25x your current multiplier when added.%n%n");} else {System.out.println();}
    }

}

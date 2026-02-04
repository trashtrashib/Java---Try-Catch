public class Broccoli extends Food {

    public Broccoli(Cooking game) { // Constructer.
        this.game = game;
        this.name = "Broccoli";
        this.nutrition = 15;
        this.calories = 70;
        this.size = 4;
        this.cooked = false;
        this.group = FoodGroup.Vegetable;
    }
    
    public void special(Trigger cause, boolean inFridge) {}

    public void cook() { // Apply effects of cooking.
        this.calories -= 10;
        this.size--;
        this.name = "Chopped Broccoli";
        this.cooked = true;
    }
    public void cookInfo() { // Print what happens what cooked, or tell the player they already have been.
        if (!this.cooked) {
        System.out.printf("Chop up broccoli:%n-10 Calories.%n-1 Size.%n%n");} else {
            System.out.printf("Broccoli has already been chopped.%n%n");
        }
    }
    public void info() { // Print Ingrediant stats.
        System.out.printf("%s:%nNutrition Value: %d.%nCalories: %d.%nSize: %d.%nGroup: Vegetable%n%n",this.name,this.nutrition,this.calories,this.size);
    }

}

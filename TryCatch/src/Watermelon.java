public class Watermelon extends Food {

    public Watermelon(Cooking game) { // Constructer.
        this.game = game;
        this.name = "Watermelon";
        this.nutrition = 50;
        this.calories = 500;
        this.size = 15;
        this.cooked = false;
        this.group = FoodGroup.Fruit;
    }
    
    public void special(Trigger cause, boolean inFridge) {}

    public void cook() { // Apply effects of cooking.
        this.nutrition -= 30;
        this.calories -= 350;
        this.size -= 8;
        this.name = "Watermelon Slice";
        this.cooked = true;
    }
    public void cookInfo() { // Print what happens what cooked, or tell the player they already have been.
        if (!this.cooked) {
        System.out.printf("Cut out a wedge:%n-30 Nutrition.%n+-350 Calories.%n-8 Size.%n%n");} else {
            System.out.printf("We can't waste any more food.%n%n");
        }
    }
    public void info() { // Print Ingrediant stats.
        System.out.printf("%s:%nNutrition Value: %d.%nCalories: %d.%nSize: %d.%nGroup: Fruit%n%n",this.name,this.nutrition,this.calories,this.size);
    }

}

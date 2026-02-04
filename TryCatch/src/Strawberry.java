public class Strawberry extends Food {

    public Strawberry(Cooking game) { // Constructer.
        this.game = game;
        this.name = "Strawberry";
        this.nutrition = 20;
        this.calories = 80;
        this.size = 5;
        this.cooked = false;
        this.group = FoodGroup.Fruit;
    }
    
    public void special(Trigger cause, boolean inFridge) {}

    public void cook() { // Apply effects of cooking.
        this.calories += 150;
        this.nutrition -= 2;
        this.name = "Sugar Sprinkled Strawberry";
        this.cooked = true;
    }
    public void cookInfo() { // Print what happens what cooked, or tell the player they already have been.
        if (!this.cooked) {
        System.out.printf("Sugar sprinkle strawberry:%n-2 Nutrition.%n+150 Calories.%n%n");} else {
            System.out.printf("Strawberry is suitably cavity inducing.%n%n");
        }
    }
    public void info() { // Print Ingrediant stats.
        System.out.printf("%s:%nNutrition Value: %d.%nCalories: %d.%nSize: %d.%nGroup: Fruit%n%n",this.name,this.nutrition,this.calories,this.size);
    }

}

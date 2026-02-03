public class Strawberry extends Food {
    
    protected String name = "Strawberry";
    protected int nutrition = 20;
    protected int calories = 80;
    protected int size = 5;
    protected boolean cooked = false;
    protected FoodGroup group = FoodGroup.Fruit;

    public Strawberry(Cooking game) {this.game = game;}
    
    public void special(Trigger cause, boolean inFridge) {}

    public void cook() {
        this.calories += 150;
        this.nutrition -= 2;
        this.name = "Sugar Sprinkled Strawberry";
        this.cooked = true;
    }
    public void cookinfo() {
        if (!this.cooked) {
        System.out.printf("Sugar sprinkle strawberry:%n-2 Nutrition.%n+150 Calories.%n");} else {
            System.out.println("Strawberry is suitably cavity inducing.");
        }
    }
    public void info() {
        System.out.printf("%s:%nNutrition Value: %d.%nCalories: %d.%nSize: %d.%nGroup: Fruit%n",this.name,this.nutrition,this.calories,this.size);
    }

}

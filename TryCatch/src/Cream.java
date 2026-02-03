public class Cream extends Food {
    
    protected String name = "Cream";
    protected int nutrition = 12;
    protected int calories = 200;
    protected int size = 6;
    protected boolean cooked = false;
    protected FoodGroup group = FoodGroup.Dairy;

    public Cream(Cooking game) {this.game = game;}
    
    public void special(Trigger cause, boolean inFridge) {
        if (this.cooked && cause == Trigger.addFruit) {this.nutrition += 2;}
    }

    public void cook() {
        for (int i = 0; i<game.getPot().size(); i++) {
            if (game.getPot().get(i).getGroup() == FoodGroup.Fruit) {this.nutrition += 2;}
        }
        this.size++;
        this.name = "Whipped Cream";
        this.cooked = true;
    }
    public void cookinfo() {
        if (!this.cooked) {
        System.out.printf("Whip the cream:%n+1 Size.%nHas +2 nutrition for each fruit in the meal.");} else {
            System.out.println("Cream has already been whipped.");
        }
    }
    public void info() {
        System.out.printf("%s:%nNutrition Value: %d.%nCalories: %d.%nSize: %d.%nGroup: Vegetable%n",this.name,this.nutrition,this.calories,this.size);
        if (this.cooked) {System.out.println("Special: Has +2 nutrition for each fruit in the meal.");}
    }

}

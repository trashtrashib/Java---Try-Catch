public class Cream extends Food {

    public Cream(Cooking game) {
        this.game = game;
        this.name = "Cream";
        this.nutrition = 12;
        this.calories = 200;
        this.size = 6;
        this.cooked = false;
        this.group = FoodGroup.Dairy;
    }
    
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
        System.out.printf("Whip the cream:%n+1 Size.%nHas +2 nutrition for each fruit in the meal.%n%n");} else {
            System.out.printf("Cream has already been whipped.%n%n");
        }
    }
    public void info() {
        System.out.printf("%s:%nNutrition Value: %d.%nCalories: %d.%nSize: %d.%nGroup: Dairy%n",this.name,this.nutrition,this.calories,this.size);
        if (this.cooked) {System.out.printf("Special: Has +2 nutrition for each fruit in the meal.%n%n");} else {System.out.println();}
    }

}

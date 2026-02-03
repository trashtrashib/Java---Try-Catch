public class Yogurt extends Food {

    public Yogurt(Cooking game) {
        this.game = game;
        this.name = "Yogurt";
        this.nutrition = 8;
        this.calories = 70;
        this.size = 5;
        this.cooked = false;
        this.group = FoodGroup.Dairy;
    }
    
    public void special(Trigger cause, boolean inFridge) {
        if (cause == Trigger.eat && !inFridge) {
            game.getCalArray()[3] = game.getCalArray()[3] + this.calories;
        }
    }

    public void cook() {
        this.calories += 100;
        this.name = "Frozen Yogurt";
        this.cooked = true;
    }
    public void cookinfo() {
        if (!this.cooked) {
        System.out.printf("Freeze the yogurt:%n+100 Calories.%n%n");} else {
            System.out.printf("Yogurt has already been froen.%n%n");
        }
    }
    public void info() {
        System.out.printf("%s:%nNutrition Value: %d.%nCalories: %d.%nSize: %d.%nGroup: Dairy%n",this.name,this.nutrition,this.calories,this.size);
        System.out.printf("Special: Also adds to protein calories when eaten. (Does not count towards this meals multiplier.)%n%n");
    }

}

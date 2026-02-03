public class Oats extends Food {

    public Oats(Cooking game) {
        this.game = game;
        this.name = "Oats";
        this.nutrition = 2;
        this.calories = 100;
        this.size = 5;
        this.cooked = false;
        this.group = FoodGroup.Grain;
    }
    
    public void special(Trigger cause, boolean inFridge) {
        if (cause == Trigger.added && this.cooked) {
            game.setScore(game.getScore() + ((int)(game.calPot(FoodGroup.Grain) / 50) * 5));
        }
    }

    public void cook() {
        this.calories += 50;
        this.name = "Frosted Oats";
        this.cooked = true;
    }
    public void cookinfo() {
        if (!this.cooked) {
        System.out.printf("Frost oats:%n+50 Calories.%n%n");} else {
            System.out.printf("Oats are already frosted.%n%n");
        }
    }
    public void info() {
        System.out.printf("%s:%nNutrition Value: %d.%nCalories: %d.%nSize: %d.%nGroup: Grain%n",this.name,this.nutrition,this.calories,this.size);
        System.out.printf("Special: Immediately gain 5 score for every 50 calories of grain in the meal when added to the meal.%n%n");
    }

}

public class Almond extends Food {

    public Almond(Cooking game) {
        this.game = game;
        this.name = "Almonds";
        this.nutrition = 16;
        this.calories = 60;
        this.size = 4;
        this.cooked = false;
        this.group = FoodGroup.Protein;
    }

    public void special(Trigger cause, boolean inFridge) {
        if (!this.cooked && cause == Trigger.added && game.getRandom().nextInt(10) < 2) {this.nutrition -= 20;} // Gains nutrition if it has been made into jerky whenever something is added to the pot.
    }

    public void cook() {
        this.nutrition -= 7;
        this.size -= 3;
        this.name = "Roasted Almonds";
        this.cooked = true;
    }
    public void cookinfo() {
        if (!this.cooked) {
        System.out.printf("Roast the almonds:%nNo longer has a chance to poison the meal.%n");} else {
            System.out.printf("The almonds will never recover from this.%n%n");
        }
    }
    public void info() {
        System.out.printf("%s:%nNutrition Value: %d.%nCalories: %d.%nSize: %d.%nGroup: Protein%n",this.name,this.nutrition,this.calories,this.size);
        if (!this.cooked) {System.out.printf("Special: Has a 1 in 5 chance to lose 20 nutrition when added.%n%n");} else {System.out.println();}
    }

}

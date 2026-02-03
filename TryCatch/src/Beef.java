public class Beef extends Food {
    
    protected String name = "Beef";
    protected int nutrition = 25;
    protected int calories = 250;
    protected int size = 10;
    protected boolean cooked = false;
    protected FoodGroup group = FoodGroup.Protein;

    public Beef(Cooking game) {this.game = game;}

    public void special(Trigger cause, boolean inFridge) {
        if (this.cooked && cause == Trigger.add && inFridge) {this.nutrition++;} // Gains nutrition if it has been made into jerky whenever something is added to the pot.
    }

    public void cook() {
        this.nutrition -= 7;
        this.size -= 3;
        this.name = "Beef Jerky";
        this.cooked = true;
    }
    public void cookinfo() {
        if (!this.cooked) {
        System.out.printf("Make jerky:%n-8 Nutrition.%n-3 Size.%nGains +1 nutrition whenever you add another ingrediant to the meal before it.%n");} else {
            System.out.println("The beef has already been jerked.");
        }
    }
    public void info() {
        System.out.printf("%s:%nNutrition Value: %d.%nCalories: %d.%nSize: %d.%nGroup: Protein%n",this.name,this.nutrition,this.calories,this.size);
        if (this.cooked) {System.out.println("Special: Gains +1 nutrition whenever you add another ingrediant to the meal before it.");}
    }

}

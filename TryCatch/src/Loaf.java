public class Loaf extends Food {
    
    protected String name = "Loaf of Bread";
    protected int nutrition = 8;
    protected int calories = 265;
    protected int size = 8;
    protected boolean cooked = false;
    protected FoodGroup group = FoodGroup.Grain;

    public Loaf(Cooking game) {this.game = game;}
    
    public void special(Trigger cause, boolean inFridge) {
        if (cause == Trigger.added && this.cooked) {game.setScore(game.getScore() + (int)(game.calmult() * 25));}
    }

    public void cook() {
        this.size += 2;
        this.name = "Leavened Bread";
        this.cooked = true;
    }
    public void cookinfo() {
        if (!this.cooked) {
        System.out.printf("Leaven bread:%n+2 Size.%nImmediately gain score equal to 25x your current multiplier when added.%n");} else {
            System.out.println("Bread is already leavened.");
        }
    }
    public void info() {
        System.out.printf("%s:%nNutrition Value: %d.%nCalories: %d.%nSize: %d.%nGroup: Grain%n",this.name,this.nutrition,this.calories,this.size);
        if (this.cooked) {System.out.println("Special: Immediately gain score equal to 25x your current multiplier when added.");}
    }

}

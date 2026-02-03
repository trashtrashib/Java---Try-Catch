public class Broccoli extends Food {
    
    protected String name = "Broccoli";
    protected int nutrition = 15;
    protected int calories = 70;
    protected int size = 4;
    protected boolean cooked = false;
    protected FoodGroup group = FoodGroup.Vegetable;

    public Broccoli(Cooking game) {this.game = game;}
    
    public void special(Trigger cause, boolean inFridge) {}

    public void cook() {
        this.calories -= 10;
        this.size--;
        this.name = "Chopped Broccoli";
        this.cooked = true;
    }
    public void cookinfo() {
        if (!this.cooked) {
        System.out.printf("Chop up broccoli:%n-10 Calories.%n-1 Size.%n");} else {
            System.out.println("Broccoli has already been chopped.");
        }
    }
    public void info() {
        System.out.printf("%s:%nNutrition Value: %d.%nCalories: %d.%nSize: %d.%nGroup: Vegetable%n",this.name,this.nutrition,this.calories,this.size);
    }

}

public class Broccoli extends Food {
    
    private String name = "Broccoli";
    private int nutrition = 10;
    private int calories = 50;
    private int size = 5;
    private boolean cooked = false;
    private FoodGroup group = FoodGroup.Vegetable;

    public void cook() {
        this.calories -= 10;
        this.size -= 1;
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
        System.out.printf("%s:%nNutrition Value: %d.%nCalories: %d.%nSize: %d.%n",name,nutrition,calories,size);
    }

}

public class Chicken extends Food {

    public Chicken(Cooking game) { // Constructer.
        this.game = game;
        this.name = "Chicken Breast";
        this.nutrition = 18;
        this.calories = 230;
        this.size = 8;
        this.cooked = false;
        this.group = FoodGroup.Protein;
    }

    public void special(Trigger cause, boolean inFridge) { // Find a random ingrediant in the fridge and increase its nutrition by 3.
        if (cause == Trigger.added) {
            Food target = game.getFridge().get(game.getRandom().nextInt(game.getFridge().size()));
            target.setNut(target.getNut() + 3);;
        }
    }

    public void cook() { // Apply effects of cooking.
        this.calories -= 30;
        this.size -= 1;
        this.name = "Drumsticks";
        this.cooked = true;
    }
    public void cookInfo() { // Print what happens what cooked, or tell the player they already have been.
        if (!this.cooked) {
        System.out.printf("Make drumsticks:%n-1 Size.%n-30 Calories.%n%n");} else {
            System.out.printf("The chicken is already drumsticked.%n%n");
        }
    }
    public void info() { // Print Ingrediant stats.
        System.out.printf("%s:%nNutrition Value: %d.%nCalories: %d.%nSize: %d.%nGroup: Protein%n",this.name,this.nutrition,this.calories,this.size);
        System.out.printf("Special: When added to the meal, another random avalible ingrediant gets +3 nutrition.%n%n");
    }

}

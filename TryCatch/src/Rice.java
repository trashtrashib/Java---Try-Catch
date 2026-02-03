public class Rice extends Food {

    public Rice(Cooking game) {
        this.game = game;
        this.name = "Rice";
        this.nutrition = 5;
        this.calories = 120;
        this.size = 5;
        this.cooked = false;
        this.group = FoodGroup.Grain;
    }
    
    public void special(Trigger cause, boolean inFridge) {
        if (cause == Trigger.added) {
            for (int i = 0; i<game.getPot().size(); i++) {
                if (!game.getPot().get(i).equals(this)) {game.getPot().get(i).setCal(game.getPot().get(i).getCal() - 20);}
            }
        }
    }

    public void cook() {
        this.nutrition += 5;
        this.name = "Fried Rice";
        this.cooked = true;
    }
    public void cookinfo() {
        if (!this.cooked) {
        System.out.printf("Fry the rice:%n+5 Nutrition.%n%n");} else {
            System.out.printf("Next you'll be telling me a shrimp fried this rice.%n%n");
        }
    }
    public void info() {
        System.out.printf("%s:%nNutrition Value: %d.%nCalories: %d.%nSize: %d.%nGroup: Grain%n",this.name,this.nutrition,this.calories,this.size);
        System.out.printf("Special: When added to the meal, each other ingrediant loses 20 calories.%n%n");
    }

}

public class Carrot extends Food {

    public Carrot(Cooking game) {
        this.game = game;
        this.name = "Carrot";
        this.nutrition = 10;
        this.calories = 80;
        this.size = 5;
        this.cooked = false;
        this.group = FoodGroup.Vegetable;
    }
    
    public void special(Trigger cause, boolean inFridge) {
        if (this.cooked && cause == Trigger.eat && !inFridge) {game.setFridge(game.getFridgeSize() + 1);}
    }

    public void cook() {
        this.name = "Boiled Carrots";
        this.cooked = true;
    }
    public void cookinfo() {
        if (!this.cooked) {
        System.out.printf("Boil carrot:%nReceive an additional ingrediant for the rest of the game when eaten.%n%n");} else {
            System.out.printf("Soggy...%n%n");
        }
    }
    public void info() {
        System.out.printf("%s:%nNutrition Value: %d.%nCalories: %d.%nSize: %d.%nGroup: Vegetable%n%n",this.name,this.nutrition,this.calories,this.size);
        if (this.cooked) {System.out.printf("Special: Receive an additional ingrediant for the rest of the game when eaten.%n%n");} else {System.out.println();}
    }

}

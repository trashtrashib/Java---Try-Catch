public class BlueberryClone extends Blueberry {

    public BlueberryClone(Cooking game, Blueberry parent) {
        this.game = game;
        this.name = parent.name;
        this.nutrition = parent.nutrition;
        this.calories = parent.calories;
        this.size = parent.size;
        this.cooked = parent.cooked;
        this.group = parent.group;
    }
    
    public void special(Trigger cause, boolean inFridge) {}

}

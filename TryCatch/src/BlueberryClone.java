public class BlueberryClone extends Food {

    public BlueberryClone(Cooking game, Blueberry parent) { // copies parents stats.
        this.game = game;
        this.name = parent.name;
        this.nutrition = parent.nutrition;
        this.calories = parent.calories;
        this.size = parent.size;
        this.cooked = parent.cooked;
        this.group = parent.group;
    }
    

    // Dummy methods for abstract class.
    public void special(Trigger cause, boolean inFridge) {}
    public void info() {}
    public void cookInfo() {}
    public void cook() {}

}

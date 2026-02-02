public abstract class Food {

    private String name = "";
    private int nutrition = 0; // How good a food is for you.
    private int calories = 0; // How many calories.
    private int size = 0; // How filling it is.
    private boolean cooked = false; // Has it been modified?
    private FoodGroup group = FoodGroup.Protein; // What catagory of food it comes from.

    public int getNut() {return nutrition;}
    public int getCal() {return calories;}
    public int getSize() {return size;}
    public FoodGroup getGroup() {return group;}

    public abstract void cook();
    public abstract void cookinfo();
    public abstract void info();


}

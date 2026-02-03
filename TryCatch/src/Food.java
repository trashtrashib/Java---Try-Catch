public abstract class Food {

    protected String name = "";
    protected int nutrition = 0; // How good a food is for you.
    protected int calories = 0; // How many calories.
    protected int size = 0; // How filling it is.
    protected boolean cooked = false; // Has it been modified?
    protected FoodGroup group = FoodGroup.Protein; // What catagory of food it comes from.
    protected Cooking game;

    public String getName() {return this.name;}
    public int getNut() {return this.nutrition;}
    public int getCal() {return this.calories;}
    public int getSize() {return this.size;}
    public FoodGroup getGroup() {return this.group;}
    public boolean getCooked() {return cooked;}
    
    public void setNut(int nut) {this.nutrition = nut;}
    public void setCal(int cal) {this.calories = cal;}

    public abstract void cook();
    public abstract void cookinfo();
    public abstract void info();
    public abstract void special(Trigger cause, boolean inFridge);

}

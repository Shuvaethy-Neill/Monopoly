import java.util.ArrayList;

public class Player {

    private String name;
    private double money;
    private int position;
    private boolean bankrupt;
    //private int numDoublesRolled;
    private ArrayList<Property> properties;

    public Player(String name){
        this.name = name;
        money = 1500.00;
        position = 0;
        bankrupt = false;
        properties = new ArrayList<Property>();
    }

    public String getName() {
        return name;
    }

    public int getPosition(){
        return position;
    }

    public double getMoney() {
        return money;
    }

    //pass in Dice.getRollValue as spaces
    public void move (int spaces) {
        position = position + spaces;
    }

    public boolean isBankrupt(){
        return bankrupt;
    }

    public void doTransaction (int amount) {
        money = money - amount;
    }

    public void addProperty (Property property) {
        properties.add(property);
    }

    public ArrayList<Property> getProperties () {
        return properties;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

import java.util.ArrayList;

/**
 * The Player class represents a player in the game
 * @author
 *
 * @version 1.0
 *
 * @since 2021-10-19
 *
 */
public class Player {

    private String name; //name of player
    private double money; //how much money player has
    private int position; //position of the player on the board
    private boolean bankrupt; //state of bankruptcy
    private ArrayList<Property> properties;

    /**
     * Constructor for Player class
     * @param name
     */
    public Player(String name){
        this.name = name;
        money = 1500.00;
        position = 0;
        bankrupt = false;
        properties = new ArrayList<Property>();
    }

    /**
     * This method gets the name of the player
     * @return String, the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * This method gets the position of the player
     * @return int, position relative to board squares
     */
    public int getPosition(){
        return position;
    }

    /**
     * This method shows how much money the player has
     * @return
     */
    public double getMoney() {
        return money;
    }

    /**
     *
     * @param spaces
     */
    //pass in Dice.getRollValue as spaces
    public void move (int spaces) {
        position = position + spaces;
    }

    /**
     *
     * @return
     */
    public boolean isBankrupt(){
        return bankrupt;
    }

    /**
     *
     * @param amount
     */
    public void doTransaction (int amount) {
        money = money - amount;
    }

    /**
     *
     * @param property
     */
    public void addProperty (Property property) {
        properties.add(property);
    }

    /**
     *
     * @return
     */
    public ArrayList<Property> getProperties () {
        return properties;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return this.name;
    }
}

import java.util.ArrayList;

/**
 * The Player class represents a player in the game
 * @author s
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
    private int numDoublesRolled;
    private boolean bankrupt; //state of bankruptcy
    private ArrayList<Property> properties;

    /**
     * Constructor for Player class
     * @param name , name of player
     */
    public Player(String name){
        this.name = name;
        this.money = 1500.00;
        this.position = 0;
        this.numDoublesRolled = 0;
        this.bankrupt = false;
        this.properties = new ArrayList<Property>();
    }

    /**
     * This method gets the name of the player
     * @return String, the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * This method shows how much money the player has
     * @return double, the user's money
     */
    public double getMoney() {
        return money;
    }

    /**
     * This method gets the position of the player
     * @return int, position relative to board squares
     */
    public int getPosition(){
        return position;
    }

    /**
     * This method gets the number of doubles the user
     * has rolled during their turn
     * @return int, the number of doubles rolled
     */
    public int getNumDoublesRolled(){
        return this.numDoublesRolled;
    }

    /**
     * This method sets the number of doubles the user has rolled
     */
    public void setNumDoubledRolled(int amount){
        this.numDoublesRolled  = amount;
    }

    /**
     * This method moves the player around the board (change their position)
     * given the value of their dice roll
     * @param spaces , the amount of spaces they need to move on the board
     */
    //pass in Dice.getRollValue as spaces
    public void move (int spaces) {
        position = position + spaces;
    }

    /**
     * This method identifies the player's state of bankruptcy
     * @return boolean, true if bankrupt or false otherwise
     */
    public boolean isBankrupt(){
        return bankrupt;
    }

    /**
     * This method handles the player's money when they do any
     * sort of transaction where they need to pay
     * @param amount , the amount of money they need to pay
     */
    public void doTransaction (int amount) {
        money -= amount;
    }

    /**
     * This method handles the player's money when they do any
     * sort of transaction where they get money
     * @param amount , the amount of money they collect
     */
    public void setMoney (int amount) {
        money += amount;
    }

    /**
     * This method adds a property to the list of properties the player owns
     * @param property of type Property that will be added to the lst
     */
    public void addProperty (Property property) {
        properties.add(property);
    }

    /**
     * This method gets the list of properties that the player owns
     * @return ArrayList<Property>, the player's list of properties
     */
    public ArrayList<Property> getProperties () {
        return properties;
    }

    /**
     * This method overrides the toString method in the Object
     * class and provides a more accurate String representation
     * @return String, the name of the player
     */
    @Override
    public String toString() {
        return this.name;
    }
}

import java.util.ArrayList;
import java.util.Objects;

/**
 * The Player class represents a player in the game
 *
 * @author Shuvaethy Neill, Harsimran Kanwar, Dorothy Tran
 * @version 1.0
 * @since 2021-10-24
 */
public class Player {

    private String name; //name of player
    private double money; //how much money player has
    private int position; //position of the player on the board
    private String positionName; //name of the position the player is on
    private int numDoublesRolled; //count of how many doubles rolled in a turn
    private boolean bankrupt; //state of bankruptcy
    private ArrayList<Property> properties; //list of properties player owns

    /**
     * Constructor for Player class
     * @param name , name of player
     */
    public Player(String name){
        this.name = name;
        this.money = 1500.00;
        this.position = 0;
        this.positionName="";
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
     *
     * @param newPosition
     */
    public void setPosition(int newPosition) {
        this.position = newPosition;
    }

    /**
     * This method sets the position name of the player after being moved
     * @return String, position name relative to board squares
     */
    public void setPositionName(String newPosition){
        this.positionName = newPosition;
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
     * This method resets the number of doubles the user
     * has rolled during their turn
     * @return int, the number of doubles rolled
     */
    public void resetNumDoublesRolled() {
        this.numDoublesRolled = 0;
    }

    /**
     * This method increments the number of doubles the user has rolled
     */
    public void incrementNumDoublesRolled(){
        if (numDoublesRolled ==4){ //reset if more than 3 doubles rolled
            resetNumDoublesRolled();
        }
        else {
            this.numDoublesRolled++;
        }
    }

    /**
     * This method moves the player around the board (change their position)
     * given the value of their dice roll
     * @param spaces , the amount of spaces they need to move on the board
     */
    public void move(int spaces) {
        if(position + spaces >= BoardSquares.values().length) {
            position = ((position + spaces) - BoardSquares.values().length); //loops around the board
        }
        else{
            position += spaces;
        }
    }

    /**
     * This method identifies the player's state of bankruptcy
     * @return boolean, true if bankrupt or false otherwise
     */
    public boolean isBankrupt(int toPay){
        if(money < toPay){bankrupt = true;} //might be bankrupt before 0
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
     * This method overrides the toString method in the Object class
     * and provides a more accurate String representation of the whole
     * state of the player (name, position, money, properties owned)
     *
     * @return String, state of player
     */
    @Override
    public String toString() {
        return "You are player: " + name
                + "\nYou're currently located at: " + positionName
                + "\nYou have $" + money + " in your account"
                + "\nYour properties are: " + properties.toString();
    }

    /**
     * Method to check if a player is equivalent to another player
     *
     * @param o Object, the object being compared with the object the method is called on
     * @return boolean, if the objects are equivalent
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return name == player.name && money == player.money && position==player.position && Objects.equals(properties, player.properties);
    }
}

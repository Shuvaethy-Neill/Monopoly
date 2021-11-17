import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * The Property class extends BoardSpace and represents
 * a space that is specifically a property
 *
 * @author
 * @version 1.0
 * @since 2021-11-17
 */
public class Railroad extends BoardSpace {
    /**
     * Whether the property is available or not
     */
    private boolean isAvailable;

    /**
     * The price of the property
     */
    private final int price;

    private Player owner;

    private ImageIcon square;

    /**
     * The Property constructor
     *
     * @param name String, The name of the property
     * @param price int, The price of the property
     */
    public Railroad(String name, int price, String path) {
        super(name, "railroad", path);
        this.isAvailable = true;
        this.price = price;
        this.square = new ImageIcon(path);
    }

    /**
     * Getter for the availability of the railroad
     *
     * @return boolean, availability of the railroad
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * sets the owner of the railroad
     *
     * @param p player who owns the railroad
     */
    public void setOwner(Player p){
        this.owner= p;
    }

    /**
     * Getter for the price of the railroad
     *
     * @return int, the price of the railroad
     */
    public int getPrice() {
        return price;
    }

    /**
     * Getter for the owner of the railroad
     *
     * @return Player, the player of the railroad
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Purchase a railroad by setting the isAvailable attribute to false
     *
     * @return boolean, whether the railroad was successful or not
     */
    public boolean purchase() {
        if (isAvailable) {
            isAvailable = false;
            return true;
        }
        return false;
    }

    /**
     * Sell a railroad by setting the isAvailable attribute to true
     *
     * @return boolean, whether the sale was successful or not
     */
    public boolean sell() {
        if (!isAvailable) {
            isAvailable = true;
            return true;
        }
        this.owner = null;
        return false;
    }

    /**
     * Get the price of rent for the railroad
     *
     * @return int, the price of railroad
     */

    //HAVE TO CHANGE THIS!! are we doing 1 = 25, 2 = 50,...? probs right?
    public int getRent() {
        return (int) Math.round(Math.pow(price, 3) * 0.000001 + Math.pow(price, 2) * -0.0007 + price * 0.2014 - 7.5593);
    }

    /**
     * Displays railroad landed on along with if it's available for purchase or they pay rent.
     */
    @Override
    public String displayInfo() {
        String text = "\nYou are now located at: " + getName();
        if (isAvailable){
            text += "\nThis railroad costs $" + getPrice();
        }
        else{
            text += "\nThe rent for this railroad is $" + getRent() + ".";
        }
        return text;
    }

    /**
     *
     */
    @Override
    public void updatePanel() {

    }

    /**
     * Determines if a railroad is equivalent to another railroad
     *
     * @param o Object, the object to be compared with the object this method is called on
     * @return boolean, if the objects are equivalent
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Railroad railroad = (Railroad) o;
        return isAvailable == railroad.isAvailable && price == railroad.price && Objects.equals(owner, railroad.owner);
    }

    /**
     * Returns a String representation of the Railroad object
     *
     * @return String, the railroad object as a string value
     */
    @Override
    public String toString() {
        return "Name: " + this.getName() + ", Price: " + this.getPrice();
    }
}

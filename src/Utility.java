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
public class Utility extends BoardSpace {
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
    public Utility(String name, int price, String path) {
        super(name, "utility", path);
        this.isAvailable = true;
        this.price = price;
        this.square = new ImageIcon(path);
    }

    /**
     * Getter for the availability of the utility
     *
     * @return boolean, availability of the utility
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * sets the owner of the utility
     *
     * @param p player who owns the utility
     */
    public void setOwner(Player p){
        this.owner= p;
    }

    /**
     * Getter for the price of the utility
     *
     * @return int, the price of the utility
     */
    public int getPrice() {
        return price;
    }

    /**
     * Getter for the owner of the utility
     *
     * @return Player, the player of the utility
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Purchase a utility by setting the isAvailable attribute to false
     *
     * @return boolean, whether the utility was successful or not
     */
    public boolean purchase() {
        if (isAvailable) {
            isAvailable = false;
            return true;
        }
        return false;
    }

    /**
     * Sell a utility by setting the isAvailable attribute to true
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
     * Get the price of rent for the utility
     *
     * @return int, the price of utility
     */

    //HAVE TO CHANGE THIS!! are we doing 1 = 25, 2 = 50,...? probs right?
    public int getRent() {
        return (int) Math.round(Math.pow(price, 3) * 0.000001 + Math.pow(price, 2) * -0.0007 + price * 0.2014 - 7.5593);
    }

    /**
     * Displays utility landed on along with if it's available for purchase or they pay rent.
     */
    @Override
    public String displayInfo() {
        String text = "\nYou are now located at: " + getName();
        if (isAvailable){
            text += "\nThis utility costs $" + getPrice();
        }
        else{
            text += "\nThe rent for this utility is $" + getRent() + ".";
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
     * Determines if a utility is equivalent to another utility
     *
     * @param o Object, the object to be compared with the object this method is called on
     * @return boolean, if the objects are equivalent
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utility utility = (Utility) o;
        return isAvailable == utility.isAvailable && price == utility.price && Objects.equals(owner, utility.owner);
    }

    /**
     * Returns a String representation of the Utility object
     *
     * @return String, the utility object as a string value
     */
    @Override
    public String toString() {
        return "Name: " + this.getName() + ", Price: " + this.getPrice();
    }
}

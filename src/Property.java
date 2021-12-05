import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * The Property class extends BoardSpace and represents
 * a space that is specifically a property
 *
 * @author Evan Smedley
 * @version 1.0
 * @since 2021-10-22
 */
public abstract class Property extends BoardSpace {
    /**
     * Whether the property is available or not
     */
    protected boolean isAvailable;

    /**
     * The price of the property
     */
    protected int price;

    /**
     * The owner of the property
     */
    protected Player owner;

    /**
     * The color of the property
     */
    protected String color;

    /**
     * The Property constructor
     *
     * @param name String, The name of the property
     * @param price int, The price of the property
     */
    public Property(String name, int price, String type, String color, String path, int position) {
        super(name, type, path, position);
        this.isAvailable = true;
        this.color = color;
        this.price = price;
    }

    /**
     * Getter for the color of the property
     *
     * @return String, the color of the property
     */
    public String getColor() {
        return color;
    }

    /**
     * Getter for the availability of the property
     *
     * @return boolean, availability of the property
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * sets the owner of the property
     *
     * @param p player who owns the property
     */
    public void setOwner(Player p){
        this.owner= p;
    }

    /**
     * Getter for the price of the property
     *
     * @return int, the price of the property
     */
    public int getPrice() {
        return price;
    }

    /**
     * Getter for the owner of the property
     *
     * @return Player, the player of the property
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Purchase a property by setting the isAvailable attribute to false
     *
     * @return boolean, whether the purchase was successful or not
     */
    public boolean purchase() {
        if (isAvailable) {
            isAvailable = false;
            return true;
        }
        return false;
    }

    /**
     * Sell a property by setting the isAvailable attribute to true
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
     *
     * @param available
     */
    public void setIsAvailable(boolean available) {
        this.isAvailable = available;
    }

    /**
     *
     * @param price
     */
    public void setPrice(int price) {
        this.price = price;
        centerPanel.add(new JLabel("$" + String.valueOf(price)), JLabel.CENTER);
    }

    /**
     *
     * @param color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Get the price of rent for the property
     *
     * @return int, the price of rent
     */
    public abstract int getRent();

    /**
     * Displays property landed on along with if it's available for purchase or they pay rent.
     */
    @Override
    public String displayInfo() {
        String text = "\nYou are now located at: " + getName();
        if (isAvailable){
            text += "\nThis property costs $" + getPrice();
        }
        else{
            text += "\nThe rent for this property is $";
        }
        return text;
    }

    /**
     * Determines if a property is equivalent to another property
     *
     * @param o Object, the object to be compared with the object this method is called on
     * @return boolean, if the objects are equivalent
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property property = (Property) o;
        return isAvailable == property.isAvailable && price == property.price && Objects.equals(owner, property.owner);
    }

    /**
     * Returns a String representation of the Property object
     *
     * @return String, the property object as a string value
     */
    @Override
    public String toString() {
        return "Name: " + this.getName() + ", Color: " + ", Price: " + this.getPrice();
    }
}
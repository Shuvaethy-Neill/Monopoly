import java.util.Objects;

/**
 * A property
 *
 * @author e
 * @version 1.0
 */
public class Property extends BoardSpace {
    /**
     * Whether the property is available or not
     */
    private boolean isAvailable;

    /**
     * The price of the property
     */
    private final int price;

    /**
     * The color group of the property
     */
    private final String color;

    private Player owner;

    /**
     * The Property constructor
     *
     * @param name String, The name of the property
     * @param price int, The price of the property
     * @param color String, The color of the property
     */
    public Property(String name, int price, String color) {
        super(name, "property");
        this.isAvailable = true;
        this.price = price;
        this.color = color;
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
     * Getter for the color of the property
     *
     * @return String, the color of the property
     */
    public String getColor() {
        return color;
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
     * Get the price of rent for the property
     *
     * @return int, the price of rent
     */
    public int getRent() {
        return (int) Math.round(Math.pow(price, 3) * 0.000001 + Math.pow(price, 2) * -0.0007 + price * 0.2014 - 7.5593);
    }

    /**
     * Displays property landed on along with if it's available for purchase or they pay rent.
     */
    @Override
    public void displayInfo() {
        System.out.println("You are now located at: " + getName());
        if (isAvailable){
            System.out.println("This property costs $" + getPrice());
            System.out.println("Type 'buy' if you would like to purchase this property");
            System.out.println("Type 'pass' if you would not like to purchase this property");
        }
        else{
            System.out.println("You must pay rent, taking $ " + getRent() + " from your account");
        }
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property property = (Property) o;
        return isAvailable == property.isAvailable && price == property.price && Objects.equals(color, property.color) && Objects.equals(owner, property.owner);
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(isAvailable, price, color, owner);
    }

    /**
     *
     * @return
     */
    public String toString() {
        return this.getName() + " (" + this.getColor() + ")"; //can change this but did it for now to see who owns what 
    }
}

/**
 * Represents a monopoly property
 *
 * @author Evan Smedley
 */
public class Property {
    /**
     * Whether the property is available or not
     */
    private boolean isAvailable;

    /**
     * The property's name
     */
    private String name;

    /**
     * The price of the property
     */
    private int price;

    /**
     * The color group of the property
     */
    private String color;

    /**
     * The Property constructor
     *
     * @param name String, The name of the property
     * @param price int, The price of the property
     * @param color String, The color of the property
     */
    public Property(String name, int price, String color) {
        this.isAvailable = true;
        this.name = name;
        this.price = price;
        this.color = color;
    }

    /**
     * Purchase a property by setting the isAvailable attribute to false
     *
     * @return boolean, Whether the purchase was successful or not
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
     * @return boolean, Whether the sale was successful or not
     */
    public boolean sell() {
        if (!isAvailable) {
            isAvailable = true;
            return true;
        }
        return false;
    }

    /**
     * Get the price of rent for the property
     *
     * @return int, The price of rent
     */
    public int getRent() {
        return (int) Math.round(Math.pow(price, 3) * 0.000001 + Math.pow(price, 2) * -0.0007 + price * 0.2014 - 7.5593);
    }
}

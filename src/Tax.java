import javax.swing.*;
import java.io.IOException;

/**
 * The Tax class extends BoardSpace and represents
 * a space that is specifically tax
 *
 * @author Evan Smedley
 * @version 1.0
 * @since 2021-10-22
 */
public class Tax extends BoardSpace {

    /**
     * The cost of landing on the Tax BoardSpace
     */
    private final int cost;

    private ImageIcon square;

    /**
     * Constructor for the Tax Class
     *
     * @param name, the name of the tax space
     * @param cost, the cost of landing on the tax space
     */
    public Tax(String name, int cost, String path, int position) {
        super(name, "tax", path, position);
        this.cost = cost;
    }

    /**
     * Getter for the cost of landing on the tax space
     *
     * @return int, the cost of landing on the tax space
     */
    public int getCost() {
        return this.cost;
    }

    /**
     * A method for displaying information about the tax space
     */
    @Override
    public String displayInfo() {
        String text = "\nYou are now located at: " + getName();
        text +="\nYou must pay tax! Extracting $" + cost + " from account.\n";
        return text;
    }

    /**
     * Determines if an instance of Tax is equivalent to another instance of Tax
     *
     * @param o, Object
     * @return boolean, if the objects are equivalent
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tax tax = (Tax) o;
        return cost == tax.cost;
    }

    /**
     * Returns a String representation of the Tax object
     *
     * @return String, representation of the Tax object
     */
    @Override
    public String toString() {
        return "Name: " + getName() + ", Cost: " + cost;
    }
}

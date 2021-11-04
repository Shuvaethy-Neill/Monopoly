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

    /**
     * Constructor for the Tax Class
     *
     * @param name, the name of the tax space
     * @param cost, the cost of landing on the tax space
     */
    public Tax(String name, int cost) {
        super(name, "tax");
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
    public void displayInfo() {
        System.out.println("You are now located at: " + getName());
        System.out.println("You must pay tax! Extracting $" + cost + " from account.");
    }

    /**
     *
     */
    @Override
    public void updatePanel() {

    }

    /**
     * Determines if an instance of Tax is equivalent to another instance of Tax
     *
     * @param o
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

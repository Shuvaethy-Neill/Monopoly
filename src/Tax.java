import java.util.Objects;

/**
 * A Tax space on the board
 *
 * @author e
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
     * @param name
     * @param cost
     */
    public Tax(String name, int cost) {
        super(name, "tax");
        this.cost = cost;
    }

    /**
     *
     * @return
     */
    public int getCost() {
        return this.cost;
    }

    /**
     *
     */
    @Override
    public void displayInfo() {
        System.out.println("You are now located at: " + getName());
        System.out.println("You must pay tax! Extracting $" + cost + " from account.");
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
        Tax tax = (Tax) o;
        return cost == tax.cost;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(cost);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Tax{" +
                "cost=" + cost +
                '}';
    }
}

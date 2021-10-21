import java.util.Objects;

/**
 * A Tax space on the board
 *
 * @author e
 * @version 1.0
 */
public class Tax extends BoardSpace {

    /**
     *
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

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
     *
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
}

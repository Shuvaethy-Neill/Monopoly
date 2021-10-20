/**
 * A free parking space on the board
 *
 * @author
 * @verion 1.0
 */
public class FreeParking extends BoardSpace {

    /**
     *
     */
    private int amount;

    /**
     *
     * @param name
     */
    public FreeParking(String name) {
        super(name, "free parking");
        this.amount = 0;
    }

    /**
     *
     * @param amount
     */
    public void addAmount(int amount) {
        this.amount += amount;
    }

    /**
     *
     * @return
     */
    public int getAmount() {
        return this.amount;
    }

    /**
     *
     */
    @Override
    public void displayInfo() {

    }
}

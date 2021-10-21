import java.util.Objects;

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
     * FreeParking class constructor
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

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FreeParking that = (FreeParking) o;
        return amount == that.amount;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "FreeParking{" +
                "amount=" + amount +
                '}';
    }
}

/**
 * The Go class extends BoardSpace and specifically
 * represents the GO space
 *
 * @author Harsimran Kanwar, Shuvaethy Neill, Dorothy Tran
 * @verion 1.0
 * @since 2021-11-17
 */
public class Go extends BoardSpace {

    /**
     * The amount of money GO gives a player
     */
    private final int amount;

    /**
     * GO constructor
     *
     * @param name String, the name of the free parking space
     */
    public Go(String name, String path, int position) {
        super(name, "go", path, position);
        this.amount = 200;
    }
    /**
     * A getter for the amount of money currently at free parking
     *
     * @return int, the amount of money currently at free parking
     */
    public int getAmount() {
        return this.amount;
    }

    /**
     * A method for displaying information about the free parking space
     */
    @Override
    public String displayInfo() {
        return "\nYou are now located at: " + getName() + "\nThis is a free space!";
    }

    /**
     * Method to check if a FreeParking object is equivalent to another FreeParking object
     *
     * @param o Object, the object to be compared with the object this method is called on
     * @return boolean, if the objects are equivalent
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Go that = (Go) o;
        return amount == that.amount;
    }

    /**
     * Returns a String representation of this instance of the property object
     *
     * @return String, string representation of the property object
     */
    @Override
    public String toString() {
        return "Name: " + getName() + ", Amount: " + amount;
    }
}

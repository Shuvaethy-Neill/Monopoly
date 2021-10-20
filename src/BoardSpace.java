/**
 * A space on the monopoly board
 *
 * @author e
 * @version 1.0
 */
public abstract class BoardSpace {

    /**
     *
     */
    private final String name;

    /**
     *
     */
    private final String type;

    /**
     *
     */
    public BoardSpace(String name, String type) {
        this.name = name;
        this.type = type;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     *
     * @return
     */
    public String getType() {
        return this.type;
    }

    /**
     *
     */
    public abstract void displayInfo();
}

/**
 * A space on the monopoly board
 *
 * @author
 * @version 1.0
 * @since 2021-10-22
 */
public abstract class BoardSpace {

    /**
     * The name of the board space
     */
    private final String name;

    /**
     * The type of board space
     */
    private final String type;

    /**
     * The constructor for a BoardSpace object
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

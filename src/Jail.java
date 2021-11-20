/**
 * Jail Class that extends the BoardSpace Class for when a player is in a Jail board square
 */
public class Jail extends BoardSpace{
    private String type;

    /**
     * Constructor for the Jail Class
     * @param name String, name of the board square
     * @param path String, path directory
     * @param type String, type of property
     * @param position int, position number on the board
     */
    public Jail(String name, String path, String type,int position) {
        super(name, type, path, position);
        this.type = type;
    }

    /**
     * Method returns the  Jail board square type (Just Visiting or Go to Jail)
     * @return
     */
    public String getType(){
        return this.type;
    }

    /**
     * Overridden displayInfo method that displays info to the player when they are sent to jail
     * @return String, text info
     */
    @Override
    public String displayInfo() {
        if (type.equals("go to jail")) {
            return "\nYou are being sent to Jail!";
        }
        return "";
    }

}

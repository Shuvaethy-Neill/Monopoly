import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Jail Class that extends the BoardSpace Class for
 * when a player is in a Jail board square
 *
 * @author Harsimran Kanwar and Dorothy Tran
 * @verion 1.0
 * @since 2021-11-17
 */

public class Jail extends BoardSpace{

    /**
     *
     */
    private String type;

    /**
     *
     */
    public Jail() {
        this("", "", "", -1);
    }

    /**
     * Constructor for the Jail Class
     * @param name String, name of the board square
     * @param type String, type of property
     * @param s
     * @param position int, position number on the board
     */
    public Jail(String name, String type, String s, int position) {
        super(name, type, s, position);
        this.type = type;

        ImageIcon jailIcon;
        if (type.equals("go to jail")) {
            jailIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("images/go_to_jail.png")));
        } else {
            jailIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("images/in_jail.png")));

        }
        centerPanel.add(new JLabel(new ImageIcon(jailIcon.getImage().getScaledInstance(40, 40, Image.SCALE_FAST))), BorderLayout.CENTER);
    }

    @Override
    /**
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Method returns the  Jail board square type (Just Visiting or Go to Jail)
     * @return the type
     */
    public String getType(){
        return this.type;
    }

    @Override
    /**
     * Method sets the  Jail board square type (Just Visiting or Go to Jail)
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Overridden displayInfo method that displays info to the player when they are sent to jail
     * @return String, text info
     */
    @Override
    public String displayInfo() {
        if (type.equals("go to jail")) {
            return "\nYou are being sent to Jail!\n";
        }
        return "";
    }

}

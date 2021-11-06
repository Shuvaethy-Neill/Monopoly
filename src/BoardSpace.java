import javax.swing.*;
import java.awt.*;

/**
 * The BoardSpace class is an abstract class that
 * represents a space on the board
 *
 * @author Evan Smedley
 * @version 1.0
 * @since 2021-10-22
 */
public abstract class BoardSpace extends JPanel {

    /**
     * The name of the board space
     */
    private final String name;

    /**
     * The type of board space
     */
    private final String type;

    private ImageIcon square;


    /**
     * The constructor for a BoardSpace object
     */
    public BoardSpace(String name, String type, String path) {
        super(new BorderLayout());
        this.name = name;
        this.type = type;
        this.square = new ImageIcon(path);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //JLabel nameLabel = new JLabel(name, JLabel.CENTER);
        //this.add(nameLabel, BorderLayout.PAGE_START);

        this.square = new ImageIcon(this.square.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
        JLabel picLabel = new JLabel(this.square, JLabel.CENTER);
        this.add(picLabel, BorderLayout.PAGE_START);

        this.setPreferredSize(new Dimension(50, 50));
    }

    /**
     * Getter for the name of the board space
     *
     * @return String, name of the board space
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for the type of the board space
     *
     * @return String, type of the board space
     */
    public String getType() {
        return this.type;
    }

    /**
     * An abstract method for displaying information about the board space
     */
    public abstract void displayInfo();

    /**
     *
     */
    public abstract void updatePanel();
}

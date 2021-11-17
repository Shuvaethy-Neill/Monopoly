import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The BoardSpace class is an abstract class that
 * represents a space on the board
 *
 * @author Evan Smedley, Harsismran Kanwar, Dorothy Tran, Shuvaethy Neill
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

    /**
     * Image for the board space
     */
    private JLabel square;

    /**
     * Position number of the BoardSpace
     */
    private final int position;

    private ArrayList<JLabel> playerIcons;

    private ArrayList<String> playerIconPaths;

    private JPanel playerIconPanel;


    /**
     * The constructor for a BoardSpace object
     */
    public BoardSpace(String name, String type, String path, int position) {
        super(new BorderLayout());
        this.name = name;
        this.type = type;
        this.position = position;
        this.playerIcons = new ArrayList<>();
        this.playerIconPaths = new ArrayList<>(Arrays.asList("images/yellow-player.png", "images/blue-player.png",
                "images/red-player.png", "images/purple-player.png"));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        playerIconPanel = new JPanel(new GridLayout(1,4));
        playerIconPanel.setPreferredSize(new Dimension(80,20));
        this.add(playerIconPanel, BorderLayout.PAGE_START);

        for (int i = 0; i < playerIconPaths.size(); i++) {
            ImageIcon playerIcon = new ImageIcon(playerIconPaths.get(i));
            playerIcons.add(new JLabel(new ImageIcon(playerIcon.getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT))));
        }

        ImageIcon temp1 = new ImageIcon(path);
        ImageIcon temp2 = new ImageIcon(temp1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        this.square = new JLabel(temp2, JLabel.CENTER);
        this.add(square, BorderLayout.CENTER);

        this.setPreferredSize(new Dimension(40, 40));
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
     *
     */
    public int getPosition() {
        return this.position;
    }

    /**
     *
     * @param playerIndex
     */
    public void addPlayerIcon(int playerIndex) {
        playerIconPanel.add(playerIcons.get(playerIndex));
    }

    /**
     *
     */
    public void clearPlayerIcons() {
        playerIconPanel.removeAll();
    }

    /**
     * An abstract method for displaying information about the board space
     */
    public abstract String displayInfo();
}

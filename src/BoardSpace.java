import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * The BoardSpace class is an abstract class that
 * represents a space on the board
 *
 * @author Evan Smedley, Harsismran Kanwar, Dorothy Tran, Shuvaethy Neill
 * @version 3.0
 * @since 2021-11-21
 */
public abstract class BoardSpace extends JPanel {

    /**
     * The name of the board space
     */
    private String name;

    /**
     * The type of board space
     */
    private String type;

    /**
     * The path of the background image for this board space
     */
    private String path;

    /**
     * Position number of the BoardSpace
     */
    private int position;

    /**
     * An arraylist of all the player icons
     */
    private ArrayList<JLabel> playerIcons;

    /**
     * An arraylist of the path of each player icon
     */
    private ArrayList<String> playerIconPaths;

    /**
     * The panel that the player icons are displayed in
     */
    private JPanel playerIconPanel;

    /**
     * The background image for this board space
     */
    private Image backgroundImage;

    /**
     * The constructor for a BoardSpace object
     */
    public BoardSpace(String name, String type, String path, int position) {
        super(new BorderLayout());
        this.name = name;
        this.type = type;
        this.path = path;
        this.position = position;
        this.playerIcons = new ArrayList<>();
        this.playerIconPaths = new ArrayList<>(Arrays.asList("images/yellow-player.png", "images/blue-player.png",
                "images/red-player.png", "images/purple-player.png", "images/greenp.png", "images/pink.png","images/burgundyP.png","images/turquoiseP.png"));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //readBackgroundImage();

        playerIconPanel = new JPanel(new GridLayout(1,8));
        playerIconPanel.setPreferredSize(new Dimension(80,10));
        playerIconPanel.setOpaque(false);
        this.add(playerIconPanel, BorderLayout.CENTER);

        for (String playerIconPath : playerIconPaths) {
            ImageIcon playerIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(playerIconPath)));
            playerIcons.add(new JLabel(new ImageIcon(playerIcon.getImage().getScaledInstance(10, 10, Image.SCALE_FAST))));
        }

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
     * A getter for the position of this board space
     *
     * @return int, the position of this board space
     */
    public int getPosition() {
        return this.position;
    }

    /**
     * Add a player icon to this board space
     *
     * @param playerIndex int, the index of the player to add
     */
    public void addPlayerIcon(int playerIndex) {
        playerIconPanel.add(playerIcons.get(playerIndex));
    }

    /**
     * Remove all player icons from this board space
     */
    public void clearPlayerIcons() {
        playerIconPanel.removeAll();
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     *
     * @param position
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Read the background image for this board space
     */
    public void readBackgroundImage() {
        try {
            BufferedImage inputImage = ImageIO.read(Objects.requireNonNull(getClass().getResource(String.valueOf(new File(path)))));
            //Image inputImage = ImageIO.read(new File(path));
            backgroundImage = inputImage.getScaledInstance(80,80,Image.SCALE_FAST);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * An abstract method for displaying information about the board space
     */
    public abstract String displayInfo();

    /**
     * Paint this board space normally but also add a background image
     *
     * @param g Graphics
     */
    public void paintComponent (Graphics g) {
        //super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
    }
}

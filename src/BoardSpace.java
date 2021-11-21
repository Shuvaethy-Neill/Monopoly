import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
    private final String name;

    /**
     * The type of board space
     */
    private final String type;

    private final String path;

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

    private JLabel houseIcon;

    private JLabel hotelIcon;

    private JPanel playerIconPanel;

    private JPanel houseHotelPanel;

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

        readBackgroundImage();

        houseHotelPanel = new JPanel(new GridLayout(1,4));
        houseHotelPanel.setPreferredSize(new Dimension(80,20));
        houseHotelPanel.setOpaque(false);
        this.add(houseHotelPanel, BorderLayout.PAGE_START);

        ImageIcon tempHouseIcon = new ImageIcon("images/house.png");
        houseIcon = new JLabel(new ImageIcon(tempHouseIcon.getImage().getScaledInstance(20,20,Image.SCALE_FAST)));
        ImageIcon tempHotelIcon = new ImageIcon("images/hotel.png");
        hotelIcon = new JLabel(new ImageIcon(tempHotelIcon.getImage().getScaledInstance(20,20,Image.SCALE_FAST)));

        playerIconPanel = new JPanel(new GridLayout(1,8));
        playerIconPanel.setPreferredSize(new Dimension(80,10));
        playerIconPanel.setOpaque(false);
        this.add(playerIconPanel, BorderLayout.PAGE_END);

        for (String playerIconPath : playerIconPaths) {
            ImageIcon playerIcon = new ImageIcon(playerIconPath);
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

    public void addHouseHotel(int playerIndex) {
    }

    public void removeHouseHotel() {
    }

    private void readBackgroundImage() {
        try {
            Image inputImage = ImageIO.read(new File(path));
            backgroundImage = inputImage.getScaledInstance(80,80,Image.SCALE_FAST);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * An abstract method for displaying information about the board space
     */
    public abstract String displayInfo();

    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
    }
}

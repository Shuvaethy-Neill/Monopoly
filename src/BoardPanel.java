import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * BoardPanel Class that extends from the JPanel Class
 * and implements the MonopolyView Interface
 *
 * @author Evan Smedley
 * @version 2.0
 * @since 2021-11-21
 */
public class BoardPanel extends JPanel implements MonopolyView, Serializable {

    private MonopolyModel model;

    private JLabel monopolyLabel;

    private int dimension;

    private GridBagConstraints monopolyLabelConstraints;

    private GridBagConstraints[] boardSpaceConstraints;

    /**
     * Constructor for the BoardPanel Class
     *
     * @param model MonopolyModel, MVC MonopolyModel of the Monopoly Board
     */
    public BoardPanel(MonopolyModel model) {
        super(new GridBagLayout());
        this.model = model;
        this.setPreferredSize(new Dimension(640, 640));
        this.model.addView(this);

        initializeLayout();
    }

    /**
     * Method that creates the layout of the Monopoly Board
     */
    private void initializeLayout() {
        BoardSpace[] boardSpaces = model.getPieces().toArray(new BoardSpace[0]);
        this.boardSpaceConstraints = new GridBagConstraints[boardSpaces.length];

        dimension = ((boardSpaces.length % 4 == 0) ? (boardSpaces.length / 4 + 1) : (boardSpaces.length / 4 + 2));
        int x = dimension - 1;
        int y = dimension - 1;
        for (int i = 0; i < boardSpaces.length; i++) {
            boardSpaceConstraints[i] = new GridBagConstraints();
            boardSpaceConstraints[i].fill = GridBagConstraints.HORIZONTAL;
            boardSpaceConstraints[i].gridx = x;
            boardSpaceConstraints[i].gridy = y;

            //layeredBoardSpacePanes

            if (y == dimension - 1) {
                if (x == 0) {
                    y--;
                } else {
                    x--;
                }
            } else if (y == 0) {
                if (x == dimension - 1) {
                    y++;
                } else {
                    x++;
                }
            } else {
                if (x == 0) {
                    y--;
                } else if (x == dimension - 1) {
                    y++;
                }
            }
        }
        monopolyLabelConstraints = new GridBagConstraints();
        monopolyLabelConstraints.fill = GridBagConstraints.HORIZONTAL;
        monopolyLabelConstraints.gridwidth = dimension - 2;
        monopolyLabelConstraints.gridheight = dimension - 2;
        monopolyLabelConstraints.gridx = 1;
        monopolyLabelConstraints.gridy = 1;

        //monopolyLabel = new JLabel(new ImageIcon("images/logo.jpg"), JLabel.CENTER);
        monopolyLabel = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("images/logo.jpg"))), JLabel.CENTER);
        monopolyLabel.setPreferredSize(new Dimension(512, 512));
    }

    /**
     * Method updates the MonopolyEvent
     */
    @Override
    public void update(MonopolyEvent e) {
        this.removeAll();
        ArrayList<Player> players = model.getPlayers();

        for (int i = 0; i < boardSpaceConstraints.length; i++) {
            BoardSpace currentSpace = model.getPieces().get(i);
            currentSpace.clearPlayerIcons();

            for (int j = 0; j < players.size(); j++) {
                if (players.get(j).getPosition() == currentSpace.getPosition()) {
                    currentSpace.addPlayerIcon(j);
                }
            }
            this.add(currentSpace, boardSpaceConstraints[i]);
        }
        this.add(monopolyLabel, monopolyLabelConstraints);
        this.repaint();
    }
}
import javax.swing.*;
import java.awt.*;

/**
 * BoardPanel Class that extends from the JPanel Class and implements the MonopolyView Interface
 */
public class BoardPanel extends JPanel implements MonopolyView {

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
        this.setPreferredSize(new Dimension(1200, 1200));
        this.model.addView(this);

        initializeLayout();
        update();
    }

    /**
     * Method that creates the layout of the Monopoly Board
     */
    private void initializeLayout() {
        BoardSpace[] boardSpaces = model.getPieces();
        this.boardSpaceConstraints = new GridBagConstraints[boardSpaces.length];

        dimension = ((boardSpaces.length % 4 == 0) ? (boardSpaces.length / 4) : (boardSpaces.length / 4 + 1));
        int x = dimension - 1;
        int y = dimension - 1;
        for (int i = 0; i < boardSpaces.length; i++) {
            boardSpaceConstraints[i] = new GridBagConstraints();
            boardSpaceConstraints[i].fill = GridBagConstraints.HORIZONTAL;
            boardSpaceConstraints[i].gridx = x;
            boardSpaceConstraints[i].gridy = y;

            if (x == 0) {
                x = dimension - 1;
                y--;
            } else if ((y == 0) | (y == dimension - 1)) {
                x--;
            } else {
                x = 0;
            }
        }
        monopolyLabelConstraints = new GridBagConstraints();
        monopolyLabelConstraints.fill = GridBagConstraints.HORIZONTAL;
        monopolyLabelConstraints.gridwidth = dimension - 2;
        monopolyLabelConstraints.gridheight = dimension - 2;
        monopolyLabelConstraints.gridx = 1;
        monopolyLabelConstraints.gridy = 1;

        monopolyLabel = new JLabel("MONOPOLY", JLabel.CENTER);
    }

    /**
     *
     */
    @Override
    public void update() {
        this.removeAll();
        BoardSpace currentSpace;
        for (int i = 0; i < boardSpaceConstraints.length; i++) {
            currentSpace = model.getPieces()[i];
            currentSpace.setPreferredSize(new Dimension(1200/dimension, 1200/dimension));
            this.add(currentSpace, boardSpaceConstraints[i]);
        }
        this.add(monopolyLabel, monopolyLabelConstraints);
    }
}
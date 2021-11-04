import javax.swing.*;
import java.awt.*;

/**
 *
 */
public class BoardPanel extends JPanel implements MonopolyView {

    /**
     *
     */
    private MonopolyModel model;

    /**
     *
     */
    private JLabel monopolyLabel;

    /**
     *
     */
    private GridBagConstraints monopolyLabelConstraints;

    /**
     *
     */
    private GridBagConstraints[] boardSpaceConstraints;

    /**
     *
     * @param model
     */
    public BoardPanel(MonopolyModel model) {
        super(new GridBagLayout());
        this.model = model;

        initializeLayout();
        update();
    }

    /**
     *
     */
    private void initializeLayout() {
        BoardSpace[] boardSpaces = model.getPieces();
        this.boardSpaceConstraints = new GridBagConstraints[boardSpaces.length];

        int dimension = ((boardSpaces.length % 4 == 0) ? (boardSpaces.length / 4) : (boardSpaces.length / 4 + 1));
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
        monopolyLabel.setPreferredSize(new Dimension(500 / dimension,500 / dimension));
    }

    /**
     *
     */
    @Override
    public void update() {
        this.removeAll();
        for (int i = 0; i < boardSpaceConstraints.length; i++) {
            this.add(model.getPieces()[i], boardSpaceConstraints[i]);
        }
        this.add(monopolyLabel, monopolyLabelConstraints);
    }
}

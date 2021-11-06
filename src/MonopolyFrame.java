import javax.swing.*;
import java.awt.*;

/**
 * MonopolyFrame Class that extends from the JFrame Class and implements from the
 * MonopolyView Interface representing the GUI Frame
 *
 * @author Evan Smedley
 * @version 1.0
 * @since 2021-10-22
 */
public class MonopolyFrame extends JFrame implements MonopolyView {

    private MonopolyModel model;

    private JPanel instructionPanel;

    private static final int MAX_PLAYERS = 4;

    /**
     * Constructor for the MonopolyFrame Class
     *
     * @param model
     */
    public MonopolyFrame(MonopolyModel model) {
        super("Monopoly");
        this.model = model;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1600,1600));
        this.model.addView(this);

        instructionPanel = new JPanel(new BorderLayout());
        instructionPanel.setPreferredSize(new Dimension(1600,200));
        instructionPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        instructionPanel.add(new JLabel("This is where instructions and buttons to make decisions will appear!", JLabel.CENTER), BorderLayout.CENTER);
    }

    /**
     * Method that adds the panels to each section of the frame
     *
     * @param boardPanel BoardPanel, the Monopoly Board Panel
     * @param playerPanel PlayerPanel, the Monopoly Player Panel
     */
    public void addPanels(BoardPanel boardPanel, PlayerPanel playerPanel) {
        this.add(boardPanel, BorderLayout.CENTER);
        this.add(playerPanel, BorderLayout.LINE_END);
        this.add(instructionPanel, BorderLayout.PAGE_END);

        this.pack();
        this.setVisible(true);
    }

    /**
     *
     */
    @Override
    public void update() {

    }
}

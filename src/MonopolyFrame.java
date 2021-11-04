import javax.swing.*;
import java.awt.*;

public class MonopolyFrame extends JFrame implements MonopolyView {

    private MonopolyModel model;

    private static final int MAX_PLAYERS = 4;

    /**
     *
     * @param model
     */
    public MonopolyFrame(MonopolyModel model) {
        super("Monopoly");
        this.model = model;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(1600,1000));
    }

    public void addPanels(BoardPanel boardPanel, PlayerPanel playerPanel) {
        this.add(boardPanel, BorderLayout.LINE_START);
        this.add(playerPanel, BorderLayout.LINE_END);
        this.setVisible(true);
    }

    /**
     *
     */
    @Override
    public void update() {

    }
}

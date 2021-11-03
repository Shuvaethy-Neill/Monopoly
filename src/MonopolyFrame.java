import javax.swing.*;
import java.awt.*;

public class MonopolyFrame extends JFrame implements MonopolyView {

    private MonopolyModel model;

    /**
     *
     * @param model
     */
    public MonopolyFrame(MonopolyModel model, BoardPanel board, PlayerPanel player) {
        super("Monopoly");
        this.model = model;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());



        this.setVisible(true);
    }

    /**
     *
     */
    @Override
    public void update() {

    }
}

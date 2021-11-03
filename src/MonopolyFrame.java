import javax.swing.*;
import java.awt.*;

public class MonopolyFrame extends JFrame implements MonopolyView {

    private MonopolyModel model;

    /**
     *
     * @param model
     */
    public MonopolyFrame(MonopolyModel model) {
        super("Monopoly");
        this.model = model;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add();

        update();

        this.setVisible(true);
    }

    private int getNumPlayers() {

    }

    /**
     *
     */
    @Override
    public void update() {

    }

    @Override
    public void handleMonopolyStatusUpdate() {

    }
}

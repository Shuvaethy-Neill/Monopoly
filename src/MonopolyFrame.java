import javax.swing.*;

public class MonopolyFrame extends JFrame implements MonopolyView {

    private MonopolyModel model;

    /**
     *
     * @param model
     */
    public MonopolyFrame(MonopolyModel model) {
        this.model = model;
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

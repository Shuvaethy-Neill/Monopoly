import javax.swing.*;

public class PlayerPanel extends JTabbedPane implements MonopolyView {

    private MonopolyModel model;

    public PlayerPanel(MonopolyModel model) {
        super();
        this.model = model;
    }
    @Override
    public void update() {

    }
}

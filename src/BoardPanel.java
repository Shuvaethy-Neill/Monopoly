import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel implements MonopolyView {

    private MonopolyModel model;

    private GridBagConstraints[] boardSpaceConstraints;

    public BoardPanel(MonopolyModel model) {
        super();
        this.model = model;
        this.setLayout(new GridBagLayout());


    }

    /**
     *
     */
    @Override
    public void update() {

    }
}

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

// Under construction
public class DiceDisplay extends JPanel {
    private int value = 1;
    private Dice dice;

    public DiceDisplay(int height, int width) {
        setBorder(new LineBorder(new Color(0, 0, 0)));
        setPreferredSize(new Dimension(10, 10));
    }

    public void createDices(Graphics diceModel) {
    }
}

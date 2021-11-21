import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * DiceDisplay class that represents the two dices on the Monopoly Board
 *
 * @author Shuvaethy Neill and Dorothy Tran
 * @version 1.0
 * @since 2021-10-24
 */

public class DiceDisplay extends JPanel {
    private int diceValue;
    private int dotDiameter;

    /**
     * Constructor for DiceDisplay Class
     */
    public DiceDisplay() {
        this.setPreferredSize(new Dimension(65, 65));
        this.diceValue = 1;
        this.dotDiameter = 10;
    }

    /**
     * Method that displays and sets the output of the dice after rolling
     * @param diceOutput int, value of the dice output
     */
    public void displayDiceValue(int diceOutput) {
        diceValue = diceOutput;
        // repaint() will adjust the dice face value based on the roll
        repaint();
    }

    /**
     * Method that illustrates the black dots of a die
     * @param diceModel Graphics, drawing of the model of the dice
     * @param width int, width of the dice
     * @param height int, height of the dice
     */
    private void createDots(Graphics diceModel, int width, int height) {
        int totalWidth = width - dotDiameter;
        int totalHeight = height - dotDiameter;
        diceModel.fillOval(totalWidth, totalHeight, dotDiameter, dotDiameter);
    }

    /**
     * Overriden method from the JComponent Class that illustrates the dice and displays the output
     * @param g, Graphics context
     */
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);
        this.setBorder(new LineBorder(new Color(0, 0,0)));
        switch(diceValue) {
            // Dice rolls 1
            case 1:
                // Center dot
                createDots(g, getWidth()/2 + 5, getHeight()/2 + 4);
                break;
            // Dice rolls 2
            case 2:
                // Top left dot
                createDots(g, getWidth()/4 + 5, getHeight()/4 + 5);
                // Bottom right dot
                createDots(g, ((getWidth() * 3) + 5)/4, ((getHeight() * 3) + 5)/4);
                break;
            // Dice rolls 3
            case 3:
                // Center dot
                createDots(g, getWidth()/2 + 5, getHeight()/2 + 4);
                // Top left dot
                createDots(g, getWidth()/4 + 5, getHeight()/4 + 5);
                // Bottom right dot
                createDots(g, ((getWidth() * 3) + 5)/4, ((getHeight() * 3) + 5)/4);
                break;
            // Dice rolls 4
            case 4:
                // Top left dot
                createDots(g, getWidth()/4 + 5, getHeight()/4 + 5);
                // Bottom right dot
                createDots(g, ((getWidth() * 3) + 4)/4, ((getHeight() * 3) + 5)/4);
                // Bottom left dot
                createDots(g, getWidth()/4 + 5, ((getHeight() * 3) + 5)/4);
                // Top right dot
                createDots(g, (getWidth() * 3)/4, (getHeight()/4 + 5));
                break;
            // Dice rolls 5
            case 5:
                // Top left dot
                createDots(g, getWidth()/4 + 5, getHeight()/4 + 5);
                // Bottom right dot
                createDots(g, ((getWidth() * 3) + 2)/4, ((getHeight() * 3) + 5)/4);
                // Bottom left dot
                createDots(g, getWidth()/4 + 5, ((getHeight() * 3) + 5)/4);
                // Top right dot
                createDots(g, ((getWidth() * 3) + 1)/4, (getHeight()/4 + 5));
                // Center dot
                createDots(g, getWidth()/2 + 5, getHeight()/2 + 4);
                break;
            // Dice rolls 6
            case 6:
                // Top left dot
                createDots(g, getWidth()/4 + 5, getHeight()/4 + 5);
                // Top right dot
                createDots(g, ((getWidth() * 3) + 1)/4, (getHeight()/4 + 5));
                // Center left dot
                createDots(g, getWidth()/4 + 5, getHeight()/2 + 4);
                // Center right dot
                createDots(g, (getWidth() * 3)/4 + 1, getHeight()/2 + 4);
                // Bottom right dot
                createDots(g, ((getWidth() * 3) + 2)/4, ((getHeight() * 3) + 5)/4);
                // Bottom left dot
                createDots(g, getWidth()/4 + 5, ((getHeight() * 3) + 5)/4);
                break;
        }
    }
}
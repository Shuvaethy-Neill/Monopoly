import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Random;

/**
 * DiceDisplay Class that represents the two dices on the Monopoly Board
 */
public class DiceDisplay extends JPanel {
    private int diceValue;
    private int dotDiameter = 10;

    /**
     * Constructor for DiceDisplay Class
     */
    public DiceDisplay() {
        this.setPreferredSize(new Dimension(50, 50));
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
     * Method that gets the dice value that is displayed
     * @return int, dice value
     */
    public int getDiceValue() {
        return diceValue;
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
                createDots(g, getWidth()/2, getHeight()/2);
                break;
            // Dice rolls 2
            case 2:
                createDots(g, getWidth()/4,getHeight()/4);
                createDots(g, (getWidth() * 3)/4, (getHeight() * 3)/4);
                break;
            // Dice rolls 3
            case 3:
                createDots(g, getWidth()/2, getHeight()/2);
                createDots(g, getWidth()/4, getHeight()/4);
                createDots(g, (getWidth() * 3)/4, (getHeight() * 3)/4);
                break;
            // Dice rolls 4
            case 4:
                createDots(g, getWidth()/4, getHeight()/4);
                createDots(g, getWidth()/4, (getHeight() * 3)/4);
                createDots(g, (getWidth() * 3)/4, (getHeight() * 3)/4);
                createDots(g, (getWidth() * 3)/4, getHeight()/4);
            // Dice rolls 5
            case 5:
                createDots(g, getWidth()/4, getHeight()/4);
                createDots(g, getWidth()/2, getHeight()/2);
                createDots(g, getWidth()/4, (getHeight() * 3)/4);
                createDots(g, (getWidth() * 3)/4, getHeight()/4);
                createDots(g, (getWidth() * 3)/4, (getHeight() * 3)/4);
                break;
            // Dice rolls 6
            case 6:
                createDots(g, getWidth()/4, getHeight()/4);
                createDots(g, getWidth()/4, (getHeight() * 3)/4);
                createDots(g, getWidth()/4, getHeight()/2);
                createDots(g, (getWidth() * 3)/4, getHeight()/4);
                createDots(g, (getWidth() * 3)/4, getHeight()/2);
                createDots(g, (getWidth() * 3) /4, (getHeight() * 3)/4);
                break;
        }
    }
}
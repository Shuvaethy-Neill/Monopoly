import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class DiceDisplay extends JPanel {
    private int diceValue = 1;
    private int dotDiameter = 10;

    public DiceDisplay() {
        this.setPreferredSize(new Dimension(60, 60));
    }

    public void setDiceValue(int value) {
        diceValue = value;
        // repaint() will adjust the dice face value based on the roll
        repaint();
    }

    /**
     * Method draws the black dots of a dice
     *
     * @param diceModel
     * @param width
     * @param height
     */
    private void createDots(Graphics diceModel, int width, int height) {
        int totalWidth = width - dotDiameter;
        int totalHeight = height - dotDiameter;
        diceModel.fillOval(totalWidth, totalHeight, dotDiameter, dotDiameter);
    }

    @Override
    public void paintComponent(Graphics diceModel) {
        diceModel.setColor(Color.WHITE);
        diceModel.fillRect(0, 0, getWidth(), getHeight());
        diceModel.setColor(Color.BLACK);
        this.setBorder(new LineBorder(new Color(0, 0,0)));
        switch(diceValue) {
            // Dice rolls 1
            case 1:
                createDots(diceModel, getWidth()/2, getHeight()/2);
                break;
            // Dice rolls 2
            case 2:
                createDots(diceModel, getWidth()/4,getHeight()/4);
                createDots(diceModel, (getWidth() * 3)/4, (getHeight() * 3)/4);
                break;
            // Dice rolls 3
            case 3:
                createDots(diceModel, getWidth()/2, getHeight()/2);
                createDots(diceModel, getWidth()/4, getHeight()/4);
                createDots(diceModel, (getWidth() * 3)/4, (getHeight() * 3)/4);
                break;
            // Dice rolls 4
            case 4:
                createDots(diceModel, getWidth()/4, getHeight()/4);
                createDots(diceModel, getWidth()/4, (getHeight() * 3)/4);
                createDots(diceModel, (getWidth() * 3)/4, (getHeight() * 3)/4);
                createDots(diceModel, (getWidth() * 3)/4, getHeight()/4);
            // Dice rolls 5
            case 5:
                createDots(diceModel, getWidth()/4, getHeight()/4);
                createDots(diceModel, getWidth()/2, getHeight()/2);
                createDots(diceModel, getWidth()/4, (getHeight() * 3)/4);
                createDots(diceModel, (getWidth() * 3)/4, getHeight()/4);
                createDots(diceModel, (getWidth() * 3)/4, (getHeight() * 3)/4);
                break;
            // Dice rolls 6
            case 6:
                createDots(diceModel, getWidth()/4, getHeight()/4);
                createDots(diceModel, getWidth()/4, (getHeight() * 3)/4);
                createDots(diceModel, getWidth()/4, getHeight()/2);
                createDots(diceModel, (getWidth() * 3)/4, getHeight()/4);
                createDots(diceModel, (getWidth() * 3)/4, getHeight()/2);
                createDots(diceModel, (getWidth() * 3) /4, (getHeight() * 3)/4);
                break;
        }
    }
}
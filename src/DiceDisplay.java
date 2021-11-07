import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class DiceDisplay extends JPanel {
    private int diceValue = 1;
    private int diameter = 10;

    public DiceDisplay() {
        setPreferredSize(new Dimension(60, 60));
    }

    private void createDots(Graphics diceModel, int w, int h) {
        int totalWidth = w - diameter;
        int totalHeight = h - diameter;
        diceModel.fillOval(totalWidth/2, totalHeight/2, diameter, diameter);
    }

    public void setDiceValue(int value) {
        diceValue = value;
        repaint();
    }

    @Override
    public void paintComponent(Graphics diceModel) {
        super.paintComponent(diceModel);
        diceModel.setColor(Color.WHITE);
        diceModel.fillRect(0, 0, getWidth(), getHeight());
        diceModel.setColor(Color.BLACK);
        setBorder(new LineBorder(new Color(0, 0,0)));
        switch(diceValue) {
            case 1:
                createDots(diceModel, getWidth()/2, getHeight()/2);
                break;
            case 2:
                createDots(diceModel, getWidth()/4,getHeight()/4);
                createDots(diceModel, getWidth() * 3/4, getHeight() * 3/4);
                break;
            case 3:
                createDots(diceModel, getWidth()/2, getHeight()/2);
                createDots(diceModel, getWidth()/4, getHeight()/4);
                createDots(diceModel, getWidth() * 3/4, getHeight() * 3/4);
                break;
            case 4:
                createDots(diceModel, getWidth()/4, getHeight()/4);
                createDots(diceModel, getWidth() * 3/4, getHeight() * 3/4);
                createDots(diceModel, getWidth()/4, getHeight()/4);
                createDots(diceModel, getWidth() * 3/4, getHeight() * 3/4);
            case 5:
                createDots(diceModel, getWidth()/4, getHeight()/4);
                createDots(diceModel, getWidth() * 3/4, getHeight() * 3/4);
                createDots(diceModel, getWidth()/4, getHeight()/4);
                createDots(diceModel, getWidth() * 3/4, getHeight() * 3/4);
                createDots(diceModel, getWidth()/4, getHeight()/4);
            case 6:
                createDots(diceModel, getWidth()/4, getHeight()/4);
                createDots(diceModel, 3 * getWidth()/4, 3* getHeight()/4);
                createDots(diceModel, 3 * getWidth()/4, getHeight()/4);
                createDots(diceModel, getWidth()/4, 3*getHeight()/4);
                createDots(diceModel, getWidth()/4, getHeight()/2);
                createDots(diceModel, 3 * getWidth()/4, getHeight()/2);
                break;
        }
    }
}
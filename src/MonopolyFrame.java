import javax.swing.*;
import java.awt.*;

/**
 * MonopolyFrame Class that extends from the JFrame Class and implements from the
 * MonopolyView Interface representing the GUI Frame
 *
 * @author Evan Smedley
 * @version 1.0
 * @since 2021-10-22
 */
public class MonopolyFrame extends JFrame implements MonopolyView {

    private MonopolyModel model;
    private JPanel instructionPanel;
    private JPanel buttonPanel;
    private JPanel dicePanel;
    private static final int MAX_PLAYERS = 4;
    private JButton rollButton, buyButton, helpButton, passButton;
    private JTextArea instructionInfo;
    private Dice dice;
    private DiceDisplay dice1, dice2;

    /**
     * Constructor for the MonopolyFrame Class
     *
     * @param model MonopolyModel
     */
    public MonopolyFrame(MonopolyModel model) {
        super("The Monopoly Game!");
        this.model = model;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1100,1000));
        this.model.addView(this);

        rollButton = new JButton("Roll Dice");
        rollButton.setPreferredSize(new Dimension(250, 50));
        rollButton.addActionListener( e -> {
            dice1.diceRoll();
            dice2.diceRoll();
            instructionInfo.setText("\n Rolling the Dices!\n You rolled : " + dice1.getDiceValue() + " & " + dice2.getDiceValue());
            rollButton.setEnabled(false);
        });

        buyButton = new JButton("Buy Property");
        buyButton.setPreferredSize(new Dimension(250, 50));
        buyButton.addActionListener( e -> model.play(buyButton.getText()));

        //goes with buy functionality
        helpButton = new JButton("Help");
        helpButton.setPreferredSize(new Dimension(250, 50));
        helpButton.addActionListener( e -> model.play(helpButton.getText()));

        passButton = new JButton("Pass");
        passButton.setPreferredSize(new Dimension(250, 50));
        passButton.addActionListener( e -> {
            rollButton.setEnabled(true);
            instructionInfo.setText("Your turn is now over! Passing to the next player");
            model.play(passButton.getText());
        });

        buttonPanel = new JPanel(new GridLayout(2, 2));
        buttonPanel.add(rollButton);
        buttonPanel.add(buyButton);
        buttonPanel.add(helpButton);
        buttonPanel.add(passButton);

        instructionPanel = new JPanel(new GridLayout(1, 2));
        instructionPanel.setPreferredSize(new Dimension(1000,100));
        instructionPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Instruction box that will continuously update when a user makes a move
        instructionInfo = new JTextArea();
        instructionInfo.setColumns(20);
        instructionInfo.setRows(5);
        instructionInfo.setEditable(false);
        instructionInfo.setPreferredSize(new Dimension(500, 50));
        instructionInfo.setText("\n Welcome to the Monopoly Game! \n This is where instructions and buttons to make decisions will appear!");
        instructionPanel.add(instructionInfo, BorderLayout.SOUTH);
        instructionPanel.add(buttonPanel, BorderLayout.NORTH);

        // Panel to contain the 2 dices
        dicePanel = new JPanel(new FlowLayout());
        dice1 = new DiceDisplay();
        dice2 = new DiceDisplay();
        dicePanel.add(dice1);
        dicePanel.add(dice2);
        instructionPanel.add(dicePanel);
    }

    /**
     * Method that adds the panels to each section of the frame
     *
     * @param boardPanel BoardPanel, the Monopoly Board Panel
     * @param playerPanel PlayerPanel, the Monopoly Player Panel
     */
    public void addPanels(BoardPanel boardPanel, PlayerPanel playerPanel) {
        this.add(boardPanel, BorderLayout.CENTER);
        this.add(playerPanel, BorderLayout.LINE_END);
        this.add(instructionPanel, BorderLayout.PAGE_END);

        this.pack();
        this.setVisible(true);
    }

    /**
     *
     */
    @Override
    public void update() {

    }
}

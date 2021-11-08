import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

/**
 * MonopolyFrame Class that extends from the JFrame Class and implements from the
 * MonopolyView Interface representing the GUI Frame
 *
 * @author Evan Smedley,Harsismran Kanwar, Dorothy Tran, Shuvaethy Neill
 * @version 1.0
 * @since 2021-10-22
 */
public class MonopolyFrame extends JFrame implements MonopolyView {

    private MonopolyModel model;
    private JPanel instructionPanel;
    private JPanel buttonPanel;
    private JPanel dicePanel;
    private JButton startButton, rollButton, buyButton, helpButton, passButton, quitButton;
    private JTextArea instructionInfo;
    private DiceDisplay dice1;
    private DiceDisplay dice2;

    /**
     * Constructor for the MonopolyFrame Class
     * @param model MonopolyModel
     */
    public MonopolyFrame(MonopolyModel model) {
        super("The Monopoly Game!");
        this.model = model;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1100,1000));
        this.model.addView(this);

        startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(50, 50));
        startButton.addActionListener( e -> { //instructionInfo.setText("Starting game....\n" + model.start() + " will start!" );
            model.start();
            startButton.setEnabled(false);
        });

        rollButton = new JButton("Roll Dice");
        rollButton.setPreferredSize(new Dimension(50, 50));
        rollButton.setEnabled(false);
        rollButton.addActionListener( e ->  model.play(rollButton.getText()));

        buyButton = new JButton("Buy Property");
        buyButton.setPreferredSize(new Dimension(50, 50));
        buyButton.setEnabled(false);
        buyButton.addActionListener( e -> model.play(buyButton.getText()));

        helpButton = new JButton("Help");
        helpButton.setPreferredSize(new Dimension(50, 50));
        helpButton.setEnabled(false);
        helpButton.addActionListener( e -> model.play(helpButton.getText()));

        passButton = new JButton("Pass");
        passButton.setPreferredSize(new Dimension(50, 50));
        passButton.setEnabled(false);
        passButton.addActionListener( e -> {
            rollButton.setEnabled(true);
            model.play(passButton.getText());
        });

        quitButton = new JButton("Quit");
        quitButton.setPreferredSize(new Dimension(50, 50));
        quitButton.addActionListener( e -> model.play(quitButton.getText()));

        buttonPanel = new JPanel(new GridLayout(3, 2));
        buttonPanel.add(startButton);
        buttonPanel.add(rollButton);
        buttonPanel.add(buyButton);
        buttonPanel.add(helpButton);
        buttonPanel.add(passButton);
        buttonPanel.add(quitButton);

        instructionPanel = new JPanel(new GridLayout(1, 3));
        instructionPanel.setPreferredSize(new Dimension(1000,150));
        instructionPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Instruction box that will continuously update when a user makes a move
        instructionInfo = new JTextArea();
        instructionInfo.setColumns(20);
        instructionInfo.setRows(5);
        instructionInfo.setEditable(false);
        instructionInfo.setPreferredSize(new Dimension(500, 50));
        instructionInfo.setText("\n Welcome to the Monopoly Game! \n Please press the \"Start\" button to start a game!");
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
     * Method updates the MonopolyEvent
     */
    @Override
    public void update(MonopolyEvent e) {
        int[] dice = e.getDice().getDice();
        dice1.displayDiceValue(dice[0]);
        dice2.displayDiceValue(dice[1]);

        instructionInfo.setText(e.getInstruction());

        if (e.status == MonopolyModel.Status.PLAYING){
            buyButton.setEnabled(true);
            if(!e.getDice().isDouble()) {
                rollButton.setEnabled(false);
            }
        }
        else if(e.status == MonopolyModel.Status.BANKRUPT) {
            rollButton.setEnabled(false);
            buyButton.setEnabled(false);
            passButton.setEnabled(false);
            helpButton.setEnabled(false);

            JOptionPane.showMessageDialog(this, "Game over! Thanks for playing!");
            //System.exit(0); having this enabled ruins the test cases
        }
        else if(e.status == MonopolyModel.Status.BANKRUPT2) {
            JOptionPane.showMessageDialog(this, "Current player has been eliminated.");
            instructionInfo.setText("Next player's turn!");

            rollButton.setEnabled(true);
            buyButton.setEnabled(false);
            passButton.setEnabled(false);
            helpButton.setEnabled(true);
        }
        else if(e.status == MonopolyModel.Status.QUITTING) {
            int input = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit the program?",
                    "Exit Program Message Box", JOptionPane.YES_NO_OPTION);
            if (input == JOptionPane.YES_OPTION) {
                this.dispose();
            }

            rollButton.setEnabled(true);
            buyButton.setEnabled(false);
            passButton.setEnabled(false);
            helpButton.setEnabled(true);
        }
        else{
            rollButton.setEnabled(true);
            buyButton.setEnabled(false);
            passButton.setEnabled(true);
            helpButton.setEnabled(true);
        }
    }
}

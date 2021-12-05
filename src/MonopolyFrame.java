import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * MonopolyFrame Class that extends from the JFrame Class and implements from the
 * MonopolyView Interface representing the GUI Frame
 *
 * @author Evan Smedley, Harsismran Kanwar, Dorothy Tran, Shuvaethy Neill
 * @version 1.0
 * @since 2021-10-22
 */
public class MonopolyFrame extends JFrame implements MonopolyView {

    private MonopolyModel model;
    private JPanel instructionPanel;
    private JPanel buttonPanel;
    private JPanel dicePanel;
    private JButton startButton, rollButton, buyButton, helpButton, passButton, buildButton;
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
        this.setPreferredSize(new Dimension(900,800));
        this.model.addView(this);

        startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(200, 50));
        startButton.addActionListener( e -> {
            model.start();
            startButton.setEnabled(false);
        });

        rollButton = new JButton("Roll Dice");
        rollButton.setPreferredSize(new Dimension(200, 50));
        rollButton.setEnabled(false);
        rollButton.addActionListener( e ->  model.play(rollButton.getText()));

        buyButton = new JButton("Buy");
        buyButton.setPreferredSize(new Dimension(200, 50));
        buyButton.setEnabled(false);
        buyButton.addActionListener( e -> model.play(buyButton.getText()));

        helpButton = new JButton("Help");
        helpButton.setPreferredSize(new Dimension(200, 50));
        helpButton.setEnabled(false);
        helpButton.addActionListener( e -> model.play(helpButton.getText()));

        passButton = new JButton("Pass");
        passButton.setPreferredSize(new Dimension(200, 50));
        passButton.setEnabled(false);
        passButton.addActionListener( e -> {
            rollButton.setEnabled(true);
            model.play(passButton.getText());
        });

        buildButton = new JButton("Build House/Hotel");
        buildButton.setPreferredSize(new Dimension(200, 50));
        buildButton.setEnabled(false);
        buildButton.addActionListener( e -> model.play(buildButton.getText()));

        buttonPanel = new JPanel(new GridLayout(3, 2));
        buttonPanel.setPreferredSize(new Dimension(400, 150));
        buttonPanel.add(startButton);
        buttonPanel.add(rollButton);
        buttonPanel.add(buyButton);
        buttonPanel.add(helpButton);
        buttonPanel.add(passButton);
        buttonPanel.add(buildButton);

        instructionPanel = new JPanel(new BorderLayout());
        instructionPanel.setPreferredSize(new Dimension(1100,150));
        instructionPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Instruction box that will continuously update when a user makes a move
        instructionInfo = new JTextArea();
        instructionInfo.setColumns(20);
        instructionInfo.setRows(5);
        instructionInfo.setEditable(false);
        instructionInfo.setPreferredSize(new Dimension(425, 150));
        instructionInfo.setText("Welcome to the Monopoly Game! \nPlease press the \"Start\" button to start a game!");
        instructionPanel.add(instructionInfo, BorderLayout.WEST);
        instructionPanel.add(buttonPanel, BorderLayout.CENTER);

        // Panel to contain the 2 dice
        dicePanel = new JPanel();
        dicePanel.setPreferredSize(new Dimension(250, 150));
        dice1 = new DiceDisplay();
        dice2 = new DiceDisplay();
        dicePanel.add(dice1);
        dicePanel.add(dice2);
        instructionPanel.add(dicePanel, BorderLayout.EAST);

        // Create menu bar and add option to save or quit
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        menuBar.add(menu);

        JMenuItem saveItem = new JMenuItem("Save Game");
        saveItem.addActionListener(e -> {
            model.saveSerialize("MonopolyGame.txt");
            System.out.println("SAVED!");
            System.exit(0);
        });
        menu.add(saveItem);

        JMenuItem quitItem = new JMenuItem("Quit");
        quitItem.addActionListener(e -> model.play(quitItem.getText()));
        menu.add(quitItem);

        this.setJMenuBar(menuBar);
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

        //no way of updating if a player can build or not if we disable button so i think we should just show the error in the panel
        /*
        if(!((Player)(e.getCurrentPlayers().get(e.getPlayer()))).getCanBuild()){
            buildButton.setEnabled(false);
        }
        else{
            buildButton.setEnabled(true);
        }

         */

        if (e.status == MonopolyModel.Status.PLAYING){
            buyButton.setEnabled(true);
            buildButton.setEnabled(true);
            if(!e.getDice().isDouble()) {
                rollButton.setEnabled(false);
            }
        }

        else if(e.status == MonopolyModel.Status.BUILDING) {
            if(((Player)(e.getCurrentPlayers().get(e.getPlayer()))).getCanBuild()){
                //uhmmmmmmm
            }

            rollButton.setEnabled(true);
            buyButton.setEnabled(false);
            passButton.setEnabled(true);
            helpButton.setEnabled(true);
        }
        else if(e.status == MonopolyModel.Status.BANKRUPT) {
            rollButton.setEnabled(false);
            buyButton.setEnabled(false);
            passButton.setEnabled(false);
            helpButton.setEnabled(false);

            JOptionPane.showMessageDialog(this, "Game over! Thanks for playing!");
            System.exit(0);
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
        else if (e.status == MonopolyModel.Status.JAIL){
            rollButton.setEnabled(true);
            buyButton.setEnabled(true);
            passButton.setEnabled(false);
            helpButton.setEnabled(false);
        }
        else{
            rollButton.setEnabled(true);
            buyButton.setEnabled(false);
            passButton.setEnabled(true);
            helpButton.setEnabled(true);
        }
    }
}
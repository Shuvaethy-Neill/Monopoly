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
    private static final int MAX_PLAYERS = 4;
    private JButton rollButton, buyButton, rentButton, passButton;
    private JTextArea instructionInfo;

    /**
     * Constructor for the MonopolyFrame Class
     *
     * @param model MonopolyModel
     */
    public MonopolyFrame(MonopolyModel model) {
        super("Monopoly");
        this.model = model;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1600,1600));
        this.model.addView(this);

        rollButton = new JButton("Roll Dice");
        rollButton.setPreferredSize(new Dimension(50, 50));
        rollButton.addActionListener( e -> {
        });

        buyButton = new JButton("Buy Property");
        buyButton.setPreferredSize(new Dimension(150, 50));
        buyButton.addActionListener( e -> {
        });

        //goes with buy functionality
        rentButton = new JButton("Pay Rent");
        buyButton.setPreferredSize(new Dimension(150, 50));
        buyButton.addActionListener( e -> {
        });

        passButton = new JButton("Pass");
        passButton.setPreferredSize(new Dimension(150, 50));
        passButton.addActionListener( e -> {
        });



        buttonPanel = new JPanel(new GridLayout(2, 2));
        buttonPanel.add(rollButton);
        buttonPanel.add(buyButton);
        buttonPanel.add(rentButton);
        buttonPanel.add(passButton);

        instructionPanel = new JPanel(new GridLayout(1, 2));
        instructionPanel.setPreferredSize(new Dimension(1600,200));
        instructionPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Instruction box that will continuously update when a user makes a move
        instructionInfo = new JTextArea();
        instructionInfo.setColumns(20);
        instructionInfo.setRows(5);
        instructionInfo.setEditable(false);
        instructionInfo.setPreferredSize(new Dimension(500, 50));
        instructionInfo.setText("This is where instructions and buttons to make decisions will appear!");

        instructionPanel.add(instructionInfo, BorderLayout.SOUTH);
        instructionPanel.add(buttonPanel, BorderLayout.NORTH);

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

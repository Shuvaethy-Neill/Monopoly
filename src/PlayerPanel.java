import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayerPanel extends JTabbedPane implements MonopolyView {

    private MonopolyModel model;

    private JPanel[] individualPlayerPanels;

    private Color[] colours;


    public PlayerPanel(MonopolyModel model) {
        super();
        this.model = model;
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.model.addView(this);

        colours = new Color[] {(new Color(255, 255, 222)), (new Color(238, 248, 255)), (new Color(255, 241, 238)), (new Color(248, 238, 255))};

        initializeLayout();
    }

    /**
     *
     */
    private void initializeLayout() {
        individualPlayerPanels = new JPanel[model.getPlayers().size()];
        for (int i = 0; i < model.getPlayers().size(); i++) {
            individualPlayerPanels[i] = new JPanel();
            individualPlayerPanels[i].setPreferredSize(new Dimension(450,800));
            individualPlayerPanels[i].setLayout(new BoxLayout(individualPlayerPanels[i], BoxLayout.PAGE_AXIS));
            individualPlayerPanels[i].setBackground(colours[i]);
            updatePlayerList(model.getPlayers(), i);
            this.addTab(model.getPlayers().get(i).getName(), individualPlayerPanels[i]);
        }
    }

    /**
     *
     * @param playerIndex
     */
    private void updatePlayerList(ArrayList<Player> players, int playerIndex) {
        this.addTab(players.get(playerIndex).getName(), individualPlayerPanels[playerIndex]);
        Player currentPlayer = players.get(playerIndex);
        individualPlayerPanels[playerIndex].removeAll();
        DefaultListModel<String> listModel = new DefaultListModel<>();

        individualPlayerPanels[playerIndex].add(new JLabel("Money: " + currentPlayer.getMoney()));
        individualPlayerPanels[playerIndex].add(new JLabel("Position: " + model.getPieces()[currentPlayer.getPosition()].getName()));

        if (currentPlayer.getProperties().size() == 0) {
            individualPlayerPanels[playerIndex].add(new JLabel("Properties: None"));
        } else {
            individualPlayerPanels[playerIndex].add(new JLabel("Properties:"));
            for (Property p : currentPlayer.getProperties()) {
                listModel.addElement(p.toString());
            }
            JList<String> list = new JList<>(listModel);
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list.setSelectedIndex(0);
            list.setVisibleRowCount(5);
            JScrollPane listScrollPane = new JScrollPane(list);
            list.setBackground(colours[playerIndex]);
            individualPlayerPanels[playerIndex].add(listScrollPane);
        }
    }

    /**
     *
     */
    @Override
    public void update(MonopolyEvent e) {
        this.removeAll();
        for (int i = 0; i < e.getCurrentPlayers().size(); i++) {
            updatePlayerList(e.getCurrentPlayers(),i);
        }
    }
}
import javax.swing.*;

public class PlayerPanel extends JTabbedPane implements MonopolyView {

    private MonopolyModel model;

    private JPanel[] individualPlayerPanels;

    public PlayerPanel(MonopolyModel model) {
        super();
        this.model = model;

        initializeLayout();
        update();
    }

    /**
     *
     */
    private void initializeLayout() {
        individualPlayerPanels = new JPanel[model.getPlayers().size()];
        for (int i = 0; i < model.getPlayers().size(); i++) {
            individualPlayerPanels[i] = new JPanel();
            individualPlayerPanels[i].setLayout(new BoxLayout(individualPlayerPanels[i], BoxLayout.PAGE_AXIS));
            updatePlayerList(i);
            this.addTab(model.getPlayers().get(i).getName(), individualPlayerPanels[i]);
        }
    }

    /**
     *
     * @param playerIndex
     */
    private void updatePlayerList(int playerIndex) {
        Player currentPlayer = model.getPlayers().get(playerIndex);
        individualPlayerPanels[playerIndex].removeAll();

        individualPlayerPanels[playerIndex].add(new JLabel("Money: " + currentPlayer.getMoney()));
        individualPlayerPanels[playerIndex].add(new JLabel("Position: " + model.getPieces()[currentPlayer.getPosition()].getName()));

        if (currentPlayer.getProperties().size() == 0) {
            individualPlayerPanels[playerIndex].add(new JLabel("Properties: None"));
        } else {
            individualPlayerPanels[playerIndex].add(new JLabel("Properties:"));
            DefaultListModel<String> listModel = new DefaultListModel<>();

            for (Property p : currentPlayer.getProperties()) {
                listModel.addElement(p.toString());
            }
            JList<String> list = new JList<>(listModel);
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list.setSelectedIndex(0);
            list.setVisibleRowCount(5);
            JScrollPane listScrollPane = new JScrollPane(list);
            individualPlayerPanels[playerIndex].add(listScrollPane);
        }
    }

    /**
     *
     */
    @Override
    public void update() {
        for (int i = 0; i < individualPlayerPanels.length; i++) {
            updatePlayerList(i);
        }
    }
}

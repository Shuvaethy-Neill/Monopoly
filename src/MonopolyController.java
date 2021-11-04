import javax.swing.*;

public class MonopolyController {
    private MonopolyModel model;
    private MonopolyFrame view;

    private static final int MAX_PLAYERS = 4;

    private int numPlayers;

    public MonopolyController(MonopolyModel model, MonopolyFrame view) {
        this.model = model;
        this.view = view;
        this.numPlayers = getNumPlayers();
        for (int i = 0; i < numPlayers; i++) {
            model.addPlayer(getPlayerInformation());
        }
    }

    private int getNumPlayers() {
        Object[] options = new Object[MAX_PLAYERS];
        for (int i = 0; i < MAX_PLAYERS; i++) {
            options[i] = i + 1;
        }
        return (int) JOptionPane.showInputDialog(
                view, "How many players are there?", "Player Selection",
                JOptionPane.PLAIN_MESSAGE, null, options, 1
        );
    }

    private Player getPlayerInformation() {
        String playerName = (String) JOptionPane.showInputDialog(
                view, "Please choose a name that is 10 characters or less.\n" +
                "If it is too long it will be truncated.",
                "Player " + (model.getPlayers().size() + 1) + " Name Selection", JOptionPane.PLAIN_MESSAGE
        );
        playerName = playerName.substring(0, Math.min(playerName.length(), 10));
        return new Player(playerName);
    }
}